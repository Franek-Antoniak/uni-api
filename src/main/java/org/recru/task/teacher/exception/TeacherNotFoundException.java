package org.recru.task.teacher.exception;

import org.recru.task.person.exception.PersonNotFoundException;

public class TeacherNotFoundException extends PersonNotFoundException {
	public TeacherNotFoundException(Long id) {
		super("Teacher with id: " + id + " not found");
	}
}
