package app.strada.sagv.DataClasses

/**
 * Producto hace referencia al producto seleccionado.
 * CantidadProducto hace referencia a la cantidad de ese producto desea consumir.
 * Notas hace referencia a las notas sobre esa orden, por ejemplo: Sin cebolla, sin hielo.
 */
data class ContenidoOrden(var id: Long, var idProducto: Long, var idOrden: Long, var cantidadProducto: Int, var notas: String){
    companion object {
        private var ultimoId:Long = 0
        fun crear(idProducto: Long, idOrden:Long, cantidadProducto: Int, notas: String): ContenidoOrden {
            val nuevoId = ++ultimoId
            return ContenidoOrden(nuevoId, idProducto, idOrden, cantidadProducto, notas)
        }
    }
}
