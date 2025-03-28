package app.strada.sagv

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 * En esta clase necesito poder recopilar todas las ordenes asociadas a una mesa y poder proceder
 * al pago.
 * 1. Acceder a las ordenes asociadas a una mesa.
 * 2. Sumar el prepcio de cada articulo.
 * 3. Mostrar el precio total.
 * 4. Proceder a hacer el pago.
 */
class RegistrarVenta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrar_venta)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}