package com.example.Pokemondeckbuilder.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Pokemondeckbuilder.DeckRepository;
import com.example.Pokemondeckbuilder.entity.UserEntity;
import com.example.Pokemondeckbuilder.models.response.DeckRest;
import com.example.Pokemondeckbuilder.service.DeckService;
import com.example.Pokemondeckbuilder.service.UserService;
import com.example.Pokemondeckbuilder.shared.dto.DeckDto;
import com.example.Pokemondeckbuilder.shared.dto.UserDto;

@RestController
@RequestMapping("api/decks")
public class DeckController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	DeckService deckService;
	
	@PutMapping(path="/{deckId}")
	public DeckRest updateDeck(@PathVariable String deckId, @RequestBody DeckRest deck) {
		ModelMapper modelMapper = new ModelMapper();
		DeckDto deckDto = new DeckDto();
		BeanUtils.copyProperties(deck, deckDto);
		DeckDto savedDeck = deckService.updateDeck(deckDto);
		DeckRest returnValue = modelMapper.map(savedDeck, DeckRest.class);
		return returnValue;
	}
	
 }
