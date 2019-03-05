package net.agasper.unitynotification;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

/**
 * Created by gileadis on 11/14/17.
 */

public class UnityNotificationActionHandler extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int id = intent.getIntExtra("id", 0);
        String gameObject = intent.getStringExtra("gameObject");
        String handlerMethod = intent.getStringExtra("handlerMethod");
        String actionId = intent.getStringExtra("actionId");
        String bundle = intent.getStringExtra("bundle");
        boolean foreground = intent.getBooleanExtra("foreground", true);

        Log.i("UnityNotificationAction", "package=" +bundle+ ",gameObject=" + gameObject +",handlerMethod =" + handlerMethod +",actionId"+ actionId);
        //调用unity 的方法
        UnityPlayer.UnitySendMessage(gameObject, handlerMethod, actionId);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(id);


        Intent notificationIntent = context.getPackageManager().getLaunchIntentForPackage(bundle);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(notificationIntent);
        context.startActivity(notificationIntent);

        //        if (foreground) {
//            Intent launchIntent = new Intent(context, UnityPlayerActivity.class);
//            launchIntent.setPackage(null);
//            launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//            context.startActivity(launchIntent);
//        }


    }
}
