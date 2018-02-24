DROP DATABASE
IF EXISTS looksteam;

CREATE DATABASE looksteam DEFAULT CHARACTER
SET utf8mb4;

USE looksteam;

CREATE TABLE player (
	steamid VARCHAR (255) PRIMARY KEY NOT NULL,
	communityvisibilitystate INT,
	profilestate INT,
	personaname VARCHAR (255),
	lastlogoff INT,
	commentpermission INT,
	profileurl VARCHAR (255),
	avatar VARCHAR (255),
	avatarmedium VARCHAR (255),
	avatarfull VARCHAR (255),
	personastate INT,
	realname VARCHAR (255),
	primaryclanid VARCHAR (255),
	timecreated INT,
	personastateflags INT,
	gameextrainfo VARCHAR (255),
	gameserverip VARCHAR (255),
	gameid INT,
	loccountrycode VARCHAR (255),
	locstatecode VARCHAR (255),
	loccityid VARCHAR (255),
	updatetime VARCHAR (255),
	extra_varchar VARCHAR (255),
	extra_int INT
);

CREATE TABLE app (
	appid INT PRIMARY KEY NOT NULL,
	appname VARCHAR (255),
	chname VARCHAR (255),
	price INT,
	img_icon_url VARCHAR (255),
	img_logo_url VARCHAR (255),
	pic_1 VARCHAR (255),
	pic_2 VARCHAR (255),
	pic_3 VARCHAR (255),
	pic_4 VARCHAR (255),
	pic_5 VARCHAR (255),
	updatetime VARCHAR (255),
	extra_varchar VARCHAR (255),
	extra_int INT
);

CREATE TABLE gameschame (
	appid INT NOT NULL,
	achname VARCHAR (255) NOT NULL,
	defaultvalue INT,
	displayName VARCHAR (255),
	hidden INT,
	icon VARCHAR (255),
	icongray VARCHAR (255),
	updatetime VARCHAR (255),
	extra_varchar VARCHAR (255),
	extra_int INT,
	PRIMARY KEY (appid, achname)
);

CREATE TABLE ownedgame (
	steamid VARCHAR (255) NOT NULL,
	appid INT NOT NULL,
	appname VARCHAR(255),
	playtime_2week INT,
	playtime_forever INT,
	img_icon_url VARCHAR (255),
	img_logo_url VARCHAR (255),
	has_community_visible_state TINYINT (1),
	updatetime VARCHAR (255),
	extra_varchar VARCHAR (255),
	extra_int INT,
	PRIMARY KEY (steamid, appid)
);

CREATE TABLE playerach (
	steamid VARCHAR (255) NOT NULL,
	appid INT NOT NULL,
	achname VARCHAR (255) NOT NULL,
	achieved INT,
	unlocktime INT,
	updatetime VARCHAR (255),
	extra_varchar VARCHAR (255),
	extra_int INT,
	PRIMARY KEY (steamid, appid, achname)
);

CREATE TABLE friend (
	steamid VARCHAR (255) NOT NULL,
	friendsteamid VARCHAR (255) NOT NULL,
	relationship VARCHAR (255),
	friend_since INT,
	updatetime VARCHAR (255),
	extra_varchar VARCHAR (255),
	extra_int INT,
	PRIMARY KEY (steamid, friendsteamid)
)