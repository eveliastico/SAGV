package app.strada.sagv

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NuevaOrden : AppCompatActivity() {

    private lateinit var btnPlatillos: Button
    private lateinit var btnBebidas: Button
    private lateinit var btnPostres: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nueva_orden)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnPlatillos = findViewById(R.id.btnPlatillos)
        btnBebidas = findViewById(R.id.btnBebidas)
        btnPostres = findViewById(R.id.btnPostres)

        btnPlatillos.setOnClickListener(){
            val intent = Intent(this, Menu::class.java)
            intent.putExtra("categoria", "Platillos")
            startActivity(intent)
        }

        btnBebidas.setOnClickListener{
            val intent = Intent(this, Menu::class.java)
            intent.putExtra("categoria", "Bebidas")
            startActivity(intent)
        }
        btnPostres.setOnClickListener{
            val intent = Intent(this, Menu::class.java)
            intent.putExtra("categoria", "Postres")
            startActivity(intent)
        }

    }


}