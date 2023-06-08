package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * victim_gendersテーブルへの処理を行うクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Repository
public class VictimGenderRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 文字列の被害者の性別情報から被害者の性別IDを取得.
	 * 
	 * @param victimGender 文字列の被害者の性別情報
	 * @return 該当被害者の性別ID
	 */
	public Integer getVictimGenderIdByVictimGender(String victimGender) {
		String sql = "SELECT id FROM victim_genders WHERE value = :victimGender";
		List<Integer> victimGenderIdList = template.queryForList(sql,
				new MapSqlParameterSource("victimGender", victimGender), Integer.class);

		if (victimGenderIdList.isEmpty()) {
			return null;
		} else {
			return victimGenderIdList.get(0);
		}
	}

}
