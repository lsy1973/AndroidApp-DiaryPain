package com.example.paindiarysecond.ui.reports;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.paindiarysecond.databinding.FragmentLocationChartBinding;
import com.example.paindiarysecond.entity.Customer;
import com.example.paindiarysecond.ui.dailyrecord.UploadWoker;
import com.example.paindiarysecond.ui.paindataentry.CustomerViewModel;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LocationChartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class LocationChartFragment extends Fragment {
    private CustomerViewModel customerViewModel;
    private FragmentLocationChartBinding binding;
    private PieChart pieChart;
    List<PieEntry> list;

    public LocationChartFragment() {
    }

    public static void newInstance() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentLocationChartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        pieChart = binding.locationPieChart;
        customerViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(CustomerViewModel.class);
        customerViewModel.getAllCustomers().observe(getViewLifecycleOwner(), new Observer<List<Customer>>() {
            @Override
            public void onChanged(List<Customer> customers) {
                Map<String, Integer> map = getDatalocation(customers);
                //Log.e("map location", map.toString());
                drawChart(map);

            }
        });
        return root;
    }

    public Map<String, Integer> getDatalocation(List<Customer> customers) {
        Map<String, Integer> map = new HashMap<>();

        map.put("back", 0);
        map.put("neck", 0);
        map.put("head", 0);
        map.put("knees", 0);
        map.put("hips", 0);
        map.put("abdomen", 0);
        map.put("elbows", 0);
        map.put("shoulders", 0);
        map.put("shins", 0);
        map.put("jaw", 0);
        map.put("facial", 0);
        for (Customer temp : customers) {
            String s = temp.painLocation;
            map.put(s,map.get(s) + 1);
        }
        //Log.e("maplocation",map.toString());
        return map;
    }
    public void drawChart(Map<String, Integer> map) {
        list = new ArrayList<>();
        List<LegendEntry> entries = new ArrayList<>();
        List<Integer> colors = new ArrayList<>();
        Legend legend = pieChart.getLegend();
        legend.setEnabled(true);
        legend.setWordWrapEnabled(true);
        legend.setXEntrySpace(1000f);

        // Hash map storing Legend Data (numbers,colors)
        UpdateList(entries, map, colors, "back", "#FFCCCC");
        UpdateList(entries, map, colors, "neck", "#CCCCFF");
        UpdateList(entries, map, colors, "head", "#CCCCCC");
        UpdateList(entries, map, colors, "knees", "#99CCFF");
        UpdateList(entries, map, colors, "hips", "#66CCCC");
        UpdateList(entries, map, colors, "abdomen", "#0099CC");
        UpdateList(entries, map, colors, "elbows", "#66CCCC");
        UpdateList(entries, map, colors, "shoulders", "#339999");
        UpdateList(entries, map, colors, "shins", "#FFFF99");
        UpdateList(entries, map, colors, "jaw", "#66CC99");
        UpdateList(entries, map, colors, "facial", "#CCFF66");
        //Log.e("listchart", list.toString());


        PieDataSet pieDataSet = new PieDataSet(list, " ");
        PieData pieData = new PieData(pieDataSet);
        pieData.setDrawValues(true);
        pieData.setValueFormatter(new PercentFormatter(pieChart));
        pieDataSet.setColors(colors);
        pieData.setValueTextColor(Color.WHITE);
        pieData.setValueTextSize(15f);
        pieDataSet.setSliceSpace(1f);
        pieDataSet.setHighlightEnabled(true);

        pieChart.setData(pieData);
        pieChart.setHoleRadius(0f);
        pieChart.setTransparentCircleRadius(0f);
        pieChart.setDrawCenterText(true);
        pieChart.setUsePercentValues(true);
        pieChart.animateY(1000); // 图2
        pieChart.invalidate();

        // Set legend
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setFormToTextSpace(4f);
        legend.setCustom(entries);
        Description description = new Description();
        description.setText("This is a pie chart showing the statistic of pain location");
        description.setTextSize(10f);
        pieChart.setDescription(description);
    }

    private void UpdateList(List<LegendEntry> entries, Map<String, Integer> map, List<Integer> colors, String back, String s) {
        if (map.get(back) != 0) {
            list.add(new PieEntry(map.get(back), back));
            colors.add(Color.parseColor(s));
            entries.add(new LegendEntry(
                    back,
                    Legend.LegendForm.CIRCLE,
                    14f,
                    0f,
                    null,
                    Color.parseColor(s))
            );
        }
    }
}