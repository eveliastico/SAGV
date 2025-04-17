package app.strada.sagv


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import app.strada.sagv.DataClasses.Producto
import app.strada.sagv.database.ProductoDatabase
import app.strada.sagv.database.repository.ProductoRepository
import kotlinx.coroutines.launch

class InicioViewModel(application: Application) : AndroidViewModel(application) {

    private val database = ProductoDatabase.getDatabase(application)
    private val productoDao = database.productoDAO()
    private val productoRepository = ProductoRepository(productoDao)

    suspend fun getProductos(): List<Producto> {
        return productoRepository.getProductos()
    }

    fun getProductoById(id: Long): Producto {
            return productoRepository.getProductoById(id)
    }

    fun insertProducto(producto: Producto){
        viewModelScope.launch{
            productoRepository.insertProducto(producto)
        }
    }

    fun deleteAllProductos(){
        viewModelScope.launch{
            productoRepository.deleteAllProductos()
        }
    }
}