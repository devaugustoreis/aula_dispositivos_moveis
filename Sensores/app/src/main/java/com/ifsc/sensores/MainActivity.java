package com.ifsc.sensores;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mySensorManager;
    private Sensor myLightSensor;
    TextView myTextViewLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myTextViewLight = findViewById(R.id.tvLight);

//        mySensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mySensorManager = (SensorManager) getSystemService(getApplicationContext().SENSOR_SERVICE);
        myLightSensor = (Sensor) mySensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        mySensorManager.registerListener(this, myLightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensorType = event.sensor.getType();

        float currentValue = event.values[0];
        myTextViewLight.setText(Float.toString(currentValue));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}