package classi;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginUtente extends JFrame {

	private static final long serialVersionUID = 7259184181002658553L;
	private JPanel contentPane;
	private JTextField textFieldCF;
	private JTextField textFieldEmail;
	private JTextField textFieldPassw;
	private Connection conn;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUtente frame = new LoginUtente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public LoginUtente() {
		conn = LoginUtente.connetti();
		setForeground(new Color(255, 255, 255));
		setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 14));
		setTitle("www.streamingforyou.com");
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		JLabel cf = new JLabel("Inserisci CF*:");
		cf.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		panel_2.add(cf);
		
		textFieldCF = new JTextField();
		textFieldCF.setPreferredSize(new Dimension(150,20));
		panel_2.add(textFieldCF);
		textFieldCF.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		
		JLabel lblNewLabel_1 = new JLabel("Inserisci e-mail*:");
		lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		panel_3.add(lblNewLabel_1);
		
		textFieldEmail = new JTextField();
		panel_3.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		textFieldEmail.setPreferredSize(new Dimension(150,20));
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		
		JLabel lblNewLabel_2 = new JLabel("Inserisci password*:");
		lblNewLabel_2.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		panel_4.add(lblNewLabel_2);
		
		textFieldPassw = new JTextField();
		panel_4.add(textFieldPassw);
		textFieldPassw.setColumns(10);
		textFieldPassw.setPreferredSize(new Dimension(150,20));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton login = new JButton("Login");
		login.setBackground(new Color(255, 255, 255));
		login.setForeground(new Color(0, 0, 0));
		login.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		panel_1.add(login);
		
		JPanel panel_5 = new JPanel();
		contentPane.add(panel_5, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Benvenuto!");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		panel_5.add(lblNewLabel);
		
		login.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String CF = null, email = null, passw = null;
				if(!textFieldCF.getText().isEmpty()) {
					CF = textFieldCF.getText();
				}
				
				if(!textFieldEmail.getText().isEmpty()) {
					email = textFieldEmail.getText();
				}
				
				if(!textFieldPassw.getText().isEmpty()) {
					passw = textFieldPassw.getText();
				}
				
				if(CF!=null && email!=null && passw!=null) {
					eseguiLogin(CF, email, passw);	
				}
				else
					JOptionPane.showMessageDialog(null, "Non hai inserito tutti i campi obbligatori!");
				
			}
		});
	}
	
	/*
	 * Conessione al DB:
	 * 
	 * Apertura di una connesione al DB, associata all'utilizzo di un oggetto di tipo Connection,
	 * quest'ultimo si ottiene chiamando il metodo getConnection() della classe DriverManager, passando
	 * un url al database, il nome dell'utente, e la password dell'utente;
	 * 
	 */
	
	private static Connection connetti() {
		Connection conn = null;
		
		String url = "jdbc:mysql://localhost/streaming";
		String usr = "root";
		String pass = "29Maggio200Uno";
		
		try {
			conn = DriverManager.getConnection(url, usr, pass);
			System.out.println("Connessione avvenuta con successo");
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error 404", "Errore di connessione", 2);
			System.exit(ERROR);
		}
		
		return conn;
	}
	
	/*
	 * differenza tra Statement e PreparedStatement:
	 * 
	 * Statement -> � utilizzato quando c'� necessit� definire delle interrogazioni in SQL statiche e ottenere
	 * un risultato mediante il metodo executeQuery() che restituisce un oggetto ResulSet;
	 * � particolarmente utilizzato per istruzioni DDL (create, drop, alter, truncate).
	 * 
	 * PreparedStatement -> � utilizzato quando c'� necessita di definire delle interrogazioni dinamiche, poich�
	 * l'istruzione viene precompilata dal DB e attende che venga dato l'input per eseguire l'interrogazione,
	 * in questo modo si ha la possibilit� di formattare l'interrogazione mediante i metodi set() a cui si va
	 * ad assegnare ai valori definiti nella query con '?' i valori desiderati prima di farla eseguire dal DB
	 * con il metodo executeQuery();
	 * PreparedStatement � particolarmente adatto per comandi DML: (insert, select, update, delete).
	 * � molto pi� veloce ed efficiente poich� consente di sfruttare la cache del DB, se una stessa istruzione 
	 * � eseguita pi� volte il risultato viene recuperato dalla cache.
	 * 
	 */
	
	/*
	 * Cos'� ResultSet?
	 * 
	 * Rappresenta un astrazione ad alto livello del risultato di una query eseguito su un DB. � un oggetto
	 * che mantiene un cursore che punta alla riga di dati corrente. Inzialmente il cursore � posizionato prima
	 * della prima riga. Il metodo next() permette di spostare in avanti il curosore una riga alla volta.
	 * Quando non ci sono pi� righe il metodo next() restituisce false, per cui risulta comodo scorrere il risultato
	 * di una query mediante un ciclo while. Un oggetto ResultSet predefinito non � aggiornabile e ha
	 * un cursore che si sposta solo in avanti, pertanto � possibile iterare solo una volta dalla prima all'ultima riga.
	 * Volendo si possono produrre oggetti ResulSet aggiornabili.
	 * L'interfaccia ResulSet fornisce i metodi getter() con cui � possibile ottenere dati specifici dal risultato di
	 * una query, il driver JDBC tenta di convertire i dati sottostanti nel tipo Java specificato nel
	 * metodo getter e restituisce un valore Java adatto.
	 */
	
	private void eseguiLogin(String CF, String email, String passw) {
		String query = "SELECT * FROM (utenteRegistrato u join emailUtente e on u.CF = e.CFUtente) WHERE u.CF = ? and e.email = ? and u.passwordUtente = ?";
		
		try {
			PreparedStatement p = conn.prepareStatement(query);		//viene creato un oggetto di tipo PreparedStatement relativo alla stringa SQL passata in input. 
																	//Qualora venga restituito un ResulSet questo sar� di tipo non aggiornabile.
			p.setString(1, CF);
			p.setString(2, email);
			p.setString(3, passw);
			
			ResultSet rs = p.executeQuery();						//eseque la query sull'oggetto PreparedStatement e ritorna un oggetto ResulSet associato
																	//al risulato della query
		
			if(!rs.isBeforeFirst()) {
				JOptionPane.showMessageDialog(null, "Nessun utente corrisponde alle credenziali inserite");
				return;
			}
			
			MenuPrincipale m = new MenuPrincipale(conn, CF);
			m.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			m.setVisible(true);
				
		}
		catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error 404", "Errore di connessione", 2);
		}	
	}

}
