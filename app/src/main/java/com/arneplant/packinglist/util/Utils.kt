package com.arneplant.packinglist.util

import android.content.Context
import android.net.wifi.WifiManager
import android.text.format.Formatter
import com.arneplant.packinglist.model.PackingList
import com.arneplant.packinglist.model.dto.Mensaje
import com.arneplant.packinglist.model.dto.PackingListDTO
import com.google.gson.GsonBuilder
import java.lang.Exception

class Utils {
    companion object {
        fun getTipo(cod: String): Tipo {
            return if (cod.startsWith("01")) {
                Tipo.Contenedor
            } else if (cod.startsWith("192")) {
                Tipo.IP
            }else if (cod.startsWith("03")) {
                Tipo.Ubicacion
            } else{
                Tipo.None
            }
        }

        fun getLocalIpAddress(context: Context): String {
            try {

                val wifiManager: WifiManager = context?.getSystemService(Context.WIFI_SERVICE) as WifiManager
                val wifiInfo = wifiManager.connectionInfo
                val ip = wifiInfo.ipAddress
                return Formatter.formatIpAddress(ip)
            }catch (ex: Exception){
                return ""
            }
        }

        private fun ipToString(i: Int): String {
            return (i and 0xFF).toString() + "." +
                    (i shr 8 and 0xFF) + "." +
                    (i shr 16 and 0xFF) + "." +
                    (i shr 24 and 0xFF)
        }

        fun JSON2PackingListsDTO(json :String): List<PackingListDTO> {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'hh:mm:ss").create()
            val model = gson.fromJson(json, Array<PackingListDTO>::class.java).toList()
            return model
        }

        fun PackingList2PackingListDTO(packingList: PackingList):PackingListDTO{
            var dto = PackingListDTO()
            dto.codigoAgrupacion = packingList.codAgrupacion
            dto.id = packingList.id
            dto.observacion = packingList.observacion
            dto.cajas = ArrayList()
            return dto
        }

        fun descifrarMensaje (msg: String): Mensaje{
            var troceado = msg.split(';')
            var mensaje = Mensaje()
            mensaje.tipo = troceado[0].toInt()
            mensaje.ipEmisor = troceado[1]
            mensaje.ipDestinatario = troceado[2]
            mensaje.cuerpo = troceado[3]
            return mensaje
        }

        fun getTituloPackingList(p: PackingListDTO):String{
            var titulo = p.id.toString()+"\n"+p!!.cajas.size.toString()
            return titulo
        }
    }
}