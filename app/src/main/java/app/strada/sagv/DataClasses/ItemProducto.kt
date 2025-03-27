package app.strada.sagv.DataClasses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemProducto(
    val nombre: String,
    var cantidad: Int
) : Parcelable
