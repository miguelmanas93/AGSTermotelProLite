package com.miguel.ags.agstermotelprolite.ui.home

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.miguel.ags.agstermotelprolite.R
import com.miguel.ags.agstermotelprolite.data.model.Sondas
import kotlinx.android.synthetic.main.table_list_item.view.*

class SondasAdapter(private val sondasList: List<Sondas>) : RecyclerView.Adapter<SondasAdapter.RowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.table_list_item, parent, false)
        return RowViewHolder(itemView)
    }

    private fun setHeaderBg(view: View) {
        view.setBackgroundResource(R.drawable.table_header_cell_bg)
    }

    private fun setContentBg(view: View) {
        view.setBackgroundResource(R.drawable.table_content_cell_bg)
    }

    override fun onBindViewHolder(holder: RowViewHolder, position: Int) {
        val rowPos = holder.adapterPosition

        if (rowPos == 0) {
            // Header Cells. Main Headings appear here
            holder.itemView.apply {
                setHeaderBg(tvNumSerie)
                setHeaderBg(tvAlias)
                setHeaderBg(tvDescripcion)
                setHeaderBg(tvTemperatura)

                tvNumSerie.text = "Num Serie"
                tvAlias.text = "Alias"
                tvDescripcion.text = "Descripción"
                tvTemperatura.text = "Temperatura"
            }
        } else {
            val sondaValor = sondasList[rowPos - 1]

            holder.itemView.apply {
                setContentBg(tvNumSerie)
                setContentBg(tvAlias)
                setContentBg(tvDescripcion)
                setContentBg(tvTemperatura)

                tvNumSerie.text = sondaValor.numSerie
                tvAlias.text = sondaValor.alias
                tvDescripcion.text = sondaValor.descripcion
                tvTemperatura.text = sondaValor.temperatura.toString() + "ºC"
            }
        }
    }

    override fun getItemCount(): Int {
        return sondasList.size + 1 // one more to add header row
    }

    inner class RowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}