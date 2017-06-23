CREATE DATABASE  IF NOT EXISTS `chatApplication` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `chatApplication`;
-- MySQL dump 10.13  Distrib 5.5.55, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: chatApplication
-- ------------------------------------------------------
-- Server version	5.5.55-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chat`
--

DROP TABLE IF EXISTS `chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) NOT NULL,
  `timestamp` datetime DEFAULT NULL,
  `author_user_id` int(11) NOT NULL,
  `recipient_user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc1nigrmy6q7cbwh5n7w37k4fm` (`author_user_id`),
  KEY `FKrtpurm4m1032at2p93rco8hgr` (`recipient_user_id`),
  CONSTRAINT `FKrtpurm4m1032at2p93rco8hgr` FOREIGN KEY (`recipient_user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKc1nigrmy6q7cbwh5n7w37k4fm` FOREIGN KEY (`author_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat`
--

LOCK TABLES `chat` WRITE;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
INSERT INTO `chat` VALUES (1,'hello there Jason, how are you','2017-06-22 00:00:00',1,2),(2,'hello there Jim, how are you','2017-06-22 00:00:00',1,3),(3,'I sense disturbance with the force.','2017-06-22 00:00:00',1,4),(4,'Do or die.','2017-06-22 00:00:00',1,5),(5,'Dinner tonight?','2017-06-22 00:00:00',2,1),(6,'Dinner tonight?','2017-06-22 00:00:00',2,3),(7,'Dinner tonight?','2017-06-22 00:00:00',2,4),(8,'Luke, I am your father!!!','2017-06-22 00:00:00',4,4),(9,'Luke, I am your father!!!','2017-06-22 00:00:00',4,1),(10,'Luke, I am your father!!!','2017-06-22 00:00:00',4,3),(11,'Luke, I am your father!!!','2017-06-22 00:00:00',4,6);
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'john@smith.edu','john','smith','$2a$10$Yi0PtDyyIV7WvqujsRjhu.yNQagrfYptz1baJsgxd6krXgObGvLdS'),(2,'jason@ncsu.edu','jason','bourne','$2a$10$ln7NDREgssbNEuU2aG/FQObZ4EGieKjN5.V5DPSgmggjhLpRZ0ZIy'),(3,'ace@ventura.edu','ace','ventura','$2a$10$5e3qJFpSvd0H2ARg7ESut.LNZ.oybZgouTlETndjrjI8bcjwJg62i'),(4,'darth@vader.edu','darth','vader','$2a$10$rQ/XmSO5ZXhxZr8uTYskBOcYwcXRhIQmn7dGhBpPykxVsTglEuGOW'),(5,'rocky@balboa.edu','rocky','balboa','$2a$10$irIxD20xwLwbrLb8nzV.SuJih7//wOPOHuFA0/9VJ4wEXHXxlU3z2'),(6,'king@kong.edu','king','kong','$2a$10$kvm0AD2baWoNHHye6TBBzObAPU4ckymGQ4nNNHMIdR2tDbTAk6zJ6');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-22 23:30:04
