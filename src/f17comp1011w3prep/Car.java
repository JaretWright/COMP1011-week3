package f17comp1011w3prep;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

/**
 *
 * @author JWright
 */
public class Car {
    
    //variables common to all cars
    private static int nextStockNum = 10001;
    private static String[] validBodyTypes = {"Sedan", "Coupe", "Pickup", "SUV", "Minivan", "Convertible"};
    private static String[] manufacturers = {"Acura", "Audi", "BMW", "Buick", "Cadillac", "Chevrolet", "Dodge", "Honda", "Hyundai", "Mazda", "Mercedes", "Porche"}; 
    private static String[] validColors = {"Red", "Yellow", "Green", "Grey","Black","White", "Orange"};
    
    //instance variables
    private Image vehicleImage;   
    private LocalDate purchaseByDealerDate, purchasedByOwnerDate;
    private double purchaseByDealerCost, askingPrice, soldPrice;
    private int milage, year, stockNumber;
    private String make, model, color, bodyType;    
    
    public Car(int year, String make, String model, String color, String bodyType, double purchasePrice,Image image) {
        this(year, make, model, color, bodyType, purchasePrice);
        vehicleImage=image;        
    }
    
    public Car(int year, String make, String model, String color, String bodyType, double purchasePrice) {
        setMilage(milage);
        setYear(year);
        setMake(make);
        setModel(model);
        setColor(color);
        setBodyType(bodyType);
        vehicleImage=null;
        purchaseByDealerCost = purchasePrice;
        setAskingPrice(purchasePrice*1.2);        
        stockNumber = nextStockNum;
        nextStockNum++; 
        
        try 
        {
            BufferedImage bufferedImage = ImageIO.read(new File("defaultCar.jpg"));
            vehicleImage = SwingFXUtils.toFXImage(bufferedImage, null);
        } 
        catch (IOException e) 
        {
            System.err.println(e.getMessage());
        }    
    }

    public Image getVehicleImage() {
        return vehicleImage;
    }

    public void setVehicleImage(Image vehicleImage) {
        this.vehicleImage = vehicleImage;
    }
    
    
    public static List<String> getValidBodyTypes() {
        List<String> bodyTypes = Arrays.asList(validBodyTypes);
        Collections.sort(bodyTypes);
        return bodyTypes;
    }

    public static List<String> getValidColors() {
        List<String> colors = Arrays.asList(validColors);
        Collections.sort(colors);
        return colors;
    }
    
    public void vehicleSold(double sellingPrice, LocalDate dateSold)
    {
        if (sellingPrice<0)
            throw new IllegalArgumentException("Selling price must be greater than or equal to 0");
        else 
            soldPrice = sellingPrice;
        
        purchasedByOwnerDate = dateSold;                    
    }
    
    
    public static List<String> getManufacturers()
    {
        List<String> validMakes = Arrays.asList(manufacturers);
        Collections.sort(validMakes);
        return validMakes;
    }

    public int getMilage() {
        return milage;
    }

    public String getBodyType() {
        return bodyType;
    }

    /**
     * This method will validate that the body type is valid and set the body type
     * @param bodyType - must be in the list "Sedan", "Coupe", "Pickup", "SUV", "Minivan", "Convertible"
     */
    public void setBodyType(String bodyType) {
        List<String> bodyTypes = Arrays.asList(validBodyTypes);
        
        if (bodyTypes.contains(bodyType))
            this.bodyType = bodyType;
        else
            throw new IllegalArgumentException("Invalid body type");
    }

    /**
     * This method will set the milage for the car
     * @param milage - must be greater than 0
     */
    public void setMilage(int milage) {
        if (milage < 0)
            throw new IllegalArgumentException("milage must be greater than or equal to 0");
        else
            this.milage = milage;
    }

    public int getYear() {
        return year;
    }

    
    /**
     * This sets the model year of the car.  
     * @param year - must be in the range of 1900 to 1 year beyond the current year
     */
    public void setYear(int year) {
        int nextYear = LocalDate.now().getYear()+1;
        if (year < 1900 || year > LocalDate.now().getYear()+1)
            throw new IllegalArgumentException("Model year of the car must be between 1900 and " + nextYear);
        else
            this.year = year;
    }

    public String getMake() {
        return make;
    }

    /**
     * This sets the make of the car
     * @param make - valid entries are "Acura", "Audi", "BMW", "Buick", "Cadillac", "Chevrolet", "Dodge", "Honda", "Hyundai", "Mazda", "Mercedes", "Porche"
     */
    public void setMake(String make) {
        List<String> validMakes = Arrays.asList(this.manufacturers);
        
        if (validMakes.contains(make))
            this.make = make;
        else
            throw new IllegalArgumentException("Invalid manufacturer name");
    }

    
    
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public LocalDate getPurchaseByDealerDate() {
        return purchaseByDealerDate;
    }

    public void setPurchaseByDealerDate(LocalDate purchaseByDealerDate) {
        this.purchaseByDealerDate = purchaseByDealerDate;
    }

    public LocalDate getPurchasedByOwnerDate() {
        return purchasedByOwnerDate;
    }

    public void setPurchasedByOwnerDate(LocalDate purchasedByOwnerDate) {
        this.purchasedByOwnerDate = purchasedByOwnerDate;
    }

    public double getPurchaseByDealerCost() {
        return purchaseByDealerCost;
    }

    public void setPurchaseByDealerCost(double purchaseByDealerCost) {
        this.purchaseByDealerCost = purchaseByDealerCost;
    }

    public double getAskingPrice() {
        return askingPrice;
    }

    public void setAskingPrice(double askingPrice) {
        this.askingPrice = askingPrice;
    }

    public double getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(double soldPrice) {
        this.soldPrice = soldPrice;
    }

    public int getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(int stockNumber) {
        this.stockNumber = stockNumber;
    }
    
    public String toString()
    {
        return String.format("%d %s %s dealer purchace price $%.2f is and an image was loaded %s", this.year, this.make, this.model, this.purchaseByDealerCost, vehicleImage != null);
    }
    
}
