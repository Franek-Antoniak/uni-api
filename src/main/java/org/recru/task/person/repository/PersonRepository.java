package org.recru.task.person.repository;

import org.recru.task.person.PersonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface PersonRepository<T extends PersonEntity> {
	Set<T> findAllByFirstName(String firstName);

	Set<T> findAllByLastName(String lastName);

	Set<T> findAllByFirstNameAndLastName(String firstName, String lastName);

	Set<T> getAllByPersonIdIn(List<Long> personIds);

	Page<T> findAll(Pageable pageable);
}

