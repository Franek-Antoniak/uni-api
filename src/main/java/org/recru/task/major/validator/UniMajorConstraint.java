package org.recru.task.major.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniMajorValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniMajorConstraint {
	String message() default "Invalid major or major is not supported";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}