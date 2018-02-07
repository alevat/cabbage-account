package com.alevat.cabbage.account.bdd.screenplay.questions

import com.alevat.cabbage.account.service.dto.TransactionDto
import org.hamcrest.BaseMatcher
import org.hamcrest.Description

class ContainsTransaction extends BaseMatcher<TransactionDto> {

    private final TransactionDto transactionToFind

    ContainsTransaction(TransactionDto transactionToFind) {
        this.transactionToFind = transactionToFind
    }

    @Override
    boolean matches(Object item) {
        List<TransactionDto> transactions = item
        return transactions.contains(transactionToFind)
    }

    @Override
    void describeTo(Description description) {
        description.appendText("description")
    }

}
