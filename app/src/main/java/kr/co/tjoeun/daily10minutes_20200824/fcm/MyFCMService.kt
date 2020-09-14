package kr.co.tjoeun.daily10minutes_20200824.fcm

import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.os.Handler

//15:FirebaseMessage -> 16:MyFCMService firebase.MESSAGE_EVENT(AndroidMenifest.xml)
class MyFCMService : FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)

        Log.d("새로발급된기기토큰", p0)
    }

    //앱이꺼져있을경우 알림,앱이켜져있을경우 알림x
    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)

        Log.d("받은메세지.제목", p0.notification?.title.toString())
        Log.d("받은메세지.내용", p0.notification?.body.toString())

        //17:
        val myHandler = Handler(Looper.getMainLooper())
        myHandler.post {
            Toast.makeText(applicationContext, p0.notification?.title.toString(), Toast.LENGTH_SHORT).show()
        }
    }

}