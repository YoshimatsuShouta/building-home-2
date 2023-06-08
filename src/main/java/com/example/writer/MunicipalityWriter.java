package com.example.writer;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.stereotype.Component;

import com.example.domain.Municipality;

import jakarta.persistence.EntityManagerFactory;

/**
 * Municipalityオブジェクトをmunicipalitiesテーブルに挿入するライタークラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Component
@StepScope
public class MunicipalityWriter implements ItemWriter<Municipality> {

	private JpaItemWriter<Municipality> writer;

	public MunicipalityWriter(EntityManagerFactory entityManagerFactory) {
		writer = new JpaItemWriter<>();
		writer.setEntityManagerFactory(entityManagerFactory);
	}

	@Override
	public void write(Chunk<? extends Municipality> chunk) throws Exception {
		writer.write(chunk);
	}


}
