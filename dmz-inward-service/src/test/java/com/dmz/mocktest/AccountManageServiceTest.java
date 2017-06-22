package com.dmz.mocktest;

import com.dmz.AutoRunnerJunit;
import com.dmz.mockdata.MockYingmiClientData;
import com.dmz.service.PaymentProvider;
import com.dmz.service.YingmiClient;
import com.dmz.service.iservice.IAccountManageService;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dmz
 * @date 2017/2/24
 */
@ContextConfiguration(locations = {"classpath:spring-service-test.xml"})
/*
* 执行监听,用于执行测试类之前做一些预处理,DependencyInjectionTestExecutionListener TransactionalTestExecutionListener 不指定的话也是默认
*
*/
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class})
public class AccountManageServiceTest extends AutoRunnerJunit {

    @Resource
    YingmiClient yingmiClient;

    @Resource
    IAccountManageService accountManageService;

    @Test
    public void paymentProviders() {
        MockYingmiClientData.paymentProviders(yingmiClient);
        List<PaymentProvider> providers = accountManageService.paymentProviders();
        for (PaymentProvider paymentProvider : providers) {
            System.out.println(paymentProvider.getName());
        }
    }
}
