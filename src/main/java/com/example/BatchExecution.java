package com.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;


/**
 * すべてのDB情報を用意するメソッド.
 * 
 * @author yoshida_yuuta
 */

public class BatchExecution {
	public static Connection con = SampleDBManager.createConnection();
	
	/**
	 * 実行メソッド.
	 * 
	 * @param args
	 * @throws SQLException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void main(String[] args) throws SQLException, IOException, ParseException {
		
		//以下をtrueにして実行するとDBが準備されます。
		boolean execute =true;
		
		//実行しないものは以下のメソッド内でfalseに変更 drop:tableの消去　create:テーブル作成 insert:データを入れます
		dropTable(execute);
		createTable(execute);
		insertData(execute);
	}
	
	/**
	 * 以下trueにすると該当のテーブルが作成されます.
	 * 
	 * @param execute true:実行 false:実行しない
	 * @throws SQLException
	 */
	public static void createTable(boolean execute) throws SQLException {
		if(execute) {
		createOriginal(true);
		createPrefectures(true);
		createMunicipalities(true);
		createAddress(true);
		createInstitutions(true);
		createTagSpecies(true);
	}}
	
	/**
	 * テーブルを消去するメソッド.
	 * 
	 * @param execute　true:実行 false:実行しない
	 * @throws SQLException
	 */
	public static void dropTable(boolean execute) throws SQLException {
		StringBuilder deleteSql =new StringBuilder();
		
		//以下table名を追加するとDropされます
		String[] tableNames={"address","municipalities","prefectures","original","institutions","tag_species"};
		
		//sql実行
		for(String tableName : tableNames) {
		deleteSql.append("DROP TABLE IF EXISTS ");
		deleteSql.append(tableName);
		deleteSql.append(";");
		PreparedStatement deletePstmt = con.prepareStatement(deleteSql.toString());
		deletePstmt.execute();
		}
	}
	
	
	/**
	 * テーブルを追加するときは以下に習いcreate文を追加してください.
	 * 
	 * @param execute　true:実行 false:実行しない
	 * @throws SQLException
	 */
	public static void createOriginal(boolean execute ) throws SQLException {
		if(execute) {
		String deleteSql = "DROP TABLE IF EXISTS original;";
		PreparedStatement deletePstmt = con.prepareStatement(deleteSql);
		deletePstmt.execute();
		String createSql = "CREATE TABLE original (\r\n"
				+ "	id serial NOT NULL,\r\n"
				+ "	prefectures_code integer,\r\n"
				+ "	prefectures character varying (255),\r\n"
				+ "	prefectures_kana character varying (255),\r\n"
				+ "	prefectures_rome character varying (255),\r\n"
				+ "	municipalities_code integer,\r\n"
				+ "	municipalities character varying (255),\r\n"
				+ "	municipalities_kana character varying (255),\r\n"
				+ "	municipalities_rome character varying (255),\r\n"
				+ "	address character varying (255),\r\n"
				+ "	address_kana character varying (255),\r\n"
				+ "	address_rome character varying (255),\r\n"
				+ "	Latitude double precision,\r\n"
				+ "	Longitude double precision,\r\n"
				+ "	CONSTRAINT original_PKC PRIMARY KEY (id)\r\n"
				+ ");";
		PreparedStatement createPstmt = con.prepareStatement(createSql);
		createPstmt.execute();}
	}
	
	public static void createPrefectures(boolean execute ) throws SQLException {
		if(execute) {
		String createSql = "CREATE TABLE prefectures (id INT PRIMARY KEY,name VARCHAR(255) NOT NULL,name_kana VARCHAR(255),name_rome VARCHAR(255));";
		PreparedStatement createPstmt = con.prepareStatement(createSql);
		createPstmt.execute();}
	}
	public static void createMunicipalities(boolean execute ) throws SQLException {
		if(execute) {
		String createSql = "CREATE TABLE municipalities (id INT PRIMARY KEY,name VARCHAR(255) NOT NULL,name_kana VARCHAR(255),name_rome VARCHAR(255),prefecture_id INT,FOREIGN KEY (prefecture_id) REFERENCES prefectures(id));";
		PreparedStatement createPstmt = con.prepareStatement(createSql);
		createPstmt.execute();}
	}
	public static void createAddress(boolean execute ) throws SQLException {
		if(execute) {
		String createSql = "CREATE TABLE address (\r\n"
				+ "	id serial NOT NULL,\r\n"
				+ "	name character varying (255),\r\n"
				+ "	name_kana character varying (255),\r\n"
				+ "	name_rome character varying (255),\r\n"
				+ "	Latitude double precision,\r\n"
				+ "	Longitude double precision,\r\n"
				+ "	prefecture_id INT,\r\n"
				+ "	municipality_id INT,\r\n"
				+ "	CONSTRAINT address_PKC PRIMARY KEY (id),\r\n"
				+ "	FOREIGN KEY (prefecture_id) REFERENCES prefectures (id),\r\n"
				+ "	FOREIGN KEY (municipality_id) REFERENCES municipalities (id)\r\n"
				+ ");";
		PreparedStatement createPstmt = con.prepareStatement(createSql);
		createPstmt.execute();}
	}
	public static void createInstitutions(boolean execute ) throws SQLException {
		if(execute) {
		String createSql = "CREATE TABLE institutions (id SERIAL PRIMARY KEY, name VARCHAR(255),longitude DECIMAL(9, 6), latitude DECIMAL(9, 6),available_days VARCHAR(255),start_time VARCHAR(255),end_time VARCHAR(255),phone_number VARCHAR(20),address VARCHAR(255),postal_code VARCHAR(10),tag_id VARCHAR(255),address_id Integer);";
		PreparedStatement createPstmt = con.prepareStatement(createSql);
		createPstmt.execute();}
	}
	public static void createTagSpecies(boolean execute ) throws SQLException {
		if(execute) {
		String createSql = "CREATE TABLE tag_species (id SERIAL PRIMARY KEY ,name VARCHAR(255));";
		PreparedStatement createPstmt = con.prepareStatement(createSql);
		createPstmt.execute();}
	}
	
	
	/**
	 * データをinsertします(mainメソッドを使用).
	 * 
	 * @param execute　true:実行 false:実行しない
	 * @throws IOException
	 * @throws SQLException
	 * @throws ParseException
	 */
	public static void insertData(boolean execute) throws IOException, SQLException, ParseException {
		String[] args = null ;
		if(execute) {
			CsvImportMain.main(args);
			PrefecturesMain.main(args);
			InstitutionMain.main(args);
		}
		
	}
	
}

