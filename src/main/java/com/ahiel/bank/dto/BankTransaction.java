package com.ahiel.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * @author Ahielg
 * @date 21/12/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankTransaction {
    private String name;
    private Integer amount;
    private Instant time;
}
