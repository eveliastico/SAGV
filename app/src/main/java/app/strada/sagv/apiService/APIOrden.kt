package app.strada.sagv.apiService

import app.strada.sagv.dtos.OrdenDTO
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIOrden {

    @GET("api/orden/get_all")
    suspend fun getOrden(): Response<List<OrdenDTO>>

    @GET("api/orden/get/{id}")
    suspend fun getOrdenById(@Body id: Long): Response<OrdenDTO>

    @POST("api/orden/save")
    suspend fun saveOrden(@Body orden: OrdenDTO): Response<OrdenDTO>

    @POST("api/orden/delete/{id}")
    suspend fun deleteOrden(@Body id: Long): Response<OrdenDTO>
}
