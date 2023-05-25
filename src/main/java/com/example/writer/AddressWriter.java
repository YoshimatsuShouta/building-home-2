package com.example.writer;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.stereotype.Component;

import com.example.domain.Address;

import jakarta.persistence.EntityManagerFactory;

/**
 * Addressオブジェクトをaddresseseテーブルに挿入するライタークラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Component
@StepScope
public class AddressWriter implements ItemWriter<Address> {

	private JpaItemWriter<Address> writer;

	public AddressWriter(EntityManagerFactory entityManagerFactory) {
		writer = new JpaItemWriter<>();
		writer.setEntityManagerFactory(entityManagerFactory);
	}

	@Override
	public void write(Chunk<? extends Address> chunk) throws Exception {
		writer.write(chunk);

	}

}
