package com.example.jeudemath

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Resultat : AppCompatActivity() {
    private lateinit var btnHome: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultat)

        btnHome = findViewById(R.id.btnHome)
        btnHome.setOnClickListener { goToCategoriePage() }
        var containerResultat: TableLayout = this.findViewById(R.id.containerResultat)
        var allQuest = intent.extras as Bundle


        for (key in allQuest.keySet()) {
            var reponnse = allQuest[key] as ArrayList<Int>
            var row = TableRow(this)
            reponnse.forEachIndexed { i, question ->
                var info = TextView(this)
                info.text = "$question"
                if (question==-1) info.text = ""
                row.addView(info)
                if(1==i)
                    if ((reponnse[1]==reponnse[2]) )
                    info.setBackgroundColor(Color.GREEN)
                else info.setBackgroundColor(Color.RED)
                info.gravity= Gravity.CENTER
            }
            containerResultat.addView(row)

        }
    }

    private fun goToCategoriePage() {
        val intent = Intent(this, Categorie::class.java)
        startActivity(intent)
    }
}