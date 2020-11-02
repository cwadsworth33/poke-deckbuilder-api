package com.example.Pokemondeckbuilder.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.UNAUTHORIZED, reason="Resource does not belong to user.")
public class UserUnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 3595791379648654984L;

}
