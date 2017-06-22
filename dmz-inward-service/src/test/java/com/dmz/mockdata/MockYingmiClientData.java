package com.dmz.mockdata;

import com.dmz.service.PaymentProvider;
import com.dmz.service.YingmiClient;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dmz
 * @date 2017/2/24
 */
public class MockYingmiClientData {

    public static void paymentProviders(YingmiClient yingmiClient) {
        List<PaymentProvider> providers = new ArrayList<>();
        PaymentProvider paymentProvider = new PaymentProvider();
        paymentProvider.setName("DMZ");
        providers.add(paymentProvider);
        Mockito.when(yingmiClient.get(Mockito.anyString(), Mockito.anyMap(), Mockito.any())).thenReturn(providers);

    }
}
