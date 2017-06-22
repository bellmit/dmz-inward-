package com.dmz.service;

import com.dmz.service.annotation.Forbidden;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author dmz
 * @date 2017/5/12
 */
public class ForbiddenValidator implements ConstraintValidator<Forbidden, String> {

    private String[] forbiddenWords;

    @Override
    public void initialize(Forbidden forbidden) {
        forbiddenWords = forbidden.values();
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isEmpty(s)) {
            return true;
        }

        for (String word : forbiddenWords) {
            if (s.equals(word)) {
                return false;//验证失败
            }
        }
        return true;
    }
}
