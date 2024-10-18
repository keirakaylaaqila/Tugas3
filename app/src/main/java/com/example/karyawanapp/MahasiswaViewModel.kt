package com.example.karyawanapp


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MahasiswaViewModel(application: Application) : AndroidViewModel(application) {
    private val mahasiswaDao = AppDatabase.getDatabase(application).mahasiswaDao()
    val mahasiswaList = MutableLiveData<List<Mahasiswa>>()  // LiveData untuk daftar karyawan

    fun insertMahasiswa(mahasiswa: Mahasiswa) = viewModelScope.launch {
        mahasiswaDao.insert(mahasiswa)
        refreshList()  // Refresh setelah insert
    }

    fun updateMahasiswa(mahasiswa: Mahasiswa) = viewModelScope.launch {
        mahasiswaDao.update(mahasiswa)
        refreshList()  // Refresh setelah update
    }

    fun deleteMahasiswa(mahasiswa: Mahasiswa) = viewModelScope.launch {
        mahasiswaDao.delete(mahasiswa)
        refreshList()  // Refresh setelah delete
    }

    fun refreshList() = viewModelScope.launch {
        mahasiswaList.postValue(mahasiswaDao.getAll())
    }

    suspend fun getMahasiswaById(id: Int): Mahasiswa? = mahasiswaDao.getById(id)
}

