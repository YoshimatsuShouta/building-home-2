package com.example.reader;

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

import com.example.domain.TagSpeciesOriginal;

public class TagSpeciesOriginalReader implements ItemReader<TagSpeciesOriginal> {
	private FlatFileItemReader<TagSpeciesOriginal> reader;

	private static final String[] COLUMNS = { "a0", "POIChord", "name", "a3" };

	private static final String ENCORDING_TYPE = "shift_JIS";
	private static final int LINES_TO_SKIP = 1;

	public TagSpeciesOriginalReader(String classPath) {
		reader = new FlatFileItemReader<>();
		reader.setResource(new ClassPathResource(classPath));
		reader.setLineMapper(createLineMapper());
		reader.setLinesToSkip(LINES_TO_SKIP);
		reader.setEncoding(ENCORDING_TYPE);
		reader.open(new ExecutionContext());
	}

	@Override
	public TagSpeciesOriginal read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return reader.read();
	}

	private LineMapper<TagSpeciesOriginal> createLineMapper() {
		DefaultLineMapper<TagSpeciesOriginal> lineMapper = new DefaultLineMapper<>();
		lineMapper.setLineTokenizer(createLineTokenizer());
		BeanWrapperFieldSetMapper<TagSpeciesOriginal> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(TagSpeciesOriginal.class);
		lineMapper.setFieldSetMapper(fieldSetMapper);

		return lineMapper;
	}

	private LineTokenizer createLineTokenizer() {

		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer(DelimitedLineTokenizer.DELIMITER_COMMA);
		tokenizer.setNames(COLUMNS);

		return tokenizer;
	}

}
