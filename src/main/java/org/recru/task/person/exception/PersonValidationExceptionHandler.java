package org.recru.task.person.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.HashMap;

@ControllerAdvice
public class PersonValidationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
		HashMap<String, String> errors = Arrays.stream(e.getMessage()
		                                                .split(","))
		                                       .map(s -> s.split(":"))
		                                       .collect(HashMap::new, (m, a) -> m.put(a[0], a[1]), HashMap::putAll);
		StringBuilder sb = new StringBuilder();
		errors.forEach((k, v) -> sb.append(k.substring(k.lastIndexOf(".") + 1))
		                           .append(":")
		                           .append(v)
		                           .append("\n"));
		return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(PersonNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handlePersonNotFoundException(PersonNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
}
