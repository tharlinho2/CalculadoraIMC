package com.tharles.calculadoradeimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.tharles.calculadoradeimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bt_calcular = binding.btCalcular
        val mensagem = binding.mensagem

        bt_calcular.setOnClickListener {

            val editPeso = binding.editPeso.text.toString()
            val editAltura = binding.editAltura.text.toString()

            if (editPeso.isEmpty()){
                mensagem.setText("Informe o seu Peso")
            }else if (editAltura.isEmpty()){
                mensagem.setText("Informe a sua Altura")
            }else{
                CalculoDeIMC()
            }

        }

    }

    private fun CalculoDeIMC(){
        val pesoID = binding.editPeso
        val alturaID = binding.editAltura

        val peso = Integer.parseInt(pesoID.text.toString())
        val altura = java.lang.Float.parseFloat(alturaID.text.toString())
        val resultado = binding.mensagem
        val imc = peso / (altura * altura)

        val Mensagem = when{
            imc <=18.5 -> "Peso Baixo"
            imc <=24.9 -> "Peso Normal"
            imc <=29.9 -> "sobrepeso"
            imc <=34.9 -> "Obesidade (Grau 1)"
            imc <=39.9 -> "Obesidade (Grau 2)"
            else -> "Obesidade Móbida (Grau 3)"
        }
        imc.toString()
        resultado.setText("IMC: $imc \n $Mensagem")

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflate = menuInflater
        inflate.inflate(R.menu.menu_principal,menu)

        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.reset ->{

                val limparEditPeso = binding.editPeso
                val limparEditAltura = binding.editAltura
                val limparMensagem = binding.mensagem

                limparEditPeso.setText("")
                limparEditAltura.setText("")
                limparMensagem.setText("")
            }
        }

        return super.onOptionsItemSelected(item)
    }
}