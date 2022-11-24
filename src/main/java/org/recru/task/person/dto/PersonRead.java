package org.recru.task.person.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PersonRead {
	protected Long personId;
	protected String firstName;
	protected String lastName;
	protected String email;
}
