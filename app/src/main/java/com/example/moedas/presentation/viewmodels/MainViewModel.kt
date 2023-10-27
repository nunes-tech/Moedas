package com.example.moedas.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moedas.domain.models.Moedas
import com.example.moedas.domain.usecase.MoedasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val moedasUseCase: MoedasUseCase
) : ViewModel() {

    private val _moedas = MutableLiveData<Moedas?>()
    val moedas: LiveData<Moedas?> get() = _moedas

    fun recuperarMoedasAPI(){
        CoroutineScope(Dispatchers.IO).launch {
            val moedasApi = moedasUseCase()
            if (moedasApi != null)
                _moedas.postValue(moedasApi)
        }
    }
}