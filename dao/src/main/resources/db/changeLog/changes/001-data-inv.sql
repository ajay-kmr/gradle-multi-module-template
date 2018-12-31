--
-- Dumping data for table `inv`
--

LOCK TABLES `inv` WRITE;
/*!40000 ALTER TABLE `inv`
  DISABLE KEYS */;
INSERT INTO `inv` VALUES (1, '866638', '2010-12-02 14:33:16', '04:32:31', '2010-12-03', 'NET 30'),
  (2, '766673', '2010-11-11 11:11:00', '09:30:12', '2010-11-12', 'NET 7'),
  (3, '687636', '2010-09-23 16:46:23', '16:46:23', '2010-10-01', 'UPON RECEIPT');
/*!40000 ALTER TABLE `inv`
  ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Dumping data for table `lidetail`
--

LOCK TABLES `lidetail` WRITE;
/*!40000 ALTER TABLE `lidetail`
  DISABLE KEYS */;
INSERT INTO `lidetail` VALUES (2, 1, 'Gift wrapping requested'), (3, 2, 'Gold trim');
/*!40000 ALTER TABLE `lidetail`
  ENABLE KEYS */;
UNLOCK TABLES;

--
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

--
-- Dumping data for table `lineitem`
--

LOCK TABLES `lineitem` WRITE;
/*!40000 ALTER TABLE `lineitem`
  DISABLE KEYS */;
INSERT INTO `lineitem`
VALUES (1, 1, 'C1206HCV50G', 3800, '0.42'), (1, 2, 'C0805CH583Q', 10100, '0.24'), (2, 1, 'VZ8802H39', 100, '2.31'),
  (3, 1, 'Q3882OLV40', 5200, '0.72'), (3, 2, '9009898873', 15, '23.12'), (3, 3, 'P0201V050Q', 50000, '0.42');
/*!40000 ALTER TABLE `lineitem`
  ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2010-12-03 10:20:13