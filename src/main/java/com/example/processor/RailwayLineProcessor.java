package com.example.processor;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.domain.RailwayLine;
import com.example.domain.RailwayLineOriginal;

/**
 * RailwayLineOriginalオブジェクトをRailwayLineオブジェクトに変換する業務処理を行うプロセッサクラス.
 * 
 * @author yoshimatsushouta
 *
 */
@Component
@StepScope
public class RailwayLineProcessor implements ItemProcessor<RailwayLineOriginal, RailwayLine> {

	@Override
	public RailwayLine process(RailwayLineOriginal railwayLineOriginal) throws Exception {
		RailwayLine railwayLine = new RailwayLine();

		railwayLine.setId(railwayLineOriginal.getLineId());
		railwayLine.setCompnayId(railwayLineOriginal.getCompanyId());
		railwayLine.setLineName(railwayLineOriginal.getLineName());
		railwayLine.setLineNameK(railwayLineOriginal.getLineNameKana());
		railwayLine.setLineNameH(railwayLineOriginal.getLineNameH());
		railwayLine.setLongitude(railwayLineOriginal.getLongitude());
		railwayLine.setLatitude(railwayLineOriginal.getLatitude());

		return railwayLine;
	}
}
