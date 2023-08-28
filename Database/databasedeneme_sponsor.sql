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
-- Table structure for table `sponsor`
--

DROP TABLE IF EXISTS `sponsor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sponsor` (
  `SponsorAdi` varchar(30) DEFAULT NULL,
  `SponsorSoyadi` varchar(30) DEFAULT NULL,
  `Sponsorid` int NOT NULL AUTO_INCREMENT,
  `SponsorMail` varchar(50) DEFAULT NULL,
  `SponsorCepTel` bigint DEFAULT NULL,
  `EgitimYili` int DEFAULT NULL,
  `aciklama` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`Sponsorid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sponsor`
--

LOCK TABLES `sponsor` WRITE;
/*!40000 ALTER TABLE `sponsor` DISABLE KEYS */;
INSERT INTO `sponsor` VALUES ('Aselsan','makine',1,'bmail',1111,2022,'null'),('Microsoft','xbox',2,'microsoft@gmail',5075894389,2020,'mallar'),('Rahmi','Koç',4,'koçgmail',5055055555,2023,'çok çokk zengin'),('Ahmet','Kral',5,'akral@gmail.com',5094352342,1976,''),('Ali Baba','dasdasda',11,'sdadas',323,432,''),('Ali Yıldırım','Koç',13,'akoc@gmail.com',3232,2023,'dsaldğpsaldğasp'),('Ali Yıldırım','Ekici',14,'akoc@koc.edu.tr',5555433,2025,'param cok'),('Algida Sanayi Dond','Buz derin dondurucu',16,'algida@gmail.com',111111,2023,'doakdsağkdağodka'),('CEM BARIŞ','Keskin',17,'cbkeskin@akbank.com',5526985785,2028,'Yardım etmek istiyor.'),('GARANTİ','BANKASI',18,'garanti',44431100,2027,'garanti bankası yardim');
/*!40000 ALTER TABLE `sponsor` ENABLE KEYS */;
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
