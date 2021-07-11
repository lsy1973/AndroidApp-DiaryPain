package com.example.roomjava.repository;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.example.roomjava.dao.CustomerDAO;
import com.example.roomjava.database.CustomerDatabase;
import com.example.roomjava.entity.Customer;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class CustomerRepository {
    private CustomerDAO customerDao;
    private LiveData<List<Customer>> allCustomers;
    public CustomerRepository(Application application){
        CustomerDatabase db = CustomerDatabase.getInstance(application);
        customerDao =db.customerDao();
        allCustomers= customerDao.getAll();
    }
    // Room executes this query on a separate thread
    public LiveData<List<Customer>> getAllCustomers() {
        return allCustomers;
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
}
