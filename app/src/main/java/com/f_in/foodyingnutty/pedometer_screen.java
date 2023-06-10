package com.f_in.foodyingnutty;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class pedometer_screen extends AppCompatActivity implements SensorEventListener {

    private TextView textView3, steps_count;
    private SensorManager sensorManager;
    private Sensor StepCounter;
    private boolean isCounterSensorPresent;
    int stepscount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer_screen);

        ImageView leftIcon = findViewById(R.id.left_icon);

        leftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMenu(view);
            }
        });

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        textView3 = findViewById(R.id.textView3);
        steps_count = findViewById(R.id.steps_count);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null)
        {
            StepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isCounterSensorPresent = true;
        }
        else {
            steps_count.setText("Counter Sensor is not present");
            isCounterSensorPresent = false;
        }
    }
    private void showMenu(View view){
        PopupMenu popupMenu = new PopupMenu(pedometer_screen.this,view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.item_one)
                    startActivity(new Intent(pedometer_screen.this, nutrition_plan_screen.class));
                if (menuItem.getItemId() == R.id.item_two)
                    startActivity(new Intent(pedometer_screen.this, health_advice_screen.class));
                if (menuItem.getItemId() == R.id.item_three)
                    startActivity(new Intent(pedometer_screen.this, pedometer_screen.class));
                if (menuItem.getItemId() == R.id.item_four)
                    startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:foodyingnutty@gmail.com")));
                if (menuItem.getItemId() == R.id.item_five)
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://bit.ly/foodyingnutty_f-s")));
                if (menuItem.getItemId() == R.id.item_six)
                    startActivity(new Intent(pedometer_screen.this, community_log_in.class));
                return true;
            }
        });
        popupMenu.show();
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor == StepCounter){
            stepscount = (int) sensorEvent.values[0];
            steps_count.setText(String.valueOf(stepscount));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null)
            sensorManager.registerListener(this, StepCounter, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null)
            sensorManager.unregisterListener(this, StepCounter);
    }
}