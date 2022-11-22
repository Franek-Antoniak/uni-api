package org.recru.task.teacher.repository;

import org.recru.task.person.repository.PersonRepository;
import org.recru.task.teacher.TeacherEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends PersonRepository<TeacherEntity> {
	TeacherEntity save(TeacherEntity personEntity);

	void deleteById(Long teacherId);

	Optional<TeacherEntity> findById(Long teacherId);
}

