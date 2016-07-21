package com.example.administrator.resource;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Administrator on 2016/7/18.
 */
public class MyFragment1 extends Fragment
{
    private boolean flag=false;
    private Button mButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.fragment1, container, false);
        mButton=(Button)view.findViewById(R.id.hello);
        mButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                doit(view);
            }
        });
        return view;
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
}
