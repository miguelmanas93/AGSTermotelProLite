package com.miguel.ags.agstermotelprolite.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
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

    private val userViewModel by viewModel<DatosCamaras>()
    private val datosSondas by viewModel<DatosCamaras>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)


        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        //Spiner con los datos
        val spinner = root.findViewById<View>(R.id.spinnerCamaras) as Spinner
        datosCamaras = ViewModelProviders.of(this).get(DatosCamaras::class.java)
        datosCamaras.cargarUsuarios()
        var nombresCamarasOpt: String? = null
        userViewModel.data.observe(viewLifecycleOwner, Observer {
            for (nombresCamaras in it[0].camaras!!)
                nombresCamarasOpt = nombresCamaras.nombre
        })

        //ReciclerView
        val reciclerview = root.findViewById(R.id.recyclerViewSondasList) as RecyclerView
        reciclerview.layoutManager = LinearLayoutManager(activity)
        reciclerview.adapter = TableViewAdapter(sondasListTest)

        return root
    }

    private val sondasListTest = ArrayList<Sondas>().apply {

        datosCamaras.cargarUsuarios()
       // datosSondas.cargarDatosSondas(0, "AGS Pita")
        datosCamaras.data.observe(viewLifecycleOwner, Observer {
            for (sondas in it.get(0).camaras!![0].sondas!!)
                add(Sondas(sondas.numSerie, sondas.alias, sondas.descripcion, sondas.temperatura))
        })

//        add(Sondas("1", "Pirates of the Caribbean: On Stranger Tides", "2011", 378.0))
//        add(Sondas("2", "Avengers: Age of Ultron", "2015", 365.0))
//        add(Sondas("3", "Avengers: Infinity War", "2018", 316.0))
//        add(Sondas("4", "Pirates of the Caribbean: At World's End", "2007", 300.0))
//        add(Sondas("5", "Justice League", "2017", 300.0))
//        add(Sondas("6", "Solo: A Star Wars Story", "2018", 275.0))
//        add(Sondas("7", "John Carter", "2012", 264.0))
//        add(Sondas("8", "Batman v Superman: Dawn of Justice", "2016", 263.0))
//        add(Sondas("9", "Star Wars: The Last Jedi", "2017", 26.3))
//        add(Sondas("9", "Star Wars: The Last Jedi", "2017", 26.3))
//        add(Sondas("9", "Star Wars: The Last Jedi", "2017", 26.3))
//        add(Sondas("9", "Star Wars: The Last Jedi", "2017", 26.3))
//        add(Sondas("9", "Star Wars: The Last Jedi", "2017", 26.3))
//        add(Sondas("9", "Star Wars: The Last Jedi", "2017", 26.3))
//        add(Sondas("9", "Star Wars: The Last Jedi", "2017", 26.3))
//        add(Sondas("9", "Star Wars: The Last Jedi", "2017", 263.0))
//        add(Sondas("9", "Star Wars: The Last Jedi", "2017", 26.3))
//        add(Sondas("9", "Star Wars: The Last Jedi", "2017", 26.3))

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }

}




