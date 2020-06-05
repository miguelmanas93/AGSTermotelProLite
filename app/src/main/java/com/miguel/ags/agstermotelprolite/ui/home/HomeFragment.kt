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
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var datosCamaras: HomeViewModel

    private val userViewModel by viewModel<HomeViewModel>()

    private lateinit var spinner: Spinner
    private lateinit var reciclerview: RecyclerView

    val ID_SPINNER = 2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        datosCamaras =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        datosCamaras = ViewModelProvider(this).get(HomeViewModel::class.java)
        val textView: TextView = root.findViewById(R.id.text_home)
        datosCamaras.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        spinner = root.findViewById<Spinner>(R.id.spinnerCamaras)
        spinner.id = ID_SPINNER
        datosCamaras.cargarUsuarios()
        cargarCamarasSpinner()
        reciclerview = root.findViewById(R.id.recyclerViewSondasList)
        reciclerview.layoutManager = LinearLayoutManager(activity)

        with(spinner) {
            setSelection(0, false)
            onItemSelectedListener = this@HomeFragment
            gravity = Gravity.CENTER

        }
        return root
    }

    fun cargarCamarasSpinner() {
        val nombres: ArrayList<String> = arrayListOf("¿Que camara quieres ver?")
        userViewModel.data.observe(viewLifecycleOwner, Observer {
            for (camaras in it[0]?.camaras!!)
                nombres.add(camaras.nombre)
        })
        spinner.let {
            val arrayAdapter = context?.let {
                    ArrayAdapter(
                        it,
                        android.R.layout.simple_expandable_list_item_1,
                        nombres
                    )
                }
            spinner.adapter = arrayAdapter
        }
    }




    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(context, "Nada Seleccinado", Toast.LENGTH_LONG).show()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (view?.id) {
            1 -> {
                context?.let {
                    userViewModel.cargarSondas(reciclerview, position - 1,
                        it, viewLifecycleOwner)
                }
            }
            else -> {
                //   Toast.makeText(context, "Spinner 3 Position:${position}", Toast.LENGTH_LONG).show()
                context?.let {
                    userViewModel.cargarSondas(reciclerview, position - 1,
                        it, viewLifecycleOwner)
                }
            }
        }
    }
}