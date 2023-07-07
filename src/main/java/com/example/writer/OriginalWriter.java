package com.example.writer;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.stereotype.Component;

import com.example.domain.Original;

import jakarta.persistence.EntityManagerFactory;

@Component
@StepScope
public class OriginalWriter implements ItemWriter<Original> {

	private JpaItemWriter<Original> writer;

	public OriginalWriter(EntityManagerFactory entityManagerFactory) {
		writer = new JpaItemWriter<>();
		writer.setEntityManagerFactory(entityManagerFactory);
	}

	@Override
	public void write(Chunk<? extends Original> chunk) throws Exception {
		writer.write(chunk);
	}
}
