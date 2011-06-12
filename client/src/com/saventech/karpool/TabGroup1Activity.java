package com.saventech.karpool;

import android.content.Intent;
import android.os.Bundle;

public class TabGroup1Activity extends TabGroupActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Oooooooooooooooooooooooooooooooooooooooooooooooooo");
        startChildActivity("RideDeatailsDriver", new Intent(this.getParent(),RideDetailsDriver.class));
        
        //startChildActivity("OptionsActivity", new Intent(this,CancelRoute.class));
    }
}
