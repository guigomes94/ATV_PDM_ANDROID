package com.example.banco

import java.util.*

class Pessoa {
    var id: Int
    var nome: String
    var datahora: Long

    // nova pessoa
    constructor(nome: String){
        this.id = -1
        this.nome = nome
        this.datahora = Calendar.getInstance().timeInMillis
    }

    // recuperar pessoa do banco
    constructor(id: Int, nome: String, datahora: Long){
        this.id = id
        this.nome = nome
        this.datahora = datahora
    }

    fun dataHora(): String{
        val c = Calendar.getInstance()
        c.timeInMillis = this.datahora
        val dt = "${c.get(Calendar.DAY_OF_MONTH)}/${c.get(Calendar.MONTH) + 1}/${c.get(Calendar.YEAR)}"
        val hr = "${c.get(Calendar.HOUR_OF_DAY)}:${c.get(Calendar.MINUTE)}:${c.get(Calendar.SECOND)}"
        return "${dt} ${hr}"
    }

    override fun toString(): String {
        return "${id} - ${nome} - ${dataHora()}"
    }
}