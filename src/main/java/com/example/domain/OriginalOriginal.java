package com.example.domain;

import lombok.Data;

@Data
public class OriginalOriginal {

	private int id;
	/** 都道府県コード */
	private Integer prefecturesCode;
	/** 都道府県名 */
	private String prefectures;
	/** 都道府県名カナ */
	private String prefecturesKana;
	/** 都道府県名ローマ字 */
	private String prefecturesRome;
	/** 市区町村コード */
	private Integer municipalitiesCode;
	/** 市区町村名 */
	private String municipalities;
	/** 市区町村名カナ */
	private String municipalitiesKana;
	/** 市区町村名ローマ字 */
	private String municipalitiesRome;
	/** 町丁名 */
	private String address;
	/** 町丁名カナ */
	private String addressKana;
	/** 町丁名ローマ字 */
	private String addressRome;
	/** 小文字・別称 */
	private String Alias;
	/** 緯度 */
	private Double latitude;
	/** 経度 */
	private Double longitude;

}
