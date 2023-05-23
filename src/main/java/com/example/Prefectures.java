package com.example;

/**
 * 都道府県コードに関するドメイン.
 * 
 * @author yoshimatsushouta
 *
 */
public class Prefectures {
	/** 都道府県コード */
	private Integer id;
	/** 都道府県名 */
	private String name;
	/** 都道府県カナ */
	private String nameKana;
	/** 都道府県ローマ字 */
	private String nameRome;

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

	@Override
	public String toString() {
		return "Prefectures [id=" + id + ", name=" + name + ", nameKana=" + nameKana + ", nameRome=" + nameRome + "]";
	}

}
