package Coding;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class User extends JInternalFrame {
	private JTextField tbID;
	private JTextField tbNama;
	private JTextField tbUsername;
	private JTextField tbPassword;
	private JTable table;
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
	public User() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		//Kolom ID
		JLabel lblID = new JLabel("ID User :");
		lblID.setBounds(230, 130, 72, 14);
		getContentPane().add(lblID);
		
		tbID = new JTextField();
		tbID.setBounds(306, 127, 161, 20);
		getContentPane().add(tbID);
		tbID.setColumns(10);
		
		//Kolom Nama
		JLabel lblNama = new JLabel("Nama :");
		lblNama.setBounds(230, 155, 72, 14);
		getContentPane().add(lblNama);
		
		tbNama = new JTextField();
		tbNama.setBounds(306, 152, 161, 20);
		getContentPane().add(tbNama);
		tbNama.setColumns(10);
		
		//Kolom Username
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setBounds(230, 180, 72, 14);
		getContentPane().add(lblUsername);
		
		tbUsername = new JTextField();
		tbUsername.setBounds(306, 177, 161, 20);
		getContentPane().add(tbUsername);
		tbUsername.setColumns(10);
		//Kolom Password
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(230, 205, 72, 14);
		getContentPane().add(lblPassword);
		
		tbPassword = new JTextField();
		tbPassword.setBounds(306, 202, 161, 20);
		getContentPane().add(tbPassword);
		tbPassword.setColumns(10);
		
		//Kolom Status
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Admin", "Kasir"}));
		comboBox.setBounds(306, 227, 161, 20);
		getContentPane().add(comboBox);
		
		JLabel lblStatus = new JLabel("Status :");
		lblStatus.setBounds(230, 230, 46, 14);
		getContentPane().add(lblStatus);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(80, 11, 590, 108);
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
		        String uname = (String) tabelModel.getValueAt(a, 2);
		        tbUsername.setText(uname);
		        String pass = (String) tabelModel.getValueAt(a, 3);
		        tbPassword.setText(pass);
		        String stat = (String) tabelModel.getValueAt(a, 4);
		        comboBox.setSelectedItem(stat);
		        
		        tbID.setEnabled(false);
			}
		});
		
		scrollPane_1.setViewportView(table);	
        tabelModel = new DefaultTableModel();
        tabelModel.addColumn("Id Karyawan");
        tabelModel.addColumn("Nama");
        tabelModel.addColumn("Username");
        tabelModel.addColumn("Password");
        tabelModel.addColumn("Status");
		table.setModel(tabelModel);	
        tampilTabel();
		
        //Tombol Tambah
		JButton btnTambah = new JButton("Tambah");
		btnTambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try
			        {
			        Connection config = Config.getCon();
			        String query = "INSERT INTO karyawan VALUES(?,?,?,?,?)";
			        PreparedStatement prepare = config.prepareStatement(query);
			        
			        prepare.setString(1, tbID.getText());
			        prepare.setString(2, tbNama.getText());
			        prepare.setString(3, tbUsername.getText());
			        prepare.setString(4, tbPassword.getText());
			        prepare.setString(5, (String) comboBox.getSelectedItem());

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
		btnTambah.setBounds(226, 254, 89, 23);
		getContentPane().add(btnTambah);
		
		//Tombol Ubah
		JButton button = new JButton("Ubah");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
			        Connection config = Config.getCon();
			        String query = "UPDATE karyawan SET  nama = ?, username = ?, password = ?, status = ? WHERE id = ?";
			        PreparedStatement prepare = config.prepareStatement(query);
			       
			        prepare.setString(1, tbNama.getText());
			        prepare.setString(2, tbUsername.getText());
			        prepare.setString(3, tbPassword.getText());
			        prepare.setString(4, (String) comboBox.getSelectedItem());
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
		button.setBounds(325, 254, 89, 23);
		getContentPane().add(button);
		
		//Tombol Hapus
		JButton button_1 = new JButton("Hapus");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try
			        {
			            Connection config = Config.getCon();
			            String query = "DELETE FROM karyawan WHERE id = ?";
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
		button_1.setBounds(420, 254, 89, 23);
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
            String query = "SELECT * FROM karyawan";
            
            ResultSet rs = state.executeQuery(query);
            
            while(rs.next())
            {
                Object obj[] = new Object[5];
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                obj[2] = rs.getString(3);
                obj[3] = rs.getString(4);
                obj[4] = rs.getString(5);
                
                tabelModel.addRow(obj);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
}