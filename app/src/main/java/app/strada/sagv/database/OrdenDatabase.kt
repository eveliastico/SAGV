package app.strada.sagv.database

import androidx.room.Database
import androidx.room.RoomDatabase
import app.strada.sagv.DataClasses.Orden
import app.strada.sagv.database.daos.OrdenDAO

@Database(entities = [Orden::class], version = 1)
abstract class OrdenDatabase: RoomDatabase() {
    abstract fun getOrdenDao(): OrdenDAO
}