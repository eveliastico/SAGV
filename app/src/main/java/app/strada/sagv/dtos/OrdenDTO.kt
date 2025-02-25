package app.strada.sagv.dtos

import app.strada.sagv.DataClasses.Orden
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Data Transfer Object (DTO) para Orden.
 * Esta clase se utiliza para transferir datos entre la aplicación y la API.
 *
 * @property id El identificador único de la orden.
 * @property fechaHora La fecha y hora en que se registró la orden, formateada como una cadena.
 * @property numMesa El número de mesa asociado con la orden.
 */
@JsonClass(generateAdapter = true)
data class OrdenDTO(
    @Json(name = "id")
    val id: Long,
    @Json(name = "fechaHora")
    val fechaHora: String,
    @Json(name = "numMesa")
    val numMesa: Int
) {
    companion object {
        private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")

        /**
         * Convierte un objeto Orden a un objeto OrdenDTO.
         *
         * @param orden El objeto Orden a convertir.
         * @return El objeto OrdenDTO convertido.
         */
        fun fromOrden(orden: Orden): OrdenDTO {
            return OrdenDTO(
                id = orden.id,
                fechaHora = orden.fechaHoraRegistro.format(formatter),
                numMesa = orden.numMesa
            )
        }
    }
}
