package com.arneplant.packinglist.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arneplant.packinglist.R
import com.arneplant.packinglist.adapter.CajasUbicadasAdapter
import com.arneplant.packinglist.ui_interface.BuscadorFragmentDelegate
import com.arneplant.packinglist.util.Tipo
import com.arneplant.packinglist.util.Utils

class UbicarActivity : AppCompatActivity(),BuscadorFragmentDelegate {

    var adapter: CajasUbicadasAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubicar)

        this.title = "UBICAR"
    }

    override fun buscadorFragmentCodigoEscaneado(msg: String) {
       when(Utils.getTipo(msg)){
           Tipo.Ubicacion->{}
           Tipo.Contenedor->{}
       }
    }

    private fun cargarUbicacion(cod:String){

    }

}
