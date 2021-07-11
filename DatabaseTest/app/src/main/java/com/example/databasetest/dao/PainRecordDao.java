package com.example.databasetest.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.databasetest.entity.PainRecord;

import java.util.List;

@Dao
public interface PainRecordDao {

    @Query("SELECT * FROM painrecord ORDER BY date_of_entry ASC")
    LiveData<List<PainRecord>> getAll();
    @Query("SELECT * FROM painrecord WHERE uid = :painRecordId")
    PainRecord findByID(int painRecordId);

    @Insert
    void insert(PainRecord painRecord);
    @Delete
    void delete(PainRecord painRecord);
    @Update
    void updateCustomer(PainRecord painRecord);
    @Query("DELETE FROM painrecord")
    void deleteAll();
}
