package com.example.jeudemath

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Categorie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorie)

        val btnCalcul: Button = findViewById(R.id.btnCalcul)
        val btnReflexe: Button = findViewById(R.id.btnReflexe)

        btnCalcul.setOnClickListener { goToActivity(Quiz::class.java) }
        btnReflexe.setOnClickListener { goToActivity(JeuDeReflexe::class.java) }

    }

    private fun goToActivity(cls:Class<*>) {
        val intent = Intent(this, cls)
        startActivity(intent)
    }

}