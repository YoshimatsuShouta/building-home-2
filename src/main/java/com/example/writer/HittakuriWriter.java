package com.example.writer;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.stereotype.Component;

import com.example.domain.Hittakuri;

import jakarta.persistence.EntityManagerFactory;

/**
 * Hittakuriオブジェクトをhittakuriテーブルに挿入するライタークラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Component
@StepScope
public class HittakuriWriter implements ItemWriter<Hittakuri> {

	private JpaItemWriter<Hittakuri> writer;

	public HittakuriWriter(EntityManagerFactory entityManagerFactory) {
		writer = new JpaItemWriter<>();
		writer.setEntityManagerFactory(entityManagerFactory);
	}

	@Override
	public void write(Chunk<? extends Hittakuri> chunk) throws Exception {

		writer.write(chunk);

	}

}
