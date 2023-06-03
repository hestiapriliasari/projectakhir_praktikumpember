package com.example.projectakhir.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectakhir.R
import com.example.projectakhir.data.entity.Club

class UserAdapter(
    var list: List<Club>
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private lateinit var dialog: Dialog

    fun setDialog(dialog: Dialog){
        this.dialog= dialog
    }

    interface Dialog {
        fun onClick(position: Int)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var clubName: TextView
        var julukan: TextView
        var stadion: TextView
        init {
            clubName = view.findViewById(R.id.club_name)
            julukan = view.findViewById(R.id.julukan)
            stadion = view.findViewById(R.id.stadion)
            view.setOnClickListener{
                dialog.onClick(layoutPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu,parent,false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.clubName.text = list[position].clubName
        holder.julukan.text = list[position].julukan
        holder.stadion.text = list[position].stadion
    }
}