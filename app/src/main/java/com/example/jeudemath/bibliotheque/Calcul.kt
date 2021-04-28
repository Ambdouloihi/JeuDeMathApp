import java.util.*

class Calcul() {
    private var numA: Int
    private var numB: Int
    val answer: Int
    override fun toString(): String {
        return "$numA x $numB"
    }

    fun isAnswer(rep: Int): Boolean {
        return rep == answer
    }
    init {
        val rand = Random()
        val max = 10
        numA = rand.nextInt(max + 1)
        numB = rand.nextInt(max + 1)
        answer = numA * numB
    }
}