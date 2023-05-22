package com.example;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * データベースとの接続、切断に関するクラス.
 * 
 * @author yoshimatsushouta
 *
 */
public class SampleDBManager {
	private final static String URL = "jdbc:postgresql://localhost:5432/homedb";
	private final static String USER_NAME = "postgres";
	private final static String PASSWORD = "postgres";

	/**
	 * データベースに接続します.
	 * 
	 * @return 接続情報
	 */
	public static Connection createConnection() {
		try {
			Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("DBの切断に失敗しました", e);
		}
	}

	/**
	 * データベースから切断します.
	 * 
	 * @param con 接続オブジェクト
	 */
	public static void closeConnection(Connection con) {
		try {
			con.close();
		} catch (Exception e) {
		}
	}
}
