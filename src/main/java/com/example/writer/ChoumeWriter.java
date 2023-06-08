package com.example.writer;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.stereotype.Component;

import com.example.domain.Choume;

import jakarta.persistence.EntityManagerFactory;

/**
 * Choumeオブジェクトをchoumeテーブルに挿入するライタークラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Component
@StepScope
public class ChoumeWriter implements ItemWriter<Choume> {

	private JpaItemWriter<Choume> writer;

	public ChoumeWriter(EntityManagerFactory entityManagerFactory) {
		writer = new JpaItemWriter<>();
		writer.setEntityManagerFactory(entityManagerFactory);
	}

	@Override
	public void write(Chunk<? extends Choume> chunk) throws Exception {
		writer.write(chunk);

	}

}
