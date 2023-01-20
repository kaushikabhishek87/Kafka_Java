package com.kafka.streams;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;



public class BankingApplicationStream{

    public static void main(String[] args) {
        
        // Stream Properties
        // Stream Builder
        // Stream/table builder
        // Aggregating
        // Create Topology
        // Start Stream
        // Add shutdown hook
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG,"banking-app");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        properties.put(StreamsConfig.PROCESSING_GUARANTEE_CONFIG, StreamsConfig.EXACTLY_ONCE_V2);

        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, User> bankingAppStream = builder.stream("banking-app-output");

        // bankingAppStream.map((key, value) -> (value.get("Name"), value.get())) ;
    }
    
}