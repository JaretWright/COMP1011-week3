package f17comp1011w3prep;

import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author JWright
 */
public class Inventory {
    private ObservableList<Car> inventory;
    
       
    public Inventory()
    {
        inventory = FXCollections.observableArrayList();
    }
    
    public void addCar(Car newCar)
    {
        inventory.add(newCar);
    }
    
    
    public void sellCar(Car soldCar, double price, LocalDate dateSold)
    {
        soldCar.vehicleSold(price, dateSold);
    }
    
    
    /**
     * This method will count and return the number of vehicles that have 
     * been sold
     */
     public int getNumberOfCarsSold()
    {
        int numOfCars = 0;
        
        for (Car car : inventory)
        {            
            if (car.getPurchasedByOwnerDate() != null)
                numOfCars++;
        }
        
        return numOfCars;
    }
    
     /**
      * This method returns the total profit for all sales
      */
     public double getProfit()
     {
         double profit=0;
         
         for (Car car : inventory)
         {
             if (car.getPurchasedByOwnerDate() != null)
             {
                 profit += car.getSoldPrice() - car.getPurchaseByDealerCost();
             }
         }
         
         return profit;
     }
    
    /**
     * Returns the asking $ value of the cars that have not been sold
     * @return 
     */
    public double getCurrentInventoryValue()
    {
        double inventoryValue = 0;
        
        for (Car car : inventory)
        {            
            if (car.getPurchasedByOwnerDate() == null)
                inventoryValue += car.getAskingPrice();
        }
        
        return inventoryValue;
    }
    
    
    public void remove(Car car)
    {
        inventory.remove(car);
    }
    
    /**
     * Returns the asking $ value of the cars that have not been sold
     * @return 
     */
    public int getNumberOfCarsInStock()
    {
        int numOfCars = 0;
        
        for (Car car : inventory)
        {            
            if (car.getPurchasedByOwnerDate() == null)
                numOfCars++;
        }
        
        return numOfCars;
    }
    

    /**
     * This method will sum up the $ amounts of all vehicles that have been sold
     * @return 
     */
    public double getTotalSales()
    {
        double totalSales = 0;
        
        for (Car car : inventory)
        {            
            if (car.getPurchasedByOwnerDate() == null)
                totalSales += car.getSoldPrice();
        }
        
        return totalSales;
    }
    
    
    
    public ObservableList<Car> getInventory() {
        return inventory;
    }    
    
    
    public String toString()
    {
        String returnString = "";
        for (Car car : inventory)
        {
            returnString += car + "%n";
        }
        return returnString;
    }
}
