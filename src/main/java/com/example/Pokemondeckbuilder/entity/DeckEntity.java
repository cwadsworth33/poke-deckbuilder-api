package com.example.Pokemondeckbuilder.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.Pokemondeckbuilder.shared.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "decks")
public class DeckEntity implements Serializable {

	private static final long serialVersionUID = -6123054861410194376L;
	@Id
	@GeneratedValue
	private long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
	@JoinColumn(name = "users_id")
	private UserEntity user;

	@Column(nullable = false)
	private String deckId;

	@Column
	private Boolean standardLegal;

	@Column
	private String name;

	@Column
	private Boolean expandedLegal;

	public List<String> getCards() {
		return cards;
	}

	public void setCards(List<String> cards) {
		this.cards = cards;
	}

	@ElementCollection
	@CollectionTable(name = "deck_card_ids", joinColumns = @JoinColumn(name = "deck_id"))
	@Column()
	private List<String> cards;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeckId() {
		return deckId;
	}

	public void setDeckId(String deckId) {
		this.deckId = deckId;
	}

	public Boolean getStandardLegal() {
		return standardLegal;
	}

	public void setStandardLegal(Boolean standardLegal) {
		this.standardLegal = standardLegal;
	}

	public Boolean getExpandedLegal() {
		return expandedLegal;
	}

	public void setExpandedLegal(Boolean expandedLegal) {
		this.expandedLegal = expandedLegal;
	}
}
