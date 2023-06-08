package com.example.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * municipalitiesテーブルの情報を保持するクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Data
@Entity
@Table(name = "municipalities")
public class Municipality {

	/** 市区町村ID */
	@Id
	@Column(name = "id")
	private int id;
	/** 市区町村名 */
	@Column(name = "name")
	private String name;
	/** 市区町村名カナ */
	@Column(name = "name_kana")
	private String nameKana;
	/** 市区町村名ローマ字 */
	@Column(name = "name_rome")
	private String nameRome;
	/** 都道府県ID */
	@Column(name = "prefecture_id")
	private int prefectureId;
}
