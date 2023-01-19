package com.kafka.streams;

import java.util.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import org.json.simple.JSONObject;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class BankingApplicationProducerV0{

    public static void main(String[] args) {
        // Producer Properties
        // Producer Defination
        // Producer Execution

        String bootstrapServer = "localhost:9092";


        
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName() );
        // properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, Kafka );
        properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_DOC,true);
        
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        int totalTransactions = 0;
        while(totalTransactions <= 3)
        {
            for(int i =1; i <= 100; i++)
            
            {
                String msg = "{ Name:"+randomNameGenerator() 
            + ", amount:"+randomAmountGenerator()
            +", time:"+ generateTimeStsmp() +" }";

                ProducerRecord<String, String> record = new ProducerRecord<String,String>("banking-app-input", 
                msg);


                producer.send(record);
                

            }

            try{
                Thread.sleep(1000);
            } catch(InterruptedException ex)
            {
        
            }

            totalTransactions += 1;

            
        }
        producer.flush();
        producer.close();


    }

    public static int randomAmountGenerator(){

        Random random = new Random();
        
        return random.nextInt(100);

    }

    public static String randomNameGenerator(){

        Random random = new Random();

        List<String> nameList = new ArrayList<>();
        nameList.add("Abhi");
        nameList.add("Pooja");
        nameList.add("Sunny");
        nameList.add("Guudu");

        return nameList.get(random.nextInt(nameList.size()));


    }

    public static Instant generateTimeStsmp(){

        Date date = new Date();

        Instant currentTimeStamp = date.toInstant();

        return currentTimeStamp;

    }
    public static JSONObject jsonGenerator(String Name, int Amt, Instant timestamp){

        JSONObject custTransction = new JSONObject();
        
        custTransction.put("time",timestamp);
        custTransction.put("amount",Amt);
        custTransction.put("Name",Name);

        return custTransction;

        
    }

    
    
}