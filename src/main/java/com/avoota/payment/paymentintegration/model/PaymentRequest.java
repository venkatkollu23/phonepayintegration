package com.avoota.payment.paymentintegration.model;

public class PaymentRequest {

    private Double amount;
    private String rollId;


    public PaymentRequest(){

    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getRollId() {
        return rollId;
    }

    public void setRollId(String rollId) {
        this.rollId = rollId;
    }
}
