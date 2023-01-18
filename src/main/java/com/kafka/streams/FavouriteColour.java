package com.kafka.streams;

import java.security.Key;
import java.security.acl.Group;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Aggregator;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.KeyValueMapper;

public class FavouriteColour{

    public static void main(String[] args) {

        //Properties
        //Bootstrap-server, serdes, application-id, 
        //read topic - Ktable builder
        //Data Manuplation - KTable
        //Output Topic - Ktable
        //execute 
        //Close

        Properties properties = new Properties();
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG,Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,Serdes.String().getClass());
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "favouriteColour");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

        StreamsBuilder builder = new StreamsBuilder();
        
        KStream<String, String> favouriteColorInput =  builder.stream("favourite_colour");

        // KTable<String, String> favouriteColorInterim =

        KTable<String, String> favouriteColorInterim = 
        favouriteColorInput
        .map((key, value) -> KeyValue.pair(value.split(",")[0], value.split(",")[1]))
        .toTable();

        KTable<String, Long> favouriteColorOutput = favouriteColorInterim.
        toStream().
        selectKey((key,value) -> value)
        .groupByKey()
        .count();
        
        favouriteColorOutput.toStream().to("favouriteColorOutput");

        KafkaStreams streams = new KafkaStreams(builder.build(), properties);
        
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
        
    }
}
