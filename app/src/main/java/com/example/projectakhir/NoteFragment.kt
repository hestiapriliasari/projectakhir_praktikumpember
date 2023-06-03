package com.example.projectakhir

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectakhir.adapter.UserAdapter
import com.example.projectakhir.data.AppDatabase
import com.example.projectakhir.data.entity.Club
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NoteFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private var list = mutableListOf<Club>()
    private lateinit var adapter: UserAdapter
    private lateinit var database: AppDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_note, container, false)

        recyclerView = rootView.findViewById(R.id.recycler_view)
        fab = rootView.findViewById(R.id.floating_btn)

        database = AppDatabase.getInstance(requireContext())
        adapter = UserAdapter(list)
        adapter.setDialog(object : UserAdapter.Dialog {
            override fun onClick(position: Int) {
                val dialog = AlertDialog.Builder(requireContext())
                dialog.setTitle(list[position].clubName)
                dialog.setItems(R.array.pilihan, DialogInterface.OnClickListener { dialog, which ->
                    if (which == 0) {
                        // coding ubah
                        val editFragment = EditFragment()
                        val bundle = Bundle()
                        bundle.putInt("id", list[position].id ?: 0)
                        editFragment.arguments = bundle

                        val fragmentManager = requireActivity().supportFragmentManager
                        val fragmentTransaction = fragmentManager.beginTransaction()
                        fragmentTransaction.replace(R.id.frame_container, editFragment)
                        fragmentTransaction.addToBackStack(null)
                        fragmentTransaction.commit()
                    } else if (which == 1) {
                        // coding hapus
                        database.clubDao().delete(list[position])
                        getData()
                    } else {
                        // coding batal
                        dialog.dismiss()
                    }
                })
                val dialogView = dialog.create()
                dialogView.show()
            }
        })

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))

        fab.setOnClickListener {
            val editFragment = EditFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frame_container, editFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        return rootView
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    private fun getData() {
        list.clear()
        list.addAll(database.clubDao().getAll())
        adapter.notifyDataSetChanged()
    }
}
