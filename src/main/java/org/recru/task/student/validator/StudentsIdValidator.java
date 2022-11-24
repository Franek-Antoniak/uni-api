package org.recru.task.student.validator;

import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@RequiredArgsConstructor
public class StudentsIdValidator implements ConstraintValidator<StudentsIdConstraint, List<Long>> {
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
