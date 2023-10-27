package com.example.moedas.domain.usecase

import com.example.moedas.data.repository.ICoinsRepository
import com.example.moedas.domain.models.Moedas
import javax.inject.Inject

class MoedasUseCase @Inject constructor(
    private val repository: ICoinsRepository
) {
    suspend operator fun invoke(): Moedas? {
        return repository.getAllCoins()
    }
}