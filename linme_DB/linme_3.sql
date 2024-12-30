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
-- Table `linme`.`best_prod`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `linme`.`best_prod` (
  `best_prod_id` INT NOT NULL AUTO_INCREMENT COMMENT '인기상품 일련번호',
  `product_name` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL COMMENT '상품이름',
  `order_item_id` INT NOT NULL COMMENT '결제 완료 상품',
  `order_count` INT NOT NULL COMMENT '판개 개수(수량)',
  `reg_date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`best_prod_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1483
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
COMMENT = '인기상품 순위';


-- -----------------------------------------------------
-- Table `linme`.`brand`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `linme`.`brand` (
  `brand_id` INT NOT NULL AUTO_INCREMENT COMMENT '브랜드 일련번호',
  `brand_name` VARCHAR(100) NOT NULL COMMENT '브랜드명',
  `reg_date` DATETIME NOT NULL COMMENT '등록일시',
  `edit_date` DATETIME NOT NULL COMMENT '변경일시',
  PRIMARY KEY (`brand_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '브랜드';


-- -----------------------------------------------------
-- Table `linme`.`member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `linme`.`member` (
  `member_id` INT NOT NULL AUTO_INCREMENT COMMENT '회원 일련번호',
  `user_id` VARCHAR(255) NOT NULL COMMENT '아이디(이메일)',
  `user_pw` VARCHAR(255) NOT NULL COMMENT '비밀번호',
  `user_name` VARCHAR(100) NOT NULL COMMENT '회원 이름',
  `nickname` VARCHAR(100) NOT NULL COMMENT '닉네임',
  `tel` VARCHAR(20) NOT NULL COMMENT '휴대폰',
  `postcode` CHAR(5) NOT NULL COMMENT '우편번호',
  `addr1` VARCHAR(255) NOT NULL COMMENT '검색된 주소',
  `addr2` VARCHAR(255) NOT NULL COMMENT '나머지 주소',
  `addr_msg` VARCHAR(255) NULL DEFAULT NULL COMMENT '배송 요청사항',
  `profile` BLOB NULL DEFAULT NULL COMMENT '프로필 사진',
  `is_out` ENUM('Y', 'N') NOT NULL COMMENT '탈퇴여부(Y/N)',
  `is_admin` ENUM('Y', 'N') NOT NULL COMMENT '관리자 여부(Y/N)',
  `login_date` DATETIME NULL DEFAULT NULL COMMENT '마지막 로그인 일시',
  `reg_date` DATETIME NOT NULL COMMENT '등록일시',
  `edit_date` DATETIME NOT NULL COMMENT '변경일시',
  PRIMARY KEY (`member_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '회원';


-- -----------------------------------------------------
-- Table `linme`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `linme`.`category` (
  `category_id` INT NOT NULL AUTO_INCREMENT COMMENT '카테고리 일련번호',
  `category_name` VARCHAR(100) NOT NULL COMMENT '카테고리명',
  `reg_date` VARCHAR(45) NOT NULL COMMENT '등록일시',
  `edit_date` DATETIME NOT NULL COMMENT '변경일시',
  PRIMARY KEY (`category_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '카테고리';


-- -----------------------------------------------------
-- Table `linme`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `linme`.`product` (
  `product_id` INT NOT NULL AUTO_INCREMENT COMMENT '상품 일련번호',
  `brand_id` INT NOT NULL COMMENT '브랜드 일련번호',
  `category_id` INT NOT NULL COMMENT '카테고리 일련번호',
  `product_name` VARCHAR(255) NOT NULL COMMENT '상품명',
  `price` INT NOT NULL COMMENT '정가',
  `sale_price` INT NOT NULL COMMENT '판매가',
  `discount_rate` INT NULL DEFAULT NULL COMMENT '할인율',
  `discount_price` INT NULL DEFAULT NULL COMMENT '할인 금액',
  `reg_date` DATETIME NOT NULL COMMENT '등록일시',
  `edit_date` DATETIME NOT NULL COMMENT '변경일시',
  PRIMARY KEY (`product_id`),
  INDEX `fk_product_brand1_idx` (`brand_id` ASC) VISIBLE,
  INDEX `fk_product_category1_idx` (`category_id` ASC) VISIBLE,
  CONSTRAINT `fk_product_brand1`
    FOREIGN KEY (`brand_id`)
    REFERENCES `linme`.`brand` (`brand_id`),
  CONSTRAINT `fk_product_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `linme`.`category` (`category_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '상품';


-- -----------------------------------------------------
-- Table `linme`.`cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `linme`.`cart` (
  `cart_id` INT NOT NULL AUTO_INCREMENT COMMENT '장바구니 일련번호',
  `member_id` INT NOT NULL COMMENT '회원 일련번호',
  `product_id` INT NOT NULL COMMENT '상품 일련번호',
  `product_count` INT NOT NULL COMMENT '선택한 상품 수량',
  `total_price` INT NOT NULL COMMENT '전체 주문금액',
  `reg_date` DATETIME NOT NULL COMMENT '등록일시',
  `edit_date` DATETIME NOT NULL COMMENT '변경일시',
  PRIMARY KEY (`cart_id`),
  INDEX `fk_cart_member1_idx` (`member_id` ASC) VISIBLE,
  INDEX `fk_cart_product1_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_cart_member1`
    FOREIGN KEY (`member_id`)
    REFERENCES `linme`.`member` (`member_id`),
  CONSTRAINT `fk_cart_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `linme`.`product` (`product_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 238
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '장바구니';


-- -----------------------------------------------------
-- Table `linme`.`img`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `linme`.`img` (
  `img_id` INT NOT NULL AUTO_INCREMENT COMMENT '이미지 일련번호',
  `product_id` INT NOT NULL COMMENT '상품 일련번호',
  `img` VARCHAR(255) NOT NULL COMMENT '이미지',
  `d_img` VARCHAR(255) NOT NULL COMMENT '상세 이미지',
  `reg_date` DATETIME NOT NULL COMMENT '등록일시',
  `edit_date` DATETIME NOT NULL COMMENT '변경일시',
  PRIMARY KEY (`img_id`),
  INDEX `fk_img_product1_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_img_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `linme`.`product` (`product_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '이미지';


-- -----------------------------------------------------
-- Table `linme`.`new_member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `linme`.`new_member` (
  `new_member_id` INT NOT NULL AUTO_INCREMENT COMMENT '신규회원 ID',
  `member_count` INT NOT NULL COMMENT '신규회원 수',
  `reg_date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`new_member_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 57
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `linme`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `linme`.`payment` (
  `payment_id` INT NOT NULL AUTO_INCREMENT COMMENT '주문/결제 일련번호',
  `member_id` INT NOT NULL,
  `order_no` VARCHAR(100) NOT NULL COMMENT '주문 번호',
  `order_name` VARCHAR(100) NOT NULL COMMENT '주문자 이름',
  `order_tel` VARCHAR(20) NOT NULL COMMENT '주문자 연락처',
  `addr1` VARCHAR(255) NOT NULL COMMENT '검색된 주소',
  `addr2` VARCHAR(255) NOT NULL COMMENT '나머지 주소',
  `nickname` VARCHAR(255) NOT NULL COMMENT '배송지명(닉네임)',
  `addr_msg` VARCHAR(255) NOT NULL COMMENT '배송 요청사항',
  `discount_price` INT NULL DEFAULT NULL,
  `total_price` INT NOT NULL COMMENT '전체 주문금액',
  `reg_date` DATETIME NOT NULL COMMENT '등록일시',
  `edit_date` DATETIME NOT NULL COMMENT '변경일시',
  PRIMARY KEY (`payment_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 467
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '주문/결제';


-- -----------------------------------------------------
-- Table `linme`.`order_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `linme`.`order_item` (
  `order_item_id` INT NOT NULL AUTO_INCREMENT COMMENT '주문 상품 확인 일련번호',
  `payment_id` INT NOT NULL COMMENT '주문/결제 일련번호',
  `order_img` VARCHAR(255) NOT NULL COMMENT '주문 상품 이미지',
  `order_bname` VARCHAR(100) NOT NULL COMMENT '주문 상품 브랜드명',
  `order_pname` VARCHAR(255) NOT NULL COMMENT '주문 상품 상품명',
  `order_count` INT NOT NULL COMMENT '주문 상품 확인 수량',
  `order_sprice` INT NOT NULL COMMENT '주문 상품 구매가',
  `reg_date` DATETIME NOT NULL COMMENT '등록일시',
  `edit_date` DATETIME NOT NULL COMMENT '변경일시',
  PRIMARY KEY (`order_item_id`),
  INDEX `fk_order_item_payment1_idx` (`payment_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_item_payment1`
    FOREIGN KEY (`payment_id`)
    REFERENCES `linme`.`payment` (`payment_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 52
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '주문 상품 확인';


-- -----------------------------------------------------
-- Table `linme`.`profit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `linme`.`profit` (
  `profit_id` INT NOT NULL AUTO_INCREMENT COMMENT '수익ID',
  `category_id` INT NOT NULL COMMENT '카테고리 이름',
  `total_count` INT NOT NULL COMMENT '판매수량',
  `reg_date` DATE NOT NULL COMMENT '등록일자',
  PRIMARY KEY (`profit_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 103
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '카테고리별 판매량';


-- -----------------------------------------------------
-- Table `linme`.`sales`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `linme`.`sales` (
  `sales_id` INT NOT NULL AUTO_INCREMENT COMMENT '판매 집계 일련번호',
  `sales_total` INT NOT NULL COMMENT '전체 합계',
  `sales_date` DATE NOT NULL COMMENT '날짜',
  PRIMARY KEY (`sales_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 204
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '판매 집계';


-- -----------------------------------------------------
-- Table `linme`.`spring_session`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `linme`.`spring_session` (
  `PRIMARY_ID` CHAR(36) NOT NULL,
  `SESSION_ID` CHAR(36) NOT NULL,
  `CREATION_TIME` BIGINT NOT NULL,
  `LAST_ACCESS_TIME` BIGINT NOT NULL,
  `MAX_INACTIVE_INTERVAL` INT NOT NULL,
  `EXPIRY_TIME` BIGINT NOT NULL,
  `PRINCIPAL_NAME` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`PRIMARY_ID`),
  UNIQUE INDEX `SPRING_SESSION_IX1` (`SESSION_ID` ASC) VISIBLE,
  INDEX `SPRING_SESSION_IX2` (`EXPIRY_TIME` ASC) VISIBLE,
  INDEX `SPRING_SESSION_IX3` (`PRINCIPAL_NAME` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `linme`.`spring_session_attributes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `linme`.`spring_session_attributes` (
  `SESSION_PRIMARY_ID` CHAR(36) NOT NULL,
  `ATTRIBUTE_NAME` VARCHAR(200) NOT NULL,
  `ATTRIBUTE_BYTES` BLOB NOT NULL,
  PRIMARY KEY (`SESSION_PRIMARY_ID`, `ATTRIBUTE_NAME`),
  CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK`
    FOREIGN KEY (`SESSION_PRIMARY_ID`)
    REFERENCES `linme`.`spring_session` (`PRIMARY_ID`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
