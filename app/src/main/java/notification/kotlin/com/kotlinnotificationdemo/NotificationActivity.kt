package notification.kotlin.com.kotlinnotificationdemo

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class NotificationActivity : AppCompatActivity() {

    private var notificationManager:NotificationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel("notification.kotlin.com.kotlinnotificationdemo",
                "Notification Demo",
                "Demo Test")
    }

    private fun createNotificationChannel(id:String,name:String,description:String){

        val importance = NotificationManager.IMPORTANCE_LOW
        val channel = NotificationChannel(id,name,importance)
        channel.description = description
        channel.enableLights(true)
        channel.lightColor = Color.RED
        channel.enableVibration(true)
        channel.vibrationPattern = longArrayOf(100,200,300,400,500,400,300,200,400)

        notificationManager?.createNotificationChannel(channel)
    }

    fun sendNotification(view: View){

        val notificationId = 101

        val resultIntent = Intent(this, SecondActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(this,0,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT)


        val channelId= "notification.kotlin.com.kotlinnotificationdemo"

        val notification = Notification.Builder(this@NotificationActivity, channelId)
                .setContentTitle("Demo Notification")
                .setContentText("Hi Buddy")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setChannelId(channelId)
                .setContentIntent(pendingIntent)
                .build()

        notificationManager?.notify(notificationId, notification)

    }
}
