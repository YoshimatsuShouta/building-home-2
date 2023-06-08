package com.example.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * railway_linesテーブルの情報を保持するドメイン.
 * 
 * @author yoshimatsushouta
 *
 */
@Data
@Entity
@Table(name = "railway_lines")
public class RailwayLine {

	/** 沿線ID */
	@Id
	@Column(name = "id")
	private int id;

	/** 鉄道会社ID */
	@Column(name = "company_id")
	private int compnayId;

	/** 沿線名 */
	@Column(name = "line_name")
	private String lineName;

	/** エンセンメイ */
	@Column(name = "line_name_k")
	private String lineNameK;

	/** 沿線名別名 */
	@Column(name = "line_name_h")
	private String lineNameH;

	/** 経度 */
	@Column(name = "longitude")
	private double longitude;

	/** 緯度 */
	@Column(name = "latitude")
	private double latitude;
}
