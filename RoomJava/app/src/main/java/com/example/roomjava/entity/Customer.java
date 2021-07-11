package com.example.roomjava.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Customer {
        @PrimaryKey(autoGenerate = true)
        public int uid;
        @ColumnInfo(name = "pain_intensity_level")
        @NonNull
        public String painIntensityLevel;
        @ColumnInfo(name = "pain_location")
        @NonNull
        public String painLocation;

        public String moodLevel;
        public int stepsTaken;
        public String dateOfentry;
        public double temperature;
        public int humidity;
        public int pressure;
        public String email;

        public Customer(@NonNull String painIntensityLevel, @NonNull String painLocation, String moodLevel, int stepsTaken, String dateOfentry,
                        double temperature,int humidity, int pressure, String email) {
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
}
