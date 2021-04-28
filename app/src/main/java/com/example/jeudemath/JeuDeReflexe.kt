package com.example.jeudemath

import Calcul
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
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
    fun restart(view: View){
        c.listText.clear()
        c.fillListCoord()
        c.mainI=0
        c.score=0
    }
}