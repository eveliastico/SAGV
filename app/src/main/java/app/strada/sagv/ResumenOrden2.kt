package app.strada.sagv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.strada.sagv.DataClasses.ItemProducto
import app.strada.sagv.adapter.OrdenAdapter

class ResumenOrden2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resumen_orden2)

//        val recyclerView: RecyclerView = findViewById(recyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(this)



//        recyclerView.adapter = OrdenAdapter(orderList)

        initRecyclerView()
    }

    val orderList = listOf(
        ItemProducto("Orden Asada", 2),
        ItemProducto("Alambre", 1),
        ItemProducto("Quezadilla", 4),
        ItemProducto("Agua Piña", 6),
        ItemProducto("Pay Limón", 4)
    )

    fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewProducto)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = OrdenAdapter(orderList)
    }
}
