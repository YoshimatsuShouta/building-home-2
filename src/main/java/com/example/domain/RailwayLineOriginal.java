package com.example.domain;

import lombok.Data;

/**
 * 沿線に関するCSVファイルの情報を保持するクラス.
 * 
 * @author yoshimatsushouta
 *
 */
@Data
public class RailwayLineOriginal {
	/** 沿線ID */
	private Integer lineId;
	/** 鉄道会社ID */
	private Integer companyId;
	/** 沿線名 */
	private String lineName;
	/** エンセンメイ */
	private String lineNameKana;
	/** 沿線名別名 */
	private String lineNameH;
	/** 沿線カラーH */
	private String lineColorH;
	/** 沿線カラーT */
	private String lineColorT;
	/** 沿線の種類 */
	private String lineType;
	/** 経度 */
	private Double longitude;
	/** 緯度 */
	private Double latitude;
	/** ズーム */
	private String zoom;
	/** Eステータス */
	private String eStatus;
	/** Eソート */
	private String eSort;
}
