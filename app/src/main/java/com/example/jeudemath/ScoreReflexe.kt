package com.example.jeudemath

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ScoreReflexe : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_reflexe)


    var db = DataBaseHandler(this)

        val data: MutableList<Int>? = db.readData()
        val listeTable=ArrayList<Score> ()

        listeTable.add(Score("Rang", "Score"))
        data!!.forEachIndexed { i, score ->
            listeTable.add(Score("${i + 1}", "$score"))
        }


        val listView = findViewById<ListView>(R.id.tableViewScore)

       class ArrayAdaptaterScore: ArrayAdapter<Score>(this, R.layout.my_list_item_2score, R.id.rang, listeTable){
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                var view:View= super.getView(position, convertView, parent)
                val text1:TextView = view.findViewById(R.id.rang)
                val text2:TextView = view.findViewById(R.id.score)


                text1.setText(listeTable.get(position).rang)
                text2.setText( listeTable.get(position).score )
                return view
            }
        }

        val arrayAdaptaterScore=ArrayAdaptaterScore()
        listView.adapter = arrayAdaptaterScore



    }
    class Score(var rang: String, var score: String){

    }
}
