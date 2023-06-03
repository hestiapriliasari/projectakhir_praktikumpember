package com.example.projectakhir.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectakhir.Jadwal
import com.example.projectakhir.JadwalFragment
import com.example.projectakhir.R

class JadwalAdapter (
    private val clubList: ArrayList<Jadwal>,
    private val context: Context
        ): RecyclerView.Adapter<JadwalAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img1 : ImageView = itemView.findViewById(R.id.gambar1)
        val namaClub1 : TextView = itemView.findViewById(R.id.tvNama1)
        val img2 : ImageView = itemView.findViewById(R.id.gambar2)
        val namaClub2 : TextView = itemView.findViewById(R.id.tvNama2)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JadwalAdapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: JadwalAdapter.ViewHolder, position: Int) {
        holder.namaClub1.text = clubList[position].club1
        Glide.with(context)
            .load(clubList[position].image1)
            .into(holder.img1)

        holder.namaClub2.text = clubList[position].club2
        Glide.with(context)
            .load(clubList[position].image2)
            .into(holder.img2)
    }

    override fun getItemCount(): Int {
        return clubList.size
    }

}