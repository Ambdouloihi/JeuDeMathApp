package com.example.jeudemath

import QuizSerie
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children


class Quiz() : AppCompatActivity() {
    private val COUNTERSTART = 5
    private var counter = COUNTERSTART

    private var numQuest = 0
    private var quizSerie: QuizSerie = QuizSerie()
    private var allQuest = quizSerie.questionList
    private val nbQuest: Int = allQuest!!.size
    private var currentQuest = allQuest?.get(numQuest)

    private lateinit var containerAnswer: LinearLayout
    private lateinit var lbTimer: TextView
    private lateinit var lbquestion: TextView
    private lateinit var lbCalcul: TextView
    private lateinit var btnNext: Button
    private lateinit var btnTerminer: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        containerAnswer = findViewById(R.id.boxReponse)
        lbquestion = findViewById(R.id.question)
        lbCalcul = findViewById(R.id.calcul)
        lbTimer = findViewById(R.id.timer)
        btnNext = findViewById(R.id.btnNext)
        btnTerminer = findViewById(R.id.btnTerminer)

        btnTerminer.isEnabled = false
        btnNext.isEnabled = false

        btnNext.setOnClickListener {
            btnNext.isEnabled = false
            numQuest++
            nextQuest(numQuest)
        }
        btnTerminer.setOnClickListener { goToResultat2() }

        containerAnswer.children.forEachIndexed { i, view ->
            val btnAnswer=view as Button

            btnAnswer.text= currentQuest!!.answerList[i].toString()

            btnAnswer.setOnClickListener {
                containerAnswer.children.forEachIndexed { i, view ->
                    val btnAnswer=view as Button
                    btnAnswer.setTextColor(Color.BLACK)
                }

                when (btnAnswer.currentTextColor) {
                    Color.BLACK -> btnAnswer.setTextColor(Color.BLUE)
                }
                if (counter != 0) currentQuest!!.userAnswer = (btnAnswer.text as String).toInt()
            }
        }



        nextQuest(numQuest)
    }

    private fun startTimeCounter() {
        object : CountDownTimer(
            (COUNTERSTART * 1000).toLong(),
            1000
                               ) {
            override fun onTick(millisUntilFinished: Long) {
                lbTimer.text = "$counter sec"
                counter--
            }

            override fun onFinish() {
                if ((numQuest + 1) == nbQuest) {
                    btnTerminer.isEnabled = true
                    lbTimer.text = ""
                } else {
                    lbTimer.text = "$counter sec"
                    btnNext.isEnabled = true
                    counter = COUNTERSTART
                }

            }
        }.start()
    }

    private fun nextQuest(i: Int) {
        currentQuest = allQuest?.get(i)

        lbquestion.text = "Question ${i + 1}"

        var calculQuest = currentQuest?.calcul
        lbCalcul.text = calculQuest.toString() + " = ?"

        //containerAnswer.removeAllViews()
        containerAnswer.children.forEachIndexed { i, btn ->
            val btnAnswer=btn as Button
            btnAnswer.setTextColor(Color.BLACK)
            btnAnswer.text= currentQuest!!.answerList[i].toString()
        }

        startTimeCounter()
    }

    private fun goToResultat2() {
        val intent = Intent(this, Resultat::class.java)
        // start your next activity
        val mBundle = Bundle()
        allQuest!!.forEachIndexed { i, quest ->
            var data = ArrayList<String>()

            data.add(quest.calcul.toString())
            data.add("" + quest.userAnswer)
            data.add("" + quest.calcul.answer)

            mBundle.putStringArrayList(i.toString(), data)
        }
        intent.putExtras(mBundle)
        startActivity(intent)
    }
}