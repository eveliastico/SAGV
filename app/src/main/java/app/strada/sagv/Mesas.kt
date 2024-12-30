package app.strada.sagv

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity

class Mesas : AppCompatActivity() {
    private lateinit var btnAgregarMesa: Button
    private lateinit var gridMesas: GridLayout
    private var contadorMesas = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mesas)

        btnAgregarMesa = findViewById(R.id.btnAgregarMesa)
        gridMesas = findViewById(R.id.gridMesas)

        for (i in 1..8) {
            agregarMesa()
        }

        btnAgregarMesa.setOnClickListener {
            agregarMesa()
        }


    }



    private fun agregarMesa() {
        val intent = Intent(this, NuevaOrden::class.java)
        val nuevaMesa = Button(this).apply {
            text = "Mesa $contadorMesas"
            textSize = 16f
            setPadding(8, 8, 8, 8)
            setOnClickListener {
                var numMesa = this.text
                intent.putExtra("mesaId", numMesa)
                println("NUMERO DE MESA: $numMesa")
                startActivity(intent)
            }
        }

        val layoutParams = GridLayout.LayoutParams().apply {
            columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
            width = 0 // Distribuir uniformemente en columnas
        }
        nuevaMesa.layoutParams = layoutParams

        gridMesas.addView(nuevaMesa)
        contadorMesas++
    }

}