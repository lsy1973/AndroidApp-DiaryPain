package com.example.paindiarysecond.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.paindiarysecond.entity.Customer;

import java.time.temporal.ChronoUnit;
import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;


@Dao
public interface CustomerDAO {
    @Query("SELECT * FROM painrecord")
    LiveData<List<Customer>> getAll();

    @Query("SELECT * FROM painrecord WHERE uid = :customerId LIMIT 1")
    Customer findByID(int customerId);

    @Query("SELECT * FROM painrecord WHERE date = :customerDate LIMIT 1")
    Customer findByDate(String customerDate);



    @Insert
    void insertAll(Customer... customers);

    @Insert
    long insert(Customer customer);

    @Delete
    void delete(Customer customer);

    @Update(onConflict = REPLACE)
    void updateCustomer(Customer... customers);

    @Query("DELETE FROM painrecord")
    void deleteAll();

}
