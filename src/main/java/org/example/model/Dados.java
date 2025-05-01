package org.example.model;

import java.util.Random;
public class Dados {

    Random random = new Random();

    public Integer getD20 (){
        return random.nextInt(20) + 1;
    }
    public Integer getD12(){
        return random.nextInt(12) + 1;
    }
    public Integer getD10 (){
        return random.nextInt(10) + 1;
    }
    public Integer getD8(){
        return random.nextInt(8) + 1;
    }
    public Integer getD6(){
        return random.nextInt(6) + 1;
    }
    public Integer getD4() {
        return random.nextInt(4) + 1;
    }
}
