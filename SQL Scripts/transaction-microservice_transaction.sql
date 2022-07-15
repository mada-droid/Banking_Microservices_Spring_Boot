-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: transaction-microservice
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `operation_type` varchar(45) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `operation_date` date DEFAULT NULL,
  `account_id` int NOT NULL,
  `causal` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=187 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (114,'PRELIEVO',-11.11,'2022-06-27',129,'Prelievo online'),(115,'PRELIEVO',-11.12,'2022-06-27',129,'Prelievo online'),(116,'PRELIEVO',-11.13,'2022-06-27',129,'Prelievo online'),(117,'PRELIEVO',-2.91,'2022-06-27',129,'Prelievo online'),(118,'PRELIEVO',-9,'2022-06-27',129,'Prelievo online'),(119,'PRELIEVO',-1,'2022-06-27',129,'Prelievo online'),(120,'PRELIEVO',-1,'2022-06-27',129,'Prelievo online'),(121,'PRELIEVO',-12.33,'2022-06-27',129,'Prelievo online'),(122,'PRELIEVO',-0.66,'2022-06-27',129,'Prelievo online'),(123,'PRELIEVO',-86.01,'2022-06-27',129,'Prelievo online'),(124,'PRELIEVO',-100,'2022-06-27',129,'Prelievo online'),(125,'PRELIEVO',-2.35,'2022-06-28',129,'Prelievo online'),(126,'BONIFICO',-0.5,'2022-06-28',129,'provaTransferDiVia'),(127,'BONIFICO',0.5,'2022-06-28',132,'provaTransferDiVia'),(128,'BONIFICO',-0.5,'2022-06-28',129,'provaTransferDiVia'),(129,'BONIFICO',0.5,'2022-06-28',132,'provaTransferDiVia'),(130,'BONIFICO',-0.45,'2022-06-28',129,'provaTransferDiVia'),(131,'BONIFICO',0.45,'2022-06-28',132,'provaTransferDiVia'),(132,'BONIFICO',-3,'2022-06-28',132,'provaTransferDiVia'),(133,'BONIFICO',3,'2022-06-28',129,'provaTransferDiVia'),(134,'BONIFICO',-10,'2022-06-28',129,'Bonifico all\'iban: IT61A8626435863737211525386'),(135,'BONIFICO',10,'2022-06-28',132,'Bonifico all\'iban: IT61A8626435863737211525386'),(136,'PRELIEVO',-87.7,'2022-06-28',129,'Prelievo online'),(137,'BONIFICO',-10,'2022-06-28',129,'Bonifico all\'iban: IT61A8626435863737211525386'),(138,'BONIFICO',10,'2022-06-28',132,'Bonifico all\'iban: IT61A8626435863737211525386'),(139,'BONIFICO',-0.01,'2022-06-28',132,'Bonifico all\'iban: IT86T1478187548462846675375'),(140,'BONIFICO',0.01,'2022-06-28',129,'Bonifico all\'iban: IT86T1478187548462846675375'),(141,'PRELIEVO',-1,'2022-06-28',132,'Prelievo online'),(142,'PRELIEVO',-1,'2022-06-28',132,'Prelievo online'),(143,'PRELIEVO',-10,'2022-06-28',132,'Prelievo online'),(144,'BONIFICO',-3,'2022-06-29',132,'provaTransferDiVia'),(145,'BONIFICO',3,'2022-06-29',129,'provaTransferDiVia'),(146,'BONIFICO',-25.72,'2022-06-29',131,'provaTransferDiVia'),(147,'BONIFICO',25.72,'2022-06-29',132,'provaTransferDiVia'),(148,'DEPOSITO',25,'2022-06-29',132,'ProvaDel29'),(149,'PRELIEVO',-60,'2022-06-29',132,'ProvaDel29'),(150,'DEPOSITO',60,'2022-06-29',132,'ProvaDel29'),(151,'DEPOSITO',45,'2022-06-29',132,'ProvaDel29'),(152,'PRELIEVO',-30,'2022-06-29',132,'ProvaDel29'),(153,'PRELIEVO',-0.71,'2022-06-29',132,'ProvaDel29'),(154,'DEPOSITO',30,'2022-06-29',132,'Deposito online'),(155,'BONIFICO',-7,'2022-06-29',132,'Bonifico da: Mada Lore a: demon Slayer'),(156,'BONIFICO',7,'2022-06-29',129,'Bonifico da: Mada Lore a: demon Slayer'),(157,'DEPOSITO',94,'2022-06-29',132,'Deposito online'),(158,'RICARICA_TELEFONICA',-87,'2022-06-29',132,'Ricarica telefonica a: 3277718964'),(159,'RICARICA_TELEFONICA',-100,'2022-06-29',132,'ProvaDel29Ricarica-Telefonica'),(160,'DEPOSITO',100,'2022-06-29',132,'Deposito online'),(161,'DEPOSITO',100,'2022-06-29',132,'Deposito online'),(162,'BONIFICO',-100,'2022-06-29',132,'Bonifico da: Mada Lore a: demon slayer'),(163,'BONIFICO',100,'2022-06-29',131,'Bonifico da: Mada Lore a: demon slayer'),(164,'BONIFICO',-25,'2022-06-29',132,'provaTransferDiVia'),(165,'BONIFICO',25,'2022-06-29',131,'provaTransferDiVia'),(166,'BONIFICO',-15,'2022-06-29',132,'Bonifico da: Mada Lore a: demon Ali'),(167,'BONIFICO',15,'2022-06-29',129,'Bonifico da: Mada Lore a: demon Ali'),(168,'RICARICA_TELEFONICA',-10,'2022-06-29',132,'Ricarica telefonica a: 3277718964'),(169,'RICARICA_TELEFONICA',-5,'2022-06-29',132,'Ricarica telefonica a: 3277718964'),(170,'RICARICA_TELEFONICA',5,'2022-06-29',132,'Ricarica telefonica a: 3277718964'),(171,'DEPOSITO',20,'2022-06-29',132,'Prelievo online'),(172,'BONIFICO',-15,'2022-06-29',132,'Bonifico da: Mada Lore a: Mohamed Sayed Abdelgawwad Mada'),(173,'BONIFICO',15,'2022-06-29',131,'Bonifico da: Mada Lore a: Mohamed Sayed Abdelgawwad Mada'),(174,'BONIFICO',-40,'2022-06-29',132,'Bonifico da: Mada Lore a: demon Mada'),(175,'BONIFICO',40,'2022-06-29',131,'Bonifico da: Mada Lore a: demon Mada'),(176,'DEPOSITO',95,'2022-06-29',132,'Deposito online'),(177,'BONIFICO',-20,'2022-06-29',132,'Bonifico da: Mada Lore a: Mohamed Sayed Abdelgawwad Marzouk'),(178,'BONIFICO',20,'2022-06-29',131,'Bonifico da: Mada Lore a: Mohamed Sayed Abdelgawwad Marzouk'),(179,'PRELIEVO',-1,'2022-06-29',132,'Prelievo online'),(180,'DEPOSITO',10,'2022-06-30',132,'Deposito online'),(181,'DEPOSITO',1,'2022-06-30',132,'Deposito online'),(182,'DEPOSITO',2,'2022-06-30',132,'Deposito online'),(183,'DEPOSITO',100,'2022-06-30',138,'Deposito online'),(184,'RICARICA_TELEFONICA',-15,'2022-07-06',129,'Ricarica telefonica a: 3277718964'),(185,'DEPOSITO',12,'2022-07-07',140,'Deposito online'),(186,'DEPOSITO',1,'2022-07-13',129,'Deposito online');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-15 15:04:43
