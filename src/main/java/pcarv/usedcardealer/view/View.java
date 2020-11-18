/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcarv.usedcardealer.view;

import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import pcarv.usedcardealer.model.Car;

/**
 * A class that represents the view.
 * View is responsible for getting input from the user and displaying the results of his actions.
 * View uses Swing Framework to display UI to user.
 * @author Paweł Rykała
 * @version 1.5 
 */
public class View {

    /**
     * Program window.
     */
    private JFrame frame;
    
    /**
     * Browse menu tab.
     */
    private final BrowseMenu browseMenu;
    /**
     * Management menu tab.
     */
    private final ManageMenu manageMenu;
    
    /**
     * Initializes an object, create and assigns tabs.
     * @param title title to set.
     * @param frame frame to set.
     */
    public View(String title,JFrame frame) {

        this.frame = frame;
        frame.setTitle(title);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(800, 400);
        this.frame.setLayout(new CardLayout());
        this.frame.setResizable(false);

        browseMenu = new BrowseMenu();
        manageMenu = new ManageMenu();

        //JTabbedPane
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Browse Cars", browseMenu);
        tabs.addTab("Car management", manageMenu);

        this.frame.setLocationRelativeTo(null);
        this.frame.add(tabs);
        this.frame.setVisible(true);
    }

    // Manage Menu 
    /**
     * Shows a message dialog about the lack of cars 
     * with the id parameter specified in the vehicle management menu.
     * Clears text area in management tab.
     */
    public void addCarWithExistingIdInfoInManageMenu(){
        JOptionPane.showMessageDialog(null, "Car with given id already exists", "Info", JOptionPane.INFORMATION_MESSAGE);
        manageMenu.clearPrintField();
    }
    
    /**
     * Shows a message dialog about the correct deleting of the car.
     * Clears text area in management tab.
     */
    public void carSuccessfullyDeletedInfoInManageMenu(){
        JOptionPane.showMessageDialog(null, "The car has been successfully deleted", "Info", JOptionPane.INFORMATION_MESSAGE);
        manageMenu.clearPrintField();
    }
    
     /**
     * Shows a message dialog about unsuccessful adding operation of the car.
     * Clears text area in management tab.
     */
    public void carNotAddedInfoInManageMenu(){
        JOptionPane.showMessageDialog(null, "The car hasn't been added", "Info", JOptionPane.INFORMATION_MESSAGE);
        manageMenu.clearPrintField();
    }
    
     /**
     * Shows a message dialog about successful adding operation of the car.
     */
    public void carSuccessfullyAddedInfoInManageMenu(){
        JOptionPane.showMessageDialog(null, "The car has been successfully added", "Info", JOptionPane.INFORMATION_MESSAGE);
    }
     
     /**
     * Shows a error dialog about problem with data from management tab text fields.
     */
    public void problemWithDataErrorInManageMenu(){
        JOptionPane.showMessageDialog(null, "Problem with data from textFields", "ERROR", JOptionPane.ERROR_MESSAGE);
        manageMenu.clearPrintField();
    }
    
     /**
     * Shows a warning dialog about bad data input in management tab text fields.
     */
    public void badInputWarningInManageMenu(){
        JOptionPane.showMessageDialog(null, "Wrong parameters have been entered", "Warning", JOptionPane.WARNING_MESSAGE);
        manageMenu.clearPrintField();
    }
    
     /**
     * Shows a information with message given as parameter.
     * @param message to display.
     */
    public void noResultInManageMenu(String message){
        JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.INFORMATION_MESSAGE);
        manageMenu.clearPrintField();
    }
    
     /**
     * Returns data from id text field from management tab.
     * @return String with data from id text field from management tab.
     */
    public String getIdTextFieldDataFromManageMenu(){
        return manageMenu.getIdTextFieldData();
    }
    
     /**
     * Returns data from all management tab text fields.
     * @return String array with data from all management tab text fields.
     */
    public String[] getTextFieldsDataFromManageMenu(){
        return manageMenu.getTextFieldsData();
    }
    
    /**
     * Prints car in magement tab text area field.
     * @param car {@link pcarv.usedcardealer.model.Car} object which parameters will be displayed.
     */
    public void printCarInManageMenu(Car car) {
        manageMenu.printCar(car);
    }

    /**
     * Returns a button responsible for car search operation.
     * @return JButton select button from management tab.
     */
    public JButton getSelectCarButton() {
        return manageMenu.selectButton;
    }

    /**
     * Returns a button responsible for car update operation.
     * @return JButton update button from management tab.
     */
    public JButton getUpdateCarButton() {
        return manageMenu.updateButton;
    }

    /**
     * Returns a button responsible for car delete operation.
     * @return JButton delete button from management tab.
     */
    public JButton getDeleteCarButton() {
        return manageMenu.deleteButton;
    }

    /**
     * Returns a button responsible for adding a car to database operation.
     * @return JButton add button from management tab.
     */
    public JButton getAddCarButton() {
        return manageMenu.addButton;
    }
    
    /**
     * Sets text fields in management tab to values from the given as a parameter object.
     * @param car {@link pcarv.usedcardealer.model.Car} object needed to set 
     * text fields in management tab.
     */
    public void setTextFieldsInManageMenu(Car car) {
        manageMenu.setTextFields(car);
    }

    // Browse Menu 
    /**
     * Returns a button responsible for browse cars in database.
     * @return JButton browse button from browse tab.
     */
    public JButton getBrowseCarsButton() {
        return browseMenu.searchButton;
    }
    
    /**
     * Returns a button responsible for printing all cars from database.
     * @return JButton print button from browse tab.
     */
    public JButton getPrintAllCarsButton(){
        return browseMenu.printAllButton;
    }

    /**
     * Shows a message dialog with message given as a parameter.
     * Clears text area in browse tab.
     * @param message message to display.
     */
    public void noResultsInBrowseMenuInfo(String message) {
        JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.INFORMATION_MESSAGE);
        browseMenu.clearPrintField();
    }

    /**
     * Shows a error dialog.
     * Clears text area in browse tab.
     */
    public void somethingWentWrongInBrowseMenu() {
        JOptionPane.showMessageDialog(null, "Something went wrong", "Error", JOptionPane.ERROR_MESSAGE);
        browseMenu.clearPrintField();
    }

    /**
     * Prints car in browse tab text area field.
     * @param car {@link pcarv.usedcardealer.model.Car} object which parameters will be displayed.
     */
    public void printCarInBrowseMenu(Car car) {
        browseMenu.printCar(car);
    }
    
    /**
     * Clears the text area in browse tab.
     */
    public void clearPrintFieldInBrowseMenu() {
        browseMenu.clearPrintField();
    }
    /**
     * The method returns a String array of the entered contents of all text fields from browse tab.
     * @return String array of the entered contents of all text fields from browse tab.
     */
    public String[] getTextFieldsDataFromBrowseMenu(){
        return browseMenu.getTextFieldsData();
    }

    /**
     * Returns an object form private field "frame".
     * @return JFrame from private field "frame".
     */
    public JFrame getFrame() {
        return frame;
    }
    
    /**
     * Sets private field "frame" to frame given as a parameter.
     * @param frame frame to set.
     */
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
}
