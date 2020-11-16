/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcarv.usedcardealer.controller;

import java.util.List;
import javax.swing.JOptionPane;
import pcarv.usedcardealer.exception.EmptyListException;
import pcarv.usedcardealer.model.Car;
import pcarv.usedcardealer.model.CarList;
import pcarv.usedcardealer.view.View;

/**
 *
 * @author bambe
 */
public class Controller {
      
    private View view; 
    private CarList list;
    private Car car;

    
    public Controller(CarList list, View view) {
        this.list = list;
        this.view = view;
        car = list.getList().get(0);
        //initView();
        //initController();
    }
    public void initView() {
        //view.getFirstnameTextfield().setText(car.getBrand());
        //view.getLastnameTextfield().setText(car.getModel());
    }
    
    public void initController() {
        //view.getFirstnameSaveButton().addActionListener(event->saveFirstname());
        //view.getLastnameSaveButton().addActionListener(event -> saveLastname());
        view.search().addActionListener(event -> searchForCars());
        //view.getHello().addActionListener(e -> sayHello());
    }
    
    private float getFloatValue(String text){
        try{
            float tester = Float.parseFloat(text);
            return tester;
        } catch(NumberFormatException e){
            return 0.0f;
        }
    }
    
    private int getIntValue(String text){
        try{
            int tester = Integer.parseInt(text);
            return tester;
        } catch(NumberFormatException e){
            return 0;
        }
    }
    
    private void searchForCars(){
        view.clearTextArea();
        try{
            CarList tmp = (CarList)list.clone();
            List<Car> result = list.getList();
            //Get Form
            float lowerPriceLimit = getFloatValue(view.getLowerPriceLimitTextField().getText());
            float upperPriceLimit = getFloatValue(view.getUpperPriceLimitTextField().getText());
            int lowerYearLimit = getIntValue(view.getLowerYearLimitTextField().getText());
            int upperYearLimit = getIntValue(view.getUpperYearLimitTextField().getText());
            String brand = view.getBrandTextField().getText();
            String model = view.getModelTextField().getText();
            int lowerHorsepowerLimit = getIntValue(view.getLowerHorsepowerLimitTextField().getText());
            int upperHorsepowerLimit = getIntValue(view.getUpperHorsepowerLimitTextField().getText());
            int mileageLimit = getIntValue(view.getMileageLimitTextField().getText());
            
            boolean lowNot0 = (lowerPriceLimit != 0.0f);
            boolean hiNot0 = (upperPriceLimit!= 0.0f);
            if(lowNot0 || hiNot0){
                if(lowNot0){
                    if(hiNot0){
                        if(upperPriceLimit >= lowerPriceLimit)
                            result = tmp.searchCarsByPrice(lowerPriceLimit, upperPriceLimit);
                        else result = tmp.searchCarsByPrice(upperPriceLimit, lowerPriceLimit);
                    }
                    else result = tmp.searchCarsByPrice(lowerPriceLimit, Float.MAX_VALUE);
                }
                else if (hiNot0){
                    result = tmp.searchCarsByPrice(0.0f, upperYearLimit);
                }
                tmp.setList(result);
            }
            lowNot0 = (lowerYearLimit != 0);
            hiNot0 = (upperYearLimit != 0);
            if(lowNot0 || hiNot0){
                if(lowNot0){
                    if(hiNot0){
                        if(upperYearLimit >= lowerYearLimit)
                            result = tmp.searchCarsByYear(lowerYearLimit, upperYearLimit);
                        else result = tmp.searchCarsByYear(upperYearLimit, lowerYearLimit);
                    }
                    else result = tmp.searchCarsByYear(lowerYearLimit, 2020);
                }
                else if (hiNot0){
                    result = tmp.searchCarsByYear(1900, upperYearLimit);
                }
                tmp.setList(result);
            }
            if(!brand.isEmpty()){
                result = tmp.searchCarsByBrand(brand);
                tmp.setList(result);
            }
            if(!model.isEmpty()){
                result = tmp.searchCarsByModel(model);
                tmp.setList(result);
            }
            lowNot0 = (lowerHorsepowerLimit != 0);
            hiNot0 = (upperHorsepowerLimit != 0);
            if(lowNot0 || hiNot0){
                if(lowNot0){
                    if(hiNot0){
                        if(upperHorsepowerLimit >= lowerHorsepowerLimit)
                            result = tmp.searchCarsByPower(lowerHorsepowerLimit, upperHorsepowerLimit);
                        else result = tmp.searchCarsByPower(upperHorsepowerLimit, lowerHorsepowerLimit);
                    }
                    else result = tmp.searchCarsByPower(lowerHorsepowerLimit, Integer.MAX_VALUE);
                }
                else if (hiNot0){
                    result = tmp.searchCarsByYear(0, upperHorsepowerLimit);
                }
                tmp.setList(result);
            }
            if(mileageLimit != 0){
                result = tmp.searchCarsByMileage(mileageLimit);
                tmp.setList(result);
            }
            for(Car elem : result)
                view.printCar(elem);
        } catch(CloneNotSupportedException e){
            view.somethingWentWrong();
        } catch(EmptyListException e){
            view.noResultsWarning();
        } 
    }
    
    private void saveFirstname() {
        //car.setBrand(view.getFirstnameTextfield().getText());
        JOptionPane.showMessageDialog(null, "Firstname saved : " + car.getBrand(), "Info", JOptionPane.INFORMATION_MESSAGE);
        System.out.print("HERE");
    }
    
    private void saveLastname() {
        //car.setModel(view.getLastnameTextfield().getText());
        JOptionPane.showMessageDialog(null, "Lastname saved : " + car.getModel(), "Info", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void sayHello() {
        JOptionPane.showMessageDialog(null, "Hello " + car.getBrand() + " " + car.getModel(), "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
