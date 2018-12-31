-- MySQL dump 10.13  Distrib 5.1.38, for apple-darwin9.5.0 (i386)
--
-- Host: localhost    Database: demo_db
-- ------------------------------------------------------
-- Server version	5.1.38

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `contact_ball_of_mud`
--

DROP TABLE IF EXISTS `contact_ball_of_mud`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact_ball_of_mud` (
  `id`              BIGINT(20) NOT NULL AUTO_INCREMENT,
  `number`          BIGINT(20)          DEFAULT NULL,
  `gender`          VARCHAR(45)         DEFAULT NULL,
  `givenname`       VARCHAR(45)         DEFAULT NULL,
  `middleinitial`   VARCHAR(45)         DEFAULT NULL,
  `surname`         VARCHAR(45)         DEFAULT NULL,
  `streetaddress`   VARCHAR(45)         DEFAULT NULL,
  `city`            VARCHAR(45)         DEFAULT NULL,
  `state`           VARCHAR(45)         DEFAULT NULL,
  `zipcode`         VARCHAR(45)         DEFAULT NULL,
  `country`         VARCHAR(45)         DEFAULT NULL,
  `emailaddress`    VARCHAR(45)         DEFAULT NULL,
  `password`        VARCHAR(45)         DEFAULT NULL,
  `telephonenumber` VARCHAR(45)         DEFAULT NULL,
  `mothersmaiden`   VARCHAR(45)         DEFAULT NULL,
  `birthday`        DATETIME            DEFAULT NULL,
  `CCType`          VARCHAR(45)         DEFAULT NULL,
  `CCNumber`        VARCHAR(45)         DEFAULT NULL,
  `CVV2`            VARCHAR(5)          DEFAULT NULL,
  `CCExpires`       DATETIME            DEFAULT NULL,
  `NationalID`      VARCHAR(45)         DEFAULT NULL,
  `UPS`             VARCHAR(45)         DEFAULT NULL,
  `Occupation`      VARCHAR(45)         DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `number_UNIQUE` (`number`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2952
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `inv`
--

DROP TABLE IF EXISTS `inv`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inv` (
  `invid`           INT(11)     NOT NULL,
  `invnumber`       VARCHAR(75) NOT NULL,
  `datetimecreated` DATETIME    NOT NULL,
  `udtime`          TIME        NOT NULL,
  `uddate`          DATE        NOT NULL,
  `terms`           VARCHAR(15) DEFAULT NULL,
  PRIMARY KEY (`invid`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lidetail`
--

DROP TABLE IF EXISTS `lidetail`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lidetail` (
  `invoice_id` BIGINT(20) NOT NULL,
  `row_number` BIGINT(20) NOT NULL,
  `comment`    VARCHAR(255) DEFAULT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

-- Table structure for table `lineitem`
--

DROP TABLE IF EXISTS `lineitem`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lineitem` (
  `invid`  BIGINT(20)  NOT NULL,
  `rownum` BIGINT(20)  NOT NULL,
  `pn`     VARCHAR(50) NOT NULL,
  `qty`    BIGINT(20)  NOT NULL,
  `price`  DECIMAL(10, 2) DEFAULT NULL,
  PRIMARY KEY (`invid`, `rownum`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2010-12-03 10:20:13