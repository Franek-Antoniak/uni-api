package org.recru.task.person.exception;

public class PersonNotFoundException extends RuntimeException {
	public PersonNotFoundException(Long id) {
		super("Person with id: " + id + " not found");
	}

	public PersonNotFoundException(String message) {
		super(message);
	}
}
