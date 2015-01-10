package Coding;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Dimension;



import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
	private JButton btnLogin;
	private JMenuItem mi = new JMenuItem("Login");
	private JPanel contentPane;
	private JLabel Judul;
	
	public static JDesktopPane desktop;
	/**
	 * Launch the application.
	 */
	public Main() {
		super("Form Utama");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,800,600);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		desktop = new JDesktopPane();
		desktop.setBounds(0,0,800,600);
		getContentPane().add(desktop);
		desktop.add(mi);
		
		Judul = new JLabel("APLIKASI PENJUALAN TOKO OBAT HERBAL");
		//Judul.setForeground(new Color(0,0,0));
		Judul.setBounds(350, 200, 100, 40);
		getContentPane().add(Judul);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(350,250,100,40);
		getContentPane().add(btnLogin);
		

		/*desktop.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent me){
				if(me.getButton() == 3) //klik kanan
				{
					pop.show(me.getComponent(),me.getX(),me.getY());
				}
			}
		});*/
		
		btnLogin.addActionListener(new ActionListener ()
		{
			@Override
			public void actionPerformed(ActionEvent act)
			{
				desktop.add(new Login());
				btnLogin.setVisible(false);
			}
		});
		
	}
	public static void tampil(javax.swing.JInternalFrame f){
		Dimension size=desktop.getSize();
		desktop.add(f);
		f.setVisible(true);
		f.setSize(size);
		f.setLocation(0,0);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}