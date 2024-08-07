package br.edu.infnet.receitafacil_microservices.receitas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.receitafacil_microservices.R
import br.edu.infnet.receitafacil_microservices.model.Receita
import com.squareup.picasso.Picasso
import kotlinx.coroutines.withContext

class ReceitaAdapter(private val receitaList: List<Receita>) :
    RecyclerView.Adapter<ReceitaAdapter.ReceitaViewHolder>() {

    class ReceitaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView:  CardView = itemView.findViewById(R.id.ReceitaLayout)
        val imageView: ImageView = itemView.findViewById(R.id.img_receita_item)
        val textView1: TextView = itemView.findViewById(R.id.txt_nome_item)
        val textView2: TextView = itemView.findViewById(R.id.txt_ingredientes_item)
        val textView3: TextView = itemView.findViewById(R.id.txt_preparo_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceitaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return ReceitaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ReceitaViewHolder, position: Int) {
        val currentItem = receitaList[position]
        var ingredientes: String = "\n"

        Picasso.get().load(currentItem.figura).placeholder(R.drawable.ic_wait).into(holder.imageView);
        holder.textView1.text = currentItem.nome + String.format(": custo (R$ %.2f)", currentItem.custo)
        currentItem.ingredientes?.forEach { i -> ingredientes += "- " + i.quantidade + " " + i.medida.nome + " de " + i.item.descricao + "\n" }
        holder.textView2.text = ingredientes
        holder.textView3.text = currentItem.preparo

        // Ao selecionar item, abre tela para atualizar / apagar cadastro
        holder.cardView.setOnClickListener {
            val action = ReceitasFragmentDirections.actionNavReceitasToNavAtualizar(currentItem) // envia item selecionado como argumento para AtualizarFragment

            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = receitaList.size
}