import org.kohsuke.args4j.Argument
import org.kohsuke.args4j.CmdLineException
import org.kohsuke.args4j.CmdLineParser
import org.kohsuke.args4j.Option
import java.io.File
import kotlin.system.exitProcess


fun main(args: Array<String>) = Find().finder(args)

class Find {

    @Option(name = "-r", usage = "Поиск файла во всех поддиректориях.")
    private var subdirectory = false

    @Option(name = "-d", usage = "Поиск файла в текущей директории.")
    private var directory = "."

    @Argument(required = true, usage = "Имя файла.")
    private var file = ""

    private fun parser(args: Array<String>) {
        val parse = CmdLineParser(this)
        try {
            parse.parseArgument(*args)
        } catch (e: CmdLineException) {
            System.err.println(e.message)
            parse.printUsage(System.out)
            exitProcess(1)
        }
    }

    fun pathToFile(subdirectory: Boolean, directory: String, file: String): List<String> {
        val result = mutableListOf<String>()
        for (element in File(directory).listFiles()!!) {
            val newDirectory = element.toString()
            if (file in newDirectory) {
                result.add(newDirectory)
            }
            if (element.isDirectory && subdirectory)
                pathToFile(subdirectory, newDirectory, file).forEach { result.add(it) }
        }
        return result
    }

    fun finder(args: Array<String>) {
        parser(args)
        val result = mutableListOf<String>()
        val fileDir = File(directory)
        var check = false
        if (!fileDir.exists()) {
            check = true
            println("Директория не найдена.")
        }
        if (fileDir.isDirectory) for (path in pathToFile(subdirectory, directory, file)) result.add(path)
        if (!check && result.isEmpty()) println("Файл не найден.")
        for (i in result) println(i)
    }
}
