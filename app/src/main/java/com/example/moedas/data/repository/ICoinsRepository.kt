package com.example.moedas.data.repository

import com.example.moedas.domain.models.Moedas

interface ICoinsRepository {
    suspend fun getAllCoins() : Moedas?
}