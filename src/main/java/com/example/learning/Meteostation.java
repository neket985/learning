package com.example.learning;

public class Meteostation {
    private int temperature;
    private int davlenie;

    public void setTemp(int newTemp){
        temperature = newTemp;
    }



    Meteostation(int initTemp, int initDavl){
        temperature = initTemp;
        davlenie = initDavl;
    }
}
