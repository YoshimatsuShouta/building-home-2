package com.example;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * addressesテーブルの情報を保持するクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Data
@Entity
/**
 * 住所情報に関するドメイン.
 * 
 * @author yoshimatsushouta
 *
 */

public class OldAddress {
	@Id
	private int id;

	/** 町丁名 */
	private String name;

	/** 町丁名カナ */
	private String nameKana;

	/** 町丁名ローマ字 */
	private String nameRome;

	/** 緯度 */
	private Double latitude;

	/** 経度 */
	private Double longitude;

	/** 都道府県コード */
	private Integer prefectureId;

	/** 市区町村コード */
	private Integer municipalityId;

}
