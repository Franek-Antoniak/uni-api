package org.recru.task.major.validator;

import org.recru.task.major.UniMajor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniMajorValidator implements ConstraintValidator<UniMajorConstraint, String> {
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		return UniMajor.isSupported(value);
	}
}
