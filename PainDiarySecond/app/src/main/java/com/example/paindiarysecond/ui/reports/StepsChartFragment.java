package com.example.paindiarysecond.ui.reports;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.paindiarysecond.dao.CustomerDAO;
import com.example.paindiarysecond.database.CustomerDatabase;

import com.example.paindiarysecond.entity.Customer;
import com.example.paindiarysecond.ui.paindataentry.CustomerViewModel;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class StepsChartFragment extends Fragment {
    private com.example.paindiarysecond.databinding.FragmentStepsChartBinding binding;
    private PieChart pieChart;
    List<PieEntry> list;
    private CustomerViewModel customerViewModel;

    private CustomerDAO customerDao;
    private CustomerDatabase db;
    public StepsChartFragment() {
        // Required empty public constructor
    }

    public static StepsChartFragment newInstance() {
        StepsChartFragment fragment = new StepsChartFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = com.example.paindiarysecond.databinding.FragmentStepsChartBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        pieChart = binding.pieChart;
        drawChart();
        return root;
    }

    public void drawChart(){
        List<LegendEntry> entries = new ArrayList<>();
        SharedPreferences sharedStepsGoal = requireActivity().getSharedPreferences("StepsGoal", Context.MODE_PRIVATE);
        String stepsGoal = sharedStepsGoal.getString("StepsGoal",null);
        String stepsTaken = sharedStepsGoal.getString("StepsTaken",null);
        //Log.e("StepsGoal",stepsGoal);
        list = new ArrayList<>();

        list.add(new PieEntry (Integer.parseInt(stepsTaken),"Steps Taken"));
        list.add(new PieEntry (Integer.parseInt(stepsGoal) - Integer.parseInt(stepsTaken),"Steps Remaining"));

        PieDataSet pieDataSet=new PieDataSet(list," ");
        PieData pieData=new PieData(pieDataSet);
        pieData.setDrawValues(true);
        pieData.setValueTextColor(Color.WHITE);
        pieData.setValueTextSize(12f);
        pieDataSet.setSliceSpace(1f);
        pieDataSet.setHighlightEnabled(true);
        pieDataSet.setColors(Color.parseColor("#FF8C00"),Color.parseColor("#808080"));//设置各个数据的颜色

        pieChart.setData(pieData);
        pieChart.setDrawCenterText(false);
        pieChart.setHoleRadius(60f);
        pieChart.setTransparentCircleRadius(0f);
        pieChart.animateY(1000); // 图2
        pieChart.invalidate();
        Legend legend = pieChart.getLegend();
        legend.setEnabled(true);
        legend.setWordWrapEnabled(true);
        entries.add(new LegendEntry(
                "Steps Taken",
                Legend.LegendForm.CIRCLE,
                14f,
                9f,
                null,
                Color.parseColor("#FF8C00"))
        );
        entries.add(new LegendEntry(
                "Steps Remaining",
                Legend.LegendForm.CIRCLE,
                14f,
                0f,
                null,
                Color.parseColor("#808080"))
        );
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setCustom(entries);
        Description description = new Description();
        description.setText("This is a pie chart showing steps taken and remaining steps.");
        description.setTextSize(10f);
        pieChart.setDescription(description);
    }
}