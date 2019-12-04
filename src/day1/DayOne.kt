package day1

import java.io.File

fun main(args: Array<String>) {
    val fuelsNeeded: ArrayList<Int> = ArrayList()
    val fuelsNeededForFuel: ArrayList<Int> = ArrayList()
    File("src/day1/input_fuels.txt").forEachLine { fuelsNeeded.add(DayOne().calculateFuelByMass(it.toInt())) }
    print("Sums of fuel for all modules : ")
    val sumOfModules = fuelsNeeded.map { it }.sum()
    println(sumOfModules)
    fuelsNeeded.forEach {
        calculateFuelForModule(it, fuelsNeededForFuel)
    }

    print("Sums of fuel for all fuels of all modules : ")
    val sumsOfFuelModules = fuelsNeededForFuel.map { it }.sum()
    println(sumsOfFuelModules)

    println("Sums of everything : " + (sumOfModules + sumsOfFuelModules))
    val fuelsNeededForFuelCopy: ArrayList<Int> = ArrayList()
    calculateFuelForModuleWithPrint(98541, fuelsNeededForFuelCopy)
}

private fun calculateFuelForModule(it: Int, fuelsNeededForFuel: ArrayList<Int>) {
    var sums: Int = 0
    var remainingFuel = it
    do {
        remainingFuel = DayOne().calculateFuelByMass(remainingFuel)
        sums += remainingFuel
    } while (remainingFuel != 0)
    fuelsNeededForFuel.add(sums)
}

private fun calculateFuelForModuleWithPrint(it: Int, fuelsNeededForFuel: ArrayList<Int>) {
    var sums: Int = 0
    var remainingFuel = it
    println("Initial value of Fuel : " + it)
    do {
        remainingFuel = DayOne().calculateFuelByMass(remainingFuel)
        sums += remainingFuel
        println("Fuel for this iteration : " + remainingFuel)
        println("Sums so far : " + sums)
    } while (remainingFuel != 0)
    fuelsNeededForFuel.add(sums)
}

class DayOne {
    // divide by 3, round down, substract 2
    fun calculateFuelByMass(mass: Int): Int {
        if ( mass <= 0 ) {
            return 0;
        }
        val roundedDownValue = mass / 3
        val substractedValue = roundedDownValue - 2
        if(substractedValue <= 0) {
            return 0
        }
        return substractedValue
    }
}