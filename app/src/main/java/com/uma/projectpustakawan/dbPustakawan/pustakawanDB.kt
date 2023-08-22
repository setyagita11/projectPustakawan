package com.uma.projectpustakawan.dbPustakawan

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [Pustakawan::class], version = 1)
abstract class pustakawanDB : RoomDatabase() {
    abstract fun pustakawanDAO(): pustakawanDao

    companion object{

        @Volatile
        private var Instances: pustakawanDB? = null
        private var key = Any()

    @Synchronized
        fun getInstances(context: Context): pustakawanDB {
            if (Instances == null) {
                Instances = Room.databaseBuilder(context, pustakawanDB::class.java, "pustakawan")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                }
            return Instances!!
        }
    }
}