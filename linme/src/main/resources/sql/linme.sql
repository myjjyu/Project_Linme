-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema linme
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema linme
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `linme` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `linme` ;

-- -----------------------------------------------------
-- Table `linme`.`brand`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `linme`.`brand` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '브랜드 일련번호',
  `brand_name` VARCHAR(100) NOT NULL COMMENT '브랜드명',
  `reg_date` DATETIME NOT NULL COMMENT '등록일시',
  `edit_date` DATETIME NOT NULL COMMENT '변경일시',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '브랜드';


-- -----------------------------------------------------
-- Table `linme`.`member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `linme`.`member` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '회원 일련번호',
  `user_id` VARCHAR(255) NOT NULL COMMENT '아이디(이메일)',
  `user_pw` VARCHAR(255) NOT NULL COMMENT '비밀번호',
  `user_name` VARCHAR(100) NOT NULL COMMENT '회원 이름',
  `nickname` VARCHAR(100) NOT NULL COMMENT '닉네임',
  `tel` VARCHAR(20) NOT NULL COMMENT '휴대폰',
  `postcode` CHAR(5) NOT NULL COMMENT '우편번호',
  `addr1` VARCHAR(255) NOT NULL COMMENT '검색된 주소',
  `addr2` VARCHAR(255) NOT NULL COMMENT '나머지 주소',
  `addr_name` VARCHAR(100) NOT NULL COMMENT '배송지명',
  `addr_msg` VARCHAR(255) NULL DEFAULT NULL COMMENT '배송 요청사항',
  `profile` BLOB NULL DEFAULT NULL COMMENT '프로필 사진',
  `is_out` ENUM('Y', 'N') NOT NULL COMMENT '탈퇴여부(Y/N)',
  `is_admin` ENUM('Y', 'N') NOT NULL COMMENT '관리자 여부(Y/N)',
  `login_date` DATETIME NULL DEFAULT NULL COMMENT '마지막 로그인 일시',
  `reg_date` DATETIME NOT NULL COMMENT '등록일시',
  `edit_date` DATETIME NOT NULL COMMENT '변경일시',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '회원';


-- -----------------------------------------------------
-- Table `linme`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `linme`.`category` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '카테고리 일련번호',
  `category_name` VARCHAR(100) NOT NULL COMMENT '카테고리명',
  `icon_category` VARCHAR(255) NULL DEFAULT NULL COMMENT '아이콘 카테고리',
  `title_category` VARCHAR(255) NULL DEFAULT NULL COMMENT '타이틀 카테고리',
  `header_item` VARCHAR(10) NULL DEFAULT NULL COMMENT '카테고리 헤더 아이템',
  `reg_date` VARCHAR(45) NOT NULL COMMENT '등록일시',
  `edit_date` DATETIME NOT NULL COMMENT '변경일시',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '카테고리';


-- -----------------------------------------------------
-- Table `linme`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `linme`.`product` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '상품 일련번호',
  `product_name` VARCHAR(255) NOT NULL COMMENT '상품명',
  `brand_id` INT NOT NULL COMMENT '브랜드 일련번호',
  `price` INT NOT NULL COMMENT '정가',
  `sale_price` INT NOT NULL COMMENT '판매가',
  `discount_rate` INT NULL DEFAULT NULL COMMENT '할인율',
  `product_img` VARCHAR(255) NOT NULL COMMENT '상품 이미지',
  `detail_img` VARCHAR(255) NOT NULL COMMENT '상세 이미지',
  `category_id` INT NOT NULL COMMENT '카테고리 ID',
  `header_item` VARCHAR(10) NULL DEFAULT NULL COMMENT '신상품, 베스트, 특가상품',
  `discount_price` INT NULL DEFAULT NULL COMMENT '할인 금액',
  `reg_date` DATETIME NOT NULL COMMENT '등록일시',
  `edit_date` DATETIME NOT NULL COMMENT '변경일시',
  PRIMARY KEY (`id`),
  INDEX `category_id` (`category_id` ASC) VISIBLE,
  INDEX `product_ibfk_2` (`brand_id` ASC) VISIBLE,
  CONSTRAINT `product_ibfk_1`
    FOREIGN KEY (`category_id`)
    REFERENCES `linme`.`category` (`id`),
  CONSTRAINT `product_ibfk_2`
    FOREIGN KEY (`brand_id`)
    REFERENCES `linme`.`brand` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '상품';


-- -----------------------------------------------------
-- Table `linme`.`cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `linme`.`cart` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '장바구니 일련번호',
  `product_count` INT NULL DEFAULT NULL COMMENT '선택한 상품 수량',
  `total_price` INT NULL DEFAULT NULL COMMENT '전체 주문금액',
  `member_id` INT NOT NULL COMMENT '회원 일련번호',
  `product_id` INT NOT NULL COMMENT '상품 일련번호',
  `reg_date` DATETIME NOT NULL COMMENT '등록일시',
  `edit_date` DATETIME NOT NULL COMMENT '변경일시',
  PRIMARY KEY (`id`),
  INDEX `cart_ibfk_1` (`member_id` ASC) VISIBLE,
  INDEX `cart_ibfk_2` (`product_id` ASC) VISIBLE,
  CONSTRAINT `cart_ibfk_1`
    FOREIGN KEY (`member_id`)
    REFERENCES `linme`.`member` (`id`),
  CONSTRAINT `cart_ibfk_2`
    FOREIGN KEY (`product_id`)
    REFERENCES `linme`.`product` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '장바구니';


