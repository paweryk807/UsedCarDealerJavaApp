/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcarv.usedcardealer.model;

import java.util.ArrayList;
import java.util.List;
import pcarv.usedcardealer.exception.NoCarException;

/**
 * The class that stores all cars loaded from the database.
 * Implements clone with shallow copy.
 * 
 * @author Paweł Rykała
 * @version 1.3
 */
public class CarList implements Cloneable {

    /**
     * A list that stores objects of the {@link pcarv.usedcardealer.model.Car} type.
     */
    private List<Car> carList;

    /**
     * Initiates a {@link CarList} by creating an empty list of {@link pcarv.usedcardealer.model.Car} objects.
     */
    public CarList() {
        carList = new ArrayList<>();
    }

    /**
     * Sets the class field "carList" to the specified list
     */
    public void setList(List<Car> list) {
        carList = list;
    }
    
    /**
     * A method that checks if the id passed as a parameter has already occurred.
     * 
     * @return true if the given id has already occurred.
     */
    public boolean idExist(int id) {
        for (Car auto : carList) {
            if (auto.getId() == id) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Method returning the car with the given id parameter, if it exists.
     * If there is no such car, the method throws an exception {@link pcarv.usedcardealer.exception.NoCarException}.
     * 
     * @return {@link pcarv.usedcardealer.model.Car} with the given id, if it exists in the list.
     */
    public Car getCarById(int id) throws NoCarException {
        Car result = null;
        for (Car auto : carList) {
            if (auto.getId() == id) {
                result = auto;
            }
        }
        if (result == null) {
            throw new NoCarException("Car with selected id does not exist!");
        } else {
            return result;
        }
    }
    
    /**
     * Return the value of private field "carList".
     * 
     * @return a list of {@link pcarv.usedcardealer.model.Car} objects from the private field "carList".
     */
    public List<Car> getList() {
        return carList;
    }

    /**
     * Method adding to the list from a private field "carList" provided as a parameter {@link pcarv.usedcardealer.model.Car} object.
     */
    public void add(Car car) {
        carList.add(car);
    }

    /**
     * Method removing from the list from a private field "carList" provided as a parameter {@link pcarv.usedcardealer.model.Car} object.
     * Throws exception {@link pcarv.usedcardealer.exception.NoCarException} when private field "carList" is empty.
     */
    public void remove(Car car) throws NoCarException {
        if (carList.isEmpty()) {
            throw new NoCarException("Car can't be deleted from empty list!");
        }
        carList.remove(car);
    }

    /**
     * Method which search and returns list of all {@link pcarv.usedcardealer.model.Car} 
     * objects whose private field "brand" is the same as the one given as a parameter.
     * from private field "carList" 
     * Throws exception {@link pcarv.usedcardealer.exception.NoCarException} when private field "carList" is empty.
     * 
     * @return list of {@link pcarv.usedcardealer.model.Car} objects whose private field "brand" is the same as the one given as a parameter.
     */
    public List<Car> searchCarsByBrand(String brand) throws NoCarException {
        List<Car> resultList = new ArrayList<>();
        for (Car auto : carList) {
            if (auto.getBrand().equals(brand)) {
                resultList.add(auto);
            }
        }
        if (resultList.isEmpty()) {
            throw new NoCarException("No cars found");
        } else {
            return resultList;
        }
    }

    /**
     * Method which search and returns list of all {@link pcarv.usedcardealer.model.Car} 
     * objects whose private field "model" is the same as the one given as a parameter.
     * from private field "carList" 
     * Throws exception {@link pcarv.usedcardealer.exception.NoCarException} when private field "carList" is empty.
     * 
     * @return list of {@link pcarv.usedcardealer.model.Car} objects whose private field "model" is the same as the one given as a parameter.
     */
    public List<Car> searchCarsByModel(String model) throws NoCarException {
        List<Car> resultList = new ArrayList<>();
        for (Car auto : carList) {
            if (auto.getModel().equals(model)) {
                resultList.add(auto);
            }
        }
        if (resultList.isEmpty()) {
            throw new NoCarException("No cars found");
        } else {
            return resultList;
        }
    }

    /**
     * Method which search and returns list of all {@link pcarv.usedcardealer.model.Car} 
     * objects whose private field "year" is in range given as a parameter.
     * from private field "carList" 
     * Throws exception {@link pcarv.usedcardealer.exception.NoCarException} when private field "carList" is empty.
     * 
     * @return list of {@link pcarv.usedcardealer.model.Car} objects whose private field "year" is in range given as a parameter.
     */
    public List<Car> searchCarsByYear(int lowerLimit, int upperLimit) throws NoCarException {
        List<Car> resultList = new ArrayList<>();
        int year;
        for (Car auto : carList) {
            year = auto.getYear();
            if (year >= lowerLimit && year <= upperLimit) {
                resultList.add(auto);
            }
        }
        if (resultList.isEmpty()) {
            throw new NoCarException("No cars found");
        } else {
            return resultList;
        }
    }

    /**
     * Method which search and returns list of all {@link pcarv.usedcardealer.model.Car} 
     * objects whose private field "horsepower" is in range given as a parameter.
     * from private field "carList" 
     * Throws exception {@link pcarv.usedcardealer.exception.NoCarException} when private field "carList" is empty.
     * 
     * @return list of {@link pcarv.usedcardealer.model.Car} objects whose private field "horsepower" is in range given as a parameter.
     */
    public List<Car> searchCarsByPower(int lowerLimit, int upperLimit) throws NoCarException {
        List<Car> resultList = new ArrayList<>();
        int hp;
        for (Car auto : carList) {
            hp = auto.getHorsepower();
            if (hp >= lowerLimit && hp <= upperLimit) {
                resultList.add(auto);
            }
        }
        if (resultList.isEmpty()) {
            throw new NoCarException("No cars found");
        } else {
            return resultList;
        }
    }

    /**
     * Method which search and returns list of all {@link pcarv.usedcardealer.model.Car} 
     * objects whose private field "mileage" is in range given as a parameter.
     * from private field "carList" 
     * Throws exception {@link pcarv.usedcardealer.exception.NoCarException} when private field "carList" is empty.
     * 
     * @return list of {@link pcarv.usedcardealer.model.Car} objects whose private field "mileage" is in range given as a parameter.
     */
    public List<Car> searchCarsByMileage(int upperLimit) throws NoCarException {
        List<Car> resultList = new ArrayList<>();
        int mileage;
        for (Car auto : carList) {
            mileage = auto.getMileage();
            if (mileage <= upperLimit) {
                resultList.add(auto);
            }
        }
        if (resultList.isEmpty()) {
            throw new NoCarException("No cars found");
        } else {
            return resultList;
        }
    }

    /**
     * Method which search and returns list of all {@link pcarv.usedcardealer.model.Car} 
     * objects whose private field "price" is in range given as a parameter.
     * from private field "carList" 
     * Throws exception {@link pcarv.usedcardealer.exception.NoCarException} when private field "carList" is empty.
     * 
     * @return list of {@link pcarv.usedcardealer.model.Car} objects whose private field "price" is in range given as a parameter.
     */
    public List<Car> searchCarsByPrice(float lowerLimit, float upperLimit) throws NoCarException {
        List<Car> resultList = new ArrayList<>();
        float price;
        for (Car auto : carList) {
            price = auto.getPrice();
            if (price >= lowerLimit && price <= upperLimit) {
                resultList.add(auto);
            }
        }
        if (resultList.isEmpty()) {
            throw new NoCarException("No cars found");
        } else {
            return resultList;
        }
    }

    /**
     * Method which returns shallow copy of class object. 
     * @return shallow copy of class object;
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
