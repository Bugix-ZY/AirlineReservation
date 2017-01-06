package io.github.bugix_zy.airlinereservation;

public class Ticket {

    /*-------------------fields-------------------*/
    private SpecificFlight specFlight;
    private CustomerRole customer;
    private double price;
    private String seatnum;
    //passenger info
    private String passengerName;
    private String passengerPhone;
    private String passgenerId;
    private String emergencyName; //Emergency Contact Person's name
    private String emergencyContact;//Emergency Contact Person's phone

    /*-----------------constructors---------------*/
    public Ticket(CustomerRole customer, SpecificFlight specFlight, String seatnum, String passengerName,
                  String passengerPhone, String passgenerId, String emergencyName, String emergencyContact) {
        this.specFlight = specFlight;
        this.customer = customer;
        this.seatnum = seatnum;
        this.specFlight.bookSeat(seatnum);
        this.passengerName = passengerName;
        this.passengerPhone = passengerPhone;
        this.passgenerId = passgenerId;
        this.emergencyName = emergencyName;
        this.emergencyContact = emergencyContact;
        this.price = this.specFlight.getCurrentPrice();
        // add links
        this.customer.addLinkToTicket(this);
        this.specFlight.addLinkToTicket(this);
    }

    /*-------------------methods------------------*/
    public void cancelTicket(){
        // update specific flight's status
        int cur = this.specFlight.getCurCapacity();
        this.specFlight.setCurCapacity(cur + 1);
        this.specFlight.returnSeat(this.seatnum);
        // delete links
        this.customer.deleteLinkToTicket(this);
        this.specFlight.deleteLinkToTicket(this);
    }

    /*-------------------Getters and Setters-------------------*/
    public CustomerRole getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerRole customer) {
        this.customer = customer;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerPhone() {
        return passengerPhone;
    }

    public void setPassengerPhone(String passengerPhone) {
        this.passengerPhone = passengerPhone;
    }

    public String getPassgenerId() {
        return passgenerId;
    }

    public void setPassgenerId(String passgenerId) {
        this.passgenerId = passgenerId;
    }

    public String getEmergencyName() {
        return emergencyName;
    }

    public void setEmergencyName(String emergencyName) {
        this.emergencyName = emergencyName;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }


}
