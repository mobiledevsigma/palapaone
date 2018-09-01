package co.id.telkomsigma.palapaone.controller.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity

import co.id.telkomsigma.palapaone.R

class Splashscreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_1)

        Handler().postDelayed({
            val intent = Intent(this@Splashscreen, Splashscreen2::class.java)
            startActivity(intent)
        }, SPLASH_TIME_OUT.toLong())
    }

    companion object {
        private val SPLASH_TIME_OUT = 2000

    }
}
