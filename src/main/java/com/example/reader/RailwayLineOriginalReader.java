package com.example.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.example.domain.RailwayLineOriginal;

@Component
@StepScope
public class RailwayLineOriginalReader implements ItemReader<RailwayLineOriginal> {
	// フラットファイル ≒ CSVファイルを読み込むためのクラス
	private FlatFileItemReader<RailwayLineOriginal> reader;

	// CSVファイル一列毎に千切ったカラム
	private static final String[] COLUMNS = { "lineId", "companyId", "lineName", "lineNameKana", "lineNameH",
			"lineColorH", "lineColorT", "lineType", "longitude", "latitude", "zoom", "eStatus", "eSort" };

	// 文字化け対策のために設定
	private static final String ENCORDING_TYPE = "UTF-8";

	// 一列目はカラム名なのでスキップする
	private static final int LINES_TO_SKIP = 1;

	public RailwayLineOriginalReader(String classPath) {
		// リーダーを宣言
		reader = new FlatFileItemReader<>();
		// 引数で渡されたCSVクラスをリーダーにセット
		reader.setResource(new ClassPathResource(classPath));
		reader.setLineMapper(createLineMapper());
		reader.setLinesToSkip(LINES_TO_SKIP);
		reader.setEncoding(ENCORDING_TYPE);
		reader.open(new ExecutionContext());
	}

	/**
	 * readメソッドをオーバーライドし、RailwayLineOriginal型で返すように設定.
	 */
	@Override
	public RailwayLineOriginal read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return reader.read();
	}

	/**
	 * CSVファイルをRailwayLineOriginal型一行ずつ詰めたLineMapperを返すメソッド.
	 * 
	 * @return
	 */
	private LineMapper<RailwayLineOriginal> createLineMapper() {
		DefaultLineMapper<RailwayLineOriginal> lineMapper = new DefaultLineMapper<>();
		lineMapper.setLineTokenizer(createLineTokenizer());
		BeanWrapperFieldSetMapper<RailwayLineOriginal> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		// 分けたカラムはRailwayLineOriginalに準ずることを設定
		fieldSetMapper.setTargetType(RailwayLineOriginal.class);
		// 全行入れることを設定
		lineMapper.setFieldSetMapper(fieldSetMapper);

		return lineMapper;
	}

	/**
	 * カンマ区切りであることを定義するメソッド.
	 * 
	 * @return カンマ区切りのデータ
	 */
	private LineTokenizer createLineTokenizer() {
		// CSVファイルのためカンマで区切ることを設定
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer(DelimitedLineTokenizer.DELIMITER_COMMA);
		tokenizer.setNames(COLUMNS);

		return tokenizer;
	}
}
