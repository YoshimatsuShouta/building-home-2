package com.example.domain;

import lombok.Data;

@Data
public class StationOriginal {
	/** 駅ID */
	private Integer stationCd;
	/** 駅gID */
	private Integer stationGCd;
	/** 駅名 */
	private String stationName;
	/** 駅名kana */
	private String stationNameK;
	/** 駅名rome */
	private String stationNameR;
	/** 沿線ID */
	private Integer lineId;
	/** prefCd */
	private Integer prefCd;
	/** 郵便番号 */
	private String post;
	/** 住所 */
	private String address;
	/** 経度 */
	private Double longtude;
	/** 緯度 */
	private Double latitude;
	/** 開設日 */
	private String openYmd;
	/** 閉館日 */
	private String closeYmd;
	/** eステータス */
	private Integer eStatus;
	/** eソート */
	private Integer eSort;
}
