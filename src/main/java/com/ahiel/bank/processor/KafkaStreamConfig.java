package com.ahiel.bank.processor;

import com.ahiel.bank.dto.BankBalance;
import com.ahiel.bank.dto.BankTransaction;
import com.ahiel.bank.topic.KafkaTopicConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.stereotype.Component;

/**
 * @author Ahielg
 * @date 21/12/2020
 */


@Component
@EnableKafka
@EnableKafkaStreams
@Slf4j
public class KafkaStreamConfig {

    //  @Value("${kafka.topic.bank-transaction}")
//  private String bankTransactionTopic;
//
//  @Value("${kafka.topic.bank-balance}")
//  private String bankBalanceTopic;


    private KafkaTopicConfig kafkaTopics;

    KafkaStreamConfig(KafkaTopicConfig kafkaTopics) {
        this.kafkaTopics = kafkaTopics;
    }

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Bean
    public KStream<String, BankTransaction> transactionUpdateStream(StreamsBuilder builder) {
        KStream<String, BankTransaction> stream = builder.stream(kafkaTopics.getBankTransaction());
        stream.groupByKey()
                .aggregate(
                        BankBalance::new,
                        (key, transaction, oldBalance) -> new BankBalance(transaction, oldBalance),
                        Materialized.as("bank-balance-aggregate")
                )
                .toStream()
                .to(kafkaTopics.getBankBalance());

        return stream;
    }

}