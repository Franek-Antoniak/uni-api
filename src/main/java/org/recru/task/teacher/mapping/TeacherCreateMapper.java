package org.recru.task.teacher.mapping;

import org.mapstruct.*;
import org.recru.task.student.service.StudentService;
import org.recru.task.subject.UniSubject;
import org.recru.task.teacher.TeacherEntity;
import org.recru.task.teacher.dto.TeacherCreate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;


@Mapper(componentModel = "spring")
public abstract class TeacherCreateMapper {
	@Autowired
	protected StudentService studentService;


	@Mappings(
			{@Mapping(
					target = "personId", ignore = true
			), @Mapping(
					target = "students", ignore = true
			), @Mapping(
					target = "subject", ignore = true
			)}
	)
	public abstract TeacherEntity toEntity(TeacherCreate teacherCreate);

	@AfterMapping
	protected void afterMapping(TeacherCreate teacherCreate, @MappingTarget TeacherEntity teacherEntity) {
		teacherEntity.setSubject(UniSubject.fromValue(teacherCreate.getSubject()));
		teacherEntity.setStudents(new HashSet<>());
		if (Objects.nonNull(teacherCreate.getStudents())) {
			List<Long> teachersId = teacherCreate.getStudents()
			                                     .stream()
			                                     .filter(Objects::nonNull)
			                                     .toList();
			if (!teachersId.isEmpty()) {
				teacherEntity.getStudents()
				             .addAll(studentService.getPersonsById(teachersId));
			}
		}
	}
}
