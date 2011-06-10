-- MySQL dump 10.13  Distrib 5.1.55, for redhat-linux-gnu (i386)
--
-- Host: localhost    Database: kaarpool
-- ------------------------------------------------------
-- Server version	5.1.55

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
-- Table structure for table `account_details`
--

DROP TABLE IF EXISTS `account_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_details` (
  `acid` bigint(20) NOT NULL,
  `limit` int(11) DEFAULT NULL,
  PRIMARY KEY (`acid`),
  UNIQUE KEY `acid_UNIQUE` (`acid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_details`
--

LOCK TABLES `account_details` WRITE;
/*!40000 ALTER TABLE `account_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `current_location`
--

DROP TABLE IF EXISTS `current_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `current_location` (
  `clid` bigint(20) NOT NULL AUTO_INCREMENT,
  `ridid` bigint(20) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `placeid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`clid`),
  UNIQUE KEY `clid_UNIQUE` (`clid`),
  KEY `placeid` (`placeid`),
  CONSTRAINT `placeid` FOREIGN KEY (`placeid`) REFERENCES `places` (`plid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11161400011 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `current_location`
--

LOCK TABLES `current_location` WRITE;
/*!40000 ALTER TABLE `current_location` DISABLE KEYS */;
INSERT INTO `current_location` VALUES (11161400001,11161700038,17,78,'ram@kaarpool.com',NULL),(11161400002,11161700039,17,78,'ram@kaarpool.com',NULL),(11161400003,11161700001,17.44667,78.3772783333333,'ram@kaarpool.com',NULL),(11161400004,11161700001,17.408325,78.4721433333333,'raj@kaarpool.com',NULL),(11161400005,11161700002,17.44667,78.3772783333333,'ram@kaarpool.com',NULL),(11161400006,11161700038,17,78,'uid',NULL),(11161400007,11161700039,17,78,'uid',NULL),(11161400008,11161700001,17,78,'uid',NULL),(11161400009,11161700003,17.44667,78.3772783333333,'ram@kaarpool.com',NULL),(11161400010,11161700004,17.44667,78.3772783333333,'ram@kaarpool.com',NULL);
/*!40000 ALTER TABLE `current_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `journey_details`
--

DROP TABLE IF EXISTS `journey_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `journey_details` (
  `jid` bigint(20) NOT NULL AUTO_INCREMENT,
  `jsource` varchar(45) DEFAULT NULL,
  `jdestination` varchar(45) DEFAULT NULL,
  `userid` bigint(20) DEFAULT NULL,
  `locid` bigint(20) DEFAULT NULL,
  `stime` varchar(20) DEFAULT NULL,
  `etime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`jid`),
  UNIQUE KEY `jid_UNIQUE` (`jid`),
  KEY `locid` (`locid`),
  KEY `userid` (`userid`),
  CONSTRAINT `locid` FOREIGN KEY (`locid`) REFERENCES `location` (`lid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `userid` FOREIGN KEY (`userid`) REFERENCES `user_details` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11161600020 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `journey_details`
--

LOCK TABLES `journey_details` WRITE;
/*!40000 ALTER TABLE `journey_details` DISABLE KEYS */;
INSERT INTO `journey_details` VALUES (11161600001,'a','b',11160000002,NULL,'2011/6/7 5:59 PM',NULL),(11161600002,'a','b',11160000001,NULL,'2011/6/7 5:59 PM',NULL),(11161600003,'b','c',11160000002,NULL,'2011/6/7 6:9 PM',NULL),(11161600004,'b','c',11160000001,NULL,'2011/6/7 6:9 PM',NULL),(11161600005,'s','d',11160000002,NULL,'2011/6/7 6:25 PM',NULL),(11161600006,'s','d',11160000001,NULL,'2011/6/7 6:25 PM',NULL),(11161600007,'a','b',11160000002,NULL,'2011/6/7 6:30 PM',NULL),(11161600008,'a','b',11160000001,NULL,'2011/6/7 6:30 PM',NULL),(11161600009,'source','destination',11160000002,NULL,'2011/6/7 10:0 PM',NULL),(11161600010,'source','destination',11160000003,NULL,'2011/6/7 10:0 PM',NULL),(11161600015,'kk','ss',11160000004,NULL,'2011/6/9 3:38 PM',NULL),(11161600019,'source','destination',11160000001,NULL,'2011/6/9 10:0 PM',NULL);
/*!40000 ALTER TABLE `journey_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kaarpoolnetwork_details`
--

DROP TABLE IF EXISTS `kaarpoolnetwork_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kaarpoolnetwork_details` (
  `kid` bigint(20) NOT NULL AUTO_INCREMENT,
  `loginid` varchar(50) NOT NULL,
  `loginpwd` varchar(20) NOT NULL,
  `ksession` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`kid`)
) ENGINE=InnoDB AUTO_INCREMENT=11161000005 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kaarpoolnetwork_details`
--

LOCK TABLES `kaarpoolnetwork_details` WRITE;
/*!40000 ALTER TABLE `kaarpoolnetwork_details` DISABLE KEYS */;
INSERT INTO `kaarpoolnetwork_details` VALUES (11161000001,'raj@kaarpool.com','saven',1),(11161000002,'ram@kaarpool.com','saven',1),(11161000003,'bhargav@kaarpool.com','bhargav',1),(11161000004,'kanth@kaarpool.com','kanth',1);
/*!40000 ALTER TABLE `kaarpoolnetwork_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location` (
  `lid` bigint(20) NOT NULL AUTO_INCREMENT,
  `curlocid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`lid`),
  UNIQUE KEY `lid_UNIQUE` (`lid`),
  KEY `curlocid` (`curlocid`),
  CONSTRAINT `curlocid` FOREIGN KEY (`curlocid`) REFERENCES `current_location` (`clid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11161500001 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `msg_updates`
--

DROP TABLE IF EXISTS `msg_updates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `msg_updates` (
  `muid` bigint(20) NOT NULL AUTO_INCREMENT,
  `rdid` bigint(20) DEFAULT NULL,
  `ridername` varchar(50) NOT NULL,
  `drivername` varchar(50) NOT NULL,
  `messageid` varchar(3) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`muid`),
  UNIQUE KEY `msg_updatescol_UNIQUE` (`muid`),
  KEY `messageid` (`messageid`),
  CONSTRAINT `messageid` FOREIGN KEY (`messageid`) REFERENCES `msgformat` (`mfid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=111629000005 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `msg_updates`
--

LOCK TABLES `msg_updates` WRITE;
/*!40000 ALTER TABLE `msg_updates` DISABLE KEYS */;
INSERT INTO `msg_updates` VALUES (111629000001,11161700001,'raj@kaarpool.com','ram@kaarpool.com','r2','running'),(111629000002,11161700002,'raj@kaarpool.com','ram@kaarpool.com','r2','running'),(111629000003,11161700003,'raj@kaarpool.com','ram@kaarpool.com','r2','running'),(111629000004,11161700004,'raj@kaarpool.com','ram@kaarpool.com','r2','running');
/*!40000 ALTER TABLE `msg_updates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `msgformat`
--

DROP TABLE IF EXISTS `msgformat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `msgformat` (
  `mfid` varchar(3) NOT NULL,
  `message` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`mfid`),
  UNIQUE KEY `mfid_UNIQUE` (`mfid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `msgformat`
--

LOCK TABLES `msgformat` WRITE;
/*!40000 ALTER TABLE `msgformat` DISABLE KEYS */;
INSERT INTO `msgformat` VALUES ('d1','Driver accept'),('d2','Driver reject'),('d3','Driver confirmation'),('d4','Driver confirmation reject'),('d5','Driver pickup request'),('d6','Driver drop request'),('r1','Rider request'),('r2','Rider confirmation'),('r3','Rider confirmation reject'),('r4','Rider pickup confirmation'),('r5','Rider pickup reject'),('r6','Rider drop confirmation'),('r7','Rider rerequest');
/*!40000 ALTER TABLE `msgformat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `networkdetails`
--

DROP TABLE IF EXISTS `networkdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `networkdetails` (
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  `ndid` bigint(20) NOT NULL,
  PRIMARY KEY (`nid`),
  UNIQUE KEY `nid_UNIQUE` (`nid`),
  UNIQUE KEY `ndid_UNIQUE` (`ndid`)
) ENGINE=InnoDB AUTO_INCREMENT=11161200001 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `networkdetails`
--

LOCK TABLES `networkdetails` WRITE;
/*!40000 ALTER TABLE `networkdetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `networkdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal_details`
--

DROP TABLE IF EXISTS `personal_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personal_details` (
  `pid` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `dob` varchar(10) DEFAULT NULL,
  `mobile` mediumtext,
  `address` varchar(100) DEFAULT NULL,
  `gender` varchar(7) DEFAULT NULL,
  `image` blob,
  PRIMARY KEY (`pid`),
  UNIQUE KEY `pid_UNIQUE` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=11162000005 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_details`
--

LOCK TABLES `personal_details` WRITE;
/*!40000 ALTER TABLE `personal_details` DISABLE KEYS */;
INSERT INTO `personal_details` VALUES (11162000001,'raj@kaarpool.com','06-07-2011','09876543212','iiit','Male','/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAMCAgMCAgMDAwMEAwMEBQgFBQQEBQoHBwYIDAoMDAsK\nCwsNDhIQDQ4RDgsLEBYQERMUFRUVDA8XGBYUGBIUFRT/2wBDAQMEBAUEBQkFBQkUDQsNFBQUFBQU\nFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBT/wAARCAAoACgDASIA\nAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQA\nAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3\nODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWm\np6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEA\nAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSEx\nBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElK\nU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3\nuLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDn9I0r\nzNhx19BXWaZ4f3onyjpS+G9OD7MgH617F8LfAMfijWEtpmMdtEnmylRyVBxtHoTmv0+vXVOLlLZH\n5DhsO6rUY7s8wTw18oO0HueOlZd94f8ALUHGcE19tj4Z+F/sItP7GtzHjG453/8Afed2ffNeEfE3\nwDH4V1c20LtJayJ50LP1C5OVPqQQfzrzsNmEK8+TZnqYjLZ0Ic71Pm7U9J8phkDPNFdfr+nAAnGA\nM/1or24TdjwJQVzU8MbSY/Svevgvqy6Xq7h0f7PdRiJpVQlVYHK5PbuPxFfPvgHZqetaXYyMwS5u\nI4WKnnDMAce/Nfden6fb6ZaQW1pCsFvGu1Y0GABXzuZ1lCPs2r3Pp8poupL2qduUsKenArw74yas\nmparGI45fItkMIlaMqpbOTtJ6+n4V7gvaobuzg1C0ktrmFJ4JVKSRyKCrj0NfPYer7Cana59HiKL\nr03BOx8NeJDn8jRSfERI9K8RarYQkmK2upYV3ckhWIGT+FFffUo88E0fnlR8s2mc58KtR3+OPDSZ\n66jAB/38Wv0R7L9KKK+aznSrH0/Vn0+QtunU9UIvb8aRcYH40UV88fTr+vwPz2+Lmp7fiD4kTONu\noXAHt85ooor9Ow0V7Jei/I/JcRJ+1l6v8z//2Q=='),(11162000002,'ram@kaarpool.com','06-07-2011','09876543211','my home sarovar plaza','Male','/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAMCAgMCAgMDAwMEAwMEBQgFBQQEBQoHBwYIDAoMDAsK\nCwsNDhIQDQ4RDgsLEBYQERMUFRUVDA8XGBYUGBIUFRT/2wBDAQMEBAUEBQkFBQkUDQsNFBQUFBQU\nFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBT/wAARCAAoACgDASIA\nAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQA\nAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3\nODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWm\np6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEA\nAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSEx\nBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElK\nU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3\nuLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDn9I0r\nzNhx19BXWaZ4f3onyjpS+G9OD7MgH617F8LfAMfijWEtpmMdtEnmylRyVBxtHoTmv0+vXVOLlLZH\n5DhsO6rUY7s8wTw18oO0HueOlZd94f8ALUHGcE19tj4Z+F/sItP7GtzHjG453/8Afed2ffNeEfE3\nwDH4V1c20LtJayJ50LP1C5OVPqQQfzrzsNmEK8+TZnqYjLZ0Ic71Pm7U9J8phkDPNFdfr+nAAnGA\nM/1or24TdjwJQVzU8MbSY/Svevgvqy6Xq7h0f7PdRiJpVQlVYHK5PbuPxFfPvgHZqetaXYyMwS5u\nI4WKnnDMAce/Nfden6fb6ZaQW1pCsFvGu1Y0GABXzuZ1lCPs2r3Pp8poupL2qduUsKenArw74yas\nmparGI45fItkMIlaMqpbOTtJ6+n4V7gvaobuzg1C0ktrmFJ4JVKSRyKCrj0NfPYer7Cana59HiKL\nr03BOx8NeJDn8jRSfERI9K8RarYQkmK2upYV3ckhWIGT+FFffUo88E0fnlR8s2mc58KtR3+OPDSZ\n66jAB/38Wv0R7L9KKK+aznSrH0/Vn0+QtunU9UIvb8aRcYH40UV88fTr+vwPz2+Lmp7fiD4kTONu\noXAHt85ooor9Ow0V7Jei/I/JcRJ+1l6v8z//2Q=='),(11162000003,'bhargav@kaarpool.com','06-07-2011','09876543211','iiit','Male','/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAMCAgMCAgMDAwMEAwMEBQgFBQQEBQoHBwYIDAoMDAsK\nCwsNDhIQDQ4RDgsLEBYQERMUFRUVDA8XGBYUGBIUFRT/2wBDAQMEBAUEBQkFBQkUDQsNFBQUFBQU\nFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBT/wAARCAAoACgDASIA\nAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQA\nAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3\nODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWm\np6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEA\nAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSEx\nBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElK\nU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3\nuLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDn9I0r\nzNhx19BXWaZ4f3onyjpS+G9OD7MgH617F8LfAMfijWEtpmMdtEnmylRyVBxtHoTmv0+vXVOLlLZH\n5DhsO6rUY7s8wTw18oO0HueOlZd94f8ALUHGcE19tj4Z+F/sItP7GtzHjG453/8Afed2ffNeEfE3\nwDH4V1c20LtJayJ50LP1C5OVPqQQfzrzsNmEK8+TZnqYjLZ0Ic71Pm7U9J8phkDPNFdfr+nAAnGA\nM/1or24TdjwJQVzU8MbSY/Svevgvqy6Xq7h0f7PdRiJpVQlVYHK5PbuPxFfPvgHZqetaXYyMwS5u\nI4WKnnDMAce/Nfden6fb6ZaQW1pCsFvGu1Y0GABXzuZ1lCPs2r3Pp8poupL2qduUsKenArw74yas\nmparGI45fItkMIlaMqpbOTtJ6+n4V7gvaobuzg1C0ktrmFJ4JVKSRyKCrj0NfPYer7Cana59HiKL\nr03BOx8NeJDn8jRSfERI9K8RarYQkmK2upYV3ckhWIGT+FFffUo88E0fnlR8s2mc58KtR3+OPDSZ\n66jAB/38Wv0R7L9KKK+aznSrH0/Vn0+QtunU9UIvb8aRcYH40UV88fTr+vwPz2+Lmp7fiD4kTONu\noXAHt85ooor9Ow0V7Jei/I/JcRJ+1l6v8z//2Q=='),(11162000004,'kanth@kaarpool.com','06-07-2011','09876543212','iiit','Male','/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAMCAgMCAgMDAwMEAwMEBQgFBQQEBQoHBwYIDAoMDAsK\nCwsNDhIQDQ4RDgsLEBYQERMUFRUVDA8XGBYUGBIUFRT/2wBDAQMEBAUEBQkFBQkUDQsNFBQUFBQU\nFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBT/wAARCAAoACgDASIA\nAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQA\nAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3\nODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWm\np6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEA\nAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSEx\nBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElK\nU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3\nuLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDn9I0r\nzNhx19BXWaZ4f3onyjpS+G9OD7MgH617F8LfAMfijWEtpmMdtEnmylRyVBxtHoTmv0+vXVOLlLZH\n5DhsO6rUY7s8wTw18oO0HueOlZd94f8ALUHGcE19tj4Z+F/sItP7GtzHjG453/8Afed2ffNeEfE3\nwDH4V1c20LtJayJ50LP1C5OVPqQQfzrzsNmEK8+TZnqYjLZ0Ic71Pm7U9J8phkDPNFdfr+nAAnGA\nM/1or24TdjwJQVzU8MbSY/Svevgvqy6Xq7h0f7PdRiJpVQlVYHK5PbuPxFfPvgHZqetaXYyMwS5u\nI4WKnnDMAce/Nfden6fb6ZaQW1pCsFvGu1Y0GABXzuZ1lCPs2r3Pp8poupL2qduUsKenArw74yas\nmparGI45fItkMIlaMqpbOTtJ6+n4V7gvaobuzg1C0ktrmFJ4JVKSRyKCrj0NfPYer7Cana59HiKL\nr03BOx8NeJDn8jRSfERI9K8RarYQkmK2upYV3ckhWIGT+FFffUo88E0fnlR8s2mc58KtR3+OPDSZ\n66jAB/38Wv0R7L9KKK+aznSrH0/Vn0+QtunU9UIvb8aRcYH40UV88fTr+vwPz2+Lmp7fiD4kTONu\noXAHt85ooor9Ow0V7Jei/I/JcRJ+1l6v8z//2Q==');
/*!40000 ALTER TABLE `personal_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `places`
--

DROP TABLE IF EXISTS `places`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `places` (
  `plid` bigint(20) NOT NULL AUTO_INCREMENT,
  `place` varchar(50) DEFAULT NULL,
  `place_lat` double DEFAULT NULL,
  `place_lon` double DEFAULT NULL,
  PRIMARY KEY (`plid`),
  UNIQUE KEY `plid_UNIQUE` (`plid`)
) ENGINE=InnoDB AUTO_INCREMENT=11161300001 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `places`
--

LOCK TABLES `places` WRITE;
/*!40000 ALTER TABLE `places` DISABLE KEYS */;
/*!40000 ALTER TABLE `places` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preferences`
--

DROP TABLE IF EXISTS `preferences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `preferences` (
  `prefid` bigint(20) NOT NULL AUTO_INCREMENT,
  `tbdid` bigint(20) DEFAULT NULL,
  `travelid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`prefid`),
  UNIQUE KEY `prefid_UNIQUE` (`prefid`),
  KEY `tbdid` (`tbdid`),
  KEY `travelid` (`travelid`),
  CONSTRAINT `tbdid` FOREIGN KEY (`tbdid`) REFERENCES `timebased_defaultloc` (`tbid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `travelid` FOREIGN KEY (`travelid`) REFERENCES `ridepreferences` (`trid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11165000001 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preferences`
--

LOCK TABLES `preferences` WRITE;
/*!40000 ALTER TABLE `preferences` DISABLE KEYS */;
/*!40000 ALTER TABLE `preferences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ride`
--

DROP TABLE IF EXISTS `ride`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ride` (
  `rid` bigint(20) NOT NULL AUTO_INCREMENT,
  `jdid` bigint(20) DEFAULT NULL,
  `seats` int(11) DEFAULT NULL,
  `routename` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`rid`),
  KEY `jdid` (`jdid`),
  CONSTRAINT `jdid` FOREIGN KEY (`jdid`) REFERENCES `journey_details` (`jid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11161700015 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ride`
--

LOCK TABLES `ride` WRITE;
/*!40000 ALTER TABLE `ride` DISABLE KEYS */;
INSERT INTO `ride` VALUES (11161700001,11161600001,2,'Route'),(11161700002,11161600003,2,'Route'),(11161700003,11161600005,2,'Route'),(11161700004,11161600007,2,'Route'),(11161700005,11161600009,2,'Route'),(11161700006,11161600010,2,'Route'),(11161700011,11161600015,2,'Route');
/*!40000 ALTER TABLE `ride` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ride_members`
--

DROP TABLE IF EXISTS `ride_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ride_members` (
  `rideid` bigint(20) DEFAULT NULL,
  `usrid` bigint(20) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ride_members`
--

LOCK TABLES `ride_members` WRITE;
/*!40000 ALTER TABLE `ride_members` DISABLE KEYS */;
INSERT INTO `ride_members` VALUES (11161700001,11160000002,'driver'),(11161700002,11160000002,'driver'),(11161700003,11160000002,'driver'),(11161700004,11160000002,'driver'),(11161700005,11160000002,'driver'),(11161700006,11160000003,'driver'),(11161700007,11160000004,'driver'),(11161700008,11160000004,'driver'),(11161700009,11160000004,'driver'),(11161700010,11160000004,'driver'),(11161700011,11160000004,'driver'),(11161700012,11160000004,'driver'),(11161700013,11160000004,'driver'),(11161700014,11160000004,'driver');
/*!40000 ALTER TABLE `ride_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ridepreferences`
--

DROP TABLE IF EXISTS `ridepreferences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ridepreferences` (
  `trid` bigint(20) NOT NULL AUTO_INCREMENT,
  `ladies` varchar(1) DEFAULT NULL,
  `gents` varchar(1) DEFAULT NULL,
  `music` varchar(1) DEFAULT NULL,
  `smoke` varchar(1) DEFAULT NULL,
  `children` varchar(1) DEFAULT NULL,
  `handicap` varchar(1) DEFAULT NULL,
  `seatavail` varchar(11) DEFAULT NULL,
  `carimg` blob,
  PRIMARY KEY (`trid`),
  UNIQUE KEY `trid_UNIQUE` (`trid`)
) ENGINE=InnoDB AUTO_INCREMENT=11163000001 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ridepreferences`
--

LOCK TABLES `ridepreferences` WRITE;
/*!40000 ALTER TABLE `ridepreferences` DISABLE KEYS */;
/*!40000 ALTER TABLE `ridepreferences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socialnetwork_details`
--

DROP TABLE IF EXISTS `socialnetwork_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `socialnetwork_details` (
  `sid` bigint(20) NOT NULL AUTO_INCREMENT,
  `loginid` varchar(50) NOT NULL,
  `loginpwd` varchar(20) NOT NULL,
  `account_type` varchar(10) DEFAULT NULL,
  `session` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=11161100001 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socialnetwork_details`
--

LOCK TABLES `socialnetwork_details` WRITE;
/*!40000 ALTER TABLE `socialnetwork_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `socialnetwork_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timebased_defaultloc`
--

DROP TABLE IF EXISTS `timebased_defaultloc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `timebased_defaultloc` (
  `tbid` bigint(20) NOT NULL AUTO_INCREMENT,
  `days` varchar(45) DEFAULT NULL,
  `source` varchar(45) DEFAULT NULL,
  `destination` varchar(45) DEFAULT NULL,
  `startime` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`tbid`),
  UNIQUE KEY `tbid_UNIQUE` (`tbid`)
) ENGINE=InnoDB AUTO_INCREMENT=11164000001 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timebased_defaultloc`
--

LOCK TABLES `timebased_defaultloc` WRITE;
/*!40000 ALTER TABLE `timebased_defaultloc` DISABLE KEYS */;
/*!40000 ALTER TABLE `timebased_defaultloc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactions` (
  `tid` bigint(20) NOT NULL AUTO_INCREMENT,
  `sender` varchar(50) NOT NULL,
  `receiver` varchar(50) NOT NULL,
  `amount` double DEFAULT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`tid`),
  UNIQUE KEY `Transactions_UNIQUE` (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=1116180001 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_details`
--

DROP TABLE IF EXISTS `user_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_details` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT,
  `prdid` bigint(20) DEFAULT NULL,
  `accid` bigint(20) DEFAULT NULL,
  `netid` bigint(20) DEFAULT NULL,
  `modeid` int(11) DEFAULT NULL,
  `preid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid_UNIQUE` (`uid`),
  KEY `prdid` (`prdid`),
  KEY `accid` (`accid`),
  KEY `mode` (`modeid`),
  KEY `preid` (`preid`),
  CONSTRAINT `prdid` FOREIGN KEY (`prdid`) REFERENCES `personal_details` (`pid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `accid` FOREIGN KEY (`accid`) REFERENCES `account_details` (`acid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `mode` FOREIGN KEY (`modeid`) REFERENCES `usermode` (`mid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `preid` FOREIGN KEY (`preid`) REFERENCES `preferences` (`prefid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11160000005 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_details`
--

LOCK TABLES `user_details` WRITE;
/*!40000 ALTER TABLE `user_details` DISABLE KEYS */;
INSERT INTO `user_details` VALUES (11160000001,11162000001,NULL,11161000001,2,NULL),(11160000002,11162000002,NULL,11161000002,2,NULL),(11160000003,11162000003,NULL,11161000003,1,NULL),(11160000004,11162000004,NULL,11161000004,1,NULL);
/*!40000 ALTER TABLE `user_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usermode`
--

DROP TABLE IF EXISTS `usermode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usermode` (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `mode` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`mid`),
  UNIQUE KEY `mid_UNIQUE` (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usermode`
--

LOCK TABLES `usermode` WRITE;
/*!40000 ALTER TABLE `usermode` DISABLE KEYS */;
INSERT INTO `usermode` VALUES (1,'driver'),(2,'rider');
/*!40000 ALTER TABLE `usermode` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-06-10 12:37:50
