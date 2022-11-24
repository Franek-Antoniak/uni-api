package org.recru.task.person.dto;

import lombok.Getter;
import lombok.Setter;
import org.recru.task.person.validator.EmailConstraint;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public abstract class PersonUpdate {
	@NotNull
	protected Long personId;
	@Size(min = 2, message = "First name must be at least 2 characters long")
	protected String firstName;
	@Size(min = 2, message = "Last name must be at least 2 characters long")
	protected String lastName;
	@EmailConstraint
	protected String email;
	@Min(
			value = 19, message = "You must be at least 19 years old"
	)
	protected Short age;
}
