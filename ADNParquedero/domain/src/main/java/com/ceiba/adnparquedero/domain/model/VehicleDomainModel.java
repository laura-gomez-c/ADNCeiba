package com.ceiba.adnparquedero.domain.model;

import com.ceiba.adnparquedero.domain.common.constant.DateFormat;
import com.ceiba.adnparquedero.domain.common.util.CalendarOperatorUtil;

import java.util.Calendar;

import lombok.Data;

@Data
public class VehicleDomainModel {

    protected String licensePlate;

    protected String arrivingTime;

    protected String leavingTime;


    public VehicleDomainModel() {
    }

    public VehicleDomainModel(String licensePlate, String arrivingTime, String leavingTime) {
        this.licensePlate = licensePlate;
        this.arrivingTime = arrivingTime;
        this.leavingTime = leavingTime;
    }

    public void setCalendarArrivingTime(Calendar calendar) {
        this.arrivingTime = CalendarOperatorUtil.parseCalendarToString(calendar, DateFormat.DATE_TIME);
    }

    /**
     * Method to validate the parking entry, if the license plate starting with "A" and the entry calendar day is SUNDAY or MONDAY
     * the vehicle won't be able to get in. Otherwise, the vehicle will so.
     *
     * @return true if the entry is valid. Otherwise, false.
     */
    public boolean hasValidEntryByDay() {
        if (this.getLicensePlate().toUpperCase().matches("\\^".concat("A".toUpperCase()))) {
            Calendar calendar = Calendar.getInstance();
            return Calendar.SUNDAY != calendar.get(Calendar.DAY_OF_WEEK) && Calendar.MONDAY != calendar.get(Calendar.DAY_OF_WEEK);
        }

        return true;
    }
}
