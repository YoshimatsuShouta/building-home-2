package com.example.common;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.domain.Address;
import com.example.domain.Hittakuri;
import com.example.domain.HittakuriOriginal;
import com.example.domain.Municipality;
import com.example.domain.Prefecture;
import com.example.processor.HittakuriProcessor;
import com.example.reader.AddressReader;
import com.example.reader.HittakuriReader;
import com.example.reader.MunicipalityReader;
import com.example.reader.PrefectureReader;
import com.example.writer.AddressWriter;
import com.example.writer.HittakuriWriter;
import com.example.writer.MunicipalityWriter;
import com.example.writer.PrefectureWriter;

/**
 * stepとjobを設定するためのconfigクラス
 * 
 * @author sugaharatakamasa
 *
 */
@Component
@Configuration
public class BatchConfig {

	@Autowired
	private HittakuriReader hittakuriReader;
	@Autowired
	private HittakuriProcessor hittakuriProcessor;
	@Autowired
	private HittakuriWriter hittakuriWriter;
	// prefectureに関するもの
	@Autowired
	private PrefectureReader prefectureReader;
	@Autowired
	private PrefectureWriter prefectureWriter;
	// municipalityに関するもの
	@Autowired
	private MunicipalityReader municipalityReader;
	@Autowired
	private MunicipalityWriter municipalityWriter;
	// addressに関するもの
	@Autowired
	private AddressReader addressReader;
	@Autowired
	private AddressWriter addressWriter;

	@Bean
	Step stepOfPrefecture(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("stepOfPrefecture", jobRepository).<Prefecture, Prefecture>chunk(100, transactionManager)
				.reader(prefectureReader).writer(prefectureWriter).build();
	}

	@Bean
	Step stepOfMunicipality(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("stepOfMunicipality", jobRepository)
				.<Municipality, Municipality>chunk(1000, transactionManager).reader(municipalityReader)
				.writer(municipalityWriter).build();
	}

	@Bean
	Step stepOfAddress(JobRepository jobRepository, PlatformTransactionManager transactionManager) {

		return new StepBuilder("stepOfAddress", jobRepository).<Address, Address>chunk(1000, transactionManager)
				.reader(addressReader).writer(addressWriter).build();
	}

	@Bean
	Step stepOfHittakuri(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("stepOfHittakuri", jobRepository)
				.<HittakuriOriginal, Hittakuri>chunk(1000, transactionManager).reader(hittakuriReader)
				.processor(hittakuriProcessor).writer(hittakuriWriter).build();
	}

	@Bean
	Job job(JobRepository jobRepository, Step stepOfPrefecture, Step stepOfMunicipality, Step stepOfAddress,
			Step stepOfHittakuri) {
		return new JobBuilder("job", jobRepository).incrementer(new RunIdIncrementer()).start(stepOfPrefecture)
				.next(stepOfMunicipality).next(stepOfAddress).next(stepOfHittakuri).build();

	}

}
