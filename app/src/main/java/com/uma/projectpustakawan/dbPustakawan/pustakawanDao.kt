package com.uma.projectpustakawan.dbPustakawan

import androidx.room.*

@Dao
interface pustakawanDao {

    @Insert
    fun insertData (pustakawan: Pustakawan)

    @Update
    fun ubahData (pustakawan: Pustakawan)

    @Delete
    fun hapusData (pustakawan: Pustakawan)

    @Query ("SELECT * FROM 'tbPustakawan'")
    fun getAllData() :List<Pustakawan>

}