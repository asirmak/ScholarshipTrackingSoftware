
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class BursiyerSponsorGoruntule extends JFrame {

	private JPanel contentPane;
	private JLabel sponsorAdiSoyadi;
	private JLabel message1;
	private JButton anaMenuButton;
	private JButton silButton;
	private JTable table;
	private JScrollPane scrollPane;
	private JTable table_1;
	private static int selectedSponsorid;
	private static int selectedBursiyerid;
	private static String selectedSponsorName;
	private static String selectedSponsorSurname;
	private static String selectedAylikBurs;
	private static String selectedAySayisi;
	private static String selectedEgitimYili;
	private static String selectedAciklama;
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
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
					UIManager.setLookAndFeel(lookAndFeel);
					BursiyerSponsorGoruntule frame = new BursiyerSponsorGoruntule();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	public BursiyerSponsorGoruntule() {
		
		String url="jdbc:mysql://127.0.0.1:3306/databasedeneme";
		String username = "root";
		String password="060723";
		
		setResizable(false);
		setTitle("Bursiyer-Sponsor Eşleştirme");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1600, 900);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		try {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, username, password);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("select * from sponsor where Sponsorid = '"+ getSelectedSponsorid() + "'");
			
			while(rs.next()) {			
				setSelectedSponsorName(rs.getString("SponsorAdi"));
				setSelectedSponsorSurname(rs.getString("SponsorSoyadi"));
			}
			
		}catch(ClassNotFoundException ex){
			System.out.println(ex);
			System.out.println("Class not found");
		}catch (SQLException ex2) {
			System.out.println(ex2);
		}	
		
		
		message1 = new JLabel("Bursiyer-Sponsor Eşleştirme");
		message1.setBounds(10, 10, 1564, 30);
		message1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		message1.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(message1);
		
		sponsorAdiSoyadi = new JLabel(getSelectedSponsorName() +" "+ getSelectedSponsorSurname());
		sponsorAdiSoyadi.setBounds(10, 61, 1564, 39);
		sponsorAdiSoyadi.setHorizontalAlignment(SwingConstants.CENTER);
		sponsorAdiSoyadi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(sponsorAdiSoyadi);
				
		anaMenuButton = new JButton("Ana Menü");
		anaMenuButton.setFocusPainted(false);
		anaMenuButton.setBounds(1474, 827, 100, 23);
		anaMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				GirisEkrani.main(null);
			}
		});
		contentPane.add(anaMenuButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 111, 1564, 311);
		contentPane.add(scrollPane);
		
		table = new JTable(){
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
	        	 return false;
	         }
		};		
		scrollPane.setViewportView(table);		
		
		silButton = new JButton("Sil");
		silButton.setFocusPainted(false);
		silButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection(url, username, password);
					Statement statement = conn.createStatement();
					statement.executeUpdate("DELETE FROM bursiyer_sponsormatch WHERE Sponsorno = '" + getSelectedSponsorid() + "' AND Bursiyerno = '" + getSelectedBursiyerid() + "'");
				}catch(ClassNotFoundException ex){
					System.out.println(ex);
					System.out.println("Class not found");
				}catch (SQLException ex2) {
					System.out.println(ex2);
				}
				
				if(table.getSelectedRowCount() == 1) {
					tblModel.removeRow(table.getSelectedRow());
				}
				else {
					if(table.getRowCount() == 0) { // table empty
						JOptionPane.showMessageDialog(null, "Silinecek öğe yok.");

					}
					else { //table not empty row not selected or multi-selection
						JOptionPane.showMessageDialog(null, "Silinecek öğe seç.");
					}
				}
								
			}
		});
		silButton.setBounds(1359, 827, 100, 23);
		contentPane.add(silButton);
				
		try {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, username, password);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("select * from bursiyer_sponsormatch where SponsorNo = '"+ getSelectedSponsorid() + "'");
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			
			String[] colName= {"SPONSOR ID", "BURSİYER ID", "AYLIK BAĞIŞ", "AY", "EĞİTİM YILI", "AÇIKLAMA"};

			model.setColumnIdentifiers(colName);
			
			while(rs.next()) {			
				setSelectedSponsorid(rs.getInt("SponsorNo"));
				setSelectedBursiyerid(rs.getInt("BursiyerNo"));
				setSelectedAylikBurs(rs.getString("AylikBurs"));
				setSelectedAySayisi(rs.getString("AySayisi"));
				setSelectedEgitimYili(rs.getString("EgitimYili"));
				setSelectedAciklama(rs.getString("aciklama"));
	            
	            String[] row = {String.valueOf(getSelectedSponsorid()), String.valueOf(getSelectedBursiyerid()), getSelectedAylikBurs(), getSelectedAySayisi(),
	            		getSelectedEgitimYili(),getSelectedAciklama()};
	            model.addRow(row);
			}


		}catch(ClassNotFoundException e){
			System.out.println(e);
			System.out.println("Class not found");
		}catch (SQLException e) {
			System.out.println(e);
		}
		
		JLabel lblBursiyerBilgileri = new JLabel("Bursiyer Bilgileri");
		lblBursiyerBilgileri.setHorizontalAlignment(SwingConstants.CENTER);
		lblBursiyerBilgileri.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBursiyerBilgileri.setBounds(10, 455, 1564, 39);
		contentPane.add(lblBursiyerBilgileri);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(10, 505, 1564, 311);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable(){
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
	        	 return false;
	         }
		};	
		scrollPane_1.setViewportView(table_1);
	
		table.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        JTable table =(JTable) mouseEvent.getSource();
		        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
		        	try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn = DriverManager.getConnection(url, username, password);
						Statement statement = conn.createStatement();
						ResultSet rs = statement.executeQuery("SELECT * FROM bursiyer WHERE Bursiyerid = " + table.getValueAt(table.getSelectedRow(), 1));
						DefaultTableModel model = (DefaultTableModel) table_1.getModel();
						
						String[] colName= {"BURSİYER ADI", "BURSİYER SOYADI", "BURSİYER ID", "OKUL BİLGİSİ", "OKUL ADI","SINIF", "ŞEHİR", 
								"IBAN NO", "BANKA ADI", "AİLE ADRES", "BURSİYER E-MAİL", "BURSİYER CEP TEL", "EĞİTİM YILI", "AÇIKLAMA"};

						model.setColumnIdentifiers(colName);
						
						while (rs.next()) {
							setSelectedBursiyerName(rs.getString("BursiyerAdi"));
							setSelectedBursiyerSurname(rs.getString("BursiyerSoyadi"));
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
							setSelectedBursiyerid(rs.getInt("Bursiyerid"));
							setSelectedBursiyerMail(rs.getString("BursiyerMail"));
			                
			                String[] row1 = {getSelectedBursiyerName(), getSelectedBursiyerSurname(), String.valueOf(getSelectedBursiyerid()), getSelectedBursiyerOkulTuru(), getSelectedBursiyerOkuladi(), getSelectedBursiyerSinif()
			                		,getSelectedBursiyersehir(), getSelectedBursiyerIban(), getSelectedBursiyerBankaAdi(), getSelectedBursiyerAileAdresi(), getSelectedBursiyerMail(), getSelectedBursiyerCepTel(),getSelectedBursiyerEgitimYili()
			                		,getSelectedBursiyerAciklama()};
			                
				            model.addRow(row1);
			            }
			
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
		        }
		    }
		});
		
	}


	public static int getSelectedSponsorid() {
		return selectedSponsorid;
	}


	public static void setSelectedSponsorid(int selectedSponsorid) {
		BursiyerSponsorGoruntule.selectedSponsorid = selectedSponsorid;
	}


	public static int getSelectedBursiyerid() {
		return selectedBursiyerid;
	}


	public static void setSelectedBursiyerid(int selectedBursiyerid) {
		BursiyerSponsorGoruntule.selectedBursiyerid = selectedBursiyerid;
	}


	public static String getSelectedSponsorName() {
		return selectedSponsorName;
	}


	public static void setSelectedSponsorName(String selectedSponsorName) {
		BursiyerSponsorGoruntule.selectedSponsorName = selectedSponsorName;
	}


	public static String getSelectedSponsorSurname() {
		return selectedSponsorSurname;
	}


	public static void setSelectedSponsorSurname(String selectedSponsorSurname) {
		BursiyerSponsorGoruntule.selectedSponsorSurname = selectedSponsorSurname;
	}


	public static String getSelectedAylikBurs() {
		return selectedAylikBurs;
	}


	public static void setSelectedAylikBurs(String selectedAylikBurs) {
		BursiyerSponsorGoruntule.selectedAylikBurs = selectedAylikBurs;
	}


	public static String getSelectedAySayisi() {
		return selectedAySayisi;
	}


	public static void setSelectedAySayisi(String selectedAySayisi) {
		BursiyerSponsorGoruntule.selectedAySayisi = selectedAySayisi;
	}


	public static String getSelectedEgitimYili() {
		return selectedEgitimYili;
	}


	public static void setSelectedEgitimYili(String selectedEgitimYili) {
		BursiyerSponsorGoruntule.selectedEgitimYili = selectedEgitimYili;
	}


	public static String getSelectedAciklama() {
		return selectedAciklama;
	}


	public static void setSelectedAciklama(String selectedAciklama) {
		BursiyerSponsorGoruntule.selectedAciklama = selectedAciklama;
	}


	public static String getSelectedBursiyerName() {
		return selectedBursiyerName;
	}


	public static void setSelectedBursiyerName(String selectedBursiyerName) {
		BursiyerSponsorGoruntule.selectedBursiyerName = selectedBursiyerName;
	}


	public static String getSelectedBursiyerSurname() {
		return selectedBursiyerSurname;
	}


	public static void setSelectedBursiyerSurname(String selectedBursiyerSurname) {
		BursiyerSponsorGoruntule.selectedBursiyerSurname = selectedBursiyerSurname;
	}


	public static String getSelectedBursiyerMail() {
		return selectedBursiyerMail;
	}


	public static void setSelectedBursiyerMail(String selectedBursiyerMail) {
		BursiyerSponsorGoruntule.selectedBursiyerMail = selectedBursiyerMail;
	}


	public static String getSelectedBursiyerCepTel() {
		return selectedBursiyerCepTel;
	}


	public static void setSelectedBursiyerCepTel(String selectedBursiyerCepTel) {
		BursiyerSponsorGoruntule.selectedBursiyerCepTel = selectedBursiyerCepTel;
	}


	public static String getSelectedBursiyerOkulTuru() {
		return selectedBursiyerOkulTuru;
	}


	public static void setSelectedBursiyerOkulTuru(String selectedBursiyerOkulTuru) {
		BursiyerSponsorGoruntule.selectedBursiyerOkulTuru = selectedBursiyerOkulTuru;
	}


	public static String getSelectedBursiyerOkuladi() {
		return selectedBursiyerOkuladi;
	}


	public static void setSelectedBursiyerOkuladi(String selectedBursiyerOkuladi) {
		BursiyerSponsorGoruntule.selectedBursiyerOkuladi = selectedBursiyerOkuladi;
	}


	public static String getSelectedBursiyerSinif() {
		return selectedBursiyerSinif;
	}


	public static void setSelectedBursiyerSinif(String selectedBursiyerSinif) {
		BursiyerSponsorGoruntule.selectedBursiyerSinif = selectedBursiyerSinif;
	}


	public static String getSelectedBursiyersehir() {
		return selectedBursiyersehir;
	}


	public static void setSelectedBursiyersehir(String selectedBursiyersehir) {
		BursiyerSponsorGoruntule.selectedBursiyersehir = selectedBursiyersehir;
	}


	public static String getSelectedBursiyerIban() {
		return selectedBursiyerIban;
	}


	public static void setSelectedBursiyerIban(String selectedBursiyerIban) {
		BursiyerSponsorGoruntule.selectedBursiyerIban = selectedBursiyerIban;
	}


	public static String getSelectedBursiyerBankaAdi() {
		return selectedBursiyerBankaAdi;
	}


	public static void setSelectedBursiyerBankaAdi(String selectedBursiyerBankaAdi) {
		BursiyerSponsorGoruntule.selectedBursiyerBankaAdi = selectedBursiyerBankaAdi;
	}


	public static String getSelectedBursiyerAileAdresi() {
		return selectedBursiyerAileAdresi;
	}


	public static void setSelectedBursiyerAileAdresi(String selectedBursiyerAileAdresi) {
		BursiyerSponsorGoruntule.selectedBursiyerAileAdresi = selectedBursiyerAileAdresi;
	}


	public static String getSelectedBursiyerEgitimYili() {
		return selectedBursiyerEgitimYili;
	}


	public static void setSelectedBursiyerEgitimYili(String selectedBursiyerEgitimYili) {
		BursiyerSponsorGoruntule.selectedBursiyerEgitimYili = selectedBursiyerEgitimYili;
	}


	public static String getSelectedBursiyerAciklama() {
		return selectedBursiyerAciklama;
	}


	public static void setSelectedBursiyerAciklama(String selectedBursiyerAciklama) {
		BursiyerSponsorGoruntule.selectedBursiyerAciklama = selectedBursiyerAciklama;
	}
}
