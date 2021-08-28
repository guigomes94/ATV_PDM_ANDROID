package com.example.wishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class DesejoAdapter (val lista: MutableList<Desejo>): RecyclerView.Adapter<DesejoAdapter.DesejoViewHolder>() {
    var listener: OnItemClickRecycleView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DesejoViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_item, parent, false)
        return DesejoViewHolder(view)
    }

    override fun onBindViewHolder(holder: DesejoViewHolder, position: Int) {
        val desejo = this.lista.get(position)
        holder.tvDescricao.text = desejo.descricao
        holder.tvNota.text = desejo.nota.toString()
    }

    override fun getItemCount(): Int = this.lista.size

    fun del(position: Int){
        this.lista.removeAt(position)
        notifyItemRemoved(position)
    }

    fun swap(from: Int, to: Int){
        Collections.swap(this.lista, from, to)
        notifyItemMoved(from, to)
    }

    inner class DesejoViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvDescricao: TextView
        var tvNota: TextView

        init {
            this.tvDescricao = itemView.findViewById(R.id.tvItemDescricao)
            this.tvNota = itemView.findViewById(R.id.tvItemNota)

            itemView.setOnClickListener{
                this@DesejoAdapter.listener?.onItemClick(this.adapterPosition)
            }

            itemView.setOnLongClickListener{
                // ??
                return@setOnLongClickListener true
            }
        }
    }
}