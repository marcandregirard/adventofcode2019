package day3

import java.io.File
import java.lang.IllegalStateException

fun main(args: Array<String>) {
    val input = File("src/day3/input.txt").readLines()
    input.forEach { createWire(it) }
}

fun createWire(wireRaw: String) {
    val sections = wireRaw.split(",")
    val wire = ArrayList<Point>()
    var origin = Point(0, 0)
    sections.forEach {
        val direction = Direction.parseDirection(it[0])
        val numberOfPoints = it.substring(1)
        println("$direction $numberOfPoints")

        for (i in 1..numberOfPoints.toInt()) {
            when (direction) {
                Direction.RIGHT -> {
                    origin = Point(origin.x + 1, origin.y)
                    wire.add(origin)
                }
                Direction.UP -> {
                    origin = Point(origin.x, origin.y + 1)
                    wire.add(origin)
                }
                Direction.DOWN -> {
                    origin = Point(origin.x, origin.y - 1)
                    wire.add(origin)
                }
                Direction.LEFT -> {
                    origin = Point(origin.x - 1, origin.y)
                    wire.add(origin)
                }
            }
        }
    }
}

class Point(x: Int, y: Int) {
    val x = x
    val y = y
}

enum class Direction(val value : Char) {
    UP('U'),
    DOWN('D'),
    LEFT('L'),
    RIGHT('R');

    companion object {
        fun parseDirection(direction: Char) : Direction {
            when(direction) {
                UP.value -> return UP
                DOWN.value -> return DOWN
                LEFT.value -> return LEFT
                RIGHT.value -> return RIGHT
                else -> throw IllegalStateException("You must have one of the four direction as a value")
            }
        }
    }
}