package com.exercise;

public class OtherEvChargeStatus implements VehicleChargeStatus{

    @Override
    public int getChargeStatus(int percentageOfCharge) {
        if (percentageOfCharge < 50) {
            return 80;
        } else if (percentageOfCharge <= 60) {
            return 70;
        } else {
            return 50;
        }
    }
}
