package com.kafka.streams;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.time.Instant;



import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Hello world!
 *
 */
public class Rough 
{
    public static void main( String[] args )
    {
        // Random random = new Random();
        // int randomLoop = 0;

        // do{
        // System.out.print(randomAmountGenerator()+", ");
        // randomLoop += 1;
        // } while(randomLoop <= 20);
        
        // List<String> personName = new ArrayList<>();
        // personName.add("P1");
        // personName.add("P2");
        // personName.add("P3");

        SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy/.HH:mm:ss");
        String timeStamp = df.format(new Date());


        // Trying Object Mapper for creating JSON

        
        int nameGenerator = 0;
        while(nameGenerator<=5){

            String msg = "{ Name:"+randomNameGenerator() 
            + ", amount:"+randomAmountGenerator()
            +", time:"+ timestampGenerator() +" }";

            System.out.println(msg);
            System.out.println(jsonGenerator(randomNameGenerator(), randomAmountGenerator(), timestampGenerator()));

            JSONObject custTransaction = jsonGenerator(randomNameGenerator(), randomAmountGenerator(), timestampGenerator());
            System.out.println( custTransaction.get("Name"));

            try        
            {
                Thread.sleep(1000);
            } 
            catch(InterruptedException ex) 
            {
                Thread.currentThread().interrupt();
            }
            
        nameGenerator += 1;
        }


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
    public static Instant timestampGenerator(){

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


    }



}
