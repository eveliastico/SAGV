package app.strada.sagv.apiService

import app.strada.sagv.dtos.ContenidoOrdenDTO
import app.strada.sagv.dtos.OrdenDTO
import app.strada.sagv.dtos.ProductoDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface APIContenidoOrden {

    @POST("/api/contenido_orden/save")
    suspend fun saveContenidoOrden(@Body contenidoOrden: ContenidoOrdenDTO): Response<ContenidoOrdenDTO>

    @DELETE("api/contenido_orden/{id}")
    suspend fun deleteContenidoOrden(@Body id: Long): Response<ContenidoOrdenDTO>

}