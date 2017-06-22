package dmz.service.implement;

import com.alibaba.fastjson.JSON;
import com.dmz.service.*;
import com.dmz.service.iservice.IAccountManageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by dmz on 2016/6/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring-basic.xml","classpath:Spring-service.xml","classpath:Spring-utils.xml"})
public class YingMiClientTest {

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
        System.out.println(JSON.toJSONString(account));
        System.out.println(JSON.toJSONString(account.getAccountName()));
    }

    @Test(timeout = 5000) //5秒退出
    public void testGetFundInfo() {
        FundInfoRequest request = new FundInfoRequest();
        request.setFundCode("270001");
        while (true) {
            FundInfo fundInfo = accountManage.getFundInfo(request);
            System.out.println(JSON.toJSONString(fundInfo));
        }
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
