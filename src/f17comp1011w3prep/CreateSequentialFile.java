package f17comp1011w3prep;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JWright
 */
public class CreateSequentialFile {
    private static ObjectOutputStream output; //outputs data to a file
    
    public static void main(String[] args)
    {
        openFile();
        addRecords();
        closeFile();
    }
    
    public static void openFile()
    {
        try{
            output = new ObjectOutputStream(Files.newOutputStream(Paths.get("clients.ser")));
        }
        catch (IOException ioException)
        {
            System.err.println("Error opening file. Terminating.");
            System.exit(1);
        }        
    }
    
    public static void addRecords() 
    {
        Scanner keyboard = new Scanner(System.in);
        
        System.out.printf("%s%n%s%n? ", "Enter account number, first name, last name and balance.",
                                        "Enter end of file indicator (control-z) to end input");
        
        boolean keepAsking = true;
        
        while (keepAsking)
        {
            try
            {
                //create new account record
                Account record = new Account(keyboard.nextInt(), keyboard.next(), keyboard.next(), keyboard.nextDouble());
                output.writeObject(record);
            }
            catch(NoSuchElementException elementException)
            {
                System.err.println("Invalid input.  Please try again");
                keepAsking=false;
            } catch (IOException ex) {
                System.err.println("Error writing to file.  Terminating");
                break;
            }
            System.out.print("? ");
        }
    }
    
    
    public static void closeFile()
    {
        try
        {
            if (output != null)
                output.close();
        }
        catch (IOException ioException)
        {
            System.err.println("Error closing the file. Terminating");
        }
    }
}
