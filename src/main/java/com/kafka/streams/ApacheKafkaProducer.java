package com.kafka.streams;

import java.util.Properties;
import java.util.Scanner;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class ApacheKafkaProducer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String,String> producer = new KafkaProducer<String,String>(properties);

        int i = 5;

        while (i<10){
            i++;
            Scanner scanner =  new Scanner(System.console().readLine("Provide input as Key, Vlaue = " ));
            ProducerRecord<String,String> record = new ProducerRecord<String,String>("test", null, null, scanner.nextLine());
            producer.send(record);
           
        
    }
    producer.flush();
    producer.close();


    }
 
}
