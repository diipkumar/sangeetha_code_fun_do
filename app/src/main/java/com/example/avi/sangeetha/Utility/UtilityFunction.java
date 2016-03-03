package com.example.avi.sangeetha.Utility;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.EditText;

public class UtilityFunction {
    public static String SESSION_PREFENCES_NAME = "SESSION_OHOPREF";

    public Activity activity;

    public UtilityFunction(Activity activity) {
        this.activity = activity;
    }

    public void goActivity(Class activity)
    {
        if(this.activity == null)
        {
            Log.d("Cannot open", "Another activity");
            return ;
        }
        Intent i =new Intent(this.activity,activity);
        Log.d("Going for", "Another activity");
        this.activity.startActivity(i);
    }

    public void cleargoActivity(Class activityclass) {
        ((BaseActivity)activity).stopprogress();
        if(this.activity == null)
        {
            Log.d("Cannot open", "Another activity");
            return ;
        }
        Log.d("Going for", "Another activity");
        Intent intent = new Intent(this.activity, activityclass);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        this.activity.startActivity(intent);
    }

    public String getData(EditText mobilenumber) {
        return mobilenumber.getText().toString();
    }


    public SharedPreferences.Editor getEditor(String name)
    {
        SharedPreferences pref = activity.getSharedPreferences(name, activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        return editor;
    }


    public void clearData() {
        getEditor(SESSION_PREFENCES_NAME).clear().commit();
    }
}
