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

import com.example.domain.HittakuriOriginal;

/**
 * ひったくり情報のcsvファイルからHittakuriOriginalオブジェクトを生成するリーダークラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Component
@StepScope
public class HittakuriOriginalReader implements ItemReader<HittakuriOriginal> {

	private FlatFileItemReader<HittakuriOriginal> reader;

	private static final String[] COLUMNS = { "crime", "crimeMethod", "policeStation", "policeBox", "municipalityId",
			"prefectureName", "municipalityName", "addressName", "eventDate", "eventTime", "eventLocation",
			"victimGender", "victimAge", "cashDamage" };

	private static final String ENCORDING_TYPE = "shift_JIS";
	private static final int LINES_TO_SKIP = 1;

	public HittakuriOriginalReader(String classPath) {
		reader = new FlatFileItemReader<>();
		reader.setResource(new ClassPathResource(classPath));
		reader.setLineMapper(createLineMapper());
		reader.setLinesToSkip(LINES_TO_SKIP);
		reader.setEncoding(ENCORDING_TYPE);
		reader.open(new ExecutionContext());
	}

	@Override
	public HittakuriOriginal read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return reader.read();
	}

	private LineMapper<HittakuriOriginal> createLineMapper() {
		DefaultLineMapper<HittakuriOriginal> lineMapper = new DefaultLineMapper<>();
		lineMapper.setLineTokenizer(createLineTokenizer());
		BeanWrapperFieldSetMapper<HittakuriOriginal> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(HittakuriOriginal.class);
		lineMapper.setFieldSetMapper(fieldSetMapper);

		return lineMapper;
	}

	private LineTokenizer createLineTokenizer() {

		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer(DelimitedLineTokenizer.DELIMITER_COMMA);
		tokenizer.setNames(COLUMNS);

		return tokenizer;

	}

}
