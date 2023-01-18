package com.kafka.streams;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

public class ApacheKafkaConsumer {
    public static void main(String[] args) {
        // Logger logger = LoggerFactory.getLogger(ApacheKafkaConsumer.class.getTypeName());
        
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"KafkaConsumer");

        KafkaConsumer<String, Long> consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(Arrays.asList("word-count-ouput"));

        while(true){
            ConsumerRecords<String, Long> records = consumer.poll(Duration.ofMillis(100));

            for(ConsumerRecord<String, Long> record: records){
               System.out.println("Key:" + record.key() + " , Value: "+record.value());
                System.out.println("Partition: " + record.partition() + " Offset: "+record.offset());
                
            }

        }

    }
  
}
