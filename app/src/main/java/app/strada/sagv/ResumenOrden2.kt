package app.strada.sagv

import android.content.ClipData
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.strada.sagv.DataClasses.ItemProducto
import app.strada.sagv.adapter.OrdenAdapter
import app.strada.sagv.dtos.ContenidoOrdenDTO

class ResumenOrden2 : AppCompatActivity() {

    private lateinit var listaItemsAgregados: List<ItemProducto>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resumen_orden2)

//        val recyclerView: RecyclerView = findViewById(recyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(this)



//        recyclerView.adapter = OrdenAdapter(orderList)

        initRecyclerView()

        // Recibo la la lista con los productos que se agregaron
        listaItemsAgregados = intent.getParcelableArrayListExtra<ItemProducto>("listaItems")!!
    }

    /*
    * Este metodo convierte un ArrayList<ContenidoOrdenDTO> a List<ItemProducto>
    *
    fun convertirAItemProducto(contenidoOrdenList: List<ContenidoOrdenDTO>
    ): List<ItemProducto> {
        return contenidoOrdenList.mapNotNull { contenido ->
            ItemProducto(
                nombre = "aasd",
                cantidad = 12
            )
        }
    }
    */

    fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewProducto)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = OrdenAdapter(listaItemsAgregados)
    }
}
