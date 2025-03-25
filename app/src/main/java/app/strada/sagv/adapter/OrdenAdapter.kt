package app.strada.sagv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.strada.sagv.DataClasses.ItemProducto
import app.strada.sagv.R

class OrdenAdapter(private val itemList: List<ItemProducto>) : RecyclerView.Adapter<ProductoViewHolder>() {

//    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val buttonProduct: Button = itemView.findViewById(R.id.buttonProduct)
//        val editTextQuantity: EditText = itemView.findViewById(R.id.editTextQuantity)
//        val buttonIncrease: ImageButton = itemView.findViewById(R.id.buttonIncrease)
//        val buttonDecrease: ImageButton = itemView.findViewById(R.id.buttonDecrease)
//    }

    /*
    * Obtiene lo atributos del objeto OrdenAdapter y lo muestra en la vista
    * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductoViewHolder(layoutInflater.inflate(R.layout.activity_item_producto2, parent, false))
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val item = itemList[position]
        holder.btnProducto.text = item.nombre
        holder.etCantidad.setText(item.cantidad.toString())

        holder.btnAumentar.setOnClickListener {
            item.cantidad++
            holder.etCantidad.setText(item.cantidad.toString())
        }

        holder.btnDisminuir.setOnClickListener {
            if (item.cantidad > 0) {
                item.cantidad--
                holder.etCantidad.setText(item.cantidad.toString())
            }
        }
    }

    // Devuelve la cantidad de elementos en la lista
    override fun getItemCount() = itemList.size
}
