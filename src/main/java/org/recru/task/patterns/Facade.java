package org.recru.task.patterns;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Component
@Service
@Documented
public @interface Facade {
	@AliasFor(annotation = Component.class) String value() default "";
}
