package com.example.writer;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.stereotype.Component;

import com.example.domain.Institutions;

import jakarta.persistence.EntityManagerFactory;

@Component
@StepScope
public class InstitutionsWriter implements ItemWriter<Institutions> {

	private JpaItemWriter<Institutions> writer;

	public InstitutionsWriter(EntityManagerFactory entityManagerFactory) {
		writer = new JpaItemWriter<>();
		writer.setEntityManagerFactory(entityManagerFactory);
	}

	@Override
	public void write(Chunk<? extends Institutions> chunk) throws Exception {
		writer.write(chunk);
	}
}
