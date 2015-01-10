package Coding;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Laporan extends JInternalFrame {
	private JTable table;
	private DefaultTableModel tabelModel;
	public Laporan() {
		setBounds(100, 100, 597, 384);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 55, 690, 400);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);	
        tabelModel = new DefaultTableModel();
        tabelModel.addColumn("ID");
        tabelModel.addColumn("ID Product");
        tabelModel.addColumn("Kuantitas");
		table.setModel(tabelModel);	
        tampilTabel();

	}
	public void tampilTabel()
    {
        tabelModel.getDataVector().removeAllElements();
        tabelModel.fireTableDataChanged();
        try
        {
            Connection konek = Config.getCon();
            Statement state = konek.createStatement();
            String query = "SELECT * FROM transaksi";
            
            ResultSet rs = state.executeQuery(query);
            
            while(rs.next())
            {
                Object obj[] = new Object[3];
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                obj[2] = rs.getString(3);
             
                tabelModel.addRow(obj);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
}
