package com.example.maskup;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class StatsFragment extends Fragment
{
    int stateRiskLevel;
    ArrayList<Integer> stateNewCases = new ArrayList<>();
    ArrayList<Integer> stateNewDeaths = new ArrayList<>();
    ArrayList<Integer> usNewCases = new ArrayList<>();
    ArrayList<Integer> usNewDeaths = new ArrayList<>();

    ProgressBar progressBar;
    LineChart stateNewCasesGraph;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_stats,container,false);
        progressBar = view.findViewById(R.id.id_riskLevelBar);
        stateNewCasesGraph = view.findViewById(R.id.id_stateNewCasesGraph);

        if(getArguments() != null)
        {
            stateRiskLevel = getArguments().getInt("stateRiskLevel");
            stateNewCases = getArguments().getIntegerArrayList("stateNewCases");
            stateNewDeaths = getArguments().getIntegerArrayList("stateNewDeaths");
            usNewCases = getArguments().getIntegerArrayList("usNewCases");
            usNewDeaths = getArguments().getIntegerArrayList("usNewDeaths");

            progressBar.setProgress((int) (((double)stateRiskLevel/10)*100));

            List<Entry> stateNewCasesData = new ArrayList<Entry>();
            Entry e1 = new Entry(0,stateNewCases.get(4));
            stateNewCasesData.add(e1);
            Entry e2 = new Entry(0,stateNewCases.get(3));
            stateNewCasesData.add(e2);
            Entry e3 = new Entry(0,stateNewCases.get(2));
            stateNewCasesData.add(e3);
            Entry e4 = new Entry(0,stateNewCases.get(1));
            stateNewCasesData.add(e4);
            Entry e5 = new Entry(0,stateNewCases.get(0));
            stateNewCasesData.add(e5);

            LineDataSet set1 = new LineDataSet(stateNewCasesData,"State New Cases");
            set1.setAxisDependency(YAxis.AxisDependency.LEFT);

            List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(set1);

            LineData data = new LineData(dataSets);
            stateNewCasesGraph.setData(data);
            stateNewCasesGraph.invalidate();

        }

        Log.d("stateRiskLevel",""+stateRiskLevel);
        Log.d("stateNewCases",""+stateNewCases.toString());
        return view;
    }

}
