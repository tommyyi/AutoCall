package com.example.administrator.resource.accessibility;

import java.util.List;

import com.example.administrator.resource.R;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Notification;
import android.os.Bundle;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/7/21.
 */
public class Core extends AccessibilityService
{
    public static final String IMAGE_BUTTON = "android.widget.ImageButton";
    private static final String COM_ANDROID_DIALER = "com.android.dialer";
    String installPackge[] = {COM_ANDROID_DIALER};

    @Override
    public void onCreate()
    {
        super.onCreate();
        Notification.Builder builder=new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setTicker("今日头条");
        builder.setContentTitle("this is title");
        builder.setContentText("this is content");
        builder.setWhen(System.currentTimeMillis());
        Notification notification = builder.build();

        startForeground(1, notification);
    }

    @Override
    protected void onServiceConnected()
    {
        super.onServiceConnected();
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.packageNames = installPackge;
        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN;
        info.notificationTimeout = 300;
        setServiceInfo(info);
        Toast.makeText(getApplicationContext(),"connected",Toast.LENGTH_LONG).show();
    }

    private void findImageAndPress(String viewId)
    {
        //through viewId to find
        List<AccessibilityNodeInfo> nodes = getRootInActiveWindow().findAccessibilityNodeInfosByViewId(COM_ANDROID_DIALER+":id/"+viewId);
        if (nodes == null || nodes.size() == 0)
        {
            return;
        }

        AccessibilityNodeInfo accessibilityNodeInfo = nodes.get(0);
        if (accessibilityNodeInfo.getClassName().equals(IMAGE_BUTTON))
            accessibilityNodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
    }

    private boolean isViewExist(String viewId)
    {
        //通过viewId找到当前的节点
        List<AccessibilityNodeInfo> nodes = getRootInActiveWindow().findAccessibilityNodeInfosByViewId(COM_ANDROID_DIALER+":id/"+viewId);
        if (nodes == null || nodes.size() == 0)
        {
            return false;
        }

        return true;
    }

    /*
    * com.android.dialer
    * id:
    *
    *android.widget.ImageView
    *
    * android.widget.ImageButton
    * com.android.dialer:id/one
    * com.android.dialer:id/two
    * com.android.dialer:id/three
    * com.android.dialer:id/four
    * com.android.dialer:id/five
    * com.android.dialer:id/six
    * com.android.dialer:id/seven
    * com.android.dialer:id/eight
    * com.android.dialer:id/nine
    * com.android.dialer:id/zero
    *
    * android.widget.EditText
    * com.android.dialer:id/digits
    *
    * android.widget.ImageButton
    * com.android.dialer:id/dialButton*/
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event)
    {
        Toast.makeText(getApplicationContext(),"onAccessibilityEvent--"+event.getEventType(),Toast.LENGTH_LONG).show();

        if(!isViewExist("dialButton")
                ||!isViewExist("one")
                ||!isViewExist("two")
                ||!isViewExist("three")
                ||!isViewExist("four")
                ||!isViewExist("five")
                ||!isViewExist("six")
                ||!isViewExist("seven")
                ||!isViewExist("eight")
                ||!isViewExist("nine")
                ||!isViewExist("zero")
                )
            return;

        clearDigits("digits");
        findImageAndPress("one");
        findImageAndPress("three");
        findImageAndPress("eight");
        findImageAndPress("two");
        findImageAndPress("five");
        findImageAndPress("two");
        findImageAndPress("five");
        findImageAndPress("eight");
        findImageAndPress("two");
        findImageAndPress("seven");
        findImageAndPress("nine");

        findImageAndPress("dialButton");
    }

    private void clearDigits(String digits)
    {
        List<AccessibilityNodeInfo> nodes = getRootInActiveWindow().findAccessibilityNodeInfosByViewId(COM_ANDROID_DIALER+":id/"+digits);
        if (nodes == null || nodes.size() == 0)
        {
            return;
        }

        AccessibilityNodeInfo accessibilityNodeInfo = nodes.get(0);
        Bundle arguments = new Bundle();
        arguments.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE,"");
        accessibilityNodeInfo.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT,arguments);
    }

    @Override
    public void onInterrupt()
    {
        Toast.makeText(getApplicationContext(),"onInterrupt",Toast.LENGTH_LONG).show();
        Log.d("receive event","onInterrupt");
    }
}
