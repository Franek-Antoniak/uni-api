package org.recru.task.student.repository;

import org.recru.task.person.repository.PersonRepository;
import org.recru.task.student.StudentEntity;

import java.util.Optional;

public interface StudentRepository extends PersonRepository<StudentEntity> {
	StudentEntity save(StudentEntity personEntity);

	void deleteById(Long studentId);

	Optional<StudentEntity> findById(Long studentId);

	StudentEntity getReferenceById(Long studentId);
}
