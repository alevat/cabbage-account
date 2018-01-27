package com.alevat.cabbage.account.bdd.screenplay.questions

import com.alevat.cabbage.account.service.dto.TransactionDTO
import org.hamcrest.BaseMatcher
import org.hamcrest.Description

class ContainsTransaction extends BaseMatcher<TransactionDTO> {

    private final TransactionDTO transactionToFind

    ContainsTransaction(TransactionDTO transactionToFind) {
        this.transactionToFind = transactionToFind
    }

    @Override
    boolean matches(Object item) {
        List<TransactionDTO> transactions = item
        return transactions.contains(transactionToFind)
    }

    @Override
    void describeTo(Description description) {
        description.appendText("description")
    }

}
