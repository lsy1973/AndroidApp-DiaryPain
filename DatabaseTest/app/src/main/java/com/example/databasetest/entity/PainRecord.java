package com.example.databasetest.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class PainRecord {
    @PrimaryKey (autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "pain_intensity_level")
    @NonNull
    public String painIntensityLevel;

    @ColumnInfo(name = "pain_location")
    @NonNull
    public String painLocation;

    @ColumnInfo(name = "mood_level")
    @NonNull
    public String moodLevel;

    @ColumnInfo(name = "steps_taken")
    @NonNull
    public int stepsTaken;

    @ColumnInfo(name = "date_of_entry")
    @NonNull
    public String dateOfentry;

    @ColumnInfo(name = "temperature")
    @NonNull
    public double temperature;

    @ColumnInfo(name = "humidity")
    @NonNull
    public int humidity;

    @ColumnInfo(name = "pressure")
    @NonNull
    public int pressure;

    @ColumnInfo(name = "email")
    @NonNull
    public String email;

    public PainRecord( @NonNull String painIntensityLevel,
                       @NonNull String painLocation,
                       @NonNull String moodLevel,
                       @NonNull int stepsTaken,
                       @NonNull String dateOfentry,
                       @NonNull double temperature,
                       @NonNull int humidity,
                       @NonNull int pressure,
                       @NonNull String email
                       ){
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
