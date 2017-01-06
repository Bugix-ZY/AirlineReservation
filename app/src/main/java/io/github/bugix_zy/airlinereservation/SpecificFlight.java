package io.github.bugix_zy.airlinereservation;

import java.util.ArrayList;

public class SpecificFlight {

    /*-------------------fields-------------------*/
    private RegularFlight regFlight; //due to 1...n
    private ArrayList<Ticket> tickets = new ArrayList<Ticket>(0);//due to 1...n
    private ArrayList<EmployeeRole> employees = new ArrayList<EmployeeRole>(0); // due to n...n
    private ArrayList<String> seats = new ArrayList<String>(108);
    private String date;
    private double currentPrice;
    private int maxCapacity;
    private int curCapacity;
    private String curDepTime;
    private String curArrTime;
    private int boardingGate;

    /*-----------------constructors---------------*/
    public SpecificFlight(RegularFlight regFlight, String date, int maxCapacity, int boardingGate) {
        this.regFlight = regFlight;
        this.date = date;
        this.maxCapacity = maxCapacity;
        this.curCapacity = maxCapacity;
        this.boardingGate = boardingGate;

        this.currentPrice = regFlight.getNominalPrice();
        this.curDepTime = regFlight.getDepTime();
        this.curArrTime = regFlight.getArrTime();

        //default 108 seats
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18};
        char[] alphas = {'A', 'B', 'C', 'D', 'E', 'F'};

        for (int n : numbers) {
            for (char c : alphas) {
                this.seats.add(Integer.toString(n) + c);
            }
        }

        // add links
        regFlight.addLinkToSpecFlight(this);
    }

    /*-------------------methods------------------*/
    public void addLinkToTicket(Ticket ticket){
        this.tickets.add(ticket);
    }


    public void deleteLinkToTicket(Ticket ticket){
        this.tickets.remove(ticket);
    }

    public boolean addEmployee(EmployeeRole e){
        return this.employees.add(e);
    }

    public void addLinkToEmployee(EmployeeRole e){
        this.employees.add(e);
    }

    public void deleteLinkToEmployee(EmployeeRole e){
        this.employees.remove(e);
    }

    public void addCrew(EmployeeRole e){
        e.addLinkToSpecFlight(this);
    }

    public void removeCrew(EmployeeRole e){
        e.deleteLinkToSpecFlight(this);
    }

    public ArrayList<String> getAllPassengers(){
        ArrayList<String> passengers = new ArrayList<String>();
        for (Ticket ticket : tickets) {
            passengers.add(ticket.getPassengerName());
        }
        return passengers;
    }

    public ArrayList<EmployeeRole> getAllEmployees(){
        return this.employees;
    }

    public void cancelSpeFlight(){
        // delete links to Regular Flight
        this.regFlight.deleteLinkToSpecFlight(this);
        // cancel tickets(delete links to the tickets)
        for (Ticket ticket : this.tickets) {
            ticket.cancelTicket();
        }
    }

    public void bookSeat(String seatnum){
        this.seats.remove(seatnum);
    }

    public void returnSeat(String seatnum){
        this.seats.add(seatnum);
    }




    /*-------------------Getters and Setters-------------------*/
    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getCurCapacity() {
        return curCapacity;
    }

    public void setCurCapacity(int curCapacity) {
        this.curCapacity = curCapacity;
    }

    public String getCurDepTime() {
        return curDepTime;
    }

    public void setCurDepTime(String curDepTime) {
        this.curDepTime = curDepTime;
    }

    public String getCurArrTime() {
        return curArrTime;
    }

    public void setCurArrTime(String curArrTime) {
        this.curArrTime = curArrTime;
    }

    public int getBoardingGate() {
        return boardingGate;
    }

    public void setBoardingGate(int boardingGate) {
        this.boardingGate = boardingGate;
    }

    public RegularFlight getRegFlight() {
        return regFlight;
    }
}
