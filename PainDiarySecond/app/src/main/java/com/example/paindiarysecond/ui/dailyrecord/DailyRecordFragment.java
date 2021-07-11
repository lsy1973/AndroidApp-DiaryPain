package com.example.paindiarysecond.ui.dailyrecord;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.paindiarysecond.adapter.RecyclerViewAdapter;
import com.example.paindiarysecond.databinding.DailyRecordFragmentBinding;
import com.example.paindiarysecond.entity.Customer;
import com.example.paindiarysecond.ui.paindataentry.CustomerViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class DailyRecordFragment extends Fragment {
    private DailyRecordFragmentBinding binding;
    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private CustomerViewModel customerViewModel;
    private WorkManager workManager;
    public static DailyRecordFragment newInstance() {
        return new DailyRecordFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DailyRecordFragmentBinding.inflate(inflater,container,false);
        View root = binding.getRoot();


        customerViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(CustomerViewModel.class);
        adapter = new RecyclerViewAdapter();
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(),LinearLayoutManager.VERTICAL));
        binding.recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this.getContext());
        binding.recyclerView.setLayoutManager(layoutManager);
        customerViewModel.getAllCustomers().observe(getViewLifecycleOwner(), new
                Observer<List<Customer>>() {
                    @Override
                    public void onChanged(@Nullable final List<Customer> customers) {
                        //here we use the adapter to update the data in RecyclerView
                        adapter.setData(customers);
                    }
                });
        UploadDataToCloud();
        return root;
    }

    public void UploadDataToCloud(){
        workManager = WorkManager.getInstance(this.getContext());

        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(UploadWoker.class).build();

        //Every 24 hours, data will be uploaded to cloud.
        PeriodicWorkRequest uploadWorkRequest = new PeriodicWorkRequest.Builder(UploadWoker.class,24,TimeUnit.HOURS,15,TimeUnit.MINUTES)
                .build();
//                PeriodicWorkRequest uploadWorkRequest = new PeriodicWorkRequest.Builder(UploadWoker.class,15,TimeUnit.MINUTES)
//                .build();
        workManager.getInstance().enqueue(oneTimeWorkRequest);

    }
}