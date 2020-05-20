package br.com.tatianeberrocal.beecodeapplication

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URL

object ServicosService {
    val host = "https://tatianeberrocal.pythonanywhere.com"
    val TAG = "beecode"

    fun getServicos (context: Context): List<Servicos>{
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/servicos"
            val json = HttpHelper.get(url)

        Log.d(TAG, json)
        return parserJson<List<Servicos>>(json)
        }else{
            return ArrayList()
        }
    }

    fun save(servicos: Servicos): Response {
        val json = HttpHelper.post("$host/servicos", servicos.toJson())
        return parserJson(json)
    }

    fun delete(servicos: Servicos): Response {
        Log.d(TAG, servicos.id.toString())
        val url = "$host/servicos/${servicos.id}"
        val json = HttpHelper.delete(url)
        Log.d(TAG, json)
        return parserJson(json)
    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}