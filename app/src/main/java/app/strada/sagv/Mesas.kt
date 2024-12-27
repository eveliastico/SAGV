package app.strada.sagv

import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Mesas : AppCompatActivity() {
    private lateinit var btnAgregarMesa: Button
    private lateinit var gridMesas: GridLayout
    private var contadorMesas = 8

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mesas)

        btnAgregarMesa = findViewById(R.id.btnAgregarMesa)
        gridMesas = findViewById(R.id.gridMesas)

        btnAgregarMesa.setOnClickListener {
            agregarMesa()
        }

    }

    private fun agregarMesa() {
        val nuevaMesa = Button(this).apply {
            text = "Mesa $contadorMesas"
            textSize = 16f
            setPadding(8, 8, 8, 8)
            setOnClickListener {
                // Acción al hacer clic en una mesa
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