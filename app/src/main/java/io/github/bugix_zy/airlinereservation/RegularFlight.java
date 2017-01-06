package io.github.bugix_zy.airlinereservation;


import java.util.ArrayList;

public class RegularFlight {
    /*-------------------fields-------------------*/
    private ArrayList<SpecificFlight> specflights = new ArrayList<SpecificFlight>(0);
    private Airline airline = Airline.getInstance();
    private String flightNo;
    private String departure;
    private String arrival;
    private String depTime;
    private String arrTime;
    private double nominalPrice;


    /*-----------------constructors---------------*/
    public RegularFlight(String flightNo, String depature, String arrival, String depTime, String arrTime,
                         double nominalPrice) {
        this.flightNo = flightNo;
        this.departure = depature;
        this.arrival = arrival;
        this.depTime = depTime;
        this.arrTime = arrTime;
        this.nominalPrice = nominalPrice;
        // add links
        airline.addLinkToRegFlight(this);
    }


    /*-------------------methods------------------*/
    public void addLinkToSpecFlight(SpecificFlight flight){
        this.specflights.add(flight);
    }

    public void deleteLinkToSpecFlight(SpecificFlight flight){
        this.specflights.remove(flight);
    }

    public void addSpecFlight( String date, int maxCapacity, int boardingGate){
        new SpecificFlight(this, date, maxCapacity, boardingGate);
    }

    public ArrayList<SpecificFlight> getAllSpecFlights(){
        return this.specflights;
    }

    public void cancelRegFlight(){
        // delete links to airline
        this.airline.deleteLinkToRegFlight(this);
        // cancel each specific flight
        for (SpecificFlight specificFlight : specflights) {
            specificFlight.cancelSpeFlight();
        }
    }
    /*-------------------Getters and Setters-------------------*/
    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getDepature() {
        return departure;
    }

    public void setDepature(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDepTime() {
        return depTime;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }

    public String getArrTime() {
        return arrTime;
    }

    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }

    public double getNominalPrice() {
        return nominalPrice;
    }

    public void setNominalPrice(double nominalPrice) {
        this.nominalPrice = nominalPrice;
    }


}
