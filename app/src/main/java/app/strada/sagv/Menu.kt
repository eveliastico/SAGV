package app.strada.sagv

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            // Retornar el insets, ya que es necesario en este contexto
            return@setOnApplyWindowInsetsListener insets
        }

        // Obtener el parametro después de haber configurado la vista
        val parametro = intent.getStringExtra("parametro_key")
        Toast.makeText(this, "Parametro recibido: $parametro", Toast.LENGTH_LONG).show()
    }
}