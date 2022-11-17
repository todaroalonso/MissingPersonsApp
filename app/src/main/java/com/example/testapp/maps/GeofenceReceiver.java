package com.example.testapp.maps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.testapp.helpers.NotificationHelper;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

public class GeofenceReceiver extends BroadcastReceiver {

    private static final String TAG = "GeofenceReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //Toast.makeText(context,"Geofence triggered...",Toast.LENGTH_SHORT).show();
//sending notifications based on the context in the onReceive method (context is a parameter in the onReceive method)
        NotificationHelper notificationHelper=new NotificationHelper(context);

        GeofencingEvent geofencingEvent=GeofencingEvent.fromIntent(intent);
        
        if(geofencingEvent.hasError()){
            Log.d(TAG, "onReceive: Error receiving geofence event...");
            return;
        }
        List<Geofence>geofenceList=geofencingEvent.getTriggeringGeofences();
        for (Geofence geofence:geofenceList){
            Log.d(TAG, "onReceive: "+geofence.getRequestId());
        }
        //get the exact location where the geofence is triggered
        //Location location=geofencingEvent.getTriggeringLocation();

        int transitionType=geofencingEvent.getGeofenceTransition();



        if(transitionType == Geofence.GEOFENCE_TRANSITION_ENTER){
                //Toast.makeText(context,"GEOFENCE_TRANSITION_ENTER",Toast.LENGTH_SHORT).show();
                notificationHelper.sendHighPriorityNotification("A missing person was reported in your area","", Locater.class);
        }
        if(transitionType == Geofence.GEOFENCE_TRANSITION_DWELL){
            //Toast.makeText(context,"GEOFENCE_TRANSITION_DWELL",Toast.LENGTH_SHORT).show();
            notificationHelper.sendHighPriorityNotification("A missing person was reported in your area","",Locater.class);
        }
    }
}