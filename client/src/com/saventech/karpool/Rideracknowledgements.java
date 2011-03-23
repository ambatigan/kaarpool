package com.saventech.karpool;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Rideracknowledgements extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        textview.setText("This is the acknowledgement tab");
        setContentView(textview);
    }
}
