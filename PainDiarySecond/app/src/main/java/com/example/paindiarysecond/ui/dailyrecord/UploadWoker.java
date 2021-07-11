package com.example.paindiarysecond.ui.dailyrecord;

import android.app.Application;
import android.content.Context;
import android.icu.util.Calendar;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.paindiarysecond.ResetPasswordActivity;
import com.example.paindiarysecond.entity.Customer;
import com.example.paindiarysecond.ui.paindataentry.CustomerViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class UploadWoker extends Worker {

    CustomerViewModel customerViewModel;
    private DatabaseReference mDatabase;
    public UploadWoker(@NonNull @NotNull Context context, @NonNull @NotNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @NotNull
    @Override
    public Result doWork() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.setTimeInMillis(System.currentTimeMillis());
        String date = year + "-" + month + "-" + day; //Current Date year-month-day


        customerViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance((Application) getApplicationContext()).create(CustomerViewModel.class);


        CompletableFuture<Customer> customerCompletableFuture = customerViewModel.findByDateFuture(date);
        customerCompletableFuture.thenApply(customer -> {
            if (customer != null) {
                String uid = String.valueOf(customer.uid);
                writeNewUserWithTaskListeners(String.valueOf(customer.uid), customer.painIntensityLevel, customer.painLocation,customer.moodLevel,customer.stepsTaken,customer.dateOfentry,customer.temperature,customer.humidity,customer.pressure,customer.email);

            } else {

            }
            return customer;
        });


        Log.d("upload worker","upload work started!");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("upload worker","upload work finished!");
        return Result.success();
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

                        Log.d("database", "successful");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Write failed
                        // ...
//                        Toast.makeText(ResetPasswordActivity.this, "failed", Toast.LENGTH_LONG).show();
                        Log.d("database", "failed");
                    }
                });
        // [END rtdb_write_new_user_task]
    }
}
