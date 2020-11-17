/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcarv.usedcardealer.controller;

import java.util.List;
import pcarv.usedcardealer.exception.NoCarException;
import pcarv.usedcardealer.model.Car;
import pcarv.usedcardealer.model.CarList;
import pcarv.usedcardealer.view.View;

/**
 * Controller class. 
 * It is responsible for communication between model and view.
 * 
 * @author Paweł Rykała
 * @version 1.5
 */
public class Controller {

    /**
     * Application view.
     */
    private View view;
    
    /**
     * List which contains all cars from database.
     */
    private CarList list;
    
    /**
     * Initiates controller.
     * @param list model to set
     * @param view view to set
     */
    public Controller(CarList list, View view) {
        this.list = list;
        this.view = view;
    }

    /**
     * Initiates communication between controller and view.
     */
    public void initController() {
        view.getBrowseCarsButton().addActionListener(event -> searchForCarsAndUpdateView());
        view.getPrintAllCarsButton().addActionListener(event -> printAllCars());
        view.getSelectCarButton().addActionListener(event -> getSelectedCarAndUpdateView());
        view.getUpdateCarButton().addActionListener(event -> updateCarAndUpdateView());
        view.getDeleteCarButton().addActionListener(event -> deleteCarAndUpdateView());
        view.getAddCarButton().addActionListener(event -> addCarAndUpdateView());

    }

    /**
     * The method responsible for finding a car by the id 
     * taken from the management tab from view.
     * After finding the car, it try to update it with data taken from view. 
     * Updates the view.
     */
    private void updateCarAndUpdateView() {
        String[] input = view.getTextFieldsDataFromManageMenu();
        try {
            if (input.length == 7) {
                Car result = list.getCarById(Integer.parseInt(input[0]));
                float newPrice = Float.parseFloat(input[1]);
                int newYear = Integer.parseInt(input[2]); //input[3] brand//input[4] model
                int newHp = Integer.parseInt(input[5]);
                int newMileage = Integer.parseInt(input[6]);
                result.setBrand(input[3]);
                result.setModel(input[4]);
                result.setPrice(newPrice);
                result.setYear(newYear);
                result.setHorsepower(newHp);
                result.setMileage(newMileage);
                view.printCarInManageMenu(list.getCarById(Integer.parseInt(input[0])));
            } else {
                view.problemWithDataErrorInManageMenu();
            }
        } catch (NumberFormatException e) {
            view.badInputWarningInManageMenu();
        } catch (NoCarException e) {
            view.noResultInManageMenu(e.getMessage());
        }
    }
    
    /**
     * The method responsible for finding a car by the id 
     * taken from the management tab from view.
     * After finding the car, it delete it. 
     * Updates the view.
     */
    private void deleteCarAndUpdateView() {
        String id = view.getIdTextFieldDataFromManageMenu();
        try {
            Car result = list.getCarById(Integer.parseInt(id));
            list.remove(result);
            view.carSuccessfullyDeletedInfoInManageMenu();
        } catch (NumberFormatException e) {
            view.badInputWarningInManageMenu();
        } catch (NoCarException e) {
            view.noResultInManageMenu(e.getMessage());
        }
    }

    /**
     * @param result value based on which a specific message is displayed in the view
     */
    private void resultOfCarAddition(boolean result) {
        if (result) {
            view.carSuccessfullyAddedInfoInManageMenu();
        } else {
            view.carNotAddedInfoInManageMenu();
        }
    }

