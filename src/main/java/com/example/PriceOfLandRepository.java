package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class PriceOfLandRepository {
	public void insertToPriceOfLand(List<PriceOfLand> priceOfLandList) {
		Connection con = SampleDBManager.createConnection();

		String sql = "INSERT INTO price_of_land (municipality_id, address_id, address_num, current_price, previos_price, change_previos_price, acreage, nearest_station, usage, neighborhood_usage, info_year)"
				+ " SELECT ?, (SELECT id from address where municipality_id = ? AND name LIKE ? LIMIT 1), ?, ?, ?, ?, ?, ?, ?, ?, ? ;";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			int cnt = 0; // listが回った回数を一時記録する
			int listCnt = priceOfLandList.size(); // listの件数を一時記録する
			// listの行数文for文を回す
			for (PriceOfLand priceOfLand : priceOfLandList) {

//				// SQLの 「?」 の部分にそれぞれ値をセットする
				pstmt.setInt(1, priceOfLand.getMunicipalitiesCode());

				pstmt.setInt(2, priceOfLand.getMunicipalitiesCode());
				pstmt.setString(3, priceOfLand.getAddressForSearch() + "%");

				pstmt.setString(4, priceOfLand.getAddressNum());
				pstmt.setInt(5, priceOfLand.getCurrentPrice());
				if (Objects.isNull(priceOfLand.getPreviosPrice())) {
					pstmt.setNull(6, java.sql.Types.INTEGER);
				} else {
					pstmt.setInt(6, priceOfLand.getPreviosPrice());
				}

				if (Objects.isNull(priceOfLand.getChangePreviosPrice())) {
					pstmt.setNull(7, java.sql.Types.DOUBLE);
				} else {
					pstmt.setDouble(7, priceOfLand.getChangePreviosPrice());
				}
				pstmt.setInt(8, priceOfLand.getAcreage());
				pstmt.setString(9, priceOfLand.getNearestStation());
				pstmt.setString(10, priceOfLand.getUsage());
				pstmt.setString(11, priceOfLand.getNeighborhoodUsage());
				pstmt.setInt(12, 2021);

				pstmt.addBatch();// 一行毎の情報を溜める
				cnt++;

				// 5000行毎にinsert文を実行する 最後の端数までaddBatchした時にも実行
				if (cnt % 500 == 0 || cnt == listCnt) {
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
