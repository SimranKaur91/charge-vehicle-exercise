# EV charging exercise

<p>
Decides how much an EV should be charged or discharged, given the time of day, EV type and current charge

1.  Any EV connected with less than 50% charge is automatically charged to 80%; any EV with 50-60 % is charged to 70% any EV above 60% is discharged to 50%
2. If a school bus is connected before 8am make sure to charge it to 90%; if it is connected after 8am but before 11am discharge to 50%, if the charge is above 50% and if the school bus is connected after 6pm and before 12am discharge to 30%.
3. If a commuter bus is connected between 3am and 7am charge up to 95%; if connected after 11:15pm then discharge to 30%.

</p>


## How to run it
1. Build the project 
`mvn clean compile`

2. Run the project from terminal
` mvn install`

3. Run the project from IDE 
- Navigate to `com/exercise/VehicleChargeFactoryTest.java`
- Run all the junit tests
