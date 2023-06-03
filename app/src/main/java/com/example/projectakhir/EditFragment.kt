package com.example.projectakhir

import com.example.projectakhir.data.AppDatabase
import com.example.projectakhir.data.entity.Club
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class EditFragment : Fragment() {
    private lateinit var clubName: EditText
    private lateinit var julukan: EditText
    private lateinit var stadion: EditText
    private lateinit var btnSave: Button
    private lateinit var database: AppDatabase
    private var userId: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_edit, container, false)

        clubName = rootView.findViewById(R.id.club_name)
        julukan = rootView.findViewById(R.id.julukan)
        stadion = rootView.findViewById(R.id.stadion)
        btnSave = rootView.findViewById(R.id.btn_save)

        database = AppDatabase.getInstance(requireContext())

        userId = arguments?.getInt("id") ?: 0
        val user = if (userId != 0) {
            database.clubDao().get(userId)
        } else {
            null
        }

        if (user != null) {
            clubName.setText(user.clubName ?: "")
            julukan.setText(user.julukan ?: "")
            stadion.setText(user.stadion ?: "")
        } else {
            Toast.makeText(requireContext(), "Data Kosong", Toast.LENGTH_SHORT).show()
        }


        btnSave.setOnClickListener {
            val clubNameText = clubName.text.toString()
            val julukanText = julukan.text.toString()
            val stadionText = stadion.text.toString()

            if (clubNameText.isNotEmpty() && julukanText.isNotEmpty() && stadionText.isNotEmpty()) {
                val updatedClub = Club(userId, clubNameText, julukanText, stadionText)
                val insertClub = Club(null,clubNameText, julukanText, stadionText)

                if (user != null) {
                    database.clubDao().update(updatedClub)
                    Toast.makeText(requireContext(), "Data berhasil diperbarui", Toast.LENGTH_SHORT).show()
                } else {
                    database.clubDao().insertAll(insertClub)
                    Toast.makeText(requireContext(), "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                }

                val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
                fragmentManager.popBackStack()
            } else {
                Toast.makeText(requireContext(), "Data Tidak Diisi Dengan Valid", Toast.LENGTH_SHORT).show()
            }
        }

        return rootView
    }
}
