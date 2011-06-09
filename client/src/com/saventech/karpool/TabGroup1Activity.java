package com.saventech.karpool;

import android.content.Intent;
import android.os.Bundle;

public class TabGroup1Activity extends TabGroupActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startChildActivity("RideDetailsDriver", new Intent(this,RideDetailsDriver.class));
        //startChildActivity("OptionsActivity", new Intent(this,CancelRoute.class));
    }
}
