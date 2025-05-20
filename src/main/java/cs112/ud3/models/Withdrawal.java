package cs112.ud3.models;

/*
 * -------------------------------------------
 *                Withdrawal
 * -------------------------------------------
 *  + type : String
 *  + DEFAULT_AMOUNT : double
 * -------------------------------------------
 *  + Withdrawal()
 *  + Withdrawal(name : String, date : String,
 *               amount : double)
 *  + Withdrawal(original : Withdrawal)
 *  + getType() : String
 * -------------------------------------------
 */

public class Withdrawal extends Transaction{
    public static final String type = "Withdrawal";
    public static final double DEFAULT_AMOUNT = -100.0;
    /**
     * Default constructor for Withdrawal.
     * Initializes the Withdrawal transaction  as Water Bill, Withdrawal, 01/01/2001, -100.0
     */
    public Withdrawal() {
        super(Transaction.DEFAULT_NAME, Transaction.DEFAULT_DATE, DEFAULT_AMOUNT);
    }

    /**
     * Full constructor for Withdrawal.
     * Initializes the withdrawal transaction with specified name, date, and amount.
     *
     * @param name  The name of the withdrawal transaction.
     * @param date  The date of the withdrawal transaction in MM/DD/YYYY format.
     * @param amount The numerical value of the withdrawal transaction (must be negative).
     */
    public Withdrawal(String name, String date, double amount) {
        super(name, date, amount);
    }

    /**
     * Copy constructor builds object with all data from Withdrawal object provided. No
     * changes made to original object, no shallow copying
     *
     * @param original Withdrawal object to use for copying
     *
     */
    public Withdrawal(Withdrawal original) {
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
