package app.strada.sagv

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import app.strada.sagv.DataClasses.CategoriaProducto
import app.strada.sagv.DataClasses.ContenidoOrden
import app.strada.sagv.DataClasses.Producto

class Menu : AppCompatActivity() {

    private lateinit var txtPlatilloSeleccionado: TextView
    private val listaOrdenesMesa = mutableListOf<ContenidoOrden>()
    private var productoSeleccionado: Producto? = null

    private val listaProductos = listOf(
        Producto.crear("Orden Asada", 60.0f, "4 tacos de asada de puerco", CategoriaProducto.PLATILLO),
        Producto.crear("Orden Pastor", 60.0f, "4 tacos de pastor de puerco", CategoriaProducto.PLATILLO),
        Producto.crear("Alambre", 80.0f, "Carne, queso y verdura", CategoriaProducto.PLATILLO)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            return@setOnApplyWindowInsetsListener insets
        }

        val btnDecrementar = findViewById<Button>(R.id.btnDecrementar)
        val btnIncrementar = findViewById<Button>(R.id.btnIncrementar)
        val linearPlatillos = findViewById<LinearLayout>(R.id.linearPlatillos)
        val txtCantidad = findViewById<TextView>(R.id.txtCantidad)
        txtPlatilloSeleccionado = findViewById<TextView>(R.id.txtPlatilloSeleccionado)
        val btnAgregarOrden = findViewById<Button>(R.id.btnAgregar)
        val etNotas = findViewById<EditText>(R.id.editNotas)

        agregarBotonesDinamicos(linearPlatillos)

        btnAgregarOrden.setOnClickListener {
            if (txtCantidad.text.toString().toInt() == 0) {
                makeText(this, "Ingresa un platillo", LENGTH_LONG).show()
            } else if (productoSeleccionado == null) {
                makeText(this, "Selecciona un platillo", LENGTH_LONG).show()
            } else {
                // Registrar la orden
                val ordenARegistrar = ContenidoOrden.crear(
                    productoSeleccionado!!,
                    txtCantidad.text.toString().toInt(),
                    etNotas.text.toString()
                )
                listaOrdenesMesa.add(ordenARegistrar)
                makeText(this, "Orden registrada", LENGTH_LONG).show()
            }
        }

        btnDecrementar.setOnClickListener {
            if (txtCantidad.text.toString().toInt() > 0) {
                val decrementarProducto = txtCantidad.text.toString().toInt() - 1
                txtCantidad.text = decrementarProducto.toString()
            }
        }

        btnIncrementar.setOnClickListener {
            val incrementarProducto = txtCantidad.text.toString().toInt() + 1
            txtCantidad.text = incrementarProducto.toString()
        }
    }

    private fun agregarBotonesDinamicos(contenedor: LinearLayout) {
        listaProductos.forEach { producto ->
            val button = Button(this).apply {
                text = producto.nombre
                textSize = 16f
                setTextColor(resources.getColor(android.R.color.white, theme))
                background = ContextCompat.getDrawable(this@Menu, R.drawable.button_background)
                setPadding(16, 16, 16, 16)
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(16, 8, 16, 8)
                }
                setOnClickListener {
                    makeText(this@Menu, "Seleccionaste: ${producto.nombre}", LENGTH_SHORT).show()
                    txtPlatilloSeleccionado.text = producto.nombre
                }
            }
            contenedor.addView(button)
        }
    }
}
