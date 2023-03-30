package com.sunsum.thread;

import java.math.BigDecimal;

public class Account {

    private BigDecimal amount;

    public Account(Double amount) {
        this.amount = new BigDecimal(amount);
    }

    public Account() {

    }

    public void credit(Double amount){
        this.amount = this.amount.add(new BigDecimal(amount));
    }

    public Double getAmount(){
        return this.amount.doubleValue();
    }

    public synchronized void debit(Double amount){
        if(this.amount.doubleValue() <= amount){
            throw new RuntimeException("Not sufficient balance");
        }
        this.amount = this.amount.subtract(new BigDecimal(amount));
    }


}
