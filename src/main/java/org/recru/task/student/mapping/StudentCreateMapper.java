package org.recru.task.student.mapping;

import org.mapstruct.*;
import org.recru.task.major.UniMajor;
import org.recru.task.student.StudentEntity;
import org.recru.task.student.dto.StudentCreate;
import org.recru.task.teacher.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;


@Mapper(componentModel = "spring")
public abstract class StudentCreateMapper {
	@Autowired
	protected TeacherService teacherService;

	@Mappings(
			{@Mapping(
					target = "personId", ignore = true
			), @Mapping(
					target = "teachers", ignore = true
			), @Mapping(
					target = "major", ignore = true
			)}
	)
	public abstract StudentEntity toEntity(StudentCreate studentCreate);

	@AfterMapping
	protected void afterMapping(StudentCreate studentCreate, @MappingTarget StudentEntity studentEntity) {
		studentEntity.setMajor(UniMajor.fromValue(studentCreate.getMajor()));
		studentEntity.setTeachers(new HashSet<>());
		if (Objects.nonNull(studentCreate.getTeachers())) {
			List<Long> teachersId = studentCreate.getTeachers()
			                                     .stream()
			                                     .filter(Objects::nonNull)
			                                     .toList();
			if (!teachersId.isEmpty()) {
				studentEntity.getTeachers()
				             .addAll(teacherService.getPersonsById(teachersId));
			}
		}
	}
}
