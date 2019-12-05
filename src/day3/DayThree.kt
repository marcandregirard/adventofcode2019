package day3

import java.io.File
import java.lang.IllegalStateException
import kotlin.math.abs

fun main(args: Array<String>) {
    val input = File("src/day3/input.txt").readLines()
    val wires = ArrayList<ArrayList<Point>>()
    input.forEach { wires.add(createWire(it)) }
    val crossingPoints =  ArrayList<Int>()
    wires[0].forEach {
        if(wires[1].contains(it)) {
            crossingPoints.add(it.distance())
        }
    }

    crossingPoints.sort()
    println(crossingPoints[0])
}

fun createWire(wireRaw: String) : ArrayList<Point> {
    val sections = wireRaw.split(",")
    val wire = ArrayList<Point>()
    var origin = Point(0, 0)
    sections.forEach {
        val direction = Direction.parseDirection(it[0])
        val numberOfPoints = it.substring(1)

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
    return wire
}

class Point(x: Int, y: Int) {
    val x = x
    val y = y

    override fun equals(other: Any?): Boolean {
        other as Point
        return other.x == this.x && other.y == this.y
    }

    fun distance() : Int {
        return abs(this.x) + abs(this.y)
    }
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