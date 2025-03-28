package app.strada.sagv

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import app.strada.sagv.DataClasses.ContenidoOrden
import app.strada.sagv.DataClasses.ItemProducto
import app.strada.sagv.Menu
import app.strada.sagv.apiService.APIClient
import app.strada.sagv.dtos.ContenidoOrdenDTO
import kotlinx.coroutines.launch
import android.content.Intent

class ResumenOrden : AppCompatActivity() {

    private var listaProductosAgregados: MutableList<ContenidoOrdenDTO> = mutableListOf()
    private var listaItemsAgregados: MutableList<ItemProducto> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resumen_orden)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        listaProductosAgregados = intent.getParcelableArrayListExtra<ContenidoOrdenDTO>("listaProductosAgregados")!!
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
        val btnOrdenar = findViewById<Button>(R.id.btnOrdenar)

        listaItemsAgregados.forEach { item ->
            val productoView = layoutInflater.inflate(R.layout.activity_item_producto2, linearResumen, false)
            val index = listaItemsAgregados.indexOf(item)

            // Configuración del nombre del producto
            val txtProducto = productoView.findViewById<TextView>(R.id.txtNombreProducto)
            txtProducto.text = item.nombre

            // Configuración de la cantidad inicial
            val txtCantidad = productoView.findViewById<TextView>(R.id.txtCantidad)
            txtCantidad.setText(item.cantidad.toString())

            // Botón de sumar
            val btnSumar = productoView.findViewById<ImageButton>(R.id.btnAumentar)
            btnSumar.setOnClickListener {
                val cantidadActual = txtCantidad.text.toString().toInt()
                println("CANTIDAD ACTUAL SUMAR: " + cantidadActual)
                item.cantidad = cantidadActual + 1
                listaProductosAgregados[index].cantidadProducto = cantidadActual + 1
                txtCantidad.setText((cantidadActual + 1).toString())
            }

            // Botón de restar
            val btnRestar = productoView.findViewById<ImageButton>(R.id.btnDisminuir)
            btnRestar.setOnClickListener {
                val cantidadActual = txtCantidad.text.toString().toInt()
                println("CANTIDAD ACTUAL RESTAR: " + cantidadActual)
                item.cantidad = cantidadActual - 1
                if (cantidadActual > 0) {
                    listaProductosAgregados[index].cantidadProducto = cantidadActual - 1
                    txtCantidad.setText((cantidadActual - 1).toString())
                }
            }

            /**
             * NOTA
             * Cuando el cliente presione el nombre del producto deberia de ver las notas asociadas
             * a ese contenidoOrden.
             * Puedo mostrar una especie de Dialog con las notas.
             */

            /**
             * Cuando presiona el boton ordenar:
             * 1. Cada que se presiona aumentar o disminuir la cantidad se resta ahi mismo la
             * cantidad del objeto.
             *
             */
            btnOrdenar.setOnClickListener {
                listaProductosAgregados.forEach { contenidoOrdenDTO ->
                    registrarContenidoOrden(contenidoOrdenDTO)
                }
                Toast.makeText(this, "Orden Registrada", LENGTH_LONG).show()
                //NOTA: AQUI TENGO QUE PONER LA LOGICA PARA ENVIAR TMBN LA ORDEN A LA COCINA
            }

            // Agregar la vista al contenedor
            linearResumen.addView(productoView)
        }
    }
    /**
     * Este metodo se encarga de registrar el ContenidoOrden en la BD.
     */
    private fun registrarContenidoOrden(contenidoOrdenDTO: ContenidoOrdenDTO) {

        try {
            lifecycleScope.launch {
                try {
                    println("CANTIDAD PRODUCTO BD: " + contenidoOrdenDTO.cantidadProducto)
                    val response = APIClient.apiContenidoOrden.saveContenidoOrden(contenidoOrdenDTO)
                    if (response.isSuccessful) {
                        Toast.makeText(this@ResumenOrden, "ContenidoOrden registrado", LENGTH_LONG).show()
                        navegarAInicio()

                    } else {
                        Toast.makeText(
                            this@ResumenOrden,
                            "Error al registrar ContenidoOrden: ${response.message()}",
                            LENGTH_LONG
                        ).show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this@ResumenOrden, "Error de conexión: ${e.message}", LENGTH_LONG).show()
                }
            }
        }catch (error:Exception){
            println("Error al registrar contenidoOrden: ${error.message}")
        }

    }

    private fun navegarAInicio(){
        val intent = Intent(this, Inicio::class.java)
        startActivity(intent)
    }
}