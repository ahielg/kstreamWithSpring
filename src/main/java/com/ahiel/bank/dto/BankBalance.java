package com.ahiel.bank.dto;

import lombok.Data;

import java.time.Instant;

/**
 * @author Ahielg
 * @date 21/12/2020
 */

@Data
public class BankBalance {
    private Integer count;
    private Integer balance;
    private Instant time;

    public BankBalance() {
        this.count = 0;
        this.balance = 0;
        this.time = Instant.ofEpochMilli(0L);
    }

    public BankBalance(BankTransaction transaction, BankBalance oldBalance) {
        this.count = oldBalance.count + 1;
        this.balance = oldBalance.balance + transaction.getAmount();
        this.time = (oldBalance.time.isBefore(transaction.getTime())) ? transaction.getTime() : oldBalance.time;
    }
}