package com.example.auctionapp.util.annotation;

import com.example.auctionapp.util.LuhnValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = LuhnValidator.class)
@Target({FIELD})
@Retention(RUNTIME)
public @interface LuhnCheck {
    String message() default "Invalid credit card number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
