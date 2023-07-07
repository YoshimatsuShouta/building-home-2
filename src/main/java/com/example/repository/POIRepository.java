package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class POIRepository {

	@Autowired
	NamedParameterJdbcTemplate template;

	// 使わない
	public void setPoi(String name) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(name);
		String sql = "INSERT INTO tag_species (name) SELECT :name WHERE NOT EXISTS SELECT name FROM tag_species WHERE name = :name;";
		template.update(sql, param);
	}

}
