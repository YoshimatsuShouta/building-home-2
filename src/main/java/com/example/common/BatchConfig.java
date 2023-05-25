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
import com.example.listner.PrefectureRederListner;
import com.example.listner.PrefectureStepListner;
import com.example.processor.HittakuriProcessor;
import com.example.reader.AddressReader;
import com.example.reader.HittakuriOriginalReader;
import com.example.reader.MunicipalityReader;
import com.example.reader.PrefectureReader;
import com.example.writer.AddressWriter;
import com.example.writer.HittakuriWriter;
import com.example.writer.MunicipalityWriter;
import com.example.writer.PrefectureWriter;

/**
 * バッチ処理の設定を行うクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Component
@Configuration
public class BatchConfig {

	@Autowired
	private PrefectureReader prefectureReader;
	@Autowired
	private PrefectureWriter prefectureWriter;

	@Autowired
	private MunicipalityReader municipalityReader;
	@Autowired
	private MunicipalityWriter municipalityWriter;

	@Autowired
	private AddressReader addressReader;
	@Autowired
	private AddressWriter addressWriter;

	@Autowired
	private HittakuriProcessor hittakuriProcessor;
	@Autowired
	private HittakuriWriter hittakuriWriter;

	@Bean
	Step stepOfPrefecture(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("stepOfPrefecture", jobRepository).<Prefecture, Prefecture>chunk(50, transactionManager)
				.reader(prefectureReader).writer(prefectureWriter).build();
	}

	@Bean
	Step stepOfMunicipality(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("stepOfmunicipality", jobRepository)
				.<Municipality, Municipality>chunk(1000, transactionManager).reader(municipalityReader)
				.writer(municipalityWriter).build();
	}

	@Bean
	Step stepOfAddress(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("stepOfAddress", jobRepository).<Address, Address>chunk(5000, transactionManager)
				.reader(addressReader).writer(addressWriter).build();
	}

	@Bean
	Step stepOfHittakuriTokyo2021(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("stepOfHittakuriTokyo2021", jobRepository)
				.<HittakuriOriginal, Hittakuri>chunk(1000, transactionManager)
				.reader(new HittakuriOriginalReader("/csv/hittakuri/tokyo_2021hittakuri.csv"))
				.processor(hittakuriProcessor).writer(hittakuriWriter).build();
	}

	@Bean
	Step stepOfHittakuriTokyo2020(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("stepOfHittakuriTokyo2020", jobRepository)
				.<HittakuriOriginal, Hittakuri>chunk(1000, transactionManager)
				.reader(new HittakuriOriginalReader("/csv/hittakuri/tokyo_2020hittakuri.csv"))
				.processor(hittakuriProcessor).writer(hittakuriWriter).build();
	}

	@Bean
	Step stepOfHittakuriTokyo2019(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("stepOfHittakuriTokyo2019", jobRepository)
				.<HittakuriOriginal, Hittakuri>chunk(1000, transactionManager)
				.reader(new HittakuriOriginalReader("/csv/hittakuri/tokyo_2019hittakuri.csv"))
				.processor(hittakuriProcessor).writer(hittakuriWriter).build();
	}

	@Bean
	Job job(JobRepository jobRepository, Step stepOfPrefecture, Step stepOfMunicipality, Step stepOfAddress,
			Step stepOfHittakuriTokyo2021, Step stepOfHittakuriTokyo2020, Step stepOfHittakuriTokyo2019) {
		return new JobBuilder("job", jobRepository).incrementer(new RunIdIncrementer()).start(stepOfPrefecture)
				.next(stepOfMunicipality).next(stepOfAddress).next(stepOfHittakuriTokyo2021)
				.next(stepOfHittakuriTokyo2020).next(stepOfHittakuriTokyo2019).build();
	}

}
