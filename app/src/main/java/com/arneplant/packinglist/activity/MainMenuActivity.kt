package com.arneplant.packinglist.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import com.arneplant.packinglist.R
import com.arneplant.packinglist.util.ApkUpdateAsyncTask
import kotlinx.android.synthetic.main.activity_main_menu.*

class MainMenuActivity : AppCompatActivity() {
    var ctx : Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        this.ctx = this

        this.title = "MENÚ PRINCIPAL"

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val app_name = packageManager.getPackageInfo(packageName, 0).packageName
        var url_apk = "http://192.168.0.104/aplicaciones/$app_name/app-debug.apk"
        var url_v = "http://192.168.0.104/aplicaciones/$app_name/version.json"

        var atualizaApp = ApkUpdateAsyncTask()
        atualizaApp.setContext(applicationContext)
        atualizaApp.execute(url_apk, url_v)

        btPacking.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btUbicar.setOnClickListener {
            val intent = Intent(this, UbicarActivity::class.java)
            startActivity(intent)
        }

        btConvertirEtiquetas.setOnClickListener {
            val intent = Intent(this, ConvertirEtiquetasActivity::class.java)
            startActivity(intent)
        }
    }
}
