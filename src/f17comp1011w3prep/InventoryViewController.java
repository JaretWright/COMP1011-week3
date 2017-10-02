package f17comp1011w3prep;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author JWright
 */
public class InventoryViewController implements Initializable {

    private Inventory inventory;
    
    @FXML private Button newCarButton;  
    @FXML private Button sellCarButton;
    
    
    //configure the table
    @FXML private TableView<Car> inventoryTableView;
    @FXML private TableColumn<Car, String> makeColumn;
    @FXML private TableColumn<Car, String> modelColumn;
    @FXML private TableColumn<Car, Integer> yearColumn;
    @FXML private TableColumn<Car, String> colorColumn;
    @FXML private TableColumn<Car, String> bodyTypeColumn;
    @FXML private TableColumn<Car, Double> askingPriceColumn;
    @FXML private TableColumn<Car, Double> soldPriceColumn;
    
    //configure the labels
    @FXML private Label inventoryValueLabel;
    @FXML private Label totalSalesLabel;
    @FXML private Label profitLabel;
    @FXML private Label vehiclesInStockLabel;
    @FXML private Label vehiclesSoldLabel;
    
   
    /**
     * This method will change the scene to a newCar scene
     */
    public void newCarButtonPushed(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("NewCarView.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
        //access the controller and call a method
        NewCarViewController newCarController = loader.getController();
        
        //get the car to sell from the inventory        
        
        newCarController.initData(inventory);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    
    /**
     * If a car is selected from the table, this button will take the user to a screen
     * where they can add in the selling details for the car
     */
    public void sellCarButtonPushed(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SellCarView.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
        //access the controller and call a method
        SellCarViewController controller = loader.getController();
        
        //get the car to sell from the inventory
        Car carToSell = inventoryTableView.getSelectionModel().getSelectedItem();
        
        inventory.remove(carToSell);
        
        controller.initData(carToSell, inventory);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    
    public void initData(Inventory inventory)
    {
        this.inventory = inventory;
        inventoryTableView.setItems(inventory.getInventory());
        updateLabels();
    }
    
    public void updateLabels()
    {
        inventoryValueLabel.setText(String.format("Current inventory value: $%.2f", inventory.getCurrentInventoryValue()));
        totalSalesLabel.setText(String.format("Total sales: $%.2f", inventory.getTotalSales()));
        profitLabel.setText(String.format("Profit: $%.2f", inventory.getProfit()));
        vehiclesInStockLabel.setText(String.format("Vehicles in stock: %d", inventory.getNumberOfCarsInStock()));        
        vehiclesSoldLabel.setText(String.format("# of vehicles sold: %d", inventory.getNumberOfCarsSold()));    
    }
    
    /**
     * This will enable the sell button after a vehicle is selected from the table
     */
    public void carSelectedFromTable()
    {
        this.sellCarButton.setDisable(false);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sellCarButton.setDisable(true);
        
        //Configure the table columns
        makeColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("make"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("model"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Car, Integer>("year"));;
        colorColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("color"));
        bodyTypeColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("bodyType"));;
        askingPriceColumn.setCellValueFactory(new PropertyValueFactory<Car, Double>("askingPrice"));
        soldPriceColumn.setCellValueFactory(new PropertyValueFactory<Car, Double>("soldPrice"));
        
        //configure the $ value columns to only show 2 decimal places
        
        
        //load dummy data if first time in view
        inventoryTableView.setItems(getInitialInventory());
        
        updateLabels();
    }    
    
    
    public ObservableList<Car> getInitialInventory()
    {
        inventory = new Inventory();
        inventory.addCar(new Car(2015,"Audi","A4","Silver","Sedan",32934));
        inventory.addCar(new Car(2010,"Audi","A4","Black","Sedan",12353));
        inventory.addCar(new Car(2015,"Acura","TLX","White","Sedan",42368));
        inventory.addCar(new Car(2018,"Mercedes","C350","Silver","Coupe",72378));
        inventory.addCar(new Car(2018,"Acura","NSX","Silver","Coupe",123899));
       
        return inventory.getInventory();
    }
}
