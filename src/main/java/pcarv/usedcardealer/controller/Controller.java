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
 *
 * @author Paweł Rykała
 * @version 1.5
 */
public class Controller {

    private View view;
    private CarList list;

    public Controller(CarList list, View view) {
        this.list = list;
        this.view = view;
    }

    public void initView() {
        //view.getFirstnameTextfield().setText(car.getBrand());
        //view.getLastnameTextfield().setText(car.getModel());
    }

    public void initController() {
        view.getBrowseCarsButton().addActionListener(event -> searchForCars());
        view.getSelectCarButton().addActionListener(event -> getSelectedCar());
        view.getUpdateCarButton().addActionListener(event -> updateCar());
        view.getDeleteCarButton().addActionListener(event -> deleteCar());
        view.getAddCarButton().addActionListener(event -> addCar());
    }

    private void updateCar() {
        String[] input = view.getTextFieldsDataFromManageMenu();
        try {
            if (input.length == 7) {
                Car result = list.getCarById(Integer.parseInt(input[0]));
                float newPrice = Float.parseFloat(input[1]);
                int newYear = Integer.parseInt(input[2]); //input[3] brand//input[4] model
                int newHp = Integer.parseInt(input[5]);
                int newMileage = Integer.parseInt(input[6]);

                if (!input[3].isEmpty()) {
                    result.setBrand(input[3]);
                }
                if (!input[4].isEmpty()) {
                    result.setModel(input[4]);
                }
                if (newPrice > 0) {
                    result.setPrice(newPrice);
                }
                if (newYear >= 1900 && newYear <= 2020) {
                    result.setYear(newYear);
                }
                if (newHp > 0) {
                    result.setHorsepower(newHp);
                }
                if (newMileage > 0) {
                    result.setMileage(newMileage);
                }
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

    private void deleteCar() {
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

    private void addCar() {
        String[] input = view.getTextFieldsDataFromManageMenu();
        try {
            if (input.length == 7 && Integer.parseInt(input[0]) > 0) {
                if (!list.idExist(Integer.parseInt(input[0]))) {
                    Car result = new Car();
                    float newPrice = Float.parseFloat(input[1]);
                    int newYear = Integer.parseInt(input[2]); //input[3] brand//input[4] model
                    int newHp = Integer.parseInt(input[5]);
                    int newMileage = Integer.parseInt(input[6]);
                    boolean ok = true;
                    result.setId(Integer.parseInt(input[0]));
                    if (!input[3].isEmpty() && ok) {
                        result.setBrand(input[3]);
                    } else {
                        ok = false;
                    }
                    if (!input[4].isEmpty() && ok) {
                        result.setModel(input[4]);
                    } else {
                        ok = false;
                    }
                    if (newPrice > 0.0 && newPrice < 10000000.0 && ok) {
                        result.setPrice(newPrice);
                    } else {
                        ok = false;
                    }
                    if (newYear >= 1900 && newYear <= 2020 && ok) {
                        result.setYear(newYear);
                    } else {
                        ok = false;
                    }
                    if (newHp > 0 && newHp < 10000 && ok) {
                        result.setHorsepower(newHp);
                    } else {
                        ok = false;
                    }
                    if (newMileage > 0 && newMileage < 10000000 && ok) {
                        result.setMileage(newMileage);
                    } else {
                        ok = false;
                    }
                    if (ok) {
                        list.add(result);
                        view.printCarInManageMenu(list.getCarById(Integer.parseInt(input[0])));
                    } else {
                        view.badInputWarningInManageMenu();
                    }
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

    private void getSelectedCar() {
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

    private float getFloatValue(String text) {
        try {
            float tester = Float.parseFloat(text);
            return tester;
        } catch (NumberFormatException e) {
            return 0.0f;
        }
    }

    private int getIntValue(String text) {
        try {
            int tester = Integer.parseInt(text);
            return tester;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void searchForCars() {
        view.clearTextArea();
        try {
            CarList tmp = (CarList) list.clone();
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
            boolean hiNot0 = (upperPriceLimit != 0.0f);
            if (lowNot0 || hiNot0) {
                if (lowNot0) {
                    if (hiNot0) {
                        if (upperPriceLimit >= lowerPriceLimit) {
                            result = tmp.searchCarsByPrice(lowerPriceLimit, upperPriceLimit);
                        } else {
                            result = tmp.searchCarsByPrice(upperPriceLimit, lowerPriceLimit);
                        }
                    } else {
                        result = tmp.searchCarsByPrice(lowerPriceLimit, Float.MAX_VALUE);
                    }
                } else if (hiNot0) {
                    result = tmp.searchCarsByPrice(0.0f, upperYearLimit);
                }
                tmp.setList(result);
            }
            lowNot0 = (lowerYearLimit != 0);
            hiNot0 = (upperYearLimit != 0);
            if (lowNot0 || hiNot0) {
                if (lowNot0) {
                    if (hiNot0) {
                        if (upperYearLimit >= lowerYearLimit) {
                            result = tmp.searchCarsByYear(lowerYearLimit, upperYearLimit);
                        } else {
                            result = tmp.searchCarsByYear(upperYearLimit, lowerYearLimit);
                        }
                    } else {
                        result = tmp.searchCarsByYear(lowerYearLimit, 2020);
                    }
                } else if (hiNot0) {
                    result = tmp.searchCarsByYear(1900, upperYearLimit);
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
            lowNot0 = (lowerHorsepowerLimit != 0);
            hiNot0 = (upperHorsepowerLimit != 0);
            if (lowNot0 || hiNot0) {
                if (lowNot0) {
                    if (hiNot0) {
                        if (upperHorsepowerLimit >= lowerHorsepowerLimit) {
                            result = tmp.searchCarsByPower(lowerHorsepowerLimit, upperHorsepowerLimit);
                        } else {
                            result = tmp.searchCarsByPower(upperHorsepowerLimit, lowerHorsepowerLimit);
                        }
                    } else {
                        result = tmp.searchCarsByPower(lowerHorsepowerLimit, Integer.MAX_VALUE);
                    }
                } else if (hiNot0) {
                    result = tmp.searchCarsByYear(0, upperHorsepowerLimit);
                }
                tmp.setList(result);
            }
            if (mileageLimit != 0) {
                result = tmp.searchCarsByMileage(mileageLimit);
                tmp.setList(result);
            }
            for (Car elem : result) {
                view.printCarInBrowseMenu(elem);
            }
        } catch (CloneNotSupportedException e) {
            view.somethingWentWrongInBrowseMenu();
        } catch (NoCarException e) {
            view.noResultsInBrowseMenuInfo(e.getMessage());
        }
    }
    /*
    private void carAdded(Car car) {
        JOptionPane.showMessageDialog(null, "Car saved : " + car.getBrand() + " " + car.getModel(), "Info", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void carDeleted(String brand, String model) {
        JOptionPane.showMessageDialog(null, "Car deleted : " + brand + " " + model, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void carNotFound() {
        JOptionPane.showMessageDialog(null, "Lastname saved : " + car.getModel(), "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void sayHello() {
        JOptionPane.showMessageDialog(null, "Hello " + car.getBrand() + " " + car.getModel(), "Info", JOptionPane.INFORMATION_MESSAGE);
    }*/

}
