package com.hyunjine.notification

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class ForegroundService: Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "ForegroundService is started")
        NotificationSettingManager(this).apply {
            createNotificationChannel(SERVICE_NOTIFICATION_CHANNEL_ID, "포그라운드")
            startForeground(
                SERVICE_NOTIFICATION_ID,
                getNotification(
                    "서비스 실행 중",
                    "포그라운드 서비스가 실행 중 입니다. 앱을 종료하지 말아주세요."
                )
            )
        }
    }

    override fun onDestroy() {
        Log.d(TAG, "ForegroundService is destroy")
        super.onDestroy()
    }
    override fun onBind(p0: Intent?): IBinder? = null
}