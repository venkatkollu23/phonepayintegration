package com.avoota.payment.paymentintegration.service;

import com.avoota.payment.paymentintegration.model.PaymentRequest;
import com.avoota.payment.paymentintegration.model.PaymentResponse;
import com.phonepe.sdk.pg.Env;
import com.phonepe.sdk.pg.common.http.PhonePeResponse;
import com.phonepe.sdk.pg.payments.v1.PhonePePaymentClient;
import com.phonepe.sdk.pg.payments.v1.models.request.PgPayRequest;
import com.phonepe.sdk.pg.payments.v1.models.response.PayPageInstrumentResponse;
import com.phonepe.sdk.pg.payments.v1.models.response.PgPayResponse;
import org.springframework.stereotype.Service;

@Service
public class PhonePayIntegrationService {

    String merchantId = "PGTESTPAYUAT86";
    String saltKey = "96434309-7796-489d-8924-ab56988a6076";
    Integer saltIndex = 1;
    Env env = Env.SIMULATOR;
    String redirectMode="REDIRECT";
    public PaymentResponse initiatePayment(String txId, PaymentRequest paymentRequest){
        boolean shouldPublishEvents = false;
        PhonePePaymentClient phonepeClient = new PhonePePaymentClient(
                merchantId, saltKey, saltIndex, env, shouldPublishEvents);

        PgPayRequest pgPayRequest=PgPayRequest.PayPagePayRequestBuilder()
                .amount(paymentRequest.getAmount().longValue())
                .merchantId(merchantId)
                .merchantTransactionId(txId)
                .merchantUserId("merchantUserId")
                .redirectUrl("http://localhost:4200/ordersummary")
                .redirectMode(redirectMode)
                .build();
        PhonePeResponse<PgPayResponse> payResponse=phonepeClient.pay(pgPayRequest);
        PayPageInstrumentResponse payPageInstrumentResponse=(PayPageInstrumentResponse)payResponse.getData().getInstrumentResponse();
        String url=payPageInstrumentResponse.getRedirectInfo().getUrl();
        PaymentResponse response = new PaymentResponse();
        response.setUrl(url);
        return  response;
    }
}