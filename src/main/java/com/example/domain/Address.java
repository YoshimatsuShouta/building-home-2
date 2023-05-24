package com.example.domain;

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
@Table(name = "addresses")
public class Address {

	/** 町丁目ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	/** 町丁目名 */
	@Column(name = "name")
	private String name;
	/** 町丁目名カナ */
	@Column(name = "name_kana")
	private String nameKana;
	/** 町丁目名ローマ字 */
	@Column(name = "name_rome")
	private String nameRome;
	/** 緯度 */
	@Column(name = "latitude")
	private Double latitude;
	/** 経度 */
	@Column(name = "longitude")
	private Double longitude;
	/* 都道府県ID */
	@Column(name = "prefecture_id")
	private int prefectureId;
	/** 市区町村ID */
	@Column(name = "municipality_id")
	private int municipalityId;

}
