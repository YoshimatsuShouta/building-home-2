package com.example.processor;

import java.util.Map;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.domain.Institutions;
import com.example.domain.InstitutionsOriginal;
import com.example.repository.AddressRepository;
import com.example.repository.POIRepository;

@Component
@StepScope
public class InstitutionsProcessor implements ItemProcessor<InstitutionsOriginal, Institutions> {

	@Autowired
	private AddressRepository addressRepos;
	@Autowired
	private POIRepository pOIRepos;

	@Override
	public Institutions process(InstitutionsOriginal institutionsOriginal) throws Exception {
		Institutions institutions = new Institutions();

		Map<String, Integer> addressMap = addressRepos.addressToMap();

		institutions.setName(institutionsOriginal.getName());
		institutions.setLongitude(Double.valueOf(institutionsOriginal.getLongitude()));
		institutions.setLatitude(Double.valueOf(institutionsOriginal.getLatitude()));
		institutions.setAvailableDays(institutionsOriginal.getAvailableDays());
		institutions.setStartTime(institutionsOriginal.getStartTime());
		institutions.setEndTime(institutionsOriginal.getEndTime());
		institutions.setPhoneNumber(institutionsOriginal.getPhoneNumber());
		institutions.setAddress(institutionsOriginal.getAddress());
		institutions.setPostalCode(institutionsOriginal.getPostalCode());
		institutions.setTagId(Integer.valueOf(institutionsOriginal.getTagId().replaceAll("a", "")));
		String convertAddress = convertHalfWidthNumber(institutionsOriginal.getAddress());
		for (String address : addressMap.keySet()) {
			if (convertAddress.contains(address)) {
				institutions.setAddressId(addressMap.get(address));
			}
		}
		for (String address : addressMap.keySet()) {
			if (institutionsOriginal.getAddress().contains(address)) {
				institutions.setAddressId(addressMap.get(address));
			}
		}

		return institutions;
	}

	/**
	 * 半角、漢数字を全角数字に変換する(10以降非対応).
	 * 
	 * @param data 変換するもの
	 * @return 返還後の数字
	 */
	public static String convertHalfWidthNumber(String data) {
		data = data.replace("0", "０").replace("1", "１").replace("2", "２").replace("3", "３").replace("4", "４")
				.replace("5", "５").replace("6", "６").replace("7", "７").replace("8", "８").replace("9", "９")
				.replace("一", "１").replace("二", "２").replace("三", "３").replace("四", "４").replace("五", "５")
				.replace("六", "６").replace("七", "７").replace("八", "８").replace("九", "９").replace("丁目", "");

		return data;
	}
}
