package com.example.Pokemondeckbuilder.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.Pokemondeckbuilder.shared.dto.UserDto;

public interface UserService extends UserDetailsService {
	UserDto createUser(UserDto user);
	UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException;
	UserDto getUserByUserId(String userId);
	UserDto getUser(String email);
}
