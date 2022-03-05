package com.example.medicopaciente

import android.os.Parcel
import android.os.Parcelable

class BMedico(
    val id: Int,
    var nombre: String?,
    var telefono: String?,
    var precioconsulta: Double
) : Parcelable {

    override fun toString(): String {
        return "${id}:  ${nombre}    ${telefono}   ${precioconsulta}"
    }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeString(telefono)
        parcel.writeDouble(precioconsulta)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BMedico> {
        override fun createFromParcel(parcel: Parcel): BMedico {
            return BMedico(parcel)
        }

        override fun newArray(size: Int): Array<BMedico?> {
            return arrayOfNulls(size)
        }
    }

}