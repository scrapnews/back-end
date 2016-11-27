CREATE DATABASE `scrapnews_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE USER 'sn_user'@'aws' IDENTIFIED BY '514Montreal';

GRANT ALL PRIVILEGES ON scrapnews_db. * TO 'sn_user'@'aws';