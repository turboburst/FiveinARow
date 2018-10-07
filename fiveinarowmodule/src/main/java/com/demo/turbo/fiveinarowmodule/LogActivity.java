package com.demo.turbo.fiveinarowmodule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by turbo on 2016/5/20.
 */
public class LogActivity extends Activity
{
    private ImageView imageView1, imageView2, imageView3, imageView4, imageView5;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //Hello world
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logactivity_layout );
        imageView1 = (ImageView) findViewById(R.id.white1iconid);
        imageView2 = (ImageView) findViewById(R.id.black1iconid);
        imageView3 = (ImageView) findViewById(R.id.white2iconid);
        imageView4 = (ImageView) findViewById(R.id.black2iconid);
        imageView5 = (ImageView) findViewById(R.id.white3iconid);

        imageView1.setVisibility(View.INVISIBLE);
        imageView2.setVisibility(View.INVISIBLE);
        imageView3.setVisibility(View.INVISIBLE);
        imageView4.setVisibility(View.INVISIBLE);
        imageView5.setVisibility(View.INVISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView1.setVisibility(View.VISIBLE);
            }
        }, 1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView2.setVisibility(View.VISIBLE);
            }
        }, 2000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView3.setVisibility(View.VISIBLE);
            }
        }, 3000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView4.setVisibility(View.VISIBLE);
            }
        }, 4000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView5.setVisibility(View.VISIBLE);
            }
        }, 5000);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(LogActivity.this, MainActivity.class);
                LogActivity.this.startActivity(intent);
                LogActivity.this.finish();
            }
        }, 6000);
    }

}
