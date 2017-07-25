package com.dmz.web.controller;

import com.dmz.basic.model.Login;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * @author dmz
 * @date 2017/7/21
 */
@Validated
public interface IValidatorService {

    void validateModel(@Valid Login login);

    void validateDirect(@NotBlank String name);

}
