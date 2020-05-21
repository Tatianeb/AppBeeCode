package br.com.tatianeberrocal.beecodeapplication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ServicoDAO {
    @Query("SELECT * FROM servico where id = :id")
    fun getById(id: Long) : Servicos?

    @Query("SELECT * FROM servico")
    fun findAll(): List<Servicos>

    @Insert
    fun insert(servico: Servicos)

    @Delete
    fun delete(servico: Servicos)
}