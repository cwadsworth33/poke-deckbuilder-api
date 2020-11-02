package com.example.Pokemondeckbuilder;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.example.Pokemondeckbuilder.entity.DeckEntity;
import com.example.Pokemondeckbuilder.entity.UserEntity;

public interface DeckRepository extends CrudRepository<DeckEntity, Long> {
	DeckEntity findByDeckId(String deckId);
	List<DeckEntity> findAllByUser(UserEntity user);
}
