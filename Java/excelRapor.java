import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelRapor extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static int bursiyerId;
	private static int sponsorId;
	private String sponsorAdi;
	private String sponsorSoyadi;
	private String sponsorEmail;
	private int aylikBagis;
	private int ay;
	private long toplamTL;
	private String bursiyerAdi;
	private String bursiyerSoyadi;
	private String okul;
	private String okulBilgisi;
	private int sinif;
	private String sehir;
	private String ibanNo;
	private String bankaAdi;
	private String aileAdres;
	private String bursiyerEmail;
	private String bursiyerCepTel;
	private String sponsorCepTel;
	private int bursiyerEgitimYili;
	private int sponsorEgitimYili;
	private int matchingEgitimYili;
	private static String raporText;
	
	
	private static int selectedRaporIndex;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
					UIManager.setLookAndFeel(lookAndFeel);
					excelRapor frame = new excelRapor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public excelRapor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1600, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 71, 1564, 745);
		contentPane.add(scrollPane);
		
		table = new JTable(){
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
	        	 return false;
	         }
		};
		scrollPane.setViewportView(table);
		
		String url="jdbc:mysql://127.0.0.1:3306/databasedeneme";
		String username = "root";
		String password="060723";
		
		if(getSelectedRaporIndex() == 0) {
			try {
		    	Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection(url, username, password);
				Statement statementForMatching = conn.createStatement();
				Statement statementForBursiyer = conn.createStatement();
				Statement statementForSponsor = conn.createStatement();
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				String[] colName= {"EGİTİM YILI", "SPONSOR ADI/SOYADI", "SPONSOR E-MAIL", "AYLIK BAĞIŞ", "AY", "TOPLAM(TL)", "BURSİYER ADI/SOYADI", "OKUL", "OKUL BİLGİSİ"
						,"SINIF", "ŞEHİR", "IBAN NO", "BANKA ADI", "AİLE ADRES", "BURSİYER E-MAİL", "BURSİYER CEP TEL"};
	
				model.setColumnIdentifiers(colName);
	
				ResultSet rsForMatching = statementForMatching.executeQuery("select * from bursiyer_sponsormatch where SponsorNo = '" + getSponsorId() +"' ORDER BY EgitimYili");
				
				while(rsForMatching.next()) {
					setBursiyerId(rsForMatching.getInt("BursiyerNo"));
					setAylikBagis(rsForMatching.getInt("AylikBurs"));
					setAy(rsForMatching.getInt("AySayisi"));
					setMatchingEgitimYili(rsForMatching.getInt("EgitimYili"));
					
					ResultSet rsForBursiyer = statementForBursiyer.executeQuery("select * from bursiyer where Bursiyerid = '" + getBursiyerId() + "'");
					
					while(rsForBursiyer.next()) {
						setBursiyerAdi(rsForBursiyer.getString("BursiyerAdi"));
						setBursiyerSoyadi(rsForBursiyer.getString("BursiyerSoyadi"));
						setOkul(rsForBursiyer.getString("OkulAdi"));
						setOkulBilgisi(rsForBursiyer.getString("OkulTuru"));
						setSinif(rsForBursiyer.getInt("Sinif"));
						setSehir(rsForBursiyer.getString("sehir"));
						setIbanNo(rsForBursiyer.getString("IBAN"));
						setBankaAdi(rsForBursiyer.getString("BankaAdi"));
						setAileAdres(rsForBursiyer.getString("AileAdresi"));
						setBursiyerEmail(rsForBursiyer.getString("BursiyerMail"));
						setBursiyerCepTel(rsForBursiyer.getString("BursiyerCepTel"));
					}
					
					ResultSet rsForSponsor = statementForSponsor.executeQuery("select * from sponsor where Sponsorid = '" + getSponsorId() +"'");
					
					while(rsForSponsor.next()) {			
						setSponsorAdi(rsForSponsor.getString("SponsorAdi"));
						setSponsorSoyadi(rsForSponsor.getString("SponsorSoyadi"));
						setSponsorEmail(rsForSponsor.getString("SponsorMail"));
						
					}
									
					setToplamTL(getAylikBagis()*getAy());
					
					String[] row = {String.valueOf(getMatchingEgitimYili()), getSponsorAdi()+" "+getSponsorSoyadi(), getSponsorEmail(), String.valueOf(getAylikBagis()), String.valueOf(getAy()), String.valueOf(getToplamTL()),
							getBursiyerAdi()+" "+ getBursiyerSoyadi(), getOkul(), getOkulBilgisi(), String.valueOf(getSinif()), getSehir(), getIbanNo(), getBankaAdi(), getAileAdres(), getBursiyerEmail(),
							getBursiyerCepTel()};
		           				
					model.addRow(row);		
				}
	
			}catch(ClassNotFoundException e){
				System.out.println(e);
				System.out.println("Class not found");
			}catch (SQLException e) {
				System.out.println(e);
			}
		}
		else if (getSelectedRaporIndex() == 1) {
			try {
		    	Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection(url, username, password);
				Statement statementForMatching = conn.createStatement();
				Statement statementForBursiyer = conn.createStatement();
				Statement statementForSponsor = conn.createStatement();
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				String[] colName= {"EGİTİM YILI", "SPONSOR ADI/SOYADI", "SPONSOR E-MAIL", "AYLIK BAĞIŞ", "AY", "TOPLAM(TL)", "BURSİYER ADI/SOYADI", "OKUL", "OKUL BİLGİSİ"
						,"SINIF", "ŞEHİR", "IBAN NO", "BANKA ADI", "AİLE ADRES", "BURSİYER E-MAİL", "BURSİYER CEP TEL"};
	
	
				model.setColumnIdentifiers(colName);
	
				ResultSet rsForMatching = statementForMatching.executeQuery("select * from bursiyer_sponsormatch where BursiyerNo = '" + getBursiyerId() +"' ORDER BY EgitimYili");
				
				while(rsForMatching.next()) {
					setSponsorId(rsForMatching.getInt("SponsorNo"));
					setAylikBagis(rsForMatching.getInt("AylikBurs"));
					setAy(rsForMatching.getInt("AySayisi"));
					setMatchingEgitimYili(rsForMatching.getInt("EgitimYili"));
					
					ResultSet rsForBursiyer = statementForBursiyer.executeQuery("select * from bursiyer where Bursiyerid = '" + getBursiyerId() + "'");
					
					while(rsForBursiyer.next()) {
						setBursiyerAdi(rsForBursiyer.getString("BursiyerAdi"));
						setBursiyerSoyadi(rsForBursiyer.getString("BursiyerSoyadi"));
						setOkul(rsForBursiyer.getString("OkulAdi"));
						setOkulBilgisi(rsForBursiyer.getString("OkulTuru"));
						setSinif(rsForBursiyer.getInt("Sinif"));
						setSehir(rsForBursiyer.getString("sehir"));
						setIbanNo(rsForBursiyer.getString("IBAN"));
						setBankaAdi(rsForBursiyer.getString("BankaAdi"));
						setAileAdres(rsForBursiyer.getString("AileAdresi"));
						setBursiyerEmail(rsForBursiyer.getString("BursiyerMail"));
						setBursiyerCepTel(rsForBursiyer.getString("BursiyerCepTel"));
					}
					
					ResultSet rsForSponsor = statementForSponsor.executeQuery("select * from sponsor where Sponsorid = '" + getSponsorId() +"'");
					
					while(rsForSponsor.next()) {			
						setSponsorAdi(rsForSponsor.getString("SponsorAdi"));
						setSponsorSoyadi(rsForSponsor.getString("SponsorSoyadi"));
						setSponsorEmail(rsForSponsor.getString("SponsorMail"));
						
					}
									
					setToplamTL(getAylikBagis()*getAy());
					
					String[] row = {String.valueOf(getMatchingEgitimYili()), getSponsorAdi()+" "+getSponsorSoyadi(), getSponsorEmail(), String.valueOf(getAylikBagis()), String.valueOf(getAy()), String.valueOf(getToplamTL()),
							getBursiyerAdi()+" "+ getBursiyerSoyadi(), getOkul(), getOkulBilgisi(), String.valueOf(getSinif()), getSehir(), getIbanNo(), getBankaAdi(), getAileAdres(), getBursiyerEmail(),
							getBursiyerCepTel()};
		           				
					model.addRow(row);		
				}
	
			}catch(ClassNotFoundException e){
				System.out.println(e);
				System.out.println("Class not found");
			}catch (SQLException e) {
				System.out.println(e);
			}
		}
		
		else if (getSelectedRaporIndex() == 2) {
			
			try {
		    	Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection(url, username, password);
				Statement statementForBursiyer = conn.createStatement();
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				String[] colName= {"EĞİTİM YILI", "BURSİYER ADI/SOYADI", "OKUL", "OKUL BİLGİSİ","SINIF", "ŞEHİR", 
						"IBAN NO", "BANKA ADI", "AİLE ADRES", "BURSİYER E-MAİL", "BURSİYER CEP TEL"};
	
				model.setColumnIdentifiers(colName);
	
				ResultSet rsForBursiyer = statementForBursiyer.executeQuery("select * from bursiyer ORDER BY EgitimYili");
				
				while(rsForBursiyer.next()) {
					setBursiyerAdi(rsForBursiyer.getString("BursiyerAdi"));
					setBursiyerSoyadi(rsForBursiyer.getString("BursiyerSoyadi"));
					setOkul(rsForBursiyer.getString("OkulAdi"));
					setOkulBilgisi(rsForBursiyer.getString("OkulTuru"));
					setSinif(rsForBursiyer.getInt("Sinif"));
					setSehir(rsForBursiyer.getString("sehir"));
					setIbanNo(rsForBursiyer.getString("IBAN"));
					setBankaAdi(rsForBursiyer.getString("BankaAdi"));
					setAileAdres(rsForBursiyer.getString("AileAdresi"));
					setBursiyerEmail(rsForBursiyer.getString("BursiyerMail"));
					setBursiyerCepTel(rsForBursiyer.getString("BursiyerCepTel"));
					setBursiyerEgitimYili(rsForBursiyer.getInt("EgitimYili"));
					
					String[] row = {String.valueOf(getBursiyerEgitimYili()), getBursiyerAdi()+" "+ getBursiyerSoyadi(), getOkul(), getOkulBilgisi(), String.valueOf(getSinif()),
							getSehir(), getIbanNo(), getBankaAdi(), getAileAdres(), getBursiyerEmail(),getBursiyerCepTel()};
		           				
					model.addRow(row);
				}
												
				
			}catch(ClassNotFoundException e){
				System.out.println(e);
				System.out.println("Class not found");
			}catch (SQLException e) {
				System.out.println(e);
			}
		}
		
		else if (getSelectedRaporIndex() == 3) {
			
			try {
		    	Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection(url, username, password);
				Statement statementForSponsor = conn.createStatement();
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				String[] colName= {"EĞİTİM YILI", "SPONSOR ADI/SOYADI", "SPONSOR E-MAIL", "SPONSOR CEP TEL"};
	
				model.setColumnIdentifiers(colName);
	
				ResultSet rsForSponsor = statementForSponsor.executeQuery("select * from sponsor ORDER BY EgitimYili");
				
				while(rsForSponsor.next()) {			
					setSponsorAdi(rsForSponsor.getString("SponsorAdi"));
					setSponsorSoyadi(rsForSponsor.getString("SponsorSoyadi"));
					setSponsorEmail(rsForSponsor.getString("SponsorMail"));
					setSponsorCepTel(rsForSponsor.getString("SponsorCepTel"));
					setSponsorEgitimYili(rsForSponsor.getInt("EgitimYili"));
										
					String[] row = {String.valueOf(getSponsorEgitimYili()),getSponsorAdi()+" "+getSponsorSoyadi(), getSponsorEmail(), getSponsorCepTel()};
		           				
					model.addRow(row);
					
				}		
			}catch(ClassNotFoundException e){
				System.out.println(e);
				System.out.println("Class not found");
			}catch (SQLException e) {
				System.out.println(e);
			}
		}
		
		if(getSelectedRaporIndex() == 4) {
			try {
		    	Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection(url, username, password);
				Statement statementForMatching = conn.createStatement();
				Statement statementForBursiyer = conn.createStatement();
				Statement statementForSponsor = conn.createStatement();
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				String[] colName= {"EĞİTİM YILI","SPONSOR ADI/SOYADI", "SPONSOR E-MAIL", "AYLIK BAĞIŞ", "AY", "TOPLAM(TL)", "BURSİYER ADI/SOYADI", "OKUL", "OKUL BİLGİSİ"
						,"SINIF", "ŞEHİR", "IBAN NO", "BANKA ADI", "AİLE ADRES", "BURSİYER E-MAİL", "BURSİYER CEP TEL"};
	
				model.setColumnIdentifiers(colName);
	
				ResultSet rsForMatching = statementForMatching.executeQuery("select * from bursiyer_sponsormatch ORDER BY EgitimYili");
				
				while(rsForMatching.next()) {
					setSponsorId(rsForMatching.getInt("SponsorNo"));
					setBursiyerId(rsForMatching.getInt("BursiyerNo"));
					setAylikBagis(rsForMatching.getInt("AylikBurs"));
					setAy(rsForMatching.getInt("AySayisi"));
					setMatchingEgitimYili(rsForMatching.getInt("EgitimYili"));
					
					ResultSet rsForBursiyer = statementForBursiyer.executeQuery("select * from bursiyer where Bursiyerid = '" + getBursiyerId() + "'");
					
					while(rsForBursiyer.next()) {
						setBursiyerAdi(rsForBursiyer.getString("BursiyerAdi"));
						setBursiyerSoyadi(rsForBursiyer.getString("BursiyerSoyadi"));
						setOkul(rsForBursiyer.getString("OkulAdi"));
						setOkulBilgisi(rsForBursiyer.getString("OkulTuru"));
						setSinif(rsForBursiyer.getInt("Sinif"));
						setSehir(rsForBursiyer.getString("sehir"));
						setIbanNo(rsForBursiyer.getString("IBAN"));
						setBankaAdi(rsForBursiyer.getString("BankaAdi"));
						setAileAdres(rsForBursiyer.getString("AileAdresi"));
						setBursiyerEmail(rsForBursiyer.getString("BursiyerMail"));
						setBursiyerCepTel(rsForBursiyer.getString("BursiyerCepTel"));
					}
					
					ResultSet rsForSponsor = statementForSponsor.executeQuery("select * from sponsor where Sponsorid = '" + getSponsorId() +"'");
					
					while(rsForSponsor.next()) {			
						setSponsorAdi(rsForSponsor.getString("SponsorAdi"));
						setSponsorSoyadi(rsForSponsor.getString("SponsorSoyadi"));
						setSponsorEmail(rsForSponsor.getString("SponsorMail"));
						
					}
									
					setToplamTL(getAylikBagis()*getAy());
					
					String[] row = {String.valueOf(getMatchingEgitimYili()), getSponsorAdi()+" "+getSponsorSoyadi(), getSponsorEmail(), String.valueOf(getAylikBagis()), String.valueOf(getAy()), String.valueOf(getToplamTL()),
							getBursiyerAdi()+" "+ getBursiyerSoyadi(), getOkul(), getOkulBilgisi(), String.valueOf(getSinif()), getSehir(), getIbanNo(), getBankaAdi(), getAileAdres(), getBursiyerEmail(),
							getBursiyerCepTel()};
		           				
					model.addRow(row);		
				}
	
			}catch(ClassNotFoundException e){
				System.out.println(e);
				System.out.println("Class not found");
			}catch (SQLException e) {
				System.out.println(e);
			}
		}
		
		
		JButton anaMenuButton = new JButton("Ana Menü");
		anaMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				GirisEkrani.main(null);
			}
		});
		anaMenuButton.setBounds(1485, 827, 89, 23);
		contentPane.add(anaMenuButton);
		
		JButton excelDonusturButton = new JButton("Excel'e Dönüştür");
		excelDonusturButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            JFileChooser jFileChooser = new JFileChooser();
		            jFileChooser.showSaveDialog(excelRapor.this);
		            File saveFile = jFileChooser.getSelectedFile();
		            
		            if (saveFile != null) {
		                saveFile = new File(saveFile.toString() + ".xlsx");
		                Workbook wb = new XSSFWorkbook();
		                Sheet sheet = wb.createSheet("rapor");
		                
		                // Verileri alma işlemi
		                DefaultTableModel model = (DefaultTableModel) table.getModel();
		                int rowCount = model.getRowCount();
		                int columnCount = model.getColumnCount();
		                
		                // Başlıkları manuel olarak ekleme
		                Row headerRow = sheet.createRow(0);
		                for (int i = 0; i < columnCount; i++) {
		                    Cell cell = headerRow.createCell(i);
		                    cell.setCellValue(model.getColumnName(i));
		                }      
		                // Verileri ekleme
		                for (int j = 0; j < rowCount; j++) {
		                    Row row = sheet.createRow(j + 1); // 1'e ekliyoruz çünkü başlıklar zaten 0'da
		                    for (int k = 0; k < columnCount; k++) {
		                        Cell cell = row.createCell(k);
		                        if (model.getValueAt(j, k) != null) {
		                            cell.setCellValue(model.getValueAt(j, k).toString());
		                        }
		                    }
		                }
		                
		                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
		                wb.write(out);
		                wb.close();
		                out.close();
		                
		                File path = new File(saveFile.toString());
		                Desktop.getDesktop().open(path);
		            } else {
		                JOptionPane.showMessageDialog(null, "Error");
		            }
		        } catch (FileNotFoundException exception) {
		            System.out.println(exception);
		        } catch (IOException io) {
		            System.out.println(io);
		        }
		    }
		});
		excelDonusturButton.setBounds(1332, 827, 143, 23);
		contentPane.add(excelDonusturButton);
		
		JLabel raporAdiLabel = new JLabel(getRaporText());
		raporAdiLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		raporAdiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		raporAdiLabel.setBounds(10, 21, 1564, 33);
		contentPane.add(raporAdiLabel);
		
	}

	public String getSponsorAdi() {
		return sponsorAdi;
	}

	public void setSponsorAdi(String sponsorAdi) {
		this.sponsorAdi = sponsorAdi;
	}

	public String getSponsorSoyadi() {
		return sponsorSoyadi;
	}

	public void setSponsorSoyadi(String sponsorSoyadi) {
		this.sponsorSoyadi = sponsorSoyadi;
	}

	public String getSponsorEmail() {
		return sponsorEmail;
	}

	public void setSponsorEmail(String sponsorEmail) {
		this.sponsorEmail = sponsorEmail;
	}

	public int getAylikBagis() {
		return aylikBagis;
	}

	public void setAylikBagis(int i) {
		this.aylikBagis = i;
	}

	public int getAy() {
		return ay;
	}

	public void setAy(int i) {
		this.ay = i;
	}

	public long getToplamTL() {
		return toplamTL;
	}

	public void setToplamTL(long toplamTL) {
		this.toplamTL = toplamTL;
	}

	public String getBursiyerAdi() {
		return bursiyerAdi;
	}

	public void setBursiyerAdi(String bursiyerAdi) {
		this.bursiyerAdi = bursiyerAdi;
	}

	public String getBursiyerSoyadi() {
		return bursiyerSoyadi;
	}

	public void setBursiyerSoyadi(String bursiyerSoyadi) {
		this.bursiyerSoyadi = bursiyerSoyadi;
	}

	public String getOkul() {
		return okul;
	}

	public void setOkul(String okul) {
		this.okul = okul;
	}

	public String getOkulBilgisi() {
		return okulBilgisi;
	}

	public void setOkulBilgisi(String okulBilgisi) {
		this.okulBilgisi = okulBilgisi;
	}

	public int getSinif() {
		return sinif;
	}

	public void setSinif(int i) {
		this.sinif = i;
	}

	public String getSehir() {
		return sehir;
	}

	public void setSehir(String sehir) {
		this.sehir = sehir;
	}

	public String getIbanNo() {
		return ibanNo;
	}

	public void setIbanNo(String ibanNo) {
		this.ibanNo = ibanNo;
	}

	public String getBankaAdi() {
		return bankaAdi;
	}

	public void setBankaAdi(String bankaAdi) {
		this.bankaAdi = bankaAdi;
	}

	public String getAileAdres() {
		return aileAdres;
	}

	public void setAileAdres(String aileAdres) {
		this.aileAdres = aileAdres;
	}

	public String getBursiyerEmail() {
		return bursiyerEmail;
	}

	public void setBursiyerEmail(String bursiyerEmail) {
		this.bursiyerEmail = bursiyerEmail;
	}

	public String getBursiyerCepTel() {
		return bursiyerCepTel;
	}

	public void setBursiyerCepTel(String i) {
		this.bursiyerCepTel = i;
	}

	public static int getBursiyerId() {
		return bursiyerId;
	}

	public static void setBursiyerId(int bursiyerId) {
		excelRapor.bursiyerId = bursiyerId;
	}

	public static int getSponsorId() {
		return sponsorId;
	}

	public static void setSponsorId(int sponsorId) {
		excelRapor.sponsorId = sponsorId;
	}

	public static int getSelectedRaporIndex() {
		return selectedRaporIndex;
	}

	public static void setSelectedRaporIndex(int selectedRaporIndex) {
		excelRapor.selectedRaporIndex = selectedRaporIndex;
	}

	public String getSponsorCepTel() {
		return sponsorCepTel;
	}

	public void setSponsorCepTel(String sponsorCepTel) {
		this.sponsorCepTel = sponsorCepTel;
	}

	public int getBursiyerEgitimYili() {
		return bursiyerEgitimYili;
	}

	public void setBursiyerEgitimYili(int bursiyerEgitimYili) {
		this.bursiyerEgitimYili = bursiyerEgitimYili;
	}

	public int getSponsorEgitimYili() {
		return sponsorEgitimYili;
	}

	public void setSponsorEgitimYili(int sponsorEgitimYili) {
		this.sponsorEgitimYili = sponsorEgitimYili;
	}

	public int getMatchingEgitimYili() {
		return matchingEgitimYili;
	}

	public void setMatchingEgitimYili(int matchingEgitimYili) {
		this.matchingEgitimYili = matchingEgitimYili;
	}

	public static String getRaporText() {
		return raporText;
	}

	public static void setRaporText(String raporText) {
		excelRapor.raporText = raporText;
	}


}
