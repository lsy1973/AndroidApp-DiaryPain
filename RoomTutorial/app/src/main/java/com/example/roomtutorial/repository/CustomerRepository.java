package com.example.roomtutorial.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.roomtutorial.dao.CustomerDAO;
import com.example.roomtutorial.database.CustomerDatabase;
import com.example.roomtutorial.entity.Customer;

import java.util.List;

public class CustomerRepository {
    private CustomerDAO dao;
    private LiveData<List<Customer>> allCustomers;
    private Customer customer;

    public CustomerRepository(Application application) {
        CustomerDatabase db = CustomerDatabase.getInstance(application);
        dao = db.customerDao();
    }

    public LiveData<List<Customer>> getAllCustmers() {
        allCustomers = dao.getAll();
        return allCustomers;
    }

    public void insert(final Customer customer) {
        CustomerDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.insert(customer);
            }
        });
    }

    public void deleteAll() {
        CustomerDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.deleteAll();
            }
        });
    }

    public void delete(final Customer customer) {
        CustomerDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.delete(customer);
            }
        });
    }

    public void insertAll(final Customer... customers) {
        CustomerDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.insertAll(customers);
            }
        });
    }

    public void updateCustomers(final Customer... customers) {
        CustomerDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.updateCustomers(customers);
            }
        });
    }

    public void updateCustomerByID(final int customerId, final String firstName,
                                   final String lastName, final double salary) {
        CustomerDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.updatebyID(customerId, firstName, lastName, salary);
            }
        });
    }
    public Customer findByID ( final int customerId){
        CustomerDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Customer runCustomer = dao.findByID(customerId);
                setCustomer(runCustomer);
            }
        });
        return customer;
    }
    public void setCustomer (Customer customer){
        this.customer = customer;
    }
}

