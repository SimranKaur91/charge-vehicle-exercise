package com.exercise;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleChargeFactoryTest {
    private final String chargeMismatchMessage = "Charge status is not matched";

    @Test
    @DisplayName("When charging of an EV is less than 50% then charge to 80%")
    void whenChargingIsLessThan50ThenChargeTo80() {
        final VehicleChargeFactory chargeFactory = new VehicleChargeFactory();

        assertEquals("80%", chargeFactory.getChargeStatus(10, VehicleType.OTHER_EV), chargeMismatchMessage);
        assertEquals("80%", chargeFactory.getChargeStatus(0, VehicleType.OTHER_EV), chargeMismatchMessage);
    }

    @Test
    @DisplayName("When charging of an EV is less than 0% or more than 100 then throws an exception")
    void whenChargingIsLessThan0AndMoreThan100ThenThrowsException() {
        final VehicleChargeFactory chargeFactory = new VehicleChargeFactory();

        assertThrows(IllegalArgumentException.class, () -> chargeFactory.getChargeStatus(-1, VehicleType.OTHER_EV), chargeMismatchMessage);
        assertThrows(IllegalArgumentException.class, () -> chargeFactory.getChargeStatus(101, VehicleType.OTHER_EV), chargeMismatchMessage);
    }


    @Test
    @DisplayName("When charging of an EV is between 50% - 60%  then charge to 70%")
    void whenChargingIsBetween50To60ThenChargeTo70() {
        final VehicleChargeFactory chargeFactory = new VehicleChargeFactory();

        assertEquals("70%", chargeFactory.getChargeStatus(50, VehicleType.OTHER_EV), chargeMismatchMessage);
        assertEquals("70%", chargeFactory.getChargeStatus(55, VehicleType.OTHER_EV), chargeMismatchMessage);
        assertEquals("70%", chargeFactory.getChargeStatus(60, VehicleType.OTHER_EV), chargeMismatchMessage);
    }

    @Test
    @DisplayName("When charging of an EV is greater than 60% then discharge to 50%")
    void whenChargingIsGreaterThan60ThenDischargeTo50() {
        final VehicleChargeFactory chargeFactory = new VehicleChargeFactory();

        assertEquals("50%", chargeFactory.getChargeStatus(61, VehicleType.OTHER_EV), chargeMismatchMessage);
        assertEquals("50%", chargeFactory.getChargeStatus(99, VehicleType.OTHER_EV), chargeMismatchMessage);
        assertEquals("50%", chargeFactory.getChargeStatus(100, VehicleType.OTHER_EV), chargeMismatchMessage);
    }

    @Test
    @DisplayName("When school bus connects to charging point before 8Am then charge to 90%")
    void whenSchoolBusChargesBefore8ThenChargeTo90() {
        final int existingChargePercentage = 10;
        final VehicleChargeFactory chargeFactory = new VehicleChargeFactory();

        assertEquals("90%", chargeFactory.getChargeStatus(existingChargePercentage, VehicleType.SCHOOL_BUS, "7:59"));
        assertEquals("90%", chargeFactory.getChargeStatus(existingChargePercentage, VehicleType.SCHOOL_BUS, "04:00"));
    }

    @Test
    @DisplayName("When school bus connects to charging point between 8Am to 11Am then charge to 50%")
    void whenSchoolBusChargesBetween8To11ThenDischargeTo50() {
        final int existingChargePercentage = 10;
        final VehicleChargeFactory chargeFactory = new VehicleChargeFactory();

        assertEquals("50%", chargeFactory.getChargeStatus(existingChargePercentage, VehicleType.SCHOOL_BUS, "08:00"));
        assertEquals("50%", chargeFactory.getChargeStatus(existingChargePercentage, VehicleType.SCHOOL_BUS, "08:01"));
        assertEquals("50%", chargeFactory.getChargeStatus(existingChargePercentage, VehicleType.SCHOOL_BUS, "09:30"));
        assertEquals("50%", chargeFactory.getChargeStatus(existingChargePercentage, VehicleType.SCHOOL_BUS, "10:59"));
        assertEquals("50%", chargeFactory.getChargeStatus(existingChargePercentage, VehicleType.SCHOOL_BUS, "11:00"));
    }

    @Test
    @DisplayName("When school bus connects to charging point between 6Pm to 11Am and battery charge is more than 50% then discharge to 30%")
    void whenSchoolBusChargesBetween6PmTo12AmAndChargeIsMoreThan50ThenDischargeTo30() {
        final int existingChargePercentage = 55;
        final VehicleChargeFactory chargeFactory = new VehicleChargeFactory();

        assertAll(
                () -> assertEquals("30%", chargeFactory.getChargeStatus(existingChargePercentage, VehicleType.SCHOOL_BUS, "18:00")),
                () -> assertEquals("30%", chargeFactory.getChargeStatus(existingChargePercentage, VehicleType.SCHOOL_BUS, "18:01")),
                () -> assertEquals("30%", chargeFactory.getChargeStatus(existingChargePercentage, VehicleType.SCHOOL_BUS, "19:30")),
                () -> assertEquals("30%", chargeFactory.getChargeStatus(existingChargePercentage, VehicleType.SCHOOL_BUS, "23:59")),
                () -> assertEquals("30%", chargeFactory.getChargeStatus(existingChargePercentage, VehicleType.SCHOOL_BUS, "00:00"))
        );

    }

    @Test
    @DisplayName("When school bus connects to charging point between 6Pm to 11Am and battery charge is less than 50% then throws exception")
    void whenSchoolBusChargesBetween6PmTo12AmAndChargeIsLessThan50ThenThrowsException() {
        final int existingChargePercentage = 49;
        final VehicleChargeFactory chargeFactory = new VehicleChargeFactory();

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> chargeFactory.getChargeStatus(existingChargePercentage, VehicleType.SCHOOL_BUS, "18:00")),
                () -> assertThrows(IllegalArgumentException.class, () -> chargeFactory.getChargeStatus(existingChargePercentage, VehicleType.SCHOOL_BUS, "00:00"))
        );

    }

    @Test
    @DisplayName("When commuters bus connects to charging point between 3Am to 7Am then charge to 95%")
    void whenCommuterBusChargesBetween3AmTo7AmThenChargeTo95() {
        final int existingChargePercentage = 10;
        final VehicleChargeFactory chargeFactory = new VehicleChargeFactory();

        assertAll(
                () -> assertEquals("95%", chargeFactory.getChargeStatus(existingChargePercentage, VehicleType.COMMUTER_BUS, "3:00")),
                () -> assertEquals("95%", chargeFactory.getChargeStatus(existingChargePercentage, VehicleType.COMMUTER_BUS, "03:01")),
                () -> assertEquals("95%", chargeFactory.getChargeStatus(existingChargePercentage, VehicleType.COMMUTER_BUS, "06:59")),
                () -> assertEquals("95%", chargeFactory.getChargeStatus(existingChargePercentage, VehicleType.COMMUTER_BUS, "07:00"))
        );
    }

    @Test
    @DisplayName("When commuters bus connects to charging point after 11:15Pm then charge to 30%")
    void whenCommuterBusChargesAfterQuarterPast11PmThenChargeTo95() {
        final int existingChargePercentage = 10;
        final VehicleChargeFactory chargeFactory = new VehicleChargeFactory();

        assertAll(
                () -> assertEquals("30%", chargeFactory.getChargeStatus(existingChargePercentage, VehicleType.COMMUTER_BUS, "23:15")),
                () -> assertEquals("30%", chargeFactory.getChargeStatus(existingChargePercentage, VehicleType.COMMUTER_BUS, "23:20")),
                () -> assertEquals("30%", chargeFactory.getChargeStatus(existingChargePercentage, VehicleType.COMMUTER_BUS, "00:00")),
                () -> assertEquals("30%", chargeFactory.getChargeStatus(existingChargePercentage, VehicleType.COMMUTER_BUS, "00:59")),
                () -> assertEquals("30%", chargeFactory.getChargeStatus(existingChargePercentage, VehicleType.COMMUTER_BUS, "02:59"))
        );
    }
}