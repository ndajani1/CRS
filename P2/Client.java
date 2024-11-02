/**
 * Represents a client that extends the Person class.
 * A client has a job title, employer, and creditor details,
 * and manages a list of loans.
 */
public class Client extends Person {

    /**
     * The job title of the client.
     */
    protected String jobTitle;
    
    /**
     * The employer of the client.
     */
    protected String employer;
    
    /**
     * The creditor ID associated with the client.
     */
    protected String creditorID;

    /**
     * The period ID associated with the client's data.
     */
    protected String periodID;

    /**
     * A list of loans associated with the client.
     */
    protected ListInterface<Loan> loans;

    /**
     * Constructs a Client with all details, validating the inputs.
     * @param ssn The social security number of the client.
     * @param firstName The first name of the client.
     * @param lastName The last name of the client.
     * @param job The job title of the client.
     * @param employer The employer of the client.
     * @param creditorID The creditor ID associated with the client.
     * @param period The period ID associated with the client's data.
     * @throws IllegalArgumentException If any of the inputs do not meet the validation criteria.
     */
    public Client(String ssn, String firstName, String lastName, String job, String employer, String creditorID, String period) {
        super(ssn, firstName, lastName);
        
        if (job == null || job.isEmpty()) {
            throw new IllegalArgumentException("Job title name cannot be empty.");
        }
        if (job.length() < 4) {
            throw new IllegalArgumentException("Job title must be at least 4 characters long.");
        }
        if (employer == null || employer.isEmpty()) {
            throw new IllegalArgumentException("Employer cannot be empty.");
        }
        if (employer.length() < 4) {
            throw new IllegalArgumentException("Employer must be at least 4 characters long.");
        }

        if (creditorID == null || creditorID.isEmpty()) {
            throw new IllegalArgumentException("Creditor ID cannot be empty.");
        }
    
        if (period == null || period.isEmpty()) {
            throw new IllegalArgumentException("Period ID cannot be empty.");
        }
    
        this.jobTitle = job;
        this.employer = employer;
        this.creditorID = creditorID;
        this.periodID = period;
        this.loans = new LinkedList<>(); 
    }

    /**
     * Constructs a Client with only the ssn and creditorID.
     * No validation is applied to the parameters in this constructor.
     * @param ssn The social security number of the client.
     * @param creditorID The creditor ID associated with the client.
     */
    protected Client(String ssn, String creditorID) {
        super(ssn);
        this.creditorID = creditorID;
    }

    /**
     * Compares this client with another object for equality.
     * Clients are considered equal if they have the same ssn and creditorID.
     * @param otherObject The object to compare with this client.
     * @return True if the other object is a Client and has the same ssn and creditorID, false otherwise.
     */
    @Override
    public boolean equals(Object otherObject) {

        if (this == otherObject) return true;

        if (otherObject == null || getClass() != otherObject.getClass()) return false;
    
        Client otherClient = (Client) otherObject; 
    
        return this.ssn.equals(otherClient.ssn) && this.creditorID.equals(otherClient.creditorID);
    }

    /**
     * Adds a loan to the client's list of loans if it is not a duplicate.
     * Duplicate loans have the same loanID and creditorID.
     * The loan's creditorID must match the client's creditorID.
     * @param loan The loan to add.
     * @throws IllegalArgumentException If the loan's creditorID does not match the client's creditorID.
     */
    public void addLoan(Loan loan) {

        if (!this.creditorID.equals(loan.creditorID)) {
            throw new IllegalArgumentException("Loan's creditorID does not match the client's creditorID.");
        }

        for (int i = 0; i < loans.getSize(); i++) {
            if (loans.get(i).equals(loan)) {
                return; // Duplicate found, do not add the loan
            }
        }
    
        loans.add(loan, loans.getSize());
    }

    /**
     * Adds a new name to the client's list of other names if different from the current name.
     * The full name is formatted with the periodID in parentheses.
     * @param firstName The first name to add.
     * @param lastName The last name to add.
     * @param periodID The period associated with the name.
     * @return True if the name was added, false otherwise.
     */
    public boolean otherName(String firstName, String lastName, String periodID) {

        boolean nameAdded = super.otherName(firstName, lastName);

        if (nameAdded) {
            String fullNameWithPeriod = firstName + " " + lastName + " (" + periodID + ")";
    
            for (int i = 0; i < otherNames.getSize(); i++) {
                if (otherNames.get(i).equals(fullNameWithPeriod)) {
                    return false;  
                }
            }
 
            otherNames.add(fullNameWithPeriod, otherNames.getSize());
            return true;
        }
    
        return false;
    }

    /**
     * Returns a string representation of the client.
     * Includes the total number of loans, amounts, and creditor details.
     * @return A string representation of the client.
     */
    public String toString() {

        String result = super.toString();

        result += "\nLoans\t\t: " + loans.getSize() + " -- Total Used Amount: " + getTotalAmount() + "\n";

        result += "- Current\t: " + getLoanCountByStatus(1) + " -- Used Amount: " + getTotalUsedAmountByStatus(1) + "\n";
        result += "- Not Current\t: " + (getLoanCountByStatus(2) + getLoanCountByStatus(3) + getLoanCountByStatus(4)) 
                     + " -- Used Amount: " + (getTotalUsedAmountByStatus(2) + getTotalUsedAmountByStatus(3) + getTotalUsedAmountByStatus(4)) + "\n";
        result += "- ChargeOff\t: " + getLoanCountByStatus(5) + "\n";
    
        result += "\n\t- Submitted by: " + creditorID + "\n";
        result += "\t- Last Update: " + periodID + "\n";

        return result;
    }

    /**
     * Returns the total amount used from all loans.
     * @return The total amount used from all loans.
     */
    private double getTotalAmount() {
        double totalUsedAmount = 0;
        for (int i = 0; i < loans.getSize(); i++) {
            Loan loan = loans.get(i);
            totalUsedAmount += loan.amountUsed;
        }
        return totalUsedAmount;
    }

    /**
     * Returns the number of loans with the specified status.
     * @param status The status to filter loans by.
     * @return The count of loans with the specified status.
     */
    private int getLoanCountByStatus(int status) {
        int count = 0;
        for (int i = 0; i < loans.getSize(); i++) {
            Loan loan = loans.get(i);
            if (loan.status == status) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Returns the total amount used from loans with the specified status.
     * @param status The status to filter loans by.
     * @return The total amount used from loans with the specified status.
     */
    private double getTotalUsedAmountByStatus(int status) {
        double totalUsedAmount = 0;
        for (int i = 0; i < loans.getSize(); i++) {
            Loan loan = loans.get(i);
            if (loan.status == status) {
                totalUsedAmount += loan.amountUsed;
            }
        }
        return totalUsedAmount;
    }

    /**
     * Returns the hash code for this client, which is based on the ssn.
     * @return The hash code for this client.
     */
    public int hashCode() {
        return ssn.hashCode();
    }
}
