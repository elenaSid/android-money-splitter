package com.elena.domain.summary.interaction

import com.elena.domain.common.UseCase
import com.elena.domain.summary.OptimizedTransactionForUser

class GetOptimizedTransactionsForAllUsersUseCase(
        private val getSummaryForAllUsersUseCase: GetSummaryForAllUsersUseCase
) : UseCase<Unit, Set<OptimizedTransactionForUser>>() {

    override fun runUseCase(param: Unit): Set<OptimizedTransactionForUser> {
        val summaryForAllUsers = getSummaryForAllUsersUseCase.execute(Unit)

        val differences = summaryForAllUsers.map { summary ->
            TransactionOptimizationService.Difference(
                    id = summary.userId,
                    name = summary.userName,
                    difference = summary.getDifference()
            )
        }
        val transactions = TransactionOptimizationService.optimizeTransactions(differences)
        return transactions.mapTo(HashSet()) { transaction ->
            OptimizedTransactionForUser(
                    debtorUserId = transaction.debtorId!!,
                    debtorUserName = transaction.debtorName,
                    creditorUserId = transaction.creditorId!!,
                    creditorUserName = transaction.creditorName,
                    debt = transaction.debt
            )
        }
    }
}