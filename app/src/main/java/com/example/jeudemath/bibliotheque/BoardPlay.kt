package com.example.jeudemath.bibliotheque

import Calcul
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


class BoardPlay(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    var listText = ArrayList<(leText)>()
     val paint = Paint()
    private var txtwidth=0
    var mainAnswer=0
    var mainI=0
    var score=0


    override fun onDraw(canvas: Canvas) {
        canvas.drawColor(Color.CYAN)
        paint.color=Color.GRAY
        canvas.drawText("$mainAnswer", (width / 2).toFloat(), (height / 2).toFloat(), paint)
        canvas.drawText("Score : $score", (width).toFloat()-500, 100.0F, paint)
        updateDrawText(canvas)
        collision()
        super.onDraw(canvas)
        invalidate()
    }


    protected fun randomNumber(minVal: Int, maxVal: Int): Int {
        return Math.round(minVal + Math.random() * maxVal).toInt()
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        fillListCoord()
    }


    init {
        paint.textSize = 100F
        val txt="1 x 1"
        var bound = Rect()
        paint.getTextBounds(txt, 0, txt.length, bound);
        txtwidth = bound.width()
    }

    fun fillListCoord() {
        var cx: Int
        var cy: Int
        var txt: leText
        for (i in 1..10) {
            cx = randomNumber(0, width)
            cy = randomNumber(-(height/2), height / 2)
            txt = leText(cx, cy, paint)
            listText.add(txt)
            if ((width - txt.twidth) < txt.x)
                txt.x -= txt.twidth

        }
        mainAnswer=listText[mainI].calcAnswer
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
       for (text in listText) {
            if (event != null) {
                if (text.isTouch(event.x.toInt(), event.y.toInt())) {
                    if (text.color == Color.RED)
                        text.color = Color.GRAY
                    else
                        text.color = Color.RED
                    if (text.calcAnswer==mainAnswer)  {
                        text.color = Color.BLUE
                        if ((mainI+1)<listText.size){ mainI++}
                        mainAnswer=listText[mainI].calcAnswer
                        score++
                    } else score--
                }

            }
        }
        return super.onTouchEvent(event)
    }

    private fun updateDrawText(canvas: Canvas) {
        for (text in listText) {
            paint.color = text.color
            text.draw(canvas, paint)
        }
    }

    fun collision(){
        for (i in listText.lastIndex downTo 0) {
          for (j in i - 1 downTo 0){
              if(listText[i].isCollide(listText[j])){
                  listText[i].y-=(listText[i].theight)
              }
              //remove
              if (height  < listText[i].y) listText.removeAt(i)
          }

        }
    }
    class leText(var x: Int, var y: Int, paint: Paint) {
        var color: Int = Color.GRAY
        var txt: String = Calcul().toString()
        var twidth = 0
        var theight = 0
        var bound = Rect()
        var calcAnswer=0
        init {
            paint.getTextBounds(txt, 0, txt.length, bound);
            twidth = bound.width()
            theight = bound.height()

            var calc=Calcul()
            txt= calc.toString()
            calcAnswer=calc.answer
        }

        fun move() {
            y += 1
        }

        fun draw(canvas: Canvas, paint: Paint) {
            paint.color = color
            canvas.drawText(txt+"", x.toFloat(), y.toFloat(), paint)
            move()
        }

        fun isTouch(x1: Int, y1: Int): Boolean {
            return (x < x1) && (x1 < (x + twidth)) &&
                   ((y - theight) < y1) && (y1 < y)
        }
        fun isCollide(txt:leText): Boolean {
            return (txt.x < (x + twidth)) && (x < (txt.x+txt.twidth)) &&
                   ((txt.y - txt.theight) < y) && ((y - theight) < txt.y)
        }

    }

}
