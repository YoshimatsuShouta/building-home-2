package com.example.listner;

import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

import com.example.domain.Prefecture;

@Component
@StepScope
public class PrefectureRederListner implements ItemReadListener<Prefecture> {

	private int count = 0;

	@Override
	public void beforeRead() {
		count++;
		ItemReadListener.super.beforeRead();
		System.out.println("=================================");
		System.out.println(count + "回目のPrefectureReaderの実行を開始します。");
		System.out.println("=================================");
	}

	@Override
	public void afterRead(Prefecture item) {
		// TODO Auto-generated method stub
		ItemReadListener.super.afterRead(item);
		System.out.println("=================================");
		System.out.println(count + "回目のPrefectureReaderの処理が完了しました");
		System.out.println("=================================");
	}

}
