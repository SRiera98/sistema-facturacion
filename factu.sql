-- MySQL dump 10.13  Distrib 5.7.29, for Linux (x86_64)
--
-- Host: localhost    Database: facturacion
-- ------------------------------------------------------
-- Server version	5.7.29-0ubuntu0.18.04.1

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
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(13) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(120) COLLATE utf8_spanish_ci NOT NULL,
  `direccion` varchar(120) COLLATE utf8_spanish_ci NOT NULL,
  `cuit` varchar(11) COLLATE utf8_spanish_ci DEFAULT NULL,
  `categoria` varchar(13) COLLATE utf8_spanish_ci NOT NULL,
  `visibilidad` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'9210','Pedroo','ALsina 2020','0241270302','minorista',1),(2,'921019','Santiago Riera','Alsina 2161','32151241232','mayorista',1),(3,'129123','Santiago Miguel Riera','Alsina 2171','20412787038','minorista',1),(4,'91021','Pedro Martinez','Saavedra 912','20429783179','minorista',0),(5,'93213021','Pedro Joserto','Almada 2200','1049104845','mayorista',1),(6,'342425335325','La grosa SRL','Mitre 2234','20414627038','mayorista',1);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresas`
--

DROP TABLE IF EXISTS `empresas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empresas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cuit` varchar(11) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(120) COLLATE utf8_spanish_ci NOT NULL,
  `direccion` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `codigo_postal` varchar(11) COLLATE utf8_spanish_ci DEFAULT NULL,
  `ciudad` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `provincia` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `pais` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresas`
--

