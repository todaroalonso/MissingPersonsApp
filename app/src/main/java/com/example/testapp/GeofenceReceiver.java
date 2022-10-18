package com.example.testapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

public class GeofenceReceiver extends BroadcastReceiver {

    private static final String TAG = "GeofenceReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context,"Geofence triggered...",Toast.LENGTH_SHORT).show();

        GeofencingEvent geofencingEvent=GeofencingEvent.fromIntent(intent);
        
        if(geofencingEvent.hasError()){
            Log.d(TAG, "onReceive: Error receiving geofence event...");
            return;
        }
        List<Geofence>geofenceList=geofencingEvent.getTriggeringGeofences();
        //get the exact location where the geofence is triggered
        //Location location=geofencingEvent.getTriggeringLocation();

        int transitionType=geofencingEvent.getGeofenceTransition();

        switch(transitionType){
            case Geofence.GEOFENCE_TRANSITION_DWELL:
                Toast.makeText(context,"GEOFENCE_TRANSITION_DWELL",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}