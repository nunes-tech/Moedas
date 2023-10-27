package com.example.moedas.utils

object Extensoes {

    fun String.realFormat2CasasDec() : String{
        if (this.length > 4) {
            return String.format("R$%.2f", this.toDouble())
        }
        return this
    }
}