package app.strada.sagv.DataClasses

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * NOTA: Busca como quitar el Parceleable por que esta entidad no tiene por que estarse moviendo
 * hacia otras pantallas, para eso esta el DTO.
 */
@Entity(tableName = "orden")
@Parcelize
data class Orden(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Long? = null,
    @ColumnInfo(name = "fecha_hora") var fechaHora: LocalDateTime = LocalDateTime.now(),
    @ColumnInfo(name = "id") var numMesa: Int,
    @ColumnInfo(name = "id") var listaContenidosOrdenes: @RawValue List<ContenidoOrden> = emptyList()
) : Parcelable{
    companion object {

        fun newOrden(): Orden {
            return Orden(
                id = null,
                fechaHora = LocalDateTime.now(),
                numMesa = -1,
                listaContenidosOrdenes = emptyList()
            )
        }
    }
}
