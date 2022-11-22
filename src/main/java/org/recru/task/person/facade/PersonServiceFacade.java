package org.recru.task.person.facade;

import org.recru.task.person.PersonEntity;
import org.recru.task.person.dto.PersonCreate;
import org.recru.task.person.dto.PersonRead;
import org.recru.task.person.dto.PersonUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public abstract class PersonServiceFacade<E extends PersonEntity, C extends PersonCreate, U extends PersonUpdate,
		R extends PersonRead> {
	public abstract PersonEntity.ID createPerson(C personEntity);

	public abstract void updatePerson(U personUpdate);

	public abstract void deletePerson(Long id);

	public abstract Set<E> getPersonsReferences(List<Long> students);

	public abstract Page<R> getPersonsPage(Pageable pageable);

	public abstract Set<R> getPersonsByFirstName(String firstName);

	public abstract Set<R> getPersonsByLastName(String lastName);

	public abstract Set<R> getPersonsByFullName(String firstName, String lastName);

}
