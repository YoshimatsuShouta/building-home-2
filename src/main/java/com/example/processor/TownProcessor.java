package com.example.processor;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.domain.Address;
import com.example.domain.Town;

/**
 * Townオブジェクトに関する処理を行うクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Component
@StepScope
public class TownProcessor implements ItemProcessor<Address, Town> {

	Map<String, Integer> townMap;

	public TownProcessor() {
		townMap = new HashMap<>();
	}

	@Override
	public Town process(Address address) throws Exception {

		Town town;

		if (address.getName().indexOf("丁目") < 0) {
			town = Town.createTown(address.getName(), address.getNameKana(), address.getNameRome(),
					address.getMunicipalityId());

		} else {
			town = removeDuplicatesAndConvert(address);
		}
		return town;

	}

	private Town removeDuplicatesAndConvert(Address address) {

		String addressName = address.getName();
		Pattern patternOfKanji = Pattern.compile("([一二三四五六七八九]?[一二三四五六七八九十]?[一二三四五六七八九十]丁目)");
		Pattern patternOfArabic = Pattern.compile("\\s[1-9]?[0-9]$");
		Matcher matcherOfKanji = patternOfKanji.matcher(addressName);

		String townName = matcherOfKanji.replaceAll("");
		String key = address.getMunicipalityId() + townName;

		if (townMap.get(key) == null) {
			String addressNameKana = address.getNameKana();
			String addressNameRome = address.getNameRome();

			townMap.put(key, 0);

			// 町域(丁目除く)のカナを作成(町域カナの丁目部分を取り除く)
			Matcher matcherOfArabicForKana = patternOfArabic.matcher(addressNameKana == null ? "" : addressNameKana);
			String townNameKana = matcherOfArabicForKana.replaceAll("");
			// 町域(丁目除く)のローマ字を作成(町域ローマ字の丁目部分を取り除く)
			Matcher matcherOfArabicForRome = patternOfArabic.matcher(addressNameRome == null ? "" : addressNameRome);
			String townNameRome = matcherOfArabicForRome.replaceAll("");

			Town town = Town.createTown(townName, townNameKana, townNameRome, address.getMunicipalityId());
			return town;

		} else {
			return null;
		}

	}

}
