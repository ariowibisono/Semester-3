package Coding;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import java.sql.*;

import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;

public class Product extends JInternalFrame {
	private JTable table;
	private JTextField tbID;
	private JTextField tbIDSup;
	private JTextField tbNama;
	private JTextField tbHarga;
	private JTextField tbStok;
	private JComboBox cbNamaSup;
	private JLabel lblIdProduk;
	private JLabel lblNewLabel;
	private JLabel lblNamaProduk;
	private JLabel lblHarga;
	private JLabel lblStok;
	private DefaultTableModel tabelModel;
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
	public Product() {
		setBounds(100, 100, 450, 508);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 720, 250);
		getContentPane().add(scrollPane);
		
		//TAMPIL HEADER TABEL
		table = new JTable();
		scrollPane.setViewportView(table);
		tabelModel = new DefaultTableModel();
        tabelModel.addColumn("ID Produk");
        tabelModel.addColumn("Nama Produk");
        tabelModel.addColumn("ID Supplier");
        tabelModel.addColumn("Harga Produk");
        tabelModel.addColumn("Stok");
        
		table.setModel(tabelModel);	
        tampilTabel();
		klikTabel();
        
        //Tampil ID Product
        lblIdProduk = new JLabel("ID Produk :");
		lblIdProduk.setBounds(130, 275, 67, 14);
		getContentPane().add(lblIdProduk);
		
		tbID = new JTextField();
		tbID.setBounds(239, 272, 315, 20);
		getContentPane().add(tbID);
		tbID.setColumns(10);
		
		//Tampil ID Supplier
		lblNewLabel = new JLabel("ID Supplier :");
		lblNewLabel.setBounds(130, 307, 67, 14);
		getContentPane().add(lblNewLabel);
		
		tbIDSup = new JTextField();
		tbIDSup.setColumns(10);
		tbIDSup.setBounds(239, 304, 89, 20);
		getContentPane().add(tbIDSup);
		
		//Tampil Nama product
		lblNamaProduk = new JLabel("Nama Produk :");
		lblNamaProduk.setBounds(130, 338, 89, 14);
		getContentPane().add(lblNamaProduk);
		
		tbNama = new JTextField();
		tbNama.setColumns(10);
		tbNama.setBounds(239, 335, 315, 20);
		getContentPane().add(tbNama);
		
		//Tampil Harga Product
		lblHarga = new JLabel("Harga :");
		lblHarga.setBounds(130, 369, 46, 14);
		getContentPane().add(lblHarga);
		
		tbHarga = new JTextField();
		tbHarga.setColumns(10);
		tbHarga.setBounds(239, 366, 315, 20);
		getContentPane().add(tbHarga);
		
		//Tampil Stok Product
		lblStok = new JLabel("Stok :");
		lblStok.setBounds(130, 400, 46, 14);
		getContentPane().add(lblStok);
			
		tbStok = new JTextField();
		tbStok.setColumns(10);
		tbStok.setBounds(239, 397, 315, 20);
		getContentPane().add(tbStok);
		
		//Tombol Tambah
		JButton btnTambah = new JButton("Tambah");
		btnTambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try
			        {
			        Connection config = Config.getCon();
			        String query = "INSERT INTO products VALUES(?,?,?,?,?)";
			        PreparedStatement prepare = config.prepareStatement(query);
			        
			        prepare.setString(1, tbID.getText());
			        prepare.setString(2, tbNama.getText());
			        prepare.setString(3, tbIDSup.getText());
			        prepare.setInt(4, Integer.parseInt(tbHarga.getText()));
			        prepare.setInt(5, 0);

			        prepare.executeUpdate();
			        JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
			        prepare.close();
			        }
			        
