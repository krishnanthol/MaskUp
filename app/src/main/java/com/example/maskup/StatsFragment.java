package com.example.maskup;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class StatsFragment extends Fragment
{
    int countyRiskLevel;
    ArrayList<Integer> countyNewCases = new ArrayList<>();
    ArrayList<Integer> countyNewDeaths = new ArrayList<>();
    ArrayList<Integer> usNewCases = new ArrayList<>();
    ArrayList<Integer> usNewDeaths = new ArrayList<>();

    ProgressBar progressBar;
    GraphView countyNewCasesGraph;
    GraphView countyNewDeathsGraph;
    GraphView usNewCasesGraph;
    GraphView usNewDeathsGraph;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_stats,container,false);
        progressBar = view.findViewById(R.id.id_riskLevelBar);
        countyNewCasesGraph = view.findViewById(R.id.id_countyNewCasesGraph);
        countyNewDeathsGraph = view.findViewById(R.id.id_countyNewDeathsGraph);
        usNewCasesGraph = view.findViewById(R.id.id_usNewCasesGraph);
        usNewDeathsGraph = view.findViewById(R.id.id_usNewDeathsGraph);

        if(getArguments() != null)
        {
            countyRiskLevel = getArguments().getInt("countyRiskLevel");
            countyNewCases = getArguments().getIntegerArrayList("countyNewCases");
            countyNewDeaths = getArguments().getIntegerArrayList("countyNewDeaths");
            usNewCases = getArguments().getIntegerArrayList("usNewCases");
            usNewDeaths = getArguments().getIntegerArrayList("usNewDeaths");

            LineGraphSeries<DataPoint> countyNewCasesGraphData = new LineGraphSeries<DataPoint>((new DataPoint[]{
                    new DataPoint(1, countyNewCases.get(4)),
                    new DataPoint(2, countyNewCases.get(3)),
                    new DataPoint(3, countyNewCases.get(2)),
                    new DataPoint(4, countyNewCases.get(1)),
                    new DataPoint(5, countyNewCases.get(0)),
            }));
            countyNewCasesGraph.setTitle("New County Cases");
            countyNewCasesGraph.getGridLabelRenderer().setVerticalAxisTitle("Cases");
            countyNewCasesGraph.getGridLabelRenderer().setHorizontalAxisTitle("Past 5 Days");
            countyNewCasesGraph.getGridLabelRenderer().setPadding(100);
            countyNewCasesGraph.setBackgroundResource(R.drawable.button_gradient_drawable);
            countyNewCasesGraph.addSeries(countyNewCasesGraphData);

            LineGraphSeries<DataPoint> countyNewDeathsGraphData = new LineGraphSeries<DataPoint>((new DataPoint[]{
                    new DataPoint(1, countyNewDeaths.get(4)),
                    new DataPoint(2, countyNewDeaths.get(3)),
                    new DataPoint(3, countyNewDeaths.get(2)),
                    new DataPoint(4, countyNewDeaths.get(1)),
                    new DataPoint(5, countyNewDeaths.get(0)),
            }));
            countyNewDeathsGraph.setTitle("New County Death Cases");
            countyNewDeathsGraph.getGridLabelRenderer().setVerticalAxisTitle("Deaths");
            countyNewDeathsGraph.getGridLabelRenderer().setHorizontalAxisTitle("Past 5 Days");
            countyNewDeathsGraph.getGridLabelRenderer().setPadding(100);
            countyNewDeathsGraph.setBackgroundResource(R.drawable.button_gradient_drawable);
            countyNewDeathsGraph.addSeries(countyNewDeathsGraphData);


            LineGraphSeries<DataPoint> usNewCasesGraphData = new LineGraphSeries<DataPoint>((new DataPoint[]{
                    new DataPoint(1, usNewCases.get(4)),
                    new DataPoint(2, usNewCases.get(3)),
                    new DataPoint(3, usNewCases.get(2)),
                    new DataPoint(4, usNewCases.get(1)),
                    new DataPoint(5, usNewCases.get(0)),
            }));
            usNewCasesGraph.setTitle("New US Cases");
            usNewCasesGraph.getGridLabelRenderer().setVerticalAxisTitle("Cases");
            usNewCasesGraph.getGridLabelRenderer().setHorizontalAxisTitle("Past 5 Days");
            usNewCasesGraph.getGridLabelRenderer().setPadding(100);
            usNewCasesGraph.setBackgroundResource(R.drawable.button_gradient_drawable);
            usNewCasesGraph.addSeries(usNewCasesGraphData);

            LineGraphSeries<DataPoint> usNewDeathsGraphData = new LineGraphSeries<DataPoint>((new DataPoint[]{
                    new DataPoint(1, usNewDeaths.get(4)),
                    new DataPoint(2, usNewDeaths.get(3)),
                    new DataPoint(3, usNewDeaths.get(2)),
                    new DataPoint(4, usNewDeaths.get(1)),
                    new DataPoint(5, usNewCases.get(0)),
            }));
            usNewDeathsGraph.setTitle("New US Deaths");
            usNewDeathsGraph.getGridLabelRenderer().setVerticalAxisTitle("Deaths");
            usNewDeathsGraph.getGridLabelRenderer().setHorizontalAxisTitle("Past 5 Days");
            usNewDeathsGraph.getGridLabelRenderer().setPadding(100);
            usNewDeathsGraph.setBackgroundResource(R.drawable.button_gradient_drawable);
            usNewDeathsGraph.addSeries(usNewDeathsGraphData);

            progressBar.setProgress((int) (((double) countyRiskLevel /10)*100));
            progressBar.getProgressDrawable().setColorFilter(R.drawable.button_gradient_drawable, android.graphics.PorterDuff.Mode.SRC_IN);

        }

        Log.d("countyRiskLevel",""+ countyRiskLevel);
        Log.d("stateNewCases",""+ countyNewCases.toString());
        return view;
    }

}
