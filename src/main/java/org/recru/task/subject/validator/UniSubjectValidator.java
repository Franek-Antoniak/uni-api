package org.recru.task.subject.validator;

import org.recru.task.subject.UniSubject;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniSubjectValidator implements ConstraintValidator<UniSubjectConstraint, String> {
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		return UniSubject.isSupported(value);
	}
}
