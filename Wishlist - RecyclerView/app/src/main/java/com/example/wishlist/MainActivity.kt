package com.example.wishlist

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var lvDesejos: RecyclerView
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var lista: MutableList<Desejo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.lista = mutableListOf()

        this.lvDesejos = findViewById(R.id.lvDesejos)
        this.fabAdd = findViewById(R.id.fabAdd)

        this.lvDesejos.adapter = DesejoAdapter(this.lista)
        (this.lvDesejos.adapter as DesejoAdapter).listener = OnItemClickListener()

        ItemTouchHelper(OnSwipe()).attachToRecyclerView(this.lvDesejos)

        val resultForm = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK){
                val desejo = it.data?.getSerializableExtra("DESEJO") as Desejo
                this.lista.add(desejo)

                (this.lvDesejos.adapter as DesejoAdapter).notifyDataSetChanged()
            }
        }

        this.fabAdd.setOnClickListener{
            val intent = Intent(this, FormActivity::class.java)
            // (this.lvDesejos.adapter as DesejoAdapter).notifyDataSetChanged()
            resultForm.launch(intent)
        }

        this.fabAdd.setOnLongClickListener{
            this.limpar()
            (this.lvDesejos.adapter as DesejoAdapter).notifyDataSetChanged()
            return@setOnLongClickListener true
        }

    }

    fun limpar(){
        this.lista.clear()
    }

    inner class OnItemClickListener: OnItemClickRecycleView{
        override fun onItemClick(position: Int) {
            val desejo = this@MainActivity.lista.get(position)
            Toast.makeText(this@MainActivity, desejo.descricao, Toast.LENGTH_SHORT).show()
        }
    }

    inner class OnSwipe: ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN,
        ItemTouchHelper.START or ItemTouchHelper.END){

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder,
        ): Boolean {
            (this@MainActivity.lvDesejos.adapter as DesejoAdapter).swap(viewHolder.adapterPosition, target.adapterPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            this@MainActivity.showConfirmDialog(viewHolder)

        }


    }

    private fun showConfirmDialog(viewHolder: RecyclerView.ViewHolder) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Tem certeza que deseja excluir?")
            .setPositiveButton("Sim",
                DialogInterface.OnClickListener { dialog, id ->
                    (this@MainActivity.lvDesejos.adapter as DesejoAdapter).del(viewHolder.adapterPosition)
                    // (this@MainActivity.lvDesejos.adapter as DesejoAdapter).notifyDataSetChanged()
                    Toast.makeText(this,"Excluído!!", Toast.LENGTH_SHORT).show()
                })
            .setNegativeButton("Não",
                DialogInterface.OnClickListener { dialog, id ->
                    (this@MainActivity.lvDesejos.adapter as DesejoAdapter).notifyItemChanged(viewHolder.adapterPosition)
                    Toast.makeText(this,"Você cancelou!", Toast.LENGTH_SHORT).show()
                })
            .create().show()
    }
}