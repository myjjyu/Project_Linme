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
-- Table structure for table `best_prod`
--

DROP TABLE IF EXISTS `best_prod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `best_prod` (
  `best_prod_id` int NOT NULL AUTO_INCREMENT COMMENT '인기상품 일련번호',
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '상품이름',
  `order_item_id` int NOT NULL COMMENT '결제 완료 상품',
  `order_count` int NOT NULL COMMENT '판개 개수(수량)',
  `reg_date` date DEFAULT NULL,
  PRIMARY KEY (`best_prod_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1483 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='인기상품 순위';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `best_prod`
--

LOCK TABLES `best_prod` WRITE;
/*!40000 ALTER TABLE `best_prod` DISABLE KEYS */;
INSERT INTO `best_prod` VALUES (1468,'[오로니아][유통기한 임박]엔초비 오메가3 징코 플러스_1박스(1,331mg*60캡슐, 총 79.86g)(2개월)',15,40,'2024-12-27'),(1469,'[네츄럴메이드]알티지 오메가-3 에이드_1박스(816.5mg*120캡슐, 총 97.98g)(2개월분)',12,33,'2024-12-27'),(1470,'[파파초이스]유산균 장건강 프로바이오틱스 30억 CFU_1박스(2g*30포, 총 60g)(1개월분)',6,15,'2024-12-27'),(1471,'[뉴트리랩스]마그네슘 & 비타민B_1박스(1,450mg*90정, 총 130.5g)(3개월분)',5,9,'2024-12-27'),(1472,'[네츄럴메이드]징코큐텐 은행잎추출물_1박스(450mg*60캡슐, 총 27g)(2개월분)',13,9,'2024-12-27'),(1473,'[오로니아]오메가-3 말랑말랑한 젤리_1박스(2.3g*60꾸미, 총 138g)(1개월분)',11,6,'2024-12-27'),(1474,'[뉴트리파워]글루타치온 골드_1박스(1,500mg*120정, 총 180g)(4개월분)',10,5,'2024-12-27'),(1475,'[뉴트리어스]국내최초 100% 식물성 멀티비타민미네랄_1박스(500mg*60캡슐, 총 30g)(2개월분)',4,4,'2024-12-27'),(1476,'[네츄럴메이드]쏘팔메토&옥타코사놀_1박스(1,000mg*90캡슐, 총 90g)(3개월분)',9,3,'2024-12-27'),(1477,'[뉴트리랩스]원데이 멀티바이오틱스_1박스(500mg*60캡슐, 총 30g)(2개월분)',8,2,'2024-12-27'),(1478,'[뉴트리어스]국내최초 100% 식물성 비타민C셀레늄_1박스(500mg*60캡슐, 총 30g)(2개월분)',2,1,'2024-12-27'),(1479,'[뉴트리어스]국내최초 100% 식물성 비타민B컴플렉스_1박스(500mg*60캡슐, 총 30g)(2개월분)',3,1,'2024-12-27'),(1480,'[오로니아]프로바이오틱스 골드 가세리유산균 비피더스유산균_1박스(500mg*60캡슐, 총 30g)(2개월분)',7,1,'2024-12-27'),(1481,'[네츄럴메이드]플랙씨드 앤 햄프씨드_1박스(1,000mg*180캡슐, 총 180g)(3개월분)',14,1,'2024-12-27');
/*!40000 ALTER TABLE `best_prod` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brand` (
  `brand_id` int NOT NULL AUTO_INCREMENT COMMENT '브랜드 일련번호',
  `brand_name` varchar(100) NOT NULL COMMENT '브랜드명',
  `reg_date` datetime NOT NULL COMMENT '등록일시',
  `edit_date` datetime NOT NULL COMMENT '변경일시',
  PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='브랜드';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'파파초이스','2024-11-28 19:27:57','2024-11-28 19:27:57'),(2,'뉴트리어스','2024-11-28 19:29:09','2024-11-28 19:29:09'),(3,'뉴트리랩스','2024-11-28 19:29:32','2024-11-28 19:29:32'),(4,'오로니아, 뉴트리코리아','2024-11-28 19:31:39','2024-11-28 19:31:39'),(5,'주식회사 애프디랩','2024-11-28 19:32:02','2024-11-28 19:32:02'),(6,'오로니아','2024-11-28 19:32:26','2024-11-28 19:32:26');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `cart_id` int NOT NULL AUTO_INCREMENT COMMENT '장바구니 일련번호',
  `member_id` int NOT NULL COMMENT '회원 일련번호',
  `product_id` int NOT NULL COMMENT '상품 일련번호',
  `product_count` int NOT NULL COMMENT '선택한 상품 수량',
  `total_price` int NOT NULL COMMENT '전체 주문금액',
  `reg_date` datetime NOT NULL COMMENT '등록일시',
  `edit_date` datetime NOT NULL COMMENT '변경일시',
  PRIMARY KEY (`cart_id`),
  KEY `fk_cart_member1_idx` (`member_id`),
  KEY `fk_cart_product1_idx` (`product_id`),
  CONSTRAINT `fk_cart_member1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `fk_cart_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=238 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='장바구니';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` int NOT NULL AUTO_INCREMENT COMMENT '카테고리 일련번호',
  `category_name` varchar(100) NOT NULL COMMENT '카테고리명',
  `reg_date` varchar(45) NOT NULL COMMENT '등록일시',
  `edit_date` datetime NOT NULL COMMENT '변경일시',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='카테고리';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'영양제','2024-11-28 19:36:17','2024-11-28 19:36:17'),(2,'비타민','2024-11-28 19:36:46','2024-11-28 19:36:46'),(3,'유산균','2024-11-28 19:37:06','2024-11-28 19:37:06'),(4,'오메가3','2024-11-28 19:37:34','2024-11-28 19:37:34');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `img`
--

DROP TABLE IF EXISTS `img`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `img` (
  `img_id` int NOT NULL AUTO_INCREMENT COMMENT '이미지 일련번호',
  `product_id` int NOT NULL COMMENT '상품 일련번호',
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

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `member_id` int NOT NULL AUTO_INCREMENT COMMENT '회원 일련번호',
  `user_id` varchar(255) NOT NULL COMMENT '아이디(이메일)',
  `user_pw` varchar(255) NOT NULL COMMENT '비밀번호',
  `user_name` varchar(100) NOT NULL COMMENT '회원 이름',
  `nickname` varchar(100) NOT NULL COMMENT '닉네임',
  `tel` varchar(20) NOT NULL COMMENT '휴대폰',
  `postcode` char(5) NOT NULL COMMENT '우편번호',
  `addr1` varchar(255) NOT NULL COMMENT '검색된 주소',
  `addr2` varchar(255) NOT NULL COMMENT '나머지 주소',
  `addr_msg` varchar(255) DEFAULT NULL COMMENT '배송 요청사항',
  `profile` blob COMMENT '프로필 사진',
  `is_out` enum('Y','N') NOT NULL COMMENT '탈퇴여부(Y/N)',
  `is_admin` enum('Y','N') NOT NULL COMMENT '관리자 여부(Y/N)',
  `login_date` datetime DEFAULT NULL COMMENT '마지막 로그인 일시',
  `reg_date` datetime NOT NULL COMMENT '등록일시',
  `edit_date` datetime NOT NULL COMMENT '변경일시',
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='회원';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `new_member`
--

DROP TABLE IF EXISTS `new_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `new_member` (
  `new_member_id` int NOT NULL AUTO_INCREMENT COMMENT '신규회원 ID',
  `member_count` int NOT NULL COMMENT '신규회원 수',
  `reg_date` date DEFAULT NULL,
  PRIMARY KEY (`new_member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `new_member`
--

LOCK TABLES `new_member` WRITE;
/*!40000 ALTER TABLE `new_member` DISABLE KEYS */;
INSERT INTO `new_member` VALUES (30,4,'2024-11-30'),(31,12,'2024-12-01'),(32,3,'2024-12-02'),(33,10,'2024-12-03'),(34,10,'2024-12-04'),(35,5,'2024-12-05'),(36,10,'2024-12-06'),(37,3,'2024-12-07'),(38,14,'2024-12-08'),(39,3,'2024-12-09'),(40,15,'2024-12-10'),(41,4,'2024-12-11'),(42,8,'2024-12-12'),(43,11,'2024-12-13'),(44,2,'2024-12-14'),(45,5,'2024-12-15'),(46,2,'2024-12-16'),(47,12,'2024-12-17'),(48,8,'2024-12-18'),(49,3,'2024-12-19'),(50,7,'2024-12-20'),(51,9,'2024-12-21'),(52,9,'2024-12-22'),(53,3,'2024-12-23'),(54,3,'2024-12-24'),(55,6,'2024-12-25'),(56,7,'2024-12-26');
/*!40000 ALTER TABLE `new_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `order_item_id` int NOT NULL AUTO_INCREMENT COMMENT '주문 상품 확인 일련번호',
  `payment_id` int NOT NULL COMMENT '주문/결제 일련번호',
  `order_img` varchar(255) NOT NULL COMMENT '주문 상품 이미지',
  `order_bname` varchar(100) NOT NULL COMMENT '주문 상품 브랜드명',
  `order_pname` varchar(255) NOT NULL COMMENT '주문 상품 상품명',
  `order_count` int NOT NULL COMMENT '주문 상품 확인 수량',
  `order_sprice` int NOT NULL COMMENT '주문 상품 구매가',
  `reg_date` datetime NOT NULL COMMENT '등록일시',
  `edit_date` datetime NOT NULL COMMENT '변경일시',
  PRIMARY KEY (`order_item_id`),
  KEY `fk_order_item_payment1_idx` (`payment_id`),
  CONSTRAINT `fk_order_item_payment1` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`payment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='주문 상품 확인';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `payment_id` int NOT NULL AUTO_INCREMENT COMMENT '주문/결제 일련번호',
  `member_id` int NOT NULL,
  `order_no` varchar(100) NOT NULL COMMENT '주문 번호',
  `order_name` varchar(100) NOT NULL COMMENT '주문자 이름',
  `order_tel` varchar(20) NOT NULL COMMENT '주문자 연락처',
  `addr1` varchar(255) NOT NULL COMMENT '검색된 주소',
  `addr2` varchar(255) NOT NULL COMMENT '나머지 주소',
  `nickname` varchar(255) NOT NULL COMMENT '배송지명(닉네임)',
  `addr_msg` varchar(255) NOT NULL COMMENT '배송 요청사항',
  `discount_price` int DEFAULT NULL,
  `total_price` int NOT NULL COMMENT '전체 주문금액',
  `reg_date` datetime NOT NULL COMMENT '등록일시',
  `edit_date` datetime NOT NULL COMMENT '변경일시',
  PRIMARY KEY (`payment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=467 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='주문/결제';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` int NOT NULL AUTO_INCREMENT COMMENT '상품 일련번호',
  `brand_id` int NOT NULL COMMENT '브랜드 일련번호',
  `category_id` int NOT NULL COMMENT '카테고리 일련번호',
  `product_name` varchar(255) NOT NULL COMMENT '상품명',
  `price` int NOT NULL COMMENT '정가',
  `sale_price` int NOT NULL COMMENT '판매가',
  `discount_rate` int DEFAULT NULL COMMENT '할인율',
  `discount_price` int DEFAULT NULL COMMENT '할인 금액',
  `reg_date` datetime NOT NULL COMMENT '등록일시',
  `edit_date` datetime NOT NULL COMMENT '변경일시',
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
INSERT INTO `product` VALUES (1,1,2,'[파파초이스]비타민D 아연_1박스(1.5g*30포, 총 45g)(1개월분)',39000,27000,31,12000,'2024-11-28 19:49:57','2024-11-28 19:49:57'),(2,2,2,'[뉴트리어스]국내최초 100% 식물성 비타민C셀레늄_1박스(500mg*60캡슐, 총 30g)(2개월분)',50000,30000,40,20000,'2024-11-28 19:50:19','2024-11-28 19:50:19'),(3,2,2,'[뉴트리어스]국내최초 100% 식물성 비타민B컴플렉스_1박스(500mg*60캡슐, 총 30g)(2개월분)',60000,36000,40,24000,'2024-11-28 19:50:35','2024-11-28 19:50:35'),(4,2,2,'[뉴트리어스]국내최초 100% 식물성 멀티비타민미네랄_1박스(500mg*60캡슐, 총 30g)(2개월분)',65000,39000,40,26000,'2024-11-28 19:50:54','2024-11-28 19:50:54'),(5,3,2,'[뉴트리랩스]마그네슘 & 비타민B_1박스(1,450mg*90정, 총 130.5g)(3개월분)',72160,65600,10,6560,'2024-11-28 19:51:09','2024-11-28 19:51:09'),(6,1,3,'[파파초이스]유산균 장건강 프로바이오틱스 30억 CFU_1박스(2g*30포, 총 60g)(1개월분)',42000,37000,12,5000,'2024-11-28 19:51:41','2024-11-28 20:08:50'),(7,4,3,'[오로니아]프로바이오틱스 골드 가세리유산균 비피더스유산균_1박스(500mg*60캡슐, 총 30g)(2개월분)',19800,18000,10,1800,'2024-11-28 19:51:59','2024-11-28 20:09:11'),(8,3,3,'[뉴트리랩스]원데이 멀티바이오틱스_1박스(500mg*60캡슐, 총 30g)(2개월분)',74100,57000,30,17100,'2024-11-28 19:52:25','2024-11-28 20:16:43'),(9,3,3,'[네츄럴메이드]쏘팔메토&옥타코사놀_1박스(1,000mg*90캡슐, 총 90g)(3개월분)',52500,29800,44,22700,'2024-11-28 19:52:40','2024-11-28 20:17:21'),(10,4,3,'[뉴트리파워]글루타치온 골드_1박스(1,500mg*120정, 총 180g)(4개월분)',42500,37000,15,5500,'2024-11-28 19:53:35','2024-11-28 20:17:54'),(11,4,4,'[오로니아]오메가-3 말랑말랑한 젤리_1박스(2.3g*60꾸미, 총 138g)(1개월분)',15340,11800,30,3540,'2024-11-28 19:54:06','2024-11-28 20:18:26'),(12,5,4,'[네츄럴메이드]알티지 오메가-3 에이드_1박스(816.5mg*120캡슐, 총 97.98g)(2개월분)',79600,20100,75,59500,'2024-11-28 19:54:22','2024-11-28 20:18:50'),(13,5,4,'[네츄럴메이드]징코큐텐 은행잎추출물_1박스(450mg*60캡슐, 총 27g)(2개월분)',72500,19800,73,52700,'2024-11-28 19:54:39','2024-11-28 20:19:13'),(14,5,4,'[네츄럴메이드]플랙씨드 앤 햄프씨드_1박스(1,000mg*180캡슐, 총 180g)(3개월분)',87000,36800,58,50200,'2024-11-28 19:54:52','2024-11-28 20:19:43'),(15,5,4,'[오로니아][유통기한 임박]엔초비 오메가3 징코 플러스_1박스(1,331mg*60캡슐, 총 79.86g)(2개월)',16520,11800,48,4720,'2024-11-28 19:55:08','2024-11-28 20:20:07'),(16,6,1,'[오로니아]캐나다 멀티비타민 말랑말랑한 젤리_1박스(2.2g*60꾸미, 총 132g)(2개월분)',16520,11800,48,4720,'2024-11-28 20:20:32','2024-11-28 20:20:32'),(17,6,1,'[오로니아]칼슘 & 비타민D 60꾸미 말랑말랑한 젤리_1박스(2.3g*60꾸미, 총 138g)(1개월분)',16520,11800,48,4720,'2024-11-28 20:20:55','2024-11-28 20:20:55'),(18,2,1,'[뉴트리어스]국내최초 100% 식물성 철분엽산 스텝1_1박스(500mg*60캡슐, 총 30g)(1개월분)',33000,19800,40,13200,'2024-11-28 20:21:11','2024-11-28 20:21:11'),(19,4,1,'[뉴트리파워]비오틴 10000_1박스(400mg*180캡슐, 총 72g)(6개월분)',52540,37000,42,15540,'2024-11-28 20:21:26','2024-11-28 20:21:26'),(20,2,1,'[뉴트리어스]국내최초 100% 식물성 비타민D아연_(500mg*60캡슐, 총 30g)(1개월분)',55000,33000,40,22000,'2024-11-28 20:21:52','2024-11-28 20:21:52');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profit`
--

DROP TABLE IF EXISTS `profit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profit` (
  `profit_id` int NOT NULL AUTO_INCREMENT COMMENT '수익ID',
  `category_id` int NOT NULL COMMENT '카테고리 이름',
  `total_count` int NOT NULL COMMENT '판매수량',
  `reg_date` date NOT NULL COMMENT '등록일자',
  PRIMARY KEY (`profit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='카테고리별 판매량';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profit`
--

LOCK TABLES `profit` WRITE;
/*!40000 ALTER TABLE `profit` DISABLE KEYS */;
INSERT INTO `profit` VALUES (1,1,3,'2024-11-01'),(2,2,5,'2024-11-02'),(3,3,2,'2024-11-03'),(4,4,4,'2024-11-04'),(5,1,1,'2024-11-05'),(6,2,3,'2024-11-06'),(7,3,5,'2024-11-07'),(8,4,2,'2024-11-08'),(9,1,4,'2024-11-09'),(10,2,1,'2024-11-10'),(11,3,3,'2024-11-11'),(12,4,5,'2024-11-12'),(13,1,2,'2024-11-13'),(14,2,4,'2024-11-14'),(15,3,1,'2024-11-15'),(16,4,3,'2024-11-16'),(17,1,5,'2024-11-17'),(18,2,2,'2024-11-18'),(19,3,4,'2024-11-19'),(20,4,1,'2024-11-20'),(21,1,3,'2024-11-21'),(22,2,5,'2024-11-22'),(23,3,2,'2024-11-23'),(24,4,4,'2024-11-24'),(25,1,1,'2024-11-25'),(26,2,3,'2024-11-26'),(27,3,5,'2024-11-27'),(28,4,2,'2024-11-28'),(29,1,4,'2024-11-29'),(30,2,1,'2024-11-30'),(31,3,3,'2024-12-01'),(32,4,5,'2024-12-02'),(33,1,2,'2024-12-03'),(34,2,4,'2024-12-04'),(35,3,1,'2024-12-05'),(36,4,3,'2024-12-06'),(37,1,5,'2024-12-07'),(38,2,2,'2024-12-08'),(39,3,4,'2024-12-09'),(40,4,1,'2024-12-10'),(41,1,3,'2024-12-11'),(42,2,5,'2024-12-12'),(43,3,2,'2024-12-13'),(44,4,4,'2024-12-14'),(45,1,1,'2024-12-15'),(46,2,3,'2024-12-16'),(47,3,5,'2024-12-17'),(48,4,2,'2024-12-18'),(49,1,4,'2024-12-19'),(50,2,1,'2024-12-20'),(51,3,3,'2024-12-21'),(52,4,5,'2024-12-22'),(53,1,2,'2024-12-23'),(54,2,4,'2024-12-24'),(55,3,1,'2024-12-25'),(56,4,3,'2024-12-26'),(57,1,5,'2024-12-27'),(58,2,2,'2024-12-28'),(59,3,4,'2024-12-29'),(60,4,1,'2024-12-30');
/*!40000 ALTER TABLE `profit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales` (
  `sales_id` int NOT NULL AUTO_INCREMENT COMMENT '판매 집계 일련번호',
  `sales_total` int NOT NULL COMMENT '전체 합계',
  `sales_date` date NOT NULL COMMENT '날짜',
  PRIMARY KEY (`sales_id`)
) ENGINE=InnoDB AUTO_INCREMENT=204 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='판매 집계';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
INSERT INTO `sales` VALUES (173,814400,'2024-12-01'),(174,785800,'2024-12-02'),(175,486100,'2024-12-03'),(176,73000,'2024-12-04'),(177,907100,'2024-12-05'),(178,316000,'2024-12-06'),(179,858900,'2024-12-07'),(180,346300,'2024-12-08'),(181,154800,'2024-12-09'),(182,735400,'2024-12-10'),(183,212300,'2024-12-11'),(184,855800,'2024-12-12'),(185,641500,'2024-12-13'),(186,640300,'2024-12-14'),(187,276700,'2024-12-15'),(188,463100,'2024-12-16'),(189,485200,'2024-12-17'),(190,36600,'2024-12-18'),(191,727500,'2024-12-19'),(192,527500,'2024-12-20'),(193,455300,'2024-12-21'),(194,693700,'2024-12-22'),(195,102600,'2024-12-23'),(196,432200,'2024-12-24'),(197,853200,'2024-12-25'),(198,969000,'2024-12-26'),(199,285400,'2024-12-27'),(200,520400,'2024-12-28'),(201,745700,'2024-12-29');
/*!40000 ALTER TABLE `sales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spring_session`
--

DROP TABLE IF EXISTS `spring_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spring_session` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint NOT NULL,
  `LAST_ACCESS_TIME` bigint NOT NULL,
  `MAX_INACTIVE_INTERVAL` int NOT NULL,
  `EXPIRY_TIME` bigint NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PRIMARY_ID`),
  UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spring_session`
--

LOCK TABLES `spring_session` WRITE;
/*!40000 ALTER TABLE `spring_session` DISABLE KEYS */;
/*!40000 ALTER TABLE `spring_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spring_session_attributes`
--

DROP TABLE IF EXISTS `spring_session_attributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spring_session_attributes` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL,
  PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`),
  CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `spring_session` (`PRIMARY_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spring_session_attributes`
--

LOCK TABLES `spring_session_attributes` WRITE;
/*!40000 ALTER TABLE `spring_session_attributes` DISABLE KEYS */;
/*!40000 ALTER TABLE `spring_session_attributes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-31 16:11:23
