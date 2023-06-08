package com.example.processor;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.domain.Station;
import com.example.domain.StationOriginal;

/**
 * StationOriginalオブジェクトをStationオブジェクトに変換する業務処理を行うプロセッサクラス.
 * 
 * @author yoshimatsushouta
 *
 */
@Component
@StepScope
public class StationProcessor implements ItemProcessor<StationOriginal, Station> {
	@Override
	public Station process(StationOriginal stationOriginal) throws Exception {
		Station station = new Station();

		station.setId(stationOriginal.getStationCd());
		station.setStationName(stationOriginal.getStationName());
		station.setLineId(stationOriginal.getLineId());
		station.setPost(stationOriginal.getPost());
		station.setAddress(stationOriginal.getAddress());
		station.setLongitude(stationOriginal.getLongtude());
		station.setLatitude(stationOriginal.getLatitude());

		return station;
	}
}
