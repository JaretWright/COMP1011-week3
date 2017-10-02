package f17comp1011w3prep;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author JWright
 */
public class NewCarViewController implements Initializable {

    Inventory inventory;
    
    @FXML private Button saveCarButton;
    @FXML private Button loadImageButton;
    
    @FXML private ComboBox manufacturerComboBox;
    @FXML private ComboBox colorComboBox;
    @FXML private ComboBox bodyTypeComboBox;
    
    @FXML private Slider yearSlider;
    @FXML private Label yearLabel;
    
    @FXML private TextField modelTextField;
    @FXML private TextField dealerCostTextField;
    
    @FXML private ImageView vehicleImage;
    @FXML private Label errMsgLabel;
    
    //used for the FileChooser
    private FileChooser fileChooser;
    private File filePath;
    
    public void cancelButtonPushed(ActionEvent event) throws IOException
    {
        changeScene(event, "InventoryView.fxml");
    }
    
    
    public void changeScene(ActionEvent event, String fxmlFileName) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFileName));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        //access the controller and call a method
        InventoryViewController controller = loader.getController();

        //get the car to sell from the inventory        

        controller.initData(inventory);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
    
    
    public void chooseImageButtonPushed(ActionEvent event)
    {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        fileChooser = new FileChooser();        
        fileChooser.setTitle("Open Image");
        FileChooser.ExtensionFilter extentionFilterJPG = new FileChooser.ExtensionFilter("Image files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extentionFilterPNG = new FileChooser.ExtensionFilter("Image files (*.png)", "*.png");
        fileChooser.getExtensionFilters().addAll(extentionFilterPNG, extentionFilterJPG);
        
        //Set to user's picture directory, or go to default C drive if cannot access
        String userDirectoryString = System.getProperty("user.home") + "\\Pictures";
        File userDirectory = new File(userDirectoryString);
        
        if(!userDirectory.canRead()) {
            userDirectory = new File("c:/");
        }
        fileChooser.setInitialDirectory(userDirectory);               
        
        //open the file dialog window
        this.filePath = fileChooser.showOpenDialog(stage);
        
        try {
                BufferedImage bufferedImage = ImageIO.read(filePath);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                vehicleImage.setImage(image);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
    }
    
    
    /**
     * This method is used to set the instance variable holding the current inventory of cars
     * This will be used to add the new car to the inventory
     * @param inventory 
     */
    public void initData(Inventory inventory)
    {
        this.inventory = inventory;
    }
    
    
    
    /**
     * When this method is called, it will try to create an instance of a Car object
     */
    public void saveCarButtonPushed(ActionEvent event) throws IOException
    {
        Car newCar;
        try
        {
            if (this.vehicleImage == null)
            {
                newCar = new Car(Integer.parseInt(yearLabel.getText()), (String) manufacturerComboBox.getValue(), modelTextField.getText(), (String) colorComboBox.getValue(), 
                                    (String) bodyTypeComboBox.getValue(),Double.parseDouble(dealerCostTextField.getText()));                
            }
            else
            {
                newCar = new Car(Integer.parseInt(yearLabel.getText()), (String) manufacturerComboBox.getValue(), modelTextField.getText(), (String) colorComboBox.getValue(), 
                                    (String) bodyTypeComboBox.getValue(), Double.parseDouble(dealerCostTextField.getText()), vehicleImage.getImage());                
            }
            
            
            //add the car to the inventory and change scenes
            inventory.addCar(newCar);            
            changeScene(event, "InventoryView.fxml");            
        }
        catch (IllegalArgumentException e)
        {
            errMsgLabel.setText(e.getMessage());
        }
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        manufacturerComboBox.getItems().addAll(Car.getManufacturers());
        colorComboBox.getItems().addAll(Car.getValidColors());
        bodyTypeComboBox.getItems().addAll(Car.getValidBodyTypes());
            
        yearSlider.setMin(1900);
        yearSlider.setMax(LocalDate.now().getYear()+1);
        yearSlider.setValue(LocalDate.now().getYear());
        //yearSlider.setShowTickLabels(true);
        
        yearSlider.setShowTickMarks(true);
        yearLabel.setText(Integer.toString(LocalDate.now().getYear()));
        
        yearSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {                    
                    yearLabel.setText(String.format("%.0f", new_val));
            }

        });
        
        //add the default image for the car
        try {
                BufferedImage bufferedImage = ImageIO.read(new File("defaultCar.jpg"));
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                vehicleImage.setImage(image);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        
        errMsgLabel.setText("");
    }    
    
}
