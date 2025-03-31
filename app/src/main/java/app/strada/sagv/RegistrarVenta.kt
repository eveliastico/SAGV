package app.strada.sagv

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
import app.strada.sagv.DataClasses.Orden
import app.strada.sagv.Menu
import app.strada.sagv.apiService.APIClient
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
    private lateinit var listaOrdenes: List<OrdenDTO>
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
                manejarSeleccionMesa(numMesaSeleccionada)
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

    private fun manejarSeleccionMesa(numMesa: Int) {
        // Aqui se le asigna la mesa a la orde
        Log.d("MesaSeleccionada", "Número de mesa asignado a la orden: $numMesa")
        Toast.makeText(this@RegistrarVenta, "Mesa seleccionada: $numMesa", Toast.LENGTH_SHORT).show()
    }

    /**
     * NOTA
     * .let sirve para ejecutar un bloque de codigo si el objeto no es nulo.
     * ejemplo, objeto?.let{codigo a ejecutar si no es nulo}
     */

    private fun getAllOrdenes(){
        lifecycleScope.launch {
            try {
                val response = APIClient.apiOrden.getOrden();
                if (response.isSuccessful) {
                    listaOrdenes = response.body() ?: emptyList()
                    // Se obtiene el numero de mesas de la BD para asignarlo al contador de mesas
                    // y cargarla en el grid.
                    numeroMesas = listaOrdenes.size
                }else{
                    Toast.makeText(
                        this@RegistrarVenta,
                        "Error al obtener las ordenes: ${response.message()}",
                        LENGTH_LONG
                    ).show()
                }
            }catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@RegistrarVenta, "Error de conexión: ${e.message}", LENGTH_LONG).show()
            }

        }
    }

    private fun navegarANuevaOrden() {
        startActivity(Intent(this, NuevaOrden::class.java).apply {
            intent.putParcelableArrayListExtra("listaOrdenes", ArrayList(listaOrdenes))
        })
    }
}