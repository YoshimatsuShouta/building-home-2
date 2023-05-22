package com.example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 施設tableの作成.
 * 
 * @author yoshida_yuuta
 *
 */
public class InstitutionMain {

	public static void main(String[] args) throws IOException, SQLException, ParseException {
		Connection con = SampleDBManager.createConnection();

		// CSV読み込み
		List<String[]> institutionData = readDataAsShiftJis(
				"https://www.opendata.metro.tokyo.lg.jp/suisyoudataset/130001_public_facility.csv");
		List<String[]> poiData = readData("src/main/resources/POIコード.csv");

		// 区分を割り当てる貯めのメソッド
		Map<String, Integer> addressMap = addressToMap(con);

		// DBアクセス処理
		insertInstitutionsData(institutionData, poiData, con, addressMap);
	}

	/**
	 * CSVで得た施設情報をInsert.
	 * 
	 * @param institutiosDatas 施設csv情報
	 * @param poiData          タグに関わるcsvデータ
	 * @param conn             DBアクセスに必要な情報
	 * @param addresssMap      住所とそのIDのmap
	 * @throws SQLException
	 * @throws ParseException
	 */
	public static void insertInstitutionsData(List<String[]> institutiosData, List<String[]> poiData, Connection conn,
			Map<String, Integer> addresssMap) throws SQLException, ParseException {
		Map<String, Integer> map = new HashMap<String, Integer>();
		boolean firstIteration = true;
		PreparedStatement institutionpstmt = conn.prepareStatement(
				"INSERT INTO institutions (name,longitude,latitude,available_days,start_time,end_time,phone_number,address,postal_code,tag,area) VALUES (?,?,?,?,?,?,?,?,?,?,?) ;");
		PreparedStatement tagpstmt = conn.prepareStatement("INSERT INTO  tag_species (name) VALUES (?) ;");
		int num = 1;
		for (String[] fields : poiData) {
			if (firstIteration) {
				firstIteration = false;
				continue;
			}
			tagpstmt.setString(1, fields[2]);
			map.put(fields[1], num);
			num++;
			tagpstmt.execute();
		}

		for (String[] fields : institutiosData) {
			institutionpstmt.setString(1, fields[4]);
			institutionpstmt.setBigDecimal(2, new BigDecimal(fields[10]));
			institutionpstmt.setBigDecimal(3, new BigDecimal(fields[11]));
			institutionpstmt.setString(4, fields[16]);
			institutionpstmt.setString(5, fields[17]);
			institutionpstmt.setString(6, fields[18]);
			institutionpstmt.setString(7, fields[12]);
			institutionpstmt.setString(8, fields[8]);
			institutionpstmt.setString(9, fields[24]);
			institutionpstmt.setInt(10, map.get(fields[7].replaceAll("a", "")));
			String convertAddress = convertHalfWidthNumber(fields[8]);
			for (String address : addresssMap.keySet()) {
				if (convertAddress.contains(address)) {
					institutionpstmt.setInt(11, addresssMap.get(address));
				}
			}
			for (String address : addresssMap.keySet()) {
				if (fields[8].contains(address)) {
					institutionpstmt.setInt(11, addresssMap.get(address));
				}
			}
			institutionpstmt.execute();
		}
	}

	/**
	 * csvデータを読み込むメソッド.
	 * 
	 * @param ex 読み込むcsvの詳細
	 * @return 読み込んだデータ
	 * @throws IOException
	 */
	public static List<String[]> readData(String ex) throws IOException {
		BufferedReader reader;
		List<String[]> result = new ArrayList<>();
		if (ex.contains("http")) {
			reader = new BufferedReader(new InputStreamReader(new URL(ex).openStream(), StandardCharsets.UTF_8));
		} else if (ex.contains("sjis")) {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(ex), "Shift-JIS"));
		} else {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(ex), StandardCharsets.UTF_8));
		}
		;
		String line;
		while ((line = reader.readLine()) != null) {
			String[] fields = line.split(",");
			result.add(fields);
		}
		reader.close();
		return result;
	}

	/**
	 * 該当ファイルのみ、表示の型と改行等が入っているため別処理を作成. 他は一つ上のメソッドと同じ.
	 * 
	 * @param ex
	 * @return
	 * @throws IOException
	 */
	public static List<String[]> readDataAsShiftJis(String ex) throws IOException {
		List<String[]> result = new ArrayList<>();
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(new URL(ex).openStream(), Charset.forName("Shift-JIS")));
		String line;
		String beforeLine = "";
		boolean firstLine = true;
		while ((line = reader.readLine()) != null) {
			if (firstLine) {
				firstLine = false;
				continue;
			}
			// ２列にわたって記載されているデータを結合
			if (!line.endsWith("28")) {
				beforeLine += line;
				continue;
			}
			line = beforeLine + line;
			String[] fields = line.split(",");
			result.add(fields);
			beforeLine = "";
		}
		reader.close();
		return result;
	}

	/**
	 * 住所情報とiｄをmapを詰めるメソッド.
	 * 
	 * @param con DBアクセス情報
	 * @return map(大字名,id)
	 * @throws SQLException
	 */
	public static Map<String, Integer> addressToMap(Connection con) throws SQLException {
		PreparedStatement pstmt = con.prepareStatement("SELECT id,name  FROM address WHERE prefecture_id = 13;");
		ResultSet rs = pstmt.executeQuery();
		Map<String, Integer> map = new HashMap<String, Integer>();
		while (rs.next()) {
			// 列の値を取得
			Integer id = rs.getInt("id");
			String name = rs.getString("name");
			name = convertHalfWidthNumber(name);
			map.put(name, id);
		}
		return map;

	}

	/**
	 * 半角、漢数字を全角数字に変換する(10以降非対応).
	 * 
	 * @param data 変換するもの
	 * @return 返還後の数字
	 */
	public static String convertHalfWidthNumber(String data) {
		data = data.replace("0", "０").replace("1", "１").replace("2", "２").replace("3", "３").replace("4", "４")
				.replace("5", "５").replace("6", "６").replace("7", "７").replace("8", "８").replace("9", "９")
				.replace("一", "１").replace("二", "２").replace("三", "３").replace("四", "４").replace("五", "５")
				.replace("六", "６").replace("七", "７").replace("八", "８").replace("九", "９").replace("丁目", "");

		return data;
	}

}