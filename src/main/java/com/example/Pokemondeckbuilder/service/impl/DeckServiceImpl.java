package com.example.Pokemondeckbuilder.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Pokemondeckbuilder.DeckRepository;
import com.example.Pokemondeckbuilder.UserRepository;
import com.example.Pokemondeckbuilder.entity.DeckEntity;
import com.example.Pokemondeckbuilder.entity.UserEntity;
import com.example.Pokemondeckbuilder.service.DeckService;
import com.example.Pokemondeckbuilder.shared.Utils;
import com.example.Pokemondeckbuilder.shared.dto.DeckDto;

@Service
public class DeckServiceImpl implements DeckService {
	
	@Autowired
	DeckRepository deckRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;
	
	@Override
	public DeckDto createDeck(DeckDto deck) {
		ModelMapper modelMapper = new ModelMapper();
		DeckEntity existingDeck = deckRepository.findByDeckId(deck.getDeckId());
		if (existingDeck != null) throw new RuntimeException("Record already exists.");
		
		DeckEntity deckEntity = modelMapper.map(deck, DeckEntity.class);
		
		String publicDeckId = utils.generateUserId(30);
		deckEntity.setDeckId(publicDeckId);
		
		DeckEntity storedDeckDetails = deckRepository.save(deckEntity);
		
		DeckDto returnValue = modelMapper.map(storedDeckDetails, DeckDto.class);
		
		return returnValue;
	}

	@Override
	public List<DeckDto> getDecks(String userId) {
		List<DeckDto> returnValue = new ArrayList<DeckDto>();
		UserEntity user = userRepository.findByUserId(userId);
		
		if (user == null) return returnValue;
		
		Iterable<DeckEntity> storedDecks = deckRepository.findAllByUser(user);
		ModelMapper modelMapper = new ModelMapper();
		for(DeckEntity deckEntity:storedDecks) {
			returnValue.add(modelMapper.map(deckEntity, DeckDto.class));
		}
		
		return returnValue;
		
	}

	@Override
	public void deleteDeck(String deckId) {
		DeckEntity deckEntity = deckRepository.findByDeckId(deckId);
		if (deckEntity == null) 
			throw new RuntimeException("No record found");
		
		deckRepository.delete(deckEntity);
	}

	@Override
	public DeckDto updateDeck(DeckDto deck) {
		DeckEntity deckEntity = deckRepository.findByDeckId(deck.getDeckId());
		if (deckEntity == null)
			throw new RuntimeException("No record found");
		ModelMapper modelMapper = new ModelMapper();
		deckEntity.setCards(deck.getCards());
		deckEntity.setExpandedLegal(deck.isExpandedLegal());
		deckEntity.setStandardLegal(deck.isStandardLegal());
		deckEntity.setName(deck.getName());
		DeckEntity savedDeck = deckRepository.save(deckEntity);
		DeckDto returnValue = modelMapper.map(savedDeck, DeckDto.class);
		return returnValue;
	}
	
}
