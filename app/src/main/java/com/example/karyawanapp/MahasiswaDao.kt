package com.example.karyawanapp

import androidx.room.*

@Dao
interface MahasiswaDao {
    @Insert
    suspend fun insert(mahasiswa: Mahasiswa)

    @Update
    suspend fun update(mahasiswa: Mahasiswa)

    @Delete
    suspend fun delete(mahasiswa: Mahasiswa)

    @Query("SELECT * FROM mahasiswa ORDER BY id DESC")
    suspend fun getAll(): List<Mahasiswa>

    @Query("SELECT * FROM mahasiswa WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): Mahasiswa?
}

