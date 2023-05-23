package com.example;

import java.util.List;

/**
 * 住所、市区町村、都道府県テーブルに情報を挿入するメイン.
 * 
 * @author yoshimatsushouta
 *
 */
public class PrefecturesMain {
	public static void main(String[] args) {
		OriginalRepository originalRepository = new OriginalRepository();
		PrefecturesRepository prefecturesRepository = new PrefecturesRepository();
		MunicipalitiesRepository municipalitiesRepository = new MunicipalitiesRepository();
		AddressRepository addressRepository = new AddressRepository();

		List<Original> originalList = originalRepository.findAll();
		System.out.println(originalList.size());
		prefecturesRepository.insertToPrefectures(originalList);
		municipalitiesRepository.insertToMunicipalities(originalList);
		addressRepository.insertToAddress(originalList);
	}
}
