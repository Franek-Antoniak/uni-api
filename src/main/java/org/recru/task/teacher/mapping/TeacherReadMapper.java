package org.recru.task.teacher.mapping;

import org.mapstruct.Mapper;
import org.recru.task.teacher.TeacherEntity;
import org.recru.task.teacher.dto.TeacherRead;

@Mapper(componentModel = "spring")
public abstract class TeacherReadMapper {

	public abstract TeacherRead toDTO(TeacherEntity teacherEntity);
}
