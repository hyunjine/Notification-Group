package com.hyunjine.notification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import com.hyunjine.notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val notificationSettingManager = NotificationSettingManager(this@MainActivity)
        with(binding) {
            btnNotification.setOnClickListener {
            with(notificationSettingManager) {
                createNotificationChannel(NOTIFICATION_CHANNEL_ID, "알림")
                showNotification(SystemClock.elapsedRealtime().toInt(), "제목", "메세지")
            }
                notificationSettingManager.createNotificationChannel(NOTIFICATION_CHANNEL_ID, "알람")

            }
            btnStartService.setOnClickListener {
                val intent = Intent(this@MainActivity, ForegroundService::class.java)
                startForegroundService(intent)
            }
            btnFinishService.setOnClickListener {
                val intent = Intent(this@MainActivity, ForegroundService::class.java)
                stopService(intent)
            }
        }
    }
}