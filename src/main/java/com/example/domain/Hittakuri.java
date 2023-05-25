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
 * hittakuriテーブルの情報を保持するクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Data
@Entity
@Table(name = "hittakuri")
public class Hittakuri {

	/** ひったくり事件ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	/** 事件発生日時 */
	@Column(name = "event_date_time")
	private LocalDateTime eventDateTime;

	/** 都道府県ID(発生地) */
	@Column(name = "prefecture_id")
	private int prefectureId;

	/** 市区町村ID(発生地) */
	@Column(name = "municipality_id")
	private int municipalityId;

	/** 町丁目ID(発生地) */
	@Column(name = "address_id")
	private int addressId;

	@Column(name = "police_station")
	/** 管轄警察署(発生地) */
	private String policeStation;

	/** 管轄交番・駐在所(発生地) */
	@Column(name = "police_box")
	private String policeBox;

	/** 被害者の性別ID */
	@Column(name = "victim_gender_id")
	private Integer victimGenderId;

	/** 被害者の年齢ID */
	@Column(name = "victim_age_id")
	private Integer victimAgeId;

	/** 現金被害の有無ID */
	@Column(name = "cash_damage_id")
	private Integer cashDamageId;

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
