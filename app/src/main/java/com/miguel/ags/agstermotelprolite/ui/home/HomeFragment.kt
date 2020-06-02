package com.miguel.ags.agstermotelprolite.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ListAdapter
import com.miguel.ags.agstermotelprolite.R


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var spinnerViewModel: SpinnerViewModel

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
        spinnerViewModel = ViewModelProviders.of(this).get(SpinnerViewModel::class.java)
        val datos = spinnerViewModel.cargarDatos()






      //  val adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        //spinner.adapter = adapter

        //Recicler View para mostrar datos de la tabla


        return root
    }

}



