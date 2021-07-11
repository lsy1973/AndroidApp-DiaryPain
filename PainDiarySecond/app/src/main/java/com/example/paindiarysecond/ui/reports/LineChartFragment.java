package com.example.paindiarysecond.ui.reports;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.paindiarysecond.R;

import com.example.paindiarysecond.databinding.FragmentLineChartBinding;
import com.example.paindiarysecond.entity.Customer;
import com.example.paindiarysecond.ui.paindataentry.CustomerViewModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.listener.BarLineChartTouchListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LineChartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LineChartFragment extends Fragment {
    private FragmentLineChartBinding binding;
    private CustomerViewModel customerViewModel;
    private LineChart chart;

    List<String> getTemperature = new ArrayList<>();
    List<String> getHumidity = new ArrayList<>();
    List<String> getPressure = new ArrayList<>();
    List<String> getDate = new ArrayList<>();
    List<String> getPainlevel = new ArrayList<>();

    int year, month, day;

    public LineChartFragment() {

    }

    public static LineChartFragment newInstance(String param1, String param2) {
        LineChartFragment fragment = new LineChartFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDate();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLineChartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        initFlag();
        // init viewmodel
        customerViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(CustomerViewModel.class);
        binding.btnTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickData();
                showChart(getDate, getPainlevel, getTemperature, "Temperature");
            }
        });

        binding.btnHumidity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickData();
                showChart(getDate, getPainlevel, getHumidity, "Humidity");
            }
        });
        binding.btnPressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickData();
                showChart(getDate, getPainlevel, getPressure, "Pressure");
            }
        });

        binding.btnStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Click Start button to pick start date
                StartdatePicker();
            }
        });

        binding.btnEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Click End button to pick end date
                EnddatePicker();
            }
        });
        binding.btnCorrelation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = binding.tvCorrelation.getCurrentTextColor();
                switch (a){
                    case 0 :
                        binding.tvCorrelation.setTextColor(Color.argb(255, 0, 0, 0));
                        break;
                    case -16777216 :
                        binding.tvCorrelation.setTextColor(Color.argb(0, 0, 0, 0));
                        break;
                }
//                binding.tvCorrelation.getCurrentTextColor();
//                Log.d("color", String.valueOf(binding.tvCorrelation.getCurrentTextColor()));
//                binding.tvCorrelation.setTextColor(Color.argb(255, 0, 0, 0));
//                Log.d("color", String.valueOf(binding.tvCorrelation.getCurrentTextColor()));
            }
        });
        return root;
    }

    private void initFlag() {
        binding.btnHumidity.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        binding.btnPressure.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        binding.btnTemperature.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        binding.btnEndDate.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        binding.btnStartDate.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        binding.btnCorrelation.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        //set textview transparency
        binding.tvCorrelation.setTextColor(Color.argb(0, 0, 0, 0));
        //binding.tvCorrelation.setTextColor(Color.argb(255, 0, 0, 0));
    }


    private void PickData() {
        int startUid = getUid(binding.tvStartDate.getText().toString());
        int endUid = getUid(binding.tvEndDate.getText().toString());

        Log.d("uid chosen", startUid + " " + endUid);
        // get Data to draw chart (date, temp, humidity...)
        getDate.clear();
        getHumidity.clear();
        getPainlevel.clear();
        getPressure.clear();
        getTemperature.clear();
        for (int i = startUid; i <= endUid; i++) {
            CompletableFuture<Customer> customerCompletableFuture = customerViewModel.findByIDFuture(i);
            try {
                getTemperature.add(customerCompletableFuture.get().temperature);
                getHumidity.add(customerCompletableFuture.get().humidity.substring(0, 2));
                getPressure.add(customerCompletableFuture.get().pressure);
                getDate.add((customerCompletableFuture.get().dateOfentry));
                getPainlevel.add((customerCompletableFuture.get().painIntensityLevel));
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void initDate() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public int getUid(String date) {
        int uid = 0;
        CompletableFuture<Customer> customerCompletableFuture = customerViewModel.findByDateFuture(date);
        try {
            uid = customerCompletableFuture.get().uid;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return uid;
    }

    public void StartdatePicker() {
        new DatePickerDialog(getContext(), DatePickerDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int yearGet, int monthGet, int dayGet) {
                monthGet++;
                String date1 = yearGet + "-" + monthGet + "-" + dayGet;
                Toast.makeText(getContext(), "Start Date has been set to: " + date1, Toast.LENGTH_LONG).show();
                binding.tvStartDate.setText(date1);
                Log.d("date1", date1);
            }
        }, year, month, day).show();
    }

    public void EnddatePicker() {
        new DatePickerDialog(getContext(), DatePickerDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int yearGet, int monthGet, int dayGet) {
                monthGet++;
                String date2 = yearGet + "-" + monthGet + "-" + dayGet;
                Toast.makeText(getContext(), "End Date has been set to: " + date2, Toast.LENGTH_LONG).show();
                binding.tvEndDate.setText(date2);
            }
        }, year, month, day).show();
    }

    public void showChart(List<String> getDate, List<String> getPainlevel, List<String> getTemperature, String rightType) {
        chart = binding.lineChart;
        binding.tvCorrelation.setTextColor(Color.argb(0, 0, 0, 0));
        List<Entry> yDataLeft = new ArrayList<>();
        List<Entry> yDataRight = new ArrayList<>();
        List<String> xDataList;

        xDataList = getDate;

        int n = getPainlevel.size();
        double arr [][] = new double[n][2];
        for (int i = 0; i < n; i++) {
            yDataLeft.add(new Entry(i, Integer.parseInt(getPainlevel.get(i))));
            yDataRight.add(new Entry(i, Integer.parseInt(getTemperature.get(i))));
            arr[i][0] = Integer.parseInt(getPainlevel.get(i));
            arr[i][1] = Integer.parseInt(getTemperature.get(i));
        }
        CalculateCorrelation(arr);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setGranularityEnabled(true);
        leftAxis.setEnabled(true);
        leftAxis.setAxisMaximum(10);
        leftAxis.setAxisMinimum(0);


        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setDrawGridLines(true);//是否绘制网格
        rightAxis.setDrawZeroLine(false);//是否绘制0刻度线
        rightAxis.setGranularityEnabled(true);//等间距

        XAxis xaxis = chart.getXAxis();
        xaxis.setValueFormatter(new IndexAxisValueFormatter(xDataList));
        xaxis.setLabelRotationAngle(-50);//x轴标签倾斜
        xaxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
        xaxis.setGranularity(1f);//间隔为1
        xaxis.setTextColor(Color.parseColor("#000000"));

        LineDataSet pain = new LineDataSet(yDataLeft, "Pain Level");
        LineDataSet weather = new LineDataSet(yDataRight, rightType);
        pain.setAxisDependency(YAxis.AxisDependency.LEFT);
        pain.setLabel("Pain Level");
        pain.setDrawValues(true);//是否显示数据
        pain.setColor(Color.parseColor("#FFA500"));//曲线颜色
        pain.setCircleColor(Color.parseColor("#FFA500"));
        pain.setLineWidth(2f);//线宽
        pain.setCircleRadius(5f);
        pain.setDrawValues(true);


        weather.setDrawValues(true);
        weather.setAxisDependency(YAxis.AxisDependency.RIGHT);
        weather.setLabel(rightType);
        weather.setColor(Color.parseColor("#00BFFF"));
        weather.setCircleColor(Color.parseColor("#00BFFF"));
        weather.setLineWidth(2f);
        weather.setCircleRadius(5f);
        weather.setDrawValues(true);


        LineData data = new LineData(pain, weather);
        data.setValueTextColor(Color.WHITE);
        data.setValueTextSize(9f);
        chart.setData(data);
        chart.animateX(1000);//draw line time
        // set touchable, draggable, scalable
        chart.setTouchEnabled(true);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.invalidate();
        //hide description
        Description description = new Description();
        description.setEnabled(false);
        chart.setDescription(description);

    }

    private void CalculateCorrelation(double[][] arr) {
        RealMatrix m = MatrixUtils.createRealMatrix(arr);
        for (int i = 0; i < m.getColumnDimension(); i++)
            for (int j = 0; j < m.getColumnDimension(); j++) {
                PearsonsCorrelation pc = new PearsonsCorrelation();
                double cor = pc.correlation(m.getColumn(i), m.getColumn(j));
            }
        PearsonsCorrelation pc = new PearsonsCorrelation(m);
        RealMatrix corM = pc.getCorrelationMatrix();
        RealMatrix pM = pc.getCorrelationPValues();
        binding.tvCorrelation.setText ("        p value:" + pM.getEntry(0, 1) + "\n" + " correlation: " + corM.getEntry(0, 1));
    }

}