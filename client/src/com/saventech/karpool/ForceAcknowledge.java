package com.saventech.karpool;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ForceAcknowledge extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        textview.setText("This is force aknowledgement screen");
        setContentView(textview);
    }

}
