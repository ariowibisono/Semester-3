package Coding;

import java.sql.*;

import javax.swing.JOptionPane;

import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;

import java.awt.Color;

import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPasswordField;

public class Login extends JInternalFrame {
	private JButton btnLogin;
	private JButton btnHapus;
	private JTextField txtUser;
	private JTextField txtPass;
	public static javax.swing.JInternalFrame f;
	public static javax.swing.JInternalFrame f2;
	public static String nama;
	public static String idKar;

	/**
	 * Create the frame.
	 */
	public Login() {
		super("Login");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 600, 375);
		getContentPane().setLayout(null);
		setVisible(true);
		f=new Admin();
		f2=new Kasir();
		
		
		ImageIcon icon = new ImageIcon("./src/images/Logo.png");
		JLabel Judul = new JLabel(icon);
		//Judul.setIcon(icon);
		Judul.setBounds(45, 40, 500, 100);
		getContentPane().add(Judul);
		
		JLabel lblNo = new JLabel("Username : ");
		lblNo.setForeground(new Color(0,0,0));
		lblNo.setBounds(136, 155, 100, 15);
		getContentPane().add(lblNo);
		
		txtUser = new JTextField();
		txtUser.setBounds(230, 150, 209, 27);
		getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		JLabel lblNama = new JLabel("Password : ");
		lblNama.setForeground(new Color(0,0,0));
		lblNama.setBounds(136, 195, 100, 15);
		getContentPane().add(lblNama);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(230, 190, 209, 27);
		getContentPane().add(txtPass);
		txtPass.setColumns(10);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
			        Connection Con = Config.getCon();
			        String sql = "Select * from karyawan where username='"+txtUser.getText()+"' and password='" + txtPass.getText() + "'";
			        Statement st=Con.createStatement();
			        ResultSet rs = st.executeQuery(sql);
			        if (rs.next()){
			        if (rs.getString(5).equalsIgnoreCase("Admin")) {
			            JOptionPane.showMessageDialog(null, "Login berhasil");
			            Main.tampil(f);
			            nama = txtUser.getText();
			        }else if (rs.getString(5).equalsIgnoreCase("Kasir")){
			            JOptionPane.showMessageDialog(null, "Login berhasil");
			            Main.tampil(f2);
			            nama = txtUser.getText();
			            idKar = rs.getString(1);
			        }
			        }else{
			        JOptionPane.showMessageDialog(null, "Login gagal");
			        }
			        }catch (Exception ex){
			        JOptionPane.showMessageDialog(null, "Login gagal (koneksi)");
			        }
			}
		});
		btnLogin.setBounds(230, 250, 111, 36);
		getContentPane().add(btnLogin);
	}

}