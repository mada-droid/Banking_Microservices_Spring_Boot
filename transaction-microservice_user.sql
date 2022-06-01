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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(128) DEFAULT NULL,
  `last_name` varchar(128) DEFAULT NULL,
  `email` varchar(128) NOT NULL,
  `birth_date` varchar(50) DEFAULT NULL,
  `password` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (54,'madaw','cosentino','calcbtia@ewsdf.it','24/11/99','$2a$10$LlqoC1j/UZK6umcLpruPyeomMaWngEnHVk7zCtg7fX2hg1QkoWJPy'),(55,'madaw','cosentino','calcbfdtia@sdf.it','24/11/99','$2a$10$ykAe7q10/Qe.L39RYorom.oS0j65ewrzOFJZNoWaVWyYaTKcwYqh6'),(57,NULL,NULL,'mada3200@hotmail.com',NULL,'$2a$10$e1Zn9c8/UsGLTynAicklUOX9ZrGqpSWWJoC6euWGGpGxZP3MjTQAm'),(58,NULL,NULL,'cotedig770@luxiu2.com',NULL,'$2a$10$U1S6O..ajZbcgEylM5CINe.a5rY1RD4V/WsLLmos2uHmp0DwtXzQ6'),(59,'MadaLore','Matrix','V3@Basket.it','24/11/99','$2a$10$cj9VOVfN65xssoQukZ2G0eGdj1wLKlw99PfZdM8jEI8HEXHAXPV6a'),(60,'MadaLore','Matrix','V4@Basket.it','24/11/99','$2a$10$St/Y7DN77moxtSim.NfJYuM8o7bdC7kADPPWPK/xtrHKaNgu/jKCq'),(61,'MadaLore','Matrix','V5@Basket.it','24/11/99','$2a$10$v5/HGxdjMts/LZK/Hci4DeMNnsNIMBc6i8m4rqQCtYLUD3Y.psgU6'),(63,NULL,NULL,'V6@Basket.it',NULL,'$2a$10$4zZ7ximV3gKi64FS0YuU/.lZVVRPGjCS42muQwGoPA2PicafELQv6'),(64,NULL,NULL,'sadlkfh@dslfuh.it',NULL,'$2a$10$kc0S.9OeD716Lmfv6hIvieKyDAi.YJSrLAKUiYckoe89.4m530uoq'),(65,'MadaLore','Matrix','sakfhiew2@dslfuh.it','24/11/99','$2a$10$klt9hYuzlt4E5shPvEmuHu1VTOsb4vMRRdpeungHlnpLE4g1h4Lz2'),(67,NULL,NULL,'panaka2260@httptuan.com',NULL,'$2a$10$myVuL4/LeXL4Adgg0CMz.uH4soC37ZghJYD/1JnKIRS0ua5m/.7de'),(68,NULL,'Matrix','Liverpoolw2@dslfuh.it','24/11/99','$2a$10$mIEPcu2r38JfMP0mY2Rvnevg1eBCpSRSkH.3HGNqdQegxyc1FYTIm'),(69,'MadaLore','Matrix','Juve@Calcio.it','24/11/99','$2a$10$L7sFcIc0g4sNwCSsWigeGOpNRI/rEd505WQln9vp7Et0GTyH4TGFm'),(70,'MadaLore','Matrix','Roma@Calcio.it','24/11/99','$2a$10$1v60KoawvZUZkW3UiIhapOgQvuli4IFp9ZBcdNIHBtmUktOzsadaK'),(71,'MadaLore','Matrix','Bologna@Calcio.it','24/11/99','$2a$10$ljy/o2nx.KO/3dTHygvE7uGm7wNGnzBYJpQutLOMz8pw6LAunD4a.');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-01 10:44:16
