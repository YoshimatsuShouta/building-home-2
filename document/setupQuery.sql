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

-- address（住所）テーブルの作成 
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

-- price_of_land
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


DROP TABLE IF EXISTS original CASCADE;
DROP TABLE IF EXISTS prefectures CASCADE;
DROP TABLE IF EXISTS municipalities CASCADE;
DROP TABLE IF EXISTS address CASCADE;
DROP TABLE IF EXISTS price_of_land CASCADE;