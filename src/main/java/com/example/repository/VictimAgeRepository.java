package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * victim_agesテーブルへの処理を行うクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Repository
public class VictimAgeRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 文字列の被害者の年齢情報から被害者の年齢IDを取得.
	 * 
	 * @param victimAge 文字列の被害者の年齢情報
	 * @return 該当被害者の年齢ID
	 */
	public Integer getVictimAgeIdByVictimAge(String victimAge) {
		String sql = "SELECT id FROM victim_ages WHERE value = :victimAge";
		List<Integer> victimAgeIdList = template.queryForList(sql, new MapSqlParameterSource("victimAge", victimAge),
				Integer.class);

		if (victimAgeIdList.isEmpty()) {
			return null;
		} else {
			return victimAgeIdList.get(0);
		}
	}

}
