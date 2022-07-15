-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: login-registration-microservice
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
  `first_name` varchar(64) NOT NULL,
  `last_name` varchar(64) NOT NULL,
  `email` varchar(64) NOT NULL,
  `birth_date` date NOT NULL,
  `password` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=344 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (324,'Mada','Lore','ProvaAutenticazione@50.com','1921-08-15','$2a$10$HAo9/vQTuQrw6FrENu.qKe9NgRpF4lc36xZaO3QzniexaXLMkRm6a'),(325,'Mada','Lore','ProvaAutenticazione@29.com','1921-08-20','$2a$10$MM76YGka0tZeT7j6zZEB2eB7lmRDoJkJAIurKyOanUK94zVR9ePGS'),(326,'Omar','Osama','ProvaAutenticazione@51.com','2003-12-20','$2a$10$t14d/QJtDRWOZL9nUhHPS.0TNre5q/3O9s2upzBMRGuMaxxPLZ.6C'),(329,'uno','nuovo','ProvaAutenticazione@52.com','2000-01-01','$2a$10$40B929I3aOY9tt3Yjz5.W.MjiAeu8SWF7zXPiPS3x3RRzYGIP70CO'),(330,'Mada','Lore','ProvaAutenticazione@53.com','1921-08-20','$2a$10$5ACGpY1dwD6p/s1TgQDY3.RXFqALrE8CTAS75nJqlKresgevz1IyG'),(331,'Sayed Abdelgawad Marzouk','Aly','ProvaAutenticazione@30.com','2000-02-10','$2a$10$hkd8RZcMuN70EtdfEJajVu8eqpvq2kIpS4.S4btoOimXQsXPKugJO'),(332,'Dybala','Diego','ProvaAutenticazione@31.com','2000-02-11','$2a$10$yC1DjqgTkq.pFSGqHMJ8j.qBBcUqnpeQVJrs.8sSMjMP/JkV1nrqy'),(333,'Mada','Prova','ProvaAutenticazione@32.com','2001-02-09','$2a$10$HLqOK9BA8sZg4XeVWrzSJ.5feBdlShRouMmequqfYmAeYh1tPLN/W'),(335,'Jerome','Mada','ProvaAutenticazione@313.com','2000-12-12','$2a$10$eiv.ixZZcyymnw9LF0MJ2OQpmaRvJR0YDTz7uaGfHr./P33OMonti'),(336,'dfsgdgf','adsfdasfa','ProvaAutenticazione@55.com','2002-07-19','$2a$10$lIcvQidJJyHT.imAe9go9u0apipyaTOg3EBvQFTzDMJ3rKfwu9K/m'),(339,'Mafa','Moan','ProvaAutenticazione@556.com','1997-04-05','$2a$10$bY0jbomneLNH5ImxMmsnsul3PDrHFuvekuUPpce8z9hFDXn64210C'),(340,'Nuovo Per','Davvero','ProvaAutenticazione@5123.com','1999-12-21','$2a$10$.vAct69jjv6eTnQxtOldoOpvW3I4Q6ZMacYLQdFRJStitr8iuJNjS'),(342,'Mada','Giorno Pres','ProvaAutenticazione@58.com','1996-08-20','$2a$10$P8O6jawkDFdhwjHPs3kod.WgDXWYiYprKR2Cu0bmRvZGah2Xl8O12'),(343,'Cri','Lore','ProvaAutenticazione@60.com','1996-04-05','$2a$10$i6TQxX/bcpK0Z13G5PWSe.jTl02BOZn3qx1bZtDr.uaPAepwG9//2');
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

-- Dump completed on 2022-07-15 15:04:44