    /**
     * The method responsible for adding car with parameters setted by data
     * taken from the management tab from view.
     * After adding the car, it tells view to display it. 
     * If there is an addition problem it tells view to display message.
     * Updates the view.
     */
    private void addCarAndUpdateView() {
        String[] input = view.getTextFieldsDataFromManageMenu();
        try {
            if (input.length == 7 && Integer.parseInt(input[0]) > 0) {
                if (!list.idExist(Integer.parseInt(input[0]))) {
                    Car result = new Car();
                    float newPrice = Float.parseFloat(input[1]);
                    int newYear = Integer.parseInt(input[2]); //input[3] brand//input[4] model
                    int newHp = Integer.parseInt(input[5]);
                    int newMileage = Integer.parseInt(input[6]);
                    boolean ok = false;
                    if (result.setId(Integer.parseInt(input[0]))) {
                        if (result.setBrand(input[3])) {
                            if (result.setModel(input[4])) {
                                if (result.setPrice(newPrice)) {
                                    if (result.setYear(newYear)) {
                                        if (result.setHorsepower(newHp)) {
                                            if (result.setMileage(newMileage)) {
                                                ok = true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (ok) {
                        list.add(result);
                        view.printCarInManageMenu(list.getCarById(Integer.parseInt(input[0])));
                    }
                    resultOfCarAddition(ok);
                } else {
                    view.addCarWithExistingIdInfoInManageMenu();
                }
            } else {
                view.problemWithDataErrorInManageMenu();
            }
        } catch (NumberFormatException e) {
            view.badInputWarningInManageMenu();
        } catch (NoCarException e) {
            view.noResultInManageMenu(e.getMessage());
        }
    }

    /**
     * The method responsible for finding a car by the id 
     * taken from the management tab from view.
     * After finding the car, it tells view to display it. 
     * Updates the view.
     */
    private void getSelectedCarAndUpdateView() {
        String id = view.getIdTextFieldDataFromManageMenu();
        try {
            if (list.idExist(Integer.parseInt(id))) {
                view.printCarInManageMenu(list.getCarById(Integer.parseInt(id)));
                view.setTextFieldsInManageMenu(list.getCarById(Integer.parseInt(id)));
            } else {
                view.noResultInManageMenu("Car with selected id does not exist!");
            }
        } catch (NumberFormatException e) {
            view.badInputWarningInManageMenu();
        } catch (NoCarException e) {
            view.noResultInManageMenu(e.getMessage());
        }
    }

    /**
     * Tries to convert the given text to an float value.
     * On an NumberFormatException, it handles it and returns 0.0f.
     * @param text value to convert
     * @return float value from given String or 0.0f when exception occurs.
     */
    private float getFloatValue(String text) {
        try {
            float tester = Float.parseFloat(text);
            return tester;
        } catch (NumberFormatException e) {
            return 0.0f;
        }
    }
    
    /**
     * Tries to convert the given text to an integer value.
     * On an NumberFormatException, it handles it and returns 0.
     * @param text value to convert
     * @return int value from given String or 0 when exception occurs.
     */
    private int getIntValue(String text) {
        try {
            int tester = Integer.parseInt(text);
            return tester;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Prints all cars parameters in browse tab in the view.
     */
    private void printAllCars() {
        view.clearPrintFieldInBrowseMenu();
        for (Car elem : list.getList()) {
            view.printCarInBrowseMenu(elem);
        }
    }
    
    /**
     * Method responsible for searching cars in the model
     * based on the data collected from the form in the browse tab.
     * After searching updates the view.
     */
    private void searchForCarsAndUpdateView() {
        //Get Form
        String input[] = view.getTextFieldsDataFromBrowseMenu();
        try {
            if (input.length == 9) {
                CarList tmp = (CarList) list.clone();
                List<Car> result = list.getList();
                float lowerPriceLimit = getFloatValue(input[0]);
                float upperPriceLimit = getFloatValue(input[1]);
                int lowerYearLimit = getIntValue(input[2]);
                int upperYearLimit = getIntValue(input[3]);
                String brand = input[4];
                String model = input[5];
                int lowerHorsepowerLimit = getIntValue(input[6]);
                int upperHorsepowerLimit = getIntValue(input[7]);
                int mileageLimit = getIntValue(input[8]);

                if (lowerPriceLimit != 0.0f || upperPriceLimit != 0.0f) {
                    if (upperPriceLimit >= lowerPriceLimit) {
                        result = tmp.searchCarsByPrice(lowerPriceLimit, upperPriceLimit);
                    } else {
                        result = tmp.searchCarsByPrice(upperPriceLimit, lowerPriceLimit);
                    }
                    tmp.setList(result);
                }
                if (lowerYearLimit != 0 || upperYearLimit != 0) {
                    if (upperYearLimit >= lowerYearLimit) {
                        result = tmp.searchCarsByYear(lowerYearLimit, upperYearLimit);
                    } else {
                        result = tmp.searchCarsByYear(upperYearLimit, lowerYearLimit);
                    }
                    tmp.setList(result);
                }
                if (!brand.isEmpty()) {
                    result = tmp.searchCarsByBrand(brand);
                    tmp.setList(result);
                }
                if (!model.isEmpty()) {
                    result = tmp.searchCarsByModel(model);
                    tmp.setList(result);
                }
                if (lowerHorsepowerLimit != 0 || upperHorsepowerLimit != 0) {
                    if (upperHorsepowerLimit >= lowerHorsepowerLimit) {
                        result = tmp.searchCarsByPower(lowerHorsepowerLimit, upperHorsepowerLimit);
                    } else {
                        result = tmp.searchCarsByPower(upperHorsepowerLimit, lowerHorsepowerLimit);
                    }
                    tmp.setList(result);
                }
                if (mileageLimit != 0) {
                    result = tmp.searchCarsByMileage(mileageLimit);
                    tmp.setList(result);
                }
                
                //Update View
                view.clearPrintFieldInBrowseMenu();
                for (Car elem : result) {
                    view.printCarInBrowseMenu(elem);
                }
            }
            else view.somethingWentWrongInBrowseMenu();
        } catch (CloneNotSupportedException e) {
            view.somethingWentWrongInBrowseMenu();
        } catch (NoCarException e) {
            view.noResultsInBrowseMenuInfo(e.getMessage());
        }
    }
}
