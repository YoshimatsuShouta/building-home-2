package com.example.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * prefectureテーブルの情報を保持するクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Data
@Entity
@Table(name = "prefectures")
public class Prefecture {

	/** 都道府県ID */
	@Id
	@Column(name = "id")
	private int id;
	/** 都道府県名 */
	@Column(name = "name")
	private String name;
	/** 都道府県カナ */
	@Column(name = "name_kana")
	private String nameKana;
	/** 都道府県ローマ字 */
	@Column(name = "name_rome")
	private String nameRome;

}
