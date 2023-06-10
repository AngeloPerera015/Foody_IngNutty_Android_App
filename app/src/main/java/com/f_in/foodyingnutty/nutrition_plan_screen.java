package com.f_in.foodyingnutty;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;

public class nutrition_plan_screen extends AppCompatActivity {

    AnyChartView anyChartView;
    String[] nutritionPlan = {"Grains", "Vegetables", "Fruits", "Diary", "Meat"};
    int[] percentage = {40, 20, 20, 10, 10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_plan_screen);

        ImageView leftIcon = findViewById(R.id.left_icon);

        leftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMenu(view);
            }
        });

        anyChartView = findViewById(R.id.anyChartView);
        setupChartView();
    }
    private void showMenu(View view){
        PopupMenu popupMenu = new PopupMenu(nutrition_plan_screen.this,view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.item_one)
                    startActivity(new Intent(nutrition_plan_screen.this, nutrition_plan_screen.class));
                if (menuItem.getItemId() == R.id.item_two)
                    startActivity(new Intent(nutrition_plan_screen.this, health_advice_screen.class));
                if (menuItem.getItemId() == R.id.item_three)
                    startActivity(new Intent(nutrition_plan_screen.this, pedometer_screen.class));
                if (menuItem.getItemId() == R.id.item_four)
                    startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:foodyingnutty@gmail.com")));
                if (menuItem.getItemId() == R.id.item_five)
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://bit.ly/foodyingnutty_f-s")));
                if (menuItem.getItemId() == R.id.item_six)
                    startActivity(new Intent(nutrition_plan_screen.this, community_log_in.class));
                return true;
            }
        });
        popupMenu.show();
    }

    private void setupChartView() {
        Pie pie = AnyChart.pie();
        List<DataEntry> dataEntries = new ArrayList<>();

        for (int i=0; i<nutritionPlan.length; i++) {
            dataEntries.add(new ValueDataEntry(nutritionPlan[i],percentage[i]));
        }
        pie.data(dataEntries);
        anyChartView.setChart(pie);
    }
}