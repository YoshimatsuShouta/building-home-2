package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.example.domain.Original;

/**
 * 都道府県テーブルに関するリポジトリ.
 * 
 * @author yoshimatsushouta
 *
 */
public class PrefecturesRepository {
	/**
	 * 都道府県テーブルに情報を挿入する.
	 * 
	 * @param originalList オリジナルテーブル一行ごとの情報が詰まったリスト.
	 */
	public void insertToPrefectures(List<Original> originalList) {
		Connection con = SampleDBManager.createConnection();

		String sql = "INSERT INTO prefectures (id, name, name_kana, name_rome) SELECT ?, ?, ?, ? WHERE NOT EXISTS (SELECT name FROM prefectures WHERE name = ?);";

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
				pstmt.setString(5, original.getPrefectures());
				pstmt.addBatch();// 一行毎の情報を溜める
				System.out.println(cnt++ + "回目");

				// 50万行毎にinsert文を実行する 最後の端数までaddBatchした時にも実行
				if (cnt % 100000 == 0 || cnt == listCnt) {
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
