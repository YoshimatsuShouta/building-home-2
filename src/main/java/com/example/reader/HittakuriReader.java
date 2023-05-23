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
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.example.domain.HittakuriOriginal;

@Component
@StepScope
public class HittakuriReader implements ItemReader<HittakuriOriginal> {

	private String[] columns = { "offenceName", "modusOperandi", "policeStation", "policeBox", "municipalityCode",
			"prefectureName", "municipalityName", "addressName", "incidentDate", "incidentTime", "crimeScene",
			"genderVictim", "ageVictim", "cashDamage" };

	private FlatFileItemReader<HittakuriOriginal> reader;

	public HittakuriReader() {
		reader = new FlatFileItemReader<>();
		reader.setResource(new ClassPathResource("crimes/hittakuri/tokyo_2021hittakuri.csv")); // クラスパスで指定したい。
		reader.setLinesToSkip(1);
		reader.setLineMapper(lineMapper());
		reader.setEncoding("shift-JIS");
		reader.open(new ExecutionContext());
	}

	private LineMapper<HittakuriOriginal> lineMapper() {
		DefaultLineMapper<HittakuriOriginal> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setDelimiter(",");
		tokenizer.setNames(columns);
		BeanWrapperFieldSetMapper<HittakuriOriginal> fildSetMapper = new BeanWrapperFieldSetMapper<>();
		fildSetMapper.setTargetType(HittakuriOriginal.class);

		lineMapper.setLineTokenizer(tokenizer);
		lineMapper.setFieldSetMapper(fildSetMapper);

		return lineMapper;

	}

	@Override
	public HittakuriOriginal read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return reader.read();
	}
}
