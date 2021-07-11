package com.example.paindiaryaa.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.paindiaryaa.databinding.AddFragmentBinding;
import com.example.paindiaryaa.viewmodel.SharedViewModel;

public class AddFragment extends Fragment {
    private AddFragmentBinding addBinding;

    public AddFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the View for this fragment
        addBinding = AddFragmentBinding.inflate(inflater, container, false);
        View view = addBinding.getRoot();
        SharedViewModel model = new
                ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        addBinding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = addBinding.editText.getText().toString();
                if (!message.isEmpty()) {
                    model.setMessage(message);
                }
            }
        });
        addBinding.clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBinding.editText.setText("");
            }
        });
        return view;
    }

    public void onDestroyView() {
        super.onDestroyView();
        addBinding = null;
    }
}
