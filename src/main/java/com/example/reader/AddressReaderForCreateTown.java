package com.example.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.stereotype.Component;

import com.example.domain.Address;

import jakarta.persistence.EntityManagerFactory;

/**
 * addressesテーブルからTownオブジェクトを取得するリーダークラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Component
@StepScope
public class AddressReaderForCreateTown implements ItemReader<Address> {

	private JpaCursorItemReader<Address> reader;

	public AddressReaderForCreateTown(EntityManagerFactory entityManagerFactory) {
		reader = new JpaCursorItemReader<>();
		reader.setEntityManagerFactory(entityManagerFactory);
		reader.setQueryString("SELECT e FROM Address e");
		reader.setSaveState(true);
		reader.open(new ExecutionContext());
	}

	@Override
	public Address read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		return reader.read();
	}

}
