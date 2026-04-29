package main.kotlin.`interface`.cli

import java.io.File

class BillFileReader {
    fun read(fileName: String): Result<List<String>> =
        runCatching {
            File(fileName).readLines()
        }
}