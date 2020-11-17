/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcarv.usedcardealer.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import pcarv.usedcardealer.model.Car;

/**
 *
 * @author Paweł Rykała
 * @version 1.0
 */
class ManageMenu extends JPanel {

    private final JLabel idLabel;
    private final JLabel priceLabel;
    private final JLabel yearLabel;
    private final JLabel brandLabel;
    private final JLabel modelLabel;
    private final JLabel horsepowerLabel;
    private final JLabel mileageLabel;

    JTextField idTextField;
    JTextField priceTextField;
    JTextField yearTextField;
    JTextField brandTextField;
    JTextField modelTextField;
    JTextField horsepowerTextField;
    JTextField mileageTextField;

    JButton selectButton;
    JButton addButton;
    JButton deleteButton;
    JButton updateButton;
    JTextArea printField;

    ManageMenu() {
        super();
        super.setLayout(new GridLayout(0, 2));
        idLabel = new JLabel("Car ID: ");
        priceLabel = new JLabel("Car price: ");
        yearLabel = new JLabel("Year of production: ");
        brandLabel = new JLabel("Brand: ");
        modelLabel = new JLabel("Model: ");
        horsepowerLabel = new JLabel("Horsepower: ");
        mileageLabel = new JLabel("Mileage: ");

        idTextField = new JTextField();
        priceTextField = new JTextField();
        yearTextField = new JTextField();
        brandTextField = new JTextField();
        modelTextField = new JTextField();
        horsepowerTextField = new JTextField();
        mileageTextField = new JTextField();

        selectButton = new JButton("Select");
        selectButton.setPreferredSize(new Dimension(80, 20));
        addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(80, 20));
        deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(new Dimension(80, 20));
        updateButton = new JButton("Update");
        updateButton.setPreferredSize(new Dimension(80, 20));
        printField = new JTextArea(40, 40);
        this.createFormWithTextArea();

    }

    private void createFormWithTextArea() {
        JLabel[] labels = {
            idLabel, priceLabel,
            yearLabel, brandLabel,
            modelLabel, horsepowerLabel,
            mileageLabel
        };
        JTextField[] txtFields = {
            idTextField, priceTextField,
            yearTextField, brandTextField,
            modelTextField, horsepowerTextField,
            mileageTextField
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
        layout.putConstraint(SpringLayout.WEST, selectButton, 100, SpringLayout.WEST, form);
        layout.putConstraint(SpringLayout.NORTH, selectButton, 25 * (numPairs + 1), SpringLayout.NORTH, form);
        form.add(selectButton);

        layout.putConstraint(SpringLayout.WEST, addButton, 100, SpringLayout.WEST, form);
        layout.putConstraint(SpringLayout.NORTH, addButton, 25 * (numPairs + 2), SpringLayout.NORTH, form);
        form.add(addButton);

        layout.putConstraint(SpringLayout.WEST, updateButton, 100, SpringLayout.WEST, form);
        layout.putConstraint(SpringLayout.NORTH, updateButton, 25 * (numPairs + 3), SpringLayout.NORTH, form);
        form.add(updateButton);

        layout.putConstraint(SpringLayout.WEST, deleteButton, 100, SpringLayout.WEST, form);
        layout.putConstraint(SpringLayout.NORTH, deleteButton, 25 * (numPairs + 4), SpringLayout.NORTH, form);
        form.add(deleteButton);

        this.add(form);
        JScrollPane scroll = new JScrollPane();
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        printField.setEditable(false);
        scroll.getViewport().setView(printField);
        this.add(scroll);
    }

    String getIdTextFieldData() {
        return idTextField.getText();
    }

    String[] getTextFieldsData() {
        String[] textEntered = {
            idTextField.getText(), priceTextField.getText(),
            yearTextField.getText(), brandTextField.getText(),
            modelTextField.getText(), horsepowerTextField.getText(),
            mileageTextField.getText()
        };
        return textEntered;
    }

    void printCar(Car car) {
        this.printField.setText("");
        this.printField.append("ID: " + String.valueOf(car.getId()) + "\n");
        this.printField.append("Brand: " + car.getBrand() + "\n");
        this.printField.append("Model: " + car.getModel() + "\n");
        this.printField.append("Year: " + String.valueOf(car.getYear()) + "\n");
        this.printField.append("Price: " + String.valueOf(car.getPrice()) + "\n");
        this.printField.append("Horsepower: " + String.valueOf(car.getHorsepower()) + "\n");
        this.printField.append("Mileage: " + String.valueOf(car.getMileage()) + "\n\n");
    }

    void setTextFields(Car car) {
        idTextField.setText(String.valueOf(car.getId()));
        brandTextField.setText(car.getBrand());
        modelTextField.setText(String.valueOf(car.getModel()));
        yearTextField.setText(String.valueOf(car.getYear()));
        priceTextField.setText(String.valueOf(car.getPrice()));
        horsepowerTextField.setText(String.valueOf(car.getHorsepower()));
        mileageTextField.setText(String.valueOf(car.getMileage()));
    }
}
