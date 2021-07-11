package com.example.paindiarysecond.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paindiarysecond.databinding.RvLayoutBinding;
import com.example.paindiarysecond.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter
        <RecyclerViewAdapter.ViewHolder> {
    List<Customer> customers= new ArrayList<>();
    //this method will be used by LiveData to keep updating the recyclerview

    public void setData(List<Customer> customers) {
        this.customers = customers;
        notifyDataSetChanged();
    }
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                             int viewType) {
        RvLayoutBinding binding =
                RvLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    // this method binds the view holder created with Room livedata
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int
            position) {
        Customer customer = customers.get(position);
        viewHolder.binding.tvDatabase.setText("uid: " + (Integer.toString(customer.uid))+"   " +  "pain level: " +customer.painIntensityLevel +"\n" + "pain location: "  +customer.painLocation
        + "\n" +  "mood level: "  +customer.moodLevel + "\n" + "steps taken:" + customer.stepsTaken + "\n" +
               "weather:" +customer.temperature + "℃" +" " + customer.humidity + " "+customer.pressure+ "hPa" + "\n" +customer.email + "\n" + customer.dateOfentry);
    }
    @Override
    public int getItemCount() {
        return customers.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private RvLayoutBinding binding;
        public ViewHolder(RvLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
