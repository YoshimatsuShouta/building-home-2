package com.example.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * オリジナルの情報に関するドメイン.
 * 
 * @author yoshimatsushouta
 *
 */
@Data
public class Original {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	/** 都道府県コード */
	@Column(name = "prefectures_code")
	private Integer prefecturesCode;
	/** 都道府県名 */
	@Column(name = "prefectures")
	private String prefectures;
	/** 都道府県名カナ */
	@Column(name = "prefectures_kana")
	private String prefecturesKana;
	/** 都道府県名ローマ字 */
	@Column(name = "prefectures_rome")
	private String prefecturesRome;
	/** 市区町村コード */
	@Column(name = "municipalities_code")
	private Integer municipalitiesCode;
	/** 市区町村名 */
	@Column(name = "municipalities")
	private String municipalities;
	/** 市区町村名カナ */
	@Column(name = "municipalities_kana")
	private String municipalitiesKana;
	/** 市区町村名ローマ字 */
	@Column(name = "municipalities_rome")
	private String municipalitiesRome;
	/** 町丁名 */
	@Column(name = "address")
	private String address;
	/** 町丁名カナ */
	@Column(name = "address_kana")
	private String addressKana;
	/** 町丁名ローマ字 */
	@Column(name = "address_rome")
	private String addressRome;
	/** 緯度 */
	@Column(name = "latitude")
	private Double latitude;
	/** 経度 */
	@Column(name = "longitude")
	private Double longitude;

}
