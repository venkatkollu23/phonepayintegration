package com.avoota.payment.paymentintegration.entity;


import jakarta.persistence.*;

@Entity
@Table(schema = "sonar",name = "Order")
public class OrderEntity {

    public OrderEntity(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "txid")
    private String txid;

    @Column(name="status")
    private String status;

    @Column(name = "amount")
    private  double amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
