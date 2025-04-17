package app.strada.sagv.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.strada.sagv.DataClasses.Producto

@Dao
interface ProductoDAO {

    @Query("SELECT * FROM Producto")
    suspend fun getProductos(): List<Producto>

    @Query("SELECT * FROM Producto WHERE id = :id")
    suspend fun getProductoById(id: Long): Producto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducto(producto: Producto): Long

    //En teoria deberia de devolver el numero de filas eliminadas no?
    @Query("DELETE FROM Producto")
    suspend fun deleteAllProductos(): Int

}