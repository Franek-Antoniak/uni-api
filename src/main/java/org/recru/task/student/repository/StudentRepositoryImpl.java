package org.recru.task.student.repository;

import org.recru.task.student.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface StudentRepositoryImpl extends StudentRepository, JpaRepository<StudentEntity, Long> {
}

