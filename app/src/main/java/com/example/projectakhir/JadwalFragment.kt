package com.example.projectakhir

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectakhir.adapter.JadwalAdapter

class JadwalFragment : Fragment() {

    private fun setupListLayout() {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.rvJadwal)
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())

        val adapter = JadwalAdapter(getClub(), requireContext())
        recyclerView?.adapter = adapter

        recyclerView?.setHasFixedSize(true)
    }

    private fun getClub(): ArrayList<Jadwal> {
        val image1 = resources.obtainTypedArray(R.array.daftarLogo1)
        val nama1 = resources.getStringArray(R.array.daftarNama1)
        val image2 = resources.obtainTypedArray(R.array.daftarLogo2)
        val nama2 = resources.getStringArray(R.array.daftarNama2)

        val data = ArrayList<Jadwal>()

        val length = minOf(nama1.size, nama2.size)

        for (i in 0 until length) {
            val club2 = if (i < nama2.size) nama2[i] else ""
            val data1 = Jadwal(nama1[i], club2, image1.getResourceId(i, -1), image2.getResourceId(i, -1))
            data.add(data1)
        }

        // Tambahkan sisa klub jika ukuran tidak sama
        if (nama1.size > nama2.size) {
            for (i in length until nama1.size) {
                val data1 = Jadwal(nama1[i], "", image1.getResourceId(i, -1), -1) // klub kedua diisi dengan string kosong dan ID gambar kedua diatur ke -1
                data.add(data1)
            }
        } else if (nama2.size > nama1.size) {
            for (i in length until nama2.size) {
                val data1 = Jadwal("", nama2[i], -1, image2.getResourceId(i, -1)) // klub pertama diisi dengan string kosong dan ID gambar pertama diatur ke -1
                data.add(data1)
            }
        }

        image1.recycle()
        image2.recycle()

        return data
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListLayout()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jadwal, container, false)
    }
}
