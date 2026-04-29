package main.kotlin.`interface`.cli

import main.kotlin.application.app.SplitterApp

fun main(args: Array<String>) {
    if (args.size != 1) {
        println("Usage: java Splitter <file.txt>")
        return
    }
    //"Uncontrolled user input is passed to Path Traversal sink" -> okay for local CLI tool
    SplitterApp().run(args[0])
}