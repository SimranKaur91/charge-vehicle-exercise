package com.exercise;

public class VehicleChargeFactory {


    String getChargeStatus(final int percentageOfCharge, VehicleType vehicle) {
        return getChargeStatus(percentageOfCharge, vehicle, "");
    }

    String getChargeStatus(final int percentageOfCharge, VehicleType vehicle, String time) {
        final VehicleChargeStatus vehicleChargeStatus;
        if (percentageOfCharge > 100 || percentageOfCharge < 0){
            throw new IllegalArgumentException("Invalid charge percentage of a vehicle");
        }

        if (vehicle.equals(VehicleType.OTHER_EV)) {
            vehicleChargeStatus = new OtherEvChargeStatus();
        } else {
            vehicleChargeStatus = new BusChargeStatus(vehicle, time);
        }

        return String.format("%s%s", vehicleChargeStatus.getChargeStatus(percentageOfCharge), "%");
    }
}
