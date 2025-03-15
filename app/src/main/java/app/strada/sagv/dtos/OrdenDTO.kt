package app.strada.sagv.dtos

import android.os.Parcelable
import app.strada.sagv.DataClasses.ContenidoOrden
import app.strada.sagv.DataClasses.Orden
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Parcelize
data class OrdenDTO(
    var id: Long? = null,
    val fechaHora: LocalDateTime,
    val numMesa: Int,
    val listaContenidosOrdenes: @RawValue List<ContenidoOrdenDTO>? = null
) : Parcelable {
    companion object {
        fun fromOrden(orden: Orden): OrdenDTO {
            return OrdenDTO(
                id = orden.id,
                fechaHora = orden.fechaHora,
                numMesa = orden.numMesa,
                listaContenidosOrdenes = orden.listaContenidosOrdenes?.map {
                    ContenidoOrdenDTO.fromContenidoOrden(it)
                }
            )
        }
    }
}


//
//@JsonClass(generateAdapter = true)
//data class OrdenDTO(
//    @Json(name = "id")
//    val id: Long,
//    @Json(name = "fechaHora")
//    val fechaHora: String,
//    @Json(name = "numMesa")
//    val numMesa: Int,
//    @Json(name = "listaContenidosOrdenes")
//    val listaContenidosOrdenes: List<ContenidoOrden>
//) {
//    companion object {
//        private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
//
//        /**
//         * Convierte un objeto Orden a un objeto OrdenDTO.
//         *
//         * @param orden El objeto Orden a convertir.
//         * @return El objeto OrdenDTO convertido.
//         */
//        fun fromOrden(orden: Orden): OrdenDTO {
//            return OrdenDTO(
//                id = orden.id ?: 0L,
//                fechaHora = orden.fechaHoraRegistro.format(formatter),
//                numMesa = orden.numMesa,
//                listaContenidosOrdenes = orden.listaContenidosOrdenes.toList()?: emptyList()
//            )
//        }
//    }
//}
