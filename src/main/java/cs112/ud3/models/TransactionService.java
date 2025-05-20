package cs112.ud3.models;

import java.util.ArrayList;
import java.util.List;

/*
 * ------------------------------------------------------------------
 *                      TransactionService
 * ------------------------------------------------------------------
 * - transactions : ArrayList<Transaction>
 * ------------------------------------------------------------------
 * + TransactionService()
 * + addTransaction(transaction : Transaction) : void
 * + getTransactions() : List<Transaction>
 * + calculateTotal(transactions : List<Transaction>) : double
 * + calculateIncome(transactions : List<Transaction>) : double
 * + calculateExpenses(transactions : List<Transaction>) : double
 * -----------------------------------------------------------------
 */

/**
 * Service class for managing and analyzing transactions.
 * Provides methods for adding transactions, retrieving them, and calculating totals, income, and expenses.
 */
public class TransactionService {
    private final List<Transaction> transactions;

    /**
     * Default constructor for TransactionService.
     * Initializes an empty list to store transactions.
     */
    public TransactionService() {
        this.transactions = new ArrayList<>();
    }

    /**
     * Adds a transaction to the list of transactions.
     *
     * @param transaction The Transaction object to add. Must be an instance of a subclass of Transaction (e.g., Deposit, Withdrawal).
     */
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    /**
     * Retrieves the list of all transactions.
     *
     * @return An ArrayList of Transaction objects currently stored in the service.
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * Calculates the total amount of all transactions in the given list.
     * Positive amounts represent income, and negative amounts represent expenses.
     *
     * @param transactions A list of Transaction objects to calculate the total for.
     * @return The total amount as a double.
     */
    public double calculateTotal(List<Transaction> transactions) {
        double total = 0.0;
        for (Transaction t : transactions) {
            total += t.getAmount(); // Add each transaction's amount to the total
        }
        return total;
    }


    /**
     * Calculates the total income from the given list of transactions.
     * Income is defined as transactions with a positive amount.
     *
     * @param transactions A list of Transaction objects to calculate the income for.
     * @return The total income as a double.
     */
    public double calculateIncome(List<Transaction> transactions) {
        double income = 0.0;
        for (Transaction t : transactions) {
            if (t.getAmount() > 0) { // Check if the transaction amount is positive
                income += t.getAmount(); // Add the amount to the income total
            }
        }
        return income;
    }

    /**
     * Calculates the total expenses from the given list of transactions.
     * Expenses are defined as transactions with a negative amount.
     *
     * @param transactions A list of Transaction objects to calculate the expenses for.
     * @return The total expenses as a double.
     */
    public double calculateExpenses(List<Transaction> transactions) {
        double expenses = 0.0;
        for (Transaction t : transactions) {
            if (t.getAmount() < 0) { // Check if the transaction amount is negative
                expenses += t.getAmount(); // Add the amount to the expenses total
            }
        }
        return expenses;
    }
}
