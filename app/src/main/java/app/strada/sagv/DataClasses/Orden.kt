package app.strada.sagv.DataClasses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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
