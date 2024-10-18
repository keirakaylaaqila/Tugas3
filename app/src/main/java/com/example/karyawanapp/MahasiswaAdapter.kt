import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.karyawanapp.Mahasiswa
import com.example.karyawanapp.R

class MahasiswaAdapter(
    private var mahasiswaList: List<Mahasiswa>,
    private val onDeleteClick: (Mahasiswa) -> Unit,
    private val onUpdateClick: (Mahasiswa) -> Unit // Callback untuk update
) : RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder>() {

    inner class MahasiswaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNama: TextView = itemView.findViewById(R.id.tvNama)
        private val tvProdi: TextView = itemView.findViewById(R.id.tvProdi)
        private val tvNim: TextView = itemView.findViewById(R.id.tvNim)
        private val btnUpdate: Button = itemView.findViewById(R.id.btnUpdate)
        private val btnHapus: Button = itemView.findViewById(R.id.btnHapus)

        fun bind(mahasiswa: Mahasiswa) {
            tvNama.text = mahasiswa.nama
            tvProdi.text = mahasiswa.prodi
            tvNim.text = mahasiswa.nim.toString()

            btnUpdate.setOnClickListener {
                onUpdateClick(mahasiswa)
            }

            btnHapus.setOnClickListener {
                onDeleteClick(mahasiswa)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MahasiswaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mahasiswa, parent, false)
        return MahasiswaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MahasiswaViewHolder, position: Int) {
        holder.bind(mahasiswaList[position])
    }

    override fun getItemCount() = mahasiswaList.size

    fun updateMahasiswaList(newMahasiswaList: List<Mahasiswa>) {
        mahasiswaList = newMahasiswaList
        notifyDataSetChanged()
    }
}
