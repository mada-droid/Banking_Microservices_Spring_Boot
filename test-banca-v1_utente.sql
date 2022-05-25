-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: test-banca-v1
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
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(128) NOT NULL,
  `cognome` varchar(128) NOT NULL,
  `email` varchar(128) NOT NULL,
  `data_di_nascita` varchar(50) NOT NULL,
  `username` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (1,'Lorenzo','Rossi','lorenzo@luv2code.it','14/08/94','lore123','1234'),(2,'Mohamed','Mada','mada@tennis.com','20/08/96','mada123','1234'),(19,'Mario','Grosso','mario@micro.com','03/05/65','mariojum1234p','1234'),(20,'Mario','Grosso','mario@micro.com','03/05/65','mariojum12345p','1234'),(23,'Francesco','Amdel','fran.am@ds.com','10/04/89','franco76','$2a$10$AGqaSOiNfdNAW6siSf7Q2OHB/WOfTnNUF0ISQk09HXUOBsNTOFau.'),(25,'Juan','Carlos','gogo.rc@ads.com','10/04/98','JuanC22','$2a$10$oaY2OXeWQ8oslyc1xNh1fOKTT421ZqQWbWaruC.6t/2a34dBMo4ie'),(26,'Andrea','Ronco','Andres.rong@gmail.it','04/08/47','Juan23C22','$2a$10$mV5GmHkHpAlbblliQhPRiOasV6u0MX7uY.1NvS1U1k0sXBtxx/DL6'),(27,'Nona','Falduto','Noemi@luvself.it','24/11/99','NonaDi23','$2a$10$JNGn.w/GEGh4e4EOjKVWgeOF/zBiVIrlQvg/qY7YfvGc/wO2IPok6'),(28,'Nonfdgha','sadkfh','prova@luvself.it','24/11/99','NownaDi23','$2a$10$mR0Lh3T7ZeI5e6vOADgIMuQb6E.dX6gjFmOumBISUePhC5nRR8w9W'),(29,'ada','cosentino','calcbtia@sdf.it','24/11/99','dweqadacosenza','$2a$10$XaorLuhIw92yNriejPrfR.sJIOcyFN.7OWBmluLKyUA.fOwggcIs.'),(30,'ada','cosentino','calcbtia@sdf.it','24/11/99','prova23','$2a$10$mfuWgordJWM9K5DWehrCXuRYXFnjOtrMJ8NZbzl.wdDSht6urn3QS'),(31,'ada','cosentino','calcbtia@sdf.it','24/11/99','prova24','$2a$10$GPwonWhkaVfpoqO9DdGafezenylYNuHDYswnAkE6S4FNCyoV5E9sO');
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-25 14:47:11
