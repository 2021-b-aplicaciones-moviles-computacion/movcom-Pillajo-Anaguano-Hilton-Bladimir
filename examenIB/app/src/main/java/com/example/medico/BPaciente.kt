package com.example.medico

import android.os.Parcel
import android.os.Parcelable

class BPaciente(
    val id: Int,
    var nombre: String?,
    var pacientefrecuente: Boolean,
    var edad: Int,
    val idMedico: Int
) : Parcelable {
    override fun toString(): String {
        return "${id}: ${nombre}   ${pacientefrecuente}    ${edad}    ${idMedico}"
    }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeByte(if (pacientefrecuente) 1 else 0)
        parcel.writeInt(edad)
        parcel.writeInt(idMedico)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BPaciente> {
        override fun createFromParcel(parcel: Parcel): BPaciente {
            return BPaciente(parcel)
        }

        override fun newArray(size: Int): Array<BPaciente?> {
            return arrayOfNulls(size)
        }
    }

}