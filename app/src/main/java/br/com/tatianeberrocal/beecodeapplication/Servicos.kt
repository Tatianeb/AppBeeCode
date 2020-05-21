package br.com.tatianeberrocal.beecodeapplication

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName = "servico")
class Servicos:Serializable {

        @PrimaryKey
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