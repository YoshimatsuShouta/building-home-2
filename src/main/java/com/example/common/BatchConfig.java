package com.example.common;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.domain.Address;
import com.example.domain.Municipality;
import com.example.domain.Prefecture;
import com.example.listner.PrefectureRederListner;
import com.example.listner.PrefectureStepListner;
import com.example.reader.AddressReader;
import com.example.reader.MunicipalityReader;
import com.example.reader.PrefectureReader;
import com.example.writer.AddressWriter;
import com.example.writer.MunicipalityWriter;
import com.example.writer.PrefectureWriter;

/**
 * バッチ処理の設定を行うクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Component
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
	///////////////// listner/////////////////////
	@Autowired
	private PrefectureRederListner prefectureRederListner;
	@Autowired
	private PrefectureStepListner prefectureStepListner;

	@Bean
	Step stepOfPrefecture(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("stepOfPrefecture", jobRepository).<Prefecture, Prefecture>chunk(50, transactionManager)
				.reader(prefectureReader).writer(prefectureWriter).listener(prefectureRederListner)
				.listener(prefectureStepListner).build();
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
	Job job(JobRepository jobRepository, Step stepOfPrefecture, Step stepOfMunicipality, Step stepOfAddress) {
		return new JobBuilder("job", jobRepository).incrementer(new RunIdIncrementer()).start(stepOfPrefecture)
				.next(stepOfMunicipality).next(stepOfAddress).build();
	}

}
