
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import java.sql.*;

public class BursiyerGoruntule extends JFrame {

	private JPanel contentPane;
	private JLabel bursiyerSoyadıLabel;
	private JLabel okulTuruLabel;
	private JLabel okulAdiLabel;
	private JLabel sinifLabel;
	private JLabel sehirLabel;
	private JLabel bankaAdiLabel;
	private JLabel aileAdresiLabel;
	private JLabel ibanLabel;
	private JLabel bursiyerEmailLabel;
	private JLabel bursiyerCepTelLabel;
	private JLabel egitimYiliLabel;
	private JTextField bursiyerAdi;
	private JTextField bursiyerSoyadi;
	private JTextField okulTuru;
	private JTextField okulAdi;
	private JTextField egitimYili;
	private JTextField sinif;
	private JTextField sehir;
	private JTextField bursiyerCepTel;
	private JTextField bursiyerEmail;
	private JTextField bankaAdi;
	private JTextField iban;
	private JTextField aileAdresi;
	private JTextArea aciklama;
	private JLabel aciklamaLabel;
	private JButton anaMenuButton;
	private JButton kaydetButton;
	private JLabel message1;
	private JLabel bursiyerAdiLabel;
	private JButton duzenleButton;
	private static String selectedBursiyerName;
	private static String selectedBursiyerSurname;
	private static String selectedBursiyerMail;
	private static String selectedBursiyerCepTel;
	private static String selectedBursiyerOkulTuru;
	private static String selectedBursiyerOkuladi;
	private static String selectedBursiyerSinif;
	private static String selectedBursiyersehir;
	private static String selectedBursiyerIban;
	private static String selectedBursiyerBankaAdi;
	private static String selectedBursiyerAileAdresi;
	private static String selectedBursiyerEgitimYili;
	private static String selectedBursiyerAciklama;
	private static int selectedBursiyerid;
	private JButton silButton;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
					UIManager.setLookAndFeel(lookAndFeel);
					BursiyerGoruntule frame = new BursiyerGoruntule();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BursiyerGoruntule() {
		String url="jdbc:mysql://127.0.0.1:3306/databasedeneme";
		String username = "root";
		String password="060723";	
		try {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, username, password);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("select * from bursiyer where Bursiyerid = '"+ getSelectedBursiyerid() +"'");
			while (rs.next()) {
				setSelectedBursiyerName(rs.getString("BursiyerAdi"));
				setSelectedBursiyerSurname(rs.getString("BursiyerSoyadi"));
				setSelectedBursiyerMail(rs.getString("BursiyerMail"));
                setSelectedBursiyerCepTel(rs.getString("BursiyerCepTel"));
                setSelectedBursiyerOkulTuru(rs.getString("OkulTuru"));
                setSelectedBursiyerOkuladi(rs.getString("OkulAdi"));
                setSelectedBursiyerSinif(rs.getString("Sinif"));
                setSelectedBursiyersehir(rs.getString("sehir"));
                setSelectedBursiyerIban(rs.getString("IBAN"));
                setSelectedBursiyerBankaAdi(rs.getString("BankaAdi"));
                setSelectedBursiyerAileAdresi(rs.getString("AileAdresi"));
                setSelectedBursiyerEgitimYili(rs.getString("EgitimYili"));
                setSelectedBursiyerAciklama(rs.getString("aciklama"));
            }			
		setResizable(false);
		setTitle("Bursiyer Görüntüle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 570);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		message1 = new JLabel("Bursiyer Bilgileri");
		message1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		message1.setHorizontalAlignment(SwingConstants.CENTER);
		message1.setBounds(10, 10, 664, 30);
		contentPane.add(message1);
		
		bursiyerAdiLabel = new JLabel("Bursiyer Adı");
		bursiyerAdiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bursiyerAdiLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bursiyerAdiLabel.setBounds(40, 51, 160, 20);
		contentPane.add(bursiyerAdiLabel);
		
		bursiyerAdi = new JTextField();
		bursiyerAdi.setText(getSelectedBursiyerName());
		bursiyerAdi.setEditable(false);
		bursiyerAdi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bursiyerAdi.setBounds(40, 75, 160, 25);
		contentPane.add(bursiyerAdi);
		bursiyerAdi.setColumns(10);
		
		bursiyerSoyadıLabel = new JLabel("Bursiyer Soyadı");
		bursiyerSoyadıLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bursiyerSoyadıLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bursiyerSoyadıLabel.setBounds(40, 113, 160, 20);
		contentPane.add(bursiyerSoyadıLabel);
		
		bursiyerSoyadi = new JTextField();
		bursiyerSoyadi.setText(getSelectedBursiyerSurname());
		bursiyerSoyadi.setEditable(false);
		bursiyerSoyadi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bursiyerSoyadi.setColumns(10);
		bursiyerSoyadi.setBounds(40, 137, 160, 25);
		contentPane.add(bursiyerSoyadi);
		
		bursiyerEmailLabel = new JLabel("Bursiyer E-MAIL");
		bursiyerEmailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bursiyerEmailLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bursiyerEmailLabel.setBounds(40, 175, 160, 20);
		contentPane.add(bursiyerEmailLabel);
		
		bursiyerEmail = new JTextField();
		bursiyerEmail.setToolTipText("Maksimum 50 karakter.");
		bursiyerEmail.setText(getSelectedBursiyerMail());
		bursiyerEmail.setEditable(false);
		bursiyerEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bursiyerEmail.setColumns(10);
		bursiyerEmail.setBounds(40, 199, 160, 25);
		contentPane.add(bursiyerEmail);
		
		bursiyerCepTelLabel = new JLabel("Bursiyer Cep Tel");
		bursiyerCepTelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bursiyerCepTelLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bursiyerCepTelLabel.setBounds(262, 51, 160, 20);
		contentPane.add(bursiyerCepTelLabel);
		
		bursiyerCepTel = new JTextField();
		bursiyerCepTel.setToolTipText("Sadece rakam geçerlidir. 0-9");
		bursiyerCepTel.setText(getSelectedBursiyerCepTel());
		bursiyerCepTel.setEditable(false);
		bursiyerCepTel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bursiyerCepTel.setColumns(10);
		bursiyerCepTel.setBounds(262, 75, 160, 25);
		contentPane.add(bursiyerCepTel);
		
		okulTuruLabel = new JLabel("Okul Türü");
		okulTuruLabel.setHorizontalAlignment(SwingConstants.CENTER);
		okulTuruLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		okulTuruLabel.setBounds(262, 113, 160, 20);
		contentPane.add(okulTuruLabel);
		
		okulTuru = new JTextField();
		okulTuru.setToolTipText("Sadece harf geçerlidir. A-Z. Maksimum 14 karakter.");
		okulTuru.setText(getSelectedOkulTuru());
		okulTuru.setEditable(false);
		okulTuru.setFont(new Font("Tahoma", Font.PLAIN, 14));
		okulTuru.setColumns(10);
		okulTuru.setBounds(262, 137, 160, 25);
		contentPane.add(okulTuru);
		
		okulAdiLabel = new JLabel("Okul Adı");
		okulAdiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		okulAdiLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		okulAdiLabel.setBounds(262, 175, 160, 20);
		contentPane.add(okulAdiLabel);
		
		okulAdi = new JTextField();
		okulAdi.setToolTipText("Sadece harf geçerlidir. A-Z. Maksimum 50 karakter.");
		okulAdi.setText(getSelectedBursiyerOkulAdi());
		okulAdi.setEditable(false);
		okulAdi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		okulAdi.setColumns(10);
		okulAdi.setBounds(262, 199, 160, 25);
		contentPane.add(okulAdi);
		
		sinifLabel = new JLabel("Sınıf");
		sinifLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sinifLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sinifLabel.setBounds(484, 51, 160, 20);
		contentPane.add(sinifLabel);
		
		sinif = new JTextField();
		sinif.setToolTipText("Sadece rakam geçerlidir. 0-9");
		sinif.setText(getSelectedBursiyerSinif());
		sinif.setEditable(false);
		sinif.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sinif.setColumns(10);
		sinif.setBounds(484, 75, 160, 25);
		contentPane.add(sinif);
		
		egitimYiliLabel = new JLabel("Eğitim Yılı");
		egitimYiliLabel.setHorizontalAlignment(SwingConstants.CENTER);
		egitimYiliLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		egitimYiliLabel.setBounds(484, 113, 160, 20);
		contentPane.add(egitimYiliLabel);
		
		egitimYili = new JTextField();
		egitimYili.setToolTipText("Sadece rakam geçerlidir. 0-9");
		egitimYili.setText(getSelectedBursiyerEgitimYili());;;
		egitimYili.setEditable(false);
		egitimYili.setFont(new Font("Tahoma", Font.PLAIN, 14));
		egitimYili.setColumns(10);
		egitimYili.setBounds(484, 137, 160, 25);
		contentPane.add(egitimYili);
		
		sehirLabel = new JLabel("Şehir");
		sehirLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sehirLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sehirLabel.setBounds(484, 175, 160, 20);
		contentPane.add(sehirLabel);
		
		sehir = new JTextField();
		sehir.setToolTipText("Sadece harf geçerlidir. A-Z. Maksimum 20 karakter.");
		sehir.setText(getSelectedBursiyerSehir());
		sehir.setEditable(false);
		sehir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sehir.setColumns(10);
		sehir.setBounds(484, 199, 160, 25);
		contentPane.add(sehir);
		
		ibanLabel = new JLabel("IBAN");
		ibanLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ibanLabel.setBounds(40, 259, 65, 25);
		contentPane.add(ibanLabel);
		
		iban = new JTextField();
		iban.setToolTipText("Maksimum 26 karakter.");
		iban.setText(getSelectedBursiyerIBAN());
		iban.setEditable(false);
		iban.setFont(new Font("Tahoma", Font.PLAIN, 14));
		iban.setColumns(10);
		iban.setBounds(115, 259, 307, 25);
		contentPane.add(iban);
		
		bankaAdiLabel = new JLabel("Banka Adı");
		bankaAdiLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bankaAdiLabel.setBounds(40, 290, 65, 25);
		contentPane.add(bankaAdiLabel);
		
		bankaAdi = new JTextField();
		bankaAdi.setToolTipText("Maksimum 30 karakter.");
		bankaAdi.setText(getSelectedBursiyerBankaAdi());
		bankaAdi.setEditable(false);
		bankaAdi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bankaAdi.setColumns(10);
		bankaAdi.setBounds(115, 290, 307, 25);
		contentPane.add(bankaAdi);
		
		aileAdresiLabel = new JLabel("Aile Adresi");
		aileAdresiLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		aileAdresiLabel.setBounds(40, 321, 65, 25);
		contentPane.add(aileAdresiLabel);
		
		aileAdresi = new JTextField();
		aileAdresi.setToolTipText("Maksimum 75 karakter.");
		aileAdresi.setText(getSelectedBursiyerAileAdresi());
		aileAdresi.setEditable(false);
		aileAdresi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		aileAdresi.setColumns(10);
		aileAdresi.setBounds(115, 321, 529, 25);
		contentPane.add(aileAdresi);
		
		aciklamaLabel = new JLabel("Açıklama");
		aciklamaLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		aciklamaLabel.setBounds(40, 359, 100, 35);
		contentPane.add(aciklamaLabel);
		
		aciklama = new JTextArea();
		aciklama.setToolTipText("Maksimum 75 karakter.");
		aciklama.setFont(new Font("Tahoma", Font.PLAIN, 14));
		aciklama.setText(getSelectedBursiyerAciklama());
		aciklama.setBackground(UIManager.getColor("TextField.inactiveBackground"));
		aciklama.setEditable(false);
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
		
		duzenleButton = new JButton("Düzenle");
		duzenleButton.setFocusPainted(false);
		duzenleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				okulTuru.setEditable(true);
				okulAdi.setEditable(true);
				egitimYili.setEditable(true);
				sinif.setEditable(true);
				sehir.setEditable(true);
				bursiyerCepTel.setEditable(true);
				bursiyerEmail.setEditable(true);
				bankaAdi.setEditable(true);
				iban.setEditable(true);
				aileAdresi.setEditable(true);
				aciklama.setEditable(true);
				aciklama.setBackground(UIManager.getColor(Color.WHITE));
				kaydetButton.setEnabled(true);
				duzenleButton.setEnabled(false);
			}
		});
		duzenleButton.setBounds(322, 497, 100, 23);
		contentPane.add(duzenleButton);
		
		kaydetButton = new JButton("Kaydet");
		kaydetButton.setFocusPainted(false);
		kaydetButton.setEnabled(false);
		kaydetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean error = false;
								
				if(!(okulTuru.getText().matches( "[a-z A-ZğĞüÜşŞıİöÖçÇ.,]+" )) || okulTuru.getText().equals("") || okulTuru.getText().length() > 14) {
					okulTuru.setBackground(Color.PINK);
					error = true;
				}
				else okulTuru.setBackground(Color.WHITE);
				
				
				if(!(okulAdi.getText().matches( "[a-z A-ZğĞüÜşŞıİöÖçÇ.,]+" )) || okulAdi.getText().equals("") || okulAdi.getText().length() > 50) {
					okulAdi.setBackground(Color.PINK);
					error = true;
				}
				else okulAdi.setBackground(Color.WHITE);
				
				if(!(sehir.getText().matches( "[a-z A-ZğĞüÜşŞıİöÖçÇ.,]+" )) || sehir.getText().equals("") || sehir.getText().length() > 20) {
					sehir.setBackground(Color.PINK);
					error = true;
				}
				else sehir.setBackground(Color.WHITE);
				
				if(!(bursiyerCepTel.getText().matches( "[0-9]+" )) || bursiyerCepTel.getText().equals("")) {
					bursiyerCepTel.setBackground(Color.PINK);
					error = true;
				}
				else bursiyerCepTel.setBackground(Color.WHITE);
				
				if(!(sinif.getText().matches( "[0-9]+" )) || sinif.getText().equals("")) {
					sinif.setBackground(Color.PINK);
					error = true;
				}
				else sinif.setBackground(Color.WHITE);
				
				if(!(egitimYili.getText().matches( "[0-9]+" )) || egitimYili.getText().equals("")) {
					egitimYili.setBackground(Color.PINK);
					error = true;
				}
				else egitimYili.setBackground(Color.WHITE);
				
				if(bursiyerEmail.getText().equals("")|| bursiyerEmail.getText().length() > 40) {
					bursiyerEmail.setBackground(Color.PINK);
					error = true;
				}
				else bursiyerEmail.setBackground(Color.WHITE);
				
				if(bankaAdi.getText().equals("")|| bankaAdi.getText().length() > 30) {
					bankaAdi.setBackground(Color.PINK);
					error = true;
				}
				else bankaAdi.setBackground(Color.WHITE);
				
				if(iban.getText().equals("") || iban.getText().length() > 26) {
					iban.setBackground(Color.PINK);
					error = true;
				}
				else iban.setBackground(Color.WHITE);
				
				if(aileAdresi.getText().equals("") || aileAdresi.getText().length() > 75) {
					aileAdresi.setBackground(Color.PINK);
					error = true;
				}
				else aileAdresi.setBackground(Color.WHITE);
				
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
					try {
						setSelectedBursiyerOkulTuru(okulTuru.getText());
						setSelectedBursiyerOkuladi(okulAdi.getText());
						setSelectedBursiyerSinif(sinif.getText());
						setSelectedBursiyersehir(sehir.getText());
						setSelectedBursiyerIban(iban.getText());
						setSelectedBursiyerBankaAdi(bankaAdi.getText());
						setSelectedBursiyerAileAdresi(aileAdresi.getText());
						setSelectedBursiyerMail(bursiyerEmail.getText());
						setSelectedBursiyerCepTel(bursiyerCepTel.getText());
						setSelectedBursiyerEgitimYili(egitimYili.getText());
						setSelectedBursiyerAciklama(aciklama.getText());
						
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn = DriverManager.getConnection(url, username, password);
						Statement statement = conn.createStatement();
						statement.executeUpdate("update bursiyer set BursiyerMail = '"+ getSelectedBursiyerMail()+"', BursiyerCepTel = '"+ getSelectedBursiyerCepTel()+"', OkulTuru = '"+ getSelectedOkulTuru()+
						"', OkulAdi = '"+ getSelectedBursiyerOkulAdi()+"', Sinif = '"+ getSelectedBursiyerSinif()+"', sehir = '"+ getSelectedBursiyerSehir()+"', IBAN = '"+ getSelectedBursiyerIBAN()+"', BankaAdi = '"+getSelectedBursiyerBankaAdi()
						+"', AileAdresi = '"+ getSelectedBursiyerAileAdresi()+"', EgitimYili = '"+ getSelectedBursiyerEgitimYili()+"', aciklama = '"+ getSelectedBursiyerAciklama()+"' where Bursiyerid = '"+ getSelectedBursiyerid()+"'");
					}catch(ClassNotFoundException e2){
						System.out.println(e2);
						System.out.println("Class not found");
					}catch (SQLException e2) {
						System.out.println(e2);
					}
					
					okulTuru.setEditable(false);
					okulAdi.setEditable(false);
					egitimYili.setEditable(false);
					sinif.setEditable(false);
					sehir.setEditable(false);
					bursiyerCepTel.setEditable(false);
					bursiyerEmail.setEditable(false);
					bankaAdi.setEditable(false);
					iban.setEditable(false);
					aileAdresi.setEditable(false);
										
					okulTuru.setBackground(UIManager.getColor("TextField.inactiveBackground"));
					okulAdi.setBackground(UIManager.getColor("TextField.inactiveBackground"));
					egitimYili.setBackground(UIManager.getColor("TextField.inactiveBackground"));
					sinif.setBackground(UIManager.getColor("TextField.inactiveBackground"));
					sehir.setBackground(UIManager.getColor("TextField.inactiveBackground"));
					bursiyerCepTel.setBackground(UIManager.getColor("TextField.inactiveBackground"));
					bursiyerEmail.setBackground(UIManager.getColor("TextField.inactiveBackground"));
					bankaAdi.setBackground(UIManager.getColor("TextField.inactiveBackground"));
					iban.setBackground(UIManager.getColor("TextField.inactiveBackground"));
					aileAdresi.setBackground(UIManager.getColor("TextField.inactiveBackground"));
							
					aciklama.setEditable(false);
					aciklama.setBackground(UIManager.getColor("TextField.inactiveBackground"));
					kaydetButton.setEnabled(false);
					duzenleButton.setEnabled(true);
					
					okulTuru.setToolTipText(null);
					okulAdi.setToolTipText(null);
					sehir.setToolTipText(null);
					bursiyerCepTel.setToolTipText(null);
					
					JOptionPane.showMessageDialog(null, "Düzenleme işlemi başarıyla tamamlanmıştır");
	
				}
			}
		});
		kaydetButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
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
					statement.executeUpdate("delete from bursiyer where Bursiyerid = '"+ getSelectedBursiyerid() + "'");
					statement.executeUpdate("delete from bursiyer_sponsormatch where BursiyerNo = '"+ getSelectedBursiyerid() + "'");
					bursiyerAdi.setText("");
					bursiyerSoyadi.setText("");
					okulTuru.setText("");
					okulAdi.setText("");
					sinif.setText("");
					bursiyerCepTel.setText("");
					bursiyerEmail.setText("");
					egitimYili.setText("");
					sehir.setText("");
					bankaAdi.setText("");
					iban.setText("");
					aileAdresi.setText("");
					aciklama.setText("");
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
	
	public static String getSelectedBursiyerName() {
		return selectedBursiyerName;
	}
	public static String getSelectedBursiyerSurname() {
		return selectedBursiyerSurname;
	}
	public static String getSelectedBursiyerMail() {
		return selectedBursiyerMail;
	}
	public static String getSelectedBursiyerCepTel(){
		return selectedBursiyerCepTel;
	}
	public static String getSelectedOkulTuru() {
		return selectedBursiyerOkulTuru;
	}
	public static String getSelectedBursiyerOkulAdi(){
		return selectedBursiyerOkuladi;
	}
	public static String getSelectedBursiyerSinif(){
		return selectedBursiyerSinif;
	}
	public static String getSelectedBursiyerSehir(){
		return selectedBursiyersehir;
	}
	public static String getSelectedBursiyerIBAN(){
		return selectedBursiyerIban;
	}
	public static String getSelectedBursiyerBankaAdi(){
		return selectedBursiyerBankaAdi;
	}
	public static String getSelectedBursiyerAileAdresi(){
		return selectedBursiyerAileAdresi;
	}
	public static String getSelectedBursiyerEgitimYili() {
		return selectedBursiyerEgitimYili;
	}
	public static String getSelectedBursiyerAciklama() {
		return selectedBursiyerAciklama;
	}
	public static int getSelectedBursiyerid() {
		return selectedBursiyerid;
	}

	public static String getSelectedBursiyerOkulTuru() {
		return selectedBursiyerOkulTuru;
	}

	public static void setSelectedBursiyerOkulTuru(String selectedBursiyerOkulTuru) {
		BursiyerGoruntule.selectedBursiyerOkulTuru = selectedBursiyerOkulTuru;
	}

	public static String getSelectedBursiyerOkuladi() {
		return selectedBursiyerOkuladi;
	}

	public static void setSelectedBursiyerOkuladi(String selectedBursiyerOkuladi) {
		BursiyerGoruntule.selectedBursiyerOkuladi = selectedBursiyerOkuladi;
	}

	public static String getSelectedBursiyersehir() {
		return selectedBursiyersehir;
	}

	public static void setSelectedBursiyersehir(String selectedBursiyersehir) {
		BursiyerGoruntule.selectedBursiyersehir = selectedBursiyersehir;
	}

	public static String getSelectedBursiyerIban() {
		return selectedBursiyerIban;
	}

	public static void setSelectedBursiyerIban(String selectedBursiyerIban) {
		BursiyerGoruntule.selectedBursiyerIban = selectedBursiyerIban;
	}

	public static void setSelectedBursiyerName(String selectedBursiyerName) {
		BursiyerGoruntule.selectedBursiyerName = selectedBursiyerName;
	}

	public static void setSelectedBursiyerSurname(String selectedBursiyerSurname) {
		BursiyerGoruntule.selectedBursiyerSurname = selectedBursiyerSurname;
	}

	public static void setSelectedBursiyerMail(String selectedBursiyerMail) {
		BursiyerGoruntule.selectedBursiyerMail = selectedBursiyerMail;
	}

	public static void setSelectedBursiyerCepTel(String selectedBursiyerCepTel) {
		BursiyerGoruntule.selectedBursiyerCepTel = selectedBursiyerCepTel;
	}

	public static void setSelectedBursiyerSinif(String selectedBursiyerSinif) {
		BursiyerGoruntule.selectedBursiyerSinif = selectedBursiyerSinif;
	}

	public static void setSelectedBursiyerBankaAdi(String selectedBursiyerBankaAdi) {
		BursiyerGoruntule.selectedBursiyerBankaAdi = selectedBursiyerBankaAdi;
	}

	public static void setSelectedBursiyerAileAdresi(String selectedBursiyerAileAdresi) {
		BursiyerGoruntule.selectedBursiyerAileAdresi = selectedBursiyerAileAdresi;
	}

	public static void setSelectedBursiyerEgitimYili(String selectedBursiyerEgitimYili) {
		BursiyerGoruntule.selectedBursiyerEgitimYili = selectedBursiyerEgitimYili;
	}

	public static void setSelectedBursiyerAciklama(String selectedBursiyerAciklama) {
		BursiyerGoruntule.selectedBursiyerAciklama = selectedBursiyerAciklama;
	}

	public static void setSelectedBursiyerid(int selectedBursiyerid) {
		BursiyerGoruntule.selectedBursiyerid = selectedBursiyerid;
	}
}
