package com.example.administrator.resource;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Administrator on 2016/7/18.
 */
class MyFragmentPagerAdapter extends FragmentPagerAdapter
{
    private Activity mActivity;

    public MyFragmentPagerAdapter(Activity activity)
    {
        super(activity.getSupportFragmentManager());
        mActivity = activity;
    }

    @Override
    public Fragment getItem(int position)
    {
        return new MyFragment1();
//        if(position==0)
//        return new MyFragment1();
//        else
//        {
//            MyFragment2 myFragment2 = new MyFragment2();
//            myFragment2.setActivity(mActivity);
//            return myFragment2;
//        }
    }

    @Override
    public int getCount()
    {
        return mActivity.getResources().getInteger(R.integer.amount_of_pager);
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return mActivity.getResources().getStringArray(R.array.pagerTitleArray)[position];
    }
}
