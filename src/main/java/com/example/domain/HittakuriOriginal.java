package com.example.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import lombok.Data;

@Data
public class HittakuriOriginal {
	/** 罪名 */
	private String offenceName;
	/** 手口 */
	private String modusOperandi;
	/** 所轄警察署 */
	private String policeStation;
	/** 所轄交番・駐在所 */
	private String policeBox;
	/** 市区町村コード */
	private Integer municipalityCode;
	/** 都道府県 */
	private String prefectureName;
	/** 市区町村 */
	private String municipalityName;
	/** 町丁目 */
	private String addressName;
	/** 発生年月日 */
	private LocalDate incidentDate;
	/** 発生時 */
	private LocalTime incidentTime;
	/** 発生場所 */
	private String crimeScene;
	/** 被害者の性別 */
	private String genderVictim;
	/** 被害者の年齢 */
	private String ageVictim;
	/** 現金被害の有無 */
	private String cashDamage;

	public void setIncidentDate(String incidentDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d");
		this.incidentDate = LocalDate.parse(incidentDate, formatter);
	}

	public void setIncidentTime(int time) {
		this.incidentTime = LocalTime.of(time, 00);
	}

	public void setMunicipalityCode(String municipalityCode) {
		if (municipalityCode.isEmpty()) {
			System.out.println("市区町村コードが空のデータです。");
			this.municipalityCode = null;
			return;
		}

		try {
			this.municipalityCode = Integer.valueOf(municipalityCode);
		} catch (NumberFormatException e) {
			throw new RuntimeException("CSVファイルの市区町村コードの変換中にエラーが発生しました。", e);
		}

	}

}
