package com.example.jeudemath

import QuizSerie
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


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
    private lateinit var btnAnswer: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        containerAnswer = findViewById(R.id.boxReponse)
        lbquestion = findViewById(R.id.question)
        lbCalcul = findViewById(R.id.calcul)
        lbTimer = findViewById(R.id.timer)
        btnNext = findViewById(R.id.btnNext)

        btnNext.isEnabled = false
        btnNext.setOnClickListener {
            btnNext.isEnabled = false
            numQuest++
            if ((numQuest) == nbQuest) goToResultat() else
                nextQuest(numQuest)
        }
        nextQuest(numQuest)
    }

    fun startTimeCounter() {
        object : CountDownTimer(
            (COUNTERSTART * 1000).toLong(),
            1000
                               ) {
            override fun onTick(millisUntilFinished: Long) {
                lbTimer.text = "Temps : $counter s"
                counter--
            }

            override fun onFinish() {
                lbTimer.text = "Fini"
                if ((numQuest + 1) == nbQuest) {
                    btnNext.text = "Terminer"
                    lbTimer.text = ""
                }
                btnNext.isEnabled = true
                counter = COUNTERSTART

            }
        }.start()
    }

    fun nextQuest(i: Int) {
        currentQuest = allQuest?.get(i)

        lbquestion.text = "Question ${i + 1}"

        var calculQuest = currentQuest?.calcul
        lbCalcul.text = calculQuest.toString() + " = ?"

        containerAnswer.removeAllViews()
        currentQuest!!.answerList!!.forEach { answer ->
            btnAnswer = Button(this)
            btnAnswer.setTextSize(24F)
            btnAnswer.text = answer.toString()
            btnAnswer.setOnClickListener {
                if (counter!=0) currentQuest!!.userAnswer = answer

            }
            containerAnswer.addView(btnAnswer)
        }
        startTimeCounter()
    }

    private fun goToResultat() {
        val intent = Intent(this, Resultat::class.java)
        // start your next activity
        val mBundle = Bundle()
        allQuest!!.forEachIndexed { i, quest ->
        var g = ArrayList<Int>()
        g.add(i+1)
        g.add(quest.userAnswer)
        g.add(quest.calcul.answer)
        mBundle.putIntegerArrayList(i.toString(), g)
        }
        intent.putExtras(mBundle)
        startActivity(intent)
    }
}