package com.avoota.payment.paymentintegration.controller;


import com.avoota.payment.paymentintegration.model.PaymentRequest;
import com.avoota.payment.paymentintegration.model.PaymentResponse;
import com.avoota.payment.paymentintegration.model.PaymentStatus;
import com.avoota.payment.paymentintegration.service.PaymentService;
import com.avoota.payment.paymentintegration.service.PhonePayIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/checkStatus/{txid}")
    public PaymentStatus checkStatus(@PathVariable("txid") String txid) {

        return phonePayIntegrationService.checkStatus(txid);
    }

}
