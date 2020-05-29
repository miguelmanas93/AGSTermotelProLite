package com.miguel.ags.agstermotelprolite.ui.home

import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.miguel.ags.agstermotelprolite.data.model.Camaras
import com.miguel.ags.agstermotelprolite.network.APIService
import com.miguel.ags.agstermotelprolite.network.RetrofitClient
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type


class SpinnerViewModel : ViewModel() {
    lateinit var spinner: Spinner

    lateinit var camaras: List<Camaras>

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState);
//;
//        val languages = resources.getStringArray(R.array.sp)
//
//        // access the spinner
//        val spinner = findViewById<Spinner>(R.id.)
//        if (spinner != null) {
//            val adapter = ArrayAdapter(this,
//                R.layout.simple_spinner_item, languages)
//            spinner.adapter = adapter
//
//            spinner.onItemSelectedListener = object :
//                AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(parent: AdapterView<*>,
//                                            view: View, position: Int, id: Long) {
//                    Toast.makeText(this@SpinnerViewModel,
//                        getString(R.string.selected_item) + " " +
//                                "" + languages[position], Toast.LENGTH_SHORT).show()
//                }
//
//                override fun onNothingSelected(parent: AdapterView<*>) {
//                    // write code to perform some action
//                }
//            }
//        }
//    }

    fun loadSpinnerData() {
        val purApp = RetrofitClient.getRetrofitInstance().create(APIService::class.java)

        val releaseResponse = purApp.getCamaras(0).execute()
        val releases = releaseResponse.body()

        val result = ArrayList<Camaras>()

    }





}

