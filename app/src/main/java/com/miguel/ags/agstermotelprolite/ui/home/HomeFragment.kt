package com.miguel.ags.agstermotelprolite.ui.home

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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

    private val userViewModel by viewModel<DatosCamaras>()

    private lateinit var spinner: Spinner
    private lateinit var reciclerview : RecyclerView

    val ID_SPINNER = 2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        datosCamaras = ViewModelProvider(this).get(DatosCamaras::class.java)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        spinner = root.findViewById<Spinner>(R.id.spinnerCamaras)
        spinner.id = ID_SPINNER
        datosCamaras.cargarUsuarios()
        cargarCamaras()
        reciclerview = root.findViewById(R.id.recyclerViewSondasList)
        reciclerview.layoutManager = LinearLayoutManager(activity)

        with(spinner) {
            setSelection(0, false)
            prompt = "Select your favourite language"
            onItemSelectedListener = this@HomeFragment
            gravity = Gravity.CENTER

        }
        return root
    }

    fun cargarCamaras() {
        val nombres: ArrayList<String> = arrayListOf("¿Que camara quieres ver?")
        userViewModel.data.observe(viewLifecycleOwner, Observer {
            for (camaras in it[0].camaras!!)
                nombres.add(camaras.nombre)
        })
        if (spinner != null) {
            val arrayAdapter =
                context?.let { ArrayAdapter(it, android.R.layout.simple_expandable_list_item_1, nombres) }
            spinner.adapter = arrayAdapter
        }
    }

    private fun cargarSondas(reciclerview: RecyclerView, idCamara: Int) {
        if(idCamara < 0){
            Toast.makeText(context, "Seleccione una de las camaras por favor", Toast.LENGTH_LONG).show()
        }else{
       val sondasListTest = ArrayList<Sondas>().apply {
            userViewModel.data.observe(viewLifecycleOwner, Observer {
                for (sondas in it[0].camaras!![idCamara].sondas!!)
                    add(
                        Sondas(
                            sondas.numSerie,
                            sondas.alias,
                            sondas.descripcion,
                            sondas.temperatura
                        )
                    )
                reciclerview.adapter?.notifyDataSetChanged()
            })
        }
        reciclerview.adapter = TableViewAdapter(sondasListTest)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(context, "Nada Seleccinado", Toast.LENGTH_LONG).show()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (view?.id) {
            1 ->{
               // Toast.makeText(context, "Spinner 2 Position:${position}", Toast.LENGTH_LONG).show()
                cargarSondas(reciclerview, position-1)
                }
            else -> {
             //   Toast.makeText(context, "Spinner 3 Position:${position}", Toast.LENGTH_LONG).show()
                cargarSondas(reciclerview, position-1)
            }
        }
    }
}