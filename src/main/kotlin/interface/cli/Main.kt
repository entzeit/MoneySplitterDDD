package main.kotlin.`interface`.cli

import main.kotlin.application.app.SplitterApp

fun main(args: Array<String>) {
    if (args.size != 1) {
        println("Usage: java Splitter <file.txt>")
        return
    }
    SplitterApp().run(args[0])
}