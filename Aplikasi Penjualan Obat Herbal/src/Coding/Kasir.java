package Coding;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSpinner;

import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Kasir extends JInternalFrame {
	private JTable table;
	private JTextField tbID;
	private JSpinner tbKuantitas;
	private JTextField tbBayar;
	private JTextField tbStok;
	private JTextField tbStokSementara;
	private JTextField tbHargaSementara;
	private JLabel lblIdProduk;
	private JLabel lblKuantitas;
	private JLabel lblTotal;
	private JLabel lblBayar;
	private JLabel lblStok;
	private DefaultTableModel tabelModel;
	
	private Integer stok;
	private String tanggal;
	
	private int harga;
	DefaultComboBoxModel model = new DefaultComboBoxModel(); 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Product frame = new Product();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Kasir() {
		setBounds(100, 100, 450, 508);
		getContentPane().setLayout(null);
		
		ImageIcon icon = new ImageIcon("./src/images/Judul.png");
		JLabel Judul = new JLabel(icon);
		//Judul.setIcon(icon);
		Judul.setBounds(200, 0, 400, 100);
		getContentPane().add(Judul);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(95, 111, 600, 250);
		getContentPane().add(scrollPane);
		
		//TAMPIL HEADER TABEL
		table = new JTable();
		scrollPane.setViewportView(table);
		tabelModel = new DefaultTableModel();
        tabelModel.addColumn("ID Produk");
        tabelModel.addColumn("Nama Produk");
        tabelModel.addColumn("Harga Produk");
        tabelModel.addColumn("Stok");
        
		table.setModel(tabelModel);	
        tampilTabel();
		klikTabel();
        
        //Tampil ID Product
        lblIdProduk = new JLabel("ID Produk :");
		lblIdProduk.setBounds(260, 375, 67, 14);
		getContentPane().add(lblIdProduk);
		
		tbID = new JTextField();
		tbID.setBounds(359, 372, 89, 20);
		getContentPane().add(tbID);
		tbID.setColumns(10);
		
		//Tampil Kuantitas
		lblKuantitas = new JLabel("Kuantitas :");
		lblKuantitas.setBounds(260, 407, 67, 14);
		getContentPane().add(lblKuantitas);
		
		tbKuantitas = new JSpinner();
		tbKuantitas.setBounds(359, 404, 89, 20);
		getContentPane().add(tbKuantitas);
		
		//Tombol Bayar
		JButton btnTambah = new JButton("Bayar");
		btnTambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
					 Tes();
				 } catch(Exception ex) {
					 JOptionPane.showMessageDialog(null, "Data gagal disimpan");
			         System.out.println(ex);
				 }
			     
				 finally {
					 tampilTabel();
				 }
			}
		});
		btnTambah.setBounds(349, 437, 89, 23);
		getContentPane().add(btnTambah);

	}
	
	//Menetapkan Tanggal dan waktu
	public void setTanggal()
    {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		tanggal = dateFormat.format(date); //2014/08/06 15:59:48
    }
	
	public void CariJumlahStok(){
		try {
			String id = tbID.getText();
			Connection config = Config.getCon();
			 // Cek Stok <1 atau tidak
			 Statement state = config.createStatement();
			 //'"+id+"'
	        String queryStok = "SELECT harga, stok FROM products WHERE idProducts = ABE007";
	        ResultSet results = state.executeQuery(queryStok);

	        if(results.next()) { //there is a row
	       	 tbHargaSementara.setText(results.getString(1));
	       	 tbStokSementara.setText(results.getString(2));
	        }
	        harga = Integer.parseInt(tbStokSementara.getText());
	        //int jumlahbeli = Integer.parseInt(tbBayar.getText());
	        //int jumlahstok = Integer.parseInt(tbStokSementara.getText());
		} catch(Exception ex){
			
		}
	}
	
	//Event klik tabel
	public void klikTabel(){
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int a = table.getSelectedRow();
		        
		        if(a == -1)
		        {
		            return;
		        }
		        
		        String id = (String) tabelModel.getValueAt(a, 0);
		        tbID.setText(id);
		        Integer setok = (Integer) tabelModel.getValueAt(a, 3);
		        stok = setok;
			}
		});
	}
	
	public void Tes(){
		int jumlahbeli = (Integer)tbKuantitas.getValue();
		if (stok > jumlahbeli){
			setTanggal();
			try {
				// Mengurangi Stok di Database
				Connection config = Config.getCon();
		        String query = "UPDATE products SET stok = stok - ? WHERE idProducts = ?";
		        PreparedStatement prepare = config.prepareStatement(query);
		       
		        prepare.setInt(1, (Integer) tbKuantitas.getValue());
		        prepare.setString(2, tbID.getText());
		        
		        prepare.executeUpdate();
		        prepare.close();
		        
		        // Menambah ke database transaksi
		        try {
		        	Connection config2 = Config.getCon();
			        String query2 = "INSERT INTO transaksi VALUES(?,?,?)";
			        PreparedStatement prepare2 = config2.prepareStatement(query2);
			        
			        prepare2.setString(1, tanggal);
			        prepare2.setString(2, tbID.getText());
			        prepare2.setInt(3, (Integer) tbKuantitas.getValue());

			        prepare2.executeUpdate();
			        JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
			        prepare2.close();
		        } catch (Exception ex) {
		        	JOptionPane.showMessageDialog(null, "Data gagal disimpan");
		        }
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Data gagal tersimpan");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Stok tidak mencukupi");
		}
	}
	
	//Menampilkan data pada tabel
	public void tampilTabel() {
        tabelModel.getDataVector().removeAllElements();
        tabelModel.fireTableDataChanged();
        try
        {
            Connection config = Config.getCon();
            Statement state = config.createStatement();
            String query = "SELECT idProducts, Nama_Products, harga, stok FROM products";
            
            ResultSet rs = state.executeQuery(query);
            
            while(rs.next())
            {
                Object obj[] = new Object[5];
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                obj[2] = rs.getInt(3);
                obj[3] = rs.getInt(4);
                
                tabelModel.addRow(obj);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
}