package main.kotlin.presentation.cli

import java.io.File

class BillFileReader {
    fun read(fileName: String): Result<List<String>> =
        runCatching {
            File(fileName).readLines()
        }
}