-- -----------------------------------------------------
-- Table `linme`.`img`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `linme`.`img` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '이미지 일련번호',
  `product_id` INT NOT NULL COMMENT '상품 일련번호',
  `img_url` VARCHAR(255) NOT NULL COMMENT '이미지 URL',
  `img_path` VARCHAR(255) NOT NULL COMMENT '이미지 경로',
  `img1` VARCHAR(45) NOT NULL COMMENT '이미지1',
  `img2` VARCHAR(45) NULL DEFAULT NULL COMMENT '이미지2',
  `img3` VARCHAR(45) NULL DEFAULT NULL COMMENT '이미지3',
  `d_img1` VARCHAR(45) NOT NULL COMMENT '상세 이미지1',
  `d_img2` VARCHAR(45) NULL DEFAULT NULL COMMENT '상세 이미지2',
  `d_img3` VARCHAR(45) NULL DEFAULT NULL COMMENT '상세 이미지3',
  `reg_date` DATETIME NOT NULL COMMENT '등록일시',
  `edit_date` DATETIME NOT NULL COMMENT '변경일시',
  PRIMARY KEY (`id`),
  INDEX `img_ibfk_1` (`product_id` ASC) VISIBLE,
  CONSTRAINT `img_ibfk_1`
    FOREIGN KEY (`product_id`)
    REFERENCES `linme`.`product` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '이미지';


-- -----------------------------------------------------
-- Table `linme`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `linme`.`payment` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '주문/결제 일련번호',
  `order_name` VARCHAR(100) NOT NULL COMMENT '주문자 이름',
  `order_tel` VARCHAR(20) NOT NULL COMMENT '주문자 연락처',
  `addr1` VARCHAR(255) NOT NULL COMMENT '검색된 주소',
  `addr2` VARCHAR(255) NOT NULL COMMENT '나머지 주소',
  `addr_name` VARCHAR(255) NOT NULL COMMENT '배송지명',
  `addr_msg` VARCHAR(255) NOT NULL COMMENT '배송 요청사항',
  `discount_price` INT NULL DEFAULT NULL COMMENT '할인 금액',
  `total_price` INT NOT NULL COMMENT '전체 주문금액',
  `reg_date` DATETIME NOT NULL COMMENT '등록일시',
  `edit_date` DATETIME NOT NULL COMMENT '변경일시',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '주문/결제';


-- -----------------------------------------------------
-- Table `linme`.`order_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `linme`.`order_item` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '주문 상품 확인 일련번호',
  `order_bname` VARCHAR(100) NOT NULL COMMENT '주문 상품 브랜드명',
  `order_pname` VARCHAR(255) NOT NULL COMMENT '주문 상품 상품명',
  `order_count` INT NOT NULL COMMENT '주문 상품 확인 수량',
  `order_sprice` INT NOT NULL COMMENT '주문 상품 판매가',
  `order_price` INT NOT NULL COMMENT '주문 상품 구매가',
  `payment_id` INT NOT NULL COMMENT '주문/결제 일련번호',
  `reg_date` DATETIME NOT NULL COMMENT '등록일시',
  `edit_date` DATETIME NOT NULL COMMENT '변경일시',
  PRIMARY KEY (`id`),
  INDEX `fk_order_item_payment1_idx` (`payment_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_item_payment1`
    FOREIGN KEY (`payment_id`)
    REFERENCES `linme`.`payment` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '주문 상품 확인';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
