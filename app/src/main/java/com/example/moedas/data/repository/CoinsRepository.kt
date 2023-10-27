package com.example.moedas.data.repository

import android.annotation.SuppressLint
import com.example.moedas.data.api.AwesomeAPI
import com.example.moedas.data.dto.toMoedas
import com.example.moedas.domain.models.Moedas
import javax.inject.Inject

class CoinsRepository @Inject constructor(
    private val awesomeAPI: AwesomeAPI
) : ICoinsRepository {

    @SuppressLint("SuspiciousIndentation")
    override suspend fun getAllCoins() : Moedas? {
       try {
          val call = awesomeAPI.getCoins()
           if (call.isSuccessful){
               val response = call.body()
                val moedas = response?.toMoedas()
                    return moedas
           }
       } catch (erro: Exception) {
           erro.printStackTrace()
           return null
       }
        return null
    }

}