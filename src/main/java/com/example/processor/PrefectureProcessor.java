package com.example.processor;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.domain.Prefecture;

import jakarta.transaction.Transactional;

/**
 * Prefectureオブジェクトの処理を行うクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Component
@StepScope
public class PrefectureProcessor implements ItemProcessor<Prefecture, Prefecture> {

	@Autowired
	private NamedParameterJdbcTemplate template;

	@Override
	public Prefecture process(Prefecture prefecture) throws Exception {

		prefecture.setRegionId(getRegionIdByPrefectureName(prefecture.getName()));

		return prefecture;

	}

	@Transactional
	private int getRegionIdByPrefectureName(String prefectureName) {
		String sql = "SELECT region_id FROM prefecture_infomations WHERE name = :prefectureName";

		int regionId = template.queryForObject(sql, new MapSqlParameterSource("prefectureName", prefectureName),
				int.class);
		return regionId;
	}

}
