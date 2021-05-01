package com.example.jeudemath

import Calcul
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import com.example.jeudemath.ScoreReflexe


class BoardPlay(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val NBCALCUL = 6
    var listCalcul = ArrayList<(Calcul)>()
    var ind = 0

    private var estFini = false
    private var nbCorrectRep = 0
    val paint = Paint()
    var mainAnswer = 0
    var score = 0

    private lateinit var lblScrore: TxtLabel
    private lateinit var lblNombre: TxtLabel
    private lateinit var continueText: TxtLabel

    private lateinit var btnRestart: Button

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        dessineFond(canvas)

        dessineLabel(canvas)


        dessinerLesCalculs(canvas)
        invalidate() //rappel onDraw(cette meme fonction)
    }

    private fun dessineFond(canvas: Canvas) {
        canvas.drawColor(Color.CYAN)
    }

    private fun dessineLabel(canvas: Canvas) {
        paint.color = Color.GRAY
        paint.textSize = 50F
        lblScrore.draw("Score : $score $estFini", canvas)

        when {
            !estFini -> {
                lblNombre.txt = "$mainAnswer"
                btnRestart.isEnabled = false
            }
            estFini  -> {
                when {
                    NBCALCUL <= (nbCorrectRep + 1) -> lblNombre.txt = "GAGNÉ"
                    else                           -> lblNombre.txt = "PERDU"
                }
                paint.textSize = 40F
                continueText.draw(canvas)

                btnRestart.isEnabled = true

                estFini = false
            }
        }
        paint.textSize = 80F
        lblNombre.draw(lblNombre.txt, canvas)
    }


    fun restart() {

        nbCorrectRep = 0
        ind = 0
        score = 0

        listCalcul.clear()
        initListCalc()


        // inserer en dernier
        btnRestart.isEnabled = false
    }

    private fun dessinerLesCalculs(canvas: Canvas) {
        estFini = true // le jeu fini lorsque tous les label calcul disparaisent

        //paint.textSize = 80F
        listCalcul.forEach { text ->
            paint.color = text.color
            text.draw(canvas)
            //test de la fin du jeu
            if (text.y < height) estFini = false
        }


        corrigeCollision()




        if (estFini) {
            btnRestart.isEnabled = true

            //sauvegarde du score
            var db = DataBaseHandler(this.context)
            val allScore = db.readData()

            when {
                (allScore.size < 3) && (!allScore.contains(score))  -> db.insertData(score)
                (3 <= allScore.size) && (!allScore.contains(score)) -> {
                    val lastScore = allScore[allScore.lastIndex]
                    db.updateData(lastScore,
                                  score)
                }
            }
        }
    }


    private fun corrigeCollision() {
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


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)


        initListCalc()

        var x = (width / 2).toFloat()
        var y = 100.0F

        lblScrore = TxtLabel("Score : $score", x, y, paint)

        y = (height / 2).toFloat()

        lblNombre = TxtLabel("$mainAnswer", x, y, paint)


        continueText = TxtLabel("(cliquez sur le button recommencer)",
                                x,
                                lblNombre.y + lblNombre.twidth + 100,
                                paint)

        val canvasParent = parent.parent as ConstraintLayout
        btnRestart = canvasParent[1] as Button
        btnRestart.setOnClickListener { restart() }
        btnRestart.isEnabled = false
    }

    private fun initListCalc() {
        listCalcul = fillListCoord()
        mainAnswer = listCalcul[ind].calcAnswer
    }

    private fun fillListCoord(): ArrayList<(Calcul)> {
        var cx: Float
        var cy: Float
        var calcul: Calcul
        var list = ArrayList<Calcul>()
        (1..NBCALCUL).forEach { _ ->
            cx = randomNumber(0, width).toFloat()
            cy = randomNumber(-(height / 2), height / 2).toFloat()

            calcul = Calcul(cx, cy, paint)
            list.add(calcul)
            if ((width - calcul.twidth) < calcul.x) // gerer les coordonnes qui depasse l'ecran
                calcul.x -= calcul.twidth
        }
        return list
    }

    override fun onTouchEvent(e: MotionEvent?): Boolean {

        if (e != null)
            listCalcul.forEach { calcul ->
                when {
                    calcul.isTouch(e.x.toInt(), e.y.toInt()) -> {
                        when {
                            (calcul.calcAnswer == mainAnswer) -> {

                                if ((ind + 1) < listCalcul.size) {
                                    ind++
                                    nbCorrectRep++
                                }
                                calcul.goodClic()
                                if (score < Int.MAX_VALUE) score++
                                mainAnswer = listCalcul[ind].calcAnswer
                            }
                            else                              -> {
                                calcul.normalClic()
                                if (Int.MIN_VALUE < score) score--
                            }
                        }
                    }
                }
            }
        return super.onTouchEvent(e)
    }

    class TxtLabel(
        var txt: String,
        var x: Float,
        var y: Float,
        private var paint: Paint,
                  ) {
        var twidth = 0
        var theight = 0

        init {
            paint.color = Color.GRAY
        }

        fun draw(newTxt: String, canvas: Canvas) {
            setBounds()
            canvas.drawText(newTxt, x - (twidth / 2), y - (theight / 2), paint)
        }

        fun draw(canvas: Canvas) {
            setBounds()
            canvas.drawText(txt, x - (twidth / 2), y - (theight / 2), paint)
        }

        private fun setBounds() {
            var bounds = Rect()
            paint.getTextBounds(txt, 0, txt.length, bounds);

            twidth = bounds.width()
            theight = bounds.height()
        }
    }

    class Calcul(var x: Float, var y: Float, var paint: Paint) {
        var color: Int = Color.GRAY
        var txt: String = Calcul().toString()
        var twidth = 0
        var theight = 0
        var calcAnswer = 0
        var textSizeCalc = 100F

        init {
            var calc = Calcul()
            txt = calc.toString()
            calcAnswer = calc.answer

            paint.textSize = textSizeCalc

            setBounds(txt, paint)

        }

        private fun setBounds(
            txt: String,
            paint: Paint,
                             ) {
            var bounds = Rect()
            paint.getTextBounds(txt, 0, txt.length, bounds);
            twidth = bounds.width()
            theight = bounds.height()
        }


        fun move() {
            y += 2
        }

        fun draw(canvas: Canvas) {
            paint.textSize = textSizeCalc
            setBounds(txt, paint)

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

        fun normalClic() {
            when (color) {
                Color.RED -> color = Color.GRAY
                else      -> color = Color.RED
            }
        }

        fun goodClic() {
            when {
                color != Color.BLUE -> color = Color.BLUE
                color == Color.BLUE -> {
                    color =
                        Color.rgb(randomNumber(0, 255), randomNumber(0, 255), randomNumber(0, 255))
                    textSizeCalc += 10
                }
            }
        }

        fun randomNumber(minVal: Int, maxVal: Int): Int {
            return Math.round(minVal + Math.random() * maxVal).toInt()
        }
    }
}
