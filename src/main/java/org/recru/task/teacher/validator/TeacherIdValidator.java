package org.recru.task.teacher.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class TeacherIdValidator implements ConstraintValidator<TeachersIdConstraint, List<Long>> {
	@Override
	public boolean isValid(List<Long> value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		return value.stream()
		            .distinct()
		            .count() == value.size();
	}
}
