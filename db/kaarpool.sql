SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `kaarpool` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `kaarpool` ;

-- -----------------------------------------------------
-- Table `kaarpool`.`transactions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kaarpool`.`transactions` ;

CREATE  TABLE IF NOT EXISTS `kaarpool`.`transactions` (
  `tid` VARCHAR(30) NOT NULL ,
  `sender` VARCHAR(50) NOT NULL ,
  `receiver` VARCHAR(50) NOT NULL ,
  `amount` DOUBLE  NULL ,
  `date` TIMESTAMP NOT NULL ,
  PRIMARY KEY (`tid`) )
ENGINE = InnoDB;

CREATE UNIQUE INDEX `Transactions_UNIQUE` ON `kaarpool`.`transactions` (`tid` ASC) ;


-- -----------------------------------------------------
-- Table `kaarpool`.`personal_details`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kaarpool`.`personal_details` ;

CREATE  TABLE IF NOT EXISTS `kaarpool`.`personal_details` (
  `pid` VARCHAR(30) NOT NULL ,
  `username` VARCHAR(50) NOT NULL ,
  `dob` DATE NOT NULL ,
  `mobile` MEDIUMTEXT  NOT NULL ,
  `address` VARCHAR(100) NOT NULL ,
  `gender` TINYINT(1)  NOT NULL ,
  PRIMARY KEY (`pid`) )
ENGINE = InnoDB;

CREATE UNIQUE INDEX `PID_UNIQUE` ON `kaarpool`.`personal_details` (`pid` ASC) ;

CREATE UNIQUE INDEX `username_UNIQUE` ON `kaarpool`.`personal_details` (`username` ASC) ;


-- -----------------------------------------------------
-- Table `kaarpool`.`usermode`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kaarpool`.`usermode` ;

CREATE  TABLE IF NOT EXISTS `kaarpool`.`usermode` (
  `mid` INT NOT NULL ,
  `mode` VARCHAR(10) NULL ,
  PRIMARY KEY (`mid`) )
ENGINE = InnoDB;

CREATE UNIQUE INDEX `mid_UNIQUE` ON `kaarpool`.`usermode` (`mid` ASC) ;


-- -----------------------------------------------------
-- Table `kaarpool`.`travelpreferences`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kaarpool`.`travelpreferences` ;

CREATE  TABLE IF NOT EXISTS `kaarpool`.`travelpreferences` (
  `travelid` VARCHAR(30) NOT NULL ,
  `ladies` TINYINT(1)  NULL ,
  `gents` TINYINT(1)  NULL ,
  `handicap` TINYINT(1)  NULL ,
  `music` TINYINT(1)  NULL ,
  `smoke` TINYINT(1)  NULL ,
  PRIMARY KEY (`travelid`) )
ENGINE = InnoDB;

CREATE UNIQUE INDEX `travelid_UNIQUE` ON `kaarpool`.`travelpreferences` (`travelid` ASC) ;


-- -----------------------------------------------------
-- Table `kaarpool`.`timebased_defaultloc`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kaarpool`.`timebased_defaultloc` ;

CREATE  TABLE IF NOT EXISTS `kaarpool`.`timebased_defaultloc` (
  `tbid` VARCHAR(30) NOT NULL ,
  `days` VARCHAR(45) NULL ,
  `source` VARCHAR(45) NULL ,
  `destination` VARCHAR(45) NULL ,
  PRIMARY KEY (`tbid`) )
ENGINE = InnoDB;

CREATE UNIQUE INDEX `tbit_UNIQUE` ON `kaarpool`.`timebased_defaultloc` (`tbid` ASC) ;


-- -----------------------------------------------------
-- Table `kaarpool`.`socialnetwork_details`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kaarpool`.`socialnetwork_details` ;

CREATE  TABLE IF NOT EXISTS `kaarpool`.`socialnetwork_details` (
  `sid` VARCHAR(30) NOT NULL ,
  `loginid` VARCHAR(50) NOT NULL ,
  `loginpwd` VARCHAR(20) NOT NULL ,
  PRIMARY KEY (`sid`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kaarpool`.`kaarpoolnetwork_details`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kaarpool`.`kaarpoolnetwork_details` ;

CREATE  TABLE IF NOT EXISTS `kaarpool`.`kaarpoolnetwork_details` (
  `kid` VARCHAR(30) NOT NULL ,
  `loginid` VARCHAR(50) NOT NULL ,
  `loginpwd` VARCHAR(20) NOT NULL ,
  PRIMARY KEY (`kid`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kaarpool`.`networkdetails`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kaarpool`.`networkdetails` ;

CREATE  TABLE IF NOT EXISTS `kaarpool`.`networkdetails` (
  `nid` VARCHAR(30) NOT NULL ,
  `ndid` VARCHAR(30) NOT NULL ,
  PRIMARY KEY (`nid`) )
ENGINE = InnoDB;

CREATE UNIQUE INDEX `nid_UNIQUE` ON `kaarpool`.`networkdetails` (`nid` ASC) ;

CREATE UNIQUE INDEX `ndid_UNIQUE` ON `kaarpool`.`networkdetails` (`ndid` ASC) ;


-- -----------------------------------------------------
-- Table `kaarpool`.`preferences`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kaarpool`.`preferences` ;

