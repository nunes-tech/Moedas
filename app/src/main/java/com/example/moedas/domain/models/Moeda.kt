package com.example.moedas.domain.models

data class Moeda(
    val valor: String,
    val codigo: String,
    val codigoPara: String,
    val nome: String,
    val valorReal: String = (1 / valor.toDouble()).toString()
)
