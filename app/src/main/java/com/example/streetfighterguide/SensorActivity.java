package com.example.streetfighterguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.TextView;

public class SensorActivity extends AppCompatActivity {


    SensorManager sensorManager;
    Sensor sensorProximity;
    Sensor sensorLuminosity;
    TextView proximity;
    TextView raio;
    Vibrator vibrator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        proximity = findViewById(R.id.proximity);
        raio = findViewById(R.id.raio);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorLuminosity = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorProximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener((SensorEventListener) this);
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener((SensorEventListener) this, sensorProximity, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener((SensorEventListener) this, sensorLuminosity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        int result = Math.round(sensorEvent.values[0]);

        if(sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            proximity.setText("Chance dele estar perto de você  " + result + "%");

            if(result >= 100) {
                vibrator.vibrate(500);
                return;

            }
            vibrator.vibrate(1000);
        }
        if(sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
            raio .setText("Quantidade de raios emitidos ao seu redor  " + result);
        }

    }

    public void onAccuracyChanged(Sensor sensor, int i){}

}