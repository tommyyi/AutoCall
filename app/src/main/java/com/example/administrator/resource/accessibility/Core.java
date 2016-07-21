package com.example.administrator.resource.accessibility;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/7/21.
 */
public class Core extends AccessibilityService
{
    public static final String INSTALL = "安装";
    public static final String OPEN = "打开";
    public static final String WIDGET_CLASS = "android.widget.TextView";
    final String TAG = Core.class.getSimpleName();

    String installPackge[] = {"com.android.packageinstaller"};

    @Override
    protected void onServiceConnected()
    {
        super.onServiceConnected();
        //可用代码配置当前Service的信息
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.packageNames = installPackge; //监听过滤的包名
        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK; //监听哪些行为
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN; //反馈
        info.notificationTimeout = 100; //通知的时间
        setServiceInfo(info);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void findAndPerformAction(String buttonLabel)
    {
        //通过文字找到当前的节点
        List<AccessibilityNodeInfo> nodes = getRootInActiveWindow().findAccessibilityNodeInfosByText(buttonLabel);
        if (nodes == null || nodes.size() == 0)
        {
            return;
        }

        for (int i = 0; i < nodes.size(); i++)
        {
            AccessibilityNodeInfo node = nodes.get(i);
            Log.e(TAG, "index=" + i + "");
            Log.e(TAG, buttonLabel + ": class name :" + node.getClassName().toString());
            Log.e(TAG, buttonLabel + ": text :" + node.getText().toString());
            // 执行按钮点击行为
            if (node.getClassName().equals(WIDGET_CLASS) && node.isEnabled() && node.getText().toString().equals(buttonLabel) && node.isClickable())
            {
                node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                Log.e(TAG, "Click " + buttonLabel);
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private boolean exist(String buttonLabel)
    {
        if (getRootInActiveWindow() == null)
        {
            return false;
        }

        List<AccessibilityNodeInfo> nodes = getRootInActiveWindow().findAccessibilityNodeInfosByText(buttonLabel);
        if (nodes == null || nodes.size() == 0)
        {
            return false;
        }

        Log.e(TAG, buttonLabel + ": target");
        return true;
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event)
    {
        if (event.getSource() != null)
        {
            if (exist(INSTALL))
            {
                findAndPerformAction(INSTALL);
            }
            if (exist(OPEN))
            {
                findAndPerformAction(OPEN);
            }
        }
    }

    @Override
    public void onInterrupt()
    {
    }
}
