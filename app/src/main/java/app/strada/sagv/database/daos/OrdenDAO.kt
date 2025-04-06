package app.strada.sagv.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.strada.sagv.DataClasses.Orden

@Dao
interface OrdenDAO {

    @Query("SELECT * FROM orden")
    suspend fun getAllOrdenes(): List<Orden>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(ordenes: List<Orden>)
}