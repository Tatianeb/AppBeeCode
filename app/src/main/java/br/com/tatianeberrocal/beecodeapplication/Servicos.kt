package br.com.tatianeberrocal.beecodeapplication

import java.io.Serializable

class Servicos:Serializable {
        var id:Long = 0
        var nome = ""
        var preco = ""
        var foto = ""
        var fornecedor = ""

        override fun toString(): String {
            return "Servico(nome='$nome')"
        }

}