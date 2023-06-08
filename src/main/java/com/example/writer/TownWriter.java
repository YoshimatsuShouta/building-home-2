package com.example.writer;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.stereotype.Component;

import com.example.domain.Town;

import jakarta.persistence.EntityManagerFactory;

/**
 * Townオブジェクトをtownsテーブルに挿入するためのライタークラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Component
@StepScope
public class TownWriter implements ItemWriter<Town> {

	private JpaItemWriter<Town> writer;

	public TownWriter(EntityManagerFactory entityManagerFactory) {
		writer = new JpaItemWriter<>();
		writer.setEntityManagerFactory(entityManagerFactory);

	}

	@Override
	public void write(Chunk<? extends Town> chunk) throws Exception {
		writer.write(chunk);

	}

}
