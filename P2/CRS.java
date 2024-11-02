import java.io.*;
import com.opencsv.*;

/**
 * The CRS class provides functionality to load and manage client and loan data from CSV files.
 * It allows managing creditors, loading data into a dataset, and generating reports for clients.
 */
public class CRS {

    /**
     * A HashSet that stores clients.
     */
    private static HashSet<Client> hashSet = new HashSet<>(100);
    
    /**
     * A list of creditor IDs.
     */
    private static LinkedList<String> listOfCreditors;

    /**
     * Adds the contents of a stack of creditors to the linked list of creditors.
     * @param creditors The stack of creditors to add to the listOfCreditors.
     */
    public static void setListOfCreditors(StackInterface<String> creditors) {
        listOfCreditors = new LinkedList<>();
    
        while (!creditors.isEmpty()) {
            listOfCreditors.add(creditors.pop(), listOfCreditors.getSize());
        }
    }

    /**
     * Returns an array of files in the specified folder.
     * @param folderName The name of the folder to retrieve the files from.
     * @return An array of files from the folder. If the folder does not exist or is not a directory, returns an empty array.
     */
    public static File[] getListOfFiles(String folderName) {
        File folder = new File(folderName);
        if (!folder.exists() || !folder.isDirectory()) {
            return new File[0];  
        }
        return folder.listFiles();
    }

    /**
     * Loads data from the specified file into the dataset.
     * Only valid data from creditors in the listOfCreditors are added to the hashSet.
     * Skips invalid lines but continues processing the file.
     * @param file The file to load data from.
     * @return True if some data were added to the dataset, false otherwise.
     */
    public static boolean loadData(File file) {

        boolean dataAdded = false;

        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            String[] header = reader.readNext();  
            String fileName = file.getName();
            String creditorID = fileName.split("_")[0];
    
            if (!listOfCreditors.contains(creditorID)) {
                return false;  
            }
    
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                try {
                    String ssn = nextLine[0].trim();
                    String firstName = nextLine[1].trim();
                    String lastName = nextLine[2].trim();
                    String jobTitle = nextLine[3].trim();
                    String employer = nextLine[4].trim();
                    String loanID = nextLine[5].trim();
                    String cardType = nextLine[6].trim();
                    double limit = Double.parseDouble(nextLine[7].trim());
                    double amountUsed = Double.parseDouble(nextLine[8].trim());
                    int status = Integer.parseInt(nextLine[9].trim());
                    String periodID = fileName.split("_")[1];  

                    Loan loan = new Loan(loanID, cardType, limit, amountUsed, status, creditorID, periodID);
                    Client client = new Client(ssn, firstName, lastName, jobTitle, employer, creditorID, periodID);
    
                    Client existingClient = hashSet.get(client);
                    if (existingClient != null) {
                        existingClient.addLoan(loan);
                        existingClient.otherName(firstName, lastName, periodID);
                    } else {
                        client.addLoan(loan);
                        hashSet.put(client);
                    }
    
                    dataAdded = true;  
                } catch (Exception e) {
                    continue;  
                }
            }
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return dataAdded;
    }

    /**
     * Generates a report for the client with the specified SSN.
     * @param ssn The social security number of the client to generate the report for.
     * @return A string containing the client's report. If no data is found for the SSN, returns a message indicating that.
     */
    public static String createReport(String ssn) {
        String report = "";
        
        LinkedList<Client> allClients = hashSet.getAllValues();

        for (int i = 0; i < allClients.getSize(); i++) {
            Client client = allClients.get(i);
            if (client.ssn.equals(ssn)) {
                report += client.toString() + "\n";
            }
        }
    
        if (!report.isEmpty()) {
            return report;
        } else {
            return "No data found for SSN: " + ssn;
        }
    }

}