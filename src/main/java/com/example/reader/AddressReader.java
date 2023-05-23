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

import com.example.Address;

/**
 * originalテーブルからAddressオブジェクトを生成するリーダークラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Component
@StepScope
public class AddressReader implements ItemReader<Address> {

	private JdbcCursorItemReader<Address> reader;

	private RowMapper<Address> ROW_MAPPER = (rs, i) -> {
		Address address = new Address();
		address.setName(rs.getString("name"));
		address.setNameKana(rs.getString("name_kana"));
		address.setNameRome(rs.getString("name_rome"));
		if (rs.getDouble("latitude") == 0) {
			address.setLatitude(null);
		} else {
			address.setLatitude(rs.getDouble("latitude"));
		}
		if (rs.getDouble("longitude") == 0) {
			address.setLongitude(null);
		} else {
			address.setLongitude(rs.getDouble("longitude"));
		}
		address.setMunicipalityId(rs.getInt("municipality_id"));
		address.setPrefectureId(rs.getInt("prefecture_id"));

		return address;
	};

	public AddressReader(DataSource dataSource) {

		String sql = "SELECT address AS name,address_kana AS name_kana,address_rome AS name_rome,latitude,longitude,prefectures_code AS prefecture_id,municipalities_code AS municipality_id FROM original ORDER BY municipalities_code";

		reader = new JdbcCursorItemReader<>();
		reader.setDataSource(dataSource);
		reader.setSql(sql);
		reader.setRowMapper(ROW_MAPPER);
		reader.setSaveState(true);
		reader.open(new ExecutionContext());

	}

	@Override
	public Address read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return reader.read();
	}

}
