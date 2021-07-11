package com.example.paindiarysecond;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.paindiarysecond.databinding.ActivityResetPasswordBinding;
import com.example.paindiarysecond.entity.Customer;
import com.example.paindiarysecond.ui.paindataentry.CustomerViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

public class ResetPasswordActivity extends AppCompatActivity {
    private ActivityResetPasswordBinding binding;
    private DatabaseReference mDatabase;
    private CustomerViewModel customerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResetPasswordBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getWindow().setStatusBarColor(0xff039BE5);
        //UploadCloudData();

        binding.btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ResetPasswordActivity.this, "Reset link has been sent to your email, please check to reset password", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void UploadCloudData() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.setTimeInMillis(System.currentTimeMillis());
        String date = year + "-" + month + "-" + day; //Current Date year-month-day

        customerViewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance(getApplication()).create(CustomerViewModel.class);
        CompletableFuture<Customer> customerCompletableFuture = customerViewModel.findByDateFuture(date);
        customerCompletableFuture.thenApply(customer -> {
            if (customer != null) {
                String uid = String.valueOf(customer.uid);
                        writeNewUserWithTaskListeners(String.valueOf(customer.uid), customer.painIntensityLevel, customer.painLocation,customer.moodLevel,customer.stepsTaken,customer.dateOfentry,customer.temperature,customer.humidity,customer.pressure,customer.email);

                Toast.makeText(ResetPasswordActivity.this, "Update firebase successful！", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(ResetPasswordActivity.this, "Insert successful！", Toast.LENGTH_LONG).show();
            }
            return customer;
        });
    }

    public void writeNewUserWithTaskListeners(String uid,String painIntensityLevel,String painLocation,
                                              String moodLevel,
                                              String stepsTaken,
                                              String dateOfentry,
                                              String temperature,
                                              String humidity,
                                              String pressure,
                                              String email) {
        Customer customer = new Customer(painIntensityLevel,painLocation,
                moodLevel,
                stepsTaken,
                dateOfentry,
                temperature,
                humidity,
                pressure,
                email);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [START rtdb_write_new_user_task]
        mDatabase.child("users").child("user uid - " + uid).setValue(customer)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Write was successful!
                        // ...
                        Toast.makeText(ResetPasswordActivity.this, "successful", Toast.LENGTH_LONG).show();
                        Log.d("database", "successful");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Write failed
                        // ...
                        Toast.makeText(ResetPasswordActivity.this, "failed", Toast.LENGTH_LONG).show();
                        Log.d("database", "failed");
                    }
                });
        // [END rtdb_write_new_user_task]
    }
}
