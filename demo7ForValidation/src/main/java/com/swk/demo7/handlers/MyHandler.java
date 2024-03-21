package com.swk.demo7.handlers;

import com.swk.demo7.exceptions.MyException;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MyHandler {

    private static final Validator _VALIDATOR = Validation.byDefaultProvider()
            .configure().messageInterpolator(new ResourceBundleMessageInterpolator(
                    new PlatformResourceBundleLocator("message")
            )).buildValidatorFactory().getValidator();


    public static void assertCheckpoint(Object obj, Method method, Object[] args) {
        Set<ConstraintViolation<Object>> constraintViolations = _VALIDATOR.forExecutables().validateParameters(obj, method, args);
        List<String> result = constraintViolations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        if (!result.isEmpty()){
            throw new MyException("error!!!");
        }
    }

    public static void assertCheckpoint1(Object obj) {
        Set<ConstraintViolation<Object>> validate = _VALIDATOR.validate(obj);
        List<String> result = validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        if (!result.isEmpty()){
            throw new MyException("error!!!");
        }
    }
}
