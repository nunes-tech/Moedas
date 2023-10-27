package com.example.moedas.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.example.moedas.databinding.ActivityMainBinding
import com.example.moedas.domain.models.Moedas
import com.example.moedas.presentation.viewmodels.MainViewModel
import com.example.moedas.utils.Extensoes.realFormat2CasasDec
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    val mainViewModel by viewModels<MainViewModel>()
    private lateinit var allMoedas: Moedas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configurarSpnner()

        mainViewModel.recuperarMoedasAPI()

        mainViewModel.moedas.observe(this){ moedas ->
            if (moedas != null) {
                allMoedas = moedas
                binding.textMoedaInfo.text = allMoedas.dolarEmBr.nome
                binding.editTextNoBr.hint = "1${allMoedas.dolarEmBr.codigo}"
                binding.editTextBr.hint = allMoedas.dolarEmBr.valor.realFormat2CasasDec()
            }

        }
    }

    private fun configurarSpnner(){
        val opcoes = arrayOf(
            "Dolar",
            "Euro",
            "Bitcoin"
        )

        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcoes)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerOpcoes.adapter = spinnerAdapter

        binding.spinnerOpcoes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapter: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == 0 && (::allMoedas.isInitialized)) {

                        binding.textMoedaInfo.text = allMoedas.dolarEmBr.nome
                        binding.editTextNoBr.hint = "1${allMoedas.dolarEmBr.codigo}"
                        binding.editTextBr.hint = allMoedas.dolarEmBr.valor.realFormat2CasasDec()

                } else if (position == 1 && (::allMoedas.isInitialized)) {

                    binding.textMoedaInfo.text = allMoedas.euroEmBr.nome
                    binding.editTextNoBr.hint = "1${allMoedas.euroEmBr.codigo}"
                    binding.editTextBr.hint = allMoedas.euroEmBr.valor.realFormat2CasasDec()

                } else if (position == 2 && (::allMoedas.isInitialized)) {

                    binding.textMoedaInfo.text = allMoedas.bitcoinEmBr.nome
                    binding.editTextNoBr.hint = "1${allMoedas.bitcoinEmBr.codigo}"
                    binding.editTextBr.hint = allMoedas.bitcoinEmBr.valor.realFormat2CasasDec()

                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) { }

        }

    }

}