package org.recru.task.teacher.mapping;

import org.mapstruct.*;
import org.recru.task.student.StudentEntity;
import org.recru.task.student.service.StudentService;
import org.recru.task.subject.UniSubject;
import org.recru.task.teacher.TeacherEntity;
import org.recru.task.teacher.dto.TeacherCreate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Mapper(componentModel = "spring")
public abstract class TeacherCreateMapper {
	@Autowired
	protected StudentService studentService;


	@Mappings(
			{@Mapping(
					target = "personId", ignore = true
			), @Mapping(
					target = "students", ignore = true, defaultExpression = "java(new java.util.ArrayList<>())"
			), @Mapping(
					target = "subject", ignore = true
			)}
	)
	public abstract TeacherEntity toEntity(TeacherCreate teacherCreate);

	@AfterMapping
	protected void afterMapping(TeacherCreate teacherCreate, @MappingTarget TeacherEntity teacherEntity) {
		teacherEntity.setSubject(UniSubject.fromValue(teacherCreate.getSubject()));
		List<Long> studentsId = teacherCreate.getStudents()
		                                     .stream()
		                                     .filter(Objects::nonNull)
		                                     .toList();
		Set<StudentEntity> students = new HashSet<>();
		if (!studentsId.isEmpty()) {
			students = studentService.getPersonsReferences(studentsId);
		}
		teacherEntity.setStudents(students);
	}
}
