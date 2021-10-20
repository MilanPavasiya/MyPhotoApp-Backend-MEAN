package com.myphotoapp.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidCreatedByValidator.class)
@Documented
public @interface ValidCreatedBy {

    String message() default "Not a valid created by: try with only lowercase letters and numbers ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
