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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` int NOT NULL AUTO_INCREMENT COMMENT '상품 일련번호',
  `product_name` varchar(255) NOT NULL COMMENT '상품명',
  `price` int NOT NULL COMMENT '정가',
  `sale_price` int NOT NULL COMMENT '판매가',
  `discount_rate` int DEFAULT NULL COMMENT '할인율',
  `discount_price` int DEFAULT NULL COMMENT '할인 금액',
  `reg_date` datetime NOT NULL COMMENT '등록일시',
  `edit_date` datetime NOT NULL COMMENT '변경일시',
  `brand_id` int NOT NULL COMMENT '브랜드 일련번호',
  `category_id` int NOT NULL COMMENT '카테고리 일련번호',
  PRIMARY KEY (`product_id`),
  KEY `fk_product_brand1_idx` (`brand_id`),
  KEY `fk_product_category1_idx` (`category_id`),
  CONSTRAINT `fk_product_brand1` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`brand_id`),
  CONSTRAINT `fk_product_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='상품';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'[파파초이스]비타민D 아연_1박스(1.5g*30포, 총 45g)(1개월분)',39000,27000,31,12000,'2024-11-28 19:49:57','2024-11-28 19:49:57',1,2),(2,'[뉴트리어스]국내최초 100% 식물성 비타민C셀레늄_1박스(500mg*60캡슐, 총 30g)(2개월분)',50000,30000,40,20000,'2024-11-28 19:50:19','2024-11-28 19:50:19',2,2),(3,'[뉴트리어스]국내최초 100% 식물성 비타민B컴플렉스_1박스(500mg*60캡슐, 총 30g)(2개월분)',60000,36000,40,24000,'2024-11-28 19:50:35','2024-11-28 19:50:35',2,2),(4,'[뉴트리어스]국내최초 100% 식물성 멀티비타민미네랄_1박스(500mg*60캡슐, 총 30g)(2개월분)',65000,39000,40,26000,'2024-11-28 19:50:54','2024-11-28 19:50:54',2,2),(5,'[뉴트리랩스]마그네슘 & 비타민B_1박스(1,450mg*90정, 총 130.5g)(3개월분)',72160,65600,10,6560,'2024-11-28 19:51:09','2024-11-28 19:51:09',3,2),(6,'[파파초이스]유산균 장건강 프로바이오틱스 30억 CFU_1박스(2g*30포, 총 60g)(1개월분)',42000,37000,12,5000,'2024-11-28 19:51:41','2024-11-28 20:08:50',1,3),(7,'[오로니아]프로바이오틱스 골드 가세리유산균 비피더스유산균_1박스(500mg*60캡슐, 총 30g)(2개월분)',19800,18000,10,1800,'2024-11-28 19:51:59','2024-11-28 20:09:11',4,3),(8,'[뉴트리랩스]원데이 멀티바이오틱스_1박스(500mg*60캡슐, 총 30g)(2개월분)',74100,57000,30,17100,'2024-11-28 19:52:25','2024-11-28 20:16:43',3,3),(9,'[네츄럴메이드]쏘팔메토&옥타코사놀_1박스(1,000mg*90캡슐, 총 90g)(3개월분)',52500,29800,44,22700,'2024-11-28 19:52:40','2024-11-28 20:17:21',3,3),(10,'[뉴트리파워]글루타치온 골드_1박스(1,500mg*120정, 총 180g)(4개월분)',42500,37000,15,5500,'2024-11-28 19:53:35','2024-11-28 20:17:54',4,3),(11,'[오로니아]오메가-3 말랑말랑한 젤리_1박스(2.3g*60꾸미, 총 138g)(1개월분)',15340,11800,30,3540,'2024-11-28 19:54:06','2024-11-28 20:18:26',4,4),(12,'[네츄럴메이드]알티지 오메가-3 에이드_1박스(816.5mg*120캡슐, 총 97.98g)(2개월분)',79600,20100,75,59500,'2024-11-28 19:54:22','2024-11-28 20:18:50',5,4),(13,'[네츄럴메이드]징코큐텐 은행잎추출물_1박스(450mg*60캡슐, 총 27g)(2개월분)',72500,19800,73,52700,'2024-11-28 19:54:39','2024-11-28 20:19:13',5,4),(14,'[네츄럴메이드]플랙씨드 앤 햄프씨드_1박스(1,000mg*180캡슐, 총 180g)(3개월분)',87000,36800,58,50200,'2024-11-28 19:54:52','2024-11-28 20:19:43',5,4),(15,'[오로니아][유통기한 임박]엔초비 오메가3 징코 플러스_1박스(1,331mg*60캡슐, 총 79.86g)(2개월)',16520,11800,48,4720,'2024-11-28 19:55:08','2024-11-28 20:20:07',5,4),(16,'[오로니아]캐나다 멀티비타민 말랑말랑한 젤리_1박스(2.2g*60꾸미, 총 132g)(2개월분)',16520,11800,48,4720,'2024-11-28 20:20:32','2024-11-28 20:20:32',6,1),(17,'[오로니아]칼슘 & 비타민D 60꾸미 말랑말랑한 젤리_1박스(2.3g*60꾸미, 총 138g)(1개월분)',16520,11800,48,4720,'2024-11-28 20:20:55','2024-11-28 20:20:55',6,1),(18,'[뉴트리어스]국내최초 100% 식물성 철분엽산 스텝1_1박스(500mg*60캡슐, 총 30g)(1개월분)',33000,19800,40,13200,'2024-11-28 20:21:11','2024-11-28 20:21:11',2,1),(19,'[뉴트리파워]비오틴 10000_1박스(400mg*180캡슐, 총 72g)(6개월분)',52540,37000,42,15540,'2024-11-28 20:21:26','2024-11-28 20:21:26',4,1),(20,'[뉴트리어스]국내최초 100% 식물성 비타민D아연_(500mg*60캡슐, 총 30g)(1개월분)',55000,33000,40,22000,'2024-11-28 20:21:52','2024-11-28 20:21:52',2,1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-06 16:47:27
