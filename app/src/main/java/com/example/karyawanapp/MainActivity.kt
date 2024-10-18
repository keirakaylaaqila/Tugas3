package com.example.karyawanapp


import MahasiswaAdapter
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val viewModel: MahasiswaViewModel by viewModels()
    private lateinit var etNama: EditText
    private lateinit var etProdi: EditText
    private lateinit var etNim: EditText
    private lateinit var btnSimpan: Button
    private lateinit var rvKaryawan: RecyclerView
    private lateinit var adapter: MahasiswaAdapter
    private var currentMahasiswa: Mahasiswa? = null // Menyimpan karyawan yang sedang diedit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNama = findViewById(R.id.etNama)
        etProdi = findViewById(R.id.etProdi)
        etNim = findViewById(R.id.etNim)
        btnSimpan = findViewById(R.id.btnSimpan)
        rvKaryawan = findViewById(R.id.rvKaryawan)

        adapter = MahasiswaAdapter(emptyList(), { mahasiswa ->
            viewModel.deleteMahasiswa(mahasiswa)
            Toast.makeText(this, "Karyawan dihapus", Toast.LENGTH_SHORT).show()
        }, { mahasiswa ->
            showUpdateDialog(mahasiswa) // Tampilkan dialog untuk update
        })

        rvKaryawan.adapter = adapter
        rvKaryawan.layoutManager = LinearLayoutManager(this)

        btnSimpan.setOnClickListener {
            if (validateInput()) {
                val nama = etNama.text.toString()
                val prodi = etProdi.text.toString()
                val nim = etNim.text.toString().toInt()

                if (currentMahasiswa != null) { // Jika ada karyawan yang sedang diedit
                    currentMahasiswa?.let {
                        it.nama = nama
                        it.prodi = prodi
                        it.nim = nim
                        viewModel.updateMahasiswa(it)
                    }
                    currentMahasiswa = null // Reset setelah update
                } else {
                    val mahasiswa= Mahasiswa(nama = nama, prodi = prodi, nim = nim)
                    viewModel.insertMahasiswa(mahasiswa)
                }
                clearInput()
            }
        }

        viewModel.mahasiswaList.observe(this) { mahasiswa ->
            adapter.updateMahasiswaList(mahasiswa)
        }

        viewModel.refreshList()
    }

    private fun showUpdateDialog(mahasiswa: Mahasiswa) {
        currentMahasiswa = mahasiswa
        etNama.setText(mahasiswa.nama)
        etProdi.setText(mahasiswa.prodi)
        etNim.setText(mahasiswa.nim.toString())
    }

    private fun validateInput(): Boolean {
        return when {
            etNama.text.isEmpty() -> {
                etNama.error = "Nama tidak boleh kosong"
                false
            }
            etProdi.text.isEmpty() -> {
                etProdi.error = "Prodi tidak boleh kosong"
                false
            }
            etNim.text.isEmpty() -> {
                etNim.error = "NIM tidak boleh kosong"
                false
            }
            else -> true
        }
    }

    private fun clearInput() {
        etNama.text.clear()
        etProdi.text.clear()
        etNim.text.clear()
    }
}
