package com.example.moedas.domain.models

data class Euro(
    val valor: String,
    val codigo: String,
    val codigoPara: String,
    val nome: String
)

fun Euro.toMoeda() : Moeda{
    return Moeda(this.valor, this.codigo, this.codigoPara, this.nome)
}
