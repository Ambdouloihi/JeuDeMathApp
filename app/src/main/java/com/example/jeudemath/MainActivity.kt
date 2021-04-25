package com.example.jeudemath

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.jeudemath.Categorie as ComExampleJeudemathCategorie

class MainActivity : AppCompatActivity() {
    private val ACTIVITE_A_DEMARRER = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        title = "Callcul Mental"

        val btnJouer: Button = findViewById(R.id.btnJouer)
        btnJouer.setOnClickListener { goToCategoriePage() }
    }

    private fun goToCategoriePage() {
        val intent = Intent(this, ComExampleJeudemathCategorie::class.java)
        startActivity(intent)
    }


}
