-- prefectures（都道府県）テーブルの作成
CREATE TABLE prefectures (
  id INT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  name_kana VARCHAR(255),
  name_rome VARCHAR(255)
);

-- municipalities（市町村）テーブルの作成
CREATE TABLE municipalities (
  id INT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  name_kana VARCHAR(255),
  name_rome VARCHAR(255),
  prefecture_id INT,
  FOREIGN KEY (prefecture_id) REFERENCES prefectures(id)
);

-- addresses（住所）テーブルの作成 
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

-- original
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

-- crimes
	CREATE TABLE crimes (
	id SERIAL PRIMARY KEY,
	crime_name INTEGER NOT NULL,
	date_incident TIMESTAMP NOT NULL,
	police_station VARCHAR(50),
	police_box VARCHAR(50),
	address_id INTEGER NOT NULL,
	gender_victim INTEGER NOT NULL,
	age_victime INTEGER NOT NULL,
	cash_damage BOOLEAN NOT NULL,
	registered_user TEXT NOT NULL,
	registered_date_time TIMESTAMP NOT NULL,
	updated_user TEXT NOT NULL,
	updated_date_time TIMESTAMP NOT NULL,
	deleted BOOLEAN NOT NULL,
	FOREIGN KEY (address_id) REFERENCES address (id)
);

DROP TABLE IF EXISTS original CASCADE;
DROP TABLE IF EXISTS prefectures CASCADE;
DROP TABLE IF EXISTS municipalities CASCADE;
DROP TABLE IF EXISTS addresses CASCADE;
DROP TABLE IF EXISTS price_of_lands CASCADE;
DROP TABLE IF EXISTS nearest_stations CASCADE;