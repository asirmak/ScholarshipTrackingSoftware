
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class SponsorGoruntule extends JFrame {

	private JPanel contentPane;
	private JLabel sponsorSoyadiLabel;
	private JLabel sponsorCepTelLabel;
	private JLabel sponsorEmailLabel;
	private JLabel message1;
	private JLabel sponsorAdiLabel;
	private JButton anaMenuButton;
	private JButton kaydetButton;
	private JLabel aciklamaLabel;
	private JLabel egitimYiliLabel;
	private JButton btnDzenle;
	private JTextField sponsorAdi;
	private JTextField sponsorSoyadi;
	private JTextField sponsorEmail;
	private JTextField sponsorCepTel;
	private JTextField egitimYili;
	private JTextArea aciklama;
	private static String selectedSponsorName;
	private static String selectedSponsorSurname;
	private static int selectedSponsorid;
	private static String selectedSponsorMail;
	private static String selectedSponsorCepTel;
	private static String selectedSponsorEgitimYılı;
	private static String selectedSponsorAciklama;
	private JButton silButton;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
					UIManager.setLookAndFeel(lookAndFeel);
					SponsorGoruntule frame = new SponsorGoruntule();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SponsorGoruntule() {
		String url="jdbc:mysql://127.0.0.1:3306/databasedeneme";
		String username = "root";
		String password="060723";	
		try {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, username, password);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("select * from sponsor where Sponsorid = '"+ getSelectedSponsorid()+ "'");
			while(rs.next()) {
				setSelectedSponsorName(rs.getString("SponsorAdi"));
				setSelectedSponsorSurname(rs.getString("SponsorSoyadi"));
				setSelectedSponsorMail(rs.getString("SponsorMail"));
				setSelectedSponsorCepTel(rs.getString("SponsorCepTel"));
				setSelectedSponsorEgitimYılı(rs.getString("EgitimYili"));
				setSelectedSponsorAciklama(rs.getString("aciklama"));
				setSelectedSponsorid(rs.getInt("Sponsorid"));
			}
			
		setResizable(false);
		setTitle("Sponsor Görüntüle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 570);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		message1 = new JLabel("Sponsor Bilgiler");
		message1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		message1.setHorizontalAlignment(SwingConstants.CENTER);
		message1.setBounds(10, 10, 664, 30);
		contentPane.add(message1);
		
		sponsorAdiLabel = new JLabel("Sponsor Adı");
		sponsorAdiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sponsorAdiLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sponsorAdiLabel.setBounds(40, 51, 160, 20);
		contentPane.add(sponsorAdiLabel);
		
		sponsorAdi = new JTextField();
		sponsorAdi.setToolTipText("Sadece harf geçerlidir. A-Z. Maksimum 30 karakter.");
		sponsorAdi.setText(getSelectedSponsorName());
		sponsorAdi.setEditable(false);
		sponsorAdi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sponsorAdi.setBounds(40, 75, 160, 25);
		contentPane.add(sponsorAdi);
		sponsorAdi.setColumns(10);
		
		sponsorSoyadiLabel = new JLabel("Sponsor Soyadı");
		sponsorSoyadiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sponsorSoyadiLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sponsorSoyadiLabel.setBounds(40, 113, 160, 20);
		contentPane.add(sponsorSoyadiLabel);
		
		sponsorSoyadi = new JTextField();
		sponsorSoyadi.setToolTipText("Sadece harf geçerlidir. A-Z. Maksimum 30 karakter.");
		sponsorSoyadi.setText(getSelectedSponsorSurname());
		sponsorSoyadi.setEditable(false);
		sponsorSoyadi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sponsorSoyadi.setColumns(10);
		sponsorSoyadi.setBounds(40, 137, 160, 25);
		contentPane.add(sponsorSoyadi);
		
		sponsorCepTelLabel = new JLabel("Sponsor Cep Tel");
		sponsorCepTelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sponsorCepTelLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sponsorCepTelLabel.setBounds(262, 113, 160, 20);
		contentPane.add(sponsorCepTelLabel);
		
		sponsorCepTel = new JTextField();
		sponsorCepTel.setToolTipText("Sadece rakam geçerlidir. 0-9");
		sponsorCepTel.setText(getSelectedSponsorCepTel());
		sponsorCepTel.setEditable(false);
		sponsorCepTel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sponsorCepTel.setColumns(10);
		sponsorCepTel.setBounds(262, 137, 160, 25);
		contentPane.add(sponsorCepTel);
		
		sponsorEmailLabel = new JLabel("Sponsor E-MAIL");
		sponsorEmailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sponsorEmailLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sponsorEmailLabel.setBounds(262, 51, 160, 20);
		contentPane.add(sponsorEmailLabel);
		
		sponsorEmail = new JTextField();
		sponsorEmail.setToolTipText("Maksimum 50 karakter.");
		sponsorEmail.setText(getSelectedSponsorMail());
		sponsorEmail.setEditable(false);
		sponsorEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sponsorEmail.setColumns(10);
		sponsorEmail.setBounds(262, 75, 160, 25);
		contentPane.add(sponsorEmail);
		
		egitimYiliLabel = new JLabel("Eğitim Yılı");
		egitimYiliLabel.setHorizontalAlignment(SwingConstants.CENTER);
		egitimYiliLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		egitimYiliLabel.setBounds(484, 51, 160, 20);
		contentPane.add(egitimYiliLabel);
		
		egitimYili = new JTextField();
		egitimYili.setToolTipText("Sadece rakam geçerlidir. 0-9");
		egitimYili.setText(getSelectedSponsorEgitimYılı());
		egitimYili.setEditable(false);
		egitimYili.setFont(new Font("Tahoma", Font.PLAIN, 14));
		egitimYili.setColumns(10);
		egitimYili.setBounds(484, 75, 160, 25);
		contentPane.add(egitimYili);
		
		aciklamaLabel = new JLabel("Açıklama");
		aciklamaLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		aciklamaLabel.setBounds(40, 359, 100, 35);
		contentPane.add(aciklamaLabel);
		
		aciklama = new JTextArea();
		aciklama.setToolTipText("Maksimum 75 karakter.");
		aciklama.setFont(new Font("Tahoma", Font.PLAIN, 14));
		aciklama.setText(getSelectedSponsorAciklama());
		aciklama.setEditable(false);
		aciklama.setBackground(UIManager.getColor("TextField.inactiveBackground"));
		aciklama.setLineWrap(true);
		aciklama.setBorder(new LineBorder(new Color(171, 173, 179), 1, true));
		aciklama.setBounds(40, 390, 604, 96);
		contentPane.add(aciklama);
				
		anaMenuButton = new JButton("Ana Menü");
		anaMenuButton.setFocusPainted(false);
		anaMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				GirisEkrani.main(null);
			}
		});
		anaMenuButton.setBounds(544, 497, 100, 23);
		contentPane.add(anaMenuButton);
		
		btnDzenle = new JButton("Düzenle");
		btnDzenle.setFocusPainted(false);
		btnDzenle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				sponsorCepTel.setToolTipText("Sadece rakam geçerlidir. 0-9");
				sponsorEmail.setEditable(true);
				sponsorCepTel.setEditable(true);
				egitimYili.setEditable(true);
				aciklama.setEditable(true);
				aciklama.setBackground(UIManager.getColor(Color.WHITE));
				kaydetButton.setEnabled(true);
				btnDzenle.setEnabled(false);
			}
		});
		btnDzenle.setBounds(322, 497, 100, 23);
		contentPane.add(btnDzenle);
		
		kaydetButton = new JButton("Kaydet");
		kaydetButton.setFocusPainted(false);
		kaydetButton.setEnabled(false);
		kaydetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean error = false;
				
				if(!(sponsorCepTel.getText().matches( "[0-9]+" )) || sponsorCepTel.getText().equals("")) {
					sponsorCepTel.setBackground(Color.PINK);
					error = true;
				}
				else sponsorCepTel.setBackground(Color.WHITE);
				
				if(!(egitimYili.getText().matches( "[0-9]+" )) || egitimYili.getText().equals("")) {
					egitimYili.setBackground(Color.PINK);
					error = true;
				}
				else egitimYili.setBackground(Color.WHITE);
				
				if(sponsorEmail.getText().equals("") || sponsorEmail.getText().length() > 50) {
					sponsorEmail.setBackground(Color.PINK);
					error = true;
				}
				else sponsorEmail.setBackground(Color.WHITE);
				
				if(aciklama.getText().length() > 75) {
					aciklama.setBackground(Color.PINK);
					error = true;
				}
				else aciklama.setBackground(Color.WHITE);
				
				if(error) {
					JOptionPane.showMessageDialog(null, "Hatalı bilgi girişi yapıldı.\n"
							+ "Daha fazla bilgi için kutucukların üstüne geliniz.\n"
							+ "Açıklama bölümü hariç, Tüm bölümlerin doldurulması zorunludur.");
				}
	
				else {
					try{			
						setSelectedSponsorMail(sponsorEmail.getText());
						setSelectedSponsorCepTel(sponsorCepTel.getText());
						setSelectedSponsorEgitimYılı(egitimYili.getText());
						setSelectedSponsorAciklama(aciklama.getText());
							
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn = DriverManager.getConnection(url, username, password);
						Statement statement = conn.createStatement();
						statement.executeUpdate("UPDATE sponsor set SponsorMail = '"+getSelectedSponsorMail()+"', SponsorCepTel = '"+getSelectedSponsorCepTel()+"', EgitimYili = '"+ getSelectedSponsorEgitimYılı()
						+ "', aciklama = '"+ getSelectedSponsorAciklama()+"' where Sponsorid = '"+ getSelectedSponsorid()+"'");
					}catch(ClassNotFoundException e1){
						System.out.println(e1);
						System.out.println("Class not found");
					}catch (SQLException e1) {
						System.out.println(e1);
					}
						
						
					sponsorEmail.setEditable(false);
					sponsorCepTel.setEditable(false);
					egitimYili.setEditable(false);
					
					sponsorEmail.setBackground(UIManager.getColor("TextField.inactiveBackground"));
					sponsorCepTel.setBackground(UIManager.getColor("TextField.inactiveBackground"));
					egitimYili.setBackground(UIManager.getColor("TextField.inactiveBackground"));

					aciklama.setEditable(false);
					aciklama.setBackground(UIManager.getColor("TextField.inactiveBackground"));
					kaydetButton.setEnabled(false);
					btnDzenle.setEnabled(true);
					
					sponsorEmail.setToolTipText(null);
					sponsorCepTel.setToolTipText(null);
					egitimYili.setToolTipText(null);

					JOptionPane.showMessageDialog(null, "Düzenleme işlemi başarıyla tamamlanmıştır");
				}
				
			}
		});
		kaydetButton.setBounds(434, 497, 100, 23);
		contentPane.add(kaydetButton);
		
		silButton = new JButton("Sil");
		silButton.setFocusPainted(false);
		silButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			    	Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection(url, username, password);
					Statement statement = conn.createStatement();
					statement.executeUpdate("delete from sponsor where Sponsorid = '" + getSelectedSponsorid()+ "'");
					statement.executeUpdate("delete from bursiyer_sponsormatch where SponsorNo = '" + getSelectedSponsorid()+ "'");
					aciklama.setText("");
					sponsorCepTel.setText("");
					sponsorEmail.setText("");
					sponsorSoyadi.setText("");
					sponsorAdi.setText("");
					egitimYili.setText("");
					JOptionPane.showMessageDialog(null, "Silme işlemi başarıyla tamamlanmıştır");
					dispose();
					GirisEkrani.main(null);
					
				}catch(ClassNotFoundException e1){
					System.out.println(e1);
					System.out.println("Class not found");
				}catch (SQLException e1) {
					System.out.println(e1);
				}
			}
		});
		silButton.setBounds(212, 497, 100, 23);
		contentPane.add(silButton);
		}catch(ClassNotFoundException e){
			System.out.println(e);
			System.out.println("Class not found");
		}catch (SQLException e) {
			System.out.println(e);
		}
		
	}
	
	public static String getSelectedSponsorSurname() {
		return selectedSponsorSurname;
	}
	public static String getSelectedSponsorMail() {
		return selectedSponsorMail;
	}
	public static String getSelectedSponsorCepTel() {
		return selectedSponsorCepTel;
	}
	public static String getSelectedSponsorEgitimYılı() {
		return selectedSponsorEgitimYılı;
	}
	public static String getSelectedSponsorAciklama() {
		return selectedSponsorAciklama;
	}
	public static int getSelectedSponsorid() {
		return selectedSponsorid;
	}

	public static void setSelectedSponsorSurname(String selectedSponsorSurname) {
		SponsorGoruntule.selectedSponsorSurname = selectedSponsorSurname;
	}

	public static void setSelectedSponsorid(int selectedSponsorid) {
		SponsorGoruntule.selectedSponsorid = selectedSponsorid;
	}

	public static void setSelectedSponsorMail(String selectedSponsorMail) {
		SponsorGoruntule.selectedSponsorMail = selectedSponsorMail;
	}

	public static void setSelectedSponsorCepTel(String selectedSponsorCepTel) {
		SponsorGoruntule.selectedSponsorCepTel = selectedSponsorCepTel;
	}

	public static void setSelectedSponsorEgitimYılı(String selectedSponsorEgitimYılı) {
		SponsorGoruntule.selectedSponsorEgitimYılı = selectedSponsorEgitimYılı;
	}

	public static void setSelectedSponsorAciklama(String selectedSponsorAciklama) {
		SponsorGoruntule.selectedSponsorAciklama = selectedSponsorAciklama;
	}

	public static String getSelectedSponsorName() {
		return selectedSponsorName;
	}

	public static void setSelectedSponsorName(String selectedSponsorName) {
		SponsorGoruntule.selectedSponsorName = selectedSponsorName;
	}

}