LOCK TABLES `empresas` WRITE;
/*!40000 ALTER TABLE `empresas` DISABLE KEYS */;
INSERT INTO `empresas` VALUES (2,'42412728921','DarkEmpireActualizado','Valentan 2181','1560','Badapang','Urlingan','Alemania'),(3,'4241272','DarkEmpire','Valentin Alsina 2161','5600','San Rafael','Mendoza','Argentina'),(4,'4241272','DarkEmpire','Valentin Alsina 2161','5600','San Rafael','Mendoza','Argentina'),(5,'4241272','DarkEmpirev2','Valentin Alsina 2161','5600','San Rafael','Mendoza','Argentina'),(6,'4241272','DarkEmpirev2','Valentin Alsina 2161','5600','San Rafael','Mendoza','Argentina'),(7,'4241272','DarkEmpirev2','Valentin Alsina 2161','5600','San Rafael','Mendoza','Argentina'),(8,'20414627038','La grosa SRL','Mitre 2234','5600','San Rafael','Mendoza','Argentina'),(9,'2','ssssssssss','Valentin Alsina 2161','5600','San Rafael','Mendoza','Argentina');
/*!40000 ALTER TABLE `empresas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facturas_encabezado`
--

DROP TABLE IF EXISTS `facturas_encabezado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `facturas_encabezado` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `numero` varchar(11) COLLATE utf8_spanish_ci NOT NULL,
  `letra` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `cliente_id` bigint(20) NOT NULL,
  `anulado` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `cliente` (`cliente_id`),
  CONSTRAINT `facturas_encabezado_ibfk_1` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facturas_encabezado`
--

LOCK TABLES `facturas_encabezado` WRITE;
/*!40000 ALTER TABLE `facturas_encabezado` DISABLE KEYS */;
INSERT INTO `facturas_encabezado` VALUES (1,'2020-03-01 19:46:31','12334521','C',2,0),(2,'2020-03-01 20:05:01','21265413123','A',5,0),(3,'2020-03-01 20:18:28','1276521','A',5,0),(4,'2020-03-02 17:41:41','5125521112','B',5,0),(5,'2020-03-02 17:42:49','564321212','A',5,0),(6,'2020-03-02 17:45:30','12642181','A',1,0),(7,'2020-03-02 17:50:14','5213612','A',1,0),(8,'2020-03-02 17:52:11','98765432','A',1,0),(9,'2020-03-02 17:53:21','52131261','B',1,0),(10,'2020-03-02 17:55:28','5165311','C',5,0),(11,'2020-03-02 18:01:45','53673112','C',5,0),(12,'2020-03-02 18:08:11','512311235','B',5,0),(13,'2020-03-02 18:11:55','12334521','B',2,0),(14,'2020-03-02 18:20:46','21321523','B',5,0),(15,'2020-03-02 18:23:26','9312312','A',5,0),(16,'2020-03-02 18:32:42','1233452131','B',6,0),(17,'2020-03-02 18:35:10','123142142','A',5,0),(18,'2020-03-02 18:37:08','5124131','B',3,0),(19,'2020-03-02 18:40:22','51242141','B',5,0),(20,'2020-03-02 18:48:51','521421312','B',2,0);
/*!40000 ALTER TABLE `facturas_encabezado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facturas_items`
--

DROP TABLE IF EXISTS `facturas_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `facturas_items` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `productos_id` bigint(20) NOT NULL,
  `facturas_encabezado_id` bigint(20) NOT NULL,
  `cantidad` decimal(10,0) NOT NULL DEFAULT '0',
  `precio_unitario` decimal(10,0) NOT NULL DEFAULT '0',
  `sub_total` decimal(10,0) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `facturas_encabezado_id` (`facturas_encabezado_id`),
  KEY `productos_id` (`productos_id`),
  CONSTRAINT `facturas_items_ibfk_1` FOREIGN KEY (`facturas_encabezado_id`) REFERENCES `facturas_encabezado` (`id`),
  CONSTRAINT `facturas_items_ibfk_2` FOREIGN KEY (`productos_id`) REFERENCES `productos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facturas_items`
--

LOCK TABLES `facturas_items` WRITE;
/*!40000 ALTER TABLE `facturas_items` DISABLE KEYS */;
INSERT INTO `facturas_items` VALUES (1,20,13,2,6000,12000),(2,20,14,2,3000,6000),(3,23,16,3,80000,240000),(4,22,17,2,150000,300000),(5,20,18,1,6000,6000),(6,18,19,3,150,450),(7,22,20,2,150000,300000);
/*!40000 ALTER TABLE `facturas_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facturas_pie`
--

DROP TABLE IF EXISTS `facturas_pie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `facturas_pie` (
  `facturas_encabezado_id` bigint(20) NOT NULL,
  `precio_total` decimal(10,0) NOT NULL DEFAULT '0',
  `observaciones` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`facturas_encabezado_id`),
  CONSTRAINT `facturas_pie_ibfk_1` FOREIGN KEY (`facturas_encabezado_id`) REFERENCES `facturas_encabezado` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facturas_pie`
--

LOCK TABLES `facturas_pie` WRITE;
/*!40000 ALTER TABLE `facturas_pie` DISABLE KEYS */;
/*!40000 ALTER TABLE `facturas_pie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(13) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `estado` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'007-xd','Fernet Branca 750ml','Bebida alcoholica con propiedades holisticas',0),(3,'09932','Manaos Uva 2000ml','Gaseosa Peligro',0),(5,'3912','Vodka','Alcohol',0),(6,'4912','Vodka Saborizado','Alcohol',0),(8,'21312312','awwwwwa','ddddeafafefea',0),(9,'1231313','WWWWWW','Dwadawdawffawa',0),(10,'2910391','Cerveza','Cerveza Andes',0),(11,'41213213','Televisor FullHD+','Es un televisor 120Hz',0),(12,'921019','Vino Tinto','es vino',0),(15,'213132131','wadwadaw','wweeeeeeww',0),(16,'921019','Vino','oawdoawdowaod',1),(17,'012931230','Manaos','Danger my friend',0),(18,'8923139123','Cerveza Quilmes','birra ameo al por mayor',1),(19,'940121093','Ryzen 5 1700X','Procesador AMD',0),(20,'129313012','Memoria RAM HyperX 2666MHZ','Ram DDR4',1),(21,'2131234999','PlayStation 4','Consola 500GB',0),(22,'g9123210','Smart TV 8K','Alto tele',1),(23,'8765432','Notebook ASUS','CORE I9 1TB SSD 5TB HDD GTX 2080TI 32GB RAM',1);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-02 20:07:35
