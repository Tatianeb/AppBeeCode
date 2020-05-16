package br.com.tatianeberrocal.beecodeapplication

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cadastro_servico.*

class ServicoCadastroActivity : DebugActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_servico)
        setTitle("Novo Serviço")

        salvarServico.setOnClickListener {
            val servico = Servicos()
            servico.nome = nomeServico.text.toString()
            servico.preco = preco.text.toString()
            servico.fornecedor = fornecedor.text.toString()
            servico.foto = urlFoto.text.toString()

            taskAtualizar(servico)
        }
    }

    private fun taskAtualizar(servico: Servicos) {

        Thread {
            ServicosService.save(servico)
            runOnUiThread {

                finish()
            }
        }.start()
    }
}
