package com.example.roomtutorial.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Customer { @PrimaryKey(autoGenerate = true) public int uid;

    @ColumnInfo(name = "first_name") public String firstName;

    @ColumnInfo(name = "last_name") public String lastName;

    @ColumnInfo(name = "salary") public double salary;

    public Customer(String firstName, String lastName, double salary) {
        this.firstName=firstName; this.lastName=lastName; this.salary = salary;
    }

    public int getId() {
        return uid;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary (double salary) {
        this.salary = salary;
    }
}

