package com.arneplant.packinglist.activity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.snackbar.Snackbar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import com.arneplant.packinglist.R
import com.arneplant.packinglist.fragment.PackingListFragment
import com.arneplant.packinglist.model.PackingList
import com.arneplant.packinglist.model.Respuesta
import com.arneplant.packinglist.model.dto.*
import com.arneplant.packinglist.network_interface.GestionPackingList
import com.arneplant.packinglist.ui_interface.BuscadorFragmentDelegate
import com.arneplant.packinglist.ui_interface.LecturaCajaDelegate
import com.arneplant.packinglist.util.Tipo
import com.arneplant.packinglist.util.Utils
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_packing_lists.*
import kotlinx.android.synthetic.main.fragment_packing_lists.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.ServerSocket
import java.util.*

class PackingListsActivity : AppCompatActivity(), LecturaCajaDelegate, BuscadorFragmentDelegate {

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    private var packingLists: List<PackingListDTO>? = null
    private var ip: String = ""
    private var frags: ArrayList<PackingListFragment> = ArrayList()
    private var titulos: ArrayList<String> = ArrayList()
    private var position = 0
    private var snackbar: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_packing_lists)

        packingLists = (intent.extras.getSerializable("PACKINGLISTS") as PackingListsWrapper).packingListsDTO
        ip = intent.extras.getSerializable("IP_PC") as String
        snackbar = fab as View

        inicializarTabs(container)
        tabs.setupWithViewPager(container)
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                position = tab.position
            }
        })

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Nuevo packing list creado", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            añadirPalet()
        }

    }


    fun añadirPalet(){
        val service = GestionPackingList()
        var user = Usuario()
        user.usuario = "pda"
        val call = service.crearPackingList(user)
        call.enqueue(object: Callback<PackingList>{
            override fun onFailure(call: Call<PackingList>, t: Throwable) {
                log(t.message?:"Error en la peticion")
            }

            override fun onResponse(call: Call<PackingList>, response: Response<PackingList>) {
                var packinglist = response.body()
                if(packinglist!=null){
                    var dto = Utils.PackingList2PackingListDTO(packinglist)
                    var frag = PackingListFragment.newInstance(dto)
                    frags.add(frag)
                    var titulo = Utils.getTituloPackingList(dto!!)
                    titulos.add(titulo)
                    mSectionsPagerAdapter?.notifyDataSetChanged()
                    tabs.getTabAt(frags.size-1)?.select()
                }
                else{
                    log("RESPUESTA == NIL")
                }
            }

        })
    }

    override fun buscadorFragmentCodigoEscaneado(msg: String) {
        if(frags.size==0){
            log("No hay ningun palet creado")
            return
        }
        if (Utils.getTipo(msg).equals(Tipo.Contenedor)) {
            var frag = frags[position]
            // registrar caja
            cajaLeida(msg,frag.packingList!!.id)
        }
    }

    private fun actualizarTabs(){
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager, frags, titulos)
        container.adapter = mSectionsPagerAdapter

    }

    private fun log(msg:String){
        Snackbar.make(snackbar!!, msg, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }

    override fun cajaLeida(codigoCaja: String, idPackingList:Int) {
        var position = -1
        var i = 0
        var frag: PackingListFragment? = null
        for(f: PackingListFragment in frags){
            if(f.packingList != null && f.packingList!!.id == idPackingList){
                frag = f
                position = i
                break
            }
            else{
                // fragment reciclado
            }
            i++
        }

        if(position>-1){
            insertarCaja(codigoCaja,idPackingList, frag!!)
        }

    }

    private fun comprobarPackings(codigoCaja: String, idPackingList: Int){
        for(i in 0..titulos.size-1){
            var titulo = titulos[i]
            var frag = frags[i]
            if(frag.packingList==null) continue
            var pl = frag.packingList!!

            val numCajasInicio = pl.cajas.size

            if(pl.id == idPackingList){
                var caja = CajaDTO()
                caja.codigoEtiqueta = codigoCaja
                if(pl.cajas.find { x->x.codigoEtiqueta == codigoCaja } == null){
                    pl.cajas.add(caja)
                }
                else{
                    log("Lectura repetida")
                }
            }
            pl.cajas.removeIf{x-> pl.id!=idPackingList && x.codigoEtiqueta.equals(codigoCaja)}

            val numCajasFin = pl.cajas.size

            if(numCajasFin!= numCajasInicio){
                titulos[i] = Utils.getTituloPackingList(pl)
            }
            frag?.actualizarTv(pl.cajas.size)

        }
        mSectionsPagerAdapter?.notifyDataSetChanged()
    }

    private fun insertarCaja(codigoCaja: String, idPackingList: Int, frag: PackingListFragment){
        val service = GestionPackingList()
        var etiqueta = PackinglistEtiqueta()
        etiqueta.codigoEtiqueta = codigoCaja
        etiqueta.idPackinglist = idPackingList
        val call = service.addContenedorAPackingList(etiqueta)
        call.enqueue(object: Callback<Contenedor>{
            override fun onFailure(call: Call<Contenedor>, t: Throwable) {
                log(t.message?:"Error en la peticion")
            }

            override fun onResponse(call: Call<Contenedor>, response: Response<Contenedor>) {
                if(response.isSuccessful && response.body()!=null && response.body()!!.codigoEtiqueta!=null){
                    comprobarPackings(codigoCaja,idPackingList)
                }
                else{
                    log("Error al añadir caja")
                }
                /*var resp = response.body()
                if(resp!=null){
                    // Codigo.OK
                    if(resp.codigo==0){
                        comprobarPackings(codigoCaja,idPackingList)
                    }
                   log(resp.mensaje)
                }
                else{
                    log("RESPUESTA == NIL")
                }*/
            }
        })
    }



    private fun inicializarTabs(container: ViewPager) {
        for(p: PackingListDTO in packingLists!!){
            var frag = PackingListFragment.newInstance(p)
            frags.add(frag)
            var titulo = Utils.getTituloPackingList(p!!)
            titulos.add(titulo)
        }

        actualizarTabs()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager,
                                     mFragmentListParam: ArrayList<PackingListFragment>,
                                     titulosParam: ArrayList<String>) : FragmentPagerAdapter(fm) {

        private var mFragmentList = mFragmentListParam
        private var mTitulosList  = titulosParam

        fun actualizarTitulos(titulos: ArrayList<String>){
            mTitulosList = titulos
        }

        override fun getItem(position: Int): PackingListFragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mTitulosList[position]
        }
    }

}
