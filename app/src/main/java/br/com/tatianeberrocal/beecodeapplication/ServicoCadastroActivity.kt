package br.com.tatianeberrocal.beecodeapplication

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cadastro_servico.*

class ServicoCadastroActivity : DebugActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_servico)
        setTitle("Novo Serviço")

        salvarServico.setOnClickListener {
            val servicos = Servicos()
            servicos.nome = nomeServico.text.toString()
            servicos.preco = preco.text.toString()
            servicos.fornecedor = fornecedor.text.toString()
            servicos.foto = urlFoto.text.toString()

            taskAtualizar(servicos)
        }
    }

    private fun taskAtualizar(servicos: Servicos) {

        Thread {
            ServicosService.save(servicos)
            runOnUiThread {

                finish()
            }
        }.start()
    }
}
