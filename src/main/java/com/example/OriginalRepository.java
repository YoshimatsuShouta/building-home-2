package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * オリジナルテーブルに関するリポジトリ.
 * 
 * @author yoshimatsushouta
 *
 */
public class OriginalRepository {

	/**
	 * 引数で受け取ったリストをoriginalテーブルに入れるメソッド.
	 * 
	 * @param originalList Csvファイルのカラム毎の情報が入ったList.
	 */
	public void insertToOriginal(List<Original> originalList) {
		Connection con = SampleDBManager.createConnection();

		String sql = "INSERT INTO original (prefectures_code, prefectures, prefectures_kana, prefectures_rome, municipalities_code, municipalities, municipalities_kana, municipalities_rome, address, address_kana, address_rome, Latitude, Longitude)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ;";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			int cnt = 0; // listが回った回数を一時記録する
			int listCnt = originalList.size(); // listの件数を一時記録する
			// listの行数文for文を回す
			for (Original original : originalList) {

				// SQLの 「?」 の部分にそれぞれ値をセットする
				pstmt.setInt(1, original.getPrefecturesCode());
				pstmt.setString(2, original.getPrefectures());
				pstmt.setString(3, original.getPrefecturesKana());
				pstmt.setString(4, original.getPrefecturesRome());
				pstmt.setInt(5, original.getMunicipalitiesCode());
				pstmt.setString(6, original.getMunicipalities());
				pstmt.setString(7, original.getMunicipalitiesKana());
				pstmt.setString(8, original.getMunicipalitiesRome());
				pstmt.setString(9, original.getAddress());
				pstmt.setString(10, original.getAddressKana());
				pstmt.setString(11, original.getAddressRome());
				if (Objects.isNull(original.getLatitude())) {
					pstmt.setNull(12, java.sql.Types.DOUBLE);
				} else {
					pstmt.setDouble(12, original.getLatitude());
				}

				if (Objects.isNull(original.getLongitude())) {
					pstmt.setNull(13, java.sql.Types.DOUBLE);
				} else {
					pstmt.setDouble(13, original.getLongitude());
				}

				pstmt.addBatch();// 一行毎の情報を溜める
				cnt++;

				// 5000行毎にinsert文を実行する 最後の端数までaddBatchした時にも実行
				if (cnt % 5000 == 0 || cnt == listCnt) {
					pstmt.executeBatch(); // SQLの実行
					System.out.println(cnt + "件まで更新しました");
				}
			}

		} catch (SQLException ex) {
			System.err.println("SQL = " + sql);
			throw new RuntimeException("insert処理に失敗しました", ex);
		} finally {
			// DBとの接続を解除する
			SampleDBManager.closeConnection(con);
		}
	}

	public List<Original> findAll() {
		// 変数定義
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Original> originalList = new ArrayList<>();
		Connection con = SampleDBManager.createConnection();
		int cnt = 0;

		// SQL文を定義する
		String sql = "SELECT prefectures_code, prefectures, prefectures_kana, prefectures_rome, municipalities_code, municipalities, municipalities_kana, municipalities_rome, address, address_kana, address_rome, latitude, longitude FROM original ORDER BY id;";

		try {

			// DBへのコネクションを作成する

			// 実行するSQL文とパラメータを指定する
			ps = con.prepareStatement(sql);

			// SELECTを実行する
			rs = ps.executeQuery();

			// こんなふうにカラム数なども取得できちゃう
			int colCount = rs.getMetaData().getColumnCount();
			System.out.println("取得したカラム数:" + colCount);

			// 取得した結果を全件出力する
			while (rs.next()) {
				cnt++;
				System.out.println(cnt + "回目の処理");
				Original original = new Original();
				original.setPrefecturesCode(rs.getInt("prefectures_code"));
				original.setPrefectures(rs.getString("prefectures"));
				original.setPrefecturesKana(rs.getString("prefectures_kana"));
				original.setPrefecturesRome(rs.getString("prefectures_rome"));
				original.setMunicipalitiesCode(rs.getInt("municipalities_code"));
				original.setMunicipalities(rs.getString("municipalities"));
				original.setMunicipalitiesKana(rs.getString("municipalities_kana"));
				original.setMunicipalitiesRome(rs.getString("municipalities_rome"));
				original.setAddress(rs.getString("address"));
				original.setAddressKana(rs.getString("address_kana"));
				original.setAddressRome(rs.getString("address_rome"));
				original.setLatitude(rs.getDouble("Latitude"));
				original.setLongitude(rs.getDouble("Longitude"));

				originalList.add(original);
			}
			System.out.println(originalList.size());
		} catch (Exception ex) {
			// 例外発生時の処理
			System.out.println("sql = " + sql);
			ex.printStackTrace(); // エラー内容をコンソールに出力する

		} finally {
			// クローズ処理
			SampleDBManager.closeConnection(con);
		}

		return originalList;
	}
}