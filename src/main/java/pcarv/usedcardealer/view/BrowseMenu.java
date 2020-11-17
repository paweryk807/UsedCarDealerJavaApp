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
 *
 * @author Paweł Rykała
 * @version 1.0 View uses Swing framework to display UI to user
 */
class BrowseMenu extends JPanel {

    private final JLabel lowerPriceLimitLabel;
    private final JLabel upperPriceLimitLabel;
    private final JLabel lowerYearLimitLabel;
    private final JLabel upperYearLimitLabel;
    private final JLabel brandLabel;
    private final JLabel modelLabel;
    private final JLabel lowerHorsepowerLimitLabel;
    private final JLabel upperHorsepowerLimitLabel;
    private final JLabel mileageLimitLabel;

    final JButton searchButton;
    JFormattedTextField lowerPriceLimitTextField;
    JFormattedTextField upperPriceLimitTextField;
    JFormattedTextField lowerYearLimitTextField;
    JFormattedTextField upperYearLimitTextField;
    JTextField brandTextField;
    JTextField modelTextField;
    JFormattedTextField lowerHorsepowerLimitTextField;
    JFormattedTextField upperHorsepowerLimitTextField;
    JFormattedTextField mileageLimitTextField;

    JTextArea printField;

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
        // Create UI elements

        searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(80, 20));

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
        super.add(form);
        JScrollPane scroll = new JScrollPane();
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        printField.setEditable(false);
        scroll.getViewport().setView(printField);
        super.add(scroll);
    }

    void printCar(Car car) {
        this.printField.append("ID: " + String.valueOf(car.getId()) + "\n");
        this.printField.append("Brand: " + car.getBrand() + "\n");
        this.printField.append("Model: " + car.getModel() + "\n");
        this.printField.append("Year: " + String.valueOf(car.getYear()) + "\n");
        this.printField.append("Price: " + String.valueOf(car.getPrice()) + "\n");
        this.printField.append("Horsepower: " + String.valueOf(car.getHorsepower()) + "\n");
        this.printField.append("Mileage: " + String.valueOf(car.getMileage()) + "\n\n");
    }
}

