
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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class GirisEkrani extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu menuHelp;
	private JMenuItem menuItemAbout;
	private JLabel message1;
	private JLabel message2;
	private JButton yeniBursiyerGirisButton;
	private JButton yeniSponsorGirisButton;
	private JButton yeniBursiyerSponsorGirisButton;
	private JButton bursiyerGoruntuleButton;
	private JButton sponsorGoruntuleButton;
	private JButton bursiyerSponsorGoruntuleButton;
	private static JComboBox<Object> sponsorComboBox;
	private static JComboBox<Object> bursiyerComboBox;
	private static JComboBox<String> raporlarComboBox;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
					UIManager.setLookAndFeel(lookAndFeel);
					GirisEkrani frame = new GirisEkrani();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public GirisEkrani() {
		setVisible(true);
		setResizable(false);
		setTitle("Bursiyer Uygulaması");
		setForeground(Color.BLACK);
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		setLocationRelativeTo(null);
	
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuHelp = new JMenu("Help");
		menuBar.add(menuHelp);
		
		menuItemAbout = new JMenuItem("About");
		menuItemAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String about = "About Developers\n"
        				+ "Ali Sencer Irmak\n"
        				+ "alisencer.irmak@std.yeditepe.edu.tr\n"
        				+ "Buğra Kurgan\n"
        				+ "bugra.kurgan@std.yeditepe.edu.tr";
            	JOptionPane.showMessageDialog(null, about, "Info", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menuHelp.add(menuItemAbout);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		message1 = new JLabel("Uygulamaya Hoş Geldiniz!");
		message1.setHorizontalAlignment(SwingConstants.CENTER);
		message1.setBounds(10, 26, 364, 14);
		contentPane.add(message1);
		
		message2 = new JLabel("Lütfen aşağıdan yapmak istediğiniz işlemi seçiniz.");
		message2.setHorizontalAlignment(SwingConstants.CENTER);
		message2.setBounds(10, 51, 364, 14);
		contentPane.add(message2);
		
		yeniBursiyerGirisButton = new JButton("Yeni Bursiyer Girişi");
		yeniBursiyerGirisButton.setBackground(new Color(255, 255, 255));
		yeniBursiyerGirisButton.setFocusable(false);
		yeniBursiyerGirisButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				YeniBursiyerGiris.main(null);
			}
		});
		yeniBursiyerGirisButton.setBounds(42, 86, 300, 23);
		contentPane.add(yeniBursiyerGirisButton);
		
		yeniSponsorGirisButton = new JButton("Yeni Sponsor Girişi");
		yeniSponsorGirisButton.setFocusable(false);
		yeniSponsorGirisButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				YeniSponsorGiris.main(null);
			}
		});
		yeniSponsorGirisButton.setBounds(42, 119, 300, 23);
		contentPane.add(yeniSponsorGirisButton);
		
		yeniBursiyerSponsorGirisButton = new JButton("Yeni Bursiyer-Sponsor Eşleştirme");
		yeniBursiyerSponsorGirisButton.setFocusable(false);
		yeniBursiyerSponsorGirisButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				BursiyerSponsorEslestirme.main(null);
			}
		});
		yeniBursiyerSponsorGirisButton.setBounds(42, 153, 300, 23);
		contentPane.add(yeniBursiyerSponsorGirisButton);
		

		
		bursiyerGoruntuleButton = new JButton("Bursiyer Görüntüle / Sil");
		bursiyerGoruntuleButton.setFocusable(false);
		bursiyerGoruntuleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				bursiyerComboBoxCreate();
				
				dispose();				
				JOptionPane.showMessageDialog(null, bursiyerComboBox, "Bursiyerler", JOptionPane.QUESTION_MESSAGE);
				String selectedBursiyerName = (String) bursiyerComboBox.getSelectedItem();
				int selectedBursiyerid = (int) bursiyerComboBox.getClientProperty(selectedBursiyerName);
				BursiyerGoruntule.setSelectedBursiyerid(selectedBursiyerid);
				BursiyerGoruntule.main(null);
				
			}
		});
		bursiyerGoruntuleButton.setBounds(42, 187, 300, 23);
		contentPane.add(bursiyerGoruntuleButton);

		sponsorGoruntuleButton = new JButton("Sponsor Görüntüle / Sil");
		sponsorGoruntuleButton.setFocusable(false);
		sponsorGoruntuleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
				sponsorComboBoxCreate();
				
				dispose();
				JOptionPane.showMessageDialog(null, sponsorComboBox, "Sponsorlar", JOptionPane.QUESTION_MESSAGE);
				String selectedSponsor = (String) sponsorComboBox.getSelectedItem();
				int selectedSponsorid = (int) sponsorComboBox.getClientProperty(selectedSponsor);
				SponsorGoruntule.setSelectedSponsorid(selectedSponsorid);
				SponsorGoruntule.main(null);
			}
		});
		sponsorGoruntuleButton.setBounds(42, 221, 300, 23);
		contentPane.add(sponsorGoruntuleButton);
		
		bursiyerSponsorGoruntuleButton = new JButton("Bursiyer-Sponsor Eşleştirme Görüntüle / Sil");
		bursiyerSponsorGoruntuleButton.setFocusable(false);
		bursiyerSponsorGoruntuleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				sponsorComboBoxCreate();
				dispose();
				
				JOptionPane.showMessageDialog(null, sponsorComboBox, "Sponsorlar", JOptionPane.QUESTION_MESSAGE);
				String selectedSponsor = (String) sponsorComboBox.getSelectedItem();
				int selectedSponsorId = (int) sponsorComboBox.getClientProperty(selectedSponsor); // Seçilen sponsorun ID'sini alıyoruz.

				BursiyerSponsorGoruntule.setSelectedSponsorid(selectedSponsorId);
				BursiyerSponsorGoruntule.main(null);

			}
		});
		bursiyerSponsorGoruntuleButton.setBounds(42, 255, 300, 23);
		contentPane.add(bursiyerSponsorGoruntuleButton);
		
		JButton excelExportButton = new JButton("Excel Rapor Al");
		excelExportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				raporlarComboBox = new JComboBox<>();
				raporlarComboBox.addItem("Hangi Sponsor kime ne kadar burs vermiş");
				raporlarComboBox.addItem("Hangi Öğrenci ne kadar burs almış");
				raporlarComboBox.addItem("Toplam öğrenci sayısı");
				raporlarComboBox.addItem("Toplam sponsor sayısı");
				raporlarComboBox.addItem("Eşleştirilmiş Sponsor ve Bursiyerlerle ilgili tüm bilgiler");
				raporlarComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
							
				JOptionPane.showMessageDialog(null, raporlarComboBox, "Raporlar", JOptionPane.QUESTION_MESSAGE);
								
				if (raporlarComboBox.getSelectedIndex() == 0) {
					sponsorComboBoxCreate();
					JOptionPane.showMessageDialog(null, sponsorComboBox, "Sponsorlar", JOptionPane.QUESTION_MESSAGE);
					String selectedSponsor = (String) sponsorComboBox.getSelectedItem();
					int selectedSponsorId = (int) sponsorComboBox.getClientProperty(selectedSponsor); // Seçilen sponsorun ID'sini alıyoruz.
					excelRapor.setSponsorId(selectedSponsorId);
					excelRapor.setSelectedRaporIndex(raporlarComboBox.getSelectedIndex());
					excelRapor.setRaporText(selectedSponsor + " kime ne kadar burs vermiş, yıllara göre.");

				}
				else if(raporlarComboBox.getSelectedIndex() == 1) {
					bursiyerComboBoxCreate(); 
					JOptionPane.showMessageDialog(null, bursiyerComboBox, "Bursiyerler", JOptionPane.QUESTION_MESSAGE);
					String selectedBursiyer = (String) bursiyerComboBox.getSelectedItem();
					int selectedBursiyerId = (int) bursiyerComboBox.getClientProperty(selectedBursiyer); // Seçilen bursiyerin ID'sini alıyoruz.
					excelRapor.setBursiyerId(selectedBursiyerId);
					excelRapor.setSelectedRaporIndex(raporlarComboBox.getSelectedIndex());
					excelRapor.setRaporText(selectedBursiyer + " ne kadar burs almış, yıllara göre.");

				}
				else if(raporlarComboBox.getSelectedIndex() == 2) {
					excelRapor.setSelectedRaporIndex(raporlarComboBox.getSelectedIndex());
					excelRapor.setRaporText((String) raporlarComboBox.getSelectedItem());

				}
				else if(raporlarComboBox.getSelectedIndex() == 3) {
					excelRapor.setSelectedRaporIndex(raporlarComboBox.getSelectedIndex());
					excelRapor.setRaporText((String) raporlarComboBox.getSelectedItem());

				}
				else if(raporlarComboBox.getSelectedIndex() == 4) {
					excelRapor.setSelectedRaporIndex(raporlarComboBox.getSelectedIndex());
					excelRapor.setRaporText((String) raporlarComboBox.getSelectedItem());
				}	
				
				dispose();
				excelRapor.main(null);
			}
		});
		excelExportButton.setFocusable(false);
		excelExportButton.setBounds(42, 289, 300, 23);
		contentPane.add(excelExportButton);
	}
	
	
	public static void sponsorComboBoxCreate() {
		
		String url="jdbc:mysql://127.0.0.1:3306/databasedeneme";
		String username = "root";
		String password="060723";
		
		
		sponsorComboBox = new JComboBox<Object>();
		sponsorComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sponsorComboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {}));
		
		try {
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    Connection conn1 = DriverManager.getConnection(url, username, password);
		    Statement statement1 = conn1.createStatement();
		    ResultSet rs1 = statement1.executeQuery("select * from sponsor");

		    while (rs1.next()) {
		        String name = rs1.getString("SponsorAdi");
		        String surname = rs1.getString("SponsorSoyadi");
		        int sponsorId = rs1.getInt("Sponsorid");
		        String displayName = name + " " + surname;
		        sponsorComboBox.addItem(displayName);
		        sponsorComboBox.putClientProperty(displayName, sponsorId); // Sponsorun ID'sini client property olarak ekliyoruz.
		    }
		    
		    conn1.close();
		    statement1.close();
			
		}catch (ClassNotFoundException e1) {
		    System.out.println(e1);
		    System.out.println("Class not found");
		}catch (SQLException e1) {
		    System.out.println(e1);
		}
	}
	
	public static void bursiyerComboBoxCreate() {
		try {
			String url="jdbc:mysql://127.0.0.1:3306/databasedeneme";
			String username = "root";
			String password="060723";
			
			bursiyerComboBox = new JComboBox<Object>();
			bursiyerComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
			bursiyerComboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {}));
			
		    Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn2 = DriverManager.getConnection(url, username, password);
			Statement statement2 = conn2.createStatement();
			ResultSet rs2 = statement2.executeQuery("select * from bursiyer");
			
			while (rs2.next()) {
                String name = rs2.getString("BursiyerAdi");
                String surname = rs2.getString("BursiyerSoyadi");
                int bursiyerid = rs2.getInt("Bursiyerid");
		        String displayName = name + " " + surname;
                bursiyerComboBox.addItem(displayName);
                bursiyerComboBox.putClientProperty(displayName, bursiyerid); // Bursiyer ID'sini client property olarak ekliyoruz.                
            }
			conn2.close();
			statement2.close();
			
		}catch (ClassNotFoundException e1) {
		    System.out.println(e1);
		    System.out.println("Class not found");
		}catch (SQLException e1) {
		    System.out.println(e1);
		}
	}
	
}