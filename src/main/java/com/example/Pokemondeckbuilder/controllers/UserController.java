package com.example.Pokemondeckbuilder.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Pokemondeckbuilder.Exceptions.UserUnauthorizedException;
import com.example.Pokemondeckbuilder.models.request.DeckRequestModel;
import com.example.Pokemondeckbuilder.models.request.UserDetailsRequestModel;
import com.example.Pokemondeckbuilder.models.response.DeckRest;
import com.example.Pokemondeckbuilder.models.response.UserRest;
import com.example.Pokemondeckbuilder.service.DeckService;
import com.example.Pokemondeckbuilder.service.UserService;
import com.example.Pokemondeckbuilder.shared.dto.DeckDto;
import com.example.Pokemondeckbuilder.shared.dto.UserDto;

@RestController
@RequestMapping("api/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	DeckService deckService;
	
	private void userAuthorizedCheck(String userId, Principal principal) {
		UserDto loggedInUser = userService.getUser(principal.getName());
		if (!loggedInUser.getUserId().equals(userId)) throw new UserUnauthorizedException();
	}

	@GetMapping(path = "/{id}")
	public UserRest getUser(@PathVariable String id, Principal principal) {
		this.userAuthorizedCheck(id, principal);
		UserDto userDto = userService.getUserByUserId(id);
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(userDto, UserRest.class);
	}

	@PostMapping
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);

		UserDto createdUser = userService.createUser(userDto);
		UserRest userRest = modelMapper.map(createdUser, UserRest.class);

		return userRest;
	}
	
	@GetMapping(path="/{id}/decks")
	public List<DeckRest> getUserDecks(@PathVariable String id, Principal principal) {
		this.userAuthorizedCheck(id, principal);
		
		List<DeckRest> returnValue = new ArrayList<DeckRest>();
		List<DeckDto> deckDtos = deckService.getDecks(id);
		
		if (deckDtos != null && !deckDtos.isEmpty()) {
			java.lang.reflect.Type listType = new TypeToken<List<DeckRest>>() {}.getType();
			returnValue = new ModelMapper().map(deckDtos, listType);
		}
		
		return returnValue;
	}
	
	@PostMapping(path="/{id}/decks")
	public DeckRest addDeck(@PathVariable String id, @RequestBody DeckRequestModel deck, Principal principal) {
		this.userAuthorizedCheck(id, principal);
		
		ModelMapper modelMapper = new ModelMapper();
		UserDto user = userService.getUserByUserId(id);
		
		if (user == null) throw new UsernameNotFoundException(id);
		
		DeckDto newDeck = modelMapper.map(deck, DeckDto.class);
		newDeck.setUser(user);
				
		DeckDto createdDeck = deckService.createDeck(newDeck);
		DeckRest returnValue = modelMapper.map(createdDeck, DeckRest.class);
		return returnValue;
	}
	
	@DeleteMapping(path="/{userId}/decks/{deckId}")
	public List<DeckRest> deleteDeck(@PathVariable String userId, @PathVariable String deckId, Principal principal) {
		this.userAuthorizedCheck(userId, principal);
		
		deckService.deleteDeck(deckId);

		List<DeckRest> returnValue = new ArrayList<DeckRest>();
		List<DeckDto> deckDtos = deckService.getDecks(userId);
		
		if (deckDtos != null && !deckDtos.isEmpty()) {
			java.lang.reflect.Type listType = new TypeToken<List<DeckRest>>() {}.getType();
			returnValue = new ModelMapper().map(deckDtos, listType);
		}
		
		return returnValue;
	}
	
}
