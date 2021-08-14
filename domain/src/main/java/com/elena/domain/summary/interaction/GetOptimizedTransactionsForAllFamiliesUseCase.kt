package com.elena.domain.summary.interaction

import com.elena.domain.common.CoroutineUseCase
import com.elena.domain.summary.OptimizedTransactionForFamily

/**
 * @author elena
 */
class GetOptimizedTransactionsForAllFamiliesUseCase(
        private val getSummaryForAllFamiliesUseCase: GetSummaryForAllFamiliesUseCase
) : CoroutineUseCase<Unit, Set<OptimizedTransactionForFamily>>() {

    override suspend fun runUseCase(param: Unit): Set<OptimizedTransactionForFamily> {
        val summaryForAllFamilies = getSummaryForAllFamiliesUseCase.execute(Unit)

        val differences = summaryForAllFamilies.map { summary ->
            TransactionOptimizationService.Difference(
                    id = summary.familyId,
                    name = summary.familyName,
                    difference = summary.getDifference()
            )
        }
        val transactions = TransactionOptimizationService.optimizeTransactions(differences)
        return transactions.mapTo(HashSet()) { transaction ->
            OptimizedTransactionForFamily(
                    debtorFamilyId = transaction.debtorId,
                    debtorFamilyName = transaction.debtorName,
                    creditorFamilyId = transaction.creditorId,
                    creditorFamilyName = transaction.creditorName,
                    debt = transaction.debt
            )
        }
    }
}