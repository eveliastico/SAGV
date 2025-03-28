package app.strada.sagv

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.GridLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import app.strada.sagv.DataClasses.Orden
import app.strada.sagv.apiService.APIOrden
import app.strada.sagv.dtos.OrdenDTO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import app.strada.sagv.apiService.APIClient


class Mesas : AppCompatActivity() {
    private lateinit var btnAgregarMesa: Button
    private lateinit var gridMesas: GridLayout
    private var contadorMesas = 1
    // Ser recive la orden y se le añade la mesa.
    private var objOrden: Orden? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mesas)

        inicializarVista()
        cargarOrdenDesdeIntent()
        inicializarMesas()
    }

    private fun inicializarVista() {
        btnAgregarMesa = findViewById(R.id.btnAgregarMesa)
        gridMesas = findViewById(R.id.gridMesas)
        btnAgregarMesa.setOnClickListener { agregarMesa() }
    }

    private fun cargarOrdenDesdeIntent() {
        objOrden = intent.getParcelableExtra("orden", Orden::class.java)
    }

    private fun inicializarMesas() {
        repeat(9) { agregarMesa() }
    }

    private fun agregarMesa() {
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
        // Aqui se le asigna la mesa a la orden
        objOrden?.numMesa = numMesa

        Log.d("MesaSeleccionada", "Número de mesa asignado a la orden: $numMesa")
        Toast.makeText(this, "Mesa seleccionada: $numMesa", Toast.LENGTH_SHORT).show()

        objOrden?.let {
            enviarOrden(it)
        }

        //navegarANuevaOrden()
    }

    private fun enviarOrden(orden: Orden) {
        val ordenDTO = OrdenDTO.fromOrden(orden)

        lifecycleScope.launch {
            try {
                val response = APIClient.apiOrden.saveOrden(ordenDTO)

                if (response.isSuccessful) {
                    val ordenGuardada = response.body()
                    if (ordenGuardada != null) {
                        objOrden?.id = ordenGuardada.id
                        Log.d("OrdenGuardada", "Orden guardada con ID: ${objOrden?.id}")

                        // Solo navega después de actualizar el ID
                        navegarANuevaOrden()
                    } else {
                        Log.e("ErrorOrden", "El servidor no devolvió una orden válida")
                    }
                } else {
                    Log.e("ErrorOrden", "Error al guardar la orden: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("ErrorConexion", "Error de conexión: ${e.message}")
            }
        }
    }

    private fun navegarANuevaOrden() {
        startActivity(Intent(this, NuevaOrden::class.java).apply {
            putExtra("orden", objOrden)
        })
    }
}
