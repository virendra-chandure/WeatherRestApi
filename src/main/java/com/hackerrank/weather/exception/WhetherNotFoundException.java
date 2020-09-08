package com.hackerrank.weather.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "record not found")
public class WhetherNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 7121741447496515017L;
}