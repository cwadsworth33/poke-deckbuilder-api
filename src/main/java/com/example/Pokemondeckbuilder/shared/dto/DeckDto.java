package com.example.Pokemondeckbuilder.shared.dto;

import java.io.Serializable;
import java.util.List;

public class DeckDto implements Serializable {

	private static final long serialVersionUID = -555034270803618542L;
	private long id;
	private String deckId;
	private boolean expandedLegal;
	private boolean standardLegal;
	private String name;
	private UserDto user;
	private List<String> cards;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDeckId() {
		return deckId;
	}

	public void setDeckId(String deckId) {
		this.deckId = deckId;
	}

	public boolean isExpandedLegal() {
		return expandedLegal;
	}

	public void setExpandedLegal(boolean expandedLegal) {
		this.expandedLegal = expandedLegal;
	}

	public boolean isStandardLegal() {
		return standardLegal;
	}

	public void setStandardLegal(boolean standardLegal) {
		this.standardLegal = standardLegal;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public List<String> getCards() {
		return cards;
	}

	public void setCards(List<String> cards) {
		this.cards = cards;
	}

}
