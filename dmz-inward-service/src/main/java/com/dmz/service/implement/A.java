package com.dmz.service.implement;

import com.dmz.basic.model.Login;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * @author dmz
 * @date 2017/7/21
 */
@Validated
public interface A {

    void a();

    void b(@Valid Login login);

    void c(@NotBlank String name);
}
