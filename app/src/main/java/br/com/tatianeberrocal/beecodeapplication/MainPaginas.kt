package br.com.tatianeberrocal.beecodeapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.toolbar.*

class MainPaginas : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_paginas)
        setSupportActionBar(toolbar)

        val args = intent.extras
        val nomeTela = args?.getString("NomeTela")


        // colocar toolbar
        setSupportActionBar(toolbar)


        // alterar título da ActionBar
        supportActionBar?.title = nomeTela

    }

}
