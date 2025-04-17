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
@Parcelize
data class Orden(
    var id: Long? = null,
    var fechaHora: LocalDateTime = LocalDateTime.now(),
    var numMesa: Int,
    var listaContenidosOrdenes: @RawValue List<ContenidoOrden> = emptyList()
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
