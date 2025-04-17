package app.strada.sagv.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.strada.sagv.DataClasses.Producto
import app.strada.sagv.database.daos.ProductoDAO

@Database(entities = [Producto::class], version = 1)
abstract class ProductoDatabase(): RoomDatabase(){
    abstract fun productoDAO(): ProductoDAO

    companion object {
        @Volatile
        private var INSTANCE: ProductoDatabase? = null
        fun getDatabase(context: Context): ProductoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductoDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
