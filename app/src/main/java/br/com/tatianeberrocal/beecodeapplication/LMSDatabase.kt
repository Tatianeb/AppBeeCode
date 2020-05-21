package br.com.tatianeberrocal.beecodeapplication

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Servicos::class), version = 1)
abstract class LMSDatabase: RoomDatabase() {
    abstract fun servicoDAO(): ServicoDAO
}