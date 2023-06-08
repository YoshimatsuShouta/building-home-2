package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * cash_damagesテーブルへの処理を行うクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Repository
public class CashDamageRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 文字列の現金被害の有無情報から現金被害の有無IDを取得.
	 * 
	 * @param cashDamage 文字列の現金被害の有無情報
	 * @return 該当現金被害の有無ID
	 */
	public Integer getCashDamageIdByCashDamage(String cashDamage) {
		String sql = "SELECT id FROM cash_damages WHERE value = :cashDamage";
		List<Integer> cashDamageIdList = template.queryForList(sql, new MapSqlParameterSource("cashDamage", cashDamage),
				Integer.class);

		if (cashDamageIdList.isEmpty()) {
			return null;
		} else {
			return cashDamageIdList.get(0);
		}
	}

}
