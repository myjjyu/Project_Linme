-- MySQL dump 10.13  Distrib 8.0.38, for macos14 (arm64)
--
-- Host: localhost    Database: linme
-- ------------------------------------------------------
-- Server version	9.0.1

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
-- Table structure for table `best_prod`
--

DROP TABLE IF EXISTS `best_prod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `best_prod` (
  `best_prod_id` int NOT NULL AUTO_INCREMENT COMMENT '인기상품 일련번호',
  `product_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '상품이름',
  `order_item_id` int NOT NULL COMMENT '결제 완료 상품',
  `order_count` int NOT NULL COMMENT '판개 개수(수량)',
  `reg_date` date DEFAULT NULL,
  PRIMARY KEY (`best_prod_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1483 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='인기상품 순위';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `best_prod`
--

LOCK TABLES `best_prod` WRITE;
/*!40000 ALTER TABLE `best_prod` DISABLE KEYS */;
INSERT INTO `best_prod` VALUES (1468,'[오로니아][유통기한 임박]엔초비 오메가3 징코 플러스_1박스(1,331mg*60캡슐, 총 79.86g)(2개월)',15,40,'2024-12-27'),(1469,'[네츄럴메이드]알티지 오메가-3 에이드_1박스(816.5mg*120캡슐, 총 97.98g)(2개월분)',12,33,'2024-12-27'),(1470,'[파파초이스]유산균 장건강 프로바이오틱스 30억 CFU_1박스(2g*30포, 총 60g)(1개월분)',6,15,'2024-12-27'),(1471,'[뉴트리랩스]마그네슘 & 비타민B_1박스(1,450mg*90정, 총 130.5g)(3개월분)',5,9,'2024-12-27'),(1472,'[네츄럴메이드]징코큐텐 은행잎추출물_1박스(450mg*60캡슐, 총 27g)(2개월분)',13,9,'2024-12-27'),(1473,'[오로니아]오메가-3 말랑말랑한 젤리_1박스(2.3g*60꾸미, 총 138g)(1개월분)',11,6,'2024-12-27'),(1474,'[뉴트리파워]글루타치온 골드_1박스(1,500mg*120정, 총 180g)(4개월분)',10,5,'2024-12-27'),(1475,'[뉴트리어스]국내최초 100% 식물성 멀티비타민미네랄_1박스(500mg*60캡슐, 총 30g)(2개월분)',4,4,'2024-12-27'),(1476,'[네츄럴메이드]쏘팔메토&옥타코사놀_1박스(1,000mg*90캡슐, 총 90g)(3개월분)',9,3,'2024-12-27'),(1477,'[뉴트리랩스]원데이 멀티바이오틱스_1박스(500mg*60캡슐, 총 30g)(2개월분)',8,2,'2024-12-27'),(1478,'[뉴트리어스]국내최초 100% 식물성 비타민C셀레늄_1박스(500mg*60캡슐, 총 30g)(2개월분)',2,1,'2024-12-27'),(1479,'[뉴트리어스]국내최초 100% 식물성 비타민B컴플렉스_1박스(500mg*60캡슐, 총 30g)(2개월분)',3,1,'2024-12-27'),(1480,'[오로니아]프로바이오틱스 골드 가세리유산균 비피더스유산균_1박스(500mg*60캡슐, 총 30g)(2개월분)',7,1,'2024-12-27'),(1481,'[네츄럴메이드]플랙씨드 앤 햄프씨드_1박스(1,000mg*180캡슐, 총 180g)(3개월분)',14,1,'2024-12-27');
/*!40000 ALTER TABLE `best_prod` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-27 15:37:15
