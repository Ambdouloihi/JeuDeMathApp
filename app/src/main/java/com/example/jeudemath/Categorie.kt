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
        btnCalcul.setOnClickListener { goToQuestion() }

    }

    private  fun goToQuestion(){
        val intent = Intent(this, Quiz::class.java)
        // start your next activity
        startActivity(intent)
    }
}
