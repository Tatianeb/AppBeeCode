package br.com.tatianeberrocal.beecodeapplication

import android.content.ClipData
import android.content.Context
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
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*

class TelaInicialActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {

    private val context: Context get() = this
    private var sevicos = listOf<Servicos>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        var params = intent.extras
        var nome_usuario = params?.getString("nome_usuario")

        Toast.makeText(this,
            "Bem vindo $nome_usuario",
        Toast.LENGTH_LONG
        ).show()

        // colocar toolbar
        setSupportActionBar(toolbar)

        supportActionBar?.title = "Menu Principal"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        configuraMenuLateral()
        // configurar cardview
        recyclerServicos?.layoutManager = LinearLayoutManager(context)
        recyclerServicos?.itemAnimator = DefaultItemAnimator()
        recyclerServicos?.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        taskServicos()
    }

    fun taskServicos() {
        this.sevicos = ServicosService.getServicos(context)
        // atualizar lista
        recyclerServicos?.adapter = ServicoAdapter(sevicos) {onClickServico(it)}
    }

    // tratamento do evento de clicar em uma disciplina
    fun onClickServico(servico: Servicos) {
        Toast.makeText(context, "Clicou servico ${servico.nome}", Toast.LENGTH_SHORT).show()
    }

    fun Tela(nomeBotao: String) {
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
    private fun configuraMenuLateral() {

        var toogle = ActionBarDrawerToggle(this, layoutMenuLateral, toolbar, R.string.nav_open, R.string.nav_close)

        layoutMenuLateral.addDrawerListener(toogle)
        toogle.syncState()

        menu_lateral.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_servicos -> {
                Toast.makeText(this, "Clicou Serviços", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_mensagens -> {
                Toast.makeText(this, "Clicou Mensagens", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_forum -> {
                Toast.makeText(this, "Clicou Forum", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_localizacao -> {
                Toast.makeText(this, "Clicou Localização", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_config -> {
                Toast.makeText(this, "Clicou Config", Toast.LENGTH_SHORT).show()
            }
        }

        // fecha menu depois de tratar o evento
        layoutMenuLateral.closeDrawer(GravityCompat.START)
        return true
    }
}
