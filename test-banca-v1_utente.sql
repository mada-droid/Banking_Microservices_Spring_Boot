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
  `first_name` varchar(128) NOT NULL,
  `last_name` varchar(128) NOT NULL,
  `email` varchar(128) NOT NULL,
  `birth_date` varchar(50) NOT NULL,
  `password` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (65,'madaw','cosentino','calcbtia@ewsdf.it','24/11/99','$2a$10$gycBE9V9221og33uKYKGcOdDCIvdJnqLUBMCoexS9sYEmJ71YOH0m'),(66,'madaw','cosentino','calcbfdtia@sdf.it','24/11/99','$2a$10$wLLRNxnwRFCEvJ.H1uXKduScDv/2HAumSw8qPPBcPlM2oOsnuEk.u'),(67,'MadaLore','Matrix','V3@Basket.it','24/11/99','$2a$10$oGR2oYJ2xNOAJo7iOaNjeObB4CrOGIZ5PQ1tyi1nF1MlvCF/LP/py'),(68,'MadaLore','Matrix','V4@Basket.it','24/11/99','$2a$10$JpmoOjwKD7rCTCr2o730/eW44K6l5BKIgcHT6I8L.0LO.t22wNgW2'),(69,'MadaLore','Matrix','V5@Basket.it','24/11/99','$2a$10$MuZ1TNf4KcTy8y3h7lvlt.YMDHvds5F9Lq3LGekof1TcS5Exd/rXm'),(70,'MadaLore','Matrix','sakfhiew2@dslfuh.it','24/11/99','$2a$10$jgIvi9HpsIiCzxEMnu/Lx.iebaP7zpjCokt4.9cdZcQltC6yNVXi.'),(71,'MadaLore','Matrix','Juve@Calcio.it','24/11/99','$2a$10$rDH5TB4QWGCOXtPheXzYiuRbwO9Lx3s7cjkZk89KEBO.umuYOPzjC'),(72,'MadaLore','Matrix','Roma@Calcio.it','24/11/99','$2a$10$CgtxxvSo2ZLM.D5NvkHMneJxk2FWFJnR8RrUQgguI9RuroTxl/Ukm'),(73,'MadaLore','Matrix','Bologna@Calcio.it','24/11/99','$2a$10$2vPRpGjRKEZvgXLI5wfJROaH6MDt4tyeC2y4CWcl1oaGq0Zz4wzx6'),(74,'MadaLore','Matrix','Bologna1@Calcio.it','24/11/99','$2a$10$qOad8LHhhrAPUy1LtP7ak.kM8IRRVUI24wFQGVFzCEj7mCrE.5gR2'),(75,'marco','rossi','gianmimmo@verdi.it','15/02/1995','$2a$10$DaG9X6hfJHGzBvBmc5USKONwdDBqw0RLFhpVR2R6m6sC23xKg/4Rm'),(76,'marco','rossi','LoreMarvel@verdi.it','15/02/1995','$2a$10$TDxOg6ogoQck/xAHQ6yGuODSGvinkVMQiB5frh0wq7L5Bwz.IOFL6'),(77,'last','first','Bologna1234sdd@Calcio.it','16/05/65','$2a$10$ogNibcV3itV1vBfnYkXId.Gj3UQ.UiT/AmCmkiwk9/xVgVIzRSuWW');
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

-- Dump completed on 2022-05-31 15:10:38
