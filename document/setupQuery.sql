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

-- addresses（住所）テーブルの作成 
DROP TABLE IF EXISTS addresses CASCADE;
CREATE TABLE addresses (
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

-- price_of_lands
DROP TABLE IF EXISTS price_of_lands CASCADE;
CREATE TABLE price_of_lands (
	id serial NOT NULL,
  	municipality_id INTEGER NOT NULL,
  	address_id INTEGER NOT NULL,
  	address_num VARCHAR(255) NOT NULL,
  	current_price INTEGER NOT NULL,
  	previos_price INTEGER,
  	change_previos_price DOUBLE PRECISION,
  	acreage INTEGER,
  	nearest_station_id INTEGER,
  	usage_id INTEGER,
  	neighborhood_usage TEXT,
  	info_year INTEGER NOT NULL,
  	FOREIGN KEY (municipality_id) REFERENCES municipalities (id),
  	FOREIGN KEY (address_id) REFERENCES address (id),
  	FOREIGN KEY (usage_id) REFERENCES usages (id),
  	FOREIGN KEY (nearest_station_id) REFERENCES nearest_stations (id)
);

-- usages
CREATE TABLE usages (
	id serial NOT NULL,
	usage_text text,
	CONSTRAINT usage_PKC PRIMARY KEY (id) 
);

-- nearest_stations
DROP TABLE IF EXISTS nearest_stations CASCADE;
CREATE TABLE nearest_stations (
	id serial NOT NULL,
	name VARCHAR(255),
	CONSTRAINT nearest_stations_PKC PRIMARY KEY (id) 
);

-- institution 
	CREATE TABLE institution (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255),
	longitude DECIMAL (9,6),
	latitude DECIMAL (9,6),
	available_days VARCHAR(255),
	start_time VARCHAR(255),
	end_time VARCHAR(255),
	phone_number VARCHAR(20),
	address VARCHAR(255),
	postal_code VARCHAR(10),
	tag_id INTEGER NOT NULL,
	municipalities_id INTEGER NOT NULL,
	FOREIGN KEY (municipalities_id) REFERENCES municipalities (id)
);


--テーブル名：victim_ages
--テーブル詳細：犯罪被害者の年齢情報
DROP TABLE IF EXISTS victim_ages CASCADE;
CREATE TABLE victim_ages (
	id INTEGER PRIMARY KEY
	,VALUE VARCHAR(50) NOT NULL
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

--テーブル名：victim_genders
--テーブル詳細：犯罪被害者の性別情報
DROP TABLE IF EXISTS victim_genders CASCADE;
CREATE TABLE victim_genders (
	id INTEGER PRIMARY KEY
	,VALUE VARCHAR(50) NOT NULL
);
INSERT INTO victim_genders (id,VALUE) VALUES (1,'男性'),(2,'女性'),(3,'法人・団体');

--テーブル名：cash_damages
--テーブル詳細：現金被害の有無
DROP TABLE IF EXISTS cash_damages CASCADE;
CREATE TABLE cash_damages (
	id INTEGER PRIMARY KEY
	,VALUE VARCHAR(50) NOT NULL
);
INSERT INTO cash_damages (id,VALUE) VALUES (1,'あり'),(2,'なし');


--テーブル名：hittakuri
--テーブル詳細：ひったくり情報
DROP TABLE IF EXISTS hittakuri CASCADE;
CREATE TABLE hittakuri (
	id SERIAL PRIMARY KEY
	,event_date_time TIMESTAMP NOT NULL
	,prefecture_id INTEGER NOT NULL
	,municipality_id INTEGER NOT NULL
	,address_id INTEGER NOT NULL
	,police_station VARCHAR(50)
	,police_box VARCHAR(50)
	,victim_gender_id INTEGER 
	,victim_age_id INTEGER 
	,cash_damage_id INTEGER 
	,registered_user TEXT NOT NULL
	,registered_date_time TIMESTAMP NOT NULL
	,updated_user TEXT NOT NULL
	,updated_date_time TIMESTAMP NOT NULL
	,deleted BOOLEAN NOT NULL
	,FOREIGN KEY (prefecture_id) REFERENCES prefectures (id)
	,FOREIGN KEY (municipality_id) REFERENCES municipalities (id)
	,FOREIGN KEY (address_id) REFERENCES addresses (id)
	,FOREIGN KEY (victim_gender_id) REFERENCES victim_genders (id)
	,FOREIGN KEY (victim_age_id) REFERENCES victim_ages (id)
	,FOREIGN KEY (cash_damage_id) REFERENCES cash_damages (id)
);
