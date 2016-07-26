package com.demo.turbo.fiveinarowmodule;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Process;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    private LinearLayout linearLayout;
    private int BackGround_Ran_Index;
    private int BackGround_Res_ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = (LinearLayout)findViewById(R.id.MainActivityLayoutID);
        BackGround_Ran_Index = MyApplication.getInstance().ran_index;
        BackGround_Res_ID = MyApplication.getInstance().backgrounds[BackGround_Ran_Index];
        linearLayout.setBackgroundResource(BackGround_Res_ID);
    }

    @Override
    public void onBackPressed() {
        /*super.onBackPressed();*/
    }
}
