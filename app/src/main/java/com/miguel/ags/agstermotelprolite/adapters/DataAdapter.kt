//package com.miguel.ags.agstermotelprolite.adapters
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.miguel.ags.agstermotelprolite.R
//import com.miguel.ags.agstermotelprolite.data.model.Sondas
//import org.w3c.dom.Text
//
//class DataAdapter(private val list: List<Sondas>) : RecyclerView.Adapter<SondasViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SondasViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        return SondasViewHolder(inflater, parent)
//    }
//
//    override fun getItemCount(): Int = list.size
//
//
//    override fun onBindViewHolder(holder: SondasViewHolder, position: Int) {
//        val sondas: Sondas = list[position]
//        holder.bind(sondas)
//    }
//}
//
//
//class SondasViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
//    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)) {
//    private var tvNumDeSerie: TextView? = null
//    private var tvAlias: TextView? = null
//    private var tvDescripcion: TextView? = null
//    private var tvTemperatura: TextView? = null
//
//
//
//    init {
//        tvNumDeSerie = itemView.findViewById(R.id.tvListNumSerie)
//        tvAlias = itemView.findViewById(R.id.tvListAlias)
//        tvDescripcion = itemView.findViewById(R.id.tvListDescripcion)
//        tvTemperatura = itemView.findViewById(R.id.tvListTemperatura)
//    }
//
//    fun bind(sondas: Sondas) {
//        tvNumDeSerie?.text = sondas.numSerie
//        tvAlias?.text = sondas.alias
//        tvDescripcion?.text = sondas.descripcion
//        tvTemperatura?.text = sondas.temperatura.toString()
//
//    }
//
//}