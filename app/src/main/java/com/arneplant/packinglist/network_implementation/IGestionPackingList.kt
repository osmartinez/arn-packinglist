package com.arneplant.packinglist.network_implementation

import com.arneplant.packinglist.model.PackingList
import com.arneplant.packinglist.model.Respuesta
import com.arneplant.packinglist.model.dto.Contenedor
import com.arneplant.packinglist.model.dto.PackinglistEtiqueta
import com.arneplant.packinglist.model.dto.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface IGestionPackingList {
    @POST("packinglists/crear")
    abstract fun crearPackingList(@Body usuario: Usuario): Call<PackingList>

    @POST("packinglists/insertarContenedor")
    abstract fun addContenedorAPackingList(@Body etiquetaPackinglistEtiqueta: PackinglistEtiqueta): Call<Contenedor>

}