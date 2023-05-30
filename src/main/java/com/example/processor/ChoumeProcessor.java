package com.example.processor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.domain.Address;
import com.example.domain.Choume;
import com.example.service.ChoumeService;

/**
 * AddressオブジェクトからChoumeオブジェクトを生成する業務処理を行うクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Component
@StepScope
public class ChoumeProcessor implements ItemProcessor<Address, Choume> {

	@Autowired
	private ChoumeService service;

	@Override
	public Choume process(Address address) throws Exception {
		String addressName = address.getName();
		Pattern patternOfKanji = Pattern.compile("([一二三四五六七八九]?[一二三四五六七八九十]?[一二三四五六七八九十]丁目)");
		Pattern patternOfArabic = Pattern.compile("\\s([1-9]?[0-9])$");
		Matcher matcherOfKanji = patternOfKanji.matcher(addressName);

		String choumeName;
		Choume choume = new Choume();

		if (matcherOfKanji.find()) {
			choumeName = matcherOfKanji.group();
		} else {
			choumeName = null;
		}
		Matcher matcherOfNameKana = patternOfArabic.matcher(address.getNameKana() == null ? "" : address.getNameKana());
		Matcher matcherOfnameRome = patternOfArabic.matcher(address.getNameRome() == null ? "" : address.getNameRome());
		String choumeNameKana = matcherOfNameKana.find() ? matcherOfNameKana.group() : null;
		String choumeNameRome = matcherOfnameRome.find() ? matcherOfnameRome.group() : null;
		String townName = matcherOfKanji.replaceAll("");
		choume.setTownId(service.getTownId(townName, address.getMunicipalityId()));
		choume.setName(choumeName);
		choume.setNameKana(choumeNameKana);
		choume.setNameRome(choumeNameRome);
		choume.setAddressId(address.getId());

		return choume;
	}
}
