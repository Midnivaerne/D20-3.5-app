BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `Weapons` (
	`Item_ID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`Name` TEXT
);
CREATE TABLE IF NOT EXISTS `Spells` (
	`Item_ID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`Name` TEXT
);
CREATE TABLE IF NOT EXISTS `Skills` (
	`Item_ID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`Name` TEXT
);
CREATE TABLE IF NOT EXISTS `Races` (
	`Item_ID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`Name` TEXT
);
CREATE TABLE IF NOT EXISTS `RaceTemplates` (
	`Item_ID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`Name` TEXT
);
CREATE TABLE IF NOT EXISTS `Monsters` (
	`Item_ID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`Name` TEXT
);
CREATE TABLE IF NOT EXISTS `Heroes` (
	`Item_ID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`Name` TEXT
);
CREATE TABLE IF NOT EXISTS `Feats` (
	`Item_ID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`Name` TEXT
);
CREATE TABLE IF NOT EXISTS `Equipment` (
	`Item_ID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`Name` TEXT
);
CREATE TABLE IF NOT EXISTS `Classes` (
	`Item_ID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`Name` TEXT
);
CREATE TABLE IF NOT EXISTS `Armour` (
	`Item_ID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`Name`	TEXT
);
COMMIT;
