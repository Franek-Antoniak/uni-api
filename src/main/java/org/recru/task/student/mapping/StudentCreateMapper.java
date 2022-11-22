package org.recru.task.student.mapping;

import org.mapstruct.*;
import org.recru.task.major.UniMajor;
import org.recru.task.student.StudentEntity;
import org.recru.task.student.dto.StudentCreate;
import org.recru.task.teacher.TeacherEntity;
import org.recru.task.teacher.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Mapper(componentModel = "spring")
public abstract class StudentCreateMapper {
	@Autowired
	protected TeacherService teacherService;

	@Mappings(
			{@Mapping(
					target = "personId", ignore = true
			), @Mapping(
					target = "teachers", ignore = true, defaultExpression = "java(new java.util.ArrayList<>())"
			), @Mapping(
					target = "major", ignore = true
			)}
	)
	public abstract StudentEntity toEntity(StudentCreate studentCreate);

	@AfterMapping
	protected void afterMapping(StudentCreate studentCreate, @MappingTarget StudentEntity studentEntity) {
		studentEntity.setMajor(UniMajor.valueOf(studentCreate.getMajor()
		                                                     .toUpperCase()
		                                                     .replace(" ", "_")));
		List<Long> teachersId = studentCreate.getTeachers()
		                                     .stream()
		                                     .filter(Objects::nonNull)
		                                     .toList();
		Set<TeacherEntity> teachers = new HashSet<>();
		if (!teachersId.isEmpty()) {
			teachers = teacherService.getPersonsReferences(teachersId);
		}
		studentEntity.setTeachers(teachers);
	}

}
