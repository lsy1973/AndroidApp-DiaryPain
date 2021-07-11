package com.example.paindiarysecond.repository;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.example.paindiarysecond.dao.CustomerDAO;
import com.example.paindiarysecond.database.CustomerDatabase;
import com.example.paindiarysecond.entity.Customer;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class CustomerRepository {
    private CustomerDAO customerDao;
    private LiveData<List<Customer>> allCustomer;


    public CustomerRepository(Application application){
        CustomerDatabase db = CustomerDatabase.getInstance(application);
        customerDao = db.customerDao();
        allCustomer = customerDao.getAll();
    }

    public LiveData<List<Customer>> getAllCustomer(){
        return allCustomer;
    }

    public void insert(final Customer customer){
        CustomerDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                customerDao.insert(customer);
            }
        });
    }
    public void deleteAll(){
        CustomerDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                customerDao.deleteAll();
            }
        });
    }
    public void delete(final Customer customer){
        CustomerDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                customerDao.delete(customer);
            }
        });
    }
    public void updateCustomer(final Customer customer){
        CustomerDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                customerDao.updateCustomer(customer);
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<Customer> findByIDFuture(final int customerId) {
        return CompletableFuture.supplyAsync(new Supplier<Customer>() {
            @Override
            public Customer get() {
                return customerDao.findByID(customerId);
            }
        }, CustomerDatabase.databaseWriteExecutor);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<Customer> findByDateFuture(final String customerDate) {
        return CompletableFuture.supplyAsync(new Supplier<Customer>() {
            @Override
            public Customer get() {
                return customerDao.findByDate(customerDate);
            }
        }, CustomerDatabase.databaseWriteExecutor);
    }






}
