package com.example.Pokemondeckbuilder.models.request;

import java.util.List;

public class DeckRequestModel {
	private boolean expandedLegal;
	private boolean standardLegal;
	private String name;
	private List<String> cards;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getCards() {
		return cards;
	}

	public void setCards(List<String> cards) {
		this.cards = cards;
	}
}
