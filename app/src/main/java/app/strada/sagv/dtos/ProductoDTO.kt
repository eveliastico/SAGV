package app.strada.sagv.dtos

import app.strada.sagv.DataClasses.Producto
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ProductoDTO(
    val id: Long,
    val nombre: String,
    val precio: Float,
    val descripcion: String,
    val categoriaProducto: String
) {
    companion object {
        /**
         * Convierte un objeto \`Producto\` a un objeto \`ProductoDTO\`.
         *
         * @param producto El objeto \`Producto\` a convertir.
         * @return El objeto \`ProductoDTO\` convertido.
         */
        fun fromProducto(producto: Producto): ProductoDTO {
            return ProductoDTO(
                id = producto.id,
                nombre = producto.nombre,
                precio = producto.precio,
                descripcion = producto.descripcion,
                categoriaProducto = producto.categoriaProducto.toString()
            )
        }
    }
}