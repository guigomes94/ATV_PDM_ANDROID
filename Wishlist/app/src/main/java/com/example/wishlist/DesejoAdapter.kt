package com.example.wishlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class DesejoAdapter (val lista: MutableList<Desejo>): RecyclerView.Adapter<DesejoAdapter.DesejoViewHolder>(){

    var listener: OnItemClickRecycleView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DesejoViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.activity_main, parent, false)
        return DesejoViewHolder(view)
    }

    override fun onBindViewHolder(holder: DesejoViewHolder, position: Int) {
        val desejo = this.lista.get(position)
        holder.tvID.text = desejo.id.toString()
        holder.tvNome.text = pessoa.nome
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
        var tvID: TextView
        var tvNome: TextView

        init {
            this.tvID = itemView.findViewById(R.id.tvItemID)
            this.tvNome = itemView.findViewById(R.id.tvItemNome)

            itemView.setOnClickListener{
                this@PessoaAdapter.listener?.onItemClick(this.adapterPosition)
            }

            itemView.setOnLongClickListener{
                // ??
                return@setOnLongClickListener true
            }
        }
    }
}