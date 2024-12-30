package app.strada.sagv

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Menu : AppCompatActivity() {

    val btnDecrementar = findViewById<Button>(R.id.btnDecrementar)
    val btnIncrementar = findViewById<Button>(R.id.btnIncrementar)

    private val platillos = listOf(
        "Orden Pastor",
        "Orden Asada",
        "Alambre",
        "Quezadilla",
        "Taco Pastor",
        "Taco Asada",
        "Sope",
        "Burro",
        "Taco Pastor",
        "Taco Asada",
        "Sope",
        "Burro",
        "Taco Pastor",
        "Taco Asada",
        "Sope",
        "Burro"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            // Retornar el insets, ya que es necesario en este contexto
            return@setOnApplyWindowInsetsListener insets
        }

        val linearPlatillos = findViewById<LinearLayout>(R.id.linearPlatillos)

        // Crear botones dinámicos
        agregarBotonesDinamicos(linearPlatillos)

        // Obtener el parametro después de haber configurado la vista
        val parametro = intent.getStringExtra("categoria")
        makeText(this, "Parametro recibido: $parametro", LENGTH_LONG).show()

        val tvNumMesa = findViewById<TextView>(R.id.numMesa)
        val parametroNumMesa = intent.getStringExtra("mesaId")
        tvNumMesa.text = parametroNumMesa.toString()
    }

    private fun agregarBotonesDinamicos(contenedor: LinearLayout) {
        platillos.forEach { platillo ->
            val button = Button(this).apply {
                text = platillo
                textSize = 16f
                setTextColor(resources.getColor(android.R.color.white, theme))
                background = ContextCompat.getDrawable(this@Menu, R.drawable.button_background)
                setPadding(16, 16, 16, 16)

                // Margen a la izquierda
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(16, 8, 16, 8) // Margen izquierdo, arriba, derecho, abajo
                }

                // Listener de clic
                setOnClickListener {
                    makeText(this@Menu, "Seleccionaste: $platillo", LENGTH_SHORT).show()
                }
            }

            // Agregar el botón al contenedor
            contenedor.addView(button)
        }
    }

    private fun decrementarProductos(){
        btnDecrementar.setOnClickListener {

        }
    }

    private fun IncrementarProductos{
        btnIncrementar.setOnClickListener {

        }
    }

}