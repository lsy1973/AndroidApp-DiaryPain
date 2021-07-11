package com.example.paindiarysecond.ui.reports;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.paindiarysecond.R;
import com.example.paindiarysecond.databinding.PainDataEntryFragmentBinding;
import com.example.paindiarysecond.databinding.ReportsFragmentBinding;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ReportsFragment extends Fragment implements View.OnClickListener {

    private ReportsFragmentBinding binding;
    private LinearLayout lin_sub1, lin_sub2, lin_sub3;

    private LocationChartFragment subFragment1;
    private StepsChartFragment subFragment2;
    private LineChartFragment subFragment3;

    private ImageView mTabline;
    private int mScreen1_3;

    public static ReportsFragment newInstance() {
        return new ReportsFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (null != bundle) {
            //
        }
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ReportsFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        initView(view);
        setLinstener();
        initData();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lin_sub1:
                setSubFragment(0);
                setmTabline(0);
                break;
            case R.id.lin_sub2:
                setSubFragment(1);
                setmTabline(1);
                break;
            case R.id.lin_sub3:
                setSubFragment(2);
                setmTabline(2);
                break;
            default:
                break;
        }
    }

    private void initView(View view) {
        lin_sub1 = (LinearLayout) view.findViewById(R.id.lin_sub1);
        lin_sub2 = (LinearLayout) view.findViewById(R.id.lin_sub2);
        lin_sub3 = (LinearLayout) view.findViewById(R.id.lin_sub3);
        mTabline = (ImageView) view.findViewById(R.id.imv_tabline);
    }

    protected void initData() {
        // Display display = getWindow().getWindowManager().getDefaultDisplay();
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        mScreen1_3 = outMetrics.widthPixels / 3;
        ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) mTabline.getLayoutParams();
        lp.width = mScreen1_3;
        mTabline.setLayoutParams(lp);

        //初次显示设置
        setSubFragment(0);
        setmTabline(0);

    }
    protected void setLinstener() {
        lin_sub1.setOnClickListener(this);
        lin_sub2.setOnClickListener(this);
        lin_sub3.setOnClickListener(this);

    }
    public void setmTabline(int i) {

        LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabline
                .getLayoutParams();
        lp.leftMargin = i * mScreen1_3;
        mTabline.setLayoutParams(lp);

    }

    public void setSubFragment(int i){

        FragmentTransaction transaction =getChildFragmentManager().beginTransaction();

        if(0 == i ){
            subFragment1 = (subFragment1 == null ? new LocationChartFragment():subFragment1);
            transaction.replace(R.id.id_content,subFragment1);
            //	transaction.addToBackStack(null);
            transaction.commit();

        }else if(1 == i ){
            subFragment2 = (subFragment2 == null ? new StepsChartFragment():subFragment2);
            transaction.replace(R.id.id_content,subFragment2);
            //	transaction.addToBackStack(null);
            transaction.commit();
        }else if(2 == i ){
            subFragment3 = (subFragment3 == null ? new LineChartFragment():subFragment3);
            transaction.replace(R.id.id_content,subFragment3);
            //	transaction.addToBackStack(null);
            transaction.commit();
        }

    }


}