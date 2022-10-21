package classi;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreazioneAccount extends JFrame {

	private static final long serialVersionUID = 6849137164525435868L;
	private JPanel contentPane;
	private JTextField textFieldEmail;
	private JTextField textFieldPassw;
	private JTextField textFieldUsername;

	public CreazioneAccount(Connection conn, String CF) {
		setTitle("www.streamingforyou/menu/creazioneaccount.com");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Creazione account");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Inserisci email*:");
		lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		panel_2.add(lblNewLabel_1);
		
		textFieldEmail = new JTextField();
		panel_2.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		textFieldEmail.setPreferredSize(new Dimension(150,20));
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		
		JLabel lblNewLabel_2 = new JLabel("Scegli piattaforma*:");
		lblNewLabel_2.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		panel_3.add(lblNewLabel_2);
		
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Amazon", "Netflix", "Dazn", "Spotify", "Mediaset Infinity", "Disney Plus", "Apple TV"}));
		panel_3.add(comboBox);
		
		comboBox.setSelectedItem(null);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		
		JLabel lblNewLabel_3 = new JLabel("Inserisci username*:");
		lblNewLabel_3.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		panel_4.add(lblNewLabel_3);
		
		textFieldUsername = new JTextField();
		panel_4.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		textFieldUsername.setPreferredSize(new Dimension(150,20));
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5);
		
		JLabel lblNewLabel_4 = new JLabel("Inserisci password*:");
		lblNewLabel_4.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		panel_5.add(lblNewLabel_4);
		
		textFieldPassw = new JTextField();
		panel_5.add(textFieldPassw);
		textFieldPassw.setColumns(10);
		textFieldPassw.setPreferredSize(new Dimension(150,20));
		
		JPanel panel_6 = new JPanel();
		contentPane.add(panel_6, BorderLayout.SOUTH);
		
		JButton crea = new JButton("Crea account");
		crea.setBackground(new Color(255, 255, 255));
		crea.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		panel_6.add(crea);
		
		JButton indietro = new JButton("Indietro");
		indietro.setBackground(new Color(255, 255, 255));
		indietro.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 15));
		panel_6.add(indietro);
		
		indietro.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				CreazioneAccount.this.dispose();
			}
		});
		
		crea.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String email = null, piattaforma = null, username = null, password = null;
				
				if(!textFieldEmail.getText().isEmpty()) {
					email = textFieldEmail.getText();
					boolean flag = false;
					for(int i=0; i<email.length(); i++) {
						if(((Character)email.charAt(i)).equals('@'))
							flag = true;
					}
					if(!flag) {
						JOptionPane.showMessageDialog(null, "Formato email non corretto!");
						return;
					}
				}
					
				
				if(comboBox.getSelectedItem()!=null)
					piattaforma = (String) comboBox.getSelectedItem();
				
				if(!textFieldUsername.getText().isEmpty())
					username = textFieldUsername.getText();
				
				if(!textFieldPassw.getText().isEmpty())
					password = textFieldPassw.getText();
				
				if(email!=null && piattaforma!=null && username!=null && password!=null) {
					insertAccount(conn, CF, email, piattaforma, username, password);
				}
				else
					JOptionPane.showMessageDialog(null, "Non hai inserito tutti i campi obbligatori!");
				
			}
			
		});
	}
	
	private void insertAccount(Connection conn, String CF, String email, String piattaforma, String username, String password) {
		String insert = "INSERT INTO accounts VALUES (?,?,?,?,?);";
		
		try {
			PreparedStatement p = conn.prepareStatement(insert);
			p.setString(1, email);									//imposta il primo parametro '?' della stringa insert con il tipo Java specificato, quest'ultimo 
																				//viene convertito dal driver JDBC in un varchar o longvarchar a seconda della lunghezza
			p.setString(2, piattaforma);
			p.setString(3, CF);
			p.setString(4, username);
			p.setString(5, password);
	
			if(p.executeUpdate()>0)																				//esegue l'istruzione SQL sull'oggetto PreparedStatement; si usa il metodo execeuteUpdate()
				JOptionPane.showMessageDialog(null, "Creazione avvenuta con successo!");	//per le istruzioni DML (insert, delete, update) o istruzioni DDL (create). Restituisce il num
																												//ero di righe per le istruzioni DML, 0 per le istruzioni DDL
				
		}
		catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "Errore durante la creazione (oppure dati gi� esistenti)", "Errore server", 2);
		}

	}
	
}
