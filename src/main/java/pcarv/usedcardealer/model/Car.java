/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcarv.usedcardealer.model;

/**
 *
 * @author Paweł Rykała
 * @version 1.0
 * Class representing a car object
 */
public class Car {
    /** the car identifier
    */
    private int id;

    private String brand;
    private String model;
    /** represents the car production year
     */
    private int year;
    private float price;
    private int horsepower;
    private int mileage;
    
    /**
     * @return current car identifier
     */
    public int getId(){
        return id;
    }
    
    /**
     * @param id id to set 
     */
    public void setId(int id){
        this.id = id;
    }
    
    /**
     * @return car brand
     */
    public String getBrand(){
        return brand;
    }
    
    /**
     * @param brand brand to set
     */
    public void setBrand(String brand){
        this.brand = brand;
    }
    
    /**
     * @return car model
     */
    public String getModel(){
        return model;
    }
    
    /**
     * @param model model to set
     */
    public void setModel(String model){
        this.model = model;
    }
    
    /**
     * @return year of production
     */
    public int getYear(){
        return year;
    }
    
    /**
     * @param year year of car production to set
     */
    public void setYear(int year){
        this.year = year;
    }
    
    /**
     * @return current car price
     */
    public float getPrice(){
        return price;
    }
    
    /**
     * @param price car price to set
     */
    public void setPrice(float price){
        this.price = price;
    }
    
    /**
     * @return horsepower
     */
    public int getHorsepower(){
        return horsepower;
    }
    
    /**
     * @param horsepower horsepower to set
     */
    public void setHorsepower(int horsepower){
        this.horsepower = horsepower;
    }
    
    /**
     * @return current car mileage
     */
    public int getMileage(){
        return mileage;
    }
    
    /**
     * @param mileage mileage to set
     */
    public void setMileage(int mileage){
        this.mileage = mileage;
    }
    
}
