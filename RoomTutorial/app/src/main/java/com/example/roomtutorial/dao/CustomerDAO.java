package com.example.roomtutorial.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.OnConflictStrategy;
import com.example.roomtutorial.entity.Customer;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface CustomerDAO {

    @Query("SELECT * FROM customer")
    LiveData<List<Customer>> getAll();

    @Query("SELECT * FROM customer WHERE uid = :customerId LIMIT 1")
    Customer findByID(int customerId);

    @Insert
    void insertAll(Customer... customers);

    @Insert
    long insert(Customer customer);

    @Delete
    void delete(Customer customer);

    @Update(onConflict = REPLACE)
    void updateCustomers(Customer... customers);

    @Query("DELETE FROM customer") void deleteAll();

    @Query("UPDATE customer SET first_name=:firstName,last_name=:lastName, salary=:salary WHERE uid = :id")
    void updatebyID(int id, String firstName, String lastName, double salary);
}
