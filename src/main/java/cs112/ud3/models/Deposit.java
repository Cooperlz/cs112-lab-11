package cs112.ud3.models;

/*
 * -------------------------------------------
 *                  Deposit
 * -------------------------------------------
 *  + type : String
 *  + DEFAULT_NAME : String
 * -------------------------------------------
 *  + Deposit()
 *  + Deposit(name : String, date : String,
 *            amount : double)
 *  + Deposit(original : Deposit)
 *  + getType() : String
 * -------------------------------------------
 */

public class Deposit extends Transaction{
    public static final String type = "Deposit";
    public static final String DEFAULT_NAME = "Paycheck";

    /**
     * Default constructor for Deposit.
     * Initializes the deposit transaction  as Paycheck, Deposit, 01/01/2001, 100.0
     */
    public Deposit() {
        super(DEFAULT_NAME,Transaction.DEFAULT_DATE, Transaction.DEFAULT_AMOUNT);
    }

    /**
     * Full constructor for Deposit.
     * Initializes the deposit transaction with specified name, date, and amount.
     *
     * @param name  verbal identification for transaction.
     * @param date date of transaction in MM/DD/YYYY format. If day or month is <10, include 0 prior to (ex: 7 -> 07).
     * @param amount numerical value of transaction. Value should be positive for deposit or negative for withdrawal or payment.
     */
    public Deposit(String name, String date, double amount) {
        super(name, date, amount);
    }

    /**
     * Copy constructor builds object with all data from Deposit object provided. No
     * changes made to original object, no shallow copying
     *
     * @param original Deposit object to use for copying
     *
     */
    public Deposit(Deposit original) {
        if(original != null) {
            this.setAll(original.getName(), original.getDate(), original.getAmount());
        } else {
            System.out.println("ERROR: bad data given to copy constructor. Shutting down...");
            System.exit(0);
        }
    }

    @Override
    public String getType() {
        return type;
    }
}
