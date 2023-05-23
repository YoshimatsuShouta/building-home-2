-- original
DROP TABLE IF EXISTS original CASCADE;
CREATE TABLE original (
	id serial NOT NULL,
	prefectures_code integer,
	prefectures character varying (255),
	prefectures_kana character varying (255),
	prefectures_rome character varying (255),
	municipalities_code integer,
	municipalities character varying (255),
	municipalities_kana character varying (255),
	municipalities_rome character varying (255),
	address character varying (255),
	address_kana character varying (255),
	address_rome character varying (255),
	Latitude double precision,
	Longitude double precision,
	CONSTRAINT original_PKC PRIMARY KEY (id)
);
-- prefectures（都道府県）テーブルの作成
DROP TABLE IF EXISTS prefectures CASCADE;
CREATE TABLE prefectures (
  id INT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  name_kana VARCHAR(255),
  name_rome VARCHAR(255)
);

-- municipalities（市町村）テーブルの作成
DROP TABLE IF EXISTS municipalities CASCADE;
CREATE TABLE municipalities (
  id INT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  name_kana VARCHAR(255),
  name_rome VARCHAR(255),
  prefecture_id INT,
  FOREIGN KEY (prefecture_id) REFERENCES prefectures(id)
);

-- address（住所）テーブルの作成 
DROP TABLE IF EXISTS address CASCADE;
	CREATE TABLE address (
	id serial NOT NULL,
	name character varying (255),
	name_kana character varying (255),
	name_rome character varying (255),
	Latitude double precision,
	Longitude double precision,
	prefecture_id INT,
	municipality_id INT,
	CONSTRAINT address_PKC PRIMARY KEY (id),
	FOREIGN KEY (prefecture_id) REFERENCES prefectures (id),
	FOREIGN KEY (municipality_id) REFERENCES municipalities (id)
);


-- price_of_land
DROP TABLE IF EXISTS price_of_land CASCADE;
CREATE TABLE price_of_land (
	id serial NOT NULL,
	municipality_id INTEGER NOT NULL,
	address_id INTEGER NOT NULL,
	address_num VARCHAR(255) NOT NULL,
	current_price INTEGER NOT NULL,
	previos_price INTEGER,
	change_previos_price DOUBLE PRECISION,
	acreage INTEGER,
	nearest_station VARCHAR(255),
	usage TEXT,
	neighborhood_usage TEXT,
	info_year INTEGER NOT NULL,
	FOREIGN KEY (municipality_id) REFERENCES municipalities (id),
	FOREIGN KEY (address_id) REFERENCES address (id)
);

--テーブル名：hittakuri
--テーブル詳細：ひったくり情報
DROP TABLE IF EXISTS hittakuri CASCADE;
CREATE TABLE hittakuri (
  id SERIAL PRIMARY KEY
  ,date_incident TIMESTAMP NOT NULL
  ,police_station VARCHAR(50) 
  ,police_box VARCHAR(50)
  ,address_id INTEGER NOT NULL
  ,victim_gender_id INTEGER NOT NULL
  ,victim_age_id INTEGER NOT NULL
  ,cash_damage_id INTEGER NOT NULL
  ,registered_user TEXT NOT NULL
  ,registered_date_time TIMESTAMP NOT NULL
  ,updated_user TEXT NOT NULL
  ,updated_date_time TIMESTAMP NOT NULL
  ,deleted BOOLEAN NOT NULL 
  ,FOREIGN KEY(address_id) REFERENCES address(id)
);

--テーブル名:victim_genders
--テーブル詳細:被害者の性別情報
DROP TABLE IF EXISTS genders CASCADE;
CREATE TABLE genders (
	id INTEGER PRIMARY KEY
	,value VARCHAR(20) NOT NULL
);
INSERT INTO genders (id,value) VALUES (1,'男性'),(2,'女性'),(3,'その他');

--テーブル名:victim_ages
--テーブル詳細:被害者の年齢情報
DROP TABLE IF EXISTS victim_ages CASCADE;
CREATE TABLE victim_ages (
	id INTEGER PRIMARY KEY
	,value VARCHAR(50) NOT NULL
);
INSERT INTO victim_ages (id,value) VALUES
(1,'10歳未満')
,(2,'10歳代')
,(3,'20歳代')
,(4,'30歳代')
,(5,'40歳代')
,(6,'50歳代')
,(7,'60-64歳')
,(8,'65-69歳')
,(9,'70歳以上')
,(10,'法人・団体、被害者なし');

--テーブル名:cash_damages
--テーブル詳細:現金被害の有無
DROP TABLE IF EXISTS cash_damages CASCADE;
CREATE TABLE cash_damages (
	id INTEGER PRIMARY KEY
	,value VARCHAR(50) NOT NULL
);
INSERT INTO cash_damages (id,value) VALUES (1,'あり'),(2,'なし'),(3,'その他');