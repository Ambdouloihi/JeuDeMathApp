import android.os.Parcelable
import java.util.*

class Question(calcul: Calcul) {
    val calcul: Calcul
    var userAnswer:Int = -1
    lateinit var answerList: ArrayList<Int>
        private set
    private val rand = Random()

    private fun setAnswerList() {
        val list = ArrayList<Int>()
        val max = if (calcul.answer!=0) calcul.answer + calcul.answer / 2 else 10
        var piege: Int

        for (i in 1..2) {piege=getRandInt(max); list.add(piege)} // 2 reponses pieges

        piege = if (calcul.answer!=0) 2*calcul.answer else getRandInt(10)
        list.add(getRandInt(list.size-1),piege) // reponse piege
        list.add(getRandInt(list.size-1), calcul.answer) // la reponse

        answerList = list
    }

    private fun getRandInt(max: Int): Int {
        return rand.nextInt(max + 1)+1
    }

    /**
     * affiche "question" suivi de l'id de la question
     */
    override fun toString(): String {
        return "Question"
    }

    init {
        this.calcul = calcul
        setAnswerList()
    }
}