package org.recru.task.teacher.repository;

import org.recru.task.teacher.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepositoryImpl extends TeacherRepository, JpaRepository<TeacherEntity, Long> {
}
