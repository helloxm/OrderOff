-- MySQL dump 10.13  Distrib 5.6.21, for Win32 (x86)
--
-- Host: localhost    Database: offorder
-- ------------------------------------------------------
-- Server version	5.6.21-log

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
-- Table structure for table `deptinfo`
--

DROP TABLE IF EXISTS `deptinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `deptinfo` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `dept_code` varchar(10) NOT NULL,
  `dept_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deptinfo`
--

LOCK TABLES `deptinfo` WRITE;
/*!40000 ALTER TABLE `deptinfo` DISABLE KEYS */;
INSERT INTO `deptinfo` VALUES (1,'330','产科门诊');
/*!40000 ALTER TABLE `deptinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctorinfo`
--

DROP TABLE IF EXISTS `doctorinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctorinfo` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `doctor_code` varchar(10) NOT NULL,
  `doctor_name` varchar(20) NOT NULL,
  `dept_code` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctorinfo`
--

LOCK TABLES `doctorinfo` WRITE;
/*!40000 ALTER TABLE `doctorinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `doctorinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderbaseinfo`
--

DROP TABLE IF EXISTS `orderbaseinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderbaseinfo` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `doctor_code` varchar(10) DEFAULT NULL,
  `dept_code` varchar(10) DEFAULT NULL,
  `select_date` varchar(20) DEFAULT NULL,
  `select_time_desc` varchar(10) DEFAULT NULL,
  `select_timeM_count` int(10) DEFAULT '0',
  `select_timeA_count` int(10) DEFAULT '0',
  `select_timeM` text,
  `select_timeA` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderbaseinfo`
--

LOCK TABLES `orderbaseinfo` WRITE;
/*!40000 ALTER TABLE `orderbaseinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderbaseinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderinfo`
--

DROP TABLE IF EXISTS `orderinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderinfo` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `dept_code` varchar(10) NOT NULL,
  `doctor_code` varchar(10) NOT NULL,
  `doctor_name` varchar(20) NOT NULL,
  `order_date` varchar(20) NOT NULL,
  `order_time` varchar(20) NOT NULL,
  `adjust` tinyint(1) DEFAULT '0',
  `card_id` varchar(20) NOT NULL,
  `status` int(10) DEFAULT '2',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderinfo`
--

LOCK TABLES `orderinfo` WRITE;
/*!40000 ALTER TABLE `orderinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patientinfo`
--

DROP TABLE IF EXISTS `patientinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patientinfo` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `password` varchar(40) NOT NULL,
  `card_id` varchar(20) NOT NULL,
  `mobile_number` varchar(20) NOT NULL,
  `order_number` int(20) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patientinfo`
--

LOCK TABLES `patientinfo` WRITE;
/*!40000 ALTER TABLE `patientinfo` DISABLE KEYS */;
INSERT INTO `patientinfo` VALUES (1,'李美琳','123456','350825199309130228','13859939372',100),(2,'翟东蒙','123456','36233119911007527X','13859939372',100),(3,'李劭静','123456','350212198502055048','13859939372',99);
/*!40000 ALTER TABLE `patientinfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-03-09 14:51:49
