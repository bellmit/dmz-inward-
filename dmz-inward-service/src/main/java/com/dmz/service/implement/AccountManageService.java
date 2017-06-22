package com.dmz.service.implement;

import com.dmz.service.*;
import com.dmz.service.iservice.IAccountManageService;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by dmz on 2016/6/21.
 */
@Service
public class AccountManageService implements IAccountManageService {

    @Resource(name="yingmiClient")
    private YingmiClient yingmiClient;

    @Override
    public  List<PaymentProvider> paymentProviders() {
        Type type = new TypeToken<ArrayList<PaymentProvider>>() {
        }.getType();
        List<PaymentProvider> paymentProviders = yingmiClient.get("/utils/getPaymentProviders", new HashMap<>(),type);
        return paymentProviders;
    }

    @Override
    public AccountInfo getAccount(AccountRequest request) {

        Map<String, String> params = new HashMap<>();
        params.put("brokerUserId", request.getBrokerUserId());
        params.put("accountId", request.getAccountId());

        AccountInfo account = yingmiClient.get("/account/getAccount", params, AccountInfo.class);

        return account;
    }

    @Override
    public FundInfo getFundInfo(FundInfoRequest request) {
        Map<String, String> params = new HashMap<>();
        params.put("fundCode", request.getFundCode());

        FundInfo fundInfo = yingmiClient.get("/product/getFundInfo", params, FundInfo.class);

        return fundInfo;
    }


    public ProfitsInfoResponse getProfits(GetProfitsRequest request) {

        ProfitsInfoResponse response = new ProfitsInfoResponse();
        Map<String, String> params = new HashMap<>();
        params.put("brokerUserId", request.getBrokerUserId());
        params.put("accountId", request.getAccountId());

        if (request.getShareType() != null) {
            params.put("shareType", request.getShareType().getFeeType());
        }

        if (request.getFundCode() != null) {
            params.put("fundCode", request.getFundCode());
        }

        if (request.getBeginDate() != null && request.getEndDate() != null) {
            params.put("beginDate", new SimpleDateFormat("yyyy-MM-dd").format(request.getBeginDate()));
            params.put("endDate", new SimpleDateFormat("yyyy-MM-dd").format(request.getEndDate()));
        }

        if (request.getPaymentMethodId() != null) {
            params.put("paymentMethodId", request.getPaymentMethodId());
        }
        Type type = new TypeToken<Map<String,List<FundProfitInfo>>>() {
        }.getType();
        Map<String, List<FundProfitInfo>> resp = yingmiClient.get("/trade/getProfits", params, type);
        response.setFundProfits(resp);
        return response;
    }

    @Override
    public FundTradeDateResponse getTradeDate(FundTradeDateRequest request) {

        Map<String, String> params = new HashMap<>();
        if (request.getDatetime() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            params.put("datetime", sdf.format(request.getDatetime()));
        }
        if (request.getFundCode() != null) {
            params.put("fundCode", request.getFundCode());
        }
        if (request.getOffset() != null) {
            params.put("offset", request.getOffset().toString());
        }
        FundTradeDateResponse response = yingmiClient.get("/utils/getTradeDate", params, FundTradeDateResponse.class);

        return response;
    }
}
