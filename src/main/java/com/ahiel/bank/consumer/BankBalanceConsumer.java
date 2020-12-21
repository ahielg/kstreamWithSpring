package com.ahiel.bank.consumer;

import com.ahiel.bank.dto.BankBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author Ahielg
 * @date 21/12/2020
 */

@Component
@Slf4j
public class BankBalanceConsumer {

    @KafkaListener(topics = "${kafka.topic.bank-balance}")
    public void consume(BankBalance balance) {
        log.debug("Consumed :: {}", balance.toString());
    }

}