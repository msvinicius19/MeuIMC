package com.example.calculadora10

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextWeight: EditText
    private lateinit var editTextHeight: EditText
    private lateinit var buttonCalculate: Button
    private lateinit var textViewResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextWeight = findViewById<EditText>(R.id.editTextWeight)
        editTextHeight = findViewById<EditText>(R.id.editTextHeight)
        buttonCalculate = findViewById<Button>(R.id.buttonCalculate)
        textViewResult = findViewById<TextView>(R.id.textViewResult)

        buttonCalculate.setOnClickListener {
            calculateIMC()
        }

    }

    private fun calculateIMC() {
        val pesoStr = editTextWeight.text.toString()
        val alturaStr = editTextHeight.text.toString()

        if (TextUtils.isEmpty(pesoStr) || TextUtils.isEmpty(alturaStr)) {
            textViewResult.text = "Coloque seu peso e altura"
            return
        }

        val peso = pesoStr.toFloat()
        val altura = alturaStr.toFloat()

        val imc = peso / (altura * altura)

        displayResult(imc)
    }

    private fun displayResult(imc: Float) {
        val result: String = when {
            imc < 18.5 -> "Abaixo do peso\n Seu IMC indica que você está abaixo do peso. Consulte um " +
                    "profissional para orientação sobre como alcançar um peso saudável."
            imc < 25 -> "Peso normal\n Parabéns! Seu IMC está na faixa saudável. Continue com hábitos " +
                    "saudáveis para manter essa condição."
            imc < 30 -> "Sobrepeso\n Considere escolhas alimentares mais " +
                    "saudáveis e consulte um profissional para orientações."
            else -> "Obeso\n Consulte um profissional para um " +
                    "plano personalizado visando um peso mais saudável."
        }

        textViewResult.text = String.format("IMC: %.2f\n%s",imc, result)
    }
}
