package io.github.bugix_zy.airlinereservation;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class Airline extends AppCompatActivity {
    /*-------------------fields-------------------*/
    private ArrayList<RegularFlight> regFlights = new ArrayList<RegularFlight>(0);//due to 1...n

    private static Airline airline; //class variable

    /*-----------------constructors---------------*/
    // only one instance is allowed
    private Airline() {
    }


    /*-------------------methods------------------*/
    public static Airline getInstance(){
        if(airline== null){
            airline = new Airline();
        }
        return airline;
    }

    public void addRegFlight(String flightNo, String departure, String arrival, String depTime, String arrTime,
                             double nominalPrice){
        new RegularFlight(flightNo, departure, arrival, depTime, arrTime, nominalPrice);

    }

    public void addLinkToRegFlight(RegularFlight flight){
        this.regFlights.add(flight);
    }

    public void deleteLinkToRegFlight(RegularFlight flight){
        this.regFlights.remove(flight);
    }

    public ArrayList<RegularFlight> getRegFlights() {
        return regFlights;
    }


}
