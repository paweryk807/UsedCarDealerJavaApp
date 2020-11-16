/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcarv.usedcardealer.model;
import java.util.ArrayList;
import java.util.List;
import pcarv.usedcardealer.exception.EmptyListException;
/**
 *
 * @author Paweł Rykała
 * @version 1.0
 */
public class CarList implements Cloneable{
    private List<Car> carList;
    
    public CarList(){
        carList = new ArrayList<>();
    }
    
    public void setList(List<Car> list){
        carList = list;
    }
    
    public List<Car> getList(){
        return carList;
    }
    
    public void add(Car car){
        carList.add(car);
    }
    
    public void remove(Car car) throws EmptyListException {
        if(carList.isEmpty()) throw new EmptyListException("Car can't be deleted from empty list!");
        carList.remove(car);
    }
    
    public List<Car> searchCarsByBrand(String brand) throws EmptyListException {
        List<Car> resultList = new ArrayList<>();
        for(Car auto : carList){
            if(auto.getBrand().equals(brand))
                resultList.add(auto);
        }
        if(resultList.isEmpty()) throw new EmptyListException("No cars found");
        else return resultList;
    }
    
    public List<Car> searchCarsByModel(String model) throws EmptyListException {
        List<Car> resultList = new ArrayList<>();
        for(Car auto : carList){
            if(auto.getModel().equals(model))
                resultList.add(auto);
        }
        if(resultList.isEmpty()) throw new EmptyListException("No cars found");
        else return resultList;
    }
    
    public List<Car> searchCarsByYear(int lowerLimit, int upperLimit) throws EmptyListException {
        List<Car> resultList = new ArrayList<>();
        int year; 
        for(Car auto : carList){
            year = auto.getYear();
            if(year >= lowerLimit && year <= upperLimit)
                resultList.add(auto);
        }
        if(resultList.isEmpty()) throw new EmptyListException("No cars found");
        else return resultList;
    }
    
    public List<Car> searchCarsByPower(int lowerLimit, int upperLimit) throws EmptyListException {
        List<Car> resultList = new ArrayList<>();
        int hp; 
        for(Car auto : carList){
            hp = auto.getHorsepower();
            if(hp >= lowerLimit && hp <= upperLimit)
                resultList.add(auto);
        }
        if(resultList.isEmpty()) throw new EmptyListException("No cars found");
        else return resultList;
    }
    
    public List<Car> searchCarsByMileage(int upperLimit) throws EmptyListException {
        List<Car> resultList = new ArrayList<>();
        int mileage; 
        for(Car auto : carList){
            mileage = auto.getMileage();
            if(mileage <= upperLimit)
                resultList.add(auto);
        }
        if(resultList.isEmpty()) throw new EmptyListException("No car found");
        else return resultList;
    }
    
    public List<Car> searchCarsByPrice(float lowerLimit, float upperLimit) throws EmptyListException {
        List<Car> resultList = new ArrayList<>();
        float price; 
        for(Car auto : carList){
            price = auto.getPrice();
            if(price >= lowerLimit && price <= upperLimit)
                resultList.add(auto);
        }
        if(resultList.isEmpty()) throw new EmptyListException("No car found");
        else return resultList;
    }
    
    
    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
