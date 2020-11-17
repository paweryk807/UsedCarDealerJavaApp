/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcarv.usedcardealer.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.text.MaskFormatter;
import pcarv.usedcardealer.model.Car;

/**
 * A class that represents the browse tab in the menu.
 * Extends JPanel.
 * 
 * @author Paweł Rykała
 * @version 1.2 BrowseMenu uses Swing framework to display UI to user
 */
class BrowseMenu extends JPanel {

    /**
     * A label used to show the user which text field 
     * is responsible for entering a value of lower limit of car price.
     */
    private final JLabel lowerPriceLimitLabel;
    
    /**
     * A label used to show the user which text field 
     * is responsible for entering a value of upper limit of car price.
     */
    private final JLabel upperPriceLimitLabel;

    /**
     * A label used to show the user which text field 
     * is responsible for entering a value of lower limit of car production year.
     */
    private final JLabel lowerYearLimitLabel;
    
    /**
     * A label used to show the user which text field 
     * is responsible for entering a value of upper limit of car production year.
     */
    private final JLabel upperYearLimitLabel;
    
    /**
     * A label used to show the user which text field 
     * is responsible for entering a value of car brand.
     */
    private final JLabel brandLabel;
    
    /**
     * A label used to show the user which text field 
     * is responsible for entering a value of car model.
     */
    private final JLabel modelLabel;
    
    /**
     * A label used to show the user which text field 
     * is responsible for entering a value of lower limit of car horsepower.
     */
    private final JLabel lowerHorsepowerLimitLabel;
    
    /**
     * A label used to show the user which text field 
     * is responsible for entering a value of upper limit of car horsepower.
     */
    private final JLabel upperHorsepowerLimitLabel;
    
    /**
     * A label used to show the user which text field 
     * is responsible for entering a value of mileage limit.
     */
    private final JLabel mileageLimitLabel;

    /**
     * Button responsible for search operation.
     */
    public JButton searchButton;
    
    /**
     * Button responsible for printing all objects from model in text area.
     */
    public JButton printAllButton;
    
    /**
     * Text field responsible for entering a value of lower limit of car price.
     */
    private final JFormattedTextField lowerPriceLimitTextField;
    
    /**
     * Text field responsible for entering a value of upper limit of car price.
     */
    private final JFormattedTextField upperPriceLimitTextField;
    
    /**
     * Text field responsible for entering a value of lower limit of car year of production.
     */
    private final JFormattedTextField lowerYearLimitTextField;
    
    /**
     * Text field responsible for entering a value of upper limit of car year of production.
     */
    private final JFormattedTextField upperYearLimitTextField;
    
    /**
     * Text field responsible for entering a car brand.
     */
    private final JTextField brandTextField;
    
    /**
     * Text field responsible for entering a car model.
     */
    private final JTextField modelTextField;
    
    /**
     * Text field responsible for entering a value of lower limit of car horsepower.
     */
    private final JFormattedTextField lowerHorsepowerLimitTextField;
    
    /**
     * Text field responsible for entering a value of upper limit of car horsepower.
     */
    private final JFormattedTextField upperHorsepowerLimitTextField;
    
    /**
     * Text field responsible for entering a value of limit of mileage.
     */
    private final JFormattedTextField mileageLimitTextField;

    /**
     * Text area used for printing values sended from controller.
     */
    private final JTextArea printField;

    /**
     * Initializes an object, assigns text to labels.
     * Sets masks to formatted text fields.
     * Calls the method {@link #createFormWithTextArea() }
     */
    BrowseMenu() {
        super();
        super.setLayout(new GridLayout(0, 2));
        MaskFormatter priceMask = null;
        MaskFormatter yearOrHorsepowerMask = null;
        MaskFormatter mileageMask = null;
        try {
            priceMask = new MaskFormatter("#######.##");
            yearOrHorsepowerMask = new MaskFormatter("####");
            mileageMask = new MaskFormatter("#######");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        lowerPriceLimitTextField = new JFormattedTextField(priceMask);
        upperPriceLimitTextField = new JFormattedTextField(priceMask);
        lowerYearLimitTextField = new JFormattedTextField(yearOrHorsepowerMask);
        upperYearLimitTextField = new JFormattedTextField(yearOrHorsepowerMask);
        brandTextField = new JTextField(10);
        modelTextField = new JTextField(10);
        lowerHorsepowerLimitTextField = new JFormattedTextField(yearOrHorsepowerMask);
        upperHorsepowerLimitTextField = new JFormattedTextField(yearOrHorsepowerMask);
        mileageLimitTextField = new JFormattedTextField(mileageMask);

        searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(80, 20));

        printAllButton = new JButton("Print all");
        printAllButton.setPreferredSize(new Dimension(80, 20));

        lowerPriceLimitLabel = new JLabel("Lower price limit: ");
        upperPriceLimitLabel = new JLabel("Upper price limit: ");
        lowerYearLimitLabel = new JLabel("Year from: ");
        upperYearLimitLabel = new JLabel("Year to: ");
        brandLabel = new JLabel("Brand: ");
        modelLabel = new JLabel("Model: ");
        lowerHorsepowerLimitLabel = new JLabel("Lower horsepower limit: ");
        upperHorsepowerLimitLabel = new JLabel("Upper horsepower limit: ");
        mileageLimitLabel = new JLabel("Upper mileage limit: ");

        printField = new JTextArea(20, 20);
        this.createFormWithTextArea();
    }

