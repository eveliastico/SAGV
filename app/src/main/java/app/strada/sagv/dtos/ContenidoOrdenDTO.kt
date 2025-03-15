package app.strada.sagv.dtos

import android.os.Parcelable
import app.strada.sagv.DataClasses.ContenidoOrden
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContenidoOrdenDTO(
    val id: Long,
    val cantidadProducto: Int,
    val notas: String,
    val idProducto: Long,
    val idOrden: Long
) : Parcelable {
    companion object {
        fun fromContenidoOrden(contenidoOrden: ContenidoOrden): ContenidoOrdenDTO {
            return ContenidoOrdenDTO(
                id = contenidoOrden.id,
                idProducto = contenidoOrden.idProducto,
                idOrden = contenidoOrden.idOrden,
                cantidadProducto = contenidoOrden.cantidadProducto,
                notas = contenidoOrden.notas
            )
        }
    }
}

//@JsonClass(generateAdapter = true)
//data class ContenidoOrdenDTO(
//
//    @Json(name = "id")
//    val id: Long,
//    @Json(name = "cantidadProducto")
//    val cantidadProducto: Int,
//    @Json(name = "notas")
//    val notas: String,
//    @Json(name = "idProducto")
//    val idProducto: Long,
//    @Json(name = "idOrden")
//    val idOrden: Long
//
//) {
//    companion object {
//        fun fromContenidoOrden(contenidoOrden: ContenidoOrden): ContenidoOrdenDTO {
//            return ContenidoOrdenDTO(
//                id = contenidoOrden.id,
//                idProducto = contenidoOrden.idProducto,
//                idOrden = contenidoOrden.idOrden,
//                cantidadProducto = contenidoOrden.cantidadProducto,
//                notas = contenidoOrden.notas
//            )
//        }
//    }
//}