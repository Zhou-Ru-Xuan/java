package com.zhouruxuan.api;

import lombok.Getter;
import lombok.Setter;
import org.junit.Assert;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Set;


public class ValidationTest {
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void null_min_max_test() {
        ObjectTsst objectTsst = new ObjectTsst();
        Set<ConstraintViolation<ObjectTsst>> result = validator.validate(objectTsst);
        Assert.assertEquals(0, result.size());
    }

    @Getter
    @Setter
    class ObjectTsst {
        @Max(1)
        @Min(0)
        Integer null_min_max;
    }
}
