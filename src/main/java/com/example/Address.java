package com.example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * addressesテーブルの情報を保持するクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Data
@Entity
@Table(name = "address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/** 町丁名 */
	@Column(name = "name")
	private String name;

	/** 町丁名カナ */
	@Column(name = "name_kana")
	private String nameKana;

	/** 町丁名ローマ字 */
	@Column(name = "name_rome")
	private String nameRome;

	/** 緯度 */
	@Column(name = "latitude")
	private Double latitude;

	/** 経度 */
	@Column(name = "longitude")
	private Double longitude;

	/** 都道府県コード */
	@Column(name = "prefecture_id")
	private Integer prefectureId;

	/** 市区町村コード */
	@Column(name = "municipalityId")
	private Integer municipalityId;

}
