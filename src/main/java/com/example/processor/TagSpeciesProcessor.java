package com.example.processor;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.domain.TagSpecies;
import com.example.domain.TagSpeciesOriginal;

@Component
@StepScope
public class TagSpeciesProcessor implements ItemProcessor<TagSpeciesOriginal, TagSpecies> {

	@Override
	public TagSpecies process(TagSpeciesOriginal tagSpeciesOriginal) throws Exception {
		TagSpecies tagSpecies = new TagSpecies();

		tagSpecies.setName(tagSpeciesOriginal.getName());

		return tagSpecies;
	}
}
