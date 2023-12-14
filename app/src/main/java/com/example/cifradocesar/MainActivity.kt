package com.example.cifradocesar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var resultado: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cifrarButton = findViewById<Button>(R.id.cifrar)
        val palabra = findViewById<EditText>(R.id.palabra)
        val desplazamiento = findViewById<EditText>(R.id.desplazamiento)
        resultado = findViewById<TextView>(R.id.resultado)


        cifrarButton.setOnClickListener {
            if (palabra != null && desplazamiento != null) {
                cifrarCorutina(palabra.text.toString(), desplazamiento.text.toString().toInt())
            } else {

            }
        }
    }


    fun cifrarCorutina(palabra: String, desplazamiento: Int) {

        CoroutineScope(Dispatchers.Main).launch {
            val palabraCifrada = cifrarCesar(palabra, desplazamiento)
            resultado.text = palabraCifrada
        }

    }

    fun cifrarCesar(palabra: String, desplazamiento: Int): String {
        val alfabeto = 'A'.code..'Z'.code
        return palabra.map { caracter ->
            when {
                caracter.isLetter() -> {
                    val base = if (caracter.isLowerCase()) 'a'.code else 'A'.code
                    (((caracter.code - base + desplazamiento) % 26 + 26) % 26 + base).toChar()
                }
                else -> caracter
            }
        }.joinToString("")
    }

}