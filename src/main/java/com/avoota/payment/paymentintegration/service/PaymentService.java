package com.avoota.payment.paymentintegration.service;

import com.avoota.payment.paymentintegration.entity.OrderEntity;
import com.avoota.payment.paymentintegration.model.PaymentRequest;
import com.avoota.payment.paymentintegration.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    OrderRepository orderRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public String storeOrder(PaymentRequest paymentRequest){
        OrderEntity order = new OrderEntity();
        order.setAmount(paymentRequest.getAmount());
        String merchantTransactionId = UUID.randomUUID().toString().substring(0,34);
        order.setTxid(merchantTransactionId);
        order.setStatus("INPROGRESS");

  //      orderRepository.save(order);

        return order.getTxid();
    }
}
