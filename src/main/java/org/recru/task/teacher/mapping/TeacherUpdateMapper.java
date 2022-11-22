package org.recru.task.teacher.mapping;

import org.mapstruct.*;
import org.recru.task.student.service.StudentService;
import org.recru.task.subject.UniSubject;
import org.recru.task.teacher.TeacherEntity;
import org.recru.task.teacher.dto.TeacherUpdate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class TeacherUpdateMapper {
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
	public abstract void updateEntity(TeacherUpdate teacherUpdate, @MappingTarget TeacherEntity teacherEntity);

	@AfterMapping
	protected void afterMapping(TeacherUpdate teacherUpdate, @MappingTarget TeacherEntity teacherEntity) {
		if (Objects.nonNull(teacherUpdate.getSubject())) {
			UniSubject subject = UniSubject.fromValue(teacherUpdate.getSubject());
			teacherEntity.setSubject(subject);
		}
		if (Objects.nonNull(teacherUpdate.getStudentsId())) {
			List<Long> studentsId = teacherUpdate.getStudentsId()
			                                     .stream()
			                                     .filter(Objects::nonNull)
			                                     .toList();
			teacherEntity.setStudents(studentService.getPersonsReferences(studentsId));
		}
	}
}