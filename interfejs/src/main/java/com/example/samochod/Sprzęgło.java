package com.example.samochod;

public class Sprzęgło {

    private boolean stanSprzęgła;


    public void wciśnij(){
            stanSprzęgła=true;
        }

    public void zwolnij(){
        stanSprzęgła=false;
    }

    public void getStanSprzęgła(){
        if(stanSprzęgła==true){
            System.out.println("Sprzęgło zaciągnięte");
        }
        else {
            System.out.println("Sprzęgło zwolnione");
        }

    }



}
