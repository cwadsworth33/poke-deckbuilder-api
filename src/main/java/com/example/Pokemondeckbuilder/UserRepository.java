package com.example.Pokemondeckbuilder;

import org.springframework.data.repository.CrudRepository;

import com.example.Pokemondeckbuilder.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);
}
