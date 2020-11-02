package com.example.Pokemondeckbuilder.service;

import java.util.List;

import com.example.Pokemondeckbuilder.shared.dto.DeckDto;

public interface DeckService {
	DeckDto createDeck(DeckDto deck);
	List<DeckDto> getDecks(String userId);
	void deleteDeck(String deckId);
	DeckDto updateDeck(DeckDto deck);
}
