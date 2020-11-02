package com.example.Pokemondeckbuilder.models.request;

import java.util.List;

public class UserDetailsRequestModel {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private List<DeckRequestModel> decks;

	public List<DeckRequestModel> getDecks() {
		return decks;
	}

	public void setDecks(List<DeckRequestModel> decks) {
		this.decks = decks;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
