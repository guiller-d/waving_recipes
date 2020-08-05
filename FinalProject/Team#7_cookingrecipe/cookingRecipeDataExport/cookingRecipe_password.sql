-- MySQL dump 10.13  Distrib 8.0.20, for macos10.15 (x86_64)
--
-- Host: localhost    Database: cookingRecipe
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `password`
--

DROP TABLE IF EXISTS `password`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `password` (
  `account_id` int(11) NOT NULL,
  `password` varchar(32) NOT NULL,
  `hash_password` varchar(64) NOT NULL,
  `last_update` date DEFAULT NULL,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `password`
--

LOCK TABLES `password` WRITE;
/*!40000 ALTER TABLE `password` DISABLE KEYS */;
INSERT INTO `password` VALUES (1,'John Cena','mandjemandjeldign3m5nldign3m5n','2006-02-14'),(2,'John Smith','adfm19fnc5kgfbadfm19fnc5kgfb5n','2011-02-14'),(3,'Alex Arizona','adjfdkdajadfm19fnc5kgfbldf25as','2012-02-14'),(4,'Cali Smith','a3kasdf45wadfm19fnc5kgfbdfajdf','2012-02-14'),(5,'Karen Cena','a3kasdf45wadfm19fnc5kgfbdfajdf','2012-02-14'),(6,'Demi Gonzales','jdie93jdie93utndndutndndasdwfs','2012-02-14'),(7,'Belarus King','d1nfhddjdie93utndndj45mdneasde','2012-02-14'),(8,'Sarah Jen','aeif3i8jdie93utndnddhndsdeavks','2012-02-14'),(9,'Damian Richardsa','aeif3i8jdie93ufgdsgdhndsfeavks','2012-02-14'),(10,'Bob Junior','aeif3i8jdie9sufgdsgdhndsfeaasc','2012-02-14');
/*!40000 ALTER TABLE `password` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-02 19:44:11
