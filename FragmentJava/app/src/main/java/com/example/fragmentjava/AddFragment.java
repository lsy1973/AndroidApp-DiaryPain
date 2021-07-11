package com.example.fragmentjava;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fragmentjava.databinding.AddFragmentBinding;

public class AddFragment extends Fragment {

        private AddFragmentBinding addBinding;

        public AddFragment(){}
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the View for this fragment
            addBinding = AddFragmentBinding.inflate(inflater, container, false);
            View view = addBinding.getRoot();

            SharedViewModel model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

            addBinding.addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String message = addBinding.editText.getText().toString();
                    if (!message.isEmpty() ) {
//                        SharedPreferences sharedPref= requireActivity().
//                                getSharedPreferences("Message", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor spEditor = sharedPref.edit();
//                        spEditor.putString("message", message);
//                        spEditor.apply();
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

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            addBinding = null;
        }
    }

