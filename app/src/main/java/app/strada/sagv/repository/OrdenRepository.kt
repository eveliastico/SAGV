package app.strada.sagv.repository

import app.strada.sagv.DataClasses.Orden
import app.strada.sagv.apiService.APIOrden
import app.strada.sagv.dtos.OrdenDTO
import retrofit2.Response

class OrdenRepository(private val apiOrden: APIOrden) {

//    suspend fun getOrden(): Result<List<Orden>> {
//        return try {
//            val response: Response<List<OrdenDTO>> = apiOrden.getOrden()
//            if (response.isSuccessful) {
//                val ordenesDTO: List<OrdenDTO> = response.body() ?: emptyList()
//                val ordenes: List<Orden> = ordenesDTO.map { ordenDTO ->
//                    Orden(
//                        id = ordenDTO.id,
//                        fechaHoraRegistro = ordenDTO.fechaHoraRegistro,
//                        numMesa = ordenDTO.numMesa
//                    )
//                    }
//                Result.success(ordenes)
//            } else {
//                Result.failure(Exception("Error en la respuesta: ${response.code()} ${response.message()}"))
//            }
//        } catch (e: Exception) {
//            Result.failure(Exception("Error de red o conversión: ${e.message}", e))
//        }
//    }

    suspend fun saveOrden(orden: OrdenDTO): Result<OrdenDTO> {
        return try {
            val response: Response<OrdenDTO> = apiOrden.saveOrden(orden)

            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Respuesta vacía del servidor"))
            } else {
                Result.failure(Exception("Error en la API: ${response.errorBody()?.string()}"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Error de red o conversión: ${e.message}", e))
        }
    }
}