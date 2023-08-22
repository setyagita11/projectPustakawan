package com.uma.projectpustakawan.dbPustakawan

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "tbPustakawan")
data class Pustakawan(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo (name = "noPustakawan")
    var no_pustakawan: Int,

    @ColumnInfo (name = "namaPustakawan")
    var nama_pustakawan: String,

    @ColumnInfo (name = "pustakawanAdmin")
    var status: String,

    @ColumnInfo (name = "tanggalRegistrasi")
    var tanggal_registrasi: String

)
