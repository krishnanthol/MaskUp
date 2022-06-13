package com.example.maskup;

import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class StatsActivity extends AppCompatActivity
{
    ProgressBar progressBar;
    GraphView countyNewCasesGraph;
    GraphView countyNewDeathsGraph;
    GraphView usNewCasesGraph;
    GraphView usNewDeathsGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_stats);

        progressBar = findViewById(R.id.id_riskLevelBar);
        countyNewCasesGraph = findViewById(R.id.id_countyNewCasesGraph);
        countyNewDeathsGraph = findViewById(R.id.id_countyNewDeathsGraph);
        usNewCasesGraph = findViewById(R.id.id_usNewCasesGraph);
        usNewDeathsGraph = findViewById(R.id.id_usNewDeathsGraph);

        LineGraphSeries<DataPoint> countyNewCasesGraphData = new LineGraphSeries<DataPoint>((new DataPoint[]{
                new DataPoint(0, MainActivity.countyNewCases.get(4)),
                new DataPoint(1, MainActivity.countyNewCases.get(3)),
                new DataPoint(2, MainActivity.countyNewCases.get(2)),
                new DataPoint(3, MainActivity.countyNewCases.get(1)),
                new DataPoint(4, MainActivity.countyNewCases.get(0)),
        }));
        countyNewCasesGraph.setTitle("New County Cases");
        countyNewCasesGraph.setBackgroundResource(R.drawable.button_gradient_drawable);
        countyNewCasesGraph.addSeries(countyNewCasesGraphData);

        LineGraphSeries<DataPoint> countyNewDeathsGraphData = new LineGraphSeries<DataPoint>((new DataPoint[]{
                new DataPoint(0, MainActivity.countyNewDeaths.get(4)),
                new DataPoint(1, MainActivity.countyNewDeaths.get(3)),
                new DataPoint(2, MainActivity.countyNewDeaths.get(2)),
                new DataPoint(3, MainActivity.countyNewDeaths.get(1)),
                new DataPoint(4, MainActivity.countyNewDeaths.get(0)),
        }));
        countyNewDeathsGraph.setTitle("New County Death Cases");
        countyNewDeathsGraph.setBackgroundResource(R.drawable.button_gradient_drawable);
        countyNewDeathsGraph.addSeries(countyNewDeathsGraphData);


        LineGraphSeries<DataPoint> usNewCasesGraphData = new LineGraphSeries<DataPoint>((new DataPoint[]{
                new DataPoint(0, MainActivity.usNewCases.get(4)),
                new DataPoint(1, MainActivity.usNewCases.get(3)),
                new DataPoint(2, MainActivity.usNewCases.get(2)),
                new DataPoint(3, MainActivity.usNewCases.get(1)),
                new DataPoint(4, MainActivity.usNewCases.get(0)),
        }));
        usNewCasesGraph.setTitle("New US Cases");
        usNewCasesGraph.setBackgroundResource(R.drawable.button_gradient_drawable);
        usNewCasesGraph.addSeries(usNewCasesGraphData);

        LineGraphSeries<DataPoint> usNewDeathsGraphData = new LineGraphSeries<DataPoint>((new DataPoint[]{
                new DataPoint(0, MainActivity.usNewDeaths.get(4)),
                new DataPoint(1, MainActivity.usNewDeaths.get(3)),
                new DataPoint(2, MainActivity.usNewDeaths.get(2)),
                new DataPoint(3, MainActivity.usNewDeaths.get(1)),
                new DataPoint(4, MainActivity.usNewCases.get(0)),
        }));
        usNewDeathsGraph.setTitle("New US Deaths");
        usNewDeathsGraph.setBackgroundResource(R.drawable.button_gradient_drawable);
        usNewDeathsGraph.addSeries(usNewDeathsGraphData);

        progressBar.setProgress((int) (((double) MainActivity.countyRiskLevel /10)*100));
    }
}
