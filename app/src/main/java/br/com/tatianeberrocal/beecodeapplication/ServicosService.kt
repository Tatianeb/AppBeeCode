package br.com.tatianeberrocal.beecodeapplication

import android.content.Context

object ServicosService {
    fun getServicos (context: Context): List<Servicos> {
        val servicos = mutableListOf<Servicos>()

        for (i in 1..10) {
            val d = Servicos()
            d.nome = "Servicos $i"
            d.preco = "Preço Servicos $i"
            d.fornecedor = "Fornecedor Servicos $i"
            d.foto = "https://catracalivre.com.br/wp-content/uploads/2015/01/banho.jpg"
            servicos.add(d)
        }

        return servicos
    }
}