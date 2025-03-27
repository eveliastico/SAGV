package app.strada.sagv

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import app.strada.sagv.DataClasses.ItemProducto

class ResumenOrden : AppCompatActivity() {

    private lateinit var listaItemsAgregados: List<ItemProducto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resumen_orden)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        listaItemsAgregados = intent.getParcelableArrayListExtra<ItemProducto>("listaItems")!!

//        val productos = listOf(
//            "Orden Asada" to 2,
//            "Alambre" to 1,
//            "Quezadilla" to 4,
//            "Agua Piña" to 6,
//            "Pay Limon" to 4
//        )

        // Contenedor donde se agregarán los productos
        val linearResumen = findViewById<LinearLayout>(R.id.linearResumen)

        listaItemsAgregados.forEach { (nombre, cantidad) ->
            val productoView = layoutInflater.inflate(R.layout.activity_item_producto2, linearResumen, false)

            // Configuración del nombre del producto
            val txtProducto = productoView.findViewById<TextView>(R.id.txtNombreProducto)
            txtProducto.text = nombre

            // Configuración de la cantidad inicial
            val txtCantidad = productoView.findViewById<TextView>(R.id.txtCantidad)
            txtCantidad.setText(cantidad.toString())

            // Botón de sumar
            val btnSumar = productoView.findViewById<ImageButton>(R.id.btnAumentar)
            btnSumar.setOnClickListener {
                val cantidadActual = txtCantidad.text.toString().toInt()
                txtCantidad.setText((cantidadActual + 1).toString())
            }

            // Botón de restar
            val btnRestar = productoView.findViewById<ImageButton>(R.id.btnDisminuir)
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