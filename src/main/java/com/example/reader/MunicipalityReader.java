package com.example.reader;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.domain.Municipality;

/**
 * originalテーブルよりMunicipalityオブジェクトを生成するリーダークラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Component
@StepScope
public class MunicipalityReader implements ItemReader<Municipality> {

	private JdbcCursorItemReader<Municipality> reader;

	private RowMapper<Municipality> ROW_MAPPER = (rs, i) -> {
		Municipality municipality = new Municipality();
		municipality.setId(rs.getInt("id"));
		municipality.setName(rs.getString("name"));
		municipality.setNameKana(rs.getString("name_kana"));
		municipality.setNameRome(rs.getString("name_rome"));
		municipality.setPrefectureId(rs.getInt("prefecture_id"));

		return municipality;
	};

	public MunicipalityReader(DataSource dataSource) {

		String sql = "SELECT DISTINCT municipalities_code AS id,municipalities AS name,municipalities_kana AS name_kana,municipalities_rome AS name_rome,prefectures_code AS prefecture_id FROM original ORDER BY municipalities_code";
		reader = new JdbcCursorItemReader<>();
		reader.setDataSource(dataSource);
		reader.setSql(sql);
		reader.setRowMapper(ROW_MAPPER);
		reader.setSaveState(true);
		reader.open(new ExecutionContext());


	}

	@Override
	public Municipality read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return reader.read();
	}
}
