package br.com.tatianeberrocal.beecodeapplication

import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.renderscript.Sampler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*

class TelaInicialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        var params = intent.extras
        var nome_usuario = params?.getString("nome_usuario")

        Toast.makeText(this,
            "Bem vindo $nome_usuario",
        Toast.LENGTH_LONG
        ).show()


        supportActionBar?.title = "Menu Principal"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        botao_servico.setOnClickListener {MostrarTelaBotao("Seviço")}
        botao_cadastro.setOnClickListener {MostrarTelaBotao("Cadastro")}
        botao_produto.setOnClickListener {MostrarTelaBotao("Produto")}

    }

    fun MostrarTelaBotao(nomeBotao: String) {
        //Toast.makeText(context, "$nomeBotao", Toast.LENGTH_LONG).show()

        // criar intent
        val intentpag = Intent(this, MainPaginas::class.java)
        // colocar parâmetros (opcional)
        val params = Bundle()
        params.putString("NomeTela", nomeBotao)
        intentpag.putExtras(params)

        // fazer a chamada esperando resultado
        startActivityForResult(intentpag, 1)

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item?.itemId
       if (id == R.id.action_buscar){
          Toast.makeText(this, "Clicou em Buscar", Toast.LENGTH_LONG).show()
       }  else if (id == R.id.action_atualizar){

               progress_bar.visibility = View.VISIBLE


           Toast.makeText(this, "Clicou em Atualizar", Toast.LENGTH_SHORT).show()

       } else if (id == android.R.id.home){
           finish()
       }else if (id == R.id.action_config){
           var intent = Intent(this, MainConfiguracao::class.java)
           startActivity(intent)
           Toast.makeText(this, "Clicou em configurações", Toast.LENGTH_SHORT).show()
       }
        return super.onOptionsItemSelected(item)

    }




}
