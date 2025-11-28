package com.schwarckstudio.lionfitness.logic

object PlateCalculator {
    private val STANDARD_PLATES = listOf(25.0, 20.0, 15.0, 10.0, 5.0, 2.5, 1.25)
    private const val BAR_WEIGHT = 20.0

    fun calculatePlates(targetWeight: Double): List<Double> {
        if (targetWeight <= BAR_WEIGHT) return emptyList()

        var remainingWeight = (targetWeight - BAR_WEIGHT) / 2
        val plates = mutableListOf<Double>()

        for (plate in STANDARD_PLATES) {
            while (remainingWeight >= plate) {
                plates.add(plate)
                remainingWeight -= plate
            }
        }

        return plates
    }
}
