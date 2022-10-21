package classi;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.GregorianCalendar;

public class Abbonamenti extends JFrame {

	private static final long serialVersionUID = -4324308590673786528L;
	private JPanel contentPane;
	private JTextField textFieldEmail;
	private JTextField textFieldCarta;
	private JTextField textFieldData;
	private JTextField textFieldCVV;

	
	public Abbonamenti(Connection conn, String CF) {
		setTitle("www.streamingforyou/menu/pianiabbonamento");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Inserisci email*:");
		lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		panel_2.add(lblNewLabel_1);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setPreferredSize(new Dimension(150, 20));
		textFieldEmail.setColumns(10);
		panel_2.add(textFieldEmail);
		
		JLabel lblNewLabel_2 = new JLabel("Inserisci nome piattaforma*:");
		lblNewLabel_2.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		panel_2.add(lblNewLabel_2);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setForeground(Color.BLACK);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Amazon", "Netflix", "Dazn", "Spotify", "Mediaset Infinity", "Disney Plus", "Apple TV"}));
		comboBox.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 14));
		panel_2.add(comboBox);
		comboBox.setSelectedItem(null);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Scegli piano");
		lblNewLabel_2_1_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		panel_5.add(lblNewLabel_2_1_1);
		
		JComboBox<String> comboBoxPiano = new JComboBox<String>();
		comboBoxPiano.setModel(new DefaultComboBoxModel<String>(new String[] {"mini all inclusive-base-2.99$", "classic all inclusive-premium-4.99$", "super mini inclusive-base-5.99$", "super classic inclusive-premium-7.99$", "mini inclusive-base-0.99$", "maxi inclusive-premium-1.99$", "ultra inclusive-premium-8.99$"}));
		comboBoxPiano.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 14));
		panel_5.add(comboBoxPiano);
		comboBoxPiano.setSelectedItem(null);
		
		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6);
		
		JLabel lblNewLabel_4_1 = new JLabel("Numero carta");
		lblNewLabel_4_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		panel_6.add(lblNewLabel_4_1);
		
		textFieldCarta = new JTextField();
		textFieldCarta.setPreferredSize(new Dimension(150, 20));
		textFieldCarta.setColumns(10);
		panel_6.add(textFieldCarta);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Data scadenza");
		lblNewLabel_1_1_2.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		panel_6.add(lblNewLabel_1_1_2);
		
		textFieldData = new JTextField();
		textFieldData.setPreferredSize(new Dimension(150, 20));
		textFieldData.setColumns(10);
		panel_6.add(textFieldData);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("CVV");
		lblNewLabel_1_1_1_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		panel_6.add(lblNewLabel_1_1_1_1);
		
		textFieldCVV = new JTextField();
		textFieldCVV.setPreferredSize(new Dimension(150, 20));
		textFieldCVV.setColumns(10);
		panel_6.add(textFieldCVV);
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4, BorderLayout.SOUTH);
		
		JButton btnConfermaPagamento = new JButton("Conferma pagamento");
		btnConfermaPagamento.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		btnConfermaPagamento.setBackground(Color.WHITE);
		panel_4.add(btnConfermaPagamento);
		
		JButton indietro = new JButton("Indietro");
		indietro.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 15));
		indietro.setBackground(Color.WHITE);
		panel_4.add(indietro);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		JLabel lblScegliPianoDabbonamento = new JLabel("Scegli un piano d'abbonamento");
		lblScegliPianoDabbonamento.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		panel_1.add(lblScegliPianoDabbonamento);
		
		indietro.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Abbonamenti.this.dispose();
			}	
		});
		
		btnConfermaPagamento.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				String email = null, piattaforma = null, piano = null, carta = null, dataScadenza = null, cvv = null;
				int a =0, m = 0, g = 0;
				
				if(!textFieldEmail.getText().isEmpty())
					email = textFieldEmail.getText();
				
				if(comboBox.getSelectedItem()!=null)
					piattaforma = (String) comboBox.getSelectedItem();
				
				if(comboBoxPiano.getSelectedItem()!=null) {
					String[] piani = ((String)comboBoxPiano.getSelectedItem()).split("-");
					piano = piani[0];
				}
				
				if(!textFieldCarta.getText().isEmpty()) {
					carta= textFieldCarta.getText();
					if(carta.length()<16 || carta.length()>16) {
						JOptionPane.showMessageDialog(null, "Numero carta non valido");
						return;
					}
				}
				
				if(!textFieldData.getText().isEmpty()) {
					dataScadenza = textFieldData.getText();
					String data[] = dataScadenza.split("/");
					a = Integer.parseInt(data[0]);
					m = Integer.parseInt(data[1]);
					g = Integer.parseInt(data[2]);
					
					GregorianCalendar att = new GregorianCalendar();
					
					if(m<=0 || m>12 || g<=0 || g>31 || a<=0) {
						JOptionPane.showMessageDialog(null, "Formato data non corretto!");
						return;
					}
					else if(m == 2 && a % 4 == 0 && g>29) {
						JOptionPane.showMessageDialog(null, "Formato data non corretto!");
						return;
					}
					else if(m == 2 && a % 4 != 0 && g>28) {
						JOptionPane.showMessageDialog(null, "Formato data non corretto!");
						return;
					}
					else if((m == 4 || m == 6 || m==9 || m ==11) && g>30) {
						JOptionPane.showMessageDialog(null, "Formato data non corretto!");
						return;
					}
					else if(new GregorianCalendar(a,m-1,g).before(att)) {
						JOptionPane.showMessageDialog(null, "Carta scaduta, non � possibile accettare il pagamento!");
						return;
					}
					
				}
					
				if(!textFieldCVV.getText().isEmpty()) {
					cvv = textFieldCVV.getText();
					if(cvv.length()>3 || cvv.length()<3) {
						JOptionPane.showMessageDialog(null, "CVV deve avere lunghezza 3");
						return;
					}
					
				}
				
				boolean flag;
				
				if(email!=null && piattaforma!=null) {
					//verifico se esiste l'account per l'utente loggato all'interno del DB
					flag = ModificaAccounts.checkAccount(conn, CF, email, piattaforma);	
				}
				else {
					JOptionPane.showMessageDialog(null, "Inserisci i campi obbligatori!");
					return;
				}
				
				if(flag) {
					
					if(piano != null && carta != null && dataScadenza != null && cvv != null) {
						
						GregorianCalendar cal = new GregorianCalendar(a, m-1, g);
						Date d = new Date(cal.getTimeInMillis());
						insertPagamento(conn, email, piattaforma, piano, carta, d, cvv);
					}
					
					else if(piano == null || carta == null || dataScadenza == null || cvv == null) {
						JOptionPane.showMessageDialog(null, "Non hai inserito tutti i campi per effettuare un pagamento!");
					}
					
				}
				else if(flag == false) {
					JOptionPane.showMessageDialog(null, "Nessun account trovato!");
					return;
				}
				
			}
		});
		
	}
	
	private void insertPagamento(Connection conn, String email, String piattaforma, String piano, String carta, Date d, String cvv) {
		String insert = "INSERT INTO pagamento VALUES (?,?,?,?,?,?,?);";
		
		try {
			PreparedStatement p = conn.prepareStatement(insert);
			p.setTimestamp(1, new Timestamp(new GregorianCalendar().getTimeInMillis()));
			p.setString(2, piano);									
			p.setString(3, email);
			p.setString(4, piattaforma);
			p.setString(5, carta);
			p.setDate(6, d);
			p.setString(7, cvv);
	
			if(p.executeUpdate()>0)															
				JOptionPane.showMessageDialog(null, "Transazione avvenuta con successo!");	
				
		}
		catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "Errore durante la transazione", "Errore server", 2);
		}
				
	}
	
	public static void updateSpeseSostenute(Connection conn, String CF) {
		String update = "UPDATE utenteRegistrato SET speseSostenute";
		update+= " = (SELECT sum(p2.prezzo) FROM (accounts a join Pagamento p on a.email = p.emailAccount and p.nomePiattaforma = a.nomePiattaforma)";
		update+= " join pianoAbbonamento p2 on p2.nome = p.nomeAbbonamento WHERE a.CFUtente = ?)";
		update+= " WHERE utenteRegistrato.CF = ?";
		
		try {
			PreparedStatement p = conn.prepareStatement(update);
			p.setString(1, CF);									
			p.setString(2, CF);
			
			if(p.executeUpdate()>0)															
				System.out.println("Aggiornamento spese avvenuto con successo");
				
		}
		catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "Errore 404", "Errore server", 2);
		}
	}

}
