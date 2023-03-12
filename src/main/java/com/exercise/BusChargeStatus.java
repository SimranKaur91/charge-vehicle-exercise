package com.exercise;

public class BusChargeStatus implements VehicleChargeStatus{
    private final VehicleType vehicleType;
    private final String time;


    public BusChargeStatus(final VehicleType vehicleType, final String time) {
        this.vehicleType = vehicleType;
        this.time = time;
    }

    @Override
    public int getChargeStatus(int percentageOfCharge) {
        final int splitIndex = time.indexOf(":");
        final String hour = time.substring(0, splitIndex);
        final int minutes = Integer.parseInt(time.substring(splitIndex + 1));
        final int hours = Integer.parseInt(hour.equals("00") ? "24" : hour);

        switch (vehicleType) {
            case SCHOOL_BUS:
                if (hours < 8) {
                    return 90;
                } else if (hours <= 11) {
                    return 50;
                } else if ((hours >= 18 && hours <= 24) && (percentageOfCharge > 50)) {
                    return 30;
                }
                break;
            case COMMUTER_BUS:
                if (hours >= 3 && hours <= 7) {
                    return 95;
                } else if ((hours == 23 && minutes >= 15) || hours > 23 || (hours >= 1 && hours < 3)) {
                    return 30;
                }
                break;
        }

        final String exceptionMessage = "One of the arguments is not valid: Percentage of Charge, Vehicle Type, or Time";
        throw new IllegalArgumentException(exceptionMessage);
    }
}
