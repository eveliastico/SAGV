package app.strada.sagv
// CAMBIAR EL NOMBRE DE ESTA CLASE A ALGO RESPECTIVO A LAS MESAS
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.GridLayout
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import app.strada.sagv.DataClasses.ContenidoOrden
import app.strada.sagv.DataClasses.ItemContenidoOrden
import app.strada.sagv.DataClasses.Orden
import app.strada.sagv.Menu
import app.strada.sagv.apiService.APIClient
import app.strada.sagv.dtos.ContenidoOrdenDTO
import app.strada.sagv.dtos.OrdenDTO
import kotlinx.coroutines.launch

/**
 * En este caso necesito poder recopilar todas las ordenes asociadas a una mesa y poder proceder
 * al pago.
 * 1. Acceder a las ordenes asociadas a una mesa.
 * 2. Sumar el precio de cada articulo.
 * 3. Mostrar el precio total.
 * 4. Proceder a hacer el pago.
 */
class RegistrarVenta : AppCompatActivity() {

    private lateinit var gridMesas: GridLayout
    private lateinit var listaContenidoOrdenDTO: MutableList<ContenidoOrdenDTO>
    private lateinit var listaOrdenes: List<OrdenDTO>
    private lateinit var listaItemContenidoOrden: MutableList<ItemContenidoOrden>
    private var numeroMesas = 0
    // Ser recive la orden y se le añade la mesa.
    //private var objOrden: Orden? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrar_venta)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        inicializarVista()
        inicializarMesas()
    }

    private fun inicializarVista() {
        gridMesas = findViewById(R.id.gridMesas)
    }

    private fun inicializarMesas() {
        repeat(numeroMesas) { cargarMesas() }
    }

    private fun cargarMesas() {
        var contadorMesas = 0
        val numeroMesa = contadorMesas
        val nuevaMesa = Button(this).apply {
            text = "Mesa $numeroMesa"
            textSize = 16f
            setPadding(8, 8, 8, 8)
            tag = numeroMesa

            setOnClickListener {
                val numMesaSeleccionada = (it as Button).tag as Int
                lifecycleScope.launch {
                    manejarSeleccionMesa(numMesaSeleccionada)
                }
            }
        }

        val layoutParams = GridLayout.LayoutParams().apply {
            columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
            width = 0
        }
        nuevaMesa.layoutParams = layoutParams

        gridMesas.addView(nuevaMesa)
        contadorMesas++
    }

    private suspend fun manejarSeleccionMesa(numMesa: Int) {
        try {
            val response = APIClient.apiOrden.getOrdenesByMesa(numMesa)
            if (response.isSuccessful) {
                listaOrdenes = response.body() ?: emptyList()
                if(listaOrdenes.isNullOrEmpty()){
                    Toast.makeText(this@RegistrarVenta, "No hay ordenes para esta mesa", LENGTH_LONG).show()
                    return
                }else{
                    navegarANuevaOrden(listaOrdenes)
                }
            }
        }catch (error: Exception){
            error.printStackTrace()
            Toast.makeText(this@RegistrarVenta, "Error de conexión: ${error.message}", LENGTH_LONG).show()
        }
        Log.d("MesaSeleccionada", "Número de mesa asignado a la orden: $numMesa")
        Toast.makeText(this@RegistrarVenta, "Mesa seleccionada: $numMesa", Toast.LENGTH_SHORT).show()
    }

    /**
     * NOTA
     * .let sirve para ejecutar un bloque de codigo si el objeto no es nulo.
     * ejemplo, objeto?.let{codigo a ejecutar si no es nulo}
     */

    /**
     * Que necesito de un item?
     * 1. Producto ID
     * 2. Nombre
     * 3. Cantidad
     * 4. Precio unitario
     */
    private fun cargarListaContenidoOrden() {
        listaOrdenes.forEach { orden ->
            orden.listaContenidosOrdenes?.forEach { contenidoOrden ->
                listaContenidoOrdenDTO.add(contenidoOrden)
            }
        }
    }

    private fun cargarItemContenidoOrden() {
        var itemContenidoOrden: ItemContenidoOrden
        listaOrdenes.forEach { orden ->
            orden.listaContenidosOrdenes?.forEach { contenidoOrden ->
                //itemContenidoOrden.
            }
        }
    }




    private fun navegarANuevaOrden(lista: List<OrdenDTO>) {
        startActivity(Intent(this, NuevaOrden::class.java).apply {
            intent.putParcelableArrayListExtra("listaOrdenes", ArrayList(lista))
        })
    }
}