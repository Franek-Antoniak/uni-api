package org.recru.task.person.service;

import org.recru.task.person.PersonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public abstract class PersonService<E extends PersonEntity> {
	public abstract E savePerson(E personEntity);

	public abstract void deletePerson(Long id);

	public abstract E getPerson(Long id);

	public abstract Page<E> getPersonsPage(Pageable pageable);

	public abstract Set<E> getPersonsById(List<Long> personsId);

	public abstract Set<E> getPersonsByFirstName(String firstName);

	public abstract Set<E> getPersonsByLastName(String lastName);

	public abstract Set<E> getPersonsByFullName(String firstName, String lastName);
}

