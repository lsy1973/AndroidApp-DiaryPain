package com.example.paindiarysecond.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

//@Entity(tableName = "PainRecord",indices = {@Index(value = "date",unique = true)})
@Entity(tableName = "PainRecord")
public class Customer {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "pain level")
    public String painIntensityLevel;
    @ColumnInfo(name = "location")
    public String painLocation;
    @ColumnInfo(name = "mood")
    public String moodLevel;
    @ColumnInfo(name = "steps")
    public String stepsTaken;

    @ColumnInfo(name = "date")
    public String dateOfentry;

    public String temperature;
    public String humidity;
    public String pressure;
    public String email;

    public Customer(String painIntensityLevel,String painLocation,
            String moodLevel,
                    String stepsTaken,
                    String dateOfentry,
                    String temperature,
                    String humidity,
                    String pressure,
            String email){
        this.painIntensityLevel = painIntensityLevel;
        this.painLocation = painLocation;
        this.moodLevel = moodLevel;
        this.stepsTaken = stepsTaken;
        this.dateOfentry = dateOfentry;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.email = email;
    }

    public String getPainIntensityLevel() {
        return painIntensityLevel;
    }

    public void setPainIntensityLevel(String painIntensityLevel) {
        this.painIntensityLevel = painIntensityLevel;
    }

    public String getPainLocation() {
        return painLocation;
    }

    public void setPainLocation(String painLocation) {
        this.painLocation = painLocation;
    }

    public String getMoodLevel() {
        return moodLevel;
    }

    public void setMoodLevel(String moodLevel) {
        this.moodLevel = moodLevel;
    }

    public String getStepsTaken() {
        return stepsTaken;
    }

    public void setStepsTaken(String stepsTaken) {
        this.stepsTaken = stepsTaken;
    }

    public String getDateOfentry() {
        return dateOfentry;
    }

    public void setDateOfentry(String dateOfentry) {
        this.dateOfentry = dateOfentry;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
