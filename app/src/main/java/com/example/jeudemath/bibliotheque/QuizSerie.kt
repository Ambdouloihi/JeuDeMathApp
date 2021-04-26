import java.io.Serializable
import java.util.*

class QuizSerie() {
    var questionList: ArrayList<Question>? = null
        private set

    private fun setQuestionList() {
        val list = ArrayList<Question>()
        for (i in 1..5) {
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
        return "Quiz"
    }

    init {
        setQuestionList()
    }
}