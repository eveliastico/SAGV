package app.strada.sagv

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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

        val numMesa = intent.getStringExtra("mesaId")

        btnPlatillos.setOnClickListener { navegarAMenu("Platillos", numMesa) }
        btnBebidas.setOnClickListener { navegarAMenu("Bebidas", numMesa) }
        btnPostres.setOnClickListener { navegarAMenu("Postres", numMesa) }
    }

    private fun navegarAMenu(categoria: String, numMesa: String?) {
        val intent = Intent(this, Menu::class.java).apply {
            putExtra("categoria", categoria)
            putExtra("mesaId", numMesa)
        }
        Toast.makeText(this, "Mesa Seleccionada: $numMesa", Toast.LENGTH_LONG).show()
        startActivity(intent)
    }
}
