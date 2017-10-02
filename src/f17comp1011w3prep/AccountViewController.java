package f17comp1011w3prep;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author JWright
 */
public class AccountViewController implements Initializable {

    @FXML private TextField accountNumberTextField;
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField balanceTextField;
    
    @FXML private Label fileLabel;
    @FXML private Button chooseFileButton;
    @FXML private Button saveRecordButton;
    @FXML private Button updateDisplayButton;
    
    @FXML private TextArea recordsFromFile;
    
    //used for the FileChooser
    private FileChooser fileChooser;
    private File filePath;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    
    /**
     * This method will open a FileChooser object and return the path
     */
    public void chooseFileButtonPushed(ActionEvent event)
    {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        this.filePath = fileChooser.showSaveDialog(stage);
        fileLabel.setVisible(true);
        fileLabel.setText(filePath.toString());
        this.saveRecordButton.setDisable(false);
    }
    
    /**
     * This will create a record and save it to the file
     */
    public void saveRecordButtonPushed()
    {
        if (output == null){
            openOutputFile();
        }
        
        try
            {
                //create new account record
                Account record = new Account(Integer.parseInt(accountNumberTextField.getText()),
                                             firstNameTextField.getText(),
                                             lastNameTextField.getText(),
                                             Double.parseDouble(balanceTextField.getText()));
                System.out.printf("The new record is: %s %n", record.toString());
                output.writeObject(record);
            }
            catch(NoSuchElementException elementException)
            {
                System.err.println("Invalid input.  Please try again");
            } catch (IOException ex) {
                System.err.println("Error writing to file.  Terminating");
            }    
    }
    
    /**
     * This will close the output stream and open an input stream;
     */
    public void updateDisplayButtonPushed()
    {
        try{
            if (output != null)
            output.close();
            output = null;
        }
        catch(IOException e)
        {
            System.err.println("Oops could not close that file.");
        }
        openInputFile();
        updateRecordsTextArea();
        closeFile();
    }
    
    public void openOutputFile()
    {
        try
        {
            output = new ObjectOutputStream(Files.newOutputStream(Paths.get(filePath.getAbsolutePath())));
        }
        catch (IOException ioException)
        {
            System.err.println("Error opening file.");
            System.exit(1);
        }
    }
    
    public void openInputFile()
    {
        try
        {
            input = new ObjectInputStream(Files.newInputStream(Paths.get(filePath.getAbsolutePath())));
        }
        catch (IOException ioException)
        {
            System.err.println("Error opening file.");
            System.exit(1);
        }
    }

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.fileLabel.setVisible(false);
        this.saveRecordButton.setDisable(true);
    }    

    private void updateRecordsTextArea() {
        
        String records = "";
        records = String.format("%-10s%-12s%-12s%10s%n", "Account","First Name","Last Name", "Balance");
        
        try
        {
            while (true)
            {
                Account record =(Account) input.readObject();
                
                records += String.format("%-10d%-12s%-12s$%12.2f%n", record.getAccount(),
                                        record.getFirstName(), record.getLastName(), record.getBalance());
            }
        }
        catch (EOFException endOfFileException)
        {
            System.out.printf("No more records%n");
        }
        catch (ClassNotFoundException classNotFoundException)
        {
            System.err.println("Invalid object type - terminating");
        }
        catch (IOException ioException)
        {
            System.err.println("Error reading from file. Terminating");
        }
        this.recordsFromFile.setText(records);
        closeFile();
    }
    
    public void closeFile()
    {
        try{
            if (input != null)
                input.close();
        }
        catch(IOException ioException)
        {
            System.err.println("Error closing file. Terminating");
            System.exit(1);
        }
    }
    
}
