package co.za.openwindow.page.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import co.za.openwindow.page.MainActivity
import co.za.openwindow.page.Manifest
import co.za.openwindow.page.R

class MyNotification(
    private val context: Context
) {

    private var channelID = "Notification100"
    private var channelName = "MyNotification"

    val notificationManager = context.applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private lateinit var notificationChannel: NotificationChannel
    private lateinit var notificationBuilder: NotificationCompat.Builder

    fun showNotification(
        title: String,
        message: String
    ) {

        //.1 create notification channel
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            notificationChannel = NotificationChannel(
                channelID,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            )

            notificationManager.createNotificationChannel(notificationChannel)


            //2. building our notification values
            val intent = Intent(context, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

            notificationBuilder = NotificationCompat.Builder(context, channelID)
            notificationBuilder.setSmallIcon(R.drawable.ic_launcher_foreground) //<-- sets logo
            notificationBuilder.setContentTitle(title)
            notificationBuilder.setContentText(message)
            notificationBuilder.setAutoCancel(true)
            notificationBuilder.setContentIntent(pendingIntent)


            //3. Show/trigger the display of notification

            with(NotificationManagerCompat.from(context)) {

                if(ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
                    Log.d("AAA notification request", "DID NOT RECEIVE PERMISSION")
                }
                notify(100, notificationBuilder.build())
            }

        }


    }
}