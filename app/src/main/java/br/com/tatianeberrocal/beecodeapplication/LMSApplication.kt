package br.com.tatianeberrocal.beecodeapplication

import android.app.Application
import java.lang.IllegalStateException

class LMSApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        // singleton
        private var appInstance: LMSApplication?  = null
        fun getInstance(): LMSApplication {
            if (appInstance == null) {
                throw IllegalStateException("Configurar application no Android Manifest")
            }
            return appInstance!!
        }
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}