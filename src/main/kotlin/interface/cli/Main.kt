package main.kotlin.`interface`.cli

import main.kotlin.domain.model.Group

fun main(args: Array<String>) {
    if (args.size != 1) {
        println("Usage: java Splitter <file.txt>")
        return
    }
    Group(args)
}