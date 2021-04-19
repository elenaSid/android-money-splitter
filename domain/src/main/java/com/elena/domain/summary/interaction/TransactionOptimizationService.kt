package com.elena.domain.summary.interaction

object TransactionOptimizationService {
    private const val MAX_ERROR = 0.000001

    fun optimizeTransactions(differences: List<Difference>): Set<OptimizedTransaction> {
        val nonEmptyDiffs = differences.filter { Math.abs(it.difference) > MAX_ERROR }
        if (nonEmptyDiffs.isEmpty()) return emptySet()

        val result = HashSet<OptimizedTransaction>()

        var sortedDifferences = ArrayList(nonEmptyDiffs.sortedByDescending { it.difference })
        while (sortedDifferences.isNotEmpty()) {
            val maxCreditor = sortedDifferences.first()
            val maxDebtor = sortedDifferences.last()

            val sum: Double
            if (maxCreditor.difference > -maxDebtor.difference) {
                sum = -maxDebtor.difference
                sortedDifferences.remove(maxDebtor)

                val newCreditorDifference = maxCreditor.difference - sum
                if (Math.abs(newCreditorDifference) > MAX_ERROR) {
                    maxCreditor.difference = newCreditorDifference
                } else {
                    sortedDifferences.remove(maxCreditor)
                }
            } else {
                sum = maxCreditor.difference
                sortedDifferences.remove(maxCreditor)

                val newDebtorDifference = maxDebtor.difference + sum
                if (Math.abs(newDebtorDifference) > MAX_ERROR) {
                    maxDebtor.difference = newDebtorDifference
                } else {
                    sortedDifferences.remove(maxDebtor)
                }
            }

            result.add(OptimizedTransaction(
                    debtorId = maxDebtor.id,
                    debtorName = maxDebtor.name,
                    creditorId = maxCreditor.id,
                    creditorName = maxCreditor.name,
                    debt = sum
            ))

            sortedDifferences = ArrayList(sortedDifferences.sortedByDescending { it.difference })
        }

        return result
    }

    data class Difference(
            val id: Int?, //user or family id
            val name: String, //user or family name

            var difference: Double
    )

    data class OptimizedTransaction(
            val debtorId: Int?, //user or family id
            val debtorName: String, //user or family name

            val creditorId: Int?, //user or family id
            val creditorName: String, //user or family name

            val debt: Double
    )
}