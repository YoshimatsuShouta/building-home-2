package com.example.listner;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.service.TownService;

/**
 * StepOfTownに関するリスナー.
 * 
 * @author sugaharatakamasa
 *
 */
@Component
@StepScope
public class StepOfTownListner implements StepExecutionListener {

	@Autowired
	private TownService service;

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {

		service.createIndex();
		System.out.println("townテーブルへの挿入が終了しました。");
		return StepExecutionListener.super.afterStep(stepExecution);
	}

}
