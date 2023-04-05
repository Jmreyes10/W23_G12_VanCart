package com.example.w23_g12_ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private List<CustomerRates> customerRates = new ArrayList<>();
    PieChart pieChart1, pieChart2, pieChart3, pieChart4;
    BarChart barChart;
    Button btnDashboardExit;
    SharedPreferences mPreferences;

    private float ratingQ1;
    private float ratingQ2;
    private float ratingQ3;
    private float ratingQ4;

    private float avgRateQ1 = 0f;
    private float avgRateQ2 = 0f;
    private float avgRateQ3 = 0f;
    private float avgRateQ4 = 0f;

    private int n1RatesQ1 = 0;
    private int n2RatesQ1 = 0;
    private int n3RatesQ1 = 0;
    private int n4RatesQ1 = 0;
    private int n5RatesQ1 = 0;

    private int n1RatesQ2 = 0;
    private int n2RatesQ2 = 0;
    private int n3RatesQ2 = 0;
    private int n4RatesQ2 = 0;
    private int n5RatesQ2 = 0;

    private int n1RatesQ3 = 0;
    private int n2RatesQ3 = 0;
    private int n3RatesQ3 = 0;
    private int n4RatesQ3 = 0;
    private int n5RatesQ3 = 0;

    private int n1RatesQ4 = 0;
    private int n2RatesQ4 = 0;
    private int n3RatesQ4 = 0;
    private int n4RatesQ4 = 0;
    private int n5RatesQ4 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Read historical ratings
        readCustomeRates();

        //Add current ratings to historical list
        int transId = customerRates.size()+1;
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        Date today = Calendar.getInstance().getTime();
        String todayAsString = df.format(today);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        float ratingQ1 = mPreferences.getFloat("ratingQ1", 0);
        float ratingQ2 = mPreferences.getFloat("ratingQ2", 0);
        float ratingQ3 = mPreferences.getFloat("ratingQ3", 0);
        float ratingQ4 = mPreferences.getFloat("ratingQ4", 0);
        customerRates.add(new CustomerRates(transId, todayAsString, ratingQ1, ratingQ2, ratingQ3, ratingQ4));

        getSummaryRates();

        barChart = findViewById(R.id.barChart);
        pieChart1 = findViewById(R.id.pieChart1);
        pieChart2 = findViewById(R.id.pieChart2);
        pieChart3 = findViewById(R.id.pieChart3);
        pieChart4 = findViewById(R.id.pieChart4);
        btnDashboardExit = findViewById(R.id.btnDashboardExit);

        ArrayList<PieEntry> pieEntries1 = new ArrayList<>();
        ArrayList<PieEntry> pieEntries2 = new ArrayList<>();
        ArrayList<PieEntry> pieEntries3 = new ArrayList<>();
        ArrayList<PieEntry> pieEntries4 = new ArrayList<>();
        ArrayList<BarEntry> barEntries = new ArrayList<>();

        pieEntries1.add(new PieEntry((float)n1RatesQ1 / customerRates.size(),"1"));
        pieEntries1.add(new PieEntry((float)n2RatesQ1 / customerRates.size(),"2"));
        pieEntries1.add(new PieEntry((float)n3RatesQ1 / customerRates.size(),"3"));
        pieEntries1.add(new PieEntry((float)n4RatesQ1 / customerRates.size(),"4"));
        pieEntries1.add(new PieEntry((float)n5RatesQ1 / customerRates.size(),"5"));

        pieEntries2.add(new PieEntry((float)n1RatesQ2 / customerRates.size(),"1"));
        pieEntries2.add(new PieEntry((float)n2RatesQ2 / customerRates.size(),"2"));
        pieEntries2.add(new PieEntry((float)n3RatesQ2 / customerRates.size(),"3"));
        pieEntries2.add(new PieEntry((float)n4RatesQ2 / customerRates.size(),"4"));
        pieEntries2.add(new PieEntry((float)n5RatesQ2 / customerRates.size(),"5"));

        pieEntries3.add(new PieEntry((float)n1RatesQ3 / customerRates.size(),"1"));
        pieEntries3.add(new PieEntry((float)n2RatesQ3 / customerRates.size(),"2"));
        pieEntries3.add(new PieEntry((float)n3RatesQ3 / customerRates.size(),"3"));
        pieEntries3.add(new PieEntry((float)n4RatesQ3 / customerRates.size(),"4"));
        pieEntries3.add(new PieEntry((float)n5RatesQ3 / customerRates.size(),"5"));

        pieEntries4.add(new PieEntry((float)n1RatesQ4 / customerRates.size(),"1"));
        pieEntries4.add(new PieEntry((float)n2RatesQ4 / customerRates.size(),"2"));
        pieEntries4.add(new PieEntry((float)n3RatesQ4 / customerRates.size(),"3"));
        pieEntries4.add(new PieEntry((float)n4RatesQ4 / customerRates.size(),"4"));
        pieEntries4.add(new PieEntry((float)n5RatesQ4 / customerRates.size(),"5"));

        barEntries.add(new BarEntry(1, avgRateQ1));
        barEntries.add(new BarEntry(2, avgRateQ2));
        barEntries.add(new BarEntry(3, avgRateQ3));
        barEntries.add(new BarEntry(4, avgRateQ4));

        //Initiate bar dataset
        BarDataSet barDataSet = new BarDataSet(barEntries, "Average Rates");
        //Set colors
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        //Hide draw values
        barDataSet.setDrawValues(true);
        barDataSet.setValueTextSize(10);
        barDataSet.setValueFormatter(new DefaultValueFormatter(2));
        //Set bar data
        barChart.setData(new BarData(barDataSet));
        //Set animation
        barChart.animateY(1000);
        //Hide description
        Description description = new Description();
        description.setEnabled(false);
        barChart.setDescription(description);
        //Set X labels
        XAxis xAxis = barChart.getXAxis();
        xAxis.setLabelCount(4);
        String[] labels = new String[] {"", "Easy to use", "Completeness", "Reliability", "Overal exp."};
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        //Initialize pie data set
        PieDataSet pieDataSet1 = new PieDataSet(pieEntries1, "Q1: Easy to use");
        PieDataSet pieDataSet2 = new PieDataSet(pieEntries2, "Q2: Completeness of features included");
        PieDataSet pieDataSet3 = new PieDataSet(pieEntries3, "Q3: Reliability");
        PieDataSet pieDataSet4 = new PieDataSet(pieEntries4, "Q4: Overal experience");

        //Format datasets
        pieDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet1.setValueTextSize(10);
        pieDataSet1.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet1.setDrawValues(true);
        pieDataSet1.setValueFormatter(new PercentFormatter(pieChart1));

        pieDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet2.setValueTextSize(10);
        pieDataSet2.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet2.setDrawValues(true);
        pieDataSet2.setValueFormatter(new PercentFormatter(pieChart1));

        pieDataSet3.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet3.setValueTextSize(10);
        pieDataSet3.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet3.setDrawValues(true);
        pieDataSet3.setValueFormatter(new PercentFormatter(pieChart1));

        pieDataSet4.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet4.setValueTextSize(10);
        pieDataSet4.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet4.setDrawValues(true);
        pieDataSet4.setValueFormatter(new PercentFormatter(pieChart1));

        //Set pie data
        pieChart1.setData(new PieData(pieDataSet1));
        pieChart2.setData(new PieData(pieDataSet2));
        pieChart3.setData(new PieData(pieDataSet3));
        pieChart4.setData(new PieData(pieDataSet4));

        //Format pie
        pieChart1.animateXY(1000, 1000);
        pieChart1.setUsePercentValues(true);
        pieChart1.setHoleRadius(65);
        pieChart1.setCenterText("Easy to use");
        pieChart1.getDescription().setEnabled(false);
        Legend legend1 = pieChart1.getLegend();
        legend1.setEnabled(false);

        pieChart2.animateXY(1000, 1000);
        pieChart2.setUsePercentValues(true);
        pieChart2.setHoleRadius(65);
        pieChart2.setCenterText("Completeness of features included");
        pieChart2.getDescription().setEnabled(false);
        Legend legend2 = pieChart2.getLegend();
        legend2.setEnabled(false);

        pieChart3.animateXY(1000, 1000);
        pieChart3.setUsePercentValues(true);
        pieChart3.setHoleRadius(65);
        pieChart3.setCenterText("Realiability");
        pieChart3.getDescription().setEnabled(false);
        Legend legend3 = pieChart3.getLegend();
        legend3.setEnabled(false);

        pieChart4.animateXY(1000, 1000);
        pieChart4.setUsePercentValues(true);
        pieChart4.setHoleRadius(65);
        pieChart4.setCenterText("Overal experience");
        pieChart4.getDescription().setEnabled(false);
        Legend legend4 = pieChart4.getLegend();
        legend4.setEnabled(false);

        btnDashboardExit.setOnClickListener((View v) -> {
            finish();
        });

    }

    public void readCustomeRates(){
        InputStream inputStream = getResources().openRawResource(R.raw.customer_ratings);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
        String line="";
        try {
            reader.readLine(); //To skip header
            while((line=reader.readLine())!=null){
                //Split
                String[] tokens = line.split(",");
                CustomerRates rate = new CustomerRates();
                rate.setTransactionId(Integer.parseInt(tokens[0]));
                rate.setReviewDate(tokens[1]);
                rate.setRateQ1(Float.parseFloat(tokens[2]));
                rate.setRateQ2(Float.parseFloat(tokens[3]));
                rate.setRateQ3(Float.parseFloat(tokens[4]));
                rate.setRateQ4(Float.parseFloat(tokens[5]));
                customerRates.add(rate);
                Log.d("My Activity", "Just created line " + line + " tokens[0]: " + tokens[0]
                        + " tokens[1]: " + tokens[1]
                        + " tokens[2]: " + tokens[2]
                        + " tokens[3]: " + tokens[3]
                        + " tokens[4]: " + tokens[4]
                        + " tokens[5]: " + tokens[5]
                );
            }
        }catch (IOException e){
            Log.d("My Activity", "Error reading line " + line);
            e.printStackTrace();
        }
    }

    public void getSummaryRates() {
        for(int i=1;i<customerRates.size();i++){

            avgRateQ1 += customerRates.get(i).getRateQ1();
            avgRateQ2 += customerRates.get(i).getRateQ2();
            avgRateQ3 += customerRates.get(i).getRateQ3();
            avgRateQ4 += customerRates.get(i).getRateQ4();

            n1RatesQ1 += customerRates.get(i).getRateQ1() == 1 ? 1 : 0;
            n2RatesQ1 += customerRates.get(i).getRateQ1() > 1 && customerRates.get(i).getRateQ1() <= 2 ? 1 : 0;
            n3RatesQ1 += customerRates.get(i).getRateQ1() > 2 && customerRates.get(i).getRateQ1() <= 3 ? 1 : 0;
            n4RatesQ1 += customerRates.get(i).getRateQ1() > 3 && customerRates.get(i).getRateQ1() <= 4 ? 1 : 0;
            n5RatesQ1 += customerRates.get(i).getRateQ1() > 4 && customerRates.get(i).getRateQ1() <= 5 ? 1 : 0;

            n1RatesQ2 += customerRates.get(i).getRateQ2() == 1 ? 1 : 0;
            n2RatesQ2 += customerRates.get(i).getRateQ2() > 1 && customerRates.get(i).getRateQ2() <= 2 ? 1 : 0;
            n3RatesQ2 += customerRates.get(i).getRateQ2() > 2 && customerRates.get(i).getRateQ2() <= 3 ? 1 : 0;
            n4RatesQ2 += customerRates.get(i).getRateQ2() > 3 && customerRates.get(i).getRateQ2() <= 4 ? 1 : 0;
            n5RatesQ2 += customerRates.get(i).getRateQ2() > 4 && customerRates.get(i).getRateQ2() <= 5 ? 1 : 0;

            n1RatesQ3 += customerRates.get(i).getRateQ3() == 1 ? 1 : 0;
            n2RatesQ3 += customerRates.get(i).getRateQ3() > 1 && customerRates.get(i).getRateQ3() <= 2 ? 1 : 0;
            n3RatesQ3 += customerRates.get(i).getRateQ3() > 2 && customerRates.get(i).getRateQ3() <= 3 ? 1 : 0;
            n4RatesQ3 += customerRates.get(i).getRateQ3() > 3 && customerRates.get(i).getRateQ3() <= 4 ? 1 : 0;
            n5RatesQ3 += customerRates.get(i).getRateQ3() > 4 && customerRates.get(i).getRateQ3() <= 5 ? 1 : 0;

            n1RatesQ4 += customerRates.get(i).getRateQ4() == 1 ? 1 : 0;
            n2RatesQ4 += customerRates.get(i).getRateQ4() > 1 && customerRates.get(i).getRateQ4() <= 2 ? 1 : 0;
            n3RatesQ4 += customerRates.get(i).getRateQ4() > 2 && customerRates.get(i).getRateQ4() <= 3 ? 1 : 0;
            n4RatesQ4 += customerRates.get(i).getRateQ4() > 3 && customerRates.get(i).getRateQ4() <= 4 ? 1 : 0;
            n5RatesQ4 += customerRates.get(i).getRateQ4() > 4 && customerRates.get(i).getRateQ4() <= 5 ? 1 : 0;
        }

        avgRateQ1/=customerRates.size();
        avgRateQ2/=customerRates.size();
        avgRateQ3/=customerRates.size();
        avgRateQ4/=customerRates.size();
    }
}