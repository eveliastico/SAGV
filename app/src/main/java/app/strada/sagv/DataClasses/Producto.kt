package app.strada.sagv.DataClasses

data class Producto(var id:Int, var nombre: String, var precio: Float, var descripcion: String, var categoriaProducto: CategoriaProducto) {
    companion object {
        private var ultimoId = 0
        fun crear(nombre: String, precio: Float, descripcion: String, categoriaProducto: CategoriaProducto): Producto {
            val nuevoId = ++ultimoId
            return Producto(nuevoId, nombre, precio, descripcion, categoriaProducto)
        }
    }
}