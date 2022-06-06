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
  `first_name` varchar(128) NOT NULL,
  `last_name` varchar(128) NOT NULL,
  `email` varchar(128) NOT NULL,
  `birth_date` varchar(50) NOT NULL,
  `password` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=279 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (65,'madaw','cosentino','calcbtia@ewsdf.it','24/11/99','$2a$10$gycBE9V9221og33uKYKGcOdDCIvdJnqLUBMCoexS9sYEmJ71YOH0m'),(66,'madaw','cosentino','calcbfdtia@sdf.it','24/11/99','$2a$10$wLLRNxnwRFCEvJ.H1uXKduScDv/2HAumSw8qPPBcPlM2oOsnuEk.u'),(67,'MadaLore','Matrix','V3@Basket.it','24/11/99','$2a$10$oGR2oYJ2xNOAJo7iOaNjeObB4CrOGIZ5PQ1tyi1nF1MlvCF/LP/py'),(68,'MadaLore','Matrix','V4@Basket.it','24/11/99','$2a$10$JpmoOjwKD7rCTCr2o730/eW44K6l5BKIgcHT6I8L.0LO.t22wNgW2'),(69,'MadaLore','Matrix','V5@Basket.it','24/11/99','$2a$10$MuZ1TNf4KcTy8y3h7lvlt.YMDHvds5F9Lq3LGekof1TcS5Exd/rXm'),(70,'MadaLore','Matrix','sakfhiew2@dslfuh.it','24/11/99','$2a$10$jgIvi9HpsIiCzxEMnu/Lx.iebaP7zpjCokt4.9cdZcQltC6yNVXi.'),(71,'MadaLore','Matrix','Juve@Calcio.it','24/11/99','$2a$10$rDH5TB4QWGCOXtPheXzYiuRbwO9Lx3s7cjkZk89KEBO.umuYOPzjC'),(72,'MadaLore','Matrix','Roma@Calcio.it','24/11/99','$2a$10$CgtxxvSo2ZLM.D5NvkHMneJxk2FWFJnR8RrUQgguI9RuroTxl/Ukm'),(73,'MadaLore','Matrix','Bologna@Calcio.it','24/11/99','$2a$10$2vPRpGjRKEZvgXLI5wfJROaH6MDt4tyeC2y4CWcl1oaGq0Zz4wzx6'),(74,'MadaLore','Matrix','Bologna1@Calcio.it','24/11/99','$2a$10$qOad8LHhhrAPUy1LtP7ak.kM8IRRVUI24wFQGVFzCEj7mCrE.5gR2'),(75,'marco','rossi','gianmimmo@verdi.it','15/02/1995','$2a$10$DaG9X6hfJHGzBvBmc5USKONwdDBqw0RLFhpVR2R6m6sC23xKg/4Rm'),(76,'marco','rossi','LoreMarvel@verdi.it','15/02/1995','$2a$10$TDxOg6ogoQck/xAHQ6yGuODSGvinkVMQiB5frh0wq7L5Bwz.IOFL6'),(77,'last','first','Bologna1234sdd@Calcio.it','16/05/65','$2a$10$ogNibcV3itV1vBfnYkXId.Gj3UQ.UiT/AmCmkiwk9/xVgVIzRSuWW'),(78,'marco','rossi','LoreMarvel64kjn@verdi.it','15/02/1995','$2a$10$iPNAfw20MM0jtnsLQyIOrOAn/0BhlJIkLGsKdkW/QNKA4wJ7a.YBS'),(79,'madaw','cosentino','provaTokenRegister@sdf.it','24/11/99','$2a$10$krcaPrIFkcv4H0CBD.niH.IDm4tOKWsXCJEwy6OA6eJwZN1I4EPYe'),(80,'madaw','cosentino','provaTokenRegisterV1@sdf.it','24/11/99','$2a$10$QSCnojSxoaUTz8PpZm6uFuoGinR5aW7jA9BNod5vrO7fA1xk7BqWe'),(81,'Mada','cosentino','calcbtiasdf3231@ewef2wsdf.it','24/11/99','$2a$10$UUuRtcGV1BeD1ZfPevRfeeT0lmEUPSv8qRLab/r2BcrLzH1RvyumS'),(82,'Mada','cosentino','Italia_VS_Argentina@oggi.it','24/11/99','$2a$10$9oCsi9Lygylrq3s5lH4zVeYSMJz1tJph8SlMKaM72CxIrshQkw3kq'),(83,'Mada','cosentino','Nadal_VS_Djoko@Ieri.RG','24/11/99','$2a$10$MkTq47CPQA3y61oTFkDvvub72At3pZtwlCg./FmTrIreRPkjU9IX.'),(84,'Mada','cosentino','Nadal_VS1_Djoko@Ieri.RG','24/11/99','$2a$10$R9hXg.bOwd/xoGdslSdd4eWDvFoscHRy.UOuGzLE378dDMdAQjqHm'),(85,'Mada','cosentino','Nadal_VS3_Djoko@Ieri.RG','24/11/99','$2a$10$mIY25ZdSVbMaC7LDElTTJO53KaGhX7vQPD/35kFCC2YFIGH7P5VTK'),(86,'Mada','yoV4','NonFunzionaV2@2.com','24/11/99','$2a$10$d.T4gyiQDNblEAgwP3CNce6r0f9d9tw0KlBv.rbrvenR5t0tnBjlm'),(87,'Mada','yoV5','passwordNonEraCriptata@2.com','24/11/99','$2a$10$xGNkTsuIRLjMbH8AMdeS9uCOvBQ5ddz9faHHOMHQ7eNq25qLO70FC'),(88,'Mada','yoV6','OraTuttofunzionaa@2.com','24/11/99','$2a$10$mkAv7SHREqeYuKouHjS8IOagaUqyyci/aKXwnwK6XNilfES3q1Hru'),(259,'Mada','yoV9','ProvaAutenticazione@9.com','24/11/99','$2a$10$BO7.eI2QBDjdslGMErzH8utMOoOsC38XfcXR0OScHtoaq0i.oZQpu'),(261,'Mada','yoV10','ProvaAutenticazione@10.com','24/11/99','$2a$10$h86xWrOQz9NMYVABmrCAieC2IJWY/L9M.7aMyhaMIMkkzigP3bj4O'),(263,'Mada','yoV11','ProvaAutenticazione@11.com','24/11/99','$2a$10$4qt4hMzaDlIXRa1nGcdDwege7UVT07SR4owRl8wAl4Cy75Cfdswt.'),(265,'Mada','yoV12','ProvaAutenticazione@12.com','24/11/99','$2a$10$4/eLv4I6vUkLGk6qpEE0EOPKO/mGR65J11QkosgMvYLIrNF9.oFp.'),(266,'Mada','yoV13','ProvaAutenticazione@13.com','24/11/99','$2a$10$ZNcFApawozyMbiDpYIIPOel5DTQnm2riT2kzdhu3HwQ28I6oQ0DSi'),(267,'Mada','yoV14','ProvaAutenticazione@14.com','24/11/99','$2a$10$8QWF85T1e475qxKES6FWae7SobgpG37qE18OIpY6v8sudKRKFKZNy'),(269,'Mada','yoV15','ProvaAutenticazione@15.com','24/11/99','$2a$10$dhQNjhQei1sFWc42TrYHQuu12nbXK5N.eVOkpKDzefz6/XSWyp1g2'),(271,'Mada','yoV16','ProvaAutenticazione@16.com','24/11/99','$2a$10$TAuoqhkwwZE.aRp0rm0n/OrUhgkAAN0Uz.gg9p2RIsxxn/SiL8c4m'),(274,'Mada','yoV18','ProvaAutenticazione@18.com','24/11/99','$2a$10$rya6Mlqn2JuW8bQ0n3doPOQF4BsTYTWd2hZCtSNk.k7zP.F9g7i/W'),(275,'Mada','yoV19','ProvaAutenticazione@19.com','24/11/99','$2a$10$kXJ.8u9XYn00od81jPK5feqv3GNLk/CTTIck3efOwqHpEb3iBU1cm'),(276,'Mada','yoV20','ProvaAutenticazione@20.com','24/11/99','$2a$10$diEG7juCeRNPjiCbV4ZNSeibhXqGhLr02N1sNRZjivVVrm2VvBApu'),(277,'Mada','yoV21','ProvaAutenticazione@21.com','24/11/99','$2a$10$RLsEQTq4jyOmcFx.9YXE/OxcUR5nQ4oWIVOgZHhtu9EzV/jg53fdK');
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

-- Dump completed on 2022-06-06 11:32:39
