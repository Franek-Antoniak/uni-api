package org.recru.task.person.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public abstract class PersonUpdate {
	@NotNull
	protected Long personId;
	@Min(value = 2, message = "First name must be at least 2 characters long")
	protected String firstName;
	@Min(value = 2, message = "Last name must be at least 2 characters long")
	protected String lastName;
	@Email(
			message = "Email should be valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"
	)
	protected String email;
	@Min(
			value = 19, message = "You must be at least 19 years old"
	)
	protected short age;
}
