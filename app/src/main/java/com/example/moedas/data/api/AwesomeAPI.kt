package com.example.moedas.data.api

import com.example.moedas.data.dto.CoinsDTO
import com.example.moedas.utils.Constantes
import retrofit2.Response
import retrofit2.http.GET

interface AwesomeAPI {

    @GET(Constantes.ROTA_ALL_COINS)
    suspend fun getCoins() : Response<CoinsDTO>

}