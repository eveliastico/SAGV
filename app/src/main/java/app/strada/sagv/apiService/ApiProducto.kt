package app.strada.sagv.apiService

import app.strada.sagv.dtos.ProductoDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiProducto {

    @GET("api/producto/find/all")
    suspend fun getProducto(): Response<List<ProductoDTO>>

    @GET("api/producto/find/{id}")
    suspend fun getProductoById(@Body id: Long): Response<ProductoDTO>

    // Devuelve una lista de productos por categoria
    @GET("api/producto/categorie/{categoriaProducto}")
    suspend fun getProductoByCategoria(@Body categoriaProducto: String): Response<List<ProductoDTO>>

    @POST("api/producto/save")
    suspend fun saveProducto(@Body producto: ProductoDTO): Response<ProductoDTO>

    @POST("api/producto/delete/{id}")
    suspend fun deleteProducto(@Body id: Long): Response<ProductoDTO>
}