package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * 住所に関するリポジトリ.
 * 
 * @author yoshimatsushouta
 *
 */
public class AddressRepository {
	/**
	 * 住所情報をテーブルに挿入する.
	 * 
	 * @param originalList オリジナルテーブル一行ごとのリスト
	 */
	public void insertToAddress(List<Original> originalList) {
		Connection con = SampleDBManager.createConnection();

		String sql = "INSERT INTO addresses (name, name_kana, name_rome, latitude, longitude, prefecture_id, municipality_id) SELECT ?, ?, ?, ?, ?, ?, ? WHERE NOT EXISTS (SELECT name FROM address WHERE name = ? AND prefecture_id = ? AND municipality_id = ?);";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			int cnt = 0; // listが回った回数を一時記録する
			int listCnt = originalList.size(); // listの件数を一時記録する
			// listの行数文for文を回す
			for (Original original : originalList) {

				// SQLの 「?」 の部分にそれぞれ値をセットする
				pstmt.setString(1, original.getAddress());
				pstmt.setString(2, original.getAddressKana());
				pstmt.setString(3, original.getAddressRome());
				if (Objects.isNull(original.getLatitude())) {
					pstmt.setNull(4, java.sql.Types.DOUBLE);
				} else {
					pstmt.setDouble(4, original.getLatitude());
				}

				if (Objects.isNull(original.getLongitude())) {
					pstmt.setNull(5, java.sql.Types.DOUBLE);
				} else {
					pstmt.setDouble(5, original.getLongitude());
				}
				pstmt.setInt(6, original.getPrefecturesCode());
				pstmt.setInt(7, original.getMunicipalitiesCode());

				pstmt.setString(8, original.getAddress());
				pstmt.setInt(9, original.getPrefecturesCode());
				pstmt.setInt(10, original.getMunicipalitiesCode());
				pstmt.addBatch();// 一行毎の情報を溜める
				System.out.println(cnt++ + "回目");

				// 50万行毎にinsert文を実行する 最後の端数までaddBatchした時にも実行
				if (cnt % 50000 == 0 || cnt == listCnt) {
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

}
