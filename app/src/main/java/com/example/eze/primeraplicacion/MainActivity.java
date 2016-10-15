package com.example.eze.primeraplicacion;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor light;
    private Sensor accelerometer;
//    private TextView ejeX;
    private TextView ejeY;
//    private TextView ejeZ;
    private ImageView leftArrow;
    private ImageView rightArrow;
    private ImageView leftArrowBlack;
    private ImageView rightArrowBlack;
    private TextView luzTextView;
    private TextView puntajeTextView;
    private TextView scoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

   //     ejeX = (TextView)findViewById(R.id.XtextView);
        ejeY = (TextView)findViewById(R.id.YtextView);
  //     ejeZ = (TextView)findViewById(R.id.ZtextView);
        luzTextView = (TextView)findViewById(R.id.luzTextView);
        puntajeTextView = (TextView)findViewById(R.id.puntajeTextView);
        scoreTextView = (TextView)findViewById(R.id.scoreTextView);
        scoreTextView.setText("6");
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,light, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        rightArrow = (ImageView) findViewById(R.id.flecha_der);
        leftArrow = (ImageView) findViewById(R.id.flecha_izq);
        leftArrowBlack = (ImageView) findViewById(R.id.flecha_izq_black);
        rightArrowBlack = (ImageView) findViewById(R.id.flecha_der_black);

    }



    @Override
    public void onSensorChanged(SensorEvent event) {
    if(event.sensor.getType() == Sensor.TYPE_LIGHT){
        if(event.values[0] < 13){
            luzTextView.setText("No hay luz " + Float.toString(event.values[0]));
            scoreTextView.setText("0");
        }else{
            luzTextView.setText("Hay luz " + Float.toString(event.values[0]));
        }
    }
    if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
//        ejeX.setText("Eje X: " + Float.toString(event.values[0]));
        ejeY.setText("Eje Y: " + Float.toString(event.values[1]));
//       ejeZ.setText("Eje Z: " + Float.toString(event.values[2]));
        if(event.values[1] < -1.5){
            leftArrowBlack.setVisibility(View.VISIBLE);
            leftArrow.setVisibility(View.INVISIBLE);
            rightArrowBlack.setVisibility(View.INVISIBLE);
            rightArrow.setVisibility(View.VISIBLE);
        }
        else if(event.values[1] > 1.5){
            leftArrowBlack.setVisibility(View.INVISIBLE);
            rightArrowBlack.setVisibility(View.VISIBLE);
            rightArrow.setVisibility(View.INVISIBLE);
            leftArrow.setVisibility(View.VISIBLE);
        }
        else{
            leftArrowBlack.setVisibility(View.INVISIBLE);
            rightArrowBlack.setVisibility(View.INVISIBLE);
            rightArrow.setVisibility(View.VISIBLE);
            leftArrow.setVisibility(View.VISIBLE);
        }


    }



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
