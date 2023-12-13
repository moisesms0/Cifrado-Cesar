package com.example.cifradocesar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cifrarButton = findViewById<Button>(R.id.cifrar)
        val palabra = findViewById<EditText>(R.id.palabra)
        val desplazamiento = findViewById<EditText>(R.id.desplazamiento)
        val resultado = findViewById<TextView>(R.id.resultado)


        cifrarButton.setOnClickListener {
            if (palabra != null && desplazamiento != null) {
                val palabraCifrada = cifrarCesar(palabra.text.toString(), desplazamiento.text.toString().toInt())
                resultado.text = palabraCifrada
            } else {

            }
        }



    }

    fun cifrarCesar(palabra: String, desplazamiento: Int): String {
        val alfabeto = 'A'.code..'Z'.code
        return palabra.map { caracter ->
            when {
                caracter.isLetter() -> {
                    val base = if (caracter.isLowerCase()) 'a'.toInt() else 'A'.code
                    (((caracter.code - base + desplazamiento) % 26 + 26) % 26 + base).toChar()
                }
                else -> caracter
            }
        }.joinToString("")
    }

}