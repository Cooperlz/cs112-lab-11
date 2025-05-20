package cs112.ud3.models;

/**
 * Represents a transaction to or from a bank account
 *
 * Class invariant:
 * - Transaction name represents the reason for the transaction
 * - Transaction name is stored as a string in order for the user to be able to specify the reason for a transaction
 * - Transaction type represents the kind of transaction deposit or withdrawal
 * - Transaction name is stored as a string in order for the user to be able to specify the kind of transaction
 * - Transaction date represents the date when the Transaction occurred
 * - Transaction date is stored as a  string in order for the user to specify which date the Transaction occurred
 * - Transaction amount represents the Dollar amount of the Transaction
 * - Transaction amount is stored as a double in order for the user to be able to specify Dollar and Cents amounts
 *
 * @author Cooper Zvegintzov
 * @version 1.0
 */


/*
 * UML CLASS DIAGRAM:
 * --------------------------------------------------------------------------------
 * Transaction
 * --------------------------------------------------------------------------------
 * - name : String
 * - date : String
 * - amount : double
 * + DEFAULT_NAME : String
 * + DEFAULT_DATE : String
 * + DEFAULT_AMOUNT : double
 * --------------------------------------------------------------------------------
 * + Transaction()
 * + Transaction(name : String, date : String, amount : double)
 * + Transaction(original : Transaction)
 * + setName(name : String) : boolean
 * + setDate(date : String) : boolean
 * + setAmount(amount : double) : boolean
 * + setAll(name : String, date : String, amount : double) : boolean
 * + getName() : String
 * + getDate() : String
 * + getType() : String
 * + getAmount() : double
 * + toString() : String
 * + equals(otherTransaction : Transaction) : boolean
 * --------------------------------------------------------------------------------
 */
public abstract class Transaction {
    /*** CONSTANT VARIABLES ***/
    public static final String DEFAULT_NAME = "Water Bill";
    public static final String DEFAULT_DATE = "01/01/2001";
    public static final double DEFAULT_AMOUNT = 100.0;


    /*** INSTANCE VARIABLES ***/

    private String name;
    private String date;
    private double amount;

    /*** CONSTRUCTOR METHODS ***/

    /**
     * Default Constructor, builds default transaction as Water Bill, 01/01/2001, 100.0
     */
    public Transaction() {
        this(Transaction.DEFAULT_NAME, Transaction.DEFAULT_DATE, Transaction.DEFAULT_AMOUNT);
    }

    /**
     * Full constructor builds object with all data for instance variables provided.
     * If arguments are not valid, program shuts down with error message
     *
     * @param name  verbal identification for transaction.
     * @param date date of transaction in MM/DD/YYYY format. If day or month is <10, include 0 prior to (ex: 7 -> 07).
     * @param amount numerical value of transaction. Value should be positive for deposit or negative for withdrawal or payment.
     *
     */
    public Transaction(String name, String date, double amount) {
        if(!this.setAll(name, date, amount)) {
            System.out.println("ERROR: bad data given to full constructor. Shutting down...");
            System.exit(0);
        }
    }

    /**
     * Copy constructor builds object with all data from Transaction object provided. No
     * changes made to original object, no shallow copying
     *
     * @param original Transaction object to use for copying
     *
     */
    public Transaction(Transaction original) {
        if(original != null) {
            this.setAll(original.name, original.date, original.amount);
        } else {
            System.out.println("ERROR: bad data given to copy constructor. Shutting down...");
            System.exit(0);
        }
    }

    /*** MUTATOR METHODS (SETTERS) ***/
    /**
     * Sets name for transaction only if valid, otherwise will not change instance variable. Returns boolean representing whether error occurred (false) or operation completed successfully (true).
     *
     * @param name verbal identification for transaction
     *
     * @return true if transaction name is not null
     */
    public boolean setName(String name) {
        boolean isValid = (!name.isEmpty());
        if (isValid) {
            this.name = name;
        }
        return isValid;
    }

    /**
     * Sets date for transaction only if valid, otherwise will not change instance variable. Returns boolean representing whether error occurred (false) or operation completed successfully (true).
     *
     * @param date date of transaction in MM/DD/YYYY format. Date string must be either 9 or 10 length (e.g. 10/5/2001 or 10/10/2001)
     *
     * @return true if transaction date length is not more or less than 10.
     */
    public boolean setDate(String date) {
        boolean isValid = (date.length() == 10 || date.length() == 9);
        if (isValid) {
            this.date = date;
        }
        return isValid;
    }
    /**
     * Sets amount for transaction only if valid, otherwise will not change instance variable. Returns boolean representing whether error occurred (false) or operation completed successfully (true).
     *
     * @param amount numerical value of transaction. Value should be positive for deposit or negative for withdrawal or payment.
     *
     * @return true if transaction amount value is not 0.
     */
    public boolean setAmount(double amount) {
        boolean isValid = (!(amount == 0 ));
        if (isValid) {
            this.amount = amount;
        }
        return isValid;
    }
    /**
     * Sets suit and value for card only if valid, returns boolean representing
     * whether error occurred (false) or operation completed successfully (true)
     *
     * @param name  verbal identification for transaction.
     * @param date date of transaction in MM/DD/YYYY format. If day or month is <10, include 0 prior to (ex: 7 -> 07).
     * @param amount numerical value of transaction. Value should be positive for deposit or negative for withdrawal or payment.
     *
     * @return true if transaction name, date, and amount are valid, false otherwise
     */
    public boolean setAll(String name, String date, double amount) {
        if (!this.setName(name)) {
            return false;
        } else if (!this.setDate(date)) {
            return false;
        } else return this.setAmount(amount);
    }


    /*** ACCESSOR METHODS (GETTERS) ***/
    /**
     * Access String representing name of Transaction
     *
     * @return name as String for verbal identification of Transaction
     */
    public String getName() {
        return this.name;
    }
    /**
     * Access String representing date of Transaction
     *
     * @return date as String for date of Transaction
     */
    public String getDate() {
        return this.date;
    }
    /**
     * Access float representing amount of Transaction
     *
     * @return amount as float for amount of Transaction
     */
    public double getAmount() {
        return this.amount;
    }

    /*** ABSTRACT METHODS ***/

    /**
     * Access String representing type of Transaction
     *
     * @return type as String for verbal identification of type
     */
    public abstract String getType();

    /*** OTHER REQUIRED METHODS ***/
    /**
     * Checking for equality of Transaction objects, all instance variables exactly equal
     * to each other (case-sensitive). Argument object not changed
     *
     * @param otherTransaction Transaction object to compare for equality
     *
     * @return boolean representing equality between both objects, all data is
     *         exactly equal to each other
     */
    public boolean equals(Transaction otherTransaction) {
        if(otherTransaction == null) {
            return false;
        } else {
            return this.name.equals(otherTransaction.name) && this.getType().equals(otherTransaction.getType()) && this.date.equals(otherTransaction.date) && this.amount == otherTransaction.amount;
        }
    }

    /**
     * String of all instance variables, no newline character at the end of String.
     *
     * @return String containing (print) name, type, date, and amount. Separated by space and comma.
     */
    public String toString(){
        return "Transaction name: \"" + name + "\", Transaction type: " + this.getType() + ", Transaction date: " + date + ", Transaction amount:  $" + amount;
    }
    /*** EXTRA METHODS ***/

}