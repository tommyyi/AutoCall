package com.example.administrator.resource;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Administrator on 2016/7/18.
 */

public class SecondaryActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondarylayout);
    }

    public void doit(View view)
    {
        startActivity(new Intent(getApplicationContext(),Activity.class));
        finish();
    }
}
