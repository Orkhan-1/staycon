package com.staycon.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchValidator.class)
@Documented
public @interface PasswordMatch {

    String message() default "{error.password.mismatch}";
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload () default {};
}
