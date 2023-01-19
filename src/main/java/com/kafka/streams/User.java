package com.kafka.streams;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Hello world!
 *
 */
public class User {
    @JsonProperty
    String name;
    @JsonProperty
    int amount;
    @JsonProperty
    String time;

    public User(String name, int amount ,Instant time) {

        this.name = name;
        this.amount = amount;
        this.time = time.toString();
    }
    
   
    }