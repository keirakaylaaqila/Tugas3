package com.example.karyawanapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mahasiswa")
data class Mahasiswa(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var nama: String,
    var prodi: String,
    var nim: Int
)
