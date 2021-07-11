package com.example.paindiarysecond.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.paindiarysecond.dao.CustomerDAO;
import com.example.paindiarysecond.entity.Customer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Customer.class}, version = 4, exportSchema = false)
public abstract class CustomerDatabase extends RoomDatabase {
    public abstract CustomerDAO customerDao();

    private static CustomerDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public static synchronized CustomerDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CustomerDatabase.class, "CustomerDatabase").fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }
}
