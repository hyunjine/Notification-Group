package com.hyunjine.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

class NotificationSettingManager(private val context: Context) {

    private val notificationManager: NotificationManager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    fun createNotificationChannel(channelId: String, channelName: String) {
        val notificationChannel = NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(notificationChannel)
    }

    fun getNotification(title: String, message: String): Notification {
        return NotificationCompat.Builder(context, SERVICE_NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(com.google.android.material.R.drawable.ic_keyboard_black_24dp)
            .setContentTitle(title)
            .setOnlyAlertOnce(true)
            .setContentText(message)
            .setContentIntent(getPendingIntent())
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
    }

    fun showNotification(notificationId: Int, title: String?, message: String?) {

        val builder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(com.google.android.material.R.drawable.ic_clock_black_24dp)
            .setContentTitle(title)
            .setGroup(NOTIFICATION_GROUP_NAME)
            .setOnlyAlertOnce(true)
            .setContentText(message)
            .setContentIntent(getPendingIntent())
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_SOUND)

        notificationManager.notify(notificationId, builder.build())
        notificationManager.notify(NOTIFICATION_GROUP_ID, getGroupNotification(context).build())
    }

    private fun getGroupNotification(context: Context): NotificationCompat.Builder {
        return NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(com.google.android.material.R.drawable.ic_mtrl_checked_circle)
            .setAutoCancel(true)
            .setContentIntent(getPendingIntent())
            .setOnlyAlertOnce(true)
            .setGroup(NOTIFICATION_GROUP_NAME)
            .setGroupSummary(true)
    }

    private fun getPendingIntent(): PendingIntent {
        val contentIntent = Intent(context, MainActivity::class.java).apply {
            action = Intent.ACTION_MAIN
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addCategory(Intent.CATEGORY_LAUNCHER)
        }
        return PendingIntent.getActivity(
            context,
            0,
            contentIntent,
            PendingIntent.FLAG_IMMUTABLE
        )
    }
}