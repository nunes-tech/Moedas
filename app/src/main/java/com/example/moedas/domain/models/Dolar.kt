package com.example.moedas.domain.models

data class Dolar(
    val valor: String,
    val codigo: String,
    val codigoPara: String,
    val nome: String
)

fun Dolar.toMoeda() : Moeda{
    return Moeda(this.valor, this.codigo, this.codigoPara, this.nome)
}
