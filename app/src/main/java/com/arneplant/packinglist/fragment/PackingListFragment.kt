package com.arneplant.packinglist.fragment

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.arneplant.packinglist.R
import com.arneplant.packinglist.model.PackingList
import com.arneplant.packinglist.model.dto.PackingListDTO
import com.arneplant.packinglist.ui_interface.LecturaCajaDelegate
import com.github.marlonlom.utilities.timeago.TimeAgo
import com.github.marlonlom.utilities.timeago.TimeAgoMessages
import kotlinx.android.synthetic.main.fragment_packing_lists.*
import java.util.*

class PackingListFragment : Fragment() {
    var packingList: PackingListDTO? = null
    var delegate: LecturaCajaDelegate? = null
    var v: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_packing_lists, container, false)
        packingList = arguments?.getSerializable("KEY") as PackingListDTO

        var msg = TimeAgoMessages.Builder().withLocale(Locale("es")).build()

        var fecha = TimeAgo.using(if(packingList?.fechaCreacion!=null) packingList?.fechaCreacion?.time!! else System.currentTimeMillis(),msg)

        view.findViewById<TextView>(R.id.tbCajasLeidas).setText("${packingList?.cajas?.size}")
        view.findViewById<EditText>(R.id.tbObservacion).setText(packingList?.observacion)
        view.findViewById<EditText>(R.id.tbFechaCreacion).setText(fecha)

        v = view
        return view
    }

    fun actualizarTv(i : Int){
        v!!.findViewById<TextView>(R.id.tbCajasLeidas).setText("${i}")
    }

    fun destacarEditTexts(){
        var anim = ObjectAnimator.ofInt(R.id.tbCajasLeidas, "backgroundColor", Color.WHITE, Color.GREEN, Color.WHITE)
        anim.duration = 800
        anim.setEvaluator(ArgbEvaluator())
        anim.repeatMode = ValueAnimator.REVERSE
        anim.repeatCount = 0
        anim.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                tbCajasLeidas.background = activity?.getDrawable(R.drawable.edittext_style)
            }
        })
        anim.start()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is LecturaCajaDelegate){
            delegate = context
        }
    }


    fun getTitulo(): String {
        if (packingList != null) {
            return packingList?.id.toString()+"\n"+packingList?.cajas?.size.toString()
        } else {
            return "N/A\nN/A"
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(packingList: PackingListDTO) = PackingListFragment().apply {
            arguments = Bundle().apply {
                putSerializable("KEY", packingList)

            }
        }
    }


}