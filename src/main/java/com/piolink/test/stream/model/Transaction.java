package com.piolink.test.stream.model;

import java.util.Currency;

import lombok.Data;

/**
 * 
 * @author jini
 *
 */
public @Data class Transaction {

    private Integer price;
    
    private Currency currency;
    
    public Transaction() {}
    
    public Transaction(Currency currency, Integer price) {
        this.currency = currency;
        this.price = price;
    }

    public boolean isHeavyTransaction() {
        if (price > 1500) {
            return true;
        }
        return false;
    }
}
