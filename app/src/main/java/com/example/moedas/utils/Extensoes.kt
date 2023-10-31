package com.example.moedas.utils

object Extensoes {

    fun String.format2DecimalPlaces() : String{
        if (this.length > 4) {
            return String.format("%.2f", this.toDouble())
        }
        return this
    }
}