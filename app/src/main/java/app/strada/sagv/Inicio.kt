package app.strada.sagv

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import app.strada.sagv.DataClasses.Orden

class Inicio : AppCompatActivity() {

    private lateinit var btnNuevaOrden: Button
    private lateinit var btnRegistrarVenta: Button
    private lateinit var btnAdministrarMenu: Button
    private lateinit var btnEditarOrden: Button
    private lateinit var btnReporteVentas: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inicio)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnNuevaOrden = findViewById(R.id.btnNuevaOrden)
        btnRegistrarVenta = findViewById(R.id.btnRegistrarVenta)
        btnAdministrarMenu = findViewById(R.id.btnAdministrarMenu)
        btnEditarOrden = findViewById(R.id.btnEditarOrden)
        btnReporteVentas = findViewById(R.id.btnReporteVentas)

        btnNuevaOrden.setOnClickListener{
            val intent = Intent(this, Mesas::class.java)
            intent.putExtra("orden", Orden.newOrden())
            startActivity(intent)
        }
    }
}