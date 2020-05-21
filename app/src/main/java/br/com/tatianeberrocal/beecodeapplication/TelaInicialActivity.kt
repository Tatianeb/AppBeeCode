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
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.adapter_servico.*
import kotlinx.android.synthetic.main.toolbar.*

class TelaInicialActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val context: Context get() = this
    private var servicos = listOf<Servicos>()
    private var REQUEST_CADASTRO = 1
    private var REQUEST_REMOVE= 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        // var params = intent.extras
        // var nome_usuario = params?.getString("nome_usuario")

        // Toast.makeText(this,
        //    "Bem vindo $nome_usuario",
        // Toast.LENGTH_LONG
        // ).show()
        val args:Bundle? = intent.extras
        // colocar toolbar
        setSupportActionBar(toolbar)

        supportActionBar?.title = "Menu Principal"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        configuraMenuLateral()
        recyclerServicos?.layoutManager = LinearLayoutManager(context)
        recyclerServicos?.itemAnimator = DefaultItemAnimator()
        recyclerServicos?.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        taskServicos()
    }

    fun taskServicos() {
        Thread {
            this.servicos = ServicosService.getServicos(context)
            runOnUiThread {
                recyclerServicos?.adapter = ServicoAdapter(this.servicos) { onClickServico(it) }
                enviaNotificacao(this.servicos.get(2))
            }
        }.start()
    }

    fun enviaNotificacao(servicos: Servicos) {
        val intent = Intent(this, ServicoActivity::class.java)
        intent.putExtra("servico", servicos)
        NotificationUtil.create(this, 1, intent, "BeeCode", "Você tem nova atividade na ${servicos.nome}")
    }

    fun onClickServico(servicos: Servicos) {
        Toast.makeText(context, "Clicou servico ${servicos.nome}", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, ServicoActivity::class.java)
        intent.putExtra("servico", servicos)
        startActivityForResult(intent, REQUEST_REMOVE)
    }

    private fun configuraMenuLateral() {
        // ícone de menu (hamburger) para mostrar o menu
        var toogle = ActionBarDrawerToggle(this, layoutMenuLateral, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

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
                var intent = Intent(this, MapasActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_config -> {
                Toast.makeText(this, "Clicou Config", Toast.LENGTH_SHORT).show()
            }
        }

        layoutMenuLateral.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main, menu)
        // vincular evento de buscar
        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                // ação enquanto está digitando
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                // ação  quando terminou de buscar e enviou
                return false
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // id do item clicado
        val id = item?.itemId
        // verificar qual item foi clicado e mostrar a mensagem Toast na tela
        // a comparação é feita com o recurso de id definido no xml
        if  (id == R.id.action_buscar) {
            Toast.makeText(context, "Botão de buscar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_atualizar) {
            Toast.makeText(context, "Botão de atualizar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_config) {
            Toast.makeText(context, "Botão de configuracoes", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_adicionar) {
            // iniciar activity de cadastro
            val intent = Intent(context, ServicoCadastroActivity::class.java)
            startActivityForResult(intent, REQUEST_CADASTRO)
        }
        // botão up navigation
        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CADASTRO || requestCode == REQUEST_REMOVE ) {
            taskServicos()
        }
    }
}