			        catch(Exception ex)
			        {
			            JOptionPane.showMessageDialog(null, "Data gagal disimpan");
			            System.out.println(ex);
			        }
			        finally
			        {
			            tampilTabel();
			            //refresh();
			        }
			}
		});
		btnTambah.setBounds(239, 427, 89, 23);
		getContentPane().add(btnTambah);
		
		//Tombol Ubah
		JButton button = new JButton("Ubah");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
			        Connection config = Config.getCon();
			        String query = "UPDATE products SET  Nama_Products = ?, idSup = ?, harga = ?, stok = ? WHERE idProducts = ?";
			        PreparedStatement prepare = config.prepareStatement(query);
			       
			        prepare.setString(1, tbNama.getText());
			        prepare.setString(2, tbIDSup.getText());
			        prepare.setInt(3, Integer.parseInt(tbHarga.getText()));
			        prepare.setInt(4, Integer.parseInt(tbStok.getText()));
			        prepare.setString(5, tbID.getText());
			        
			        prepare.executeUpdate();
			        JOptionPane.showMessageDialog(null, "Data berhasil diubah");
			        prepare.close();
			        }
			        
			        catch(Exception ex)
			        {
			            JOptionPane.showMessageDialog(null, "Data gagal diubah");
			            System.out.println(ex);
			        }
			        finally
			        {
			            tampilTabel();
			            tbID.setEnabled(true);
			            //refresh();            
			        }
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(338, 427, 89, 23);
		getContentPane().add(button);
		
		//Tombol Hapus
		JButton button_1 = new JButton("Hapus");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try
			        {
			            Connection config = Config.getCon();
			            String query = "DELETE FROM products WHERE idProducts = ?";
			            PreparedStatement prepare = config.prepareStatement(query);
			            
			            prepare.setString(1, tbID.getText());
			            prepare.executeUpdate();
			            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
			            prepare.close();
			        }
			        catch(Exception ex)
			        {
			            JOptionPane.showMessageDialog(null, "Data gagal dihapus" + tbID.getText());
			            System.out.println(ex);
			        }
			        finally
			        {
			            tampilTabel();
			            tbID.setEnabled(true);
			            //refresh();
			        }
			}
		});
		button_1.setBounds(437, 427, 89, 23);
		getContentPane().add(button_1);
		
		//COMBO BOX
		cbNamaSup = new JComboBox();
		cbNamaSup.setBounds(339, 304, 216, 20);
		getContentPane().add(cbNamaSup);
		
		//tampil kode supplier dari nama supplier yang dipilih di combo box
		cbNamaSup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nama=cbNamaSup.getSelectedItem().toString();
		        try {
		            Connection config = Config.getCon();
		            Statement st = config.createStatement();
		            ResultSet rs = st.executeQuery("select idSuppliers from suppliers where Nama_Suppliers ='"+nama+"'");
		            while (rs.next()) {
		                tbIDSup.setText(rs.getString(1));
		            }
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, ex);
		        }
			}
		});
		// end
		
		//akses method isiSupplier
		isiSupplier();
		//model comboBox di set sesuai comboBoxModel pada method isiSupplier
		cbNamaSup.setModel(model);
		
		

	}
	
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
		        String name = (String) tabelModel.getValueAt(a, 1);
		        tbNama.setText(name);
		        String ids = (String) tabelModel.getValueAt(a, 2);
		        tbIDSup.setText(ids);
		        int har = (int) tabelModel.getValueAt(a, 3);
		        tbHarga.setText(""+har);
		        int sto = (int) tabelModel.getValueAt(a, 4);
		        tbStok.setText(""+sto);
		        
		        tbID.setEnabled(false);
			}
		});
	}
	
	public void tampilTabel()
    {
        tabelModel.getDataVector().removeAllElements();
        tabelModel.fireTableDataChanged();
        try
        {
            Connection config = Config.getCon();
            Statement state = config.createStatement();
            String query = "SELECT * FROM products";
            
            ResultSet rs = state.executeQuery(query);
            
            while(rs.next())
            {
                Object obj[] = new Object[5];
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                obj[2] = rs.getString(3);
                obj[3] = rs.getInt(4);
                obj[4] = rs.getInt(5);
                
                tabelModel.addRow(obj);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
	
	//Menampilkan Nama supplier ke dalam ComboBox
	public void isiSupplier()
	{
		try {
            Connection config = Config.getCon();
            Statement st = config.createStatement();
            ResultSet rs = st.executeQuery("select Nama_Suppliers from suppliers");
            while (rs.next()) {
                model.addElement(rs.getString(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
	}

}