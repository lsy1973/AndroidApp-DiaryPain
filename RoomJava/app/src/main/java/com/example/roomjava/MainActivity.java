package com.example.roomjava;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.roomjava.databinding.ActivityMainBinding;
import com.example.roomjava.entity.Customer;
import com.example.roomjava.viewmodel.CustomerViewModel;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private CustomerViewModel customerViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.idTextField.setPlaceholderText("This is only used for Edit");
//we make sure that AndroidViewModelFactory creates the view model so it can
//        accept the Application as the parameter
        customerViewModel =
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(CustomerViewModel.class);
        customerViewModel.getAllCustomers().observe(this, new
                Observer<List<Customer>>() {
                    @Override
                    public void onChanged(@Nullable final List<Customer> customers) {
                        String allCustomers = "";
                        for (Customer temp : customers) {
                            String customerDetails = (temp.uid + " " + temp.painIntensityLevel + " " + temp.painLocation + " " + temp.moodLevel+" "+temp.stepsTaken+ " " +temp.dateOfentry+ " " +temp.temperature
                                    + " " +temp.pressure+ " " +temp.humidity+ " " +temp.email);
                            allCustomers = allCustomers + System.getProperty("line.separator") + customerDetails;
                        }
                        binding.textViewRead.setText("All data: " + allCustomers);
                    }
                });
        binding.addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                String name=
//                        binding.nameTextField.getEditText().getText().toString();
//                String
//                        surname=binding.surnameTextField.getEditText().getText().toString();
//                String strSalary
//                        =binding.salaryTextField.getEditText().getText().toString();
                String painIntensityLevel = "10";
                String painLocation = "knee";
                String moodLevel = "level 5";
                int stepsTaken = 12345;
                String dateOfentry = "2021:05:04";
                double temperature = 87;
                int humidity = 3453;
                int pressure = 324;
                String email = "email@ww.com";



                //if ((!.isEmpty() && name!= null) && (!surname.isEmpty() && strSalary!=null) && (!strSalary.isEmpty() && surname!=null)) {
                if (true) {
                    //double salary = Double.parseDouble(strSalary);
                    Customer customer = new Customer(painIntensityLevel,painLocation,moodLevel,stepsTaken,dateOfentry,temperature,humidity,pressure,email);
                    customerViewModel.insert(customer);
                    //binding.textViewAdd.setText("Added Record: " + name + " " + surname + " " + salary);
                }
            }});
        binding.deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                customerViewModel.deleteAll();
                binding.textViewDelete.setText("All data was deleted");
            }
        });
        binding.clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                binding.nameTextField.getEditText().setText("");
                binding.surnameTextField.getEditText().setText("");
                binding.salaryTextField.getEditText().setText("");
            }
        });
        binding.updateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                String strId
//                        =binding.idTextField.getEditText().getText().toString();
//                int id=0;
//                if (!strId.isEmpty() && strId!= null)
//                    id=Integer.parseInt(strId);
                String Strdate = binding.idTextField.getEditText().getText().toString();
                String date;
                if(!Strdate.isEmpty() && Strdate!=null)
                    date = Strdate;
//                String name=
//                        binding.nameTextField.getEditText().getText().toString();
//                String
//                        surname=binding.surnameTextField.getEditText().getText().toString();
//                String strSalary
//                        =binding.salaryTextField.getEditText().getText().toString();
                String painIntensityLevel = "10";
                String painLocation = "knee";
                String moodLevel = "level 5";
                int stepsTaken = 12345;
                String dateOfentry = "2021:05:04";
                double temperature = 87;
                int humidity = 348673;
                int pressure = 324923;
                String email = "email@ww.com";

                if (true) {
                    //double salary = Double.parseDouble(strSalary);
//this deals with versioning issues
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        CompletableFuture<Customer> customerCompletableFuture =
                                customerViewModel.findByIDFuture(Integer.parseInt(dateOfentry));
                        customerCompletableFuture.thenApply(customer-> {
                        if (customer != null) {
                            customer.painIntensityLevel = painIntensityLevel;
                            customer.painLocation = painLocation;
                            customer.moodLevel = moodLevel;
                            customer.stepsTaken = stepsTaken;
                            customer.dateOfentry = dateOfentry;
                            customer.temperature = temperature;
                            customer.humidity = humidity;
                            customer.pressure = pressure;
                            customer.email = email;
                            customerViewModel.update(customer);
                            binding.textViewUpdate.setText("Update was successful for ID: " + customer.uid);
                        } else {
                            binding.textViewUpdate.setText("Id does not exist");
                        }
                        return customer;
});
                    }
                }}
        });
    }
}