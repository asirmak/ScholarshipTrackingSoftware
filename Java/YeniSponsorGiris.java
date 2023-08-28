
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class YeniSponsorGiris extends JFrame {

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
	private JTextArea aciklama;
	private JTextField sponsorCepTel;
	private JTextField sponsorEmail;
	private JTextField sponsorSoyadi;
	private JTextField sponsorAdi;
	private JTextField egitimYili;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
					UIManager.setLookAndFeel(lookAndFeel);
					YeniSponsorGiris frame = new YeniSponsorGiris();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public YeniSponsorGiris() {
		setBackground(Color.WHITE);
		setResizable(false);
		setTitle("Yeni Sponsor Girişi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 570);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		message1 = new JLabel("Yeni Sponsor Girişi");
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
		sponsorCepTel.setToolTipText("Sadece rakam geçerlidir. 0-9. Boşluk Koymayınız.");
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
		
		kaydetButton = new JButton("Kaydet");
		kaydetButton.setFocusPainted(false);
		kaydetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean error = false;
				
				if( !(sponsorAdi.getText().matches( "[a-z A-ZğĞüÜşŞıİöÖçÇ.,]+" ))  || sponsorAdi.getText().equals("") || sponsorAdi.getText().length() > 30) {
					sponsorAdi.setBackground(Color.PINK);
					error = true;
				}
				else sponsorAdi.setBackground(Color.WHITE);

					
				if(!(sponsorSoyadi.getText().matches( "[a-z A-ZğĞüÜşŞıİöÖçÇ.,]+" )) || sponsorSoyadi.getText().equals("") || sponsorSoyadi.getText().length() > 30) {
					sponsorSoyadi.setBackground(Color.PINK);
					error = true;
				}
				else sponsorSoyadi.setBackground(Color.WHITE);
				
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
					String url="jdbc:mysql://127.0.0.1:3306/databasedeneme";
					String username = "root";
					String password="060723";
					try {
						String SponsorName = sponsorAdi.getText();
						String SponsorSurname = sponsorSoyadi.getText();
						String SponsorMail = sponsorEmail.getText();
						String SponsorCepTel = (sponsorCepTel.getText());
						String EgitimYili = (egitimYili.getText());
						String Aciklama = aciklama.getText();
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn = DriverManager.getConnection(url, username, password);
						Statement statement = conn.createStatement();
						statement.executeUpdate("INSERT INTO sponsor (SponsorAdi, SponsorSoyadi, SponsorMail, SponsorCepTel, EgitimYili, aciklama) VALUES"
								+ "('"+ SponsorName+"','"+ SponsorSurname+"','"+SponsorMail+"','"+SponsorCepTel+"','"+EgitimYili+"','"+Aciklama+"')");
						aciklama.setText("");
						sponsorCepTel.setText("");
						sponsorEmail.setText("");
						sponsorSoyadi.setText("");
						sponsorAdi.setText("");
						egitimYili.setText("");
						JOptionPane.showMessageDialog(null, "Sponsor başarıyla kaydedilmiştir.");
						
					}catch(ClassNotFoundException e1){
						System.out.println(e1);
						System.out.println("Class not found");
					}catch (SQLException e1) {
						System.out.println(e1);
					}
					
					
				} 
			}
		});
		kaydetButton.setBounds(434, 497, 100, 23);
		contentPane.add(kaydetButton);
	}
}