    /**
     * Sets the placement of buttons, labels, text area, and text boxes. 
     * After executing the method, tab contains the form and text area.
     */
    private void createFormWithTextArea() {
        JLabel[] labels = {lowerPriceLimitLabel, upperPriceLimitLabel,
            lowerYearLimitLabel, upperYearLimitLabel,
            brandLabel, modelLabel,
            lowerHorsepowerLimitLabel, upperHorsepowerLimitLabel, mileageLimitLabel
        };
        JTextField[] txtFields = {lowerPriceLimitTextField, upperPriceLimitTextField,
            lowerYearLimitTextField, upperYearLimitTextField,
            brandTextField, modelTextField,
            lowerHorsepowerLimitTextField, upperHorsepowerLimitTextField, mileageLimitTextField
        };

        JPanel form = new JPanel();
        SpringLayout layout = new SpringLayout();
        form.setLayout(layout);
        int numPairs = labels.length;
        for (int i = 0; i < numPairs; i++) {
            layout.putConstraint(SpringLayout.WEST, labels[i], 5, SpringLayout.WEST, form);
            layout.putConstraint(SpringLayout.NORTH, labels[i], 25 * i, SpringLayout.NORTH, form);
            labels[i].setHorizontalTextPosition(JLabel.TRAILING);
            form.add(labels[i]);
            txtFields[i].setColumns(10);
            layout.putConstraint(SpringLayout.WEST, txtFields[i], 150, SpringLayout.WEST, form);
            layout.putConstraint(SpringLayout.NORTH, txtFields[i], 25 * i, SpringLayout.NORTH, form);
            form.add(txtFields[i]);
        }
        layout.putConstraint(SpringLayout.WEST, searchButton, 100, SpringLayout.WEST, form);
        layout.putConstraint(SpringLayout.NORTH, searchButton, 25 * (numPairs + 1), SpringLayout.NORTH, form);
        form.add(searchButton);
        layout.putConstraint(SpringLayout.WEST, printAllButton, 100, SpringLayout.WEST, form);
        layout.putConstraint(SpringLayout.NORTH, printAllButton, 25 * (numPairs + 2), SpringLayout.NORTH, form);
        form.add(printAllButton);
        super.add(form);
        JScrollPane scroll = new JScrollPane();
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        printField.setEditable(false);
        scroll.getViewport().setView(printField);
        super.add(scroll);
    }

    /**
     * Prints the parameters with short description of {@link pcarv.usedcardealer.model.Car} object
     * given as a parameter through the private field "printField" element.
     */
    void printCar(Car car) {
        this.printField.append("ID: " + String.valueOf(car.getId()) + "\n");
        this.printField.append("Brand: " + car.getBrand() + "\n");
        this.printField.append("Model: " + car.getModel() + "\n");
        this.printField.append("Year: " + String.valueOf(car.getYear()) + "\n");
        this.printField.append("Price: " + String.valueOf(car.getPrice()) + "\n");
        this.printField.append("Horsepower: " + String.valueOf(car.getHorsepower()) + "\n");
        this.printField.append("Mileage: " + String.valueOf(car.getMileage()) + "\n\n");
    }

    /**
     * The method returns a String array of the entered contents of all text fields.
     * @return String array of the entered contents of all text fields.
     */
    public String[] getTextFieldsData() {
        String[] textEntered = {
            lowerPriceLimitTextField.getText(),
            upperPriceLimitTextField.getText(),
            lowerYearLimitTextField.getText(),
            upperYearLimitTextField.getText(),
            brandTextField.getText(),
            modelTextField.getText(),
            lowerHorsepowerLimitTextField.getText(),
            upperHorsepowerLimitTextField.getText(),
            mileageLimitTextField.getText()};
        return textEntered;
    }
    
    /**
     * Clears the text area.
     */
    public void clearPrintField(){
        this.printField.setText("");
    }

}
