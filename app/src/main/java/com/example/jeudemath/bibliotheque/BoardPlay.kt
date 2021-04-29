package com.example.jeudemath.bibliotheque

import Calcul
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get


class BoardPlay(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val NBCALCUL = 10
    var listCalcul = ArrayList<(Calcul)>()
    var ind = 0

    private var estFini = false
    private var nbCorrectRep = 0
    val paint = Paint()
    var mainAnswer = 0
    var score = 0

    private lateinit var lblScrore: TxtLabel
    private lateinit var lblNombre: TxtLabel

    private lateinit var btnRestart: Button

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        dessineFond(canvas)

        dessineLabel(canvas)

        estFini = true
        dessinerLesCalculs(canvas)
        invalidate() //rappel onDraw(cette meme fonction)
    }

    private fun dessineFond(canvas: Canvas) {
        canvas.drawColor(Color.CYAN)
    }

    private fun dessineLabel(canvas: Canvas) {
        paint.color = Color.GRAY
        paint.textSize = 50F
        lblScrore.draw("Score : $score", canvas)
        paint.textSize = 80F

        when {
            !estFini -> lblNombre.txt = "$mainAnswer"
            estFini  -> {
                dessineEtatDeFin()
            }
        }
        lblNombre.draw(lblNombre.txt, canvas)
    }

    private fun dessineEtatDeFin() {
        when (NBCALCUL) {
            nbCorrectRep -> lblNombre.txt = "GAGNÉ!!!"
            else         -> lblNombre.txt = "PERDU"
        }
        //enable button
        btnRestart.isEnabled = true
    }
    fun restart(){
        btnRestart.isEnabled = false
       listCalcul.clear()
        fillListCoord()
        ind=0
        score=0
    }
    private fun dessinerLesCalculs(canvas: Canvas) {
        paint.textSize = 80F
        listCalcul.forEach { text ->
            paint.color = text.color
            text.draw(canvas, paint)
            //test de la fin du jeu
            if (text.y < height) estFini = false
        }
        corrigeCollision()
    }

    fun corrigeCollision() {
        var texti: Calcul
        var textj: Calcul
        //correct collision
        (listCalcul.lastIndex downTo 0).forEach { i ->
            texti = listCalcul[i]
            (i - 1 downTo 0).forEach { j ->
                textj = listCalcul[j]
                if (texti.isCollide(textj)) {
                    texti.y -= 1
                }
            }
            //safe remove text off screen by goig backword
            if (height < texti.y) {
                texti.y = (height + texti.theight).toFloat()
            }
        }
    }

    private fun randomNumber(minVal: Int, maxVal: Int): Int {
        return Math.round(minVal + Math.random() * maxVal).toInt()
    }


    fun fillListCoord() {
        var cx: Int
        var cy: Int
        var txt: Calcul

        (1..NBCALCUL).forEach { i ->
            cx = randomNumber(0, width)
            cy = randomNumber(-(height / 2), height / 2)
            txt = Calcul(cx.toFloat(), cy.toFloat(), paint)
            listCalcul.add(txt)
            if ((width - txt.twidth) < txt.x)
                txt.x -= txt.twidth
        }
        mainAnswer = listCalcul[ind].calcAnswer
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        fillListCoord()
        paint.textSize = 50F
        lblScrore = TxtLabel(
            "Score : $score",
            (width / 2).toFloat(), 100.0F, paint
                            )
        paint.textSize = 80F
        lblNombre = TxtLabel(
            "$mainAnswer",
            (width / 2).toFloat(), (height / 2).toFloat(), paint
                            )

        //desable button
        var cl = parent.parent as ConstraintLayout
        btnRestart = cl[1] as Button
        btnRestart.isEnabled = false

        btnRestart.setOnClickListener { restart() }
    }

    override fun onTouchEvent(e: MotionEvent?): Boolean {
        for (text in listCalcul) if (e != null) when {
            text.isTouch(e.x.toInt(), e.y.toInt()) -> {
                //clic calcul de base
                when (text.color) {
                    Color.RED -> text.color = Color.GRAY
                    else      -> text.color = Color.RED
                }

                //clic bonne reponse
                when {
                    (text.calcAnswer == mainAnswer) &&
                    (text.color != Color.BLUE) -> {
                        text.color = Color.BLUE
                        when {
                            (ind + 1) < listCalcul.size -> {
                                ind++;nbCorrectRep++
                            }
                        }
                        nbCorrectRep++
                        mainAnswer = listCalcul[ind].calcAnswer
                        when {
                            score < Int.MAX_VALUE -> score++
                        }
                    }
                    Int.MIN_VALUE < score      -> score--
                }

            }
        }
        return super.onTouchEvent(e)
    }

    class TxtLabel(
        var txt: String,
        private var x: Float, private var y: Float,
        private var paint: Paint
                  ) {
        private var twidth = 0
        private var theight = 0

        init {
            paint.color = Color.GRAY
        }

        fun draw(newTxt: String, canvas: Canvas) {
            setBounds()
            canvas.drawText(newTxt, x - (twidth / 2), y - (theight / 2), paint)
        }

        private fun setBounds() {
            var bounds = Rect()
            paint.getTextBounds(txt, 0, txt.length, bounds);

            twidth = bounds.width()
            theight = bounds.height()
        }
    }

    class Calcul(var x: Float, var y: Float, paint: Paint) {
        var color: Int = Color.GRAY
        var txt: String = Calcul().toString()
        var twidth = 0
        var theight = 0
        var calcAnswer = 0

        init {
            var calc = Calcul()
            txt = calc.toString()
            calcAnswer = calc.answer

            paint.textSize = 100F
            setBounds(txt, paint)
        }

        private fun setBounds(
            txt: String,
            paint: Paint
                             ) {
            var bounds = Rect()
            paint.getTextBounds(txt, 0, txt.length, bounds);
            twidth = bounds.width()
            theight = bounds.height()
        }


        fun move() {
            y += 1
        }

        fun draw(canvas: Canvas, paint: Paint) {
            paint.color = color
            canvas.drawText(txt + "", x, y, paint)
            move()
        }

        fun isTouch(x1: Int, y1: Int): Boolean {
            return (x < x1) && (x1 < (x + twidth)) &&
                   ((y - theight) < y1) && (y1 < y)
        }

        fun isCollide(txt: Calcul): Boolean {
            return (txt.x < (x + twidth)) && (x < (txt.x + txt.twidth)) &&
                   ((txt.y - txt.theight) < y) && ((y - theight) < txt.y)
        }

    }

}
