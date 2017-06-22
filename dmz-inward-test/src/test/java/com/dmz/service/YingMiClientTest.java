package com.dmz.service;

import com.alibaba.fastjson.JSON;
import com.dmz.service.iservice.IAccountManageService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by dmz on 2016/6/20.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring-basic.xml","classpath:Spring-service.xml","classpath:Spring-utils.xml"})
public class YingMiClientTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private IAccountManageService accountManage;

    @Test
    public void testPaymentProviders() {
        List<PaymentProvider> payments = accountManage.paymentProviders();
        System.out.println(JSON.toJSONString(payments.get(0).getName()));
    }

    @Test
    public void testGetAccountInfo() {
        AccountRequest request = new AccountRequest();
        request.setAccountId("5meksel7knd6");
        request.setBrokerUserId("dmzvsdmz");
        AccountInfo account = accountManage.getAccount(request);
        System.out.println(account.getRiskGrade().getValue());
        System.out.println(JSON.toJSONString(account));
        System.out.println(JSON.toJSONString(account.getAccountName()));
    }

    @Test
    public void testGetFundInfo() {
        FundInfoRequest request = new FundInfoRequest();
        request.setFundCode("270001");
        FundInfo fundInfo = accountManage.getFundInfo(request);
        System.out.println(JSON.toJSONString(fundInfo));
    }

    @Test
    public void testGetTradeDate() throws ParseException {

        FundTradeDateRequest request = new FundTradeDateRequest();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date dateTime = sdf.parse("2016-07-06T11:00:00");
		request.setDatetime(dateTime);
		request.setOffset(0);
		request.setFundCode("270001");
		FundTradeDateResponse response = accountManage.getTradeDate(request);
		System.out.println(response);
    }
    @Test
    public void testGetProfits() throws ParseException {
        GetProfitsRequest request = new GetProfitsRequest();
        request.setAccountId("5n51tejim27w");
        request.setBrokerUserId("2c918095554e92ae01554e92ae410000");
        request.setFundCode("270001");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse("2016-06-01");
        Date endDate = sdf.parse("2016-07-07");
        request.setBeginDate(startDate);
        request.setEndDate(endDate);

        ProfitsInfoResponse response = accountManage.getProfits(request);
        System.out.println(response);
    }
}
