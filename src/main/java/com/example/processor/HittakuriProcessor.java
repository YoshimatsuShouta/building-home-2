package com.example.processor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.domain.Hittakuri;
import com.example.domain.HittakuriOriginal;

import jakarta.transaction.Transactional;

@Component
@StepScope()
public class HittakuriProcessor implements ItemProcessor<HittakuriOriginal, Hittakuri> {

	@Autowired
	private NamedParameterJdbcTemplate template;

	@Override
	public Hittakuri process(HittakuriOriginal hittakuriOriginal) throws Exception {

		Hittakuri hittakuri = new Hittakuri();
		hittakuri.setIncidentDateTime(
				LocalDateTime.of(hittakuriOriginal.getIncidentDate(), hittakuriOriginal.getIncidentTime()));
		hittakuri.setPoliceStation(hittakuriOriginal.getPoliceStation());
		hittakuri.setPoliceBox(hittakuriOriginal.getPoliceBox());
		hittakuri.setAddressId(getAddressId(hittakuriOriginal.getAddressName()));
		hittakuri.setVictimGenderId(getGenderId(hittakuriOriginal.getGenderVictim()));
		hittakuri.setVictimAgeId(getAgeId(hittakuriOriginal.getAgeVictim()));
		hittakuri.setCashDamageId(getCashDamageId(hittakuriOriginal.getCashDamage()));

		String addressName = convertAddressArabicToKanji(hittakuriOriginal.getAddressName());
		hittakuri.setAddressId(getAddressId(addressName));

		return hittakuri;

	}

	/**
	 * 住所IDの取得.
	 * 
	 * @param addressName 住所(文字列)
	 * @return 該当住所ID
	 */
	@Transactional
	private Integer getAddressId(String addressName) {
		String sql = "SELECT id FROM address WHERE name = :addressName";
		List<Integer> addressIdList = template.queryForList(sql, new MapSqlParameterSource("addressName", addressName),
				Integer.class);
		if (addressIdList.size() == 0) {
			return null;
		} else {
			return addressIdList.get(0);
		}
	}

	/**
	 * 被害者性別IDの取得.
	 * 
	 * @param gender 被害者性別(文字列)
	 * @return 該当被害者性別ID
	 */
	@Transactional
	private Integer getGenderId(String gender) {
		String sql = "SELECT id FROM genders WHERE value = :gender";
		List<Integer> genderIdList = template.queryForList(sql, new MapSqlParameterSource("gender", gender),
				Integer.class);
		if (genderIdList.size() == 0) {
			return null;
		} else {
			return genderIdList.get(0);
		}
	}

	/**
	 * 被害者年齢IDの取得.
	 * 
	 * @param age 被害者年齢
	 * @return 該当被害者年齢ID
	 */
	@Transactional
	private Integer getAgeId(String age) {
		String sql = "SELECT id FROM victim_ages WHERE value = :age";
		List<Integer> ageIdList = template.queryForList(sql, new MapSqlParameterSource("age", age), Integer.class);
		if (ageIdList.size() == 0) {
			return null;
		} else {
			return ageIdList.get(0);
		}

	}

	/**
	 * 現金被害IDの取得.
	 * 
	 * @param cashDamage 現金被害の有無
	 * @return 該当現金被害ID
	 */
	@Transactional
	private Integer getCashDamageId(String cashDamage) {
		String sql = "SELECT id FROM cash_damages WHERE value = :cashDamage";
		List<Integer> ageIdList = template.queryForList(sql, new MapSqlParameterSource("cashDamage", cashDamage),
				Integer.class);
		if (ageIdList.size() == 0) {
			return null;
		} else {
			return ageIdList.get(0);
		}

	}

	/**
	 * 町丁目名に含まれる全角数字を漢数字に変換する.
	 * 
	 * @param arabicAddress 町丁目名
	 * @return 全角数字が漢数字に変換された町丁目名
	 */
	private String convertAddressArabicToKanji(String arabicAddress) {
		Pattern patternOfSingleDigit = Pattern.compile("([１２３４５６７８９])丁目");
		Pattern patternOf10s = Pattern.compile("([１２３４５６７８９]０)丁目");
		Pattern patternOf11 = Pattern.compile("１１丁目");
		Pattern patternOf12To19 = Pattern.compile("1[２３４５６７８９]丁目");
		Pattern patternOfDoubleDigit = Pattern.compile("([１２３４５６７８９][１２３４５６７８９])丁目");
		Matcher matcherOfSingleDigit = patternOfSingleDigit.matcher(arabicAddress);
		Matcher matcherOf10s = patternOf10s.matcher(arabicAddress);
		Matcher matcherOf11 = patternOf11.matcher(arabicAddress);
		Matcher matcherOf12to19 = patternOf12To19.matcher(arabicAddress);
		Matcher matcherOfDoubleDigit = patternOfDoubleDigit.matcher(arabicAddress);

		if (matcherOfSingleDigit.find()) {
			StringBuilder convertedAddress = new StringBuilder(arabicAddress);
			convertedAddress = convertedAddress.replace(0, convertedAddress.length(),
					convertedAddress.toString().replace("１", "一").replace("２", "二").replace("３", "三").replace("４", "四")
							.replace("５", "五").replace("６", "六").replace("７", "七").replace("８", "八").replace("９", "九"));
			arabicAddress = convertedAddress.toString();
		} else if (matcherOf10s.find()) {
			StringBuilder convertedAddress = new StringBuilder(arabicAddress);
			convertedAddress = convertedAddress.replace(0, convertedAddress.length(),
					convertedAddress.toString().replace("１", "").replace("２", "二").replace("３", "三").replace("４", "四")
							.replace("５", "五").replace("６", "六").replace("７", "七").replace("８", "八").replace("９", "九")
							.replace("０", "十"));
			arabicAddress = convertedAddress.toString();
		} else if (matcherOf11.find()) {
			arabicAddress = arabicAddress.replace("１１", "十一");
		} else if (matcherOf12to19.find()) {
			StringBuilder convertedAddress = new StringBuilder(arabicAddress);
			convertedAddress = convertedAddress.replace(0, convertedAddress.length(),
					convertedAddress.toString().replace("１", "十").replace("２", "二").replace("３", "三").replace("４", "四")
							.replace("５", "五").replace("６", "六").replace("７", "七").replace("８", "八").replace("９", "九"));
			arabicAddress = convertedAddress.toString();
		} else if (matcherOfDoubleDigit.find()) {
			StringBuilder convertedAddress = new StringBuilder(arabicAddress);
			convertedAddress = convertedAddress.replace(0, convertedAddress.length(),
					convertedAddress.toString().replace("１", "一").replace("２", "二").replace("３", "三").replace("４", "四")
							.replace("５", "五").replace("６", "六").replace("７", "七").replace("８", "八").replace("９", "九"));
			convertedAddress = convertedAddress.insert(1, "十");
			arabicAddress = convertedAddress.toString();
		}

		return arabicAddress;
	}

}
