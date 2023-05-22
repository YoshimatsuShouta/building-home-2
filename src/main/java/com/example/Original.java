package com.example;

/**
 * オリジナルの情報に関するドメイン.
 * 
 * @author yoshimatsushouta
 *
 */
public class Original {
	/** 都道府県コード */
	private Integer prefecturesCode;
	/** 都道府県名 */
	private String prefectures;
	/** 都道府県名カナ */
	private String prefecturesKana;
	/** 都道府県名ローマ字 */
	private String prefecturesRome;
	/** 市区町村コード */
	private Integer municipalitiesCode;
	/** 市区町村名 */
	private String municipalities;
	/** 市区町村名カナ */
	private String municipalitiesKana;
	/** 市区町村名ローマ字 */
	private String municipalitiesRome;
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

	public Integer getPrefecturesCode() {
		return prefecturesCode;
	}

	public void setPrefecturesCode(Integer prefecturesCode) {
		this.prefecturesCode = prefecturesCode;
	}

	public String getPrefectures() {
		return prefectures;
	}

	public void setPrefectures(String prefectures) {
		this.prefectures = prefectures;
	}

	public String getPrefecturesKana() {
		return prefecturesKana;
	}

	public void setPrefecturesKana(String prefecturesKana) {
		this.prefecturesKana = prefecturesKana;
	}

	public String getPrefecturesRome() {
		return prefecturesRome;
	}

	public void setPrefecturesRome(String prefecturesRome) {
		this.prefecturesRome = prefecturesRome;
	}

	public Integer getMunicipalitiesCode() {
		return municipalitiesCode;
	}

	public void setMunicipalitiesCode(Integer municipalitiesCode) {
		this.municipalitiesCode = municipalitiesCode;
	}

	public String getMunicipalities() {
		return municipalities;
	}

	public void setMunicipalities(String municipalities) {
		this.municipalities = municipalities;
	}

	public String getMunicipalitiesKana() {
		return municipalitiesKana;
	}

	public void setMunicipalitiesKana(String municipalitiesKana) {
		this.municipalitiesKana = municipalitiesKana;
	}

	public String getMunicipalitiesRome() {
		return municipalitiesRome;
	}

	public void setMunicipalitiesRome(String municipalitiesRome) {
		this.municipalitiesRome = municipalitiesRome;
	}

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

	@Override
	public String toString() {
		return "Original [prefecturesCode=" + prefecturesCode + ", prefectures=" + prefectures + ", prefecturesKana="
				+ prefecturesKana + ", prefecturesRome=" + prefecturesRome + ", municipalitiesCode="
				+ municipalitiesCode + ", municipalities=" + municipalities + ", municipalitiesKana="
				+ municipalitiesKana + ", municipalitiesRome=" + municipalitiesRome + ", address=" + address
				+ ", addressKana=" + addressKana + ", addressRome=" + addressRome + ", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
	}
}
