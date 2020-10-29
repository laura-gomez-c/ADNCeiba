package com.ceiba.adnparquedero.domain.model;

import com.ceiba.adnparquedero.domain.common.util.CalendarOperatorUtil;

import java.util.Calendar;

import lombok.Data;

@Data
public abstract class Vehicle {

    //Constants
    public static final String DATE_TIME = "dd/M/yyyy hh:mm";

    //Attributes
    protected String licensePlate;
    protected String arrivingTime;
    protected String leavingTime;


    public Vehicle() {
    }

    public Vehicle(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Vehicle(String licensePlate, String arrivingTime) {
        this.licensePlate = licensePlate;
        this.arrivingTime = arrivingTime;
    }

    public void setCalendarArrivingTime(Calendar calendar) {
        this.arrivingTime = CalendarOperatorUtil.parseCalendarToString(calendar, DATE_TIME);
    }

    /**
     * Method to validate the parking entry, if the license plate starting with "A" and the entry calendar day is SUNDAY or MONDAY
     * the vehicle won't be able to get in. Otherwise, the vehicle will so.
     *
     * @return true if the entry is valid. Otherwise, false.
     */
    public boolean hasValidEntryByDay() {
        if (this.getLicensePlate().startsWith("A")) {
            Calendar calendar = Calendar.getInstance();
            return Calendar.SUNDAY != calendar.get(Calendar.DAY_OF_WEEK) && Calendar.MONDAY != calendar.get(Calendar.DAY_OF_WEEK);
        }

        return true;
    }
}
