package io.github.bugix_zy.airlinereservation;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "airline.db";

    // regular flight
    public static final String TABLE_REGULAR_FLIGHT = "regularFlights";
    public static final String REG_COLUMN_FLIGHT_NO = "_flightNo";
    public static final String REG_COLUMN_DEPATURE = "_departure";
    public static final String REG_COLUMN_ARRIVAL = "_arrival";
    public static final String REG_COLUMN_DEPATURE_TIME = "_departureTime";
    public static final String REG_COLUMN_ARRIVAL_TIME = "_arrivalTime";
    public static final String REG_COLUMN_NOMINAL_PRICE = "_nominalPrice";

    // specific flight
    public static final String TABLE_SPECIFIC_FLIGHT = "specificFlights";
    public static final String SPE_COLUMN_ID = "_id";
    public static final String SPE_COLUMN_DATE = "_date";
    public static final String SPE_COLUMN_FLIGHT_NO = "_flightNo";
    public static final String SPE_COLUMN_MAX_CAPACITY = "_maxCapacity";
    public static final String SPE_COLUMN_CURR_CAPACITY = "_currCapacity";
    public static final String SPE_COLUMN_CURR_DEP_TIME = "_currDepTime";
    public static final String SPE_COLUMN_CURR_ARR_TIME = "_currArrTime";
    public static final String SPE_COLUMN_CURR_PRICE = "_currPrice";
    public static final String SPE_COLUMN_BOARDING_GATE = "_boardingGate";


    // customer
    public static final String TABLE_CUSTOMER = "customer";
    public static final String CUS_COLUMN_ID = "_id";
    public static final String CUS_COLUMN_NAME = "_name";
    public static final String CUS_COLUMN_ID_NUMBER = "_idNumber";
    public static final String CUS_COLUMN_PHONE = "_phone";
    public static final String CUS_COLUME_PSW = "_password";

    // Ticket
    public static final String TABLE_TICKET = "ticket";
    public static final String TIC_COLUMN_ID = "_id";
    public static final String TIC_COLUMN_SPEC_NO = "_specFlightNo";
    public static final String TIC_COLUMN_SPEC_DATE = "_specDate";
    public static final String TIC_COLUMN_CUSTOMER_ID = "_customerId";
    public static final String TIC_COLUMN_SEAT = "_seat";
    public static final String TIC_COLUMN_PASSENGER_NAME = "_passName";
    public static final String TIC_COLUMN_PASSENGER_ID = "_passId";
    public static final String TIC_COLUMN_PASSENGER_PHONE = "_passPhone";
    public static final String TIC_COLUMN_EMERGENCY_NAME = "_emerContName";
    public static final String TIC_COLUMN_EMERGENCY_PHONE = "_emerContPhone";

    // Employee
    public static final String TABLE_EMPLOYEE = "employee";
    public static final String EMP_COLUMN_ID = "_id";
    public static final String EMP_COLUMN_NAME = "_name";
    public static final String EMP_COLUMN_ID_NUMBER = "_idNumber";
    public static final String EMP_COLUMN_PHONE = "_phone";
    public static final String EMP_COLUMN_SUPERVIOSR = "_supervisor";

    // flight and employee
    public static final String TABLE_CREW = "crew";
    public static final String CREW_COLUMN_SPEC_ID = "_specId";
    public static final String CREW_COLUMN_EMP_ID = "_empId";


    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //create a new regular flight table
        String createReg = "CREATE TABLE " + TABLE_REGULAR_FLIGHT + "(" +
                REG_COLUMN_FLIGHT_NO + " TEXT PRIMARY KEY, " +
                REG_COLUMN_DEPATURE + " TEXT, " +
                REG_COLUMN_ARRIVAL + " TEXT, " +
                REG_COLUMN_DEPATURE_TIME + " TEXT, " +
                REG_COLUMN_ARRIVAL_TIME + " TEXT, " +
                REG_COLUMN_NOMINAL_PRICE + " DOUBLE " +
                ");";

        // create a new specific flight table
        String createSpec = "CREATE TABLE " + TABLE_SPECIFIC_FLIGHT + "(" +
                //SPE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SPE_COLUMN_FLIGHT_NO + " TEXT, " +
                SPE_COLUMN_DATE + " TEXT, " +
                SPE_COLUMN_MAX_CAPACITY + " INTEGER, " +
                SPE_COLUMN_CURR_CAPACITY + " INTEGER, " +
                SPE_COLUMN_CURR_DEP_TIME + " TEXT, " +
                SPE_COLUMN_CURR_ARR_TIME + " TEXT, " +
                SPE_COLUMN_CURR_PRICE + " DOUBLE, " +
                SPE_COLUMN_BOARDING_GATE + " INTEGER, " +
                " FOREIGN KEY(_flightNo) REFERENCES regularFlights(_flightNo) " +
                " PRIMARY KEY(_flightNo, _date) " +
                ");";

        // create a new customer table
        String createCustomer = "CREATE TABLE " + TABLE_CUSTOMER + "(" +
                CUS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CUS_COLUMN_NAME + " TEXT, " +
                CUS_COLUMN_ID_NUMBER + " TEXT UNIQUE, " +
                CUS_COLUMN_PHONE + " TEXT UNIQUE, " +
                CUS_COLUME_PSW + " TEXT" +
                ");";

        // create a new ticket table
        String createTicket = "CREATE TABLE " + TABLE_TICKET + "(" +
                TIC_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TIC_COLUMN_CUSTOMER_ID + " TEXT, " +
                TIC_COLUMN_SPEC_NO + " TEXT, " +
                TIC_COLUMN_SPEC_DATE + " TEXT, " +
                TIC_COLUMN_PASSENGER_NAME + " TEXT, " +
                TIC_COLUMN_PASSENGER_ID + "  TEXT, " +
                TIC_COLUMN_PASSENGER_PHONE + " PHONE, " +
                TIC_COLUMN_EMERGENCY_NAME + " TEXT, " +
                TIC_COLUMN_EMERGENCY_PHONE + " TEXT, " +
                TIC_COLUMN_SEAT + " TEXT, " +
                "FOREIGN KEY(_customerId) REFERENCES customer(_id)" +
                ");";

        // create a new employee table
        String createEmployee = "CREATE TABLE " + TABLE_EMPLOYEE + "(" +
                EMP_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EMP_COLUMN_NAME + " TEXT, " +
                EMP_COLUMN_PHONE + " TEXT, " +
                EMP_COLUMN_ID_NUMBER + " TEXT, " +
                EMP_COLUMN_SUPERVIOSR + " INTEGER, " +
                "FOREIGN KEY(_supervisor) REFERENCES employee(_id)" +
                ");";

        // create a new crew table
        String createCrew = "CREATE TABLE " + TABLE_CREW + "(" +
                CREW_COLUMN_EMP_ID + " TEXT, " +
                CREW_COLUMN_SPEC_ID + " TEXT, " +
                "PRIMARY KEY(" + CREW_COLUMN_EMP_ID + "," + CREW_COLUMN_SPEC_ID + ")" +
                ");";


        db.execSQL(createReg);
        db.execSQL(createSpec);
        db.execSQL(createCustomer);
        db.execSQL(createTicket);
        db.execSQL(createEmployee);
        db.execSQL(createCrew);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPECIFIC_FLIGHT + ";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGULAR_FLIGHT + ";");
        onCreate(db);
    }

    public void addReg(RegularFlight regularFlight) {
        ContentValues value = new ContentValues();
        value.put(REG_COLUMN_FLIGHT_NO, regularFlight.getFlightNo());
        value.put(REG_COLUMN_DEPATURE, regularFlight.getDepature());
        value.put(REG_COLUMN_ARRIVAL, regularFlight.getArrival());
        value.put(REG_COLUMN_DEPATURE_TIME, regularFlight.getDepTime());
        value.put(REG_COLUMN_ARRIVAL_TIME, regularFlight.getArrTime());
        value.put(REG_COLUMN_NOMINAL_PRICE, regularFlight.getNominalPrice());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_REGULAR_FLIGHT, null, value);
        db.close();
    }

    public void addSpec(SpecificFlight specificFlight) {
        ContentValues value = new ContentValues();
        value.put(SPE_COLUMN_DATE, specificFlight.getDate());
        value.put(SPE_COLUMN_FLIGHT_NO, specificFlight.getRegFlight().getFlightNo());
        value.put(SPE_COLUMN_MAX_CAPACITY, specificFlight.getMaxCapacity());
        value.put(SPE_COLUMN_CURR_CAPACITY,specificFlight.getCurCapacity());
        value.put(SPE_COLUMN_CURR_DEP_TIME, specificFlight.getCurDepTime());
        value.put(SPE_COLUMN_CURR_ARR_TIME, specificFlight.getCurArrTime());
        value.put(SPE_COLUMN_CURR_PRICE, specificFlight.getCurrentPrice());
        value.put(SPE_COLUMN_BOARDING_GATE, specificFlight.getBoardingGate());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_SPECIFIC_FLIGHT, null, value);
        db.close();
    }

    public void addCustomer(Person person, String psw){
        ContentValues value = new ContentValues();
        value.put(CUS_COLUMN_NAME, person.getName());
        value.put(CUS_COLUMN_ID_NUMBER, person.getId());
        value.put(CUS_COLUMN_PHONE, person.getPhone());
        value.put(CUS_COLUME_PSW, psw);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_CUSTOMER, null, value);
        db.close();
    }


    public void addEmp(Person person){
        ContentValues value = new ContentValues();
        value.put(EMP_COLUMN_ID_NUMBER, person.getId());
        value.put(EMP_COLUMN_NAME, person.getName());
        value.put(EMP_COLUMN_PHONE, person.getPhone());
        // supervisor 还未放入db

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_EMPLOYEE, null, value);
        db.close();
    }

    public void addTicket(Ticket ticket, String account, String flightno, String date){
        ContentValues value = new ContentValues();
        value.put(TIC_COLUMN_CUSTOMER_ID, account);
        value.put(TIC_COLUMN_SPEC_NO, flightno);
        value.put(TIC_COLUMN_SPEC_DATE, date);
        value.put(TIC_COLUMN_PASSENGER_NAME, ticket.getPassengerName());
        value.put(TIC_COLUMN_PASSENGER_ID, ticket.getPassgenerId());
        value.put(TIC_COLUMN_PASSENGER_PHONE, ticket.getPassengerPhone());
        value.put(TIC_COLUMN_EMERGENCY_NAME, ticket.getEmergencyName());
        value.put(TIC_COLUMN_EMERGENCY_PHONE, ticket.getEmergencyContact());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_TICKET, null, value);
        db.close();
    }


    public void addCrew(Person person, SpecificFlight specificFlight){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        int empid = this.findEmpId(person.getName(), person.getId());
        int speid = this.findSpecId(specificFlight.getRegFlight().getFlightNo(), specificFlight.getDate());
        value.put(CREW_COLUMN_EMP_ID, empid);
        value.put(CREW_COLUMN_SPEC_ID, speid);

        db.insert(TABLE_EMPLOYEE, null, value);
        db.close();
    }

    public int findEmpId(String name, String idNumber){
        String query = "SELECT * FROM " + TABLE_EMPLOYEE +
                " WHERE " + EMP_COLUMN_ID_NUMBER + "=" + idNumber +
                " and " + EMP_COLUMN_NAME + "=" + name;
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        int id = c.getColumnIndex(EMP_COLUMN_ID);
        return id;
    }

    public int findSpecId(String flightno, String date){
        String query = "SELECT * FROM " + TABLE_EMPLOYEE +
                " WHERE " + SPE_COLUMN_DATE + "=" + date +
                " and " + SPE_COLUMN_FLIGHT_NO + "=" + flightno;
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        int id = c.getColumnIndex(SPE_COLUMN_ID);
        return id;
    }


    public String findSpec(String depature, String arrival, String date){
        String result = "";

        SQLiteDatabase db = getWritableDatabase();
        // 根據depature和arrival去table reg取得flight no
        String query = "SELECT * FROM " + TABLE_REGULAR_FLIGHT + " WHERE " +
                REG_COLUMN_ARRIVAL + " = " + "\'" + arrival +  "\'" + " and " +
                REG_COLUMN_DEPATURE + " = " + "\'" + depature +  "\'"  + ";";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while(!c.isAfterLast()) {
            String flightno = c.getString(c.getColumnIndex(REG_COLUMN_FLIGHT_NO));
            String search = "SELECT * FROM " + TABLE_SPECIFIC_FLIGHT + " WHERE " +
                    SPE_COLUMN_FLIGHT_NO + " = " + "\'" + flightno +  "\'" +  " and " +
                    SPE_COLUMN_DATE + " = " + "\'" + date +  "\'" + ";";
            Cursor d = db.rawQuery(search, null);
            d.moveToFirst();
            while (!d.isAfterLast()){
                result += d.getString(d.getColumnIndex(SPE_COLUMN_FLIGHT_NO));
                result += "   ";
                result += d.getString(d.getColumnIndex(SPE_COLUMN_DATE));
                result += "   ";
                result += d.getString(d.getColumnIndex(SPE_COLUMN_CURR_DEP_TIME));
                result += "   ";
                result += d.getString(d.getColumnIndex(SPE_COLUMN_CURR_ARR_TIME));
                result += "   ";
                result += d.getString(d.getColumnIndex(SPE_COLUMN_CURR_CAPACITY));
                result += "\n";
                d.moveToNext();
            }
            d.close();
            c.moveToNext();

        }

        c.close();
        db.close();
        Log.i("bugixlogmessgae", "qndm" + result);
        return result;
    }


    public Person getCustomer(String account){
        String query = "SELECT * FROM " + TABLE_CUSTOMER +
                " WHERE " + CUS_COLUMN_PHONE + "=" + "\'"  + account +  "\';" ;
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        Person person = new Person(c.getString(c.getColumnIndex(CUS_COLUMN_NAME)),
                c.getString(c.getColumnIndex(CUS_COLUMN_ID_NUMBER)),
                account
                );
        return person;
    }

    public RegularFlight getRegFlight(String flightno){
        String query = "SELECT * FROM " + TABLE_REGULAR_FLIGHT +
                " WHERE " + REG_COLUMN_FLIGHT_NO + "=" + "\'"  + flightno + "\';"  ;
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        RegularFlight flight = new RegularFlight(flightno,
                c.getString(c.getColumnIndex(REG_COLUMN_DEPATURE)),
                c.getString(c.getColumnIndex(REG_COLUMN_ARRIVAL)),
                c.getString(c.getColumnIndex(REG_COLUMN_DEPATURE_TIME)),
                c.getString(c.getColumnIndex(REG_COLUMN_ARRIVAL_TIME)),
                c.getDouble(c.getColumnIndex(REG_COLUMN_NOMINAL_PRICE))
        );
        Log.i("bugixMessage", "in dbhandle" + flight.toString());
        return flight;
    }
    public SpecificFlight getSpecFlight(String flightno, String date){
        String query = "SELECT * FROM " + TABLE_SPECIFIC_FLIGHT +
                " WHERE " + SPE_COLUMN_DATE + "=" + "\'"  + date + "\'" +
        " and " + SPE_COLUMN_FLIGHT_NO + "=" + "\'"  + flightno  + "\';" ;
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        SpecificFlight flight = new SpecificFlight(getRegFlight(flightno),
                c.getString(c.getColumnIndex(SPE_COLUMN_DATE)),
                c.getInt(c.getColumnIndex(SPE_COLUMN_MAX_CAPACITY)),
                c.getInt(c.getColumnIndex(SPE_COLUMN_BOARDING_GATE))
        );
        Log.i("bugixMessage", "in dbhandle" + flight.toString());
        return flight;
    }

    public String getDepTime(String flightNo, String date){
        String query = "SELECT * FROM " + TABLE_SPECIFIC_FLIGHT +
                " WHERE " + SPE_COLUMN_DATE + "=" + "\'"  + date + "\'" +
                " and " + SPE_COLUMN_FLIGHT_NO + "=" + "\'"  + flightNo  + "\';" ;
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        String time  = c.getString(c.getColumnIndex(SPE_COLUMN_CURR_DEP_TIME));
        return time;
    }

    public String getArrTime(String flightNo, String date){
        String query = "SELECT * FROM " + TABLE_SPECIFIC_FLIGHT +
                " WHERE " + SPE_COLUMN_DATE + "=" + "\'"  + date + "\'" +
                " and " + SPE_COLUMN_FLIGHT_NO + "=" + "\'"  + flightNo  + "\';" ;
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        String time  = c.getString(c.getColumnIndex(SPE_COLUMN_CURR_ARR_TIME));
        return time;
    }

    public String getDepature(String flightNo){
        String query = "SELECT * FROM " + TABLE_REGULAR_FLIGHT +
                " WHERE " + REG_COLUMN_FLIGHT_NO + "=" + "\'"  + flightNo + "\'" ;
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        String start  = c.getString(c.getColumnIndex(REG_COLUMN_DEPATURE));
        return start;
    }

    public String getArrival(String flightNo){
        String query = "SELECT * FROM " + TABLE_REGULAR_FLIGHT +
                " WHERE " + REG_COLUMN_FLIGHT_NO + "=" + "\'"  + flightNo + "\'" ;
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        String end  = c.getString(c.getColumnIndex(REG_COLUMN_ARRIVAL));
        return end;
    }

    public String[] getTicket(String account){
        String query = "SELECT * FROM " + TABLE_TICKET +
                " WHERE " + TIC_COLUMN_CUSTOMER_ID + "=" + "\'"  + account + "\';";
        SQLiteDatabase db = getWritableDatabase();
        Cursor d = db.rawQuery(query, null);
        d.moveToFirst();
        int count = 0;
        while (!d.isAfterLast()){
            d.moveToNext();
            count += 1;
        }
        String[] tickets = new String[count];
        d.moveToFirst();
        int i = 0;
        while (!d.isAfterLast() && i < count){
            String result = "";
            result += d.getString(d.getColumnIndex(TIC_COLUMN_SPEC_NO));
            result += "   ";
            result += d.getString(d.getColumnIndex(TIC_COLUMN_SPEC_DATE));
            result += "   ";
            String flightno = d.getString(d.getColumnIndex(TIC_COLUMN_SPEC_NO));
            String date = d.getString(d.getColumnIndex(TIC_COLUMN_SPEC_DATE));
            result += getDepTime(flightno, date);
            result += "   ";
            result += getArrTime(flightno, date);
            result += "   ";
            result += getDepature(flightno);
            result += "   ";
            result += getArrival(flightno);
            tickets[i] = result;
            i += 1;
            d.moveToNext();
        }
        d.close();

        return tickets;
    }

    // get the datails of the ticket
    public String getTicketDetail(String flightno, String date, String account){
        String query = "SELECT * FROM " + TABLE_TICKET +
                " WHERE " + TIC_COLUMN_CUSTOMER_ID + "=" + "\'"  + account + "\'"  + " and " +
                TIC_COLUMN_SPEC_NO + "=" + "\'"  + flightno + "\'"  + " and " +
                TIC_COLUMN_SPEC_DATE + "=" + "\'"  + date + "\';";
        SQLiteDatabase db = getWritableDatabase();
        Cursor d = db.rawQuery(query, null);
        d.moveToFirst();
        String result = "";
        result += "Flight No: ";
        result += d.getString(d.getColumnIndex(TIC_COLUMN_SPEC_NO));
        result += "\n";
        result += "Date: ";
        result += d.getString(d.getColumnIndex(TIC_COLUMN_SPEC_DATE));
        result += "\n";
        result += "Departure Time: ";
        result += getDepTime(flightno, date);
        result += "\n";
        result += "Arrival Time: ";
        result += getArrTime(flightno, date);
        result += "\n";
        result += "Departure: ";
        result += getDepature(flightno);
        result += "\n";
        result += "Arrival: ";
        result += getArrival(flightno);
        result += "\n";
        result += "Passenger Name: ";
        result += d.getString(d.getColumnIndex(TIC_COLUMN_PASSENGER_NAME));
        result += "\n";
        result += "Passenger ID: ";
        result += d.getString(d.getColumnIndex(TIC_COLUMN_PASSENGER_ID));
        result += "\n";
        result += "Passenger Phone: ";
        result += d.getString(d.getColumnIndex(TIC_COLUMN_PASSENGER_PHONE));
        result += "\n";
        result += "Emergency Contact Name: ";
        result += d.getString(d.getColumnIndex(TIC_COLUMN_EMERGENCY_NAME));
        result += "\n";
        result += "Emergency Contact Phone: ";
        result += d.getString(d.getColumnIndex(TIC_COLUMN_EMERGENCY_PHONE));
        result += "\n";
        return result;
    }

    public void  deleteTicket(String flightno, String date, String account){
        String cmd = "DELETE FROM " + TABLE_TICKET + " WHERE " +
                TIC_COLUMN_CUSTOMER_ID  + "=" + "\'"  + account + "\'"  + " and " +
                TIC_COLUMN_SPEC_DATE + "=" + "\'"  + date + "\'"  + " and " +
                TIC_COLUMN_SPEC_NO + "=" + "\'"  + flightno + "\';";

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(cmd);
    }

    public void  deleteReg(String flightno){
        String cmd = "DELETE FROM " + TABLE_REGULAR_FLIGHT + " WHERE " +
                REG_COLUMN_FLIGHT_NO + "=" + "\'"  + flightno + "\';";
        String cmd2 = "DELETE FROM " + TABLE_SPECIFIC_FLIGHT + " WHERE " +
                SPE_COLUMN_FLIGHT_NO + "=" + "\'"  + flightno + "\';";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(cmd);
        db.execSQL(cmd2);
    }

    public void  deleteSpec(String flightno, String date){
        String cmd = "DELETE FROM " + TABLE_SPECIFIC_FLIGHT + " WHERE " +
                SPE_COLUMN_FLIGHT_NO + "=" + "\'"  + flightno + "\'" + " and " +
                SPE_COLUMN_DATE + "=" + "\'"  + date + "\';";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(cmd);
    }

    public String dbReg2string() {
        String dbstr = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_REGULAR_FLIGHT + " WHERE 1"; //Select every column, select every row.

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex(REG_COLUMN_FLIGHT_NO)) != null){
                dbstr += c.getString(c.getColumnIndex(REG_COLUMN_FLIGHT_NO));
                dbstr += "    \t";
                dbstr += c.getString(c.getColumnIndex(REG_COLUMN_DEPATURE));
                dbstr += "    \t";
                dbstr += c.getString(c.getColumnIndex(REG_COLUMN_ARRIVAL));
                dbstr += "    \t";
                dbstr += c.getString(c.getColumnIndex(REG_COLUMN_DEPATURE_TIME));
                dbstr += "    \t";
                dbstr += c.getString(c.getColumnIndex(REG_COLUMN_ARRIVAL_TIME));
                dbstr += "    \t";
                dbstr += c.getString(c.getColumnIndex(REG_COLUMN_NOMINAL_PRICE));
                dbstr += "    \n";
                Log.i("bugixlogmessgae", "db2str=>" + dbstr);
            }
            c.moveToNext();
        }
        c.close();
        db.close();
        return dbstr;
    }

    public void updateCapc(String no, String date){

        SQLiteDatabase db = getWritableDatabase();
        String search = "SELECT * FROM " + TABLE_SPECIFIC_FLIGHT + " WHERE " +
                SPE_COLUMN_FLIGHT_NO + " = " + "\'" + no +  "\'" +  " and " +
                SPE_COLUMN_DATE + " = " + "\'" + date +  "\'" + ";";
        Cursor d = db.rawQuery(search, null);
        d.moveToFirst();
        int capacity = d.getInt(d.getColumnIndex(SPE_COLUMN_CURR_CAPACITY));
        ContentValues cv = new ContentValues();
        cv.put(SPE_COLUMN_CURR_CAPACITY, capacity-1);
        db.update(TABLE_SPECIFIC_FLIGHT,cv, SPE_COLUMN_FLIGHT_NO + "=" + "\'" + no + "\'" +
                " and " + SPE_COLUMN_DATE + "=" + "\'" + date + "\'", null);
    }

    public boolean isMember(String account, String psw){
        String search = "SELECT * FROM " + TABLE_CUSTOMER + " WHERE " +
                CUS_COLUMN_PHONE + " = " + "\'" + account +  "\'" +  " and " +
                CUS_COLUME_PSW + " = " + "\'" + psw +  "\'" + ";";
        SQLiteDatabase db = getWritableDatabase();
        Cursor d = db.rawQuery(search, null);
        int i = d.getCount();

        d.close();
        db.close();
        return (i > 0);

    }

    public String getCustomerName(String account){
        String search = "SELECT * FROM " + TABLE_CUSTOMER + " WHERE " +
                CUS_COLUMN_PHONE + " = " + "\'" + account +  "\'";
        SQLiteDatabase db = getWritableDatabase();
        Cursor d = db.rawQuery(search, null);
        d.moveToFirst();
        String name = d.getString(d.getColumnIndex(CUS_COLUMN_NAME));
        d.close();
        db.close();
        return name;

    }
    public String dbSpec2string() {
        String dbstr = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_SPECIFIC_FLIGHT + " WHERE 1"; //Select every column, select every row.

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex(SPE_COLUMN_FLIGHT_NO)) != null){
                dbstr += c.getString(c.getColumnIndex(SPE_COLUMN_FLIGHT_NO));
                dbstr += "    \t";
                dbstr += c.getString(c.getColumnIndex(SPE_COLUMN_DATE));
                dbstr += "    \t";
                dbstr += c.getString(c.getColumnIndex(SPE_COLUMN_CURR_DEP_TIME));
                dbstr += "    \t";
                dbstr += c.getString(c.getColumnIndex(SPE_COLUMN_CURR_ARR_TIME));
                dbstr += "    \t";
                dbstr += c.getString(c.getColumnIndex(SPE_COLUMN_CURR_PRICE));
                dbstr += "    \t";
                dbstr += c.getString(c.getColumnIndex(SPE_COLUMN_CURR_CAPACITY));
                dbstr += "    \n";
                Log.i("bugixlogmessgae", "dbspec2str=>" + dbstr);
            }
            c.moveToNext();
        }
        c.close();
        db.close();
        return dbstr;
    }



    public String dbCustomerstring() {
        String dbstr = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_CUSTOMER + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex(CUS_COLUMN_ID)) != null){

                dbstr += c.getString(c.getColumnIndex(CUS_COLUMN_NAME));
                dbstr += "  ";
                dbstr += c.getString(c.getColumnIndex(CUS_COLUMN_ID_NUMBER));
                dbstr += "  ";
                dbstr += c.getString(c.getColumnIndex(CUS_COLUMN_PHONE));
                dbstr += "  ";
                dbstr += c.getString(c.getColumnIndex(CUS_COLUME_PSW));
                dbstr += "\n";
                Log.i("bugixlogmessgae", "customer db =>" + dbstr);
            }
            c.moveToNext();
        }
        c.close();
        db.close();
        return dbstr;
    }

    public String dbTicketstring() {
        String dbstr = " ";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_TICKET + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex(TIC_COLUMN_ID)) != null){
                dbstr += c.getString(c.getColumnIndex(TIC_COLUMN_CUSTOMER_ID));
                dbstr += "  ";
                dbstr += c.getString(c.getColumnIndex(TIC_COLUMN_PASSENGER_NAME));
                dbstr += "  ";
                dbstr += c.getString(c.getColumnIndex(TIC_COLUMN_PASSENGER_ID));
                dbstr += "  ";
                dbstr += c.getString(c.getColumnIndex(TIC_COLUMN_SPEC_DATE));
                dbstr += "  ";
                dbstr += c.getString(c.getColumnIndex(TIC_COLUMN_SPEC_NO));
                dbstr += "\n";
                Log.i("bugixlogmessgae", "ticket db =>" + dbstr);
            }
            c.moveToNext();
        }
        c.close();
        db.close();
        return dbstr;
    }

}
