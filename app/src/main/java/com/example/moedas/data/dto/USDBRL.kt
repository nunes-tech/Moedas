package com.example.moedas.data.dto

import com.example.moedas.domain.models.Dolar

data class USDBRL(
    val ask: String,
    val bid: String,
    val code: String,
    val codein: String,
    val create_date: String,
    val high: String,
    val low: String,
    val name: String,
    val pctChange: String,
    val timestamp: String,
    val varBid: String
)

fun USDBRL.toDolar() : Dolar {
    return Dolar(
        valor = this.ask,
        codigo = this.code,
        codigoPara = this.codein,
        nome = this.name
    )
}