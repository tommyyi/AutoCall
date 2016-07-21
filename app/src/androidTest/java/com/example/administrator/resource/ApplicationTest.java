package com.example.administrator.resource;

import android.app.NotificationManager;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ApplicationTest
{
    @Test
    public void testNotif() throws Exception
    {
        Context context = InstrumentationRegistry.getTargetContext();
        final android.support.v4.app.NotificationCompat.Builder builder = new android.support.v4.app.NotificationCompat.Builder(context);
        builder.setContentTitle("notification compatible");
        builder.setContentText("yesterday i get it");
        builder.setSmallIcon(android.R.drawable.ic_dialog_alert);
        final NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(123, builder.build());

        Thread.sleep(5000);
        notificationManager.cancel(123);
        Thread.sleep(100000);
    }
}