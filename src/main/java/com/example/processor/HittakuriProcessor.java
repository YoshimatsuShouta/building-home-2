package com.example.processor;

import java.time.LocalDateTime;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.domain.Address;
import com.example.domain.Hittakuri;
import com.example.domain.HittakuriOriginal;
import com.example.service.HittakuriService;

/**
 * HittakuriオブジェクトをHittakuriオブジェクトに変換する業務処理を行うプロセッサクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Component
@StepScope
public class HittakuriProcessor implements ItemProcessor<HittakuriOriginal, Hittakuri> {

	@Autowired
	private HittakuriService service;

	@Override
	public Hittakuri process(HittakuriOriginal hittakuriOriginal) throws Exception {

		Hittakuri hittakuri = new Hittakuri();
		try {
			hittakuri.setEventDateTime(
					LocalDateTime.of(hittakuriOriginal.getEventDate(), hittakuriOriginal.getEventTime()));
		} catch (Exception e) {
			hittakuri.setEventDateTime(null);
		}

		Address address = service.getAddressByAddressName(hittakuriOriginal.getAddressName(),
				hittakuriOriginal.getMunicipalityName());

		hittakuri.setPrefectureId(address.getPrefectureId());
		hittakuri.setMunicipalityId(address.getMunicipalityId());
		hittakuri.setAddressId(address.getId());
		hittakuri.setPoliceStation(hittakuriOriginal.getPoliceStation());
		hittakuri.setPoliceBox(hittakuriOriginal.getPoliceBox());
		hittakuri.setVictimGenderId(service.getVictimGenderIdByVictimGender(hittakuriOriginal.getVictimGender()));
		hittakuri.setVictimAgeId(service.getVictimAgeIdByVictimAge(hittakuriOriginal.getVictimAge()));
		hittakuri.setCashDamageId(service.getCashDamageIdByCashDamage(hittakuriOriginal.getCashDamage()));

		return hittakuri;
	}

}
