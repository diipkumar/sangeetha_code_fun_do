package com.example.avi.sangeetha.Utility;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.example.avi.sangeetha.R;


public class BaseActivity extends Activity {

    public Dialog network_dialog;
    ProgressDialog progress;
    private boolean showing;
    public UtilityFunction utilityFunction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListeners();
    }

    private void setListeners() {
        utilityFunction = new UtilityFunction(this);

        progress=new ProgressDialog(this);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setMessage(getResources().getString(R.string.progressmessage));
        progress.setCanceledOnTouchOutside(false);
        progress.setCancelable(false);

    }

    protected void Show_Message(String message)
    {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        stopprogress();
        super.onPause();
    }

    public void showDialog()
    {
        stopprogress();
        if(!network_dialog.isShowing())
            network_dialog.show();
    }

    public void closeDialog()
    {
        if(network_dialog.isShowing())
        {
            network_dialog.dismiss();
        }
    }


    @Override
    protected void onResume() {
        ConfigConstant.currentActivity = this;
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void showprogess()
    {
        if(!showing) {
            showing =true;
            if (progress != null && !isFinishing())
                progress.show();
        }
    }

    public void stopprogress()
    {
        if(showing) {
            showing = false;
            if (progress.isShowing()&& !isFinishing())
                progress.dismiss();
        }
    }

    public void Show_Message_uithread(final String message)
    {
        Thread myThread = new Thread() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Show_Message(message);
                    }
                });
                super.run();
            }
        };
        myThread.start();
    }


    public void showprogress_uithread()
    {
        Thread myThread = new Thread() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        showprogess();
                    }
                });
                super.run();
            }
        };
        myThread.start();
    }

    public void stopprogress_uithread()
    {
        Thread myThread = new Thread() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        stopprogress();
                    }
                });
                super.run();
            }
        };
        myThread.start();
    }
}




