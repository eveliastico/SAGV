package app.strada.sagv.database.repository

import app.strada.sagv.DataClasses.Producto
import app.strada.sagv.database.daos.ProductoDAO

class ProductoRepository(
    private val productoDao: ProductoDAO
) {

    suspend fun getProductos(): List<Producto> {
        return productoDao.getProductos()
    }

    suspend fun getProductoById(id: Long): Producto {
        return productoDao.getProductoById(id)
    }

    suspend fun insertProducto(producto: Producto): Long {
        return productoDao.insertProducto(producto)
    }

    //Este lo usare para eliminar todos los registros de los productos
    // una vez que se cierra la aplicacion, cuando se vuelva abrir realiza la consulta
    // a la api rest para que siempre este actualizado con los productos que hay
    // maybe hago algo para que se actualice automaticamente cuando se agrega un nuevo producto
    suspend fun deleteAllProductos(): Int{
        return productoDao.deleteAllProductos()
    }

}