package org.recru.task.student.exception;

import org.recru.task.person.exception.PersonNotFoundException;

public class StudentNotFoundException extends PersonNotFoundException {
	public StudentNotFoundException(Long id) {
		super("Student with id: " + id + " not found");
	}
}
