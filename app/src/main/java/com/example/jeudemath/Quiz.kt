package com.example.jeudemath

import QuizSerie
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class Quiz() : AppCompatActivity() {
    private val counterStart = 5
    private var counter = counterStart

    private var quizSerie: QuizSerie = QuizSerie()
    private var numQuest = 0
    private val nbQuest: Int = quizSerie.questionList!!.size
    private var allQuest = quizSerie.questionList
    private var currentQuest = allQuest?.get(0)

    private lateinit var btn: TextView
    private lateinit var countTime: TextView
    private lateinit var boxReponse: LinearLayout
    private lateinit var question:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        btn = findViewById(R.id.btnNext)
        countTime = findViewById(R.id.timer)
        boxReponse = this.findViewById(R.id.boxReponse) as LinearLayout
        question = this.findViewById(R.id.question) as TextView

        btn.isEnabled = false
        btn.setOnClickListener {
            btn.isEnabled = false
            numQuest++
            counter = counterStart
            if ((numQuest + 1) == nbQuest) numQuest = 0
            nextQuest(numQuest)
        }
        nextQuest(0)
    }

    fun startTimeCounter() {
        object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                countTime.text = "Temps : $counter s"
                counter--
            }

            override fun onFinish() {
                countTime.text = "Fini"
                var btn: TextView = findViewById(R.id.btnNext)
                btn.isEnabled = true
            }
        }.start()
    }

    fun nextQuest(i: Int) {
        currentQuest = allQuest?.get(i)

        var question = this.findViewById(R.id.question) as TextView
        question.text = "Question ${i + 1}"

        var leCalcul = currentQuest?.calcul
        var calcul: TextView = this.findViewById(R.id.calcul) as TextView
        calcul.text = leCalcul.toString() + " = ?"

        boxReponse.removeAllViews()
            var reponse: Button
        currentQuest!!.answerList!!.forEach { it ->
            reponse = Button(this)
            reponse.setTextSize(24F)
            reponse.text = it.toString()
            boxReponse.addView(reponse)
        }
        startTimeCounter()
    }

}