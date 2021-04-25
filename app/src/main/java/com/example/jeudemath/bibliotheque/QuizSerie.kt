import java.util.*

class QuizSerie {
    private var num = 0
    private var idQuiz: Int
    var questionList: ArrayList<Question>? = null
        private set

    private fun setQuestionList() {
        val list = ArrayList<Question>()
        for (i in 0..5) {
            list.add(
                Question(Calcul())
            )
        }
        questionList = list
    }

    /**
     * affiche "Quiz" suivi du numero du Quiz
     */
    override fun toString(): String {
        return "Quiz$idQuiz"
    }

    init {
        num++
        idQuiz = num
        setQuestionList()
    }
}