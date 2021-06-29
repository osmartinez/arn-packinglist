package com.arneplant.packinglist.network_interface

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
    @POST("api/packinglists/crear")
    fun crearPackingList(@Body usuario: Usuario): Call<PackingList>

    @POST("api/packinglists/insertarContenedor")
    fun addContenedorAPackingList(@Body etiquetaPackinglistEtiqueta: PackinglistEtiqueta): Call<Contenedor>
}