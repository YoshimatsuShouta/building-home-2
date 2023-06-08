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

import com.example.domain.Prefecture;

/**
 * 
 * Prefectureテーブルを作成するためのリーダー.
 * 
 * @author sugaharatakamasa
 *
 */
@Component
@StepScope
public class PrefectureReader implements ItemReader<Prefecture> {

	private JdbcCursorItemReader<Prefecture> reader;

	private RowMapper<Prefecture> ROW_MAPPER = (rs, i) -> {
		Prefecture prefecture = new Prefecture();
		prefecture.setId(rs.getInt("id"));
		prefecture.setName(rs.getString("name"));
		prefecture.setNameKana(rs.getString("name_kana"));
		prefecture.setNameRome(rs.getString("name_rome"));

		return prefecture;
	};

	public PrefectureReader(DataSource dataSource) {
		String sql = "SELECT DISTINCT prefectures_code AS id,prefectures AS name,prefectures_kana AS name_kana,prefectures_rome AS name_rome FROM original ORDER BY prefectures_code";
		reader = new JdbcCursorItemReader<>();
		reader.setDataSource(dataSource);
		reader.setSql(sql);
		reader.setRowMapper(ROW_MAPPER);
		reader.setSaveState(true);
		reader.open(new ExecutionContext());

	}

	@Override
	public Prefecture read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return reader.read();
	}

}
