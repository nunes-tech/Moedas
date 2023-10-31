package com.example.moedas.domain.models

data class Bitcoin(
    val valor: String,
    val codigo: String,
    val codigoPara: String,
    val nome: String
)

fun Bitcoin.toMoeda() : Moeda{
    return Moeda(this.valor, this.codigo, this.codigoPara, this.nome)
}
