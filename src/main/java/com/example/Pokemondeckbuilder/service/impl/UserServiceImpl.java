package com.example.Pokemondeckbuilder.service.impl;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Pokemondeckbuilder.UserRepository;
import com.example.Pokemondeckbuilder.entity.UserEntity;
import com.example.Pokemondeckbuilder.service.UserService;
import com.example.Pokemondeckbuilder.shared.Utils;
import com.example.Pokemondeckbuilder.shared.dto.DeckDto;
import com.example.Pokemondeckbuilder.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	Utils utils;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto user) {

		ModelMapper modelMapper = new ModelMapper();

		UserEntity existingUser = userRepository.findByEmail(user.getEmail());
		if (existingUser != null)
			throw new RuntimeException("Record already exists.");

		if (user.getDecks() != null) {
			for (int i = 0; i < user.getDecks().size(); i++) {
				DeckDto deck = user.getDecks().get(i);
				deck.setUser(user);
				deck.setDeckId(utils.generateUserId(30));
				user.getDecks().set(i, deck);
			}
		}

		UserEntity userEntity = modelMapper.map(user, UserEntity.class);
		String publicUserId = utils.generateUserId(30);

		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setUserId(publicUserId);
		userEntity.setEmailVerificationStatus(false);

		UserEntity storedUserDetails = userRepository.save(userEntity);

		UserDto returnValue = modelMapper.map(storedUserDetails, UserDto.class);

		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity existingUserEntity = userRepository.findByEmail(email);
		if (existingUserEntity == null)
			throw new UsernameNotFoundException(email);

		return new User(existingUserEntity.getEmail(), existingUserEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		ModelMapper modelMapper = new ModelMapper();
		UserEntity storedUser = userRepository.findByUserId(userId);

		if (storedUser == null)
			throw new UsernameNotFoundException(userId);

		return modelMapper.map(storedUser, UserDto.class);
	}
	
	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}

}
