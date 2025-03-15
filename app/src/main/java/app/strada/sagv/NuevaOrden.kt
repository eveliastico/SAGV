package app.strada.sagv

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import app.strada.sagv.DataClasses.Orden

class NuevaOrden : AppCompatActivity() {

    private val btnPlatillos: Button by lazy { findViewById(R.id.btnPlatillos) }
    private val btnBebidas: Button by lazy { findViewById(R.id.btnBebidas) }
    private val btnPostres: Button by lazy { findViewById(R.id.btnPostres) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nueva_orden)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main) ?: return) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Se recibe la orden desde Mesas
        val orden = intent.getParcelableExtra("orden", Orden::class.java)

        btnPlatillos.setOnClickListener {
            navegarAMenu("PLATILLO", orden)
        }
        btnBebidas.setOnClickListener {
            navegarAMenu("BEBIDA", orden)
        }
        btnPostres.setOnClickListener {
            navegarAMenu("POSTRE", orden)
        }
    }

    private fun navegarAMenu(categoria: String, orden: Orden?) {

        val intent = Intent(this, Menu::class.java).apply {
            putExtra("categoria", categoria)
            putExtra("orden", orden)

        }
        // Toast.makeText(this, "Categoria Seleccionada: ${orden?.numMesa}", Toast.LENGTH_LONG).show()
        startActivity(intent)
    }
}
