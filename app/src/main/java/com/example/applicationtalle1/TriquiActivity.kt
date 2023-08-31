package com.example.applicationtalle1


import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity


class TriquiActivity : AppCompatActivity() {
    private val casillas = arrayOfNulls<ImageView>(9)
    private var jugadorX = true // Jugador X comienza
    private val tablero = IntArray(9)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_triqui)
        val reiniciar = findViewById<Button>(R.id.B_Reset)
        reiniciar.setOnClickListener{
            reiniciarJuego()
        }

        casillas[0] = findViewById(R.id.Casilla1);
        casillas[1] = findViewById(R.id.Casilla2);
        casillas[2] = findViewById(R.id.Casilla3);
        casillas[3] = findViewById(R.id.Casilla4);
        casillas[4] = findViewById(R.id.Casilla5);
        casillas[5] = findViewById(R.id.Casilla6);
        casillas[6] = findViewById(R.id.Casilla7);
        casillas[7] = findViewById(R.id.Casilla8);
        casillas[8] = findViewById(R.id.Casilla9);
        for (i in 0..8) {
            casillas[i]!!.setOnClickListener { hacerJugada(i) }
        }


    }

    private fun hacerJugada(position: Int) {
        if (tablero[position] === 0) {
            @DrawableRes val imagenJugador: Int =
                if (jugadorX) R.drawable.ic_xicon else R.drawable.ic_oicon
            casillas[position]!!.setImageResource(imagenJugador)
            tablero[position] = if (jugadorX) 1 else 2

            // Verificar si hay un ganador o empate y mostrar mensaje si es necesario
            if (hayGanador()) {
                mostrarMensaje("El " + (if (jugadorX) "X" else "O") + " ha ganado!")

            } else if (hayEmpate()) {
                mostrarMensaje("Empate")
            }
            jugadorX = !jugadorX
        }
    }
    private fun hayGanador(): Boolean {
        val combinacionesGanadoras = arrayOf(
            intArrayOf(0, 1, 2),
            intArrayOf(3, 4, 5),
            intArrayOf(6, 7, 8),
            intArrayOf(0, 3, 6),
            intArrayOf(1, 4, 7),
            intArrayOf(2, 5, 8),
            intArrayOf(0, 4, 8),
            intArrayOf(2, 4, 6)
        )
        for (combinacion in combinacionesGanadoras) {
            val pos1 = combinacion[0]
            val pos2 = combinacion[1]
            val pos3 = combinacion[2]
            if (tablero[pos1] !== 0 && tablero[pos1] === tablero[pos2] && tablero[pos2] === tablero[pos3]) {
                return true // Hay un ganador
            }
        }
        return false // No hay un ganador en ninguna combinación
    }

    private fun hayEmpate(): Boolean {
        for (i in 0..8) {
            if (tablero[i] === 0) {
                return false // Todavía hay casillas vacías, no hay empate
            }
        }
        return true // No quedan casillas vacías, hay empate
    }
    private fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    private fun reiniciarJuego() {
        // Restablecer el estado del juego y las imágenes en las casillas
        jugadorX = true
        for (i in 0..8) {
            casillas[i]!!.setImageResource(R.drawable.whitebox)
            tablero[i] = 0
        }
    }


}