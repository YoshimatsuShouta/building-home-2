package com.example.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.Address;

/**
 * addresssesテーブルへの処理を行うクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Repository
public class AddressRepository {

	@Autowired
	NamedParameterJdbcTemplate template;

	/**
	 * 町域名と市区町村名から町域情報を取得.
	 * 
	 * @param addressName      町域名
	 * @param municipalityName 市区町村名
	 * @return 該当町域情報
	 */
	public Address getAddress(String addressName, String municipalityName) {
		String sql = "SELECT a.id AS id,a.name AS name,a.name_kana AS nameKana,a.name_rome AS nameRome,a.latitude AS latitude,a.longitude AS longitude,a.prefecture_id AS prefectureId,a.municipality_id AS municipalityId FROM addresses AS a LEFT OUTER JOIN municipalities AS m ON a.municipality_id = m.id WHERE m.name = :municipalityName AND a.name = :addressName";
		RowMapper<Address> rowMapper = new BeanPropertyRowMapper<>(Address.class);
		List<Address> addressList = template.query(sql,
				new MapSqlParameterSource("addressName", addressName).addValue("municipalityName", municipalityName),
				rowMapper);
		if (addressList.isEmpty()) {
			return null;
		} else {
			return addressList.get(0);
		}
	}

	public Address getAddress(Map<String, String> addressNameMap, String muninipalityName) {

		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT a.id AS id,a.name AS name,a.name_kana AS nameKana,a.name_rome AS nameRome,a.latitude AS latitude,a.longitude AS longitude,a.prefecture_id AS prefectureId,a.municipality_id AS municipalityId FROM addresses AS a LEFT OUTER JOIN municipalities AS m ON a.municipality_id = m.id");
		sql.append(" WHERE");
		sql.append(" ( false");
		Set<String> keys = addressNameMap.keySet();
		for (String key : keys) {
			sql.append(" OR a.name = :" + key);
		}
		sql.append(" )");
		sql.append(" AND ");
		sql.append(" m.name = :municipalityName");
		RowMapper<Address> rowMapper = new BeanPropertyRowMapper<>(Address.class);
		List<Address> addressList = template.query(sql.toString(),
				new MapSqlParameterSource(addressNameMap).addValue("municipalityName", muninipalityName), rowMapper);
		if (addressList.isEmpty()) {
			return null;
		} else {
			return addressList.get(0);
		}
	}

}
