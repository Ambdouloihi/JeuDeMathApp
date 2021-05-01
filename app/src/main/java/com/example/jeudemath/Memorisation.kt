package com.example.jeudemath


import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class Memorisation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memorisation)

        val arrayAdapter: ArrayAdapter<*>

        val listeTable= (0..10).map { i-> "Table de $i" }
        // access the listView from xml file
        val listView = findViewById<ListView>(R.id.tableViewScore)
        arrayAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, listeTable
                                   )
        listView.adapter = arrayAdapter

        var txtR: String

        listView.setOnItemClickListener { parent, view, position, id ->
            val nb = position
            txtR = ""
            (0..10).forEach { i ->
                txtR+="$nb x ${String.format("%2d", i)} = ${i*nb}\n"
            }
            val myPopup1=AlertDialog.Builder(this)
            myPopup1.setTitle("Table de $nb")
            myPopup1.setMessage(txtR)
            myPopup1.show()
        }
    }
}

