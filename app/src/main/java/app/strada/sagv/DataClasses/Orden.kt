package app.strada.sagv.DataClasses

import java.time.LocalDateTime

data class Orden(var id:Int, var fechaYHoraRegistro: LocalDateTime, var numMesa:Int, var listaProductos: List<Producto>){
    companion object {
        private var ultimoId = 0
        fun crear(fechaYHoraRegistro: LocalDateTime, numMesa:Int): Orden {
            val nuevoId = ++ultimoId
            return Orden(nuevoId, fechaYHoraRegistro, numMesa, mutableListOf<Producto>())
        }
    }
}
