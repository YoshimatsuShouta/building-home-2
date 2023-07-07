package com.example.processor;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.domain.Original;
import com.example.domain.OriginalOriginal;

@Component
@StepScope
public class OriginalProcessor implements ItemProcessor<OriginalOriginal, Original> {

	@Override
	public Original process(OriginalOriginal originalOriginal) throws Exception {
		Original original = new Original();

		original.setPrefecturesCode(originalOriginal.getPrefecturesCode());
		original.setPrefectures(originalOriginal.getPrefectures());
		original.setPrefecturesKana(originalOriginal.getPrefecturesKana());
		original.setPrefecturesRome(originalOriginal.getPrefecturesRome());
		original.setMunicipalitiesCode(originalOriginal.getMunicipalitiesCode());
		original.setMunicipalities(originalOriginal.getMunicipalities());
		original.setMunicipalitiesKana(originalOriginal.getMunicipalitiesKana());
		original.setMunicipalitiesRome(originalOriginal.getMunicipalitiesRome());
		original.setAddress(originalOriginal.getAddress());
		original.setAddressKana(originalOriginal.getAddressKana());
		original.setAddressRome(originalOriginal.getAddressRome());
		original.setLatitude(originalOriginal.getLatitude());
		original.setLongitude(originalOriginal.getLongitude());

		return original;
	}
}
