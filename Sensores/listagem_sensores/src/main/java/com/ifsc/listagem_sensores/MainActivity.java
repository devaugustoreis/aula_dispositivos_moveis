package com.ifsc.listagem_sensores;

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

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager mySensorManager;
    private Sensor myLightSensor;
    TextView myTextViewLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myTextViewLight = findViewById(R.id.tvLight);

        mySensorManager = (SensorManager) getSystemService(getApplicationContext().SENSOR_SERVICE);

        List<Sensor> listSensor =  mySensorManager.getSensorList(Sensor.TYPE_ALL);

        StringBuilder sensorListTxt = new StringBuilder();

        for (Sensor sensor : listSensor) {
            sensorListTxt.append(sensor.getName()).append(System.getProperty("line.separator"));
        }

        myTextViewLight.setText(sensorListTxt);

    }

}