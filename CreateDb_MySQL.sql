CREATE DATABASE  IF NOT EXISTS `uracsdb` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `uracsdb`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: uracsdb
-- ------------------------------------------------------
-- Server version	5.5.25

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
-- Table structure for table `t_app_role_menu`
--

DROP TABLE IF EXISTS `t_app_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_app_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NOT NULL COMMENT '角色id',
  `menuId` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_app_role_menu`
--

LOCK TABLES `t_app_role_menu` WRITE;
/*!40000 ALTER TABLE `t_app_role_menu` DISABLE KEYS */;
INSERT INTO `t_app_role_menu` VALUES (8,1,2),(9,1,5),(10,1,3),(11,1,4),(12,1,6),(13,1,1),(18,2,1),(19,2,5),(20,2,2),(21,2,6);
/*!40000 ALTER TABLE `t_app_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_app_role`
--

DROP TABLE IF EXISTS `t_app_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_app_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理角色标识',
  `roleName` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '角色名称',
  `createTime` datetime NOT NULL COMMENT '角色创建时间',
  `lastUpdate` datetime NOT NULL COMMENT '角色最近修改时间',
  `status` int(11) NOT NULL COMMENT '0:禁用,1:启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_app_role`
--

LOCK TABLES `t_app_role` WRITE;
/*!40000 ALTER TABLE `t_app_role` DISABLE KEYS */;
INSERT INTO `t_app_role` VALUES (1,'系统管理员','2013-03-28 00:00:00','2013-04-01 13:30:53',1),(2,'普通用户','2013-03-28 00:00:00','2013-03-28 00:00:00',1);
/*!40000 ALTER TABLE `t_app_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_app_user`
--

DROP TABLE IF EXISTS `t_app_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_app_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `passWord` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `createTime` datetime NOT NULL,
  `lastUpdate` datetime NOT NULL,
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '0:禁用,1:启用',
  PRIMARY KEY (`id`),
  KEY `Index_1` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_app_user`
--

LOCK TABLES `t_app_user` WRITE;
/*!40000 ALTER TABLE `t_app_user` DISABLE KEYS */;
INSERT INTO `t_app_user` VALUES (1,'admin','b444e82d17a54d53a9e623fa586bf23ad7087df5e6f44848d01150fb0d5d523ea07ed99abdd074fc','2013-03-28 00:00:00','2013-03-28 00:00:00',1),(2,'test','43b9406871ef23d348fbdd3ddffb3eee69ae76fcba61525255dc0c6b04a44f6b9d7565a78718a5ae','2013-03-28 00:00:00','2013-03-28 00:00:00',1),(3,'test1','969e1f2618d6bfc67517a549ef3033d0de740791aed0aabd65a5dd7b3224fea962ad0d3cd6bc87bb','2013-03-31 20:04:19','2013-03-31 20:17:26',1),(5,'test3','d8f2bc4461f85d9dcfcc397ee8bee45b330ebc634a760d2865e4fd8633042c1fb36bbe4f7f4aad0c','2013-03-31 20:07:01','2013-03-31 20:07:22',1);
/*!40000 ALTER TABLE `t_app_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_app_menu`
--

DROP TABLE IF EXISTS `t_app_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_app_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menuName` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '菜单名称',
  `parentId` int(11) NOT NULL DEFAULT '0' COMMENT '父菜单ID',
  `menuCode` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '编号',
  `menuUrl` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '菜单URL',
  `urlTarget` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '页面打开位置',
  `navMenu` int(11) NOT NULL DEFAULT '0' COMMENT '0:不显示在导航菜单中,1:显示在导航菜单中',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `remark` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  `createTime` datetime NOT NULL,
  `lastUpdate` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_app_menu`
--

LOCK TABLES `t_app_menu` WRITE;
/*!40000 ALTER TABLE `t_app_menu` DISABLE KEYS */;
INSERT INTO `t_app_menu` VALUES (1,'应用基本功能',0,NULL,NULL,NULL,1,0,NULL,'2013-03-28 00:00:00','2013-03-28 00:00:00'),(2,'用户中心首页',1,'main','/u/main',NULL,1,0,NULL,'2013-03-28 00:00:00','2013-03-28 00:00:00'),(3,'系统管理',0,NULL,NULL,NULL,1,0,NULL,'2013-03-28 00:00:00','2013-03-28 00:00:00'),(4,'用户管理',3,'users','/u/users',NULL,1,0,NULL,'2013-03-28 00:00:00','2013-03-28 00:00:00'),(5,'角色管理',3,'roles','/u/roles',NULL,1,0,NULL,'2013-03-28 00:00:00','2013-03-28 00:00:00'),(6,'资源管理',3,'menus','/u/menus',NULL,1,0,NULL,'2013-03-28 00:00:00','2013-03-31 22:02:15');
/*!40000 ALTER TABLE `t_app_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_app_user_role`
--

DROP TABLE IF EXISTS `t_app_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_app_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL COMMENT '用户id',
  `roleId` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_app_user_role`
--

LOCK TABLES `t_app_user_role` WRITE;
/*!40000 ALTER TABLE `t_app_user_role` DISABLE KEYS */;
INSERT INTO `t_app_user_role` VALUES (3,2,1),(4,2,2),(5,5,1),(6,5,2),(7,1,1);
/*!40000 ALTER TABLE `t_app_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-04-02  0:13:40
