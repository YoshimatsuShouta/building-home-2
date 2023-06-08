package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.repository.TownRepository;

/**
 * Townオブジェクトに関する業務処理を行うクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Service
@Transactional
public class TownService {

	@Autowired
	private TownRepository townRepository;

	public void createIndex() {
		townRepository.createIndex();
	}
}
