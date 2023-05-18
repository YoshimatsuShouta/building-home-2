package com.example;

public class PriceOfLand {
	/** 市区町村コード */
	private Integer municipalitiesCode;
	/** 所在並びに地番 */
	private String addressNum;
	/** 当年価格 */
	private Integer currentPrice;
	/** 前年価格 */
	private Integer previosPrice;
	/** 対前年変動率 */
	private Double changePreviosPrice;
	/** 地積 */
	private Integer acreage;
	/** 最寄り駅名 */
	private String nearestStation;
	/** 利用の現況 */
	private String usage;
	/** 周辺の土地の現況 */
	private String neighborhoodUsage;
	/** 情報の年 */
	private Integer infoYear;
	/** DB登録時検索用住所 */
	private String addressForSearch;

	public Integer getMunicipalitiesCode() {
		return municipalitiesCode;
	}

	public void setMunicipalitiesCode(Integer municipalitiesCode) {
		this.municipalitiesCode = municipalitiesCode;
	}

	public String getAddressNum() {
		return addressNum;
	}

	public void setAddressNum(String addressNum) {
		this.addressNum = addressNum;
	}

	public Integer getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(Integer currentPrice) {
		this.currentPrice = currentPrice;
	}

	public Integer getPreviosPrice() {
		return previosPrice;
	}

	public void setPreviosPrice(Integer previosPrice) {
		this.previosPrice = previosPrice;
	}

	public Double getChangePreviosPrice() {
		return changePreviosPrice;
	}

	public void setChangePreviosPrice(Double changePreviosPrice) {
		this.changePreviosPrice = changePreviosPrice;
	}

	public Integer getAcreage() {
		return acreage;
	}

	public void setAcreage(Integer acreage) {
		this.acreage = acreage;
	}

	public String getNearestStation() {
		return nearestStation;
	}

	public void setNearestStation(String nearestStation) {
		this.nearestStation = nearestStation;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public String getNeighborhoodUsage() {
		return neighborhoodUsage;
	}

	public void setNeighborhoodUsage(String neighborhoodUsage) {
		this.neighborhoodUsage = neighborhoodUsage;
	}

	public Integer getInfoYear() {
		return infoYear;
	}

	public void setInfoYear(Integer infoYear) {
		this.infoYear = infoYear;
	}

	public String getAddressForSearch() {
		return addressForSearch.replaceAll("絹ヶ丘", "絹ケ丘").replaceAll("金井ヶ丘", "金井").replaceAll("大字和田", "和田")
				.replaceAll("大字落川", "落川").replaceAll("大字東長沼", "東長沼").replaceAll("大字矢野口", "矢野口").replaceAll("大字押立", "押立")
				.replaceAll("大字大丸", "大丸").replaceAll("大字百村", "百村").replaceAll("大字坂浜", "坂浜").replaceAll("羽字玉川附", "羽")
				.replaceAll("二宮字森腰", "二宮").replaceAll("草花字羽ケ田", "草花").replaceAll("引田字静ノ郷", "引田")
				.replaceAll("雨間字澤田", "雨間").replaceAll("平沢字西平", "平沢").replaceAll("野辺字宅地附", "野辺")
				.replaceAll("草花字山ノ神", "草花").replaceAll("伊奈字北伊奈前", "伊奈").replaceAll("舘谷字追原", "舘谷")
				.replaceAll("戸倉字西戸倉", "戸倉").replaceAll("高尾字橋本", "高尾").replaceAll("留原字中村", "留原")
				.replaceAll("伊奈字新宿", "伊奈").replaceAll("草花字小宮久保", "草花").replaceAll("山田字上分", "山田")
				.replaceAll("草花字折立下タ", "草花").replaceAll("父島字西町", "父島").replaceAll("父島字奥村", "父島")
				.replaceAll("父島字東町", "父島");
	}

	public void setAddressForSearch(String addressForSearch) {
		this.addressForSearch = addressForSearch;
	}

	@Override
	public String toString() {
		return "PriceOfLand [municipalitiesCode=" + municipalitiesCode + ", addressNum=" + addressNum
				+ ", currentPrice=" + currentPrice + ", previosPrice=" + previosPrice + ", changePreviosPrice="
				+ changePreviosPrice + ", acreage=" + acreage + ", nearestStation=" + nearestStation + ", usage="
				+ usage + ", neighborhoodUsage=" + neighborhoodUsage + ", infoYear=" + infoYear + ", addressForSearch="
				+ addressForSearch + "]";
	}
}
