package com.example.moedas.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.example.moedas.databinding.ActivityMainBinding
import com.example.moedas.domain.models.Moeda
import com.example.moedas.domain.models.Moedas
import com.example.moedas.domain.models.toMoeda
import com.example.moedas.presentation.viewmodels.MainViewModel
import com.example.moedas.utils.Extensoes.format2DecimalPlaces
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    val mainViewModel by viewModels<MainViewModel>()
    private lateinit var allMoedas: Moedas
    private lateinit var moedaSelecionada: Moeda

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configurarSpnner()
        setEventsChangeListiner()

        mainViewModel.recuperarMoedasAPI()

        mainViewModel.moedas.observe(this){ moedas ->
            if (moedas != null) {
                allMoedas = moedas
                moedaSelecionada = allMoedas.dolarEmBr.toMoeda()
                binding.textMoedaInfo.text = moedaSelecionada.nome
                exibirResultadoNoCampo(moedaSelecionada.valor, "real")
                exibirResultadoNoCampo("1.0", "outra")
            }

        }
    }

    private fun setEventsChangeListiner() {
        binding.editTextNoBr.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {  }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    verificarValorDigitado(p0, "real")
                }

                override fun afterTextChanged(p0: Editable?) {  }

            }
        )

        binding.editTextBr.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {  }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    verificarValorDigitado(p0, "outra")
                }

                override fun afterTextChanged(p0: Editable?) {  }

            }
        )
    }

    private fun verificarValorDigitado(textCampo: CharSequence?, opcao: String) {
        if (textCampo != null && !textCampo.startsWith(".")) {

           if(opcao == "real") {
               if (textCampo.isEmpty()){
                   binding.editTextBr.hint = "R$ " + moedaSelecionada.valor.format2DecimalPlaces()
               } else {
                   val resultado = calcularValorDigitado(textCampo, moedaSelecionada.valor)
                   exibirResultadoNoCampo(resultado.toString() ,opcao)
               }

           } else {
               if (textCampo.isEmpty()){
                   binding.editTextNoBr.hint = moedaSelecionada.codigo + " 1.0"
               } else {
                   val resultado = calcularValorDigitado(textCampo, moedaSelecionada.valorReal)
                   exibirResultadoNoCampo(resultado.toString() ,opcao)
               }
           }
        }
    }

    private fun exibirResultadoNoCampo(resultado: String, opcao: String) {
        when(opcao){
            "real" -> {
                binding.editTextBr.text?.clear()
                binding.editTextBr.hint = "R$ " + resultado.format2DecimalPlaces()
            }
            "outra" -> {
                binding.editTextNoBr.text?.clear()
                if(moedaSelecionada.codigo == "BTC"){
                    binding.editTextNoBr.hint = moedaSelecionada.codigo + " " + resultado
                } else {
                    binding.editTextNoBr.hint = moedaSelecionada.codigo + " " + resultado.format2DecimalPlaces()
                }
            }
        }
    }

    private fun calcularValorDigitado(p0: CharSequence, valorMoeda: String) : Double {
        return if (valorMoeda.isNotEmpty()) {
            p0.toString().toDouble() * valorMoeda.toDouble()
        } else {
            0.0
        }
    }


    private fun configurarSpnner(){
        val opcoes = arrayOf( "Dolar", "Euro", "Bitcoin" )

        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcoes)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerOpcoes.adapter = spinnerAdapter

        binding.spinnerOpcoes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapter: AdapterView<*>?, view: View?, position: Int, id: Long) {
                binding.editTextBr.text?.clear()
                binding.editTextNoBr.text?.clear()
                if (position == 0 && (::allMoedas.isInitialized)) {

                    moedaSelecionada = allMoedas.dolarEmBr.toMoeda()

                        binding.textMoedaInfo.text = allMoedas.dolarEmBr.nome
                        binding.editTextNoBr.hint = "${allMoedas.dolarEmBr.codigo} 1.0"
                        binding.editTextBr.hint = "R$ " + allMoedas.dolarEmBr.valor.format2DecimalPlaces()

                } else if (position == 1 && (::allMoedas.isInitialized)) {

                    moedaSelecionada = allMoedas.euroEmBr.toMoeda()

                    binding.textMoedaInfo.text = allMoedas.euroEmBr.nome
                    binding.editTextNoBr.hint = "${allMoedas.euroEmBr.codigo} 1.0"
                    binding.editTextBr.hint = "R$ " + allMoedas.euroEmBr.valor.format2DecimalPlaces()

                } else if (position == 2 && (::allMoedas.isInitialized)) {

                    moedaSelecionada = allMoedas.bitcoinEmBr.toMoeda()

                    binding.textMoedaInfo.text = allMoedas.bitcoinEmBr.nome
                    binding.editTextNoBr.hint = "${allMoedas.bitcoinEmBr.codigo} 1.0"
                    binding.editTextBr.hint = "R$ " + allMoedas.bitcoinEmBr.valor.format2DecimalPlaces()

                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) { }

        }

    }

}