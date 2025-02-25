package app.strada.sagv.apiService

import com.squareup.moshi.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@JsonClass(generateAdapter = true)
class LocalDateTimeAdapter {

    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss") // Sin microsegundos

    @ToJson
    fun toJson(value: LocalDateTime): String {
        println("ENTRE AL TOJSON")
        val formattedDate = value.format(formatter)
        println("Fecha serializada por Moshi: $formattedDate")
        return formattedDate
    }

    @FromJson
    fun fromJson(json: String): LocalDateTime {
        println("ENTRE AL FROMJSON")
        println("Fecha recibida desde JSON: $json")
        return LocalDateTime.parse(json, formatter)
    }
}
