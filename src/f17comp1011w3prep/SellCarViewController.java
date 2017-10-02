package f17comp1011w3prep;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author JWright
 */
public class SellCarViewController implements Initializable {
    private Car car;
    private Inventory inventory;
    
    @FXML private Button cancelButton;
    @FXML private Button sellVehicleButton;
    
    @FXML private TextField makeTextField;
    @FXML private TextField yearTextField;
    @FXML private TextField modelTextField;
    @FXML private TextField mileageTextField;
    @FXML private TextField bodyTypeTextField;
    @FXML private TextField askingPriceTextField;
    @FXML private Label stockNumberLabel;
    @FXML private ImageView vehicleImageView;
    @FXML private TextField sellingPriceTextField;
    @FXML private DatePicker sellingDate;
    
    @FXML private Label errorMsgLabel;
    
    /**
     * This method accepts a Car object and populates the fields in the view.
     * @param car 
     */
    public void initData(Car car, Inventory inventory)
    {
        //update the instance variables;
        this.car = car;
        this.inventory = inventory;
        
        makeTextField.setText(car.getMake());
        yearTextField.setText(Integer.toString(car.getYear()));
        modelTextField.setText(car.getModel());
        mileageTextField.setText(Integer.toString(car.getMilage()));
        bodyTypeTextField.setText(car.getBodyType());
        askingPriceTextField.setText(Double.toString(car.getAskingPrice()));
        stockNumberLabel.setText(Integer.toString(car.getStockNumber()));
        vehicleImageView.setImage(car.getVehicleImage());
    }
    
    /**
     * If a car is selected from the table, this button will take the user to a screen
     * where they can add in the selling details for the car
     */
    public void cancelButtonPushed(ActionEvent event) throws IOException
    {
        //put the car back in the inventory
        inventory.addCar(car);
        
        //load the inventory view 
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("InventoryView.fxml"));
        Parent tableViewParent = loader.load();
        
        //access the controller and call a method
        InventoryViewController controller = loader.getController();
        controller.initData(inventory);
            
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    /**
     * This method will update the Car object with a selling price and the date sold
     */
    public void carSoldButtonPushed(ActionEvent event) throws IOException
    {
        if (sellingPriceTextField.getText().isEmpty() || this.sellingDate.getValue() == null)
            errorMsgLabel.setText("The selling price and date must be set.");
        else
        {
            car.setSoldPrice(Double.parseDouble(sellingPriceTextField.getText()));
            car.setPurchasedByOwnerDate(sellingDate.getValue());
            
            inventory.addCar(car);
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("InventoryView.fxml"));
            Parent tableViewParent = loader.load();
            
            //access the controller and call a method
            InventoryViewController controller = loader.getController();
            controller.initData(inventory);

            Scene tableViewScene = new Scene(tableViewParent);

            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
            }
            
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //sellVehicleButton.setDisable(true);
        errorMsgLabel.setText("");
    }    
    
}
