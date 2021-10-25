package model;

import java.util.Collections;
import java.util.List;

public class Wallet {
    private int id;
    private List<Transaction> transactions;

    public boolean addTransaction(Transaction transaction) {
         return transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }
}
