package com.example.moedas.data.dto

import com.example.moedas.domain.models.Moedas

data class CoinsDTO(
    val BTCBRL: BTCBRL,
    val EURBRL: EURBRL,
    val USDBRL: USDBRL
)

fun CoinsDTO.toMoedas() : Moedas {
    return Moedas(
        dolarEmBr = this.USDBRL.toDolar(),
        euroEmBr = this.EURBRL.toEuro(),
        bitcoinEmBr = this.BTCBRL.toBitcoin()
    )
}