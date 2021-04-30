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


class FondJeu(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val NBCALCUL = 10
    var listCalcul = ArrayList<(Calcul)>()

    private var estFini = false
    val paint = Paint()



    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (estFini) restart()
        dessineFond(canvas)
        estFini = true // le jeu fini lorsque tous les label calcul disparaisent
        dessinerLesCalculs(canvas)
        invalidate() //rappel onDraw(cette meme fonction)
    }

    private fun dessineFond(canvas: Canvas) {
        canvas.drawColor(Color.CYAN)
    }




    fun restart() {
        listCalcul.clear()
        fillListCoord()

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
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        fillListCoord()
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
