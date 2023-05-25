package com.example.writer;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.stereotype.Component;

import com.example.domain.Prefecture;

import jakarta.persistence.EntityManagerFactory;

/**
 * Prefectureオブジェクトをprefecturesテーブルに挿入するライタークラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Component
@StepScope
public class PrefectureWriter implements ItemWriter<Prefecture> {

	private JpaItemWriter<Prefecture> writer;

	public PrefectureWriter(EntityManagerFactory entityManagerFactory) {
		writer = new JpaItemWriter<>();
		writer.setEntityManagerFactory(entityManagerFactory);
	}

	@Override
	public void write(Chunk<? extends Prefecture> chunk) throws Exception {
		writer.write(chunk);
	}
}
