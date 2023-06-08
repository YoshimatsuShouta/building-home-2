package com.example.listner;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class PrefectureStepListner implements StepExecutionListener {

	@Override
	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		StepExecutionListener.super.beforeStep(stepExecution);
		System.out.println("PrefectureのSTEP処理を開始します。");
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		System.out.println("PrefectureのSTEP処理が終了しました。");
		return StepExecutionListener.super.afterStep(stepExecution);
	}

}
