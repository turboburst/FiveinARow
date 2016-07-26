package com.demo.turbo.fiveinarowmodule;

import android.app.Application;
import android.content.Intent;
import android.os.Handler;
import java.util.Random;

/**
 * Created by turbo on 2016/5/18.
 */
public class MyApplication extends Application
{
    private static MyApplication instance;
    private static final int Log_Length = 7000;

    public int[] backgrounds;
    public Random ran;
    public int ran_index;
    @Override
    public void onCreate()
    {
        super.onCreate();
        backgrounds = new int[]{R.mipmap.background1, R.mipmap.background2, R.mipmap.background3, R.mipmap.background4, R.mipmap.background5};
        ran = new Random();
        ran_index = ran.nextInt(backgrounds.length);
        instance = this;

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                startService(new Intent(getApplicationContext(), AudioService.class));
            }
        }, Log_Length);
    }

    public static MyApplication getInstance()
    {
        return instance;
    }
}
