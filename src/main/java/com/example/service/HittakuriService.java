package com.example.service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Address;
import com.example.repository.AddressRepository;
import com.example.repository.CashDamageRepository;
import com.example.repository.VictimAgeRepository;
import com.example.repository.VictimGenderRepository;

import jakarta.transaction.Transactional;

/**
 * hittakuriに関するstepの業務処理を行うクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Service
@Transactional
public class HittakuriService {

	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private VictimGenderRepository victimGenderRepository;
	@Autowired
	private VictimAgeRepository victimAgeRepository;
	@Autowired
	private CashDamageRepository cashDamageRepository;

	/**
	 * 全角数字を含む町丁目名から町丁目情報を取得.
	 * 
	 * @param addressNameWithFullDigits 全角数字を含む町丁目名
	 * @return 該当町丁目情報
	 */
	public Address getAddressByAddressName(String addressNameWithFullDigits, String municipalityName) {
		String addressNameWithKanjiDigits = convertFullDigitsToKanjiGidits(addressNameWithFullDigits);
		if (addressNameWithFullDigits.indexOf("ヶ") < 0) {
			return addressRepository.getAddress(addressNameWithKanjiDigits, municipalityName);
		} else {
			return getAddressWithEdgeCase1(addressNameWithKanjiDigits, municipalityName);
		}
	}

	/**
	 * 文字列の被害者の性別情報から被害者の性別IDを取得.
	 * 
	 * @param victimGender 文字列の被害者の性別情報
	 * @return 該当被害者の性別ID
	 */
	public Integer getVictimGenderIdByVictimGender(String victimGender) {
		return victimGenderRepository.getVictimGenderIdByVictimGender(victimGender);
	}

	/**
	 * 文字列の被害者の年齢情報から被害者の年齢IDを取得.
	 * 
	 * @param victimAge 文字列の被害者の年齢情報
	 * @return 該当被害者の年齢ID
	 */
	public Integer getVictimAgeIdByVictimAge(String victimAge) {
		return victimAgeRepository.getVictimAgeIdByVictimAge(victimAge);
	}

	/**
	 * 文字列の現金被害の有無情報から現金被害の有無IDを取得.
	 * 
	 * @param cashDamage 文字列の現金被害の有無情報
	 * @return 該当現金被害の有無ID
	 */
	public Integer getCashDamageIdByCashDamage(String cashDamage) {
		return cashDamageRepository.getCashDamageIdByCashDamage(cashDamage);
	}

	private Address getAddressWithEdgeCase1(String addressName, String municipalityName) {
		String addressNamePattern1 = addressName.replaceAll("ヶ", "ケ");
		String addressNamePattern2 = addressName.replaceAll("ヶ", "が");
		String addressNamePattern3 = addressName.replaceAll("ヶ", "ガ");
		String addressNamePattern4 = addressName.replaceAll("ヶ", "ガ");

		Map<String, String> addressNameMap = new HashMap<>();
		addressNameMap.put("addressNamePattern1", addressNamePattern1);
		addressNameMap.put("addressNamePattern2", addressNamePattern2);
		addressNameMap.put("addressNamePattern3", addressNamePattern3);
		addressNameMap.put("addressNamePattern4", addressNamePattern4);

		return addressRepository.getAddress(addressNameMap, municipalityName);
	}

	private String convertFullDigitsToKanjiGidits(String addressNameWithFullDigits) {
		String fullDigits;
		StringBuilder kanjiDigits;

		Pattern patternOfSingleDigit = Pattern.compile("([１２３４５６７８９])丁目");
		Pattern patternOfMultipleOf10 = Pattern.compile("([１２３４５６７８９][０])丁目");
		Pattern patternOf11 = Pattern.compile("(１１)丁目");
		Pattern patternOf12To19 = Pattern.compile("(１[２３４５６７８９])丁目");
		Pattern patternOfDoubleDigit = Pattern.compile("([２３４５６７８９][１２３４５６７８９])丁目");
		Matcher matcherOfSingleDigit = patternOfSingleDigit.matcher(addressNameWithFullDigits);
		Matcher matcherOfMultipleOf10 = patternOfMultipleOf10.matcher(addressNameWithFullDigits);
		Matcher matcherOf11 = patternOf11.matcher(addressNameWithFullDigits);
		Matcher matcherOf12To19Matcher = patternOf12To19.matcher(addressNameWithFullDigits);
		Matcher matcherOfDoubleDigit = patternOfDoubleDigit.matcher(addressNameWithFullDigits);

		if (matcherOfSingleDigit.find()) {
			fullDigits = matcherOfSingleDigit.group();
			kanjiDigits = new StringBuilder(fullDigits);
			kanjiDigits = kanjiDigits.replace(0, kanjiDigits.length(),
					kanjiDigits.toString().replaceAll("１", "一").replaceAll("２", "二").replaceAll("３", "三")
							.replaceAll("４", "四").replaceAll("５", "五").replaceAll("６", "六").replaceAll("７", "七")
							.replaceAll("８", "八").replaceAll("９", "九"));
		} else if (matcherOfMultipleOf10.find()) {
			fullDigits = matcherOfMultipleOf10.group();
			kanjiDigits = new StringBuilder(fullDigits);
			kanjiDigits = kanjiDigits.replace(0, kanjiDigits.length(),
					kanjiDigits.toString().replaceAll("０", "十").replaceAll("１", "").replaceAll("２", "二")
							.replaceAll("３", "三").replaceAll("４", "四").replaceAll("５", "五").replaceAll("６", "六")
							.replaceAll("７", "七").replaceAll("８", "八").replaceAll("９", "九"));
		} else if (matcherOf11.find()) {
			fullDigits = matcherOf11.group();
			kanjiDigits = new StringBuilder("十一");
		} else if (matcherOf12To19Matcher.find()) {
			fullDigits = matcherOf12To19Matcher.group();
			kanjiDigits = new StringBuilder(fullDigits);
			kanjiDigits = kanjiDigits.replace(0, kanjiDigits.length(),
					kanjiDigits.toString().replaceAll("１", "十").replaceAll("２", "二").replaceAll("３", "三")
							.replaceAll("４", "四").replaceAll("５", "五").replaceAll("６", "六").replaceAll("７", "七")
							.replaceAll("８", "八").replaceAll("９", "九"));
		} else if (matcherOfDoubleDigit.find()) {
			fullDigits = matcherOfDoubleDigit.group();
			kanjiDigits = new StringBuilder(fullDigits);
			kanjiDigits = kanjiDigits.replace(0, kanjiDigits.length(),
					kanjiDigits.toString().replaceAll("１", "一").replaceAll("２", "二").replaceAll("３", "三")
							.replaceAll("４", "四").replaceAll("５", "五").replaceAll("６", "六").replaceAll("７", "七")
							.replaceAll("８", "八").replaceAll("９", "九"));
			kanjiDigits = kanjiDigits.insert(1, "十");
		} else {
			return addressNameWithFullDigits;
		}

		return addressNameWithFullDigits.replace(fullDigits, kanjiDigits.toString());

	}
}
