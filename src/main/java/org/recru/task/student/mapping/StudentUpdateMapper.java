package org.recru.task.student.mapping;

import org.mapstruct.*;
import org.recru.task.major.UniMajor;
import org.recru.task.student.StudentEntity;
import org.recru.task.student.dto.StudentCreate;
import org.recru.task.student.dto.StudentUpdate;
import org.recru.task.teacher.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class StudentUpdateMapper {
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
	public abstract void updateEntity(StudentUpdate studentUpdate, @MappingTarget StudentEntity studentEntity);

	@AfterMapping
	protected void afterMapping(StudentCreate studentUpdate, @MappingTarget StudentEntity studentEntity) {
		if (Objects.nonNull(studentUpdate.getMajor())) {
			UniMajor major = UniMajor.fromValue(studentUpdate.getMajor());
			studentEntity.setMajor(major);
		}
		if (Objects.nonNull(studentUpdate.getTeachers())) {
			List<Long> teachersId = studentUpdate.getTeachers()
			                                     .stream()
			                                     .filter(Objects::nonNull)
			                                     .toList();
			studentEntity.setTeachers(teacherService.getPersonsReferences(teachersId));
		}
	}
}

