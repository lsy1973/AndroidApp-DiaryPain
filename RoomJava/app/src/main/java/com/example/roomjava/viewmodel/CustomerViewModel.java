package com.example.roomjava.viewmodel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomjava.entity.Customer;
import com.example.roomjava.repository.CustomerRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CustomerViewModel extends AndroidViewModel {
    private CustomerRepository cRepository;
    private LiveData<List<Customer>> allCustomers;
    public CustomerViewModel (Application application) {
        super(application);
        cRepository = new CustomerRepository(application);
        allCustomers = cRepository.getAllCustomers();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<Customer> findByIDFuture(final int customerId){
        return cRepository.findByIDFuture(customerId);
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
