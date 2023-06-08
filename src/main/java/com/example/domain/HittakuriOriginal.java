package com.example.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import lombok.Data;

/**
 * ひったくりに関するCSVファイルの情報を保持するクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Data
public class HittakuriOriginal {

	/** 罪名 */
	private String crime;

	/** 手口 */
	private String crimeMethod;

	/** 管轄警察署(発生地) */
	private String policeStation;

	/** 管轄交番・駐在所(発生地) */
	private String policeBox;

	/** 市区町村コード(発生地) */
	private Integer municipalityId;

	/** 都道府県(発生地) */
	private String prefectureName;

	/** 市区町村(発生地) */
	private String municipalityName;

	/** 町丁目(発生地) */
	private String addressName;

	/** 発生年月日(始期) */
	private LocalDate eventDate;

	/** 発生時(始期) */
	private LocalTime eventTime;

	/** 発生場所 */
	private String eventLocation;

	/** 被害者の性別 */
	private String victimGender;

	/** 被害者の年齢 */
	private String victimAge;

	/** 現金被害の有無 */
	private String cashDamage;

	public void setEventDate(String eventDateStr) {
		Pattern patternOfHyphen = Pattern.compile("[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}");
		Pattern patternOfSlash = Pattern.compile("[0-9]{4}/[0-9]{1,2}/[0-9]{1,2}");
		DateTimeFormatter formatter;
		if (patternOfHyphen.matcher(eventDateStr).matches()) {
			formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
		} else if (patternOfSlash.matcher(eventDateStr).matches()) {
			formatter = DateTimeFormatter.ofPattern("yyyy/M/d");
		} else {
			formatter = null;
		}
		LocalDate eventDate;
		try {
			eventDate = LocalDate.parse(eventDateStr, formatter);
		} catch (Exception e) {
			eventDate = null;
		}
		this.eventDate = eventDate;
	}

	public void setEventTime(String eventTimeStr) {
		LocalTime eventTime;
		try {
			eventTime = LocalTime.of(Integer.valueOf(eventTimeStr), 00);
		} catch (Exception e) {
			eventTime = null;
		}
		this.eventTime = eventTime;
	}

	public void setPoliceBox(String policeBox) {
		if (policeBox.isEmpty()) {
			this.policeBox = "CSVデータに情報が記載されていません";
		} else {
			this.policeBox = policeBox;
		}
	}

}
