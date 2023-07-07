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

import com.example.domain.InstitutionsOriginal;

@Component
@StepScope
public class InstitutionsOriginalReader implements ItemReader<InstitutionsOriginal> {

	private FlatFileItemReader<InstitutionsOriginal> reader;

	private static final String[] COLUMNS = { "a0", "a1", "a2", "a3", "name", "a4", "address", "a5", "a6", "a7",
			"tagId", "address", "a10", "longitude", "latitude", "phoneNumber", "a12", "a13", "a14", "availableDays",
			"startTime", "endTime", "a18", "a19", "a20", "a21", "a22", "postalCode", "a24", "a25", "a26", "a27", "a28",
			"a29", "a30", "a31", "a32", "a33", "a34", "a35", "a36", "a36", "a37", "a38" };

	private static final String ENCORDING_TYPE = "shift_JIS";
	private static final int LINES_TO_SKIP = 1;

	public InstitutionsOriginalReader(String classPath) {
		reader = new FlatFileItemReader<>();
		reader.setResource(new ClassPathResource(classPath));
		reader.setLineMapper(createLineMapper());
		reader.setLinesToSkip(LINES_TO_SKIP);
		reader.setEncoding(ENCORDING_TYPE);
		reader.open(new ExecutionContext());
	}

	@Override
	public InstitutionsOriginal read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return reader.read();
	}

	private LineMapper<InstitutionsOriginal> createLineMapper() {
		DefaultLineMapper<InstitutionsOriginal> lineMapper = new DefaultLineMapper<>();
		lineMapper.setLineTokenizer(createLineTokenizer());
		BeanWrapperFieldSetMapper<InstitutionsOriginal> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(InstitutionsOriginal.class);
		lineMapper.setFieldSetMapper(fieldSetMapper);

		return lineMapper;
	}

	private LineTokenizer createLineTokenizer() {

		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer(DelimitedLineTokenizer.DELIMITER_COMMA);
		tokenizer.setNames(COLUMNS);

		return tokenizer;
	}

}
