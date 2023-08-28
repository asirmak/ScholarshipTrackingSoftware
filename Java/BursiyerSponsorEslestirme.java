
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class BursiyerSponsorEslestirme extends JFrame {

	private JPanel contentPane;
	private JLabel sponsorAdiSoyadi;
	private JLabel egitimYiliLabel;
	private JLabel aySayisiLabel;
	private JLabel message1;
	private JLabel bursiyerAdiSoyadiLabel;
	private JButton anaMenuButton;
	private JButton kaydetButton;
	private JLabel aciklamaLabel;
	private JLabel aylikBursLabel;
	private JComboBox<Object> bursiyerComboBox;
	private JComboBox<Object> sponsorComboBox;
	private JTextField aySayisi;
	private JTextField egitimYili;
	private JTextArea aciklama;
	private JTextField aylikBurs;
	private static int Bursiyerid;
	private static int Sponsorid;
	private static String BursiyerName;
	private static String BursiyerSurname;
	private static String SponsorName;
	private static String SponsorSurname; 

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
					UIManager.setLookAndFeel(lookAndFeel);
					BursiyerSponsorEslestirme frame = new BursiyerSponsorEslestirme();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BursiyerSponsorEslestirme() {
		String url="jdbc:mysql://127.0.0.1:3306/databasedeneme";
		String username = "root";
		String password="060723";	
		
		setResizable(false);
		setTitle("Bursiyer-Sponsor Eşleştirme");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 570);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		message1 = new JLabel("Bursiyer-Sponsor Eşleştirme");
		message1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		message1.setHorizontalAlignment(SwingConstants.CENTER);
		message1.setBounds(10, 10, 664, 30);
		contentPane.add(message1);
		
		bursiyerAdiSoyadiLabel = new JLabel("Bursiyer Adı-Soyadı");
		bursiyerAdiSoyadiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bursiyerAdiSoyadiLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bursiyerAdiSoyadiLabel.setBounds(40, 51, 250, 20);
		contentPane.add(bursiyerAdiSoyadiLabel);
		
		bursiyerComboBox = new JComboBox<Object>();
		bursiyerComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bursiyerComboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {}));

		try {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, username, password);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("select * from bursiyer");	
			while (rs.next()) {
                String name = rs.getString("BursiyerAdi");
                String surname = rs.getString("BursiyerSoyadi");
                setBursiyerid(rs.getInt("Bursiyerid"));
		        String displayName = name + " " + surname;
                bursiyerComboBox.addItem(displayName);
                bursiyerComboBox.putClientProperty(displayName, getBursiyerid()); // Bursiyer ID'sini client property olarak ekliyoruz.
            }
			statement.close();
	    }catch(ClassNotFoundException e1){
			System.out.println(e1);
			System.out.println("Class not found");
		}catch (SQLException e1) {
			System.out.println(e1);
		}
					
		bursiyerComboBox.setBounds(40, 75, 250, 25);
		contentPane.add(bursiyerComboBox);
		
		sponsorAdiSoyadi = new JLabel("Sponsor Adı-Soyadı");
		sponsorAdiSoyadi.setHorizontalAlignment(SwingConstants.CENTER);
		sponsorAdiSoyadi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sponsorAdiSoyadi.setBounds(394, 51, 250, 20);
		contentPane.add(sponsorAdiSoyadi);
		
		sponsorComboBox = new JComboBox<Object>();
		sponsorComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sponsorComboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {}));
		try {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, username, password);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("select * from sponsor");
			
			while (rs.next()) {
				String name = rs.getString("SponsorAdi");
		        String surname = rs.getString("SponsorSoyadi");
		        int sponsorId = rs.getInt("Sponsorid");
		        String displayName = name + " " + surname;
		        sponsorComboBox.addItem(displayName);
		        sponsorComboBox.putClientProperty(displayName, sponsorId); // Sponsorun ID'sini client property olarak ekliyoruz.	                
            }
			statement.close();
	    }catch(ClassNotFoundException e1){
			System.out.println(e1);
			System.out.println("Class not found");
		}catch (SQLException e1) {
			System.out.println(e1);
		}
		
		sponsorComboBox.setBounds(394, 75, 250, 25);
		contentPane.add(sponsorComboBox);
		
		egitimYiliLabel = new JLabel("Eğitim Yılı");
		egitimYiliLabel.setHorizontalAlignment(SwingConstants.CENTER);
		egitimYiliLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		egitimYiliLabel.setBounds(484, 280, 160, 20);
		contentPane.add(egitimYiliLabel);
		
		egitimYili = new JTextField();
		egitimYili.setToolTipText("Sadece rakam geçerlidir. 0-9");
		egitimYili.setFont(new Font("Tahoma", Font.PLAIN, 14));
		egitimYili.setColumns(10);
		egitimYili.setBounds(484, 304, 160, 25);
		contentPane.add(egitimYili);
		
		aySayisiLabel = new JLabel("Ay Sayısı");
		aySayisiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		aySayisiLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		aySayisiLabel.setBounds(262, 280, 160, 20);
		contentPane.add(aySayisiLabel);
		
		aySayisi = new JTextField();
		aySayisi.setToolTipText("Sadece rakam geçerlidir. 0-9");
		aySayisi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		aySayisi.setColumns(10);
		aySayisi.setBounds(262, 304, 160, 25);
		contentPane.add(aySayisi);
		
		aylikBursLabel = new JLabel("Aylık Burs");
		aylikBursLabel.setHorizontalAlignment(SwingConstants.CENTER);
		aylikBursLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		aylikBursLabel.setBounds(40, 280, 160, 20);
		contentPane.add(aylikBursLabel);
		
		aylikBurs = new JTextField();
		aylikBurs.setToolTipText("Sadece rakam geçerlidir. 0-9");
		aylikBurs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		aylikBurs.setColumns(10);
		aylikBurs.setBounds(40, 304, 160, 25);
		contentPane.add(aylikBurs);
		
		aciklamaLabel = new JLabel("Açıklama");
		aciklamaLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
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
				
				if(!(aylikBurs.getText().matches( "[0-9]+" )) || aylikBurs.getText().equals("")) {
					aylikBurs.setBackground(Color.PINK);
					error = true;
				}
				else aylikBurs.setBackground(Color.WHITE);
			
				
				if(!(aySayisi.getText().matches( "[0-9]+" )) || aySayisi.getText().equals("")) {
					aySayisi.setBackground(Color.PINK);
					error = true;
				}
				else aySayisi.setBackground(Color.WHITE);
				
				if(egitimYili.getText().equals("")) {
					egitimYili.setBackground(Color.PINK);
					error = true;
				}
				else egitimYili.setBackground(Color.WHITE);
				
				if(error) {
					JOptionPane.showMessageDialog(null, "Hatalı bilgi girişi yapıldı.\n"
							+ "Daha fazla bilgi için kutucukların üstüne geliniz.\n"
							+ "Açıklama bölümü hariç, Tüm bölümlerin doldurulması zorunludur.");
				}
				else {			
					try {
						String selectedBursiyerFromCombo = (String) bursiyerComboBox.getSelectedItem();
						setBursiyerid((int) bursiyerComboBox.getClientProperty(selectedBursiyerFromCombo)); // Seçilen bursiyer ID'sini alıyoruz.
						
				    	Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn1 = DriverManager.getConnection(url, username, password);
						Statement statement1 = conn1.createStatement();
						ResultSet rs1 = statement1.executeQuery("select * from bursiyer where Bursiyerid = '"+ getBursiyerid() + "'");	
						while (rs1.next()) {
							setBursiyerName(rs1.getString("BursiyerAdi"));
							setBursiyerSurname(rs1.getString("BursiyerSoyadi"));
						}
					
						String selectedSponsorFromCombo = (String) sponsorComboBox.getSelectedItem();
						setSponsorid((int) sponsorComboBox.getClientProperty(selectedSponsorFromCombo)); // Seçilen sponsor ID'sini alıyoruz.	

				    	Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn2 = DriverManager.getConnection(url, username, password);
						Statement statement2 = conn2.createStatement();
						ResultSet rs2 = statement2.executeQuery("select * from sponsor where Sponsorid = '"+ getSponsorid() + "'");	
						while (rs2.next()) {
							setSponsorName(rs2.getString("SponsorAdi"));
							setSponsorSurname(rs2.getString("SponsorSoyadi"));
						}		
													
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn3 = DriverManager.getConnection(url, username, password);
						Statement statement3 = conn3.createStatement();
						statement3.executeUpdate("INSERT INTO bursiyer_sponsormatch (SponsorNo, BursiyerNo, AylikBurs, AySayisi, EgitimYili, aciklama) VALUES"
								+ "('"+ getSponsorid()+"','"+ getBursiyerid()+"','"+aylikBurs.getText()+"','"+aySayisi.getText()+"','"+egitimYili.getText()+"','"+aciklama.getText()+"')");
					}
					catch(ClassNotFoundException e1){
						System.out.println(e1);
						System.out.println("Class not found");
					}catch (SQLException e1) {
						System.out.println(e1);
					}
					aySayisi.setText("");
					egitimYili.setText("");
					aciklama.setText("");
					aylikBurs.setText("");
					JOptionPane.showMessageDialog(null, "Eşleştirme başarıyla gerçekleştirilmiştir.");
				}
			}
		});
		kaydetButton.setBounds(434, 497, 100, 23);
		contentPane.add(kaydetButton);			
	}
	
	public static int getBursiyerid() {
		return Bursiyerid;
	}
	public static void setBursiyerid(int bursiyerid) {
		Bursiyerid = bursiyerid;
	}
	public static String getBursiyerName() {
		return BursiyerName;
	}
	public static String getBursiyerSurname() {
		return BursiyerSurname;
	}
	public static String getSponsorName() {
		return SponsorName;
	}
	public static String getSponsorSurname() {
		return SponsorSurname;
	}
	public static int getSponsorid() {
		return Sponsorid;
	}
	public static void setSponsorid(int sponsorid) {
		Sponsorid = sponsorid;
	}
	public static void setBursiyerName(String bursiyerName) {
		BursiyerName = bursiyerName;
	}
	public static void setBursiyerSurname(String bursiyerSurname) {
		BursiyerSurname = bursiyerSurname;
	}
	public static void setSponsorName(String sponsorName) {
		SponsorName = sponsorName;
	}
	public static void setSponsorSurname(String sponsorSurname) {
		SponsorSurname = sponsorSurname;
	}
	

}
