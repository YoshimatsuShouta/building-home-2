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
 * chomeテーブルの情報を保持するクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Data
@Entity
@Table(name = "choume")
public class Choume {

	/** 丁目ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	/** 町域(丁目除く)ID */
	@Column(name = "town_id")
	private int townId;

	/** 町域ID */
	@Column(name = "address_id")
	private int addressId;

	/** 丁目名 */
	@Column(name = "name")
	private String name;

	/** 丁目名カナ */
	@Column(name = "name_kana")
	private String nameKana;

	/** 丁目名ローマ字 */
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

}
