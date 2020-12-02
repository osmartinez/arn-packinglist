package com.arneplant.packinglist.network_interface

import com.arneplant.packinglist.model.PackingList
import com.arneplant.packinglist.model.Respuesta
import com.arneplant.packinglist.model.dto.Contenedor
import com.arneplant.packinglist.model.dto.PackinglistEtiqueta
import com.arneplant.packinglist.model.dto.Usuario
import com.arneplant.packinglist.network.RetrofitInstance
import com.arneplant.packinglist.network_implementation.IGestionPackingList
import retrofit2.Call

class GestionPackingList: IGestionPackingList {
    override fun addContenedorAPackingList(etiquetaPackinglistEtiqueta: PackinglistEtiqueta): Call<Contenedor> {
        return service.addContenedorAPackingList(etiquetaPackinglistEtiqueta)
    }

    private var service: IGestionPackingList = RetrofitInstance.getRetrofitInstance()!!.create(IGestionPackingList::class.java)

    override fun crearPackingList(user: Usuario): Call<PackingList> {
        return service.crearPackingList(user)
    }


}