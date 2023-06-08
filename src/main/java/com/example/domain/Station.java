package com.example.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * stationsテーブルの情報を保持するクラス.
 * 
 * @author yoshimatsushouta
 *
 */
@Data
@Entity
@Table(name = "stations")
public class Station {

	/** 駅ID */
	@Id
	@Column(name = "id")
	private Integer id;

	/** 駅名 */
	@Column(name = "station_name")
	private String stationName;

	/** 沿線ID */
	@Column(name = "line_id")
	private Integer lineId;

	/** 郵便番号 */
	@Column(name = "post")
	private String post;

	/** 住所 */
	@Column(name = "address")
	private String address;

	/** 経度 */
	@Column(name = "longitude")
	private Double longitude;

	/** 緯度 */
	@Column(name = "latitude")
	private Double latitude;
}
