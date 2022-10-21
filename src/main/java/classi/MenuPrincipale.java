package classi;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuPrincipale extends JFrame {

	private static final long serialVersionUID = -7657952033429927428L;
	private JPanel contentPane;

	public MenuPrincipale(Connection conn, String CF) {
		setTitle("www.streamingforyou/menu.com");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JButton totSpese = new JButton("Visualizza spese");
		totSpese.setForeground(Color.BLACK);
		totSpese.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		totSpese.setBackground(Color.WHITE);
		panel.add(totSpese);
		
		JButton visualizza = new JButton("Visualizza accounts");
		visualizza.setBackground(new Color(255, 255, 255));
		visualizza.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		panel.add(visualizza);
		
		JButton crea = new JButton("Crea un account");
		crea.setBackground(new Color(255, 255, 255));
		crea.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		panel.add(crea);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		textArea.setEditable(false);
		JScrollPane p = new JScrollPane(textArea);
		panel_1.add(p, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.SOUTH);
		
		JButton modifica = new JButton("Modifica accounts");
		modifica.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		modifica.setBackground(new Color(255, 255, 255));
		panel_2.add(modifica);
		
		JButton abbonati = new JButton("Abbonati");
		abbonati.setForeground(Color.BLACK);
		abbonati.setBackground(new Color(255, 255, 255));
		abbonati.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		panel_2.add(abbonati);
		
		JButton indietro = new JButton("Indietro");
		indietro.setBackground(new Color(255, 255, 255));
		indietro.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 15));
		panel_2.add(indietro);
		
		visualizza.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				String query = "SELECT email, nomePiattaforma, username, passwordAccount FROM accounts WHERE CFUtente = ? ORDER BY nomePiattaforma";
				
				try {
					PreparedStatement p = conn.prepareStatement(query);
					p.setString(1, CF);
					ResultSet rs = p.executeQuery();
					
					if(!rs.isBeforeFirst()) {
						JOptionPane.showMessageDialog(null, "Nessun account associato all'utente registrato");
						return;
					}
					
					while(rs.next()) {
						textArea.append("email: " + rs.getString("email") + ", piattaforma: " + rs.getString("nomePiattaforma") + ", username: " + rs.getString("username") + ", password: " + rs.getString("passwordAccount") + "\n");
					}
					
				}
				catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Error 404", "Errore di connessione", 2);
				}	
			}
		});
		
		
		crea.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				CreazioneAccount c = new CreazioneAccount(conn, CF);
				c.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				c.setVisible(true);
			}
			
		});
		
		modifica.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				ModificaAccounts m = new ModificaAccounts(conn, CF);
				m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				m.setVisible(true);
			}
		});
		
		indietro.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				MenuPrincipale.this.dispose();
				
			}
		});
		
		abbonati.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				Abbonamenti a = new Abbonamenti(conn, CF);
				a.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				a.setVisible(true);
			}
			
		});
		
		totSpese.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Abbonamenti.updateSpeseSostenute(conn, CF);
				
				String query = "SELECT speseSostenute FROM utenteRegistrato WHERE CF = ?";
				
				try {
					PreparedStatement p = conn.prepareStatement(query);
					p.setString(1, CF);									
					ResultSet rs = p.executeQuery();
					
					double tot = 0;
					
					if(rs.next()) {
						tot = rs.getDouble("speseSostenute");
					}
					
					String s = String.format("%.2f", tot);	
					
					JOptionPane.showMessageDialog(null, "Fino ad ora hai speso: " + s + " Euro", "Transazioni", 3);
						
				}
				catch(SQLException ex) {
					JOptionPane.showMessageDialog(null, "Errore 404", "Errore server", 2);
				}
				
			}
		});
	}

}
