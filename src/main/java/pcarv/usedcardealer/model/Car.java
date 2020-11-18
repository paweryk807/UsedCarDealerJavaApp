/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcarv.usedcardealer.model;

/**
 * Class representing a car object.
 * 
 * @author Paweł Rykała
 * @version 1.2
 */
public class Car {
    /** 
     * Integer representing the car id.
    */
    private int id;
    
    /**
     * String representing the car brand.
     */
    private String brand;
    
    /**
     * String representing car model.
     */
    private String model;
    
    /** 
     * Integer representing car production year.
     */
    private int year;
    
    /**
     * Float representing car price.
     */
    private float price;
    
    /**
     * Integer representing car horsepower. 
     */
    private int horsepower;
    
        
    /**
     * Integer representing car mileage. 
     */
    private int mileage;
    
    /**
     * Returns int value from private field "id".
     * @return current car identifier
     */
    public int getId(){
        return id;
    }
    
    /**
     * Validates given as a parameter value and sets private field 
     * "id" value if given parameter is positive value.
     * @param id id to set.
     * @return true if the given parameter is a positive number and can be set.
     * 
     */
    public boolean setId(int id){
        if(id > 0){
            this.id = id;
            return true;
        }
        return false;
        
    }
    
    /**
     * Returns String value of private field "brand".
     * @return String value of private field "brand".
     */
    public String getBrand(){
        return brand;
    }
    
    /**
     * Sets the value of the private field "brand" if the given parameter is not empty.
     * @param brand brand to set.
     * @return true if the given parameter is not empty and the value of a private field is changed.
     */
    public boolean setBrand(String brand){
        if(!brand.isEmpty()){
           this.brand = brand;
           return true;
        }
        else return false;
    }
    
    /**
     * Returns String value of private field "model".
     * @return String value of private field "model".
     */
    public String getModel(){
        return model;
    }
    
    /**
     * Sets the value of the private field "model" if the given parameter is not empty.
     * @param model model to set.
     * @return true if the given parameter is not empty and the value of a private field is changed.
     */
    public boolean setModel(String model){
        if(!model.isEmpty()){
            this.model = model;
            return true;
        }
        return false;
    }
    
    /**
     * Returns integer value of private field "year".
     * @return integer value of private field "year".
     */
    public int getYear(){
        return year;
    }
    
    /**
     * Sets the value of the private field "year" if the given parameter is in range (1900 - 2020).
     * @param year year of car production to set.
     * @return true if the given parameter is in acceptable range and the value of a private field is changed.
     */
    public boolean setYear(int year){
        if(year >= 1900 && year <= 2020){
            this.year = year;
            return true;
        }
        return false;
    }
    
    /**
     * Returns float value of private field "price".
     * @return float value of private field "price".
     */
    public float getPrice(){
        return price;
    }
    
    /**
     * Sets the value of the private field "price" if the given parameter is in range (1 - 9999999).
     * @param price car price to set.
     * @return true if the given parameter is in acceptable range and the value of a private field is changed.
     */
    public boolean setPrice(float price){
        if(price > 0 && price < 10000000){
            this.price = price;
            return true;
        }
        return false;
    }
    
    /**
     * Returns integer value of private field "horsepower".
     * @return integer value of private field "horsepower".
     */
    public int getHorsepower(){
        return horsepower;
    }
    
    /**
     * Sets the value of the private field "horsepower" if the given parameter is in range (1 - 9999).
     * @param horsepower horsepower to set.
     * @return true if the given parameter is in acceptable range and the value of a private field is changed.
     */
    public boolean setHorsepower(int horsepower){
        if(horsepower > 0 && horsepower < 10000){
            this.horsepower = horsepower;
            return true;
        }
        return false;
    }
    
    /**
     * Returns integer value of private field "mileage".
     * @return integer value of private field "mileage".
     */
    public int getMileage(){
        return mileage;
    }
    
    /**
     * Sets the value of the private field "mileage" if the given parameter is in range (1 - 9999999).
     * @param mileage mileage to set.
     * @return true if the given parameter is in acceptable range and the value of a private field is changed.
     */
    public boolean setMileage(int mileage){
        if(mileage > 0 && mileage < 10000000){
            this.mileage = mileage;
            return true;
        }
        return false;
    }
    
}
