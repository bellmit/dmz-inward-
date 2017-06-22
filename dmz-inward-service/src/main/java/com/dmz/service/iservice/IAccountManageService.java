package com.dmz.service.iservice;

import com.dmz.service.*;

import java.util.List;

/**
 * Created by dmz on 2016/6/21.
 */
public interface IAccountManageService {
   List<PaymentProvider> paymentProviders();

   AccountInfo getAccount(AccountRequest request);

   FundInfo getFundInfo(FundInfoRequest request);

   ProfitsInfoResponse getProfits(GetProfitsRequest request);

   FundTradeDateResponse getTradeDate(FundTradeDateRequest request);
}
