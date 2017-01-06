package io.github.bugix_zy.airlinereservation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class CustomerRole extends PersonRole {

    /*-------------------fields-------------------*/
    private ArrayList<Ticket> tickets = new ArrayList<Ticket>(0); // due to 1...n
	/*-----------------constructors---------------*/

    /*-------------------methods------------------*/
    public void makeBooking(SpecificFlight specFlight, String seatnum, String passengerName, String passengerPhone,
                            String passengerId, String emergencyName, String emergencyContact) {
        new Ticket(this, specFlight, seatnum, passengerName, passengerPhone, passengerId, emergencyName,
                emergencyContact);
    }

    public void addLinkToTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    public void deleteLinkToTicket(Ticket ticket) {
        this.tickets.remove(ticket);
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

}
