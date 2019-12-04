package day2

import java.io.File

fun main(args: Array<String>) {
    val input = File("src/day2/input.txt").readText()
    val splittedInput = input.split(",")
    val convertedInput = splittedInput.map { it.toInt() }.toMutableList()
    println("Value at position 0 " + executeProgram(convertedInput))
}

private fun executeProgram(convertedInput: MutableList<Int>): Int {
    for (i in 0..convertedInput.size step 4) {
        val op = createOpcode(convertedInput, i)
        val operation = OpcodeOptions.parseOpcode(op.operator)
        when (operation) {
            OpcodeOptions.ADDITTION -> {
                val firstValue = convertedInput.get(op.firstPosition)
                val secondValue = convertedInput.get(op.secondPosition)
                convertedInput[op.outputPosition] = firstValue + secondValue
            }
            OpcodeOptions.MULTIPLICATION -> {
                val firstValue = convertedInput.get(op.firstPosition)
                val secondValue = convertedInput.get(op.secondPosition)
                convertedInput[op.outputPosition] = firstValue * secondValue
            }
            OpcodeOptions.PROBLEM -> println("This is a problem")
            OpcodeOptions.PROGRAM_ENDED -> return convertedInput[0]
        }
    }
    return 0
}

fun createOpcode(file: List<Int>, position: Int) : Opcode {
   return Opcode(file[position], file[position + 1], file[position + 2], file[position + 3])
}

class Opcode(operator: Int, firstPosition: Int, secondPosition: Int, outPutPosition: Int) {
    val operator : Int = operator
    val firstPosition : Int = firstPosition
    val secondPosition : Int = secondPosition
    val outputPosition : Int = outPutPosition
}

enum class OpcodeOptions(val value: Int) {
    ADDITTION(1),
    MULTIPLICATION(2),
    PROGRAM_ENDED(99),
    PROBLEM(0);

    companion object {
        fun parseOpcode(code: Int) : OpcodeOptions {
            when(code) {
                ADDITTION.value -> return ADDITTION
                MULTIPLICATION.value -> return MULTIPLICATION
                PROGRAM_ENDED.value -> return PROGRAM_ENDED
                else -> return PROBLEM
            }
        }
    }
}