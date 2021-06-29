package com.arneplant.packinglist.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.arneplant.packinglist.R
import com.arneplant.packinglist.model.PackingList
import com.arneplant.packinglist.model.dto.PackingListDTO
import com.arneplant.packinglist.model.dto.PackingListsWrapper
import com.arneplant.packinglist.model.dto.Usuario
import com.arneplant.packinglist.network.TCP
import com.arneplant.packinglist.network_implementation.GestionPackingList
import com.arneplant.packinglist.ui_interface.BuscadorFragmentDelegate
import com.arneplant.packinglist.util.Tipo
import com.arneplant.packinglist.util.Utils
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.ServerSocket
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), BuscadorFragmentDelegate {
    var ip = ""
    var sincronizado = false
    var ctx : Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.title = "PACKINGLIST"

        btNuevo.setOnClickListener { nuevoPackingList() }
    }

    private fun nuevoPackingList() {
        val service = GestionPackingList()
        var user = Usuario()
        user.usuario = "pda"
        val call = service.crearPackingList(user)
        call.enqueue(object : Callback<PackingList>{
            override fun onFailure(call: Call<PackingList>, t: Throwable) {
                Toast.makeText(ctx, t.message?:"Error en la petición", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<PackingList>, response: Response<PackingList>) {
                var packingList = response.body()

                if(packingList!=null){
                    var dto = Utils.PackingList2PackingListDTO(packingList)

                    var packings = ArrayList<PackingListDTO>()
                    packings.add(dto)
                    var paletWrapper = PackingListsWrapper()
                    paletWrapper.packingListsDTO = packings
                    abrirPackingActivity(paletWrapper)

                }
            }

        })
    }

    private fun abrirPackingActivity(wrapper: PackingListsWrapper){
        val intent = Intent(this,PackingListsActivity::class.java)
        intent.putExtra("IP_PC", this.ip)
        intent.putExtra("PACKINGLISTS", wrapper)
        startActivity(intent)
    }

    override fun buscadorFragmentCodigoEscaneado(msg: String) {
        when(Utils.getTipo(msg)){
            Tipo.IP -> asociar(msg)
        }
    }

    private fun asociar(msg: String) {
        var ip = msg.replace("X", "")
        this.ip = ip
        this.sincronizado = true
        val miIp = Utils.getLocalIpAddress(this)
        TCP.send("2;"+miIp+";"+this.ip+";"+";",this.ip)

        val server = ServerSocket(6663)
        val client = server.accept()
        val reader = Scanner(client.getInputStream())
        val info = reader.nextLine()
        reader.close()
        client.close()
        server.close()

        var mensaje = Utils.descifrarMensaje(info)


        var palets : List<PackingListDTO>? = ArrayList<PackingListDTO>()

        // si no me viene el JSON vacio, lo parseo y creo los palets a partir del backup
        palets= Utils.JSON2PackingListsDTO(mensaje.cuerpo)

        if(palets.isEmpty()){
            Toast.makeText(this, "No se han recibido packing lists. Utiliza la función NUEVO PACKING LIST", Toast.LENGTH_LONG).show()
            return
        }

        var paletWrapper = PackingListsWrapper()
        paletWrapper.packingListsDTO = palets

        abrirPackingActivity(paletWrapper)
    }
}
