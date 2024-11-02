/**
 * Represents a loan with specific details including loan ID, card type, limit, amount used, status,
 * creditor ID, and period ID.
 */
public class Loan {

    /**
     * The unique identifier for the loan.
     */
    protected String loanID;

    /**
     * The type of card associated with the loan.
     */
    protected String cardType;

    /**
     * The credit limit of the loan.
     */
    protected double limit;

    /**
     * The amount used from the loan.
     */
    protected double amountUsed;

    /**
     * The current status of the loan (1-5).
     */
    protected int status;

    /**
     * The ID of the creditor associated with the loan.
     */
    protected String creditorID;

    /**
     * The period ID indicating when the loan data was recorded.
     */
    protected String periodID;

    /**
     * Constructs a new Loan object with the specified parameters, validating all inputs.
     * 
     * @param loanID The unique identifier for the loan.
     * @param cardType The type of card associated with the loan.
     * @param limit The credit limit of the loan.
     * @param amountUsed The amount used from the loan.
     * @param status The current status of the loan (1-5).
     * @param creditorID The ID of the creditor associated with the loan.
     * @param periodID The period ID indicating when the loan data was recorded.
     * 
     * @throws IllegalArgumentException if any argument is invalid according to the specification:
     *                                  - loanID or cardType is empty
     *                                  - status is not between 1 and 5
     *                                  - limit is non-zero when status is 5
     *                                  - limit is less than 500 when status is between 1 and 4
     *                                  - amountUsed is negative or exceeds the limit
     *                                  - creditorID or periodID is empty
     */
    public Loan(String loanID, String cardType, double limit, double amountUsed, int status, String creditorID, String periodID) {

        if (loanID == null || loanID.isEmpty()) {
            throw new IllegalArgumentException("Loan ID cannot be empty.");
        }

        if (cardType == null || cardType.isEmpty()) {
            throw new IllegalArgumentException("Card type cannot be empty.");
        }

        if (status < 1 || status > 5) {
            throw new IllegalArgumentException("Invalid status. Status must be between 1 and 5.");
        }

        if (status == 5 && limit != 0) {
            throw new IllegalArgumentException("Limit must be 0 when status is 5 (Unpaid balance reported as a loss).");
        }

        if (status >= 1 && status <= 4 && limit < 500) {
            throw new IllegalArgumentException("Limit must be at least 500 USD for statuses 1-4.");
        }

        if (amountUsed < 0 || amountUsed > limit) {
            throw new IllegalArgumentException("Amount used cannot exceed the limit and must be non-negative.");
        }

        if (creditorID == null || creditorID.isEmpty()) {
            throw new IllegalArgumentException("Creditor ID cannot be empty.");
        }

        if (periodID == null || periodID.isEmpty()) {
            throw new IllegalArgumentException("Period ID cannot be empty.");
        }

        this.loanID = loanID;
        this.cardType = cardType;
        this.limit = limit;
        this.amountUsed = amountUsed;
        this.status = status;
        this.creditorID = creditorID;
        this.periodID = periodID;
    }

    /**
     * Compares this loan to another object for equality.
     * Two loans are considered equal if they have the same loanID and creditorID.
     * 
     * @param otherObject The object to compare this loan to.
     * @return True if the loans are equal (same loanID and creditorID), false otherwise.
     */
    @Override
    public boolean equals(Object otherObject) {

        if (this == otherObject) return true;
        if (otherObject == null || getClass() != otherObject.getClass()) return false;

        Loan otherLoan = (Loan) otherObject;

        return this.loanID.equals(otherLoan.loanID) && this.creditorID.equals(otherLoan.creditorID);
    }
}