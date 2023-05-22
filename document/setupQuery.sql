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

--crimesテーブルの削除・新規作成
DROP TABLE IF EXISTS crimes CASCADE;
CREATE TABLE crimes (
  id SERIAL PRIMARY KEY
  ,crime_name INTEGER NOT NULL
  ,date_incident TIMESTAMP NOT NULL
  ,police_station VARCHAR(50) 
  ,police_box VARCHAR(50)
  ,address_id INTEGER NOT NULL
  ,gender_victim INTEGER NOT NULL
  ,age_victime INTEGER NOT NULL
  ,cash_damage BOOLEAN NOT NULL
  ,registered_user TEXT NOT NULL
  ,registered_date_time TIMESTAMP NOT NULL
  ,updated_user TEXT NOT NULL
  ,updated_date_time TIMESTAMP NOT NULL
  ,deleted BOOLEAN NOT NULL 
  ,FOREIGN KEY(address_id) REFERENCES addresses(id)
);