package app.strada.sagv

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResumenOrden : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resumen_orden)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val productos = listOf(
            "Orden Asada" to 2,
            "Alambre" to 1,
            "Quezadilla" to 4,
            "Agua Piña" to 6,
            "Pay Limon" to 4
        )

        // Contenedor donde se agregarán los productos
        val linearResumen = findViewById<LinearLayout>(R.id.linearResumen)

        productos.forEach { (nombre, cantidad) ->
            val productoView = layoutInflater.inflate(R.layout.item_producto, linearResumen, false)

            // Configuración del nombre del producto
            val txtProducto = productoView.findViewById<TextView>(R.id.txtProducto)
            txtProducto.text = nombre

            // Configuración de la cantidad inicial
            val txtCantidad = productoView.findViewById<EditText>(R.id.txtCantidad)
            txtCantidad.setText(cantidad.toString())

            // Botón de sumar
            val btnSumar = productoView.findViewById<Button>(R.id.btnSumar)
            btnSumar.setOnClickListener {
                val cantidadActual = txtCantidad.text.toString().toInt()
                txtCantidad.setText((cantidadActual + 1).toString())
            }

            // Botón de restar
            val btnRestar = productoView.findViewById<Button>(R.id.btnRestar)
            btnRestar.setOnClickListener {
                val cantidadActual = txtCantidad.text.toString().toInt()
                if (cantidadActual > 0) {
                    txtCantidad.setText((cantidadActual - 1).toString())
                }
            }

            // Agregar la vista al contenedor
            linearResumen.addView(productoView)
        }
    }
}