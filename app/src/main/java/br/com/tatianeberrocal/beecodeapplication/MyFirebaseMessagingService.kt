package br.com.tatianeberrocal.beecodeapplication

import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    val TAG = "firebase"
    override fun onNewToken(token: String?) {
        super.onNewToken(token)
        Log.d(TAG, "Novo token: $token")
        Prefs.setString("FB_TOKEN", token!!)
    }

    // recebe a notificação de push
    override fun onMessageReceived(mensagemRemota: RemoteMessage?) {
        Log.d(TAG, "onMessageReceived")

        if (mensagemRemota?.notification != null) {
            val titulo = mensagemRemota?.notification?.title
            val corpo = mensagemRemota?.notification?.body
            Log.d(TAG, "Título: $titulo")
            Log.d(TAG, "Corpo: $corpo")

            val intent = Intent(this, ServicoActivity::class.java)
            NotificationUtil.create(this,1, intent, titulo!!, corpo!!)
        }
    }
}

