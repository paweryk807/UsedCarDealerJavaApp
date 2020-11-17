/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcarv.usedcardealer.view;

import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import pcarv.usedcardealer.model.Car;

public class View {

    private JFrame frame;
    private final BrowseMenu browseMenu;
    private final ManageMenu manageMenu;
    private final JTabbedPane tabs;

    public View(String title,JFrame frame) {

        this.frame = frame;//new JFrame(title);
        frame.setTitle(title);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(800, 400);
        this.frame.setLayout(new CardLayout());
        this.frame.setResizable(false);

        browseMenu = new BrowseMenu();
        manageMenu = new ManageMenu();

        //JTabbedPane
        tabs = new JTabbedPane();
        tabs.addTab("Browse Cars", browseMenu);
        tabs.addTab("Car management", manageMenu);

        this.frame.setLocationRelativeTo(null);
        this.frame.add(tabs);
        this.frame.setVisible(true);
    }

    // Manage Menu 
    public void addCarWithExistingIdInfoInManageMenu(){
        JOptionPane.showMessageDialog(null, "Car with given id already exists", "Info", JOptionPane.INFORMATION_MESSAGE);
        manageMenu.printField.setText("");
    }
    
    public void carSuccessfullyDeletedInfoInManageMenu(){
        JOptionPane.showMessageDialog(null, "The car has been successfully deleted", "Info", JOptionPane.INFORMATION_MESSAGE);
        manageMenu.printField.setText("");
    }
    
    public void problemWithDataErrorInManageMenu(){
        JOptionPane.showMessageDialog(null, "Problem with data from textFields", "ERROR", JOptionPane.ERROR_MESSAGE);
        manageMenu.printField.setText("");
    }
    public void badInputWarningInManageMenu(){
        JOptionPane.showMessageDialog(null, "Wrong parameters have been entered", "Warning", JOptionPane.WARNING_MESSAGE);
        manageMenu.printField.setText("");
    }
    public void noResultInManageMenu(String message){
        JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.INFORMATION_MESSAGE);
        manageMenu.printField.setText("");
    }
    public String getIdTextFieldDataFromManageMenu(){
        return manageMenu.getIdTextFieldData();
    }
    public String[] getTextFieldsDataFromManageMenu(){
        return manageMenu.getTextFieldsData();
    }
    public void printCarInManageMenu(Car car) {
        manageMenu.printCar(car);
    }

    public JButton getSelectCarButton() {
        return manageMenu.selectButton;
    }

    public JButton getUpdateCarButton() {
        return manageMenu.updateButton;
    }

    public JButton getDeleteCarButton() {
        return manageMenu.deleteButton;
    }

    public JButton getAddCarButton() {
        return manageMenu.addButton;
    }

    public void setTextFieldsInManageMenu(Car car) {
        manageMenu.setTextFields(car);
    }

    // Browse Menu 
    public JButton getBrowseCarsButton() {
        return browseMenu.searchButton;
    }

    public void noResultsInBrowseMenuInfo(String message) {
        JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.INFORMATION_MESSAGE);
        browseMenu.printField.setText("");
    }

    public void somethingWentWrongInBrowseMenu() {
        JOptionPane.showMessageDialog(null, "Something went wrong", "Warning", JOptionPane.WARNING_MESSAGE);
        browseMenu.printField.setText("");
    }

    public void printCarInBrowseMenu(Car car) {
        browseMenu.printCar(car);
    }

    public void clearTextArea() {
        browseMenu.printField.setText("");
    }

    public JFormattedTextField getLowerPriceLimitTextField() {
        return browseMenu.lowerPriceLimitTextField;
    }

    public JFormattedTextField getUpperPriceLimitTextField() {
        return browseMenu.upperPriceLimitTextField;
    }

    public JFormattedTextField getLowerYearLimitTextField() {
        return browseMenu.lowerYearLimitTextField;
    }

    public JFormattedTextField getUpperYearLimitTextField() {
        return browseMenu.upperYearLimitTextField;
    }

    public JTextField getBrandTextField() {
        return browseMenu.brandTextField;
    }

    public JTextField getModelTextField() {
        return browseMenu.modelTextField;
    }

    public JFormattedTextField getLowerHorsepowerLimitTextField() {
        return browseMenu.lowerHorsepowerLimitTextField;
    }

    public JFormattedTextField getUpperHorsepowerLimitTextField() {
        return browseMenu.upperHorsepowerLimitTextField;
    }

    public JFormattedTextField getMileageLimitTextField() {
        return browseMenu.mileageLimitTextField;
    }

    // Frame
    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
}
