package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Menu_principal {

	private JFrame frame;
	private JTextField txtnom;
	private JTextField txtprix;
	private JTextField txttotal;
	private JTextField txtpayer;
	private JTextField txtreste;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu_principal window = new Menu_principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTable txttable;
	public Menu_principal() {
		initialize();
		Table();
	}
	
	public void Connect() throws SQLException {
		try {
			String url = "jdbc:postgresql://localhost:5432/pharmaBase";
			String utilisateur = " ";
			String motDePasse = " ";
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, utilisateur, motDePasse);
			System.out.println("Connextion établie");
		} catch (ClassNotFoundException e) {
			System.out.println("La connéxtion à échouer");
			e.printStackTrace();
		}
	}
	
	private void Table() {
		try {
			Connect();
			String [] entet = {"Code", "Nom", "Prix", "Qte", "Total", "payer", "Reste"};
			String [] ligne =  new String[8];
			
			DefaultTableModel model = new DefaultTableModel(null, entet);
			String pgSql = "SELECT * FROM tb_pharma";
			
			Statement stm = con.createStatement();
			rs = stm.executeQuery(pgSql);
			while (rs.next()) {
				ligne[0] = rs.getString("code");
				ligne[1] = rs.getString("nom");
				ligne[2] = rs.getString("prix");
				ligne[3] = rs.getString("quantite");
				ligne[4] = rs.getString("total");
				ligne[5] = rs.getString("payer");
				ligne[6] = rs.getString("reste");
				model.addRow(ligne);
			}
			txttable.setModel(model);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 561, 647);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 11, 516, 73);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("GESTION PHARMACIE");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 496, 51);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(10, 95, 516, 243);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nom:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 27, 49, 20);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Prix :");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(10, 67, 49, 20);
		panel_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Quantité :");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_3.setBounds(10, 112, 67, 20);
		panel_1.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Total :");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_4.setBounds(271, 27, 67, 20);
		panel_1.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Payer :");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_5.setBounds(271, 67, 49, 20);
		panel_1.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_6 = new JLabel("Reste :");
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_6.setBounds(271, 111, 49, 20);
		panel_1.add(lblNewLabel_1_6);
		
		txtnom = new JTextField();
		txtnom.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtnom.setColumns(10);
		txtnom.setBounds(73, 28, 155, 20);
		panel_1.add(txtnom);
		
		txtprix = new JTextField();
		txtprix.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtprix.setColumns(10);
		txtprix.setBounds(75, 68, 153, 20);
		panel_1.add(txtprix);
		
		txttotal = new JTextField();
		txttotal.setHorizontalAlignment(SwingConstants.CENTER);
		txttotal.setBackground(Color.CYAN);
		txttotal.setForeground(Color.RED);
		txttotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txttotal.setColumns(10);
		txttotal.setBounds(324, 28, 165, 20);
		panel_1.add(txttotal);
		
		txtpayer = new JTextField();
		txtpayer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int total = Integer.parseInt(txttotal.getText());
				int payer =Integer.parseInt(txtpayer.getText());
				int reste = payer - total;
				txtreste.setText(String.valueOf(reste));
			}
		});
		txtpayer.setHorizontalAlignment(SwingConstants.CENTER);
		txtpayer.setBackground(Color.GREEN);
		txtpayer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpayer.setColumns(10);
		txtpayer.setBounds(324, 68, 165, 20);
		panel_1.add(txtpayer);
		
		txtreste = new JTextField();
		txtreste.setHorizontalAlignment(SwingConstants.CENTER);
		txtreste.setForeground(Color.WHITE);
		txtreste.setBackground(Color.LIGHT_GRAY);
		txtreste.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtreste.setColumns(10);
		txtreste.setBounds(324, 112, 165, 20);
		panel_1.add(txtreste);
		
		JSpinner txtquantite = new JSpinner();
		txtquantite.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int prix = Integer.parseInt(txtprix.getText());
				int qte = Integer.parseInt(txtquantite.getValue().toString());
				
				int total = prix * qte;
				txttotal.setText(String.valueOf(total));
			}
		});
		txtquantite.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtquantite.setBounds(75, 112, 153, 20);
		panel_1.add(txtquantite);
		
		JButton btnNewButton = new JButton("Enregistrer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			        Connect();
			        String sql = "INSERT INTO tb_pharma (nom, prix, quantite, total, payer, reste) VALUES (?, ?, ?, ?, ?, ?)";
			        pst = con.prepareStatement(sql);
			        pst.setString(1, txtnom.getText());
			        pst.setDouble(2, Double.parseDouble(txtprix.getText()));
			        pst.setInt(3, (Integer) txtquantite.getValue());  
			        pst.setDouble(4, Double.parseDouble(txttotal.getText()));
			        pst.setDouble(5, Double.parseDouble(txtpayer.getText()));
			        pst.setDouble(6, Double.parseDouble(txtreste.getText()));
			        pst.executeUpdate();
			        con.close();
			        JOptionPane.showMessageDialog(null, txtnom.getText() + " Ajouter");
			        Table();
			    } catch (Exception e2) {
			        e2.printStackTrace();
			    }
			}
			
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(324, 175, 165, 19);
		panel_1.add(btnNewButton);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 360, 516, 227);
		frame.getContentPane().add(scrollPane_1);
		
		txttable = new JTable();
		scrollPane_1.setViewportView(txttable);
		txttable.setForeground(new Color(0, 0, 0));
		txttable.setBorder(new LineBorder(new Color(0, 0, 0)));
		txttable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txttable.setBackground(Color.WHITE);
	}
}
