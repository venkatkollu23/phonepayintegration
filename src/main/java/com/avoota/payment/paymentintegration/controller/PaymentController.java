package com.avoota.payment.paymentintegration.controller;


import com.avoota.payment.paymentintegration.model.PaymentRequest;
import com.avoota.payment.paymentintegration.model.PaymentResponse;
import com.avoota.payment.paymentintegration.service.PaymentService;
import com.avoota.payment.paymentintegration.service.PhonePayIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    PhonePayIntegrationService phonePayIntegrationService;

    @PostMapping(value = "/pay", consumes = "application/json",
            produces = "application/json")
    public PaymentResponse pay(@RequestBody PaymentRequest paymentRequest) {
        String txId = paymentService.storeOrder(paymentRequest);
        return phonePayIntegrationService.initiatePayment(txId, paymentRequest);
    }

}
