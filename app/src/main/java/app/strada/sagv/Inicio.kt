package app.strada.sagv

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import app.strada.sagv.DataClasses.Orden
import app.strada.sagv.DataClasses.Producto
import app.strada.sagv.database.repository.ProductoRepository
import app.strada.sagv.repository.OrdenRepository
import kotlinx.coroutines.launch

class Inicio : AppCompatActivity() {

    private lateinit var btnNuevaOrden: Button
    private lateinit var btnRegistrarVenta: Button
    private lateinit var btnAdministrarMenu: Button
    private lateinit var btnEditarOrden: Button
    private lateinit var btnReporteVentas: Button

    private lateinit var viewModel: InicioViewModel

    private var listaProductos = listOf<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inicio)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(this).get(InicioViewModel::class.java)
        cargarProductosRoom()

        btnNuevaOrden = findViewById(R.id.btnNuevaOrden)
        btnRegistrarVenta = findViewById(R.id.btnRegistrarVenta)
        btnAdministrarMenu = findViewById(R.id.btnAdministrarMenu)
        btnEditarOrden = findViewById(R.id.btnEditarOrden)
        btnReporteVentas = findViewById(R.id.btnReporteVentas)

        btnNuevaOrden.setOnClickListener{
            val intent = Intent(this, Mesas::class.java)
            // Se crea una nueva orden y se inicializa con la fecha
            intent.putExtra("orden", Orden.newOrden())
            startActivity(intent)
        }
    }

    /**
     * Esta funcion carga los datos almacenados en room.
     */
    fun cargarProductosRoom() {
        lifecycleScope.launch {
            listaProductos = viewModel.getProductos()
            Log.d("Inicio", "Productos cargados: $listaProductos")

            // Aquí ya puedes usar listaProductos donde lo necesites
        }
    }

}