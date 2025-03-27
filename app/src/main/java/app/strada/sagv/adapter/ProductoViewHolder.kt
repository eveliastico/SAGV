package app.strada.sagv.adapter

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import app.strada.sagv.DataClasses.ItemProducto
import app.strada.sagv.R

class ProductoViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val btnProducto = view.findViewById<Button>(R.id.txtNombreProducto)
    val etCantidad = view.findViewById<EditText>(R.id.txtCantidad)
    val btnAumentar: ImageButton = itemView.findViewById(R.id.btnAumentar)
    val btnDisminuir: ImageButton = itemView.findViewById(R.id.btnDisminuir)


//    fun render(productoModel: ItemProducto){
//        btnProducto.text = productoModel.nombre
//    }
}