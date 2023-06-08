package com.example.writer;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.stereotype.Component;

import com.example.domain.RailwayLine;

import jakarta.persistence.EntityManagerFactory;

/**
 * RailwayLineオブジェクトをrailway_linesテーブルに挿入するライタークラス.
 * 
 * @author yoshimatsushouta
 *
 */
@Component
@StepScope
public class RailwayLineWriter implements ItemWriter<RailwayLine> {

	private JpaItemWriter<RailwayLine> writer;

	public RailwayLineWriter(EntityManagerFactory entityManagerFactory) {
		writer = new JpaItemWriter<>();
		writer.setEntityManagerFactory(entityManagerFactory);
	}

	// RailwayLine型で書き込むことを指定する.
	@Override
	public void write(Chunk<? extends RailwayLine> chunk) throws Exception {
		writer.write(chunk);
	}
}
