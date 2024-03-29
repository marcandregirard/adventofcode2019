package day1

import java.io.File

fun main(args: Array<String>) {
    val fuelsNeeded: ArrayList<Int> = ArrayList()
    val fuelsNeededForFuel: ArrayList<Int> = ArrayList()
    File("src/day1/input_fuels.txt").forEachLine { fuelsNeeded.add(calculateFuelByMass(it.toInt())) }
    print("Sums of fuel for all modules : ")
    val sumOfModules = fuelsNeeded.map { it }.sum()
    println(sumOfModules)
    fuelsNeeded.forEach {
        calculateFuelForModule(it, fuelsNeededForFuel)
    }

    print("Sums of fuel for all fuels of all modules : ")
    val sumsOfFuelModules = fuelsNeededForFuel.map { it }.sum()
    println(sumsOfFuelModules)
}

private fun calculateFuelForModule(it: Int, fuelsNeededForFuel: ArrayList<Int>) {
    var sums = 0
    var remainingFuel = it
    do {
        remainingFuel = calculateFuelByMass(remainingFuel)
        sums += remainingFuel
    } while (remainingFuel != 0)
    fuelsNeededForFuel.add(sums)
}

    // divide by 3, round down, substract 2
fun calculateFuelByMass(mass: Int): Int {
    val roundedDownValue = mass / 3
    val substractedValue = roundedDownValue - 2
    if(substractedValue <= 0) {
        return 0
    }
    return substractedValue
}
