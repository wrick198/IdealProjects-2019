-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: localhost    Database: knowledge_network
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu0.16.04.1

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
-- Table structure for table `answer_sheet`
--

DROP TABLE IF EXISTS `answer_sheet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer_sheet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `end_time` datetime DEFAULT NULL,
  `passed` bit(1) NOT NULL,
  `score` double NOT NULL,
  `start_time` datetime NOT NULL,
  `paper_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6djbfaa1guu5mg408qite6mhb` (`paper_id`),
  KEY `FKn4jsswvufqb49wmw9fnmkjo9o` (`student_id`),
  CONSTRAINT `FK6djbfaa1guu5mg408qite6mhb` FOREIGN KEY (`paper_id`) REFERENCES `paper` (`paper_id`),
  CONSTRAINT `FKn4jsswvufqb49wmw9fnmkjo9o` FOREIGN KEY (`student_id`) REFERENCES `student` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city` (
  `id` int(11) NOT NULL,
  `first` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `has_school` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `municipality` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk00ylup7bsikbna9whybw9mmq` (`parent_id`),
  CONSTRAINT `FKk00ylup7bsikbna9whybw9mmq` FOREIGN KEY (`parent_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_num` int(11) DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  `school_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK96x0psi3ip7rge1n5ubwbhe3y` (`school_id`),
  CONSTRAINT `FK96x0psi3ip7rge1n5ubwbhe3y` FOREIGN KEY (`school_id`) REFERENCES `school` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `collect`
--

DROP TABLE IF EXISTS `collect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `in_time` datetime DEFAULT NULL,
  `topic_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp8y9rv9ji46fgnap2cpmr7foe` (`topic_id`),
  KEY `FK5n700968hfgd6clfd5xg36hjy` (`user_id`),
  CONSTRAINT `FK5n700968hfgd6clfd5xg36hjy` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKp8y9rv9ji46fgnap2cpmr7foe` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_pic` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `course_type` int(11) NOT NULL,
  `enabled` int(11) DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `entries` bigint(20) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  `hour` int(11) DEFAULT NULL,
  `introduction` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `publish_time` datetime DEFAULT NULL,
  `rank` int(11) DEFAULT NULL,
  `rate` float DEFAULT NULL,
  `school` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm1expnaas0onmafqpktmjixnx` (`subject_id`),
  KEY `FKsybhlxoejr4j3teomm5u2bx1n` (`teacher_id`),
  CONSTRAINT `FKm1expnaas0onmafqpktmjixnx` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`),
  CONSTRAINT `FKsybhlxoejr4j3teomm5u2bx1n` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_answer`
--

DROP TABLE IF EXISTS `course_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `publish_time` datetime DEFAULT NULL,
  `answer_id` int(11) DEFAULT NULL,
  `from_uid` int(11) DEFAULT NULL,
  `question` int(11) DEFAULT NULL,
  `to_uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh1k51lwktmixwih7e2soo6ke8` (`answer_id`),
  KEY `FKnmq7btq0khdsci6cpr7cvweqc` (`from_uid`),
  KEY `FKccxu3utmlyo1wpswyynm8v6dp` (`question`),
  KEY `FKnx4jtm9ilyjeuusbcqr3p5dc` (`to_uid`),
  CONSTRAINT `FKccxu3utmlyo1wpswyynm8v6dp` FOREIGN KEY (`question`) REFERENCES `course_question` (`id`),
  CONSTRAINT `FKh1k51lwktmixwih7e2soo6ke8` FOREIGN KEY (`answer_id`) REFERENCES `course_answer` (`id`),
  CONSTRAINT `FKnmq7btq0khdsci6cpr7cvweqc` FOREIGN KEY (`from_uid`) REFERENCES `user` (`id`),
  CONSTRAINT `FKnx4jtm9ilyjeuusbcqr3p5dc` FOREIGN KEY (`to_uid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_clicks`
--

DROP TABLE IF EXISTS `course_clicks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_clicks` (
  `course_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `count` bigint(20) NOT NULL,
  PRIMARY KEY (`course_id`,`user_id`),
  KEY `FK2gdxqonkwljevriq5765y37c4` (`user_id`),
  CONSTRAINT `FK2gdxqonkwljevriq5765y37c4` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKavcsxf26o57011vmrttnp8wqb` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_evaluate`
--

DROP TABLE IF EXISTS `course_evaluate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_evaluate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `publish_time` datetime DEFAULT NULL,
  `rate` float DEFAULT NULL,
  `thumb_count` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5e4ft7m4sxn5074otj4179b5u` (`course_id`),
  KEY `FKok3d3b8fhs4e3ad6uqu357r6u` (`user_id`),
  CONSTRAINT `FK5e4ft7m4sxn5074otj4179b5u` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `FKok3d3b8fhs4e3ad6uqu357r6u` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_has_knowledge_point`
--

DROP TABLE IF EXISTS `course_has_knowledge_point`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_has_knowledge_point` (
  `course_id` int(11) NOT NULL,
  `knowledge_point_id` int(11) NOT NULL,
  KEY `FKqgdi7glx99tk18i5net8xea2y` (`knowledge_point_id`),
  KEY `FKrodx8i2ildnod1ssg7gyr3jre` (`course_id`),
  CONSTRAINT `FKqgdi7glx99tk18i5net8xea2y` FOREIGN KEY (`knowledge_point_id`) REFERENCES `knowledge_point` (`id`),
  CONSTRAINT `FKrodx8i2ildnod1ssg7gyr3jre` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_has_resource`
--

DROP TABLE IF EXISTS `course_has_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_has_resource` (
  `course_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  KEY `FKbipynieots52d54tu7sluvy5u` (`resource_id`),
  KEY `FK5ke3bvrw9hotfb4ptlgtk0qo6` (`course_id`),
  CONSTRAINT `FK5ke3bvrw9hotfb4ptlgtk0qo6` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `FKbipynieots52d54tu7sluvy5u` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course_question`
--

DROP TABLE IF EXISTS `course_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `publish_time` datetime DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `asker_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnmrtilla8ex9n00il20tm1eid` (`asker_id`),
  KEY `FK4ctjqhjpmeqeud9huj0tjvia6` (`course_id`),
  CONSTRAINT `FK4ctjqhjpmeqeud9huj0tjvia6` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `FKnmrtilla8ex9n00il20tm1eid` FOREIGN KEY (`asker_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `difficulty`
--

DROP TABLE IF EXISTS `difficulty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `difficulty` (
  `difficulty_id` int(11) NOT NULL AUTO_INCREMENT,
  `difficulty_level` int(11) NOT NULL,
  `difficulty_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`difficulty_id`),
  UNIQUE KEY `UK_oxnb9tpt37buben57yev740x6` (`difficulty_level`),
  UNIQUE KEY `UK_9qkvxr1d49me08li7o00k2h3i` (`difficulty_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `key_agreement`
--

DROP TABLE IF EXISTS `key_agreement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `key_agreement` (
  `client_sequence` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `client_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `client_pub_key` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` datetime NOT NULL,
  `server_pri_key` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `server_pub_key` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`client_sequence`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `knowledge_network_manager`
--

DROP TABLE IF EXISTS `knowledge_network_manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `knowledge_network_manager` (
  `teacher_user_id` int(11) NOT NULL,
  PRIMARY KEY (`teacher_user_id`),
  CONSTRAINT `FKp5lhoqad1kihjt0nb6neoryn5` FOREIGN KEY (`teacher_user_id`) REFERENCES `teacher` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `knowledge_point`
--

DROP TABLE IF EXISTS `knowledge_point`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `knowledge_point` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `deleted` int(11) NOT NULL,
  `enable` int(11) NOT NULL,
  `grade` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `statement` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `knowledge_point_difficult_id` int(11) DEFAULT NULL,
  `knowledge_point_importance_id` int(11) DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm23oq1r44y3094dr7mqgp1srg` (`knowledge_point_difficult_id`),
  KEY `FKsg8jhelw8mculrlmte2laaqdh` (`knowledge_point_importance_id`),
  KEY `FKfmo2pi7nmdq873w0h64tfjbob` (`subject_id`),
  CONSTRAINT `FKfmo2pi7nmdq873w0h64tfjbob` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`),
  CONSTRAINT `FKm23oq1r44y3094dr7mqgp1srg` FOREIGN KEY (`knowledge_point_difficult_id`) REFERENCES `knowledge_point_difficult` (`id`),
  CONSTRAINT `FKsg8jhelw8mculrlmte2laaqdh` FOREIGN KEY (`knowledge_point_importance_id`) REFERENCES `knowledge_point_importance` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `knowledge_point_clicks`
--

DROP TABLE IF EXISTS `knowledge_point_clicks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `knowledge_point_clicks` (
  `knowledge_point_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `count` bigint(20) NOT NULL,
  PRIMARY KEY (`knowledge_point_id`,`user_id`),
  KEY `FKed6osuvx9u68xfstu1862fqdw` (`user_id`),
  CONSTRAINT `FKed6osuvx9u68xfstu1862fqdw` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKstffmm4b4dkyw9u1l8628y165` FOREIGN KEY (`knowledge_point_id`) REFERENCES `knowledge_point` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `knowledge_point_difficult`
--

DROP TABLE IF EXISTS `knowledge_point_difficult`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `knowledge_point_difficult` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `level` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `knowledge_point_has_knowledge_point`
--

DROP TABLE IF EXISTS `knowledge_point_has_knowledge_point`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `knowledge_point_has_knowledge_point` (
  `knowledge_point_id1` int(11) NOT NULL,
  `knowledge_point_id2` int(11) NOT NULL,
  `knowledge_point_relationship_type` int(11) DEFAULT NULL,
  `tightness` int(11) DEFAULT NULL,
  PRIMARY KEY (`knowledge_point_id1`,`knowledge_point_id2`),
  KEY `FKhvc6jbukfvuuk8rolbip3h3gi` (`knowledge_point_id2`),
  CONSTRAINT `FKhvc6jbukfvuuk8rolbip3h3gi` FOREIGN KEY (`knowledge_point_id2`) REFERENCES `knowledge_point` (`id`),
  CONSTRAINT `FKjemkr8p4bvyx7vog1y5dflt9i` FOREIGN KEY (`knowledge_point_id1`) REFERENCES `knowledge_point` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `knowledge_point_has_paper`
--

DROP TABLE IF EXISTS `knowledge_point_has_paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `knowledge_point_has_paper` (
  `knowledge_point_id` int(11) NOT NULL,
  `paper_id` int(11) NOT NULL,
  KEY `FK2cgr9x7sckvjhxhke0vy6lsjn` (`paper_id`),
  KEY `FKn390v6qswewmxsboe2ub8ta4b` (`knowledge_point_id`),
  CONSTRAINT `FK2cgr9x7sckvjhxhke0vy6lsjn` FOREIGN KEY (`paper_id`) REFERENCES `paper` (`paper_id`),
  CONSTRAINT `FKn390v6qswewmxsboe2ub8ta4b` FOREIGN KEY (`knowledge_point_id`) REFERENCES `knowledge_point` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `knowledge_point_has_quize`
--

DROP TABLE IF EXISTS `knowledge_point_has_quize`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `knowledge_point_has_quize` (
  `knowledge_point_id` int(11) NOT NULL,
  `quize_id` int(11) NOT NULL,
  KEY `FKpbhnjnoknuvrexigsmj7vemeh` (`quize_id`),
  KEY `FKtriie92jqqpkbb5d3lhfo4i4p` (`knowledge_point_id`),
  CONSTRAINT `FKpbhnjnoknuvrexigsmj7vemeh` FOREIGN KEY (`quize_id`) REFERENCES `quize` (`id`),
  CONSTRAINT `FKtriie92jqqpkbb5d3lhfo4i4p` FOREIGN KEY (`knowledge_point_id`) REFERENCES `knowledge_point` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `knowledge_point_has_resource`
--

DROP TABLE IF EXISTS `knowledge_point_has_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `knowledge_point_has_resource` (
  `knowledge_point_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  KEY `FK5x0ux8rjcivtovtcj26yhke3q` (`resource_id`),
  KEY `FK9hep00d2xc0xrpa20rvs0ki5k` (`knowledge_point_id`),
  CONSTRAINT `FK5x0ux8rjcivtovtcj26yhke3q` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`id`),
  CONSTRAINT `FK9hep00d2xc0xrpa20rvs0ki5k` FOREIGN KEY (`knowledge_point_id`) REFERENCES `knowledge_point` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `knowledge_point_has_tag`
--

DROP TABLE IF EXISTS `knowledge_point_has_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `knowledge_point_has_tag` (
  `knowledge_point_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL,
  KEY `FKipqwm6f8dj6yiu3d8gxi4jr9d` (`tag_id`),
  KEY `FK4y6ib8txi8452bmt7bw2ser9b` (`knowledge_point_id`),
  CONSTRAINT `FK4y6ib8txi8452bmt7bw2ser9b` FOREIGN KEY (`knowledge_point_id`) REFERENCES `knowledge_point` (`id`),
  CONSTRAINT `FKipqwm6f8dj6yiu3d8gxi4jr9d` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `knowledge_point_importance`
--

DROP TABLE IF EXISTS `knowledge_point_importance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `knowledge_point_importance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `level` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_mvh833m6ay5fjnmats5ko1ybf` (`level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `label`
--

DROP TABLE IF EXISTS `label`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `label` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `in_time` datetime NOT NULL,
  `intro` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `topic_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_defh0r2wr6e5g7vu7vanv4pxa` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `learning_path`
--

DROP TABLE IF EXISTS `learning_path`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `learning_path` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `last_learning_time` datetime DEFAULT NULL,
  `course_id` int(11) NOT NULL,
  `student_user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhqxelldo49p4ojfw2odwcde7h` (`course_id`),
  KEY `FK7etug7ptt5isb6vdjunbr3uqc` (`student_user_id`),
  CONSTRAINT `FK7etug7ptt5isb6vdjunbr3uqc` FOREIGN KEY (`student_user_id`) REFERENCES `student` (`user_id`),
  CONSTRAINT `FKhqxelldo49p4ojfw2odwcde7h` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `learning_path_has_knowledge_point`
--

DROP TABLE IF EXISTS `learning_path_has_knowledge_point`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `learning_path_has_knowledge_point` (
  `learning_path_id` int(11) NOT NULL,
  `knowledge_point_id` int(11) NOT NULL,
  KEY `FK4rh83u6b3r2v05cfsgg66j52k` (`knowledge_point_id`),
  KEY `FKj42n5dy4jh4o3o9puilxc7bvi` (`learning_path_id`),
  CONSTRAINT `FK4rh83u6b3r2v05cfsgg66j52k` FOREIGN KEY (`knowledge_point_id`) REFERENCES `knowledge_point` (`id`),
  CONSTRAINT `FKj42n5dy4jh4o3o9puilxc7bvi` FOREIGN KEY (`learning_path_id`) REFERENCES `learning_path` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `learning_plan`
--

DROP TABLE IF EXISTS `learning_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `learning_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `end_datetime` datetime DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `start_datetime` datetime DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `student_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqgrlior5qq0v9w0k51fca8r2v` (`course_id`),
  KEY `FKp1orqeke6wj8tmdk4jmaja1ag` (`student_user_id`),
  CONSTRAINT `FKp1orqeke6wj8tmdk4jmaja1ag` FOREIGN KEY (`student_user_id`) REFERENCES `student` (`user_id`),
  CONSTRAINT `FKqgrlior5qq0v9w0k51fca8r2v` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `learning_plan_has_knowledge_point`
--

DROP TABLE IF EXISTS `learning_plan_has_knowledge_point`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `learning_plan_has_knowledge_point` (
  `learning_plan_id` int(11) NOT NULL,
  `knowledge_point_id` int(11) NOT NULL,
  KEY `FKsb0efpigj8rkyflkf7laxukkw` (`knowledge_point_id`),
  KEY `FK7imtl3j18hj3up499fxbm9qlf` (`learning_plan_id`),
  CONSTRAINT `FK7imtl3j18hj3up499fxbm9qlf` FOREIGN KEY (`learning_plan_id`) REFERENCES `learning_plan` (`id`),
  CONSTRAINT `FKsb0efpigj8rkyflkf7laxukkw` FOREIGN KEY (`knowledge_point_id`) REFERENCES `knowledge_point` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `action` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `in_time` datetime DEFAULT NULL,
  `scan` bit(1) NOT NULL,
  `target_user_id` int(11) DEFAULT NULL,
  `topic_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKopmlfyllvr1wncw4u4mi2kln9` (`target_user_id`),
  KEY `FKmtvnu86vsdt45aqlrjrhn8wf0` (`topic_id`),
  KEY `FKb0yvoep4h4k92ipon31wmdf7e` (`user_id`),
  CONSTRAINT `FKb0yvoep4h4k92ipon31wmdf7e` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKmtvnu86vsdt45aqlrjrhn8wf0` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`),
  CONSTRAINT `FKopmlfyllvr1wncw4u4mi2kln9` FOREIGN KEY (`target_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `oauth_access_token`
--

DROP TABLE IF EXISTS `oauth_access_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `authentication` longblob NOT NULL,
  `authentication_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `client_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `refresh_token` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `token` longblob NOT NULL,
  `user_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`token_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `oauth_client_details`
--

DROP TABLE IF EXISTS `oauth_client_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `authorities` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `authorized_grant_types` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `client_secret` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `resource_ids` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `scope` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `web_server_redirect_uri` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `oauth_code`
--

DROP TABLE IF EXISTS `oauth_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_code` (
  `code` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `authentication` longblob NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `oauth_refresh_token`
--

DROP TABLE IF EXISTS `oauth_refresh_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `authentication` longblob NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `token` longblob NOT NULL,
  PRIMARY KEY (`token_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `paper`
--

DROP TABLE IF EXISTS `paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paper` (
  `paper_id` int(11) NOT NULL AUTO_INCREMENT,
  `answer` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `available` int(11) NOT NULL,
  `content` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` datetime NOT NULL,
  `duration` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pass_point` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `summary` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `total_point` int(11) NOT NULL,
  `paperType_paper_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`paper_id`),
  KEY `FK1qlo06bxefaknu37clhk2i7o5` (`paperType_paper_type_id`),
  CONSTRAINT `FK1qlo06bxefaknu37clhk2i7o5` FOREIGN KEY (`paperType_paper_type_id`) REFERENCES `paper_type` (`paper_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `paper_type`
--

DROP TABLE IF EXISTS `paper_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paper_type` (
  `paper_type_id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `paper_type` int(11) NOT NULL,
  PRIMARY KEY (`paper_type_id`),
  UNIQUE KEY `UK_taf95dx61tqxv35h9e1ntufll` (`name`),
  UNIQUE KEY `UK_vws6q7358udio6qownts5h9c` (`paper_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `parent`
--

DROP TABLE IF EXISTS `parent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parent` (
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `FKl4w905h1321m2ide56ov5efb0` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `question_type`
--

DROP TABLE IF EXISTS `question_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_type` (
  `question_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `question_type_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `question_type` int(11) NOT NULL,
  PRIMARY KEY (`question_type_id`),
  UNIQUE KEY `UK_o11digdntrnnnm2hb08xn0u1j` (`question_type_name`),
  UNIQUE KEY `UK_1kcrmiacyykdoxloc1w8ngev4` (`question_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `quize`
--

DROP TABLE IF EXISTS `quize`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quize` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `anlysis` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `answer` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `content` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` datetime NOT NULL,
  `examing_point` float NOT NULL,
  `expose_times` int(11) NOT NULL,
  `is_avaliable` int(11) NOT NULL,
  `last_modify` datetime NOT NULL,
  `quize_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `right_times` int(11) NOT NULL,
  `url` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `wrong_times` int(11) NOT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `difficulty_id` int(11) DEFAULT NULL,
  `question_type_id` int(11) DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsmy5cj302oupdgfu87957homa` (`creator_id`),
  KEY `FK7aev4gql2omgyqvyjxcwk4mbx` (`difficulty_id`),
  KEY `FKatwh0h4oti21vrrj4rso342x0` (`question_type_id`),
  KEY `FKawabaj95ueijxkfvkblg813e2` (`subject_id`),
  CONSTRAINT `FK7aev4gql2omgyqvyjxcwk4mbx` FOREIGN KEY (`difficulty_id`) REFERENCES `difficulty` (`difficulty_id`),
  CONSTRAINT `FKatwh0h4oti21vrrj4rso342x0` FOREIGN KEY (`question_type_id`) REFERENCES `question_type` (`question_type_id`),
  CONSTRAINT `FKawabaj95ueijxkfvkblg813e2` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`),
  CONSTRAINT `FKsmy5cj302oupdgfu87957homa` FOREIGN KEY (`creator_id`) REFERENCES `teacher` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `quizes_has_tags`
--

DROP TABLE IF EXISTS `quizes_has_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quizes_has_tags` (
  `quize_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL,
  KEY `FKno31h84ju5btma0mph1r6xypj` (`tag_id`),
  KEY `FKnvlmjvcaleuhhhj3q7vvwa9tn` (`quize_id`),
  CONSTRAINT `FKno31h84ju5btma0mph1r6xypj` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`),
  CONSTRAINT `FKnvlmjvcaleuhhhj3q7vvwa9tn` FOREIGN KEY (`quize_id`) REFERENCES `quize` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `quizes_in_papers`
--

DROP TABLE IF EXISTS `quizes_in_papers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quizes_in_papers` (
  `quize_id` int(11) NOT NULL,
  `paper_id` int(11) NOT NULL,
  KEY `FK5x5euxe10xu239wqc5h2idtyy` (`paper_id`),
  KEY `FKqt13e3w9msiix2l7908rb5dgd` (`quize_id`),
  CONSTRAINT `FK5x5euxe10xu239wqc5h2idtyy` FOREIGN KEY (`paper_id`) REFERENCES `paper` (`paper_id`),
  CONSTRAINT `FKqt13e3w9msiix2l7908rb5dgd` FOREIGN KEY (`quize_id`) REFERENCES `quize` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `reply`
--

DROP TABLE IF EXISTS `reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `down` int(11) NOT NULL,
  `down_ids` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `in_time` datetime NOT NULL,
  `up` int(11) NOT NULL,
  `updown` int(11) NOT NULL,
  `up_ids` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `topic_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgf3tac9fa0n9vf62c7eh5eb4y` (`topic_id`),
  KEY `FKapyyxlgntertu5okpkr685ir9` (`user_id`),
  CONSTRAINT `FKapyyxlgntertu5okpkr685ir9` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKgf3tac9fa0n9vf62c7eh5eb4y` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deleted` int(11) NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `download` int(11) NOT NULL,
  `enable` int(11) NOT NULL,
  `grade` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `need_pay` int(11) NOT NULL,
  `rank` int(11) DEFAULT NULL,
  `score` double DEFAULT NULL,
  `score_times` int(11) DEFAULT NULL,
  `upload_datetime` datetime NOT NULL,
  `url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `resource_type_id` int(11) NOT NULL,
  `subject_id` int(11) DEFAULT NULL,
  `uploader_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbma5417n022ufdra8d99qscxv` (`resource_type_id`),
  KEY `FKl8mllfer0gbkligpw4k5u3qr8` (`subject_id`),
  KEY `FKcp0tx2w0sbt1sxg9bxyhyjh89` (`uploader_user_id`),
  CONSTRAINT `FKbma5417n022ufdra8d99qscxv` FOREIGN KEY (`resource_type_id`) REFERENCES `resource_type` (`id`),
  CONSTRAINT `FKcp0tx2w0sbt1sxg9bxyhyjh89` FOREIGN KEY (`uploader_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKl8mllfer0gbkligpw4k5u3qr8` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `resource_clicks`
--

DROP TABLE IF EXISTS `resource_clicks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource_clicks` (
  `resource_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `count` bigint(20) NOT NULL,
  PRIMARY KEY (`resource_id`,`user_id`),
  KEY `FK85xjqgwbxb4bepcx5d41h9e57` (`user_id`),
  CONSTRAINT `FK85xjqgwbxb4bepcx5d41h9e57` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKkugvr2fmhhwvcqlf6igaxcnju` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `resource_contains_paper`
--

DROP TABLE IF EXISTS `resource_contains_paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource_contains_paper` (
  `resource_id` int(11) NOT NULL,
  `paper_id` int(11) NOT NULL,
  KEY `FK3o24d4v2457ec7eewf3k5y4aw` (`paper_id`),
  KEY `FK8akbx7lffn8uo8jh3bnun6fcx` (`resource_id`),
  CONSTRAINT `FK3o24d4v2457ec7eewf3k5y4aw` FOREIGN KEY (`paper_id`) REFERENCES `paper` (`paper_id`),
  CONSTRAINT `FK8akbx7lffn8uo8jh3bnun6fcx` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `resource_type`
--

DROP TABLE IF EXISTS `resource_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `school`
--

DROP TABLE IF EXISTS `school`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `school` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `short_spell` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `spell` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `status` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `user_define` int(11) DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL,
  `county_id` int(11) DEFAULT NULL,
  `province_id` int(11) DEFAULT NULL,
  `school_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKayckpg460dxb87jqrmm4ek6j5` (`city_id`),
  KEY `FKlb0t07d39osn4mrx8hygap7vi` (`county_id`),
  KEY `FKdaeuwh2irkso96j31gc8v7edj` (`province_id`),
  KEY `FKjqkj8gpj5cola94ij577m23oq` (`school_type_id`),
  CONSTRAINT `FKayckpg460dxb87jqrmm4ek6j5` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`),
  CONSTRAINT `FKdaeuwh2irkso96j31gc8v7edj` FOREIGN KEY (`province_id`) REFERENCES `city` (`id`),
  CONSTRAINT `FKjqkj8gpj5cola94ij577m23oq` FOREIGN KEY (`school_type_id`) REFERENCES `school_type` (`id`),
  CONSTRAINT `FKlb0t07d39osn4mrx8hygap7vi` FOREIGN KEY (`county_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `school_type`
--

DROP TABLE IF EXISTS `school_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `school_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `section`
--

DROP TABLE IF EXISTS `section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `section` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rwqtt5x96oahjdtqt20vxu4um` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `grade` int(11) DEFAULT NULL,
  `student_id_num` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `class_id` int(11) DEFAULT NULL,
  `school_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FKdwhkib64u47wc4yo4hk0cub90` (`class_id`),
  KEY `FK1vm0oqhk9viil6eocn49rj1l9` (`school_id`),
  CONSTRAINT `FK1vm0oqhk9viil6eocn49rj1l9` FOREIGN KEY (`school_id`) REFERENCES `school` (`id`),
  CONSTRAINT `FKdwhkib64u47wc4yo4hk0cub90` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`),
  CONSTRAINT `FKk5m148xqefonqw7bgnpm0snwj` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student_answer`
--

DROP TABLE IF EXISTS `student_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `answer` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `earned_score` double NOT NULL,
  `answer_sheet_id` int(11) NOT NULL,
  `quize_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1klkbtt5f9j430tx8kkk81od8` (`answer_sheet_id`),
  KEY `FKchh31itn48whn75pk47nd1e94` (`quize_id`),
  CONSTRAINT `FK1klkbtt5f9j430tx8kkk81od8` FOREIGN KEY (`answer_sheet_id`) REFERENCES `answer_sheet` (`id`),
  CONSTRAINT `FKchh31itn48whn75pk47nd1e94` FOREIGN KEY (`quize_id`) REFERENCES `quize` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student_collected_course`
--

DROP TABLE IF EXISTS `student_collected_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_collected_course` (
  `student_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  KEY `FKmima7y0t15yh2euil52m4ghr1` (`course_id`),
  KEY `FKcpifo4ccp33o3b4q4dsiegfdi` (`student_id`),
  CONSTRAINT `FKcpifo4ccp33o3b4q4dsiegfdi` FOREIGN KEY (`student_id`) REFERENCES `student` (`user_id`),
  CONSTRAINT `FKmima7y0t15yh2euil52m4ghr1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student_has_course`
--

DROP TABLE IF EXISTS `student_has_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_has_course` (
  `student_user_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  KEY `FKkbksakfkcjg6wtp783borki7t` (`course_id`),
  KEY `FKko0f32uja9y93ophivoe64osl` (`student_user_id`),
  CONSTRAINT `FKkbksakfkcjg6wtp783borki7t` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `FKko0f32uja9y93ophivoe64osl` FOREIGN KEY (`student_user_id`) REFERENCES `student` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student_has_done_quize`
--

DROP TABLE IF EXISTS `student_has_done_quize`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_has_done_quize` (
  `quize_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `practice_time` int(11) NOT NULL,
  `right_time` int(11) NOT NULL,
  `wrong_time` int(11) NOT NULL,
  PRIMARY KEY (`quize_id`,`student_id`),
  KEY `FKhfj5aajjdip9bj6pnfqvejyx2` (`student_id`),
  CONSTRAINT `FKhfj5aajjdip9bj6pnfqvejyx2` FOREIGN KEY (`student_id`) REFERENCES `student` (`user_id`),
  CONSTRAINT `FKhtsl1ldjm0k85scame3wg5gf1` FOREIGN KEY (`quize_id`) REFERENCES `quize` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student_has_parent`
--

DROP TABLE IF EXISTS `student_has_parent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_has_parent` (
  `student_user_id` int(11) NOT NULL,
  `parent_user_id` int(11) NOT NULL,
  KEY `FKr0ijaw4g7gtxet0rb292u074a` (`parent_user_id`),
  KEY `FKaryrcp6yy3nd7mvseeo5uf3ny` (`student_user_id`),
  CONSTRAINT `FKaryrcp6yy3nd7mvseeo5uf3ny` FOREIGN KEY (`student_user_id`) REFERENCES `student` (`user_id`),
  CONSTRAINT `FKr0ijaw4g7gtxet0rb292u074a` FOREIGN KEY (`parent_user_id`) REFERENCES `parent` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student_wrong_note_subject`
--

DROP TABLE IF EXISTS `student_wrong_note_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_wrong_note_subject` (
  `student_id` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL,
  `cover_url` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`student_id`,`subject_id`),
  KEY `FKsf33veexta6mjthglvjrxn36h` (`subject_id`),
  CONSTRAINT `FKlmtjhii08h8c6o95d75qkwpdk` FOREIGN KEY (`student_id`) REFERENCES `student` (`user_id`),
  CONSTRAINT `FKsf33veexta6mjthglvjrxn36h` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_p1jgir6qcpmqnxt4a8105wsot` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `symmetric_encryption`
--

DROP TABLE IF EXISTS `symmetric_encryption`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `symmetric_encryption` (
  `client_sequence` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `client_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `shared_key` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `timestame_seq` bigint(20) NOT NULL,
  PRIMARY KEY (`client_sequence`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `system_manager`
--

DROP TABLE IF EXISTS `system_manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_manager` (
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `FKftmisbi8vvw3h3evm981mxyc` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1wdpsed5kna2y38hnbgrnhi5b` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `certificate_num` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` int(11) NOT NULL,
  `school_id` int(11) DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FKrg46bnmgbcccayv14naymqg3r` (`school_id`),
  KEY `FKnmpwvbgxwxhy9ifnnsjcj1ir9` (`subject_id`),
  CONSTRAINT `FKnmpwvbgxwxhy9ifnnsjcj1ir9` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`),
  CONSTRAINT `FKpb6g6pahj1mr2ijg92r7m1xlh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKrg46bnmgbcccayv14naymqg3r` FOREIGN KEY (`school_id`) REFERENCES `school` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `teacher_check_answer_sheet`
--

DROP TABLE IF EXISTS `teacher_check_answer_sheet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_check_answer_sheet` (
  `teacher_id` int(11) NOT NULL,
  `answersheet_id` int(11) NOT NULL,
  KEY `FK3avhc08j0wq13ivlbpusg4il8` (`answersheet_id`),
  KEY `FKmasshpln2q92m8h7gcu8aiuyj` (`teacher_id`),
  CONSTRAINT `FK3avhc08j0wq13ivlbpusg4il8` FOREIGN KEY (`answersheet_id`) REFERENCES `answer_sheet` (`id`),
  CONSTRAINT `FKmasshpln2q92m8h7gcu8aiuyj` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `teacher_has_paper`
--

DROP TABLE IF EXISTS `teacher_has_paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_has_paper` (
  `teacher_id` int(11) NOT NULL,
  `paper_id` int(11) NOT NULL,
  KEY `FKf9bf6p7wyv93l249g0qb4uve9` (`paper_id`),
  KEY `FK89lohelvfxwf9s007bgi2nxsj` (`teacher_id`),
  CONSTRAINT `FK89lohelvfxwf9s007bgi2nxsj` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`user_id`),
  CONSTRAINT `FKf9bf6p7wyv93l249g0qb4uve9` FOREIGN KEY (`paper_id`) REFERENCES `paper` (`paper_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `teaching_plan`
--

DROP TABLE IF EXISTS `teaching_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teaching_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `teaching_plan_has_knowledge_point`
--

DROP TABLE IF EXISTS `teaching_plan_has_knowledge_point`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teaching_plan_has_knowledge_point` (
  `teaching_plan_id` int(11) NOT NULL,
  `knowledge_point_id` int(11) NOT NULL,
  KEY `FKcdh7pmrgbm0ltkg73gytj1767` (`knowledge_point_id`),
  KEY `FKnm2hobyay4umbfovhc3r9cyng` (`teaching_plan_id`),
  CONSTRAINT `FKcdh7pmrgbm0ltkg73gytj1767` FOREIGN KEY (`knowledge_point_id`) REFERENCES `knowledge_point` (`id`),
  CONSTRAINT `FKnm2hobyay4umbfovhc3r9cyng` FOREIGN KEY (`teaching_plan_id`) REFERENCES `teaching_plan` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `good` bit(1) DEFAULT NULL,
  `in_time` datetime NOT NULL,
  `label_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_reply_time` datetime DEFAULT NULL,
  `topic_lock` bit(1) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `reply_count` int(11) DEFAULT NULL,
  `tab` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `top` bit(1) DEFAULT NULL,
  `up_ids` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `view` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK38wu074adxipuj6a9ifd7jv5f` (`user_id`),
  CONSTRAINT `FK38wu074adxipuj6a9ifd7jv5f` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `enable` int(11) NOT NULL,
  `image_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_login_datetime` datetime DEFAULT NULL,
  `logoff` int(11) NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `real_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `register_datetime` datetime NOT NULL,
  `sex` int(11) DEFAULT NULL,
  `user_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_lqjrcobrh9jc8wpcar64q1bfh` (`user_name`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_589idila9li6a4arw1t8ht1gx` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_has_download_resource`
--

DROP TABLE IF EXISTS `user_has_download_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_has_download_resource` (
  `id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  KEY `FK6axcjusop1su94pw6dkaakf9j` (`resource_id`),
  KEY `FKkuv6ruv5oe0yfumatbl7rgqdg` (`id`),
  CONSTRAINT `FK6axcjusop1su94pw6dkaakf9j` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`id`),
  CONSTRAINT `FKkuv6ruv5oe0yfumatbl7rgqdg` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_has_mark_resource_score`
--

DROP TABLE IF EXISTS `user_has_mark_resource_score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_has_mark_resource_score` (
  `resource_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  PRIMARY KEY (`resource_id`,`user_id`),
  KEY `FKou3txr6o4uwx0uosvwe6ohlr8` (`user_id`),
  CONSTRAINT `FKd7y695uqy1ivghb93p3jkutv4` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`id`),
  CONSTRAINT `FKou3txr6o4uwx0uosvwe6ohlr8` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_has_user_role`
--

DROP TABLE IF EXISTS `user_has_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_has_user_role` (
  `user_id` int(11) NOT NULL,
  `user_role_id` int(11) NOT NULL,
  `initial_role` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`user_role_id`),
  KEY `FK4hkrmt1rj3ohy3vaf1u3pe3mr` (`user_role_id`),
  CONSTRAINT `FK4hkrmt1rj3ohy3vaf1u3pe3mr` FOREIGN KEY (`user_role_id`) REFERENCES `user_role` (`id`),
  CONSTRAINT `FKc6nuqruaro9cabdfrgotpwpfu` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_permission`
--

DROP TABLE IF EXISTS `user_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_c0210mhu8ug1x46rokkiorusj` (`permission`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `role` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_s21d8k5lywjjc7inw14brj6ro` (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_role_has_user_permission`
--

DROP TABLE IF EXISTS `user_role_has_user_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role_has_user_permission` (
  `user_role_id` int(11) NOT NULL,
  `user_permission_id` int(11) NOT NULL,
  KEY `FKnc7ly9tqchodclvtx85bj41pq` (`user_permission_id`),
  KEY `FKfiykky4jurf6vedgfxjqool9k` (`user_role_id`),
  CONSTRAINT `FKfiykky4jurf6vedgfxjqool9k` FOREIGN KEY (`user_role_id`) REFERENCES `user_role` (`id`),
  CONSTRAINT `FKnc7ly9tqchodclvtx85bj41pq` FOREIGN KEY (`user_permission_id`) REFERENCES `user_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `wrong_note`
--

DROP TABLE IF EXISTS `wrong_note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wrong_note` (
  `quize_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `subject_Id` int(11) NOT NULL,
  `practice_time` int(11) NOT NULL,
  PRIMARY KEY (`quize_id`,`student_id`,`subject_Id`),
  KEY `FKq85g68c08ofh683c2j6u2mj6y` (`student_id`),
  KEY `FKs8vay9eec64jfc56e1y9ug7hc` (`subject_Id`),
  CONSTRAINT `FKnvkc2ey68hv9m6pf333x73i86` FOREIGN KEY (`quize_id`) REFERENCES `quize` (`id`),
  CONSTRAINT `FKq85g68c08ofh683c2j6u2mj6y` FOREIGN KEY (`student_id`) REFERENCES `student` (`user_id`),
  CONSTRAINT `FKs8vay9eec64jfc56e1y9ug7hc` FOREIGN KEY (`subject_Id`) REFERENCES `subject` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-07 21:12:42
