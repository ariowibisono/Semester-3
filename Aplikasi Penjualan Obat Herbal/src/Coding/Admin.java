package Coding;

import java.awt.EventQueue;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;

import javax.swing.JDesktopPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class Admin extends JInternalFrame {
	public Admin() {
		setBounds(100, 100, 1366, 720);
		setLocation(0,0);
		this.setTitle("Admin");
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Maitenance Product", null, panel, null);
		panel.setLayout(null);
		
		
		final JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(10, 11, 753, 500);
		panel.add(desktopPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Maitenance Supplier", null, panel_1, null);
		panel_1.setLayout(null);
		
		final JDesktopPane desktopPane_1 = new JDesktopPane();
		desktopPane_1.setBounds(10, 11, 755,500);
		panel_1.add(desktopPane_1);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Maintenance User", null, panel_2, null);
		panel_2.setLayout(null);
		
		//Dimension sizePanel = panel_3.getBounds();
		final JDesktopPane desktopPane1 = new JDesktopPane();
		desktopPane1.setBounds(10, 11, 755,500);
		panel_2.add(desktopPane1);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Laporan", null, panel_3, null);
		panel_3.setLayout(null);
		
		//Dimension sizePanel = panel_3.getBounds();
		final JDesktopPane desktopPane2 = new JDesktopPane();
		desktopPane2.setBounds(10, 11, 755,500);
		panel_3.add(desktopPane2);
		
		tabbedPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				User p = new User();
				Suplier s =new Suplier();
				Laporan l=new Laporan();
				Dimension size=desktopPane1.getSize();
				Dimension size2=desktopPane_1.getSize();
				Dimension size3=desktopPane2.getSize();
				desktopPane1.add(p);
				desktopPane_1.add(s);
				desktopPane2.add(l);
				p.setVisible(true);
				p.setSize(size);
				p.setLocation(0,0);
				s.setVisible(true);
				s.setSize(size2);
				s.setLocation(0,0);
				l.setVisible(true);
				l.setSize(size3);
				l.setLocation(0,0);

				
			}
		});
		
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameActivated(InternalFrameEvent e) {
				Product p = new Product ();
				Dimension size2=desktopPane.getSize();
				desktopPane.add(p);
				p.setVisible(true);
				p.setSize(size2);
				p.setLocation(0,0);
			}
		});
	}
	
	public javax.swing.JInternalFrame  returnAdmin(){
		return this;
	}

}
