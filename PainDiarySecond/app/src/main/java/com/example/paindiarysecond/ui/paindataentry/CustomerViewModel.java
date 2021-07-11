package com.example.paindiarysecond.ui.paindataentry;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.paindiarysecond.entity.Customer;
import com.example.paindiarysecond.repository.CustomerRepository;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CustomerViewModel extends AndroidViewModel {
    private CustomerRepository cRepository;
    private LiveData<List<Customer>> allCustomers;

    public CustomerViewModel (Application application) {
        super(application);
        cRepository = new CustomerRepository(application);
        allCustomers = cRepository.getAllCustomer();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<Customer> findByIDFuture(final int customerId){
        return cRepository.findByIDFuture(customerId);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<Customer> findByDateFuture(final String customerDate){
        return cRepository.findByDateFuture(customerDate);
    }

    public LiveData<List<Customer>> getAllCustomers() {
        return allCustomers;
    }
    public void insert(Customer customer) {
        cRepository.insert(customer);
    }
    public void deleteAll() {
        cRepository.deleteAll();
    }
    public void update(Customer customer) {
        cRepository.updateCustomer(customer);
    }

}