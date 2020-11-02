package com.example.Pokemondeckbuilder.shared;

import java.security.SecureRandom;
import java.util.Random;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

@Component
public class Utils {
	private final Random RANDOM = new SecureRandom();
	private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private final int ITERATIONS = 10000;
	private final int KEY_LENGTH = 256;
	
	public String generateUserId(int length) {
		return generateRandomString(length);
	}
	
	private String generateRandomString(int length) {
		StringBuilder randomStringBldr = new StringBuilder(length);
		
		IntStream.range(0, length).forEach(
			i -> randomStringBldr.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())))
		);
		
		return new String(randomStringBldr);
	}
}
