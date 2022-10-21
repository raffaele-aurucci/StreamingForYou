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
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModificaAccounts extends JFrame {

	private static final long serialVersionUID = 4525641577092955585L;
	private JPanel contentPane;
	private JTextField textFieldEmail;
	private JTextField textFieldUser;
	private JTextField textFieldOldPassw;
	private JTextField textFieldNewPassw;
	private JTextField textFieldConferma;

	public ModificaAccounts(Connection conn, String CF) {
		setTitle("www.streamingforyou/menu/settings.com");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Impostazioni accounts:");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Inserisci email*:");
		lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		panel_2.add(lblNewLabel_1);
		
		textFieldEmail = new JTextField();
		panel_2.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		textFieldEmail.setPreferredSize(new Dimension(150,20));
		
		JLabel lblNewLabel_2 = new JLabel("Inserisci nome piattaforma*:");
		lblNewLabel_2.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		panel_2.add(lblNewLabel_2);
		
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Amazon", "Netflix", "Dazn", "Spotify", "Mediaset Infinity", "Disney Plus", "Apple TV"}));
		panel_2.add(comboBox);
		comboBox.setSelectedItem(null);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		
		JLabel lblNewLabel_3 = new JLabel("Modifica campi:");
		lblNewLabel_3.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		panel_4.add(lblNewLabel_3);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Username");
		rdbtnNewRadioButton.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		panel_4.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setSelected(true);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Password");
		rdbtnNewRadioButton_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		panel_4.add(rdbtnNewRadioButton_1);
		
		ButtonGroup g = new ButtonGroup();
		g.add(rdbtnNewRadioButton);
		g.add(rdbtnNewRadioButton_1);
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);
		
		JLabel lblNewLabel_4 = new JLabel("Username");
		lblNewLabel_4.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		panel_5.add(lblNewLabel_4);
		
		textFieldUser = new JTextField();
		panel_5.add(textFieldUser);
		textFieldUser.setColumns(10);
		textFieldUser.setPreferredSize(new Dimension(150,20));
		
		JLabel lblNewLabel_7 = new JLabel("Inserisci password");
		lblNewLabel_7.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		panel_5.add(lblNewLabel_7);
		
		textFieldConferma = new JTextField();
		panel_5.add(textFieldConferma);
		textFieldConferma.setColumns(10);
		textFieldConferma.setPreferredSize(new Dimension(150,20));
		
		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6);
		panel_6.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7);
		
		JLabel lblNewLabel_5 = new JLabel("Vecchia password");
		lblNewLabel_5.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		panel_7.add(lblNewLabel_5);
		lblNewLabel_5.setEnabled(false);
		
		textFieldOldPassw = new JTextField();
		panel_7.add(textFieldOldPassw);
		textFieldOldPassw.setColumns(10);
		textFieldOldPassw.setEnabled(false);
		textFieldOldPassw.setPreferredSize(new Dimension(150,20));
		
		JLabel lblNewLabel_6 = new JLabel("Nuova password");
		lblNewLabel_6.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		panel_7.add(lblNewLabel_6);
		lblNewLabel_6.setEnabled(false);
		
		textFieldNewPassw = new JTextField();
		panel_7.add(textFieldNewPassw);
		textFieldNewPassw.setColumns(10);
		textFieldNewPassw.setEnabled(false);
		textFieldNewPassw.setPreferredSize(new Dimension(150,20));
		
		JPanel panel_9 = new JPanel();
		contentPane.add(panel_9, BorderLayout.SOUTH);
		
		JButton conferma = new JButton("Conferma modifiche");
		conferma.setBackground(new Color(255, 255, 255));
		conferma.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		panel_9.add(conferma);
		
		JButton delete = new JButton("Cancella account");
		delete.setBackground(new Color(255, 255, 255));
		delete.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		panel_9.add(delete);
		
		JButton indietro = new JButton("Indietro");
		indietro.setBackground(new Color(255, 255, 255));
		indietro.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 15));
		panel_9.add(indietro);
		
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(rdbtnNewRadioButton.isSelected()) {
					textFieldOldPassw.setText("");
					textFieldNewPassw.setText("");
					lblNewLabel_5.setEnabled(false);
					textFieldOldPassw.setEnabled(false);
					lblNewLabel_6.setEnabled(false);
					textFieldNewPassw.setEnabled(false);
					lblNewLabel_4.setEnabled(true);
					textFieldUser.setEnabled(true);
					lblNewLabel_7.setEnabled(true);
					textFieldConferma.setEnabled(true);
				}
					
			}
		});
		
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(rdbtnNewRadioButton_1.isSelected()) {
					textFieldUser.setText("");
					textFieldConferma.setText("");
					lblNewLabel_5.setEnabled(true);
					textFieldOldPassw.setEnabled(true);
					lblNewLabel_6.setEnabled(true);
					textFieldNewPassw.setEnabled(true);
					lblNewLabel_4.setEnabled(false);
					textFieldUser.setEnabled(false);
					lblNewLabel_7.setEnabled(false);
					textFieldConferma.setEnabled(false);
				}
					
			}
		});
		
		indietro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ModificaAccounts.this.dispose();
				
			}
		});

		conferma.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String email = null, piattaforma = null, username = null, oldPassw = null, newPassw = null, conf = null;
				
				if(!textFieldEmail.getText().isEmpty())
					email = textFieldEmail.getText();
				
				if(comboBox.getSelectedItem()!=null)
					piattaforma = (String) comboBox.getSelectedItem();
				
				if(!textFieldUser.getText().isEmpty())
					username = textFieldUser.getText();
				
				if(!textFieldOldPassw.getText().isEmpty())
					oldPassw = textFieldOldPassw.getText();
				
				if(!textFieldNewPassw.getText().isEmpty())
					newPassw = textFieldNewPassw.getText();
				
				if(!textFieldConferma.getText().isEmpty())
					conf = textFieldConferma.getText();
				
				boolean flag;
				
				if(email!=null && piattaforma!=null) {
					//verifico se esiste l'account per l'utente loggato all'interno del DB
					flag = checkAccount(conn, CF, email, piattaforma);	
				}
				else {
					JOptionPane.showMessageDialog(null, "Inserisci i campi obbligatori!");
					return;
				}
				
				if(flag) {
					
					if(username == null && oldPassw!=null && newPassw!=null) {
						
						//faccio un ceck sulla passw per verificare che la password inserita sia la stessa memorizzata all'interno del DB
						
						if(checkPassw(conn, CF, email, piattaforma, oldPassw)) {	
							updatePassword(conn, CF, email, piattaforma, newPassw);
							JOptionPane.showMessageDialog(null, "Password modificata correttamente");
						}
						else
							JOptionPane.showMessageDialog(null, "La password inserita non � corretta!");
					
					}
					
					else if(username!= null && conf!=null && oldPassw==null && newPassw==null) {
						
						if(checkPassw(conn, CF, email, piattaforma, conf)) {
							updateUsername(conn, CF, email, piattaforma, username);
							JOptionPane.showMessageDialog(null, "Username modificato correttamente");
						}
						else
							JOptionPane.showMessageDialog(null, "La password inserita non � corretta!");
					}
					
					else if(email!=null && piattaforma!=null && username==null && oldPassw==null && newPassw==null && conf==null)
						JOptionPane.showMessageDialog(null, "Nessun campo inserito!");
						
				}
				else if(flag == false) {
					JOptionPane.showMessageDialog(null, "Nessun account trovato!");
				}

			}
		});
		
		delete.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String email = null, piattaforma = null;
				
				if(!textFieldEmail.getText().isEmpty())
					email = textFieldEmail.getText();
				
				if(comboBox.getSelectedItem()!=null)
					piattaforma = (String) comboBox.getSelectedItem();
				
				boolean flag;
				
				if(email!=null && piattaforma!=null) {
					flag = checkAccount(conn, CF, email, piattaforma);
				}
				else {
					JOptionPane.showMessageDialog(null, "Inserisci i campi obbligatori!");
					return;
				}
				
				if(flag) {
					
					if(JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare l'account?", "Attenzione!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						
						while(true) {
							String passw = null; passw=JOptionPane.showInputDialog("Inserisci password");
							
							if(passw!=null) {
								
								if(checkPassw(conn, CF, email, piattaforma, passw)) {
									String delete = "DELETE FROM accounts WHERE CFUtente = ? and email = ? and nomePiattaforma = ?;";
							
									try {
										PreparedStatement p = conn.prepareStatement(delete);
										p.setString(1, CF);
										p.setString(2, email);
										p.setString(3, piattaforma);
										
										if(p.executeUpdate()>0)
											JOptionPane.showMessageDialog(null, "Account eliminato correttamente!");
										
										break;
									}
									catch(SQLException ex) {
										JOptionPane.showMessageDialog(null, "Error 404", "Errore di connessione", 2);
										break;
									}	
							
								}
								else {
									JOptionPane.showMessageDialog(null, "La password inserita non � corretta!");
								}
							}
							else
								return;
						}
					}
					else
						return;
				}
					
				else if(flag == false) {
					JOptionPane.showMessageDialog(null, "Nessun account trovato!");
				}
				
			}
		});
		
	}
	
	private void updatePassword(Connection conn, String CF, String email, String piattaforma, String newPassw) {
		String updatePassword = "UPDATE accounts SET passwordAccount = ? WHERE CFUtente = ? and email = ? and nomePiattaforma = ?;";
		
		try {
			PreparedStatement p = conn.prepareStatement(updatePassword);
			p.setString(1, newPassw);
			p.setString(2, CF);
			p.setString(3, email);
			p.setString(4, piattaforma);
			
			p.executeUpdate();
				
		}
		catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error 404", "Errore di connessione", 2);
		}	
	}
	
	private void updateUsername(Connection conn, String CF, String email, String piattaforma, String username) {
		String updateUsername = "UPDATE accounts SET username = ? WHERE CFUtente = ? and email = ? and nomePiattaforma = ?;";
		
		try {
			PreparedStatement p = conn.prepareStatement(updateUsername);
			p.setString(1, username);
			p.setString(2, CF);
			p.setString(3, email);
			p.setString(4, piattaforma);
			
			p.executeUpdate();	
		}
		catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error 404", "Errore di connessione", 2);
		}	
	}
	
	public static boolean checkPassw(Connection conn, String CF, String email, String piattaforma, String oldPassw) {
		String query = "SELECT passwordAccount FROM accounts WHERE CFUtente = ? and email = ? and nomePiattaforma = ?;";
		
		try {
			PreparedStatement p = conn.prepareStatement(query);
			p.setString(1, CF);
			p.setString(2, email);
			p.setString(3, piattaforma);
			ResultSet rs = p.executeQuery();
			
			
			while(rs.next()) {
				if(!rs.getString("passwordAccount").equals(oldPassw))
					return false;
			}
		}
		catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error 404", "Errore di connessione", 2);
		}
		
		return true;
	}
	
	public static boolean checkAccount(Connection conn, String CF, String email, String piattaforma) {
		String query = "SELECT * FROM accounts WHERE CFUtente = ? and email = ? and nomePiattaforma = ?;";
		
		try {
			PreparedStatement p = conn.prepareStatement(query);
			p.setString(1, CF);
			p.setString(2, email);
			p.setString(3, piattaforma);
			ResultSet rs = p.executeQuery();
			
			if(!rs.isBeforeFirst())
				return false;
		}
		catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error 404", "Errore di connessione", 2);
		}
		
		return true;
	}
	
}
