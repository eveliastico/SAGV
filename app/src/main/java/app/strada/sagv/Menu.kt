package app.strada.sagv

import android.content.Intent
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
import androidx.lifecycle.lifecycleScope
import app.strada.sagv.DataClasses.CategoriaProducto
import app.strada.sagv.DataClasses.ContenidoOrden
import app.strada.sagv.DataClasses.Orden
import app.strada.sagv.DataClasses.Producto
import app.strada.sagv.apiService.APIClient
import app.strada.sagv.apiService.ApiProducto
import app.strada.sagv.dtos.ContenidoOrdenDTO
import app.strada.sagv.dtos.ProductoDTO
import kotlinx.coroutines.launch

class Menu : AppCompatActivity() {

    private lateinit var txtPlatilloSeleccionado: TextView
    private val listaOrdenesMesa = mutableListOf<ContenidoOrden>()
    private var productoSeleccionado: Producto? = null
    private lateinit var listaProductos: List<ProductoDTO>
    private lateinit var listaProductosAgregados: List<ProductoDTO>

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
        val btnSiguiente = findViewById<Button>(R.id.btnSiguiente)
        val linearPlatillos = findViewById<LinearLayout>(R.id.linearPlatillos)
        val txtCantidad = findViewById<TextView>(R.id.txtCantidad)
        var numeroMesa = findViewById<TextView>(R.id.numMesa)
        txtPlatilloSeleccionado = findViewById<TextView>(R.id.txtPlatilloSeleccionado)
        val btnAgregarOrden = findViewById<Button>(R.id.btnAgregar)
        val btnNoCebolla = findViewById<Button>(R.id.btnNoCebolla)
        val btnNoCilantro = findViewById<Button>(R.id.btnNoCilantro)
        val etNotas = findViewById<EditText>(R.id.editNotas)
        val categoria = intent.getStringExtra("categoria")

        // Se recive la Orden.
        val orden = intent.getParcelableExtra("orden", Orden::class.java)
        println("ORDEN RECIBIDA: " + orden.toString())

        //Checar que si se asigne el numero de mesa.
        // Si lo asigna xd
        numeroMesa.text = orden?.numMesa.toString()

        lifecycleScope.launch {
            try {
                val response = APIClient.apiProducto.getProductoByCategoria(categoria ?: "")
                println("RESPONSE API" + response.body())
                if (response.isSuccessful) {
                    // Se almacenan los productos en una lista, si es null se setea a una lista vacia.
                    listaProductos = response.body() ?: emptyList()
                    agregarBotonesDinamicos(linearPlatillos)
                } else {
                    Toast.makeText(
                        this@Menu,
                        "Error al obtener productos: ${response.message()}",
                        LENGTH_LONG
                    ).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@Menu, "Error de conexión: ${e.message}", LENGTH_LONG).show()
            }
        }
        /*
        * Si la categoria es Platillo se muestran los botones de no cebolla y no cilantro
        * si no, no se muestran los botones
        * */
        if(categoria == CategoriaProducto.PLATILLO.toString()){
            btnNoCebolla.visibility = Button.VISIBLE
            btnNoCilantro.visibility = Button.VISIBLE
        }else{
            btnNoCebolla.visibility = Button.GONE
            btnNoCilantro.visibility = Button.GONE
        }

        btnNoCebolla.setOnClickListener {
            var textoEt = etNotas.text
            etNotas.setText(textoEt.toString() + " Sin cebolla")
        }

        btnNoCilantro.setOnClickListener {
            var textoEt = etNotas.text
            etNotas.setText(textoEt.toString() + " Sin cilantro")
        }

        btnAgregarOrden.setOnClickListener {
            if (txtCantidad.text.toString().toInt() == 0) {
                Toast.makeText(this, "Ingresa un producto", LENGTH_LONG).show()
            } else if (productoSeleccionado == null) {
                Toast.makeText(this, "Selecciona un producto", LENGTH_LONG).show()
            } else {
                print("ID DE LA ORDEN: "+orden?.id)
                // Registrar la orden
                val contenidoOrdenARegistrar = ContenidoOrden.crear(
                    // !! Sirve para indicar que el objeto no es nulo
                    productoSeleccionado!!.id,
                    orden?.id!!,
                    txtCantidad.text.toString().toInt(),
                    etNotas.text.toString()
                )
                registrarContenidoOrden(contenidoOrdenARegistrar)

                // listaOrdenesMesa.add(ordenARegistrar)
                Toast.makeText(this, "Orden registrada", LENGTH_LONG).show()
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

        btnSiguiente.setOnClickListener {
            val intent = Intent(this, ResumenOrden::class.java)
            intent.putExtra("orden", orden)
            startActivity(intent)
        }
    }



    /**
     * Agrega botones dinámicos al contenedor de platillos(Menu)
     */
    private fun agregarBotonesDinamicos(contenedor: LinearLayout) {
        listaProductos.forEach { producto ->
            val button = Button(this).apply {
                text = producto.nombre
                textSize = 16f
                setTextColor(resources.getColor(android.R.color.white, theme))
                //background = ContextCompat.getDrawable(this@Menu, R.drawable.button_background)
                setPadding(16, 16, 16, 16)
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(16, 8, 16, 8)
                }
                setOnClickListener {
                    Toast.makeText(
                        this@Menu,
                        "Seleccionaste: ${producto.nombre}",
                        LENGTH_SHORT
                    ).show()
                    txtPlatilloSeleccionado.text = producto.nombre
                    productoSeleccionado = Producto.crear(
                        producto.nombre,
                        producto.precio,
                        producto.descripcion,
                        CategoriaProducto.valueOf(producto.categoriaProducto)
                    )
                    print("ID DEL PRODUCTO:" + producto.id)
                    productoSeleccionado!!.id = producto.id
                }
            }
            contenedor.addView(button)
        }
    }

    private fun registrarContenidoOrden(contenidoOrden: ContenidoOrden) {

        val contenidoOrdenDTO = ContenidoOrdenDTO.fromContenidoOrden(contenidoOrden)

        try {
            lifecycleScope.launch {
                try {
                    val response = APIClient.apiContenidoOrden.saveContenidoOrden(contenidoOrdenDTO)
                    if (response.isSuccessful) {
                        Toast.makeText(this@Menu, "ContenidoOrden registrado", LENGTH_LONG).show()
                    } else {
                        Toast.makeText(
                            this@Menu,
                            "Error al registrar ContenidoOrden: ${response.message()}",
                            LENGTH_LONG
                        ).show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this@Menu, "Error de conexión: ${e.message}", LENGTH_LONG).show()
                }
            }
        }catch (error:Exception){
            println("Error al registrar contenidoOrden: ${error.message}")
        }

    }
}
