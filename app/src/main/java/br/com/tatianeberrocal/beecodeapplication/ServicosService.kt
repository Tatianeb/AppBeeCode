package br.com.tatianeberrocal.beecodeapplication

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URL

object ServicosService {
    val host = "https://tatianeberrocal.pythonanywhere.com"
    val TAG = "beecode"

    fun getServicos (context: Context): List<Servicos> {
        var servicos = ArrayList<Servicos>()
        if (AndroidUtils.isInternetDisponivel()) {
            val url = "$host/servicos"
            val json = HttpHelper.get(url)
            servicos = parserJson(json)

            for (d in servicos) {
                saveOffline(d)
            }
            return servicos
        } else {
            val dao = DatabaseManager.getServicoDAO()
            val servicos = dao.findAll()
            return servicos
        }

    }

    fun save(servicos: Servicos): Response {
        if (AndroidUtils.isInternetDisponivel()) {
            val json = HttpHelper.post("$host/servicos", servicos.toJson())
            return parserJson(json)
        }
        else {
            saveOffline(servicos)
            return Response("OK", "Disciplina salva no dispositivo")
        }
    }

    fun saveOffline(servicos: Servicos) : Boolean {
        val dao = DatabaseManager.getServicoDAO()

        if (! existeServico(servicos)) {
            dao.insert(servicos)
        }

        return true
    }

    fun existeServico(servicos: Servicos): Boolean {
        val dao = DatabaseManager.getServicoDAO()
        return dao.getById(servicos.id) != null
    }

    fun delete(servicos: Servicos): Response {
        if (AndroidUtils.isInternetDisponivel()) {
            val url = "$host/servicos/${servicos.id}"
            val json = HttpHelper.delete(url)

            return parserJson(json)
        } else {
            val dao = DatabaseManager.getServicoDAO()
            dao.delete(servicos)
            return Response(status = "OK", msg = "Dados salvos localmente")
        }

    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}