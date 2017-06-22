package dmztest;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author dmz
 * @date 2017/5/11
 */
class Entity {
    //    @Max(value=3)//最大值为3
    @NotBlank
    private int age;
    //    @Length(max=1) //字符串长度最大为1,hibernate 扩展的
    @NotBlank(message = "{msgs}")
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class ValidationTest {
    public static void main(String[] args) {

//        Validator validatorT = Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();
        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setProviderClass(HibernateValidator.class);
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:messages");
        messageSource.setUseCodeAsDefaultMessage(false);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(60);
        validatorFactoryBean.setValidationMessageSource(messageSource);
        Validator validator = validatorFactoryBean.getValidator();


        Entity entity = new Entity();
        entity.setAge(12);

        Set<ConstraintViolation<Entity>> constraintViolations = validator.validate(entity);
        for (ConstraintViolation<Entity> constraintViolation : constraintViolations) {
            System.out.println("对象属性:" + constraintViolation.getPropertyPath());
            System.out.println("国际化key:" + constraintViolation.getMessageTemplate());
            System.out.println("错误信息:" + constraintViolation.getMessage());
        }
    }
}