CREATE  TABLE IF NOT EXISTS `kaarpool`.`preferences` (
  `pid` VARCHAR(30) NOT NULL ,
  `trid` VARCHAR(30) NOT NULL ,
  `tbaseid` VARCHAR(30) NOT NULL ,
  PRIMARY KEY (`pid`) ,
  CONSTRAINT `trid`
    FOREIGN KEY (`trid` )
    REFERENCES `kaarpool`.`travelpreferences` (`travelid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `tbaseid`
    FOREIGN KEY (`tbaseid` )
    REFERENCES `kaarpool`.`timebased_defaultloc` (`tbid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `pid_UNIQUE` ON `kaarpool`.`preferences` (`pid` ASC) ;

CREATE UNIQUE INDEX `trid_UNIQUE` ON `kaarpool`.`preferences` (`trid` ASC) ;

CREATE UNIQUE INDEX `tbaseid_UNIQUE` ON `kaarpool`.`preferences` (`tbaseid` ASC) ;

CREATE INDEX `trid` ON `kaarpool`.`preferences` (`trid` ASC) ;

CREATE INDEX `tbaseid` ON `kaarpool`.`preferences` (`tbaseid` ASC) ;


-- -----------------------------------------------------
-- Table `kaarpool`.`account_details`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kaarpool`.`account_details` ;

CREATE  TABLE IF NOT EXISTS `kaarpool`.`account_details` (
  `acid` VARCHAR(30) NOT NULL ,
  `limit` INT NULL ,
  PRIMARY KEY (`acid`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kaarpool`.`user_details`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kaarpool`.`user_details` ;

CREATE  TABLE IF NOT EXISTS `kaarpool`.`user_details` (
  `uid` VARCHAR(30) NOT NULL ,
  `prid` VARCHAR(30) NOT NULL ,
  `netid` VARCHAR(30) NOT NULL ,
  `mode` INT NOT NULL ,
  `preid` VARCHAR(30) NOT NULL ,
  `accountid` VARCHAR(30) NULL ,
  PRIMARY KEY (`uid`) ,
  CONSTRAINT `prid`
    FOREIGN KEY (`prid` )
    REFERENCES `kaarpool`.`personal_details` (`pid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `netid`
    FOREIGN KEY (`netid` )
    REFERENCES `kaarpool`.`networkdetails` (`nid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `mode`
    FOREIGN KEY (`mode` )
    REFERENCES `kaarpool`.`usermode` (`mid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `preid`
    FOREIGN KEY (`preid` )
    REFERENCES `kaarpool`.`preferences` (`pid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `accountid`
    FOREIGN KEY (`accountid` )
    REFERENCES `kaarpool`.`account_details` (`acid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `prid_UNIQUE` ON `kaarpool`.`user_details` (`prid` ASC) ;

CREATE UNIQUE INDEX `netid_UNIQUE` ON `kaarpool`.`user_details` (`netid` ASC) ;

CREATE UNIQUE INDEX `preid_UNIQUE` ON `kaarpool`.`user_details` (`preid` ASC) ;

CREATE UNIQUE INDEX `mode_UNIQUE` ON `kaarpool`.`user_details` (`mode` ASC) ;

CREATE INDEX `prid` ON `kaarpool`.`user_details` (`prid` ASC) ;

CREATE INDEX `netid` ON `kaarpool`.`user_details` (`netid` ASC) ;

CREATE INDEX `mode` ON `kaarpool`.`user_details` (`mode` ASC) ;

CREATE INDEX `preid` ON `kaarpool`.`user_details` (`preid` ASC) ;

CREATE INDEX `accountid` ON `kaarpool`.`user_details` (`accountid` ASC) ;


-- -----------------------------------------------------
-- Table `kaarpool`.`places`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kaarpool`.`places` ;

CREATE  TABLE IF NOT EXISTS `kaarpool`.`places` (
  `placeid` VARCHAR(30) NOT NULL ,
  `place` VARCHAR(50) NOT NULL ,
  `place_lat` DOUBLE  NOT NULL ,
  `place_lon` DOUBLE  NOT NULL ,
  PRIMARY KEY (`placeid`) )
ENGINE = InnoDB;

CREATE UNIQUE INDEX `placeid_UNIQUE` ON `kaarpool`.`places` (`placeid` ASC) ;


-- -----------------------------------------------------
-- Table `kaarpool`.`current_location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kaarpool`.`current_location` ;

CREATE  TABLE IF NOT EXISTS `kaarpool`.`current_location` (
  `clid` VARCHAR(30) NOT NULL ,
  `latitude` DOUBLE  NULL ,
  `longitude` DOUBLE  NULL ,
  `time` TIMESTAMP NULL ,
  `plid` VARCHAR(30) NOT NULL ,
  PRIMARY KEY (`clid`) ,
  CONSTRAINT `plid`
    FOREIGN KEY (`plid` )
    REFERENCES `kaarpool`.`places` (`placeid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `clid_UNIQUE` ON `kaarpool`.`current_location` (`clid` ASC) ;

CREATE INDEX `plid` ON `kaarpool`.`current_location` (`plid` ASC) ;


-- -----------------------------------------------------
-- Table `kaarpool`.`location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kaarpool`.`location` ;

CREATE  TABLE IF NOT EXISTS `kaarpool`.`location` (
  `lid` VARCHAR(30) NOT NULL ,
  `curlocid` VARCHAR(30) NULL ,
  PRIMARY KEY (`lid`) ,
  CONSTRAINT `curlocid`
    FOREIGN KEY (`curlocid` )
    REFERENCES `kaarpool`.`current_location` (`clid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `curlocid` ON `kaarpool`.`location` (`curlocid` ASC) ;


-- -----------------------------------------------------
-- Table `kaarpool`.`journey_details`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kaarpool`.`journey_details` ;

CREATE  TABLE IF NOT EXISTS `kaarpool`.`journey_details` (
  `jid` VARCHAR(30) NOT NULL ,
  `locid` VARCHAR(30) NULL ,
  `userid` VARCHAR(30) NULL ,
  `jsource` VARCHAR(45) NULL ,
  `jdestination` VARCHAR(45) NULL ,
  PRIMARY KEY (`jid`) ,
  CONSTRAINT `locid`
    FOREIGN KEY (`locid` )
    REFERENCES `kaarpool`.`location` (`lid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `userid`
    FOREIGN KEY (`userid` )
    REFERENCES `kaarpool`.`user_details` (`uid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `locid` ON `kaarpool`.`journey_details` (`locid` ASC) ;

CREATE INDEX `userid` ON `kaarpool`.`journey_details` (`userid` ASC) ;


-- -----------------------------------------------------
-- Table `kaarpool`.`ride`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kaarpool`.`ride` ;

CREATE  TABLE IF NOT EXISTS `kaarpool`.`ride` (
  `rid` VARCHAR(30) NOT NULL ,
  `jdid` VARCHAR(30) NULL ,
  `start_time` TIMESTAMP NULL ,
  `end_time` TIMESTAMP NULL ,
  PRIMARY KEY (`rid`) ,
  CONSTRAINT `jdit`
    FOREIGN KEY (`jdid` )
    REFERENCES `kaarpool`.`journey_details` (`jid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `jdit` ON `kaarpool`.`ride` (`jdid` ASC) ;


-- -----------------------------------------------------
-- Table `kaarpool`.`msgformat`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kaarpool`.`msgformat` ;

CREATE  TABLE IF NOT EXISTS `kaarpool`.`msgformat` (
  `msgid` VARCHAR(5) NOT NULL ,
  `message` VARCHAR(100) NULL ,
  PRIMARY KEY (`msgid`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kaarpool`.`message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kaarpool`.`message` ;

CREATE  TABLE IF NOT EXISTS `kaarpool`.`message` (
  `messageid` VARCHAR(5) NOT NULL ,
  `msgsender` VARCHAR(50) NOT NULL ,
  `msgreceiver` VARCHAR(50) NOT NULL ,
  `msgtime` TIMESTAMP NULL ,
  CONSTRAINT `messageid`
    FOREIGN KEY (`messageid` )
    REFERENCES `kaarpool`.`msgformat` (`msgid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `messageid` ON `kaarpool`.`message` (`messageid` ASC) ;


-- -----------------------------------------------------
-- Table `kaarpool`.`ride_members`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kaarpool`.`ride_members` ;

CREATE  TABLE IF NOT EXISTS `kaarpool`.`ride_members` (
  `rideid` VARCHAR(30) NULL ,
  `usrid` VARCHAR(30) NULL ,
  `role` VARCHAR(50) NULL )
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
