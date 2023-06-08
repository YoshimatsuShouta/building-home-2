package com.example.writer;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.stereotype.Component;

import com.example.domain.Station;

import jakarta.persistence.EntityManagerFactory;

@Component
@StepScope
public class StationWriter implements ItemWriter<Station> {

	private JpaItemWriter<Station> writer;

	public StationWriter(EntityManagerFactory entityManagerFactory) {
		writer = new JpaItemWriter<>();
		writer.setEntityManagerFactory(entityManagerFactory);
	}

	// Station型で書き込むことを指定する.
	@Override
	public void write(Chunk<? extends Station> chunk) throws Exception {
		writer.write(chunk);
	}

}
