package Coding;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Suplier extends JInternalFrame {
	private JTable table;
	private JTextField tbID;
	private JTextField tbNama;
	private JLabel lblIDSup;
	private JLabel lbNamaSupplier;
	private DefaultTableModel tabelModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User frame = new User();
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
	public Suplier() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		//Tampil ID Supplier
		lblIDSup = new JLabel("ID Supplier :");
		lblIDSup.setBounds(130, 275, 67, 14);
		getContentPane().add(lblIDSup);
		
		tbID = new JTextField();
		tbID.setBounds(239, 272, 315, 20);
		getContentPane().add(tbID);
		tbID.setColumns(10);
		
		//Tampil Nama Supplier
		lbNamaSupplier = new JLabel("Nama Supplier :");
		lbNamaSupplier.setBounds(130, 307, 89, 14);
		getContentPane().add(lbNamaSupplier);
		
		tbNama = new JTextField();
		tbNama.setColumns(10);
		tbNama.setBounds(239, 304, 315, 20);
		getContentPane().add(tbNama);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(70, 11, 600, 250);
		getContentPane().add(scrollPane_1);
		
		table = new JTable();
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
		        
		        tbID.setEnabled(false);
			}
		});
		
		scrollPane_1.setViewportView(table);	
        tabelModel = new DefaultTableModel();
        tabelModel.addColumn("Id Suppliers");
        tabelModel.addColumn("Nama Suppliers");
		table.setModel(tabelModel);	
        tampilTabel();
		
        //Tombol Tambah
      	JButton btnTambah = new JButton("Tambah");
      	btnTambah.addActionListener(new ActionListener() {
      		public void actionPerformed(ActionEvent e) {
      			 try
      		        {
      		        Connection config = Config.getCon();
      		        String query = "INSERT INTO suppliers VALUES(?,?)";
      		        PreparedStatement prepare = config.prepareStatement(query);
      		        
      		        prepare.setString(1, tbID.getText());
      		        prepare.setString(2, tbNama.getText());

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
      	btnTambah.setBounds(239, 339, 89, 23);
      	getContentPane().add(btnTambah);
      	
      	//Tombol Ubah
      	JButton button = new JButton("Ubah");
      	button.addActionListener(new ActionListener() {
      		public void actionPerformed(ActionEvent e) {
      			try{
      		        Connection config = Config.getCon();
      		        String query = "UPDATE suppliers SET Nama_Suppliers = ? WHERE idSuppliers = ?";
      		        PreparedStatement prepare = config.prepareStatement(query);
      		       
      		        prepare.setString(1, tbNama.getText());
      		        prepare.setString(2, tbID.getText());
      		        
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
      	button.setBounds(338, 339, 89, 23);
      	getContentPane().add(button);
      	
      	//Tombol Hapus
      	JButton button_1 = new JButton("Hapus");
      	button_1.addActionListener(new ActionListener() {
      		public void actionPerformed(ActionEvent e) {
      			 try
      		        {
      		            Connection config = Config.getCon();
      		            String query = "DELETE FROM suppliers WHERE idSuppliers = ?";
      		            PreparedStatement prepare = config.prepareStatement(query);
      			            
      		            prepare.setString(1, tbID.getText());
      		            prepare.executeUpdate();
      		            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
      		            prepare.close();
      		        }
      		        catch(Exception ex)
      		        {
      		            JOptionPane.showMessageDialog(null, "Data gagal dihapus");
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
      	button_1.setBounds(437, 339, 89, 23);
      	getContentPane().add(button_1);
	}
	
	public void tampilTabel()
    {
        tabelModel.getDataVector().removeAllElements();
        tabelModel.fireTableDataChanged();
        try
        {
            Connection config = Config.getCon();
            Statement state = config.createStatement();
            String query = "SELECT * FROM suppliers";
            
            ResultSet rs = state.executeQuery(query);
            
            while(rs.next())
            {
                Object obj[] = new Object[2];
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                
                tabelModel.addRow(obj);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
}