--テーブル名：regions
--テーブル詳細：地方情報
DROP TABLE IF EXISTS regions CASCADE;
CREATE TABLE regions (
	id INTEGER PRIMARY KEY
	,name VARCHAR(50) NOT NULL
);
INSERT INTO regions (id,name) VALUES (1,'北海道'),(2,'東北'),(3,'関東'),(4,'中部'),(5,'近畿'),(6,'中国'),(7,'四国'),(8,'九州・沖縄');

--テーブル名：prefecture_infomations
--テーブル詳細：都道府県と地方情報の関連付け
DROP TABLE IF EXISTS prefecture_infomations CASCADE;
CREATE TABLE prefecture_infomations (
	id SERIAL PRIMARY KEY
	,name VARCHAR(50) NOT NULL
	,region_id INTEGER NOT NULL
  ,FOREIGN KEY (region_id) REFERENCES regions (id)
);
INSERT INTO prefecture_infomations (name,region_id) VALUES
('北海道',1)
,('青森県',2),('岩手県',2),('宮城県',2),('秋田県',2),('山形県',2),('福島県',2)
,('茨城県',3),('栃木県',3),('群馬県',3),('埼玉県',3),('千葉県',3),('東京都',3),('神奈川県',3)
,('新潟県',4),('富山県',4),('石川県',4),('福井県',4),('山梨県',4),('長野県',4),('岐阜県',4),('静岡県',4),('愛知県',4)
,('三重県',5),('滋賀県',5),('京都府',5),('大阪府',5),('兵庫県',5),('奈良県',5),('和歌山県',5)
,('鳥取県',6),('島根県',6),('岡山県',6),('広島県',6),('山口県',6)
,('徳島県',7),('香川県',7),('愛媛県',7),('高知県',7)
,('福岡県',8),('佐賀県',8),('長崎県',8),('熊本県',8),('大分県',8),('宮崎県',8),('鹿児島県',8),('沖縄県',8);

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