package com.example.jeudemath

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
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


        val listView: ListView = findViewById(R.id.containerResultat)

        val listeTable = ArrayList<ResultUser>()
        listeTable.add(ResultUser("Question", "Répondu", "Réponse"))


        var allQuest = intent.extras as Bundle
        allQuest.keySet().forEach { key ->
            var reponse = allQuest[key] as ArrayList<String>
            listeTable.add(ResultUser(reponse[0], reponse[1], reponse[2]))
        }


        class ArrayAdaptaterResult : ArrayAdapter<ResultUser>(this,
                                                              R.layout.my_list_item_2_resultat,
                                                              R.id.questResult,
                                                              listeTable) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                var view: View = super.getView(position, convertView, parent)

                val qest: TextView = view.findViewById(R.id.questResult)
                val repUser: TextView = view.findViewById(R.id.repUserResult)
                val rep: TextView = view.findViewById(R.id.repResult)


                qest.setText(listeTable.get(position).question)
                repUser.setText(listeTable.get(position).repondu)
                rep.setText(listeTable.get(position).reponse)

                if (!repUser.text.equals("Repondu"))
                    if (repUser.text == rep.text) repUser.setBackgroundColor(Color.GREEN)
                    else repUser.setBackgroundColor(Color.RED)

                return view
            }
        }

        val arrayAdaptaterScore = ArrayAdaptaterResult()
        listView.adapter = arrayAdaptaterScore


    }

    private fun goToCategoriePage() {
        val intent = Intent(this, Categorie::class.java)
        startActivity(intent)
    }

    class ResultUser(var question: String, var repondu: String, var reponse: String)
}
