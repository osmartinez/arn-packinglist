package com.arneplant.packinglist.network_interface

import com.arneplant.packinglist.model.Ubicacion
import com.arneplant.packinglist.model.dto.BodyUbicar
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface IUbicacionService {
    @GET("api/ubicaciones/{codigoUbicacion}")
    fun getUbicacion(@Path("codigoUbicacion") codigoUbicacion: String): Call<Ubicacion>

    @POST("api/ubicaciones/ubicarBarquilla")
    fun ubicarBarquilla(@Body body: BodyUbicar): Call<Void>

    @GET("api/ubicaciones/ubicarPrepaquete/{codigoPrepaquete}/{codigoUbicacion}")
    fun ubicarPrepaquete(@Path("codigoPrepaquete") codigoPrepaquete: String,
                         @Path("codigoUbicacion") codigoUbicacion: String): Call<Int>
}