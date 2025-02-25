package app.strada.sagv.DataClasses

/**
 * Producto hace referencia al producto seleccionado.
 * CantidadProducto hace referencia a la cantidad de ese producto desea consumir.
 * Notas hace referencia a las notas sobre esa orden, por ejemplo: Sin cebolla, sin hielo.
 */
data class ContenidoOrden(var id: Long, var producto: Producto, var cantidadProducto: Int, var notas: String){
    companion object {
        private var ultimoId:Long = 0
        fun crear(producto: Producto, cantidadProducto: Int, notas: String): ContenidoOrden {
            val nuevoId = ++ultimoId
            return ContenidoOrden(nuevoId, producto, cantidadProducto, notas)
        }
    }
}
