# Car Rent Agency

## Overview

The Car Rent Agency project is a Java application designed to handle car rentals.
It includes classes for representing cars, dates, and rental agreements, providing functionality for managing and processing rental transactions.

## Classes

### `Car`

Represents a car available for rent.

- **Attributes:**
  - `int _id`: Unique identifier for the car.
  - `char _type`: Type of the car (e.g., 'A', 'B', 'C', 'D').
  - `String _brand`: Brand of the car.
  - `boolean _isManual`: Indicates if the car is manual.

- **Key Methods:**
  - `Car(int id, char type, String brand, boolean isManual)`: Constructor to initialize a new car object.
  - `boolean equals(Car other)`: Checks if two cars are identical.
  - `boolean better(Car other)`: Determines if the current car is better than another based on type and transmission.
  - `String toString()`: Provides a string representation of the car.

### `Date`

Represents a specific date.

- **Attributes:**
  - `int _day`: Day of the month.
  - `int _month`: Month of the year.
  - `int _year`: Year.

- **Key Methods:**
  - `Date(int day, int month, int year)`: Constructor to initialize a new date object.
  - `boolean equals(Date other)`: Checks if two dates are the same.
  - `boolean before(Date other)`: Checks if the current date is before another date.
  - `boolean after(Date other)`: Checks if the current date is after another date.
  - `int difference(Date other)`: Calculates the difference in days between two dates.
  - `Date tomorrow()`: Returns a new Date object for the next day.

### `Rent`

Represents a rental agreement.

- **Attributes:**
  - `String _name`: Client's name.
  - `Car _car`: The rented car.
  - `Date _pickDate`: Pickup date.
  - `Date _returnDate`: Return date.

- **Key Methods:**
  - `Rent(String name, Car car, Date pick, Date ret)`: Constructor to initialize a new rental agreement.
  - `boolean equals(Rent other)`: Checks if two rental agreements are the same.
  - `int howManyDays()`: Returns the number of days for the rental.
  - `int getPrice()`: Calculates the total price for the rental.
  - `int upgrade(Car newCar)`: Upgrades the car if the new car is better and returns the additional cost.
  - `Rent overlap(Rent other)`: Checks if there is an overlap with another rental and returns a new rental with unified dates if there is an overlap.
  - `String toString()`: Provides a string representation of the rental.
