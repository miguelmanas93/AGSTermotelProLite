package com.miguel.ags.agstermotelprolite.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miguel.ags.agstermotelprolite.R
import com.miguel.ags.agstermotelprolite.adapters.TableViewAdapter
import com.miguel.ags.agstermotelprolite.data.DatosCamaras
import com.miguel.ags.agstermotelprolite.data.model.Sondas
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var datosCamaras: DatosCamaras
    private lateinit var datosSondas: DatosCamaras

    private val userViewModel by viewModel<DatosCamaras>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        datosCamaras = ViewModelProviders.of(this).get(DatosCamaras::class.java)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        //Spiner con los datos
        val nombres: ArrayList<String> = arrayListOf("¿Que camara quieres ver?")
        datosCamaras.cargarUsuarios()
        userViewModel.data.observe(viewLifecycleOwner, Observer {
            for (camaras in it[0].camaras!!)
                nombres.add(camaras.nombre)
        })

        val spinner = root.findViewById<Spinner>(R.id.spinnerCamaras)
        if (spinner != null) {
            val arrayAdapter =
                context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, nombres) }
            spinner.adapter = arrayAdapter
        }

        cargarSondas(root)

        return root
    }

    fun cargarSondas(root: View) {
        val reciclerview = root.findViewById(R.id.recyclerViewSondasList) as RecyclerView
        reciclerview.layoutManager = LinearLayoutManager(activity)

        val sondasListTest = ArrayList<Sondas>().apply {
            userViewModel.data.observe(viewLifecycleOwner, Observer {
                for (sondas in it[0].camaras!![0].sondas!!)
                    add(Sondas(sondas.numSerie, sondas.alias, sondas.descripcion,sondas.temperatura))
                reciclerview.adapter?.notifyDataSetChanged()
            })
        }
        reciclerview.adapter = TableViewAdapter(sondasListTest)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }
}