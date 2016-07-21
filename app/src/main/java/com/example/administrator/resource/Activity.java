package com.example.administrator.resource;

import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class Activity extends AppCompatActivity
{
    boolean flag = false;
    private Button mButton;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aboutToolbar();
        aboutViewPager();
    }

    private void aboutViewPager()
    {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);

        initiateViewPager();
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initiateViewPager()
    {
        mViewPager.setAdapter(new MyFragmentPagerAdapter(this));
        mViewPager.setOffscreenPageLimit(mViewPager.getChildCount());
    }

    private void aboutToolbar()
    {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mButton = (Button) findViewById(R.id.hello);
        setSupportActionBar(mToolbar);

        try
        {
            //            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbarmenu, menu);
        MenuItem menuItem = menu.findItem(R.id.share);
        android.support.v7.widget.ShareActionProvider provider = (android.support.v7.widget.ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

        Intent shareIntent = getSharedIntent();
        provider.setShareIntent(shareIntent);
        return super.onCreateOptionsMenu(menu);
    }

    @NonNull
    private Intent getSharedIntent()
    {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(getFilesDir(), "foo.jpg")).toString());
        return shareIntent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return super.onOptionsItemSelected(item);
    }

    public void doit(View view)
    {
        flag = !flag;

        if (flag)
        {
            mButton.setBackgroundResource(R.drawable.transition2);
        }
        else
        {
            mButton.setBackgroundResource(R.drawable.transition1);
        }

        TransitionDrawable transitionDrawable = (TransitionDrawable) mButton.getBackground();
        transitionDrawable.startTransition(2000);
    }

    public void send(MenuItem item)
    {
        Toast.makeText(getApplicationContext(), "send", Toast.LENGTH_LONG).show();
    }

}
