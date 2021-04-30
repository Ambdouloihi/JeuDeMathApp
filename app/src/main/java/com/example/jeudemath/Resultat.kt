package com.example.jeudemath

import android.app.ActionBar
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Resultat : AppCompatActivity() {
    private lateinit var btnHome: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultat)

        btnHome = findViewById(R.id.btnHome)
        btnHome.setOnClickListener { goToCategoriePage() }
        fillResultat2()
    }

    private fun fillResultat2() {
        var allQuest = intent.extras as Bundle
        var containerResultat: TableLayout = findViewById(R.id.containerResultat)
        for (key in allQuest.keySet()) {
            var reponse = allQuest[key] as ArrayList<String>
            var row = TableRow(this)
            reponse.forEachIndexed { i, question ->
                var info = TextView(this)
                info.text = question
                info.textSize= 18F
                if (question == "-1") info.text = ""
                row.addView(info)
                if (1 == i)
                    if ((reponse[1] == reponse[2]))
                        info.setBackgroundColor(Color.GREEN)
                    else info.setBackgroundColor(Color.RED)
                info.gravity = Gravity.CENTER
            }
            containerResultat.addView(row)

        }
    }

    private fun goToCategoriePage() {
        val intent = Intent(this, Categorie::class.java)
        startActivity(intent)
    }
}
