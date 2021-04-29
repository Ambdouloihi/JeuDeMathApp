package com.example.jeudemath

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.jeudemath.bibliotheque.BoardPlay

class JeuDeReflexe : AppCompatActivity() {


    private lateinit var c: BoardPlay
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jeu_de_reflexe)
        c = findViewById(R.id.canvas)
    }

    protected fun randomNumber(minVal: Int, maxVal: Int): Int {
        return Math.round(minVal + Math.random() * maxVal).toInt()
    }

}