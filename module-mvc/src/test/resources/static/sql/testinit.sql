-- MariaDB dump 10.19  Distrib 10.8.3-MariaDB, for osx10.17 (arm64)
--
-- Host: 127.0.0.1    Database: kotilnblog
-- ------------------------------------------------------
-- Server version	10.8.3-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `BatchMember`
--

SET foreign_key_checks = 0;

DROP TABLE IF EXISTS `BatchMember`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BatchMember` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_at` datetime(6) NOT NULL,
  `delete_at` datetime(6) DEFAULT NULL,
  `order_no` bigint(20) DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `postTitles` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BatchMember`
--

LOCK TABLES `BatchMember` WRITE;
/*!40000 ALTER TABLE `BatchMember` DISABLE KEYS */;
/*!40000 ALTER TABLE `BatchMember` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Comment`
--

DROP TABLE IF EXISTS `Comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_at` datetime(6) NOT NULL,
  `delete_at` datetime(6) DEFAULT NULL,
  `order_no` bigint(20) DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `content` varchar(1000) NOT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKabtb4m4ck0139ovka21brw3au` (`member_id`),
  KEY `FKk8lsulc0to0gje7apnpwobb1v` (`post_id`),
  CONSTRAINT `FKabtb4m4ck0139ovka21brw3au` FOREIGN KEY (`member_id`) REFERENCES `Member` (`id`),
  CONSTRAINT `FKk8lsulc0to0gje7apnpwobb1v` FOREIGN KEY (`post_id`) REFERENCES `Post` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Comment`
--

LOCK TABLES `Comment` WRITE;
/*!40000 ALTER TABLE `Comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `Comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Comment_closure`
--

DROP TABLE IF EXISTS `Comment_closure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Comment_closure` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_at` datetime(6) NOT NULL,
  `delete_at` datetime(6) DEFAULT NULL,
  `order_no` bigint(20) DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `depth` int(11) DEFAULT NULL,
  `id_ancestor` bigint(20) DEFAULT NULL,
  `id_descendant` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKtr622y47hwaa2v1squik3benq` (`id_ancestor`,`id_descendant`),
  KEY `FKe6rni3fwtwv7tbn3b1ktwg8pa` (`id_descendant`),
  CONSTRAINT `FKe6rni3fwtwv7tbn3b1ktwg8pa` FOREIGN KEY (`id_descendant`) REFERENCES `Comment` (`id`),
  CONSTRAINT `FKfcjvp9lsc0a6ocp78lp2f0vgb` FOREIGN KEY (`id_ancestor`) REFERENCES `Comment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Comment_closure`
--

LOCK TABLES `Comment_closure` WRITE;
/*!40000 ALTER TABLE `Comment_closure` DISABLE KEYS */;
/*!40000 ALTER TABLE `Comment_closure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Member`
--

DROP TABLE IF EXISTS `Member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_at` datetime(6) NOT NULL,
  `delete_at` datetime(6) DEFAULT NULL,
  `order_no` bigint(20) DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=201 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Member`
--

LOCK TABLES `Member` WRITE;
/*!40000 ALTER TABLE `Member` DISABLE KEYS */;
INSERT INTO `Member` (`id`, `create_at`, `delete_at`, `order_no`, `update_at`, `email`, `password`, `role`) VALUES (1,'2022-11-05 15:18:46.646434',NULL,NULL,'2022-11-05 15:18:46.646434','lenard.thompson@yahoo.test','$2a$10$Mur1iTYTaAjsNmViA.kk4u..ozKA7fRK5OTMB1Lo4V2fcqiKa/s5q','USER'),
(2,'2022-11-05 15:18:46.675972',NULL,NULL,'2022-11-05 15:18:46.675972','zona.parker@yahoo.test','$2a$10$kUBL5ffFd.fe.zvURxaIF.IxV5vsUM9W31UC7BPUYM0hEhWp1psMC','USER'),
(3,'2022-11-05 15:18:46.678282',NULL,NULL,'2022-11-05 15:18:46.678282','eli.kulas@gmail.test','$2a$10$Ou3OMiz8iadzxP7khkkf6eH1D1fcz62GAwjOn1wcM6JpYEViVFHfC','USER'),
(4,'2022-11-05 15:18:46.679526',NULL,NULL,'2022-11-05 15:18:46.679526','ms.rufus.cruickshank@hotmail.test','$2a$10$/G2Fatk193j4DZ23RJp1ZeEw6iqpjRcKBl6efH78cxN./0CUTdU86','USER'),
(5,'2022-11-05 15:18:46.680690',NULL,NULL,'2022-11-05 15:18:46.680690','mr.fred.greenholt@hotmail.test','$2a$10$o07DAirebnWW2NJ4uNcgY.LEcuFDJP7VUh3zPIDNreT9roHqO5tES','USER'),
(6,'2022-11-05 15:18:46.681966',NULL,NULL,'2022-11-05 15:18:46.681966','ms.fred.schimmel@hotmail.test','$2a$10$kSJKrfJJl20hWQWGxdOmBOS6Bcsf82INaLPN9DECikTQv4rHpKXMS','USER'),
(7,'2022-11-05 15:18:46.683447',NULL,NULL,'2022-11-05 15:18:46.683447','leonarda.lehner@gmail.test','$2a$10$a2rDBvAJB/Kw6a2mPqaaEuIiHL0gNhlU6KSTf9FfDSiDhixhSpmWC','USER'),
(8,'2022-11-05 15:18:46.684917',NULL,NULL,'2022-11-05 15:18:46.684917','mabelle.borer@gmail.test','$2a$10$d/41ZpksUF/UB3sUhXsv/./yTPddsTlXS6bNUPx2zMT.0yqrQT7ny','USER'),
(9,'2022-11-05 15:18:46.686156',NULL,NULL,'2022-11-05 15:18:46.686156','sheena.reilly@yahoo.test','$2a$10$gpiaVkwQpQtEEmop1ExE7O035RZgmEpM6VUGWHGS7mKzARMbquH1G','USER'),
(10,'2022-11-05 15:18:46.687227',NULL,NULL,'2022-11-05 15:18:46.687227','amb.janetta.nienow@gmail.test','$2a$10$STuNVzxAWPWKYeB5iGwMvuZuusUSuJl/B5bmZTXgp8atsm16b3Jxa','USER'),
(11,'2022-11-05 15:18:46.688308',NULL,NULL,'2022-11-05 15:18:46.688308','shara.sanford@yahoo.test','$2a$10$uBx2mTB0iEZX2TLah7y7yeBnMIq3ktIjNZu.sCgYLoRYfEqaygDQi','USER'),
(12,'2022-11-05 15:18:46.689440',NULL,NULL,'2022-11-05 15:18:46.689440','tyson.windler.i@gmail.test','$2a$10$i9u1Y7rekp4DGhju3ut2kOsrZNNjr4jw2IfFWvIc9zoPPWiJuACOO','USER'),
(13,'2022-11-05 15:18:46.690506',NULL,NULL,'2022-11-05 15:18:46.690506','cornelius.adams@yahoo.test','$2a$10$mGhtD4b4LSFZJlc/d2Qlau60O/FeQ7RMRUVNOUcy.LSJ5JmYE5.mu','USER'),
(14,'2022-11-05 15:18:46.691851',NULL,NULL,'2022-11-05 15:18:46.691851','fran.graham@yahoo.test','$2a$10$g7JstgDWgQyynWDMtfs8GOoCU8VR9wPDNfxgwJG9koHaHX46y5dXy','USER'),
(15,'2022-11-05 15:18:46.692997',NULL,NULL,'2022-11-05 15:18:46.692997','jeanett.prohaska.iii@hotmail.test','$2a$10$EpI26/t9l7TI8W5fJJJM5Oa7D8FJyZCk779iErbSrQC1JFLrbVSwy','USER'),
(16,'2022-11-05 15:18:46.694380',NULL,NULL,'2022-11-05 15:18:46.694380','dudley.fritsch.v@gmail.test','$2a$10$wfhuCbI4zavGaLO9BGp64.rvPG5FSupdPewrr3c4WX.4HHWIglUwS','USER'),
(17,'2022-11-05 15:18:46.695856',NULL,NULL,'2022-11-05 15:18:46.695856','codi.cummerata@gmail.test','$2a$10$cLCiwver8laXNgoN7gstC.PCpDZuYD0BTPPIS5r1O3aY5udwRRMVS','USER'),
(18,'2022-11-05 15:18:46.696969',NULL,NULL,'2022-11-05 15:18:46.696969','kathrine.leffler@hotmail.test','$2a$10$hH5SCCxTae6Wmy1nkWTurui8qdkyXwUj4La43/u4q4GGbUSMO1h.q','USER'),
(19,'2022-11-05 15:18:46.698631',NULL,NULL,'2022-11-05 15:18:46.698631','lena.davis.v@gmail.test','$2a$10$OVSMq/j53fbPPikUJ7uRNOBIFSNQ3KfrIZ8hpdjdAzzJuW4Z2rDmu','USER'),
(20,'2022-11-05 15:18:46.699579',NULL,NULL,'2022-11-05 15:18:46.699579','normand.willms.dvm@gmail.test','$2a$10$HzKkK.nSYuaEUoISWNr8juvjjxgECaTBq0vajggyvkIBHH16kp/p.','USER'),
(21,'2022-11-05 15:18:46.700406',NULL,NULL,'2022-11-05 15:18:46.700406','msgr.seymour.pfannerstill@hotmail.test','$2a$10$UHmRs7EHhU4C3Ij8WlAwh.t9W9F3YgIEqG5GniAvR/EsSzvHmRWkq','USER'),
(22,'2022-11-05 15:18:46.701548',NULL,NULL,'2022-11-05 15:18:46.701548','freeman.bradtke@gmail.test','$2a$10$XRdoo5AiDQzQ3myN1/NeuuvFo2MIxkrWKJjtfvvEudjKWc/jxg1iK','USER'),
(23,'2022-11-05 15:18:46.702578',NULL,NULL,'2022-11-05 15:18:46.702578','walter.langworth@yahoo.test','$2a$10$.sZhBlqKM8NPp.vi7rCoCON5nfj7.0bWJA2.D5hKKTlaEzCCPMDmu','USER'),
(24,'2022-11-05 15:18:46.703503',NULL,NULL,'2022-11-05 15:18:46.703503','ted.kuhic@yahoo.test','$2a$10$r7Yu0NXqqUk9asmlQp0qG.NTIBnT1lo/sSXA37uaqTt0wmEnynPQ2','USER'),
(25,'2022-11-05 15:18:46.704315',NULL,NULL,'2022-11-05 15:18:46.704315','boyce.kunze.lld@hotmail.test','$2a$10$VS9h9lgRc.0zpC0pD0RTWeWRBfnkkf3IxgCosborhDXZvEIHCq7RS','USER'),
(26,'2022-11-05 15:18:46.705301',NULL,NULL,'2022-11-05 15:18:46.705301','randall.hessel@yahoo.test','$2a$10$jkYD6GOlZS13W0QTvWlzOeWLcpZqtNlvWVSYO3Yf4GDvi7L0j9rWO','USER'),
(27,'2022-11-05 15:18:46.706266',NULL,NULL,'2022-11-05 15:18:46.706266','wes.mcclure@yahoo.test','$2a$10$vD5BVIsMMb79UNz7CMMHZ.xYaomSE19Fj9V3dSw76WCmETPRt8HcG','USER'),
(28,'2022-11-05 15:18:46.707229',NULL,NULL,'2022-11-05 15:18:46.707229','mary.greenfelder@gmail.test','$2a$10$5uTW0zyhI1Ue4q11Q4W.4OP4r2sAYpuczawcb.Ms2MDeemR1bm0ZS','USER'),
(29,'2022-11-05 15:18:46.708900',NULL,NULL,'2022-11-05 15:18:46.708900','rosario.cassin@yahoo.test','$2a$10$qcOrcX6ThYr89V6mZo/6ueUcw0o6E3G/BaEJAXKUxm/lPby8Aykmy','USER'),
(30,'2022-11-05 15:18:46.711953',NULL,NULL,'2022-11-05 15:18:46.711953','marcella.friesen@gmail.test','$2a$10$6K2BqfYV1nIMD/l3HwgDqeEijJV8YKbLL0AUYzbHmfJI7BmJFjGZy','USER'),
(31,'2022-11-05 15:18:46.714197',NULL,NULL,'2022-11-05 15:18:46.714197','javier.murphy.do@gmail.test','$2a$10$PPjyY4Eb5dvTRkWJMqQKVOnpqUGZZF.XEWZyPqGZjQN6wJEHuU3de','USER'),
(32,'2022-11-05 15:18:46.715354',NULL,NULL,'2022-11-05 15:18:46.715354','dudley.conroy.ii@yahoo.test','$2a$10$QpPj.qpqh0JcK89cQCzoIe3eLbavyef1SUuOHD3SFZZPmJOfgG2NS','USER'),
(33,'2022-11-05 15:18:46.716457',NULL,NULL,'2022-11-05 15:18:46.716457','mrs.georgie.turner@hotmail.test','$2a$10$LIQTgp.uNfs32VAC/xUHMOonKnc0KdwPtrYvKSsaGUanJeGj1y4Pe','USER'),
(34,'2022-11-05 15:18:46.717678',NULL,NULL,'2022-11-05 15:18:46.717678','india.zboncak.iii@hotmail.test','$2a$10$AI3w16x5wUdWZW4.xxjDjeAasbPgQmec54MR5co7ib27xSIgqi15y','USER'),
(35,'2022-11-05 15:18:46.718784',NULL,NULL,'2022-11-05 15:18:46.718784','carlos.padberg@yahoo.test','$2a$10$f/lQFK1SbZR5X0eGIAWCxu5iWkyBPR7Ax5tsxpSxRswl0odeE4G4G','USER'),
(36,'2022-11-05 15:18:46.719809',NULL,NULL,'2022-11-05 15:18:46.719809','jesse.brekke@gmail.test','$2a$10$gUQltZutOldeQit8OLZajeVE8ajC/GzqJ3aikkXEZ8ZyynJfcbCTC','USER'),
(37,'2022-11-05 15:18:46.720688',NULL,NULL,'2022-11-05 15:18:46.720688','dion.adams@hotmail.test','$2a$10$P0zbNengYvNfAMdi2EEiK.SGoGoy2zagr7eAVbDRRO98e7bxFzR8i','USER'),
(38,'2022-11-05 15:18:46.721695',NULL,NULL,'2022-11-05 15:18:46.721695','thaddeus.weber@gmail.test','$2a$10$dYNCDWuZdEX6OUVE/DSjCu1Rf5v6GIbIkA5pyHx83TmdPyEbOi/xe','USER'),
(39,'2022-11-05 15:18:46.724405',NULL,NULL,'2022-11-05 15:18:46.724405','miss.willie.wolff@yahoo.test','$2a$10$1hSc6dsfzZeXkOMcPsQ8Q.ZFKi7B2o5ehBGIqjRWxXmFVv98Qrs.O','USER'),
(40,'2022-11-05 15:18:46.725916',NULL,NULL,'2022-11-05 15:18:46.725916','brady.torp@hotmail.test','$2a$10$kbzbN1T6MzgYKK5HzWgTiu2EZjAUrXEVvGHg6273CFKyGeseXObTy','USER'),
(41,'2022-11-05 15:18:46.726906',NULL,NULL,'2022-11-05 15:18:46.726906','heike.reilly@hotmail.test','$2a$10$mTXEoCfnmT7qx1xokgNBA.QkPYVBBY9aSsWQcmOzdVa9rXrjcxJli','USER'),
(42,'2022-11-05 15:18:46.728367',NULL,NULL,'2022-11-05 15:18:46.728367','sheena.haag@yahoo.test','$2a$10$WakpEhSRJYW7fFwRiNbVaunOZCbrQS1d1O61G6yMhgUEXoBj6LHFu','USER'),
(43,'2022-11-05 15:18:46.729940',NULL,NULL,'2022-11-05 15:18:46.729940','tiffanie.roberts@yahoo.test','$2a$10$au2VNePcjfEmM2emQIOk1OgM5JVd7uHVZgrv3uy22NIgRekLNhypW','USER'),
(44,'2022-11-05 15:18:46.731217',NULL,NULL,'2022-11-05 15:18:46.731217','fernando.waters@gmail.test','$2a$10$VpqkpXReR/75Vx13lwYT.Ozs7gFGsQ0uzdDzuMwKmGWz/YFJ/KN2K','USER'),
(45,'2022-11-05 15:18:46.732235',NULL,NULL,'2022-11-05 15:18:46.732235','marion.romaguera@yahoo.test','$2a$10$H3CjaHK5mCmgByA9cEgmp.t//3XCIuKk9gPhnEs9650J3cfpLeULe','USER'),
(46,'2022-11-05 15:18:46.733255',NULL,NULL,'2022-11-05 15:18:46.733255','willow.langosh@hotmail.test','$2a$10$X1R2XRLa5vcEj0wyCP4Tb.85SHdnrH3QNTmFrPeEzEPImsZ0PQlom','USER'),
(47,'2022-11-05 15:18:46.734141',NULL,NULL,'2022-11-05 15:18:46.734141','felisha.marquardt@gmail.test','$2a$10$j.oObw3SxWmXP0OW1ijFuOjyrA838vJVf43NPK6VoDl0tMXzT5UQ.','USER'),
(48,'2022-11-05 15:18:46.735096',NULL,NULL,'2022-11-05 15:18:46.735096','hans.mckenzie@gmail.test','$2a$10$hiSXZ3B4wvyVP2Cd3rGf3eRLp2pARAQlxmgqliJGT4F3E378qL/M6','USER'),
(49,'2022-11-05 15:18:46.736325',NULL,NULL,'2022-11-05 15:18:46.736325','thomasina.bartell@hotmail.test','$2a$10$vPD.v1GrbwK4FiXGFMnPpu4SrtJLNc059vxNM5zINgUqAv7ArD/6C','USER'),
(50,'2022-11-05 15:18:46.737389',NULL,NULL,'2022-11-05 15:18:46.737389','clark.bednar@hotmail.test','$2a$10$7flvLJLqA.mrS.AbQabwG.O9R4tuIydf8Dr2dDv3HVUEK4IrrghD.','USER'),
(51,'2022-11-05 15:18:46.738769',NULL,NULL,'2022-11-05 15:18:46.738769','fr.brendon.gerlach@gmail.test','$2a$10$m7WfZECdesL8E2bW5AjYAOYn9jLxp5UqDWYna1gCb0Pjc7st2taKS','USER'),
(52,'2022-11-05 15:18:46.739831',NULL,NULL,'2022-11-05 15:18:46.739831','nichole.rogahn@hotmail.test','$2a$10$7bASJTq2Xr5AEbptwgl6Luvz1elHCBvLcuSgApF6fN0K4FlcNFcEC','USER'),
(53,'2022-11-05 15:18:46.741089',NULL,NULL,'2022-11-05 15:18:46.741089','manuel.harris.jr@yahoo.test','$2a$10$xz6MvMOlP233A9tQbRVR9OIMonZ.3jizvvrgywpT0r.XoQIfrgD5O','USER'),
(54,'2022-11-05 15:18:46.742243',NULL,NULL,'2022-11-05 15:18:46.742243','thomas.konopelski@hotmail.test','$2a$10$94.0riCs3HWuaWnkJu3Bn.jcnDmbBwBUhzOA5LEQi0ouuZZWO7twW','USER'),
(55,'2022-11-05 15:18:46.743338',NULL,NULL,'2022-11-05 15:18:46.743338','vance.simonis@hotmail.test','$2a$10$UEn6ELnA6k9uhVK3cKxd0uBFg20p0Ohf9e8U8.InqSgbR3mf8zsMO','USER'),
(56,'2022-11-05 15:18:46.744769',NULL,NULL,'2022-11-05 15:18:46.744769','cleo.ortiz@gmail.test','$2a$10$lMBAgHxO86Fvu8t5Lc9eMOP6nw.oRh7/oqZVRrND57p3uYMJpamZO','USER'),
(57,'2022-11-05 15:18:46.745612',NULL,NULL,'2022-11-05 15:18:46.745612','terence.anderson@gmail.test','$2a$10$jSsn0U2sV5XuY//hE815lO5dXfyrjNbhtPSn5rY7d6KkeDrtuRmRe','USER'),
(58,'2022-11-05 15:18:46.746506',NULL,NULL,'2022-11-05 15:18:46.746506','gilbert.schmitt.dvm@gmail.test','$2a$10$EC3qLT8T8Qdr/Wn4NE5iYebmZE31dzwjN1Jhzrs0htuoPd5YF.C62','USER'),
(59,'2022-11-05 15:18:46.747295',NULL,NULL,'2022-11-05 15:18:46.747295','anisa.swaniawski@gmail.test','$2a$10$2Q3Llv70XkOkRcoDOgeReeNm1cVDmxCnDmvYv1zHD02aFtbLu.BBW','USER'),
(60,'2022-11-05 15:18:46.747988',NULL,NULL,'2022-11-05 15:18:46.747988','monique.rodriguez@gmail.test','$2a$10$v3ONPn9HxbYEzcN9HgWmd.u2UkMR0OkMobtxwOAiHK9lu6enw4g4S','USER'),
(61,'2022-11-05 15:18:46.748795',NULL,NULL,'2022-11-05 15:18:46.748795','nickole.mcglynn@hotmail.test','$2a$10$l8bXmNv/uj5RclMnFAD.LOltBfl3ec3LKPwBN5fZUBGRhnxKlJ//G','USER'),
(62,'2022-11-05 15:18:46.749965',NULL,NULL,'2022-11-05 15:18:46.749965','miss.nga.mraz@hotmail.test','$2a$10$czDQcUTmpNtikxIeSuW0ieNNmdUEpifyxtJWXdOToIJUG0dR3Y0Ky','USER'),
(63,'2022-11-05 15:18:46.750959',NULL,NULL,'2022-11-05 15:18:46.750959','cristal.hartmann.do@yahoo.test','$2a$10$duwHrmwtBGlnfaCvf9/zuO/deEQ2r39ntvfCBcI4bM8vfzBgWuGla','USER'),
(64,'2022-11-05 15:18:46.751960',NULL,NULL,'2022-11-05 15:18:46.751960','gov.kayleen.durgan@yahoo.test','$2a$10$tzKwMu4z1gAzJxarwaFqnuwQmyUxxT4G8D7jB6xRP09GW32TSDsl.','USER'),
(65,'2022-11-05 15:18:46.753166',NULL,NULL,'2022-11-05 15:18:46.753166','elli.olson@hotmail.test','$2a$10$IPu.vYOOP9/CvtxfT9PjpObROxLqPM7NECyS4/3CT3SNN6AAE1PZm','USER'),
(66,'2022-11-05 15:18:46.754233',NULL,NULL,'2022-11-05 15:18:46.754233','amb.clinton.gislason@yahoo.test','$2a$10$XMqg5.LSLPaBTgfDf819Se1fS.Y.L1kGuq7Z8kcO4Gx/8GjUvOd/a','USER'),
(67,'2022-11-05 15:18:46.754957',NULL,NULL,'2022-11-05 15:18:46.754957','seth.pollich@hotmail.test','$2a$10$lEzVs53lFmbgXebiUuu28ujAUi418Y6u7VN7XJmmB9ZUOPUX0IIw.','USER'),
(68,'2022-11-05 15:18:46.755582',NULL,NULL,'2022-11-05 15:18:46.755582','chuck.koss@gmail.test','$2a$10$XdcG1Q./QmAJodHqGGSvce/d8jZx3g5CYS6ocoAgt8gdTgCNr/CwS','USER'),
(69,'2022-11-05 15:18:46.756242',NULL,NULL,'2022-11-05 15:18:46.756242','erin.hamill@hotmail.test','$2a$10$1OcqPXaScfF6x.C1XO7CvevAEqk14.VIqP2OT4PLt3ij/GF65qmWG','USER'),
(70,'2022-11-05 15:18:46.756947',NULL,NULL,'2022-11-05 15:18:46.756947','corey.keeling.jr@hotmail.test','$2a$10$wzGI.BHQ24nxHKHH5613DerziyXfrWGIaIoqCGAh8OHIAHuRPS/M6','USER'),
(71,'2022-11-05 15:18:46.757764',NULL,NULL,'2022-11-05 15:18:46.757764','sherman.muller@hotmail.test','$2a$10$xpXeNhFlebToifFoirn7u.glmo9Nr6m4sNUDgkY8H1LRzojwRnhKK','USER'),
(72,'2022-11-05 15:18:46.758916',NULL,NULL,'2022-11-05 15:18:46.758916','donnie.terry@yahoo.test','$2a$10$dZOijUpKYF7vkhX27n1ieOIKdRIeXYqDfXTcLTYkzsfP8l6epNyfy','USER'),
(73,'2022-11-05 15:18:46.759661',NULL,NULL,'2022-11-05 15:18:46.759661','kellie.purdy@yahoo.test','$2a$10$FBAkFi6Sbfr8yXVdJ59ZfO1wOnlClyqcXmqSB1uyjHNTtY/AiLz8G','USER'),
(74,'2022-11-05 15:18:46.760346',NULL,NULL,'2022-11-05 15:18:46.760346','olin.shields@hotmail.test','$2a$10$GvGO2o4A0T2/Jl3N0JTsCuwGZiEOENdOdAj4fQinLUx1VssI0vDQK','USER'),
(75,'2022-11-05 15:18:46.761140',NULL,NULL,'2022-11-05 15:18:46.761140','ching.littel@yahoo.test','$2a$10$YQEmSioIpGA6lZgKfFPotO./HZQAe3cRkFihWOpr546bHpjkggeei','USER'),
(76,'2022-11-05 15:18:46.763000',NULL,NULL,'2022-11-05 15:18:46.763000','carmelo.cartwright@yahoo.test','$2a$10$MgW.4ycTxEAFYDwWHGDBC..IZ5dBHXm6CkUE0iOwnC9n2uJCiz1xG','USER'),
(77,'2022-11-05 15:18:46.763789',NULL,NULL,'2022-11-05 15:18:46.763789','deidra.prosacco@gmail.test','$2a$10$zfOr9DMiy/ivhRWlzLoc/uOi/cyzweCCToNWviftY5V8wPrrsBe4e','USER'),
(78,'2022-11-05 15:18:46.764880',NULL,NULL,'2022-11-05 15:18:46.764880','stacee.bahringer@hotmail.test','$2a$10$wAcGX6WNPOrhhtAdEad2U.fYo65CcLyrYKLLCHyzmQiT6Gy77Xqj.','USER'),
(79,'2022-11-05 15:18:46.767606',NULL,NULL,'2022-11-05 15:18:46.767606','ingrid.runolfsdottir@gmail.test','$2a$10$qr5ch7qy9Di8V7ZxVIzP4OzbJs29Bl9p21p9RlEaHVmKJo.PyLvh6','USER'),
(80,'2022-11-05 15:18:46.768475',NULL,NULL,'2022-11-05 15:18:46.768475','simon.bruen@yahoo.test','$2a$10$PV5NjwPYzgrfWYaAFTinOup/xcR.S2eOpNYMZjIv5X2Cv2.Iv35pC','USER'),
(81,'2022-11-05 15:18:46.769329',NULL,NULL,'2022-11-05 15:18:46.769329','elbert.schoen@gmail.test','$2a$10$7Jl3ZWYOycLLX1.DJkdo5eePdtaNJE7sbN196na3JuK2h29xpTQ4e','USER'),
(82,'2022-11-05 15:18:46.770275',NULL,NULL,'2022-11-05 15:18:46.770275','darnell.blick@gmail.test','$2a$10$c2vgVFxhrd4VyJxvDBvT2Oa2YqVopeJMOUGx9s73nBKjy2ZEfS29C','USER'),
(83,'2022-11-05 15:18:46.771043',NULL,NULL,'2022-11-05 15:18:46.771043','fr.melda.klein@yahoo.test','$2a$10$T2l2TcTso8zBTjALN6nn8eaH.tTKDmfO.nYLH5EpUjwyauciNJnei','USER'),
(84,'2022-11-05 15:18:46.771759',NULL,NULL,'2022-11-05 15:18:46.771759','graham.muller.dds@yahoo.test','$2a$10$h/tIEmQ7mcigEBprJhknxOMIO4JDNx2JdHf3WSl43OcioywZ3z3Ny','USER'),
(85,'2022-11-05 15:18:46.772482',NULL,NULL,'2022-11-05 15:18:46.772482','bryon.wolf@gmail.test','$2a$10$CmMgBkCZBNQ9AUzhPPEgU.CKSsRtQPalSzD4AqBktFbjVrovvS29m','USER'),
(86,'2022-11-05 15:18:46.773150',NULL,NULL,'2022-11-05 15:18:46.773150','maryann.gleason@hotmail.test','$2a$10$g1Llol4D08VQin0LMZAHZezzWzlGyQ8/a6yHiDTdRdusWiIOz311W','USER'),
(87,'2022-11-05 15:18:46.774185',NULL,NULL,'2022-11-05 15:18:46.774185','nicky.leannon@gmail.test','$2a$10$0dl8XeDNR3iBajDX1CDr9eRkPufviWAyWNfXLxD/6iPjvCOvoo.4.','USER'),
(88,'2022-11-05 15:18:46.775495',NULL,NULL,'2022-11-05 15:18:46.775495','gov.cruz.metz@hotmail.test','$2a$10$v3I15Q13g4dh2Oq0rIhDFeatYB0X0keM7LZIk1O0Tb4.2yQDCmiO2','USER'),
(89,'2022-11-05 15:18:46.777700',NULL,NULL,'2022-11-05 15:18:46.777700','carmen.jacobi@gmail.test','$2a$10$jAXwGeWrFedunJei2fzfmO10V/WRwk6osG2n/tm7Ci/uOPsIeQuZ.','USER'),
(90,'2022-11-05 15:18:46.778644',NULL,NULL,'2022-11-05 15:18:46.778644','wyatt.hane@yahoo.test','$2a$10$foDUhnFK4z6CcS25umjMa.MA9sPiZUoBQZP8G/fISmem72r04UbU2','USER'),
(91,'2022-11-05 15:18:46.779478',NULL,NULL,'2022-11-05 15:18:46.779478','aracely.crist.cpa@gmail.test','$2a$10$RZmEPsqvGewScU.pDJLFJ.dvfn2gfLXLUGzna23VZZwT2k2XYgHdS','USER'),
(92,'2022-11-05 15:18:46.780565',NULL,NULL,'2022-11-05 15:18:46.780565','msgr.haydee.koss@yahoo.test','$2a$10$KNDgDz3CdlfMNHXYU9GkG.IE7phnKzFw1MXSxMOVhbBzSAFCghVoS','USER'),
(93,'2022-11-05 15:18:46.781282',NULL,NULL,'2022-11-05 15:18:46.781282','kasey.hoppe.i@gmail.test','$2a$10$9yQkoKKR5MnvyMIA5YF5FuZ/i2.xOHGby7KKd0bKfaFbLs/hvPDjS','USER'),
(94,'2022-11-05 15:18:46.782107',NULL,NULL,'2022-11-05 15:18:46.782107','concha.quitzon@hotmail.test','$2a$10$nI7tUs594YPLeWi4fHGqqeX0iFWbhjL4X2oiLxf/c0dENzBrjNoga','USER'),
(95,'2022-11-05 15:18:46.783071',NULL,NULL,'2022-11-05 15:18:46.783071','gov.dia.bartoletti@gmail.test','$2a$10$NOvLOEIYhaBJiKQUD/oKfu6UE.wOWpDcHTF4quzA8oNPyL1N6gY9y','USER'),
(96,'2022-11-05 15:18:46.783853',NULL,NULL,'2022-11-05 15:18:46.783853','the.hon.ricardo.howell@yahoo.test','$2a$10$6zyCP4yjhwSpqDX1wuHYp.oI/64KW7mV7OOAsoARCThtQIFAV8UE6','USER'),
(97,'2022-11-05 15:18:46.784655',NULL,NULL,'2022-11-05 15:18:46.784655','mee.hintz@gmail.test','$2a$10$64qtSagpiaX59C1vxKFJTuNBagSbjd49anCzPF2YBpSFiCpsOiAci','USER'),
(98,'2022-11-05 15:18:46.785364',NULL,NULL,'2022-11-05 15:18:46.785364','sharlene.roberts@hotmail.test','$2a$10$E5UJIvg2yXuQl0D7iv1.COxaxCrnytTTlWivVlvnxzzLE0RliBqAK','USER'),
(99,'2022-11-05 15:18:46.786144',NULL,NULL,'2022-11-05 15:18:46.786144','miss.edwin.shanahan@gmail.test','$2a$10$7YbY6LqLMOMMuAGqDOQ2Reh3XFL.sXBz4B.fU954X8DDKdE/Gqsiy','USER'),
(100,'2022-11-05 15:18:46.787851',NULL,NULL,'2022-11-05 15:18:46.787851','kara.lebsack@yahoo.test','$2a$10$OgciwasHK6PxphzqvrFyJOJmmHs/oNfJlXa93AgW4rF9GfYwFJ/lG','USER'),
(101,'2022-11-05 15:27:31.649526',NULL,NULL,'2022-11-05 15:27:31.649526','alejandro.pacocha@hotmail.test','$2a$10$ujuQH.yo0t2b/8qTKACZ2un4kaY3WrgUK6pW92pSmLoAl/F/7q6Zu','USER'),
(102,'2022-11-05 15:27:31.669086',NULL,NULL,'2022-11-05 15:27:31.669086','malcolm.schumm@hotmail.test','$2a$10$U23B2INArY7gvv.8i3c5y.G6.cldckFoP/JbcQJm3Ra8RlyzmybNq','USER'),
(103,'2022-11-05 15:27:31.670389',NULL,NULL,'2022-11-05 15:27:31.670389','jimmy.hickle@gmail.test','$2a$10$6moWqenAqLPtY/BSc3zt1uRyfFFDCgJMX2qN.cUNna34W2UnpVwpy','USER'),
(104,'2022-11-05 15:27:31.671287',NULL,NULL,'2022-11-05 15:27:31.671287','abe.carter@gmail.test','$2a$10$.bE.WzQnoVBcEbyaB97PAOD7IWOoscEcO80cDBg5FZsCiZpszUYeO','USER'),
(105,'2022-11-05 15:27:31.672271',NULL,NULL,'2022-11-05 15:27:31.672271','gov.marlena.roob@yahoo.test','$2a$10$BcGsNldkF2W74d6ELKLH9.idyV/EQXyj5JohrwsIGGVJUX01Lem36','USER'),
(106,'2022-11-05 15:27:31.672947',NULL,NULL,'2022-11-05 15:27:31.672947','cary.greenholt@hotmail.test','$2a$10$NYLvvzu8OmSyV/g0Q.kF7e28xSq4nPrjS1kSuE45SrR9YIQQ3A942','USER'),
(107,'2022-11-05 15:27:31.673581',NULL,NULL,'2022-11-05 15:27:31.673581','emmanuel.lowe@gmail.test','$2a$10$SxRgW5041BV7uehYxmtYvOJFEc043kJrsMJd.AJjGtwQN.W73X7/m','USER'),
(108,'2022-11-05 15:27:31.674652',NULL,NULL,'2022-11-05 15:27:31.674652','augustine.haley@hotmail.test','$2a$10$EW6ztSf9L.Yn/CzVLOMNAejrBLiIQ4MOqkduuTsqXmljO4QevfD/G','USER'),
(109,'2022-11-05 15:27:31.675329',NULL,NULL,'2022-11-05 15:27:31.675329','dewayne.schowalter.lld@gmail.test','$2a$10$hoi3Kt68y0K0679Vit7NFuRiww7.knQGpFNqXJzSEcgHeag7CjziS','USER'),
(110,'2022-11-05 15:27:31.676011',NULL,NULL,'2022-11-05 15:27:31.676011','sunday.stamm@yahoo.test','$2a$10$.JjzhE5PVM1brnFM7NzPCOz5j0U/8jUerIKjPo21JNQUCgRH0iGmm','USER'),
(111,'2022-11-05 15:27:31.676627',NULL,NULL,'2022-11-05 15:27:31.676627','coleman.bechtelar@gmail.test','$2a$10$p0VmDGYBc.OTb.n20QX7fe.//hvwf93nBbhCwTe8/T/Hasg7MFhim','USER'),
(112,'2022-11-05 15:27:31.677273',NULL,NULL,'2022-11-05 15:27:31.677273','lindsay.kuhlman@yahoo.test','$2a$10$L2/dYrErwilf5keeStjrq.djfphLelMCnB2sneHQ0IJiW3pmWI2vK','USER'),
(113,'2022-11-05 15:27:31.678354',NULL,NULL,'2022-11-05 15:27:31.678354','tien.ledner.do@hotmail.test','$2a$10$ptf2zRdaN6NRME.wcnkJwewJP5fIweluJ5w6ZvpEFDrMnd8g7MN66','USER'),
(114,'2022-11-05 15:27:31.679794',NULL,NULL,'2022-11-05 15:27:31.679794','msgr.herbert.donnelly@hotmail.test','$2a$10$y4mBGUsXwkrDgoN6Tp/72.nbETDsUiF4R3U5l./3YVOuEa5Eng6nu','USER'),
(115,'2022-11-05 15:27:31.680659',NULL,NULL,'2022-11-05 15:27:31.680659','prof.tynisha.russel@gmail.test','$2a$10$52PwLY4RoA5rixIOMTTHg.KqDZpuBEfadwbdba9hNmcAcYjIdUnie','USER'),
(116,'2022-11-05 15:27:31.681887',NULL,NULL,'2022-11-05 15:27:31.681887','dillon.abbott@hotmail.test','$2a$10$qe8lgB3BaAjEl.xi8f2Wtu7u0k6pR9ArQnGHXwhp/zl1VIHFbX/z.','USER'),
(117,'2022-11-05 15:27:31.682768',NULL,NULL,'2022-11-05 15:27:31.682768','msgr.agustin.pagac@gmail.test','$2a$10$bRg..OohS9lBpuMPDYSWqOMB6M.JcVKdoo5aAhnd.IRFkg8aJ4vtu','USER'),
(118,'2022-11-05 15:27:31.683912',NULL,NULL,'2022-11-05 15:27:31.683912','rev.jeanice.wunsch@hotmail.test','$2a$10$rLnyVhIKA7tlGM90leHwVOhT9URjYYnUt/7I0gpO3N8Z4UHn87ayS','USER'),
(119,'2022-11-05 15:27:31.684738',NULL,NULL,'2022-11-05 15:27:31.684738','mason.ryan@hotmail.test','$2a$10$ptDo29rDPxMftxf0yy69D.XJFX42Yp.BF8hZ994.lPPwaGMk35mKe','USER'),
(120,'2022-11-05 15:27:31.685424',NULL,NULL,'2022-11-05 15:27:31.685424','grover.white@hotmail.test','$2a$10$0M5qHXgPlNsaO6KzqjIiduz/X/mhRAc3OQs35Vqsh3vdRZc3uQvY2','USER'),
(121,'2022-11-05 15:27:31.686075',NULL,NULL,'2022-11-05 15:27:31.686075','simon.zieme@gmail.test','$2a$10$TcJFlHSrNkXyGJ3gOxJnN.u7U6f3XpxtTqxAkhJyLkm2Lq4qiwM9m','USER'),
(122,'2022-11-05 15:27:31.686704',NULL,NULL,'2022-11-05 15:27:31.686704','norine.upton@gmail.test','$2a$10$p7RIuEWaQQjWQOwM9QPH6.GwJYzKaX/x7PNdT5zF05OGgsxH5OT0.','USER'),
(123,'2022-11-05 15:27:31.687411',NULL,NULL,'2022-11-05 15:27:31.687411','ressie.upton@gmail.test','$2a$10$JAjvLjYi65iez761QXY8HOz3VCj6BxNc/qSwjzQPLXAUOsmcoJjTy','USER'),
(124,'2022-11-05 15:27:31.688002',NULL,NULL,'2022-11-05 15:27:31.688002','alexander.block@yahoo.test','$2a$10$Uth2moBLAO9dY5NZKPe.mOA7oaUsz8VF2MiirsrY3GI/i386MJtUi','USER'),
(125,'2022-11-05 15:27:31.688605',NULL,NULL,'2022-11-05 15:27:31.688605','hong.adams@gmail.test','$2a$10$fJtWLq3Kc9PJJiw8igfRMejSGcd7qtpywH2dUrWqcYjQvVIRLvHqy','USER'),
(126,'2022-11-05 15:27:31.689303',NULL,NULL,'2022-11-05 15:27:31.689303','page.hodkiewicz@hotmail.test','$2a$10$B4i8WibUQ4B1ErWc.xnkwOdfhqgsQTQErdS5o098SxEU781x4YUNm','USER'),
(127,'2022-11-05 15:27:31.689865',NULL,NULL,'2022-11-05 15:27:31.689865','maurice.hilll@gmail.test','$2a$10$2zVdMmtVghd50D9Y5wwXpu6II3UPO8oA7wZSxefJf7.sGT4R/HFA6','USER'),
(128,'2022-11-05 15:27:31.690447',NULL,NULL,'2022-11-05 15:27:31.690447','kurt.trantow@yahoo.test','$2a$10$jyvF329obRb28kk3yAfAc.MHg2qFIct8UwuyA4ywd6zlgZhcQM40S','USER'),
(129,'2022-11-05 15:27:31.691058',NULL,NULL,'2022-11-05 15:27:31.691058','herbert.steuber@gmail.test','$2a$10$yX5Bw14WVR.iXmffslKUzu9O2V7Fv3WaJxnUrRfgCAOVYvf8C8Zi2','USER'),
(130,'2022-11-05 15:27:31.692426',NULL,NULL,'2022-11-05 15:27:31.692426','the.hon.jere.jones@yahoo.test','$2a$10$H2QY6yMvxtaNTWiQSrKC8.avhmBk9MBnh9MZI2yBUjxYIYfMFEBbG','USER'),
(131,'2022-11-05 15:27:31.693410',NULL,NULL,'2022-11-05 15:27:31.693410','letisha.davis@yahoo.test','$2a$10$94QNP76lV1WW9bTwwK4HlukgMbr/QnW5Ed8guVyWnod8ATIvRqn3m','USER'),
(132,'2022-11-05 15:27:31.694250',NULL,NULL,'2022-11-05 15:27:31.694250','rosaura.heaney.jr@gmail.test','$2a$10$rbxf2mL.XNSjPC3VYaISwew30Izh.6n2ouFeLQBMOLvEs75Jrs5dy','USER'),
(133,'2022-11-05 15:27:31.695571',NULL,NULL,'2022-11-05 15:27:31.695571','darnell.weimann@yahoo.test','$2a$10$3WgBDoc2uA5D5pAki7.2Cu1RVrPDW.hjjdRUvk6vRlmWQrQ2c4oea','USER'),
(134,'2022-11-05 15:27:31.696291',NULL,NULL,'2022-11-05 15:27:31.696291','tyrell.hilll@hotmail.test','$2a$10$KWRFiLl.cg2IUSoRgTEAtuWULAmaB5gQGvrgMhmOIgKaR2rFNx0Ua','USER'),
(135,'2022-11-05 15:27:31.697368',NULL,NULL,'2022-11-05 15:27:31.697368','rep.tommye.schowalter@hotmail.test','$2a$10$B7M1gcrQkhnNU9havlryUuSx0K6v/WQ0g5epfAnpW0/u2Z0Zoh6XK','USER'),
(136,'2022-11-05 15:27:31.698330',NULL,NULL,'2022-11-05 15:27:31.698330','mozell.corkery@hotmail.test','$2a$10$PotI1VJGzqFI4xLNkcs9EuacnrJo19UV4aNU0SoxiBzrSEHIdAHt.','USER'),
(137,'2022-11-05 15:27:31.699091',NULL,NULL,'2022-11-05 15:27:31.699091','ami.harris.lld@yahoo.test','$2a$10$XIxkVbiiwpAOpmwG56htVOwEW3IyC8bZhW9VXGXYMrjVkHhVpZOtK','USER'),
(138,'2022-11-05 15:27:31.699725',NULL,NULL,'2022-11-05 15:27:31.699725','prof.mike.wolff@yahoo.test','$2a$10$VMwTH2oNFjegvKtV1D.Xp.eQKNx15kVE1pZNcfdtlweg5r53JLc.K','USER'),
(139,'2022-11-05 15:27:31.700365',NULL,NULL,'2022-11-05 15:27:31.700365','josiah.beier@hotmail.test','$2a$10$.gJgtKQiJXkExNZWmkhchusC5oy4.IQuxUFnkx3ifIU1K0x3lM4e2','USER'),
(140,'2022-11-05 15:27:31.701015',NULL,NULL,'2022-11-05 15:27:31.701015','lavina.little@gmail.test','$2a$10$sdBsbhhTbOv4PiN.McaJgucFQCAhnA11P4pcL.TIh.RWAJh7bTRmy','USER'),
(141,'2022-11-05 15:27:31.701662',NULL,NULL,'2022-11-05 15:27:31.701662','pres.floyd.zieme@gmail.test','$2a$10$lKDZfu67iq6XqVOyDlCq0uMkeJyKzDEJ0lBevt7yAFHFEsiMgzPvu','USER'),
(142,'2022-11-05 15:27:31.702402',NULL,NULL,'2022-11-05 15:27:31.702402','pres.dudley.erdman@gmail.test','$2a$10$ZSmNh7HZ4Vrr0wuabgPKSudP8OtUaC0rYa5kdY2lTcPIDC7LU3rKW','USER'),
(143,'2022-11-05 15:27:31.703120',NULL,NULL,'2022-11-05 15:27:31.703120','dominica.swaniawski.do@gmail.test','$2a$10$OENsPfJpB0LsmIccWAL2m.Y0IuQTLJE3AiW.UMfRIOWS9NNnPtK.2','USER'),
(144,'2022-11-05 15:27:31.703935',NULL,NULL,'2022-11-05 15:27:31.703935','leigh.keeling@hotmail.test','$2a$10$ajMh7tx0QfUJuYsULaTlQuW8jylYlIwlHJzr5iiP3D3dDOhKJlHDW','USER'),
(145,'2022-11-05 15:27:31.704702',NULL,NULL,'2022-11-05 15:27:31.704702','anne.rolfson@hotmail.test','$2a$10$zqsdx9FmWZZ74ZS8O51c7eFKz/EXQsI1hUZbePVN3Vfpn9UAnJF5y','USER'),
(146,'2022-11-05 15:27:31.707317',NULL,NULL,'2022-11-05 15:27:31.707317','hyun.borer@yahoo.test','$2a$10$MwoaZaZWW8sFHvqq5YDMtO3arY2gG2fXJJvWVLFTe0ygWEMkvh1Z2','USER'),
(147,'2022-11-05 15:27:31.708303',NULL,NULL,'2022-11-05 15:27:31.708303','norris.windler@gmail.test','$2a$10$jnUoQAcbJ2K751l7RJ4bc.8rEyJy8oyDS3b3vcdVzFLCcGqvKDzIS','USER'),
(148,'2022-11-05 15:27:31.708969',NULL,NULL,'2022-11-05 15:27:31.708969','bebe.heller@gmail.test','$2a$10$HeDKtu/jBIl8Ijf3btTGIe0kMIY7mAKmswxYMTLnnqnswEECWRfSW','USER'),
(149,'2022-11-05 15:27:31.709553',NULL,NULL,'2022-11-05 15:27:31.709553','shakia.pouros@yahoo.test','$2a$10$wz/KFdI3F064V4my3TGGJ.BV.Ybq6t/7bQHM3e44HxvsbhskXJ.GS','USER'),
(150,'2022-11-05 15:27:31.710106',NULL,NULL,'2022-11-05 15:27:31.710106','arnulfo.hirthe@yahoo.test','$2a$10$S/N5Y6VUJ0uwgTMCECVv7..t0xlDjAOWxiXaCt.UeNtkIbYbw1sfG','USER'),
(151,'2022-11-05 15:27:31.710791',NULL,NULL,'2022-11-05 15:27:31.710791','thomasena.langosh@hotmail.test','$2a$10$mUzfy.bimXTzC7X7cTg4jOowDOO4bDh1kAJXhfuQxumG/bDtjXLRu','USER'),
(152,'2022-11-05 15:27:31.711399',NULL,NULL,'2022-11-05 15:27:31.711399','brinda.nolan@yahoo.test','$2a$10$cDiCnysGpQZ68a1nBT851OBbUmcuGgLRg158hfR8nGRVhUAIXvzOm','USER'),
(153,'2022-11-05 15:27:31.711989',NULL,NULL,'2022-11-05 15:27:31.711989','derek.pfannerstill@hotmail.test','$2a$10$fIea6MCM/u58euEoQ32BIeu09CkRBlHMRURd4loCPJTu.QfkoyoyW','USER'),
(154,'2022-11-05 15:27:31.712600',NULL,NULL,'2022-11-05 15:27:31.712600','wendolyn.feil@yahoo.test','$2a$10$FHxtMfYoadMrbTv17yVFv.R5PiSXzTW67ztcrXr8UXRzjQBKICPje','USER'),
(155,'2022-11-05 15:27:31.713459',NULL,NULL,'2022-11-05 15:27:31.713459','dudley.mayer@gmail.test','$2a$10$uIA4YHHiYeKAVuXmhLJ.Veu3hXL9FXyfACNpro/pa8.JAht29.I36','USER'),
(156,'2022-11-05 15:27:31.714030',NULL,NULL,'2022-11-05 15:27:31.714030','leanna.rogahn.phd@gmail.test','$2a$10$pRBDS9hc.ch6Tm.TjT2KKeWQjD6re9WUEKzUyMTXbnO0yU5mWxr1O','USER'),
(157,'2022-11-05 15:27:31.714551',NULL,NULL,'2022-11-05 15:27:31.714551','bradly.hamill@hotmail.test','$2a$10$SJAtJhPHWiy8MeHgOyqM2OcJNXdxG0Nk9ZchaVl5rbL5GEI8aE0iK','USER'),
(158,'2022-11-05 15:27:31.715043',NULL,NULL,'2022-11-05 15:27:31.715043','odell.donnelly@hotmail.test','$2a$10$XsDZrd4X6KWUDV9emHh9B.xl1ExTqyVRdPPMCja5vnzWqEZEkYR62','USER'),
(159,'2022-11-05 15:27:31.715566',NULL,NULL,'2022-11-05 15:27:31.715566','reid.goodwin@gmail.test','$2a$10$bf2WbVftLZfUc2ebadcQKuKy1U94KHHkrxbyqXhrISOAwMTp9mVTC','USER'),
(160,'2022-11-05 15:27:31.716059',NULL,NULL,'2022-11-05 15:27:31.716059','natisha.parker@hotmail.test','$2a$10$V7Q1c3kEeciw0pc.KuV.Uuk0RYLiykwUJ2pSfnDnAAhSA5swV4dy6','USER'),
(161,'2022-11-05 15:27:31.716584',NULL,NULL,'2022-11-05 15:27:31.716584','arnulfo.ferry@yahoo.test','$2a$10$glt/OKXYFpamr8opbigKYOrGY5Wu3ZrCK5P0bz9YelAgUAOnuBGEW','USER'),
(162,'2022-11-05 15:27:31.717038',NULL,NULL,'2022-11-05 15:27:31.717038','christena.o\'hara@yahoo.test','$2a$10$VeITDk0d0BEA44MuQn/3TuVGs5AP85J9rmMy5dV1UURWuWbKRoUx6','USER'),
(163,'2022-11-05 15:27:31.717539',NULL,NULL,'2022-11-05 15:27:31.717539','ella.leannon@yahoo.test','$2a$10$V.1..muL9RAklJGD64YCj.VU5ylGre2SAR9FzLlBfD68CWjHXpd6W','USER'),
(164,'2022-11-05 15:27:31.718182',NULL,NULL,'2022-11-05 15:27:31.718182','alexis.cassin@yahoo.test','$2a$10$uYO7FjxxNMPKq00hM/dbyuIIcZ3hmUvBXq1.i1o.Bi1hr5aZBlUpa','USER'),
(165,'2022-11-05 15:27:31.719108',NULL,NULL,'2022-11-05 15:27:31.719108','prof.sherwood.marquardt@hotmail.test','$2a$10$qTPDvGsKMeoO.6DqVn5FY.Ph0BaXSWZMoRYaANJkWgnuea85S2keu','USER'),
(166,'2022-11-05 15:27:31.719712',NULL,NULL,'2022-11-05 15:27:31.719712','prof.marybeth.o\'reilly@hotmail.test','$2a$10$VkTSYU7XrrDaiC7neC3wa.jou7RVsfpl0fxF4.75iUfT6PwPgdhYu','USER'),
(167,'2022-11-05 15:27:31.720244',NULL,NULL,'2022-11-05 15:27:31.720244','merlin.hettinger@hotmail.test','$2a$10$fXOxR5CUZOJIHmBOYrWy.e6oKlMxk0XUWsxGMHb.kyID0fs2X3Cau','USER'),
(168,'2022-11-05 15:27:31.721819',NULL,NULL,'2022-11-05 15:27:31.721819','jaleesa.daniel.i@yahoo.test','$2a$10$dlRlFxWQD.ihpbwINAMlJ.NGBRAzcCk5J6IMGOM2fdAtvgsiTxjM.','USER'),
(169,'2022-11-05 15:27:31.722285',NULL,NULL,'2022-11-05 15:27:31.722285','miss.burton.kozey@hotmail.test','$2a$10$HcY/tucF2ZkDaJxJKPdne.emt8DQVHwLIvT.wz/VdIQ4WQ3BIuqLa','USER'),
(170,'2022-11-05 15:27:31.722737',NULL,NULL,'2022-11-05 15:27:31.722737','renee.bechtelar@hotmail.test','$2a$10$bsqtWEqvG062K1S25QHwu.w.TlN9DHgP0sTaJ..OwU7j//8PoQ0VC','USER'),
(171,'2022-11-05 15:27:31.723191',NULL,NULL,'2022-11-05 15:27:31.723191','velda.hyatt.vm@hotmail.test','$2a$10$EB3f04Ijuk5z64bxqLxBweIuMff//2dO7i7lmqgYcuppCZ5jBU1l6','USER'),
(172,'2022-11-05 15:27:31.723642',NULL,NULL,'2022-11-05 15:27:31.723642','estella.lesch@gmail.test','$2a$10$xF9LQxpvZOpYY/lHKYdZ1.glKCl1HQlMcY7BJ8Hle6PhDB/0HhzzW','USER'),
(173,'2022-11-05 15:27:31.724135',NULL,NULL,'2022-11-05 15:27:31.724135','louis.oberbrunner@yahoo.test','$2a$10$G2MSbbghsKoIJiz8GBAHt.cDcnPuHHKXgQhhgAvryxkZ0pmvK0HCC','USER'),
(174,'2022-11-05 15:27:31.724634',NULL,NULL,'2022-11-05 15:27:31.724634','heidi.johnston.v@gmail.test','$2a$10$lwWMlA87J8mArYSOYkqEleocJ6L8e60p1lg2LJzKMW//8G4ON0PoO','USER'),
(175,'2022-11-05 15:27:31.725159',NULL,NULL,'2022-11-05 15:27:31.725159','wade.mayert.dc@hotmail.test','$2a$10$sxBp5/QakSPBsh9odA3b9.n7syjr7h9RxUSFU35uI00SWqg7pyivi','USER'),
(176,'2022-11-05 15:27:31.725682',NULL,NULL,'2022-11-05 15:27:31.725682','shay.hills@gmail.test','$2a$10$/rBeeqPNFcqB97Jzd9nKwuZ706Oy4EByP0flL/xR/KvwePqeCtsLu','USER'),
(177,'2022-11-05 15:27:31.726176',NULL,NULL,'2022-11-05 15:27:31.726176','the.hon.reda.koch@yahoo.test','$2a$10$swdx1Bh42ebG1HPWMqvBxOgsA9NW0Ig7TK7Em3wthM0fg/7WxmF72','USER'),
(178,'2022-11-05 15:27:31.726677',NULL,NULL,'2022-11-05 15:27:31.726677','fabian.dickens@gmail.test','$2a$10$h.tB2XRHZy0RIcL.1XG1ZeCgURWmVC120fzSWa0jN3NQtLFhxWIsi','USER'),
(179,'2022-11-05 15:27:31.727280',NULL,NULL,'2022-11-05 15:27:31.727280','angeline.ortiz@yahoo.test','$2a$10$KwWK375QM70ELyp7S6bTauZ4LyCe/QUc6qOu2ouVmGELw3LFPPE6W','USER'),
(180,'2022-11-05 15:27:31.728492',NULL,NULL,'2022-11-05 15:27:31.728492','samual.considine@yahoo.test','$2a$10$DhmIe2UQxhEer/8NRo10.eFMuojT7ttFr4ObvUucTF5V3MtRwrzse','USER'),
(181,'2022-11-05 15:27:31.728962',NULL,NULL,'2022-11-05 15:27:31.728962','shoshana.reynolds.v@gmail.test','$2a$10$y9NKnlKhncs.IVutKzmsyeNnvAG2QNLAsmlEyM5BSbw0TlCG8VLJ2','USER'),
(182,'2022-11-05 15:27:31.729432',NULL,NULL,'2022-11-05 15:27:31.729432','morton.volkman@gmail.test','$2a$10$NUZ//Y08e.xXDnDz0K5Cgua05eOhiSoR3xB.5QUcuCP2uIN5lq/AO','USER'),
(183,'2022-11-05 15:27:31.730281',NULL,NULL,'2022-11-05 15:27:31.730281','jarvis.greenholt.ii@yahoo.test','$2a$10$S2glUw9T68ksJP2hjh.c0.AH8NjkyQLPSkkb.bhGZbZeLQOB/nqPa','USER'),
(184,'2022-11-05 15:27:31.730730',NULL,NULL,'2022-11-05 15:27:31.730730','ms.deetta.tromp@gmail.test','$2a$10$704G7VSdFr5A8ey6rWdR7eMXeR.49VF05aLMMgvR1fOkXopqtM9Zy','USER'),
(185,'2022-11-05 15:27:31.731234',NULL,NULL,'2022-11-05 15:27:31.731234','pres.clint.johnson@yahoo.test','$2a$10$5ceyKfOsO6RPMABpg/IY.OAq0MofasmAZDGg34DtAWfxTpaeYZchi','USER'),
(186,'2022-11-05 15:27:31.731723',NULL,NULL,'2022-11-05 15:27:31.731723','rev.una.gulgowski@gmail.test','$2a$10$Kpn79UJPp8bSBvbo4zlc1uGKLWJfGJDdDnhqeAHEqhyMw6vpMqUfO','USER'),
(187,'2022-11-05 15:27:31.732223',NULL,NULL,'2022-11-05 15:27:31.732223','wilbur.lemke@gmail.test','$2a$10$12XNTMcmAZkBj8HMLGVo3uunmykYR9LKD8tPnnb4hQwnVAfpst25O','USER'),
(188,'2022-11-05 15:27:31.732692',NULL,NULL,'2022-11-05 15:27:31.732692','gilbert.mcclure@yahoo.test','$2a$10$M3PdNV00dCWdLpE6u8ykfey8dLzPSwjMKm1EZWnns8ZL6SVJL7wkS','USER'),
(189,'2022-11-05 15:27:31.733325',NULL,NULL,'2022-11-05 15:27:31.733325','xiao.russel@yahoo.test','$2a$10$NRWWW9P6XZPvNBrxsW7X0uAONxp07uupuTydNrtljmIvinbB79gDS','USER'),
(190,'2022-11-05 15:27:31.734126',NULL,NULL,'2022-11-05 15:27:31.734126','micheal.predovic@hotmail.test','$2a$10$KpakHrI1SC/W2gBkndrmU.4SY4mVW4Y9O.wNXVb6xdp2u54Lzko8i','USER'),
(191,'2022-11-05 15:27:31.734841',NULL,NULL,'2022-11-05 15:27:31.734841','amb.cody.klocko@yahoo.test','$2a$10$xWc4Gbu7HL/gO2eLOUE34.9qU47KNXVvOBaCW3CoX8tDKXd5Wonmi','USER'),
(192,'2022-11-05 15:27:31.735405',NULL,NULL,'2022-11-05 15:27:31.735405','kiersten.rath@hotmail.test','$2a$10$TzRsxKq1Ea3pCgURCkXoW.tjvV43CQFX41RxasP0HQaWjPtO8Duke','USER'),
(193,'2022-11-05 15:27:31.735895',NULL,NULL,'2022-11-05 15:27:31.735895','destiny.nitzsche@gmail.test','$2a$10$8W5Z4HBekcCHDgHYZzgENegA6PoCbvv8BkR7d5vzlJ4ole/ZeSHvS','USER'),
(194,'2022-11-05 15:27:31.736370',NULL,NULL,'2022-11-05 15:27:31.736370','msgr.eleonor.wyman@yahoo.test','$2a$10$yGINqs6KWn4WtYNgwCjwE.jpDbIZuX901zMJAP4s7LVT1IBT9tcvK','USER'),
(195,'2022-11-05 15:27:31.737090',NULL,NULL,'2022-11-05 15:27:31.737090','parker.muller@gmail.test','$2a$10$IigjxVafV8uI88zMVvluzuRsfIi4..UuaAocJGvizE1UhUn7OA67a','USER'),
(196,'2022-11-05 15:27:31.737619',NULL,NULL,'2022-11-05 15:27:31.737619','dr.francis.ratke@yahoo.test','$2a$10$ajfztcPQPXkqPv9OdEwplOS7fuc852jrQ6UqqqPOxhFDfMMdIbPA2','USER'),
(197,'2022-11-05 15:27:31.738212',NULL,NULL,'2022-11-05 15:27:31.738212','kandy.heidenreich.phd@gmail.test','$2a$10$AJ9krPoTxwNvesZBLsDBzuv1nFIXcWs.qTsppumCGfc5vWSRoDQoC','USER'),
(198,'2022-11-05 15:27:31.738731',NULL,NULL,'2022-11-05 15:27:31.738731','jennifer.sanford@gmail.test','$2a$10$neVIjeiWiEMC2bcAoa0n0.DDEsSBvraclkBD3L/pH80XGIvO6u3JK','USER'),
(199,'2022-11-05 15:27:31.739374',NULL,NULL,'2022-11-05 15:27:31.739374','jay.simonis@hotmail.test','$2a$10$BoqZinNAjcG7.e6feFg1WOTVvp15Kb1pf66bytyDMBzQed0X.Af/K','USER'),
(200,'2022-11-05 15:27:31.739879',NULL,NULL,'2022-11-05 15:27:31.739879','mickey.schaefer@gmail.test','$2a$10$MSYk58eps65pyBY9snQRFOnxnF8NDmsS6A.ZOHJGSbNiE7px3.eOy','USER');
/*!40000 ALTER TABLE `Member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Post`
--

DROP TABLE IF EXISTS `Post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_at` datetime(6) NOT NULL,
  `delete_at` datetime(6) DEFAULT NULL,
  `order_no` bigint(20) DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `post_type` varchar(255) DEFAULT NULL,
  `resevate_at` datetime(6) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa9v8iie588k1ltvt2nsepu9gi` (`member_id`),
  CONSTRAINT `FKa9v8iie588k1ltvt2nsepu9gi` FOREIGN KEY (`member_id`) REFERENCES `Member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=201 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Post`
--

LOCK TABLES `Post` WRITE;
/*!40000 ALTER TABLE `Post` DISABLE KEYS */;
INSERT INTO `Post` (`id`, `create_at`, `delete_at`, `order_no`, `update_at`, `content`, `post_type`, `resevate_at`, `title`, `member_id`) VALUES (101,'2022-11-05 15:27:31.768722','2022-11-05 15:27:31.753136',NULL,'2022-11-05 15:27:31.768722','Plant a memory, plant a tree, do it today for tomorrow.','GOSSIP','2022-11-05 15:27:31.752651','Donnager',72),
(102,'2022-11-05 15:27:31.774820','2022-11-05 15:27:31.754866',NULL,'2022-11-05 15:27:31.774820','Often people, especially computer engineers, focus on the machines. But in fact we need to focus on humans, on how humans care about doing programming or operating the application of the machines.','GOSSIP','2022-11-05 15:27:31.754860','Callisto\'s Dream',55),
(103,'2022-11-05 15:27:31.775610','2022-11-05 15:27:31.754955',NULL,'2022-11-05 15:27:31.775610','Smart people underestimate the ordinarity of ordinary people.','GOSSIP','2022-11-05 15:27:31.754952','Hammurabi',60),
(104,'2022-11-05 15:27:31.776368','2022-11-05 15:27:31.755028',NULL,'2022-11-05 15:27:31.776368','Ruby inherited the Perl philosophy of having more than one way to do the same thing. I inherited that philosophy from Larry Wall, who is my hero actually. I want to make Ruby users free. I want to give them the freedom to choose.','GOSSIP','2022-11-05 15:27:31.755025','Lucien',19),
(105,'2022-11-05 15:27:31.777546','2022-11-05 15:27:31.755118',NULL,'2022-11-05 15:27:31.777546','Smart people underestimate the ordinarity of ordinary people.','GOSSIP','2022-11-05 15:27:31.755116','Tanaka',25),
(106,'2022-11-05 15:27:31.778183','2022-11-05 15:27:31.755194',NULL,'2022-11-05 15:27:31.778183','People are different. People choose different criteria. But if there is a better way among many alternatives, I want to encourage that way by making it comfortable. So that\'s what I\'ve tried to do.','GOSSIP','2022-11-05 15:27:31.755192','Lightbreaker',38),
(107,'2022-11-05 15:27:31.778898','2022-11-05 15:27:31.755292',NULL,'2022-11-05 15:27:31.778898','Language designers want to design the perfect language. They want to be able to say, \'My language is perfect. It can do everything.\' But it\'s just plain impossible to design a perfect language, because there are two ways to look at a language. One way is by looking at what can be done with that language. The other is by looking at how we feel using that language-how we feel while programming.','GOSSIP','2022-11-05 15:27:31.755289','Aristophanes',55),
(108,'2022-11-05 15:27:31.779760','2022-11-05 15:27:31.755363',NULL,'2022-11-05 15:27:31.779760','Smart people underestimate the ordinarity of ordinary people.','GOSSIP','2022-11-05 15:27:31.755361','Hammurabi',4),
(109,'2022-11-05 15:27:31.780881','2022-11-05 15:27:31.755435',NULL,'2022-11-05 15:27:31.780881','In our daily lives as programmers, we process text strings a lot. So I tried to work hard on text processing, namely the string class and regular expressions. Regular expressions are built into the language and are very tuned up for use.','GOSSIP','2022-11-05 15:27:31.755432','Marasmus',63),
(110,'2022-11-05 15:27:31.781656','2022-11-05 15:27:31.755525',NULL,'2022-11-05 15:27:31.781656','Smart people underestimate the ordinarity of ordinary people.','GOSSIP','2022-11-05 15:27:31.755522','Canterbury',58),
(111,'2022-11-05 15:27:31.782185','2022-11-05 15:27:31.755599',NULL,'2022-11-05 15:27:31.782185','Most programs are not write-once. They are reworked and rewritten again and again in their lived. Bugs must be debugged. Changing requirements and the need for increased functionality mean the program itself may be modified on an ongoing basis. During this process, human beings must be able to read and understand the original code. It is therefore more important by far for humans to be able to understand the program than it is for the computer.','GOSSIP','2022-11-05 15:27:31.755596','Kingfisher',55),
(112,'2022-11-05 15:27:31.782758','2022-11-05 15:27:31.755686',NULL,'2022-11-05 15:27:31.782758','Most programs are not write-once. They are reworked and rewritten again and again in their lived. Bugs must be debugged. Changing requirements and the need for increased functionality mean the program itself may be modified on an ongoing basis. During this process, human beings must be able to read and understand the original code. It is therefore more important by far for humans to be able to understand the program than it is for the computer.','GOSSIP','2022-11-05 15:27:31.755684','Kingfisher',79),
(113,'2022-11-05 15:27:31.783288','2022-11-05 15:27:31.755758',NULL,'2022-11-05 15:27:31.783288','Smart people underestimate the ordinarity of ordinary people.','GOSSIP','2022-11-05 15:27:31.755755','Razorback',77),
(114,'2022-11-05 15:27:31.783779','2022-11-05 15:27:31.755829',NULL,'2022-11-05 15:27:31.783779','Imagine you are writing an email. You are in front of the computer. You are operating the computer, clicking a mouse and typing on a keyboard, but the message will be sent to a human over the internet. So you are working before the computer, but with a human behind the computer.','GOSSIP','2022-11-05 15:27:31.755826','Donnager',65),
(115,'2022-11-05 15:27:31.784527','2022-11-05 15:27:31.755903',NULL,'2022-11-05 15:27:31.784527','Imagine you are writing an email. You are in front of the computer. You are operating the computer, clicking a mouse and typing on a keyboard, but the message will be sent to a human over the internet. So you are working before the computer, but with a human behind the computer.','GOSSIP','2022-11-05 15:27:31.755901','Callisto\'s Dream',35),
(116,'2022-11-05 15:27:31.785372','2022-11-05 15:27:31.755973',NULL,'2022-11-05 15:27:31.785372','From the viewpoint of what you can do, therefore, languages do differ - but the differences are limited. For example, Python and Ruby provide almost the same power to the programmer.','GOSSIP','2022-11-05 15:27:31.755971','Hammurabi',24),
(117,'2022-11-05 15:27:31.786027','2022-11-05 15:27:31.756142',NULL,'2022-11-05 15:27:31.786027','Most of the tasks we do are for humans. For example, a tax calculation is counting numbers so the government can pull money out from my wallet, but government consists of humans.','GOSSIP','2022-11-05 15:27:31.756138','Hammurabi',20),
(118,'2022-11-05 15:27:31.786614','2022-11-05 15:27:31.756228',NULL,'2022-11-05 15:27:31.786614','Imagine you are writing an email. You are in front of the computer. You are operating the computer, clicking a mouse and typing on a keyboard, but the message will be sent to a human over the internet. So you are working before the computer, but with a human behind the computer.','GOSSIP','2022-11-05 15:27:31.756225','Tanaka',82),
(119,'2022-11-05 15:27:31.787151','2022-11-05 15:27:31.756307',NULL,'2022-11-05 15:27:31.787151','Language designers want to design the perfect language. They want to be able to say, \'My language is perfect. It can do everything.\' But it\'s just plain impossible to design a perfect language, because there are two ways to look at a language. One way is by looking at what can be done with that language. The other is by looking at how we feel using that language-how we feel while programming.','GOSSIP','2022-11-05 15:27:31.756304','Lightbreaker',70),
(120,'2022-11-05 15:27:31.787623','2022-11-05 15:27:31.756396',NULL,'2022-11-05 15:27:31.787623','I believe consistency and orthogonality are tools of design, not the primary goal in design.','GOSSIP','2022-11-05 15:27:31.756394','Donnager',51),
(121,'2022-11-05 15:27:31.788068','2022-11-05 15:27:31.756468',NULL,'2022-11-05 15:27:31.788068','I didn\'t work hard to make Ruby perfect for everyone, because you feel differently from me. No language can be perfect for everyone. I tried to make Ruby perfect for me, but maybe it\'s not perfect for you. The perfect language for Guido van Rossum is probably Python.','GOSSIP','2022-11-05 15:27:31.756466','Callisto\'s Dream',73),
(122,'2022-11-05 15:27:31.788658','2022-11-05 15:27:31.756539',NULL,'2022-11-05 15:27:31.788658','Often people, especially computer engineers, focus on the machines. But in fact we need to focus on humans, on how humans care about doing programming or operating the application of the machines.','GOSSIP','2022-11-05 15:27:31.756537','S.A. Corey',78),
(123,'2022-11-05 15:27:31.789200','2022-11-05 15:27:31.756619',NULL,'2022-11-05 15:27:31.789200','Plant a memory, plant a tree, do it today for tomorrow.','GOSSIP','2022-11-05 15:27:31.756616','Anubis',59),
(124,'2022-11-05 15:27:31.789652','2022-11-05 15:27:31.756687',NULL,'2022-11-05 15:27:31.789652','Actually, I didn\'t make the claim that Ruby follows the principle of least surprise. Someone felt the design of Ruby follows that philosophy, so they started saying that. I didn\'t bring that up, actually.','GOSSIP','2022-11-05 15:27:31.756684','Donnager',58),
(125,'2022-11-05 15:27:31.790131','2022-11-05 15:27:31.756750',NULL,'2022-11-05 15:27:31.790131','Plant a memory, plant a tree, do it today for tomorrow.','GOSSIP','2022-11-05 15:27:31.756748','Schiaparelli',68),
(126,'2022-11-05 15:27:31.790602','2022-11-05 15:27:31.756822',NULL,'2022-11-05 15:27:31.790602','The orthogonal features, when combined, can explode into complexity.','GOSSIP','2022-11-05 15:27:31.756819','Aristophanes',40),
(127,'2022-11-05 15:27:31.791086','2022-11-05 15:27:31.756891',NULL,'2022-11-05 15:27:31.791086','I believe consistency and orthogonality are tools of design, not the primary goal in design.','GOSSIP','2022-11-05 15:27:31.756889','Schiaparelli',80),
(128,'2022-11-05 15:27:31.791532','2022-11-05 15:27:31.756966',NULL,'2022-11-05 15:27:31.791532','Man is driven to create; I know I really love to create things. And while I\'m not good at painting, drawing, or music, I can write software.','GOSSIP','2022-11-05 15:27:31.756963','Razorback',40),
(129,'2022-11-05 15:27:31.792167','2022-11-05 15:27:31.757042',NULL,'2022-11-05 15:27:31.792167','You want to enjoy life, don\'t you? If you get your job done quickly and your job is fun, that\'s good isn\'t it? That\'s the purpose of life, partly. Your life is better.','GOSSIP','2022-11-05 15:27:31.757039','Kittur Chennamma',43),
(130,'2022-11-05 15:27:31.792634','2022-11-05 15:27:31.757106',NULL,'2022-11-05 15:27:31.792634','The orthogonal features, when combined, can explode into complexity.','GOSSIP','2022-11-05 15:27:31.757103','Knight',10),
(131,'2022-11-05 15:27:31.793085','2022-11-05 15:27:31.757182',NULL,'2022-11-05 15:27:31.793085','Ruby inherited the Perl philosophy of having more than one way to do the same thing. I inherited that philosophy from Larry Wall, who is my hero actually. I want to make Ruby users free. I want to give them the freedom to choose.','GOSSIP','2022-11-05 15:27:31.757175','Marasmus',1),
(132,'2022-11-05 15:27:31.793594','2022-11-05 15:27:31.757247',NULL,'2022-11-05 15:27:31.793594','Smart people underestimate the ordinarity of ordinary people.','GOSSIP','2022-11-05 15:27:31.757245','Donnager',73),
(133,'2022-11-05 15:27:31.794210','2022-11-05 15:27:31.757313',NULL,'2022-11-05 15:27:31.794210','From the viewpoint of what you can do, therefore, languages do differ - but the differences are limited. For example, Python and Ruby provide almost the same power to the programmer.','GOSSIP','2022-11-05 15:27:31.757310','Rocinante',14),
(134,'2022-11-05 15:27:31.794661','2022-11-05 15:27:31.757387',NULL,'2022-11-05 15:27:31.794661','In our daily lives as programmers, we process text strings a lot. So I tried to work hard on text processing, namely the string class and regular expressions. Regular expressions are built into the language and are very tuned up for use.','GOSSIP','2022-11-05 15:27:31.757384','Knight',35),
(135,'2022-11-05 15:27:31.795115','2022-11-05 15:27:31.757454',NULL,'2022-11-05 15:27:31.795115','Because of the Turing completeness theory, everything one Turing-complete language can do can theoretically be done by another Turing-complete language, but at a different cost. You can do everything in assembler, but no one wants to program in assembler anymore.','GOSSIP','2022-11-05 15:27:31.757451','Kingfisher',22),
(136,'2022-11-05 15:27:31.795590','2022-11-05 15:27:31.757517',NULL,'2022-11-05 15:27:31.795590','It is not the responsibility of the language to force good looking code, but the language should make good looking code possible.','GOSSIP','2022-11-05 15:27:31.757514','Lucien',29),
(137,'2022-11-05 15:27:31.796083','2022-11-05 15:27:31.757577',NULL,'2022-11-05 15:27:31.796083','Smart people underestimate the ordinarity of ordinary people.','GOSSIP','2022-11-05 15:27:31.757575','Razorback',88),
(138,'2022-11-05 15:27:31.796572','2022-11-05 15:27:31.757643',NULL,'2022-11-05 15:27:31.796572','I hope to see Ruby help every programmer in the world to be productive, and to enjoy programming, and to be happy. That is the primary purpose of Ruby language.','GOSSIP','2022-11-05 15:27:31.757641','Nephthys',43),
(139,'2022-11-05 15:27:31.797182','2022-11-05 15:27:31.757705',NULL,'2022-11-05 15:27:31.797182','Smart people underestimate the ordinarity of ordinary people.','GOSSIP','2022-11-05 15:27:31.757703','Marasmus',88),
(140,'2022-11-05 15:27:31.797843','2022-11-05 15:27:31.757768',NULL,'2022-11-05 15:27:31.797843','Because of the Turing completeness theory, everything one Turing-complete language can do can theoretically be done by another Turing-complete language, but at a different cost. You can do everything in assembler, but no one wants to program in assembler anymore.','GOSSIP','2022-11-05 15:27:31.757766','Donnager',42),
(141,'2022-11-05 15:27:31.798514','2022-11-05 15:27:31.757833',NULL,'2022-11-05 15:27:31.798514','Ruby inherited the Perl philosophy of having more than one way to do the same thing. I inherited that philosophy from Larry Wall, who is my hero actually. I want to make Ruby users free. I want to give them the freedom to choose.','GOSSIP','2022-11-05 15:27:31.757831','Schiaparelli',91),
(142,'2022-11-05 15:27:31.799241','2022-11-05 15:27:31.757901',NULL,'2022-11-05 15:27:31.799241','Everyone has an individual background. Someone may come from Python, someone else may come from Perl, and they may be surprised by different aspects of the language. Then they come up to me and say, \'I was surprised by this feature of the language, so therefore Ruby violates the principle of least surprise.\' Wait. Wait. The principle of least surprise is not for you only.','GOSSIP','2022-11-05 15:27:31.757899','Kingfisher',5),
(143,'2022-11-05 15:27:31.799866','2022-11-05 15:27:31.757969',NULL,'2022-11-05 15:27:31.799866','Often people, especially computer engineers, focus on the machines. But in fact we need to focus on humans, on how humans care about doing programming or operating the application of the machines.','GOSSIP','2022-11-05 15:27:31.757967','Donnager',82),
(144,'2022-11-05 15:27:31.800721','2022-11-05 15:27:31.758043',NULL,'2022-11-05 15:27:31.800721','Everyone has an individual background. Someone may come from Python, someone else may come from Perl, and they may be surprised by different aspects of the language. Then they come up to me and say, \'I was surprised by this feature of the language, so therefore Ruby violates the principle of least surprise.\' Wait. Wait. The principle of least surprise is not for you only.','GOSSIP','2022-11-05 15:27:31.758041','Rocinante',56),
(145,'2022-11-05 15:27:31.801330','2022-11-05 15:27:31.758112',NULL,'2022-11-05 15:27:31.801330','Actually, I didn\'t make the claim that Ruby follows the principle of least surprise. Someone felt the design of Ruby follows that philosophy, so they started saying that. I didn\'t bring that up, actually.','GOSSIP','2022-11-05 15:27:31.758109','Razorback',53),
(146,'2022-11-05 15:27:31.801911','2022-11-05 15:27:31.758177',NULL,'2022-11-05 15:27:31.801911','Actually, I didn\'t make the claim that Ruby follows the principle of least surprise. Someone felt the design of Ruby follows that philosophy, so they started saying that. I didn\'t bring that up, actually.','GOSSIP','2022-11-05 15:27:31.758175','Anubis',94),
(147,'2022-11-05 15:27:31.802525','2022-11-05 15:27:31.758244',NULL,'2022-11-05 15:27:31.802525','Actually, I didn\'t make the claim that Ruby follows the principle of least surprise. Someone felt the design of Ruby follows that philosophy, so they started saying that. I didn\'t bring that up, actually.','GOSSIP','2022-11-05 15:27:31.758241','Schiaparelli',86),
(148,'2022-11-05 15:27:31.802988','2022-11-05 15:27:31.758311',NULL,'2022-11-05 15:27:31.802988','Actually, I didn\'t make the claim that Ruby follows the principle of least surprise. Someone felt the design of Ruby follows that philosophy, so they started saying that. I didn\'t bring that up, actually.','GOSSIP','2022-11-05 15:27:31.758308','Knight',15),
(149,'2022-11-05 15:27:31.803556','2022-11-05 15:27:31.758375',NULL,'2022-11-05 15:27:31.803556','I believe consistency and orthogonality are tools of design, not the primary goal in design.','GOSSIP','2022-11-05 15:27:31.758373','Aristophanes',73),
(150,'2022-11-05 15:27:31.804036','2022-11-05 15:27:31.758439',NULL,'2022-11-05 15:27:31.804036','Smart people underestimate the ordinarity of ordinary people.','GOSSIP','2022-11-05 15:27:31.758437','Kittur Chennamma',76),
(151,'2022-11-05 15:27:31.804497','2022-11-05 15:27:31.758505',NULL,'2022-11-05 15:27:31.804497','It is not the responsibility of the language to force good looking code, but the language should make good looking code possible.','GOSSIP','2022-11-05 15:27:31.758502','Knight',70),
(152,'2022-11-05 15:27:31.804955','2022-11-05 15:27:31.758572',NULL,'2022-11-05 15:27:31.804955','In our daily lives as programmers, we process text strings a lot. So I tried to work hard on text processing, namely the string class and regular expressions. Regular expressions are built into the language and are very tuned up for use.','GOSSIP','2022-11-05 15:27:31.758570','Anubis',90),
(153,'2022-11-05 15:27:31.805468','2022-11-05 15:27:31.758641',NULL,'2022-11-05 15:27:31.805468','Everyone has an individual background. Someone may come from Python, someone else may come from Perl, and they may be surprised by different aspects of the language. Then they come up to me and say, \'I was surprised by this feature of the language, so therefore Ruby violates the principle of least surprise.\' Wait. Wait. The principle of least surprise is not for you only.','GOSSIP','2022-11-05 15:27:31.758638','Callisto\'s Dream',41),
(154,'2022-11-05 15:27:31.805921','2022-11-05 15:27:31.758707',NULL,'2022-11-05 15:27:31.805921','I didn\'t work hard to make Ruby perfect for everyone, because you feel differently from me. No language can be perfect for everyone. I tried to make Ruby perfect for me, but maybe it\'s not perfect for you. The perfect language for Guido van Rossum is probably Python.','GOSSIP','2022-11-05 15:27:31.758705','Schiaparelli',51),
(155,'2022-11-05 15:27:31.806340','2022-11-05 15:27:31.758771',NULL,'2022-11-05 15:27:31.806340','Often people, especially computer engineers, focus on the machines. But in fact we need to focus on humans, on how humans care about doing programming or operating the application of the machines.','GOSSIP','2022-11-05 15:27:31.758769','Canterbury',85),
(156,'2022-11-05 15:27:31.806937','2022-11-05 15:27:31.758838',NULL,'2022-11-05 15:27:31.806937','Language designers want to design the perfect language. They want to be able to say, \'My language is perfect. It can do everything.\' But it\'s just plain impossible to design a perfect language, because there are two ways to look at a language. One way is by looking at what can be done with that language. The other is by looking at how we feel using that language-how we feel while programming.','GOSSIP','2022-11-05 15:27:31.758836','Schiaparelli',94),
(157,'2022-11-05 15:27:31.807400','2022-11-05 15:27:31.758902',NULL,'2022-11-05 15:27:31.807400','I believe consistency and orthogonality are tools of design, not the primary goal in design.','GOSSIP','2022-11-05 15:27:31.758900','Aristophanes',7),
(158,'2022-11-05 15:27:31.807808','2022-11-05 15:27:31.758969',NULL,'2022-11-05 15:27:31.807808','Everyone has an individual background. Someone may come from Python, someone else may come from Perl, and they may be surprised by different aspects of the language. Then they come up to me and say, \'I was surprised by this feature of the language, so therefore Ruby violates the principle of least surprise.\' Wait. Wait. The principle of least surprise is not for you only.','GOSSIP','2022-11-05 15:27:31.758967','Knight',82),
(159,'2022-11-05 15:27:31.808222','2022-11-05 15:27:31.759038',NULL,'2022-11-05 15:27:31.808222','I believe that the purpose of life is, at least in part, to be happy. Based on this belief, Ruby is designed to make programming not only easy but also fun. It allows you to concentrate on the creative side of programming, with less stress.','GOSSIP','2022-11-05 15:27:31.759036','Canterbury',72),
(160,'2022-11-05 15:27:31.808689','2022-11-05 15:27:31.759104',NULL,'2022-11-05 15:27:31.808689','I believe that the purpose of life is, at least in part, to be happy. Based on this belief, Ruby is designed to make programming not only easy but also fun. It allows you to concentrate on the creative side of programming, with less stress.','GOSSIP','2022-11-05 15:27:31.759102','Rocinante',65),
(161,'2022-11-05 15:27:31.809142','2022-11-05 15:27:31.759170',NULL,'2022-11-05 15:27:31.809142','Most programs are not write-once. They are reworked and rewritten again and again in their lived. Bugs must be debugged. Changing requirements and the need for increased functionality mean the program itself may be modified on an ongoing basis. During this process, human beings must be able to read and understand the original code. It is therefore more important by far for humans to be able to understand the program than it is for the computer.','GOSSIP','2022-11-05 15:27:31.759168','Knight',28),
(162,'2022-11-05 15:27:31.809595','2022-11-05 15:27:31.759237',NULL,'2022-11-05 15:27:31.809595','In our daily lives as programmers, we process text strings a lot. So I tried to work hard on text processing, namely the string class and regular expressions. Regular expressions are built into the language and are very tuned up for use.','GOSSIP','2022-11-05 15:27:31.759234','Kingfisher',38),
(163,'2022-11-05 15:27:31.810001','2022-11-05 15:27:31.759305',NULL,'2022-11-05 15:27:31.810001','Most of the tasks we do are for humans. For example, a tax calculation is counting numbers so the government can pull money out from my wallet, but government consists of humans.','GOSSIP','2022-11-05 15:27:31.759302','Callisto\'s Dream',61),
(164,'2022-11-05 15:27:31.810387','2022-11-05 15:27:31.759389',NULL,'2022-11-05 15:27:31.810387','Ruby inherited the Perl philosophy of having more than one way to do the same thing. I inherited that philosophy from Larry Wall, who is my hero actually. I want to make Ruby users free. I want to give them the freedom to choose.','GOSSIP','2022-11-05 15:27:31.759387','Callisto\'s Dream',23),
(165,'2022-11-05 15:27:31.811077','2022-11-05 15:27:31.759458',NULL,'2022-11-05 15:27:31.811077','Actually, I didn\'t make the claim that Ruby follows the principle of least surprise. Someone felt the design of Ruby follows that philosophy, so they started saying that. I didn\'t bring that up, actually.','GOSSIP','2022-11-05 15:27:31.759456','Canterbury',62),
(166,'2022-11-05 15:27:31.814147','2022-11-05 15:27:31.759523',NULL,'2022-11-05 15:27:31.814147','You want to enjoy life, don\'t you? If you get your job done quickly and your job is fun, that\'s good isn\'t it? That\'s the purpose of life, partly. Your life is better.','GOSSIP','2022-11-05 15:27:31.759520','Schiaparelli',24),
(167,'2022-11-05 15:27:31.814735','2022-11-05 15:27:31.759588',NULL,'2022-11-05 15:27:31.814735','Because of the Turing completeness theory, everything one Turing-complete language can do can theoretically be done by another Turing-complete language, but at a different cost. You can do everything in assembler, but no one wants to program in assembler anymore.','GOSSIP','2022-11-05 15:27:31.759586','Knight',74),
(168,'2022-11-05 15:27:31.815270','2022-11-05 15:27:31.759657',NULL,'2022-11-05 15:27:31.815270','Most programs are not write-once. They are reworked and rewritten again and again in their lived. Bugs must be debugged. Changing requirements and the need for increased functionality mean the program itself may be modified on an ongoing basis. During this process, human beings must be able to read and understand the original code. It is therefore more important by far for humans to be able to understand the program than it is for the computer.','GOSSIP','2022-11-05 15:27:31.759654','Lightbreaker',14),
(169,'2022-11-05 15:27:31.815985','2022-11-05 15:27:31.759722',NULL,'2022-11-05 15:27:31.815985','Most of the tasks we do are for humans. For example, a tax calculation is counting numbers so the government can pull money out from my wallet, but government consists of humans.','GOSSIP','2022-11-05 15:27:31.759719','Lucien',21),
(170,'2022-11-05 15:27:31.816750','2022-11-05 15:27:31.759791',NULL,'2022-11-05 15:27:31.816750','I didn\'t work hard to make Ruby perfect for everyone, because you feel differently from me. No language can be perfect for everyone. I tried to make Ruby perfect for me, but maybe it\'s not perfect for you. The perfect language for Guido van Rossum is probably Python.','GOSSIP','2022-11-05 15:27:31.759788','S.A. Corey',6),
(171,'2022-11-05 15:27:31.817304','2022-11-05 15:27:31.759872',NULL,'2022-11-05 15:27:31.817304','You want to enjoy life, don\'t you? If you get your job done quickly and your job is fun, that\'s good isn\'t it? That\'s the purpose of life, partly. Your life is better.','GOSSIP','2022-11-05 15:27:31.759869','S.A. Corey',48),
(172,'2022-11-05 15:27:31.817982','2022-11-05 15:27:31.759937',NULL,'2022-11-05 15:27:31.817982','Plant a memory, plant a tree, do it today for tomorrow.','GOSSIP','2022-11-05 15:27:31.759935','Corvette',11),
(173,'2022-11-05 15:27:31.818515','2022-11-05 15:27:31.760003',NULL,'2022-11-05 15:27:31.818515','People are different. People choose different criteria. But if there is a better way among many alternatives, I want to encourage that way by making it comfortable. So that\'s what I\'ve tried to do.','GOSSIP','2022-11-05 15:27:31.760001','Razorback',60),
(174,'2022-11-05 15:27:31.819042','2022-11-05 15:27:31.760073',NULL,'2022-11-05 15:27:31.819042','Because of the Turing completeness theory, everything one Turing-complete language can do can theoretically be done by another Turing-complete language, but at a different cost. You can do everything in assembler, but no one wants to program in assembler anymore.','GOSSIP','2022-11-05 15:27:31.760071','Anubis',98),
(175,'2022-11-05 15:27:31.819506','2022-11-05 15:27:31.760136',NULL,'2022-11-05 15:27:31.819506','I believe consistency and orthogonality are tools of design, not the primary goal in design.','GOSSIP','2022-11-05 15:27:31.760134','Tanaka',96),
(176,'2022-11-05 15:27:31.819921','2022-11-05 15:27:31.760203',NULL,'2022-11-05 15:27:31.819921','Often people, especially computer engineers, focus on the machines. But in fact we need to focus on humans, on how humans care about doing programming or operating the application of the machines.','GOSSIP','2022-11-05 15:27:31.760201','Aristophanes',87),
(177,'2022-11-05 15:27:31.820367','2022-11-05 15:27:31.760272',NULL,'2022-11-05 15:27:31.820367','Language designers want to design the perfect language. They want to be able to say, \'My language is perfect. It can do everything.\' But it\'s just plain impossible to design a perfect language, because there are two ways to look at a language. One way is by looking at what can be done with that language. The other is by looking at how we feel using that language-how we feel while programming.','GOSSIP','2022-11-05 15:27:31.760270','Koto',76),
(178,'2022-11-05 15:27:31.820813','2022-11-05 15:27:31.760341',NULL,'2022-11-05 15:27:31.820813','Everyone has an individual background. Someone may come from Python, someone else may come from Perl, and they may be surprised by different aspects of the language. Then they come up to me and say, \'I was surprised by this feature of the language, so therefore Ruby violates the principle of least surprise.\' Wait. Wait. The principle of least surprise is not for you only.','GOSSIP','2022-11-05 15:27:31.760338','Aristophanes',59),
(179,'2022-11-05 15:27:31.821495','2022-11-05 15:27:31.760409',NULL,'2022-11-05 15:27:31.821495','People are different. People choose different criteria. But if there is a better way among many alternatives, I want to encourage that way by making it comfortable. So that\'s what I\'ve tried to do.','GOSSIP','2022-11-05 15:27:31.760407','Donnager',2),
(180,'2022-11-05 15:27:31.822029','2022-11-05 15:27:31.760475',NULL,'2022-11-05 15:27:31.822029','Man is driven to create; I know I really love to create things. And while I\'m not good at painting, drawing, or music, I can write software.','GOSSIP','2022-11-05 15:27:31.760473','Hammurabi',39),
(181,'2022-11-05 15:27:31.822662','2022-11-05 15:27:31.760542',NULL,'2022-11-05 15:27:31.822662','In our daily lives as programmers, we process text strings a lot. So I tried to work hard on text processing, namely the string class and regular expressions. Regular expressions are built into the language and are very tuned up for use.','GOSSIP','2022-11-05 15:27:31.760540','Nauvoo',82),
(182,'2022-11-05 15:27:31.823115','2022-11-05 15:27:31.760607',NULL,'2022-11-05 15:27:31.823115','From the viewpoint of what you can do, therefore, languages do differ - but the differences are limited. For example, Python and Ruby provide almost the same power to the programmer.','GOSSIP','2022-11-05 15:27:31.760605','Knight',1),
(183,'2022-11-05 15:27:31.823555','2022-11-05 15:27:31.760718',NULL,'2022-11-05 15:27:31.823555','Most of the tasks we do are for humans. For example, a tax calculation is counting numbers so the government can pull money out from my wallet, but government consists of humans.','GOSSIP','2022-11-05 15:27:31.760715','Donnager',78),
(184,'2022-11-05 15:27:31.823992','2022-11-05 15:27:31.760794',NULL,'2022-11-05 15:27:31.823992','I didn\'t work hard to make Ruby perfect for everyone, because you feel differently from me. No language can be perfect for everyone. I tried to make Ruby perfect for me, but maybe it\'s not perfect for you. The perfect language for Guido van Rossum is probably Python.','GOSSIP','2022-11-05 15:27:31.760792','Razorback',67),
(185,'2022-11-05 15:27:31.824437','2022-11-05 15:27:31.760870',NULL,'2022-11-05 15:27:31.824437','Sometimes people jot down pseudo-code on paper. If that pseudo-code runs directly on their computers, its best, isn\'t it? Ruby tries to be like that, like pseudo-code that runs. Python people say that too.','GOSSIP','2022-11-05 15:27:31.760868','Hammurabi',58),
(186,'2022-11-05 15:27:31.825033','2022-11-05 15:27:31.760938',NULL,'2022-11-05 15:27:31.825033','People are different. People choose different criteria. But if there is a better way among many alternatives, I want to encourage that way by making it comfortable. So that\'s what I\'ve tried to do.','GOSSIP','2022-11-05 15:27:31.760936','Corvette',73),
(187,'2022-11-05 15:27:31.825575','2022-11-05 15:27:31.761004',NULL,'2022-11-05 15:27:31.825575','Language designers want to design the perfect language. They want to be able to say, \'My language is perfect. It can do everything.\' But it\'s just plain impossible to design a perfect language, because there are two ways to look at a language. One way is by looking at what can be done with that language. The other is by looking at how we feel using that language-how we feel while programming.','GOSSIP','2022-11-05 15:27:31.761002','Nephthys',87),
(188,'2022-11-05 15:27:31.826130','2022-11-05 15:27:31.761078',NULL,'2022-11-05 15:27:31.826130','Ruby inherited the Perl philosophy of having more than one way to do the same thing. I inherited that philosophy from Larry Wall, who is my hero actually. I want to make Ruby users free. I want to give them the freedom to choose.','GOSSIP','2022-11-05 15:27:31.761076','Hammurabi',82),
(189,'2022-11-05 15:27:31.826817','2022-11-05 15:27:31.761148',NULL,'2022-11-05 15:27:31.826817','Sometimes people jot down pseudo-code on paper. If that pseudo-code runs directly on their computers, its best, isn\'t it? Ruby tries to be like that, like pseudo-code that runs. Python people say that too.','GOSSIP','2022-11-05 15:27:31.761146','Donnager',22),
(190,'2022-11-05 15:27:31.827299','2022-11-05 15:27:31.761213',NULL,'2022-11-05 15:27:31.827299','Most of the tasks we do are for humans. For example, a tax calculation is counting numbers so the government can pull money out from my wallet, but government consists of humans.','GOSSIP','2022-11-05 15:27:31.761211','Kingfisher',58),
(191,'2022-11-05 15:27:31.827985','2022-11-05 15:27:31.761276',NULL,'2022-11-05 15:27:31.827985','It is not the responsibility of the language to force good looking code, but the language should make good looking code possible.','GOSSIP','2022-11-05 15:27:31.761274','Lightbreaker',14),
(192,'2022-11-05 15:27:31.828445','2022-11-05 15:27:31.761343',NULL,'2022-11-05 15:27:31.828445','People are different. People choose different criteria. But if there is a better way among many alternatives, I want to encourage that way by making it comfortable. So that\'s what I\'ve tried to do.','GOSSIP','2022-11-05 15:27:31.761341','Anubis',45),
(193,'2022-11-05 15:27:31.828935','2022-11-05 15:27:31.761430',NULL,'2022-11-05 15:27:31.828935','Because of the Turing completeness theory, everything one Turing-complete language can do can theoretically be done by another Turing-complete language, but at a different cost. You can do everything in assembler, but no one wants to program in assembler anymore.','GOSSIP','2022-11-05 15:27:31.761428','S.A. Corey',68),
(194,'2022-11-05 15:27:31.829435','2022-11-05 15:27:31.761493',NULL,'2022-11-05 15:27:31.829435','In our daily lives as programmers, we process text strings a lot. So I tried to work hard on text processing, namely the string class and regular expressions. Regular expressions are built into the language and are very tuned up for use.','GOSSIP','2022-11-05 15:27:31.761491','S.A. Corey',30),
(195,'2022-11-05 15:27:31.830078','2022-11-05 15:27:31.761556',NULL,'2022-11-05 15:27:31.830078','People are different. People choose different criteria. But if there is a better way among many alternatives, I want to encourage that way by making it comfortable. So that\'s what I\'ve tried to do.','GOSSIP','2022-11-05 15:27:31.761553','Koto',27),
(196,'2022-11-05 15:27:31.830702','2022-11-05 15:27:31.761625',NULL,'2022-11-05 15:27:31.830702','Ruby inherited the Perl philosophy of having more than one way to do the same thing. I inherited that philosophy from Larry Wall, who is my hero actually. I want to make Ruby users free. I want to give them the freedom to choose.','GOSSIP','2022-11-05 15:27:31.761623','Nephthys',27),
(197,'2022-11-05 15:27:31.831453','2022-11-05 15:27:31.761689',NULL,'2022-11-05 15:27:31.831453','Most programs are not write-once. They are reworked and rewritten again and again in their lived. Bugs must be debugged. Changing requirements and the need for increased functionality mean the program itself may be modified on an ongoing basis. During this process, human beings must be able to read and understand the original code. It is therefore more important by far for humans to be able to understand the program than it is for the computer.','GOSSIP','2022-11-05 15:27:31.761686','Schiaparelli',93),
(198,'2022-11-05 15:27:31.832080','2022-11-05 15:27:31.761750',NULL,'2022-11-05 15:27:31.832080','In our daily lives as programmers, we process text strings a lot. So I tried to work hard on text processing, namely the string class and regular expressions. Regular expressions are built into the language and are very tuned up for use.','GOSSIP','2022-11-05 15:27:31.761748','Lucien',85),
(199,'2022-11-05 15:27:31.832529','2022-11-05 15:27:31.761816',NULL,'2022-11-05 15:27:31.832529','In our daily lives as programmers, we process text strings a lot. So I tried to work hard on text processing, namely the string class and regular expressions. Regular expressions are built into the language and are very tuned up for use.','GOSSIP','2022-11-05 15:27:31.761814','Nephthys',77),
(200,'2022-11-05 15:27:31.833165','2022-11-05 15:27:31.761877',NULL,'2022-11-05 15:27:31.833165','People are different. People choose different criteria. But if there is a better way among many alternatives, I want to encourage that way by making it comfortable. So that\'s what I\'ve tried to do.','GOSSIP','2022-11-05 15:27:31.761875','Schiaparelli',54);
/*!40000 ALTER TABLE `Post` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-05 21:15:10
