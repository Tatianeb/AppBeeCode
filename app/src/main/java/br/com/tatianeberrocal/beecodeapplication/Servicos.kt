package br.com.tatianeberrocal.beecodeapplication

import com.google.gson.GsonBuilder
import java.io.Serializable

class Servicos:Serializable {
        var id:Long = 0
        var nome = ""
        var preco = ""
        var foto = ""
        var fornecedor = ""

        override fun toString(): String {
            return "Servicos(nome='$nome')"
        }

        fun toJson(): String {
                return GsonBuilder().create().toJson(this)
        }
}