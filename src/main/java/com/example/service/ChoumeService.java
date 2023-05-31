package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.repository.TownRepository;

/**
 * Choumeオブジェクトに関する業務処理を行うクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Service
@Transactional
public class ChoumeService {

	@Autowired
	private TownRepository townRepoitory;

	public Integer getTownId(String townName, int municipalityId) {
		return townRepoitory.getTownId(townName, municipalityId);
	}

}
