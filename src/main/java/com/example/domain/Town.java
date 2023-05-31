package com.example.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * townsテーブルの情報を保持するクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Data
@Entity
@Table(name = "towns")
public class Town {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	/** 市区町村ID */
	@Column(name = "municipality_id")
	private int municipalityId;

	/** 町域名(丁目を除く) */
	@Column(name = "name")
	private String name;

	/** 町域名カナ(丁目を除く) */
	@Column(name = "name_kana")
	private String nameKana;

	/** 町域名ローマ字(丁目を除く) */
	@Column(name = "name_rome")
	private String nameRome;

	/** 登録ユーザー */
	@Column(name = "registered_user")
	private String registeredUser;

	/** 登録日 */
	@CreationTimestamp
	@Column(name = "registered_date_time")
	private LocalDateTime registeredDateTime;

	/** 更新ユーザー */
	@Column(name = "updated_user")
	private String updatedUser;

	/** 更新日 */
	@UpdateTimestamp
	@Column(name = "updated_date_time")
	private LocalDateTime updatedDateTime;

	/** 論理削除判定 */
	@Column(name = "deleted")
	private boolean deleted;

	@PrePersist
	public void onPrePresist() {
		this.registeredUser = "バッチ処理";
		this.updatedUser = "バッチ処理";
		this.deleted = false;
	}
	
	public Town() {};
	
	private Town(String name ,String nameKana, String nameRome, int municipalityId) {
		this.name = name;
		this.nameKana = nameKana;
		this.nameRome = nameRome;
		this.municipalityId = municipalityId;
	}
	
	public static Town createTown(String townName,String townNameKana, String townNameRome, int municipalityId) {
		return new Town(townName, townNameKana, townNameRome, municipalityId);
				
	}

}
