package br.com.tatianeberrocal.beecodeapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import br.com.tatianeberrocal.beecodeapplication.R.drawable.imagem_login
import kotlinx.android.synthetic.main.login.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        img_login.setImageResource(imagem_login)


        botao_login.setOnClickListener {
            val textoUsuario = campo_usuario.text.toString()
            val textoSenha = campo_senha.text.toString()
            if (textoUsuario.equals("aluno")&&textoSenha.equals("impacta")){
                Toast.makeText(this,
                    "Certo:$textoUsuario",
                    Toast.LENGTH_SHORT)
                    .show()
                var intent = Intent(this, TelaInicialActivity::class.java)

                var param = Bundle()
                param.putString("nome_usuario", textoUsuario)
                intent.putExtras(param)

                startActivity(intent)
            }else {

                var alertDialog = AlertDialog.Builder(this)
                alertDialog.setTitle("Alerta") // O Titulo da notificação
                alertDialog.setMessage("Usuário ou senha incorretos") // a mensagem ou alerta

                alertDialog.show()
            }

        }


    }


}
