package app.strada.sagv.DataClasses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Parcelize
data class Orden(
    var id: Long,
    var fechaHoraRegistro: LocalDateTime,
    var numMesa: Int
) : Parcelable {
    companion object {
        private var ultimoId: Long = 0
        private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

        fun newOrden(): Orden {
            val nuevoId = ++ultimoId
            val fechaHora = LocalDateTime.now()
            return Orden(nuevoId, fechaHora, -1)
        }
    }
}

