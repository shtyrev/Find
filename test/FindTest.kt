import org.junit.Assert.assertEquals
import org.junit.Test

class FindTest {

    private val directory = "files"
    private val subT = true
    private val subF = false

    @Test
    fun test_1() {
        val file = "first.txt"
        val assertPath = mutableListOf("files\\first.txt")
        assertEquals(assertPath, Find().pathToFile(subF, directory, file))
    }

    @Test
    fun test_2() {
        val file = "second.txt"
        val assertPath = mutableListOf("files\\123\\second.txt")
        assertEquals(assertPath, Find().pathToFile(subT, directory, file))
    }

    @Test
    fun test_3() {
        val file = "third.txt"
        val assertPath = mutableListOf("files\\third.txt")
        assertEquals(assertPath, Find().pathToFile(subF, directory, file))
    }

    @Test
    fun test_4() {
        val file = "fourth.txt"
        val assertPath = mutableListOf("files\\321\\a\\b\\fourth.txt")
        assertEquals(assertPath, Find().pathToFile(subT, directory, file))
    }

    @Test
    fun test_5() {
        val file = "fifth.txt"
        val assertPath = emptyList<String>()
        assertEquals(assertPath, Find().pathToFile(subF, directory, file))
    }
}