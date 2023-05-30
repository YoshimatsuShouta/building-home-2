package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * townテーブルに関する処理を行うクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Repository
public class TownRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Integer getTownId(String townName, int municipalityId) {
		String sql = "SELECT id FROM towns WHERE name = :townName AND municipality_id = :municipalityId";
		List<Integer> townIdList = template.queryForList(sql,
				new MapSqlParameterSource("townName", townName).addValue("municipalityId", municipalityId),
				Integer.class);
		if (townIdList.isEmpty()) {
			return null;
		} else {
			return townIdList.get(0);
		}
	}

	public void createIndex() {
		String sql = "CREATE INDEX town_name_and_municipality_id ON towns (name,municipality_id)";
		jdbcTemplate.execute(sql);
	}

}
