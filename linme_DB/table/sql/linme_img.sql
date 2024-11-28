-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: linme
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `img`
--

DROP TABLE IF EXISTS `img`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `img` (
  `img_id` int NOT NULL AUTO_INCREMENT COMMENT '이미지 일련번호',
  `product_id` int NOT NULL,
  `img` varchar(255) NOT NULL COMMENT '이미지',
  `d_img` varchar(255) NOT NULL COMMENT '상세 이미지',
  `reg_date` datetime NOT NULL COMMENT '등록일시',
  `edit_date` datetime NOT NULL COMMENT '변경일시',
  PRIMARY KEY (`img_id`),
  KEY `fk_img_product1_idx` (`product_id`),
  CONSTRAINT `fk_img_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='이미지';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `img`
--

LOCK TABLES `img` WRITE;
/*!40000 ALTER TABLE `img` DISABLE KEYS */;
INSERT INTO `img` VALUES (1,1,'/product/vitamin/vitamin1.jpg','/product/vitamin/d_vitamin1.jpg','2024-11-28 20:32:17','2024-11-28 20:41:30'),(2,2,'/product/vitamin/vitamin2.jpg','/product/vitamin/d_vitamin2.jpg','2024-11-28 20:32:46','2024-11-28 20:32:46'),(3,3,'/product/vitamin/vitamin3.jpg','/product/vitamin/d_vitamin3.jpg','2024-11-28 20:33:19','2024-11-28 20:33:19'),(4,4,'/product/vitamin/vitamin4.jpg','/product/vitamin/d_vitamin4.jpg','2024-11-28 20:33:41','2024-11-28 20:33:41'),(5,5,'/product/vitamin/vitamin5.jpg','/product/vitamin/d_vitamin5.jpg','2024-11-28 20:34:00','2024-11-28 20:34:00'),(6,6,'/product/lacto/lacto1.jpg','/product/lacto/d_lacto1.jpg','2024-11-28 20:37:34','2024-11-28 20:42:00'),(7,7,'/product/lacto/lacto2.jpg','/product/lacto/d_lacto2.jpg','2024-11-28 20:37:59','2024-11-28 20:42:40'),(8,8,'/product/lacto/lacto3.jpg','/product/lacto/d_lacto3.jpg','2024-11-28 20:38:16','2024-11-28 20:43:02'),(9,9,'/product/lacto/lacto4.jpg','/product/lacto/d_lacto4.jpg','2024-11-28 20:43:21','2024-11-28 20:43:21'),(10,10,'/product/lacto/lacto5.jpg','/product/lacto/d_lacto5.jpg','2024-11-28 20:43:37','2024-11-28 20:43:37'),(11,11,'/product/omega/omega1.jpg','/product/omega/d_omega1.jpg','2024-11-28 20:46:23','2024-11-28 20:46:23'),(12,12,'/product/omega/omega2.jpg','/product/omega/d_omega2.jpg','2024-11-28 20:46:38','2024-11-28 20:46:38'),(13,13,'/product/omega/omega3.jpg','/product/omega/d_omega3.jpg','2024-11-28 20:46:55','2024-11-28 20:46:55'),(14,14,'/product/omega/omega4.jpg','/product/omega/d_omega4.jpg','2024-11-28 20:47:09','2024-11-28 20:47:09'),(15,15,'/product/omega/omega5.jpg','/product/omega/d_omega5.jpg','2024-11-28 20:47:26','2024-11-28 20:47:26'),(16,16,'/product/supplements/supplements1.jpg','/product/supplements/d_supplements1.jpg','2024-11-28 20:49:10','2024-11-28 20:49:10'),(17,17,'/product/supplements/supplements2.jpg','/product/supplements/d_supplements2.jpg','2024-11-28 20:49:28','2024-11-28 20:49:28'),(18,18,'/product/supplements/supplements3.jpg','/product/supplements/d_supplements3.jpg','2024-11-28 20:49:42','2024-11-28 20:49:42'),(19,19,'/product/supplements/supplements4.jpg','/product/supplements/d_supplements4.jpg','2024-11-28 20:49:56','2024-11-28 20:49:56'),(20,20,'/product/supplements/supplements5.jpg','/product/supplements/d_supplements5.jpg','2024-11-28 20:50:10','2024-11-28 20:50:10');
/*!40000 ALTER TABLE `img` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-28 20:57:28
