package org.recru.task.student.mapping;

import org.mapstruct.Mapper;
import org.recru.task.student.StudentEntity;
import org.recru.task.student.dto.StudentRead;

@Mapper(componentModel = "spring")
public abstract class StudentReadMapper {

	public abstract StudentRead toDTO(StudentEntity studentEntity);
}
