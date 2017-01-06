package io.github.bugix_zy.airlinereservation;

import java.util.ArrayList;

public class EmployeeRole extends PersonRole {

    /*-------------------fields-------------------*/
    private ArrayList<SpecificFlight> flights = new ArrayList<SpecificFlight>(0);
    private EmployeeRole superior; // supervisor
    private ArrayList<EmployeeRole> inferior = new ArrayList<EmployeeRole>(0); //
    private String job;


    /*-------------------methods------------------*/
    public void addLinkToSpecFlight(SpecificFlight flight){
        this.flights.add(flight);
        flight.addLinkToEmployee(this);
    }

    public void deleteLinkToSpecFlight(SpecificFlight flight){
        this.flights.remove(flight);
        flight.deleteLinkToEmployee(this);
    }

    public void addLinkToInferior(EmployeeRole e){
        this.inferior.add(e);
    }

    public void deleteLinkToInferior(EmployeeRole e){
        this.inferior.remove(e);
    }

    public void addInferior(EmployeeRole inf){
        inf.setSuperior(this);
        inf.superior.addLinkToInferior(inf);
    }

    public void removeInferior(EmployeeRole inf){
        inf.setSuperior(null);
        inf.superior.deleteLinkToInferior(inf);
    }

    /*-------------------Getters and Setters-------------------*/
    public EmployeeRole getSuperior() {
        return superior;
    }

    public void setSuperior(EmployeeRole superior) {
        this.superior = superior;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public ArrayList<SpecificFlight> getFlights() {
        return flights;
    }

    public ArrayList<EmployeeRole> getInferior() {
        return inferior;
    }



}
