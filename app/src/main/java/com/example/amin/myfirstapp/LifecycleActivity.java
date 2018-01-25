package com.example.amin.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Amin on 07/12/2017.
 */

public class LifecycleActivity extends AppCompatActivity{

    public static final String MY_FIRST_APP = "MyFirstApp";
    public static final String LOG_STATE_KEY = "log_state_key";
    TextView txtLog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

        txtLog = (TextView) findViewById(R.id.txt_Log);
        txtLog.setMovementMethod(new ScrollingMovementMethod());

//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_check);

        if (savedInstanceState != null && savedInstanceState.containsKey(LOG_STATE_KEY)){
             logMessage(savedInstanceState.getString(LOG_STATE_KEY));
        }else{
            txtLog.setText("");
        }

        logMessage("onCreate");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(LOG_STATE_KEY, txtLog.getText().toString()+"\n");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        logMessage("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        logMessage("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        logMessage("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        logMessage("onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        logMessage("onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logMessage("onDestroy");
    }

    private void logMessage(String str){
        Log.i(MY_FIRST_APP, str);
        txtLog.append(str + "\n");
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.LIFE_CYCLE_RESULT_KEY, "This is result that came from LifeCycleActivity!");
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }
}
