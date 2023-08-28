-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: databasedeneme
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bursiyer`
--

DROP TABLE IF EXISTS `bursiyer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bursiyer` (
  `BursiyerAdi` varchar(30) DEFAULT NULL,
  `BursiyerSoyadi` varchar(30) DEFAULT NULL,
  `Bursiyerid` int NOT NULL AUTO_INCREMENT,
  `OkulTuru` varchar(14) DEFAULT NULL,
  `OkulAdi` varchar(50) DEFAULT NULL,
  `Sinif` int DEFAULT NULL,
  `sehir` varchar(20) DEFAULT NULL,
  `IBAN` varchar(26) DEFAULT NULL,
  `BankaAdi` varchar(30) DEFAULT NULL,
  `AileAdresi` varchar(75) DEFAULT NULL,
  `BursiyerMail` varchar(50) DEFAULT NULL,
  `BursiyerCepTel` bigint DEFAULT NULL,
  `EgitimYili` int DEFAULT NULL,
  `aciklama` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`Bursiyerid`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bursiyer`
--

LOCK TABLES `bursiyer` WRITE;
/*!40000 ALTER TABLE `bursiyer` DISABLE KEYS */;
INSERT INTO `bursiyer` VALUES ('ali','sencer',4,'hfgfgf','gfgf',656,'gfgf','rtrt','trtr','trtr','yhthyt',6565,656,'trtr'),('esin','hoca',5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('Tacha','Serif',6,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('mehmet ','yılmaz',7,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('zeynep','yildiz',8,'dsfsdf','sdfsfsd',454,'sdfds','ffdsfdsf','sdfsd','fsdfsdf','dsadsa',423232,545,''),('burcu','selçuk',9,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('','Tuğba',10,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('Tuğba','Gunes',11,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('Cem','Yilmaz',12,'Teknik','ODTU',3,'Ankara',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('hatice','yıldız',14,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('hatice','Yildiz',15,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('ahmet',NULL,16,NULL,NULL,NULL,NULL,'123123123123',NULL,NULL,NULL,5075894389,NULL,NULL),('Doruk','Kurgan',17,'Devlet','Ege',3,'Izmir','123123123123123123123','Akbank','Izmir, Bornova','dorukkurgan@gmail.com',5055275199,2023,''),('Ali','dsada',18,'vakif','dasda',3,'dsaqda','dasdsa','dqds','dqdwq','dsadsa',32323233,3232,'dsadsadas'),('Ali Sencer','Irmak',19,'Lisans','Yeditepe Üniversitesi',3,'İstanbul','TR2600060220202151','Garanti Bankası','sadasokdaspodkaspdoask fdıqkıoeqjeoıf','alisencer.irmak@std.yeditepe.edu.tr',5524193490,2023,'ıdqjkdıqwjdkwqpdkqwpdwq'),('ali sencer','sadsa',20,'dsda','dasda',454,'dsadsa','dsa5da45sda','dsadsa','dsadsada','dsadsa',54854,545,'dsadsa'),('Mehmet Akif','Ersoy',23,'Lisans','İstanbul Teknlik Üni',4,'İstanbulş','TR545664651654165465465','Garanti BBVA','Türkiye','mersoy@outlook.com',5322562565,2023,'SA AS NABER'),('Ali Sencer','Irmak',24,'Vakıf','Yeditepe Üniversitesi',3,'İstanbul','TR54545457475545684','QNB Finansbank','İstasyon, Tuzla','alisencer.irmak@std.yeditepe.edu.tr',5524193490,2023,'Burs talebi'),('Oğuzhan Mert','Akgül',25,'Lisan','fıewjfow',3,'foıewjfow','feoıwjfoıw','feoıwjfewoı','fewoıjfw','ıfojfoıew',483984,43,''),('Mehmet Batuhan','Poyraz',27,'Lisans','ODTÜ',3,'Ankara Yenimahalle','DE3200504303923932392','Deutsche Bank','Ankara/Yenimahalle bina no 152','mbpoyraz23@outlook.com',53225,2012,'Okul ve yurt ihtiyaçları için burs talebi.'),('Ahmet Harun','Kılıç',28,'Ön Lisans','itü',1,'istanbul','TR434343123121342','Ziraat Bankası A.Ş.','İstanbul Şişli','ahkilic@outlook.com',5326987548,2027,'İTÜ YURT VE YEMEK YARDIMI TALEBİ\n');
/*!40000 ALTER TABLE `bursiyer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-20 17:59:33
