package com.kafka.streams;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;

public class WordCountApp {
    public static void main(String[] args) {
        System.out.println("Hello World");

        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "word-count-app");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());


        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, String> worldCountInput = builder.stream("test");

        KTable<String, Long> wordCounts = worldCountInput.mapValues(value -> value.toLowerCase())
        .flatMapValues(value -> Arrays.asList(value.split("\\W+")))
        .selectKey((key, value) -> value)
        .groupByKey()
        .count();


        wordCounts.toStream().to("word-count-ouput");

        KafkaStreams streams = new KafkaStreams(builder.build(), properties);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(streams :: close));



    }
}
