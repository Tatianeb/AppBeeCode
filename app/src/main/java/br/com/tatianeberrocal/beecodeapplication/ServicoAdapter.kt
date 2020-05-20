package br.com.tatianeberrocal.beecodeapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class ServicoAdapter (
    val servicos: List<Servicos>,
    val onClick: (Servicos) -> Unit): RecyclerView.Adapter<ServicoAdapter.ServicosViewHolder>() {


    class ServicosViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cardNome: TextView
        val cardImg : ImageView
        var cardProgress: ProgressBar
        var cardView: CardView

        init {
            cardNome = view.findViewById<TextView>(R.id.cardNome)
            cardImg = view.findViewById<ImageView>(R.id.cardImg)
            cardProgress = view.findViewById<ProgressBar>(R.id.cardProgress)
            cardView = view.findViewById<CardView>(R.id.card_servicos)

        }

    }

    override fun getItemCount() = this.servicos.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_servico, parent, false)
        val holder = ServicosViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ServicosViewHolder, position: Int) {
        val context = holder.itemView.context

        val servicos = servicos[position]

        holder.cardNome.text = servicos.nome
        holder.cardProgress.visibility = View.VISIBLE

        Picasso.with(context).load(servicos.foto).fit().into(holder.cardImg,
            object: com.squareup.picasso.Callback{
                override fun onSuccess() {
                    holder.cardProgress.visibility = View.GONE
                }

                override fun onError() {
                    holder.cardProgress.visibility = View.GONE
                }
            })

        holder.itemView.setOnClickListener {onClick(servicos)}
    }
}