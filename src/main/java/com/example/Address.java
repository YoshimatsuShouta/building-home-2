package com.example;

public class Address {
	/** 町丁名 */
	private String address;
	/** 町丁名カナ */
	private String addressKana;
	/** 町丁名ローマ字 */
	private String addressRome;
	/** 緯度 */
	private Double latitude;
	/** 経度 */
	private Double longitude;
	/** 都道府県コード */
	private Integer prefecturesCode;
	/** 市区町村コード */
	private Integer municipalitiesCode;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressKana() {
		return addressKana;
	}

	public void setAddressKana(String addressKana) {
		this.addressKana = addressKana;
	}

	public String getAddressRome() {
		return addressRome;
	}

	public void setAddressRome(String addressRome) {
		this.addressRome = addressRome;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getPrefecturesCode() {
		return prefecturesCode;
	}

	public void setPrefecturesCode(Integer prefecturesCode) {
		this.prefecturesCode = prefecturesCode;
	}

	public Integer getMunicipalitiesCode() {
		return municipalitiesCode;
	}

	public void setMunicipalitiesCode(Integer municipalitiesCode) {
		this.municipalitiesCode = municipalitiesCode;
	}

	@Override
	public String toString() {
		return "Address [address=" + address + ", addressKana=" + addressKana + ", addressRome=" + addressRome
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", prefecturesCode=" + prefecturesCode
				+ ", municipalitiesCode=" + municipalitiesCode + "]";
	}
}
