package org.recru.task.person.facade;

import org.recru.task.person.PersonEntity;
import org.recru.task.person.dto.PersonCreate;
import org.recru.task.person.dto.PersonRead;
import org.recru.task.person.dto.PersonUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.Set;

public abstract class PersonServiceFacade<C extends PersonCreate, U extends PersonUpdate, R extends PersonRead> {
	public abstract PersonEntity.ID createPerson(@Valid C personEntity);

	public abstract void updatePerson(@Valid U personUpdate);

	public abstract void deletePerson(Long id);

	public abstract Page<R> getPersonsPage(Pageable pageable);

	public abstract Set<R> getPersonsByName(String firstName, String lastName);
}
