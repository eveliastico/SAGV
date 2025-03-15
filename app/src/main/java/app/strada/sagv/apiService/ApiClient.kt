package app.strada.sagv.apiService

import app.strada.sagv.dtos.OrdenDTO
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object APIClient {
    private val moshi = Moshi.Builder()
        .add(LocalDateTimeAdapter()) // Asegura el adaptador de fechas
        .add(KotlinJsonAdapterFactory()) // Permite serialización automática
        .build()

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private fun <T> createService(serviceClass: Class<T>): T {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/") // Emulador accediendo al localhost
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .client(client)
            .build()
            .create(serviceClass)
    }

    val apiOrden: APIOrden = createService(APIOrden::class.java)
    val apiProducto: ApiProducto = createService(ApiProducto::class.java)
    val apiContenidoOrden: APIContenidoOrden = createService(APIContenidoOrden::class.java)
}