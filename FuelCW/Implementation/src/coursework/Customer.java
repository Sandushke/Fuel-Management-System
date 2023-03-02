package coursework;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

public class Customer {
    private int registrationNumber;
    private int vehicleType;
    private int fuelType;
    private float fuelNeeded;
    private boolean gotTicket;

    public Customer(int registrationNumber, int vehicleType, int fuelType, float fuelNeeded, boolean gotTicket){
        this.registrationNumber = registrationNumber;
        this.vehicleType = vehicleType;
        this.fuelType = fuelType;
        this.fuelNeeded = fuelNeeded;
        this.gotTicket = gotTicket;
    }

    public int getRegistrationNumber() {
        return registrationNumber;
    }

    public int getVehicleType() {
        return vehicleType;
    }

    public int getFuelType() {
        return fuelType;
    }

    public float getFuelNeeded() {
        return fuelNeeded;
    }

    public boolean gotTicket() {
        return gotTicket;
    }
    public void getTicket(){
        gotTicket=true;
    }

}

