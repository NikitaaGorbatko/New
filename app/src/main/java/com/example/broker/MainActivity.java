package com.example.broker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MainActivity extends AppCompatActivity {
    //private MqttHelper mqttHelper;
    private TextView dataReceived;
    private float[] tempArray = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private GraphView graphView;
    private DataPoint[] dataPoints = {};
    private LineGraphSeries<DataPoint> series;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataReceived = findViewById(R.id.text_view);
        graphView = findViewById(R.id.graph);
        startMqtt();
    }



    private void startMqtt() {
        /*mqttHelper = */new MqttHelper(this, new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {

            }

            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) {
                dataReceived.setText(message.toString());
                LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                        new DataPoint(Float.valueOf(message.toString()), 0),
                        //new DataPoint(12, 0),
                });
                //graphView.removeAllSeries();
                graphView.addSeries(series);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });

    }
}
