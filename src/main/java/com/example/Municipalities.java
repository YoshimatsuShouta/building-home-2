package com.example;

public class Municipalities {
	/** 市区町村コード */
	private Integer id;
	/** 市区町村名 */
	private String name;
	/** 市区町村名カナ */
	private String nameKana;
	/** 市区町村名ローマ字 */
	private String nameRome;
	/** 都道府県コード */
	private Integer prefecturesCode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameKana() {
		return nameKana;
	}

	public void setNameKana(String nameKana) {
		this.nameKana = nameKana;
	}

	public String getNameRome() {
		return nameRome;
	}

	public void setNameRome(String nameRome) {
		this.nameRome = nameRome;
	}

	public Integer getPrefecturesCode() {
		return prefecturesCode;
	}

	public void setPrefecturesCode(Integer prefecturesCode) {
		this.prefecturesCode = prefecturesCode;
	}

	@Override
	public String toString() {
		return "Municipalities [id=" + id + ", name=" + name + ", nameKana=" + nameKana + ", nameRome=" + nameRome
				+ ", prefecturesCode=" + prefecturesCode + "]";
	}
}
