package app.strada.sagv
// CAMBIAR NOMBRE DE ESTA CLASE A REGISTRAR VENTA
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.GridLayout
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
import app.strada.sagv.RegistrarVenta
import app.strada.sagv.apiService.APIClient
import app.strada.sagv.dtos.ContenidoOrdenDTO
import app.strada.sagv.dtos.OrdenDTO
import kotlinx.coroutines.launch

/**
 * NOTAS TO DO:
 * Ahora necesito crear los items correctamente
 * Poner el nombre del producto solo una vez
 * Sumar las cantidades de los mismos productos
 * Sumar los precios
 */
class ResumenVenta : AppCompatActivity() {

    private lateinit var gridOrden: GridLayout
    private var numeroContenidoOrden = 0
    private var listaOrdenes: MutableList<OrdenDTO> = mutableListOf()

    /**
     * Esta lista sirve para almacenar los contenidosOrden de cada orden asociada a una mesa.
     */
    private var listaContenidoOrden: MutableList<ContenidoOrdenDTO> = mutableListOf()

    private lateinit var linearResumen: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resumen_venta)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inicializarListaOrdenes()
    }

    private fun inicializarListaOrdenes() {
        if(intent.getParcelableArrayListExtra<OrdenDTO>("listaOrdenes").isNullOrEmpty()){
            Toast.makeText(this@ResumenVenta, "No hay ordenes para esta mesa", LENGTH_LONG).show()
            return
        }else{
            listaOrdenes = intent.getParcelableArrayListExtra<OrdenDTO>("listaOrdenes")!!
        }
    }

    private fun cargarItemContenidoOrdenes(){

        listaContenidoOrden.forEach { contenidoOrden ->
            val productoView = layoutInflater.inflate(R.layout.activity_item_resumen_orden, linearResumen, false)
            val txtNombreProducto = productoView.findViewById<TextView>(R.id.txtNombreProducto)
            val txtCantidad = productoView.findViewById<TextView>(R.id.txtCantidadProducto)
            val txtPrecio = productoView.findViewById<TextView>(R.id.txtPrecio)

            txtNombreProducto.text = contenidoOrden.idProducto

        }
    }

    private fun manejarSeleccionMesa(numMesa: Int) {
        Log.d("MesaSeleccionada", "Número de mesa asignado a la orden: $numMesa")
        Toast.makeText(this@ResumenVenta, "Mesa seleccionada: $numMesa", Toast.LENGTH_SHORT).show()
    }

    /**
     * NOTA
     * .let sirve para ejecutar un bloque de codigo si el objeto no es nulo.
     * ejemplo, objeto?.let{codigo a ejecutar si no es nulo}
     */

    private fun navegarANuevaOrden() {
        startActivity(Intent(this, NuevaOrden::class.java).apply {
            intent.putParcelableArrayListExtra("listaOrdenes", ArrayList(listaOrdenes))
        })
    }
}