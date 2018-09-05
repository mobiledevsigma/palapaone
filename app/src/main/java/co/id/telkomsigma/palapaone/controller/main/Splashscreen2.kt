package co.id.telkomsigma.palapaone.controller.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity

import co.id.telkomsigma.palapaone.R
import co.id.telkomsigma.palapaone.util.SessionManager

class Splashscreen2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_2)
        session = SessionManager(applicationContext)

        Handler().postDelayed({
            if (session!!.isLanguage()) {
                if (session!!.isLogin()) {
                    val intent = Intent(this@Splashscreen2, MenuActivity::class.java)
                    startActivity(intent)
                } else {
                    val intent = Intent(this@Splashscreen2, BeforeLoginActivity::class.java)
                    startActivity(intent)
                }
            } else {
                val intent = Intent(this@Splashscreen2, BilingualActivity::class.java)
                startActivity(intent)
            }
        }, SPLASH_TIME_OUT.toLong())
    }

    companion object {
        private val SPLASH_TIME_OUT = 2000
        private var session: SessionManager? = null
    }
}
