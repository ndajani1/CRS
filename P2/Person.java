/**
 * Represents a person with a Social Security Number (SSN), first name, and last name.
 * This class also tracks any additional names (other names) associated with the person.
 */
public class Person {

    /**
     * The Social Security Number of the person.
     */
    protected String ssn;

    /**
     * The first name of the person.
     */
    protected String firstName;

    /**
     * The last name of the person.
     */
    protected String lastName;

    /**
     * A list of other names associated with the person.
     */
    protected ListInterface<String> otherNames;

    /**
     * Constructs a Person object with the specified SSN.
     * 
     * @param ssn The Social Security Number of the person (in the format 000-00-0000).
     * @throws IllegalArgumentException if the SSN is null, empty, or not in the correct format.
     */
    protected Person(String ssn) {
        if (ssn == null || ssn.isEmpty()) {
            throw new IllegalArgumentException("SSN cannot be empty.");
        }

        if (ssn.length() != 11) {
            throw new IllegalArgumentException("SSN must be in the format 000-00-0000.");
        }

        for (int i = 0; i < ssn.length(); i++) {
            if (i == 3 || i == 6) {
                if (ssn.charAt(i) != '-') {
                    throw new IllegalArgumentException("SSN must be in the format 000-00-0000.");
                }
            } else {
                if (!Character.isDigit(ssn.charAt(i))) {
                    throw new IllegalArgumentException("SSN must be in the format 000-00-0000.");
                }
            }
        }

        this.ssn = ssn; 
        this.otherNames = new LinkedList<>();
    }

    /**
     * Constructs a Person object with the specified SSN, first name, and last name.
     * 
     * @param ssn The Social Security Number of the person (in the format 000-00-0000).
     * @param firstName The first name of the person.
     * @param lastName The last name of the person.
     * @throws IllegalArgumentException if the SSN, first name, or last name are invalid based on the criteria.
     */
    public Person(String ssn, String firstName, String lastName) {
        this(ssn);

        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty.");
        }
        if (firstName.length() < 2) {
            throw new IllegalArgumentException("First name must be at least 2 characters long.");
        }

        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be empty.");
        }
        if (lastName.length() < 2) {
            throw new IllegalArgumentException("Last name must be at least 2 characters long.");
        }

        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Adds an additional name (other name) to the list of other names, if it's not already present.
     * 
     * @param firstName The first name to add.
     * @param lastName The last name to add.
     * @return True if the name was added, false if it was already present.
     */
    public boolean otherName(String firstName, String lastName) {

        String fullName = firstName + " " + lastName;

        for (int i = 0; i < otherNames.getSize(); i++) {
            if (otherNames.get(i).equals(fullName)) {
                return false; 
            }
        }

        otherNames.add(fullName, 0);  
        return true;
    }

    /**
     * Returns a string representation of the person, including the first name, last name, SSN, and any other names.
     * 
     * @return A string representation of the person.
     */
    @Override
    public String toString() {

        String result = "First Name\t: " + firstName + "\n"
                    + "Last name\t: " + lastName + "\n"
                    + "SSN\t\t: " + ssn + "\n"
                    + "Other Names\t: ";
        
        if (otherNames != null && !otherNames.isEmpty()) {
            for (int i = 0; i < otherNames.getSize(); i++) {
                result += otherNames.get(i);
                if (i < otherNames.getSize() - 1) {
                    result += ", "; 
                }
            }
        }
        
        return result;
    }

}
