CREATE DATABASE IF NOT EXISTS `securitydb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `securitydb`;

CREATE TABLE acl_sid (
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	principal BOOLEAN NOT NULL,
	sid VARCHAR(100) NOT NULL,
	UNIQUE KEY unique_acl_sid (sid, principal)
) ENGINE=InnoDB;

CREATE TABLE acl_class (
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	class VARCHAR(100) NOT NULL,
	UNIQUE KEY uk_acl_class (class)
) ENGINE=InnoDB;

CREATE TABLE acl_object_identity (
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	object_id_class BIGINT UNSIGNED NOT NULL,
	object_id_identity BIGINT NOT NULL,
	parent_object BIGINT UNSIGNED,
	owner_sid BIGINT UNSIGNED,
	entries_inheriting BOOLEAN NOT NULL,
	UNIQUE KEY uk_acl_object_identity (object_id_class, object_id_identity),
	CONSTRAINT fk_acl_object_identity_parent FOREIGN KEY (parent_object) REFERENCES acl_object_identity (id),
	CONSTRAINT fk_acl_object_identity_class FOREIGN KEY (object_id_class) REFERENCES acl_class (id),
	CONSTRAINT fk_acl_object_identity_owner FOREIGN KEY (owner_sid) REFERENCES acl_sid (id)
) ENGINE=InnoDB;

CREATE TABLE acl_entry (
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	acl_object_identity BIGINT UNSIGNED NOT NULL,
	ace_order INTEGER NOT NULL,
	sid BIGINT UNSIGNED NOT NULL,
	mask INTEGER UNSIGNED NOT NULL,
	granting BOOLEAN NOT NULL,
	audit_success BOOLEAN NOT NULL,
	audit_failure BOOLEAN NOT NULL,
	UNIQUE KEY unique_acl_entry (acl_object_identity, ace_order),
	CONSTRAINT fk_acl_entry_object FOREIGN KEY (acl_object_identity) REFERENCES acl_object_identity (id),
	CONSTRAINT fk_acl_entry_acl FOREIGN KEY (sid) REFERENCES acl_sid (id)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `fixed_deposit_details`;
CREATE TABLE IF NOT EXISTS `fixed_deposit_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user` varchar(50) NOT NULL,
  `amount` varchar(50) NOT NULL,
  `tenure` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Contains fixed deposit details';

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(200) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE IF NOT EXISTS `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `acl_class` (`id`, `class`) VALUES
	(1, 'sample.spring.chapter16.domain.FixedDepositDetails');

INSERT INTO `acl_sid` (`id`, `principal`, `sid`) VALUES
	(3, 1, 'admin'),
	(1, 1, 'cust1'),
	(2, 1, 'cust2');

INSERT INTO `users` (`username`, `password`, `enabled`) VALUES
	('admin', '$2a$10$GL3WYjTbs5zlsAQrZU8BlOiKfOXaUulHA.G9.lk5/OA6EHuYQuvty', 1),
	('cust1', '$2a$10$xlmAHNLJXuZ1cdDloeqXCubD2Nsnw0k206hqccZ1gh1.VTCD2Scw2', 1),
	('cust2', '$2a$10$dcsvRkVmeQXig1UBiXdpS.X5N5osA/XPyflAatK5Oac2dfPrTTDOW', 1);
	
INSERT INTO `authorities` (`username`, `authority`) VALUES
	('admin', 'ROLE_ADMIN'),
	('cust1', 'ROLE_CUSTOMER'),
	('cust2', 'ROLE_CUSTOMER');