/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcarv.usedcardealer.view;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.text.MaskFormatter;
import pcarv.usedcardealer.model.Car;

/**
 *
 * @author Paweł Rykała
 * @version 1.0
 * View uses Swing framework to display UI to user
 */
public class View {
    private JFrame frame;
    private final JButton searchButton;
    private final JLabel lowerPriceLimitLabel;
    private final JLabel upperPriceLimitLabel;
    private final JLabel lowerYearLimitLabel;
    private final JLabel upperYearLimitLabel;
    private final JLabel brandLabel;
    private final JLabel modelLabel;
    private final JLabel lowerHorsepowerLimitLabel;
    private final JLabel upperHorsepowerLimitLabel;
    private final JLabel mileageLimitLabel;
    
    private JFormattedTextField lowerPriceLimitTextField;
    private JFormattedTextField upperPriceLimitTextField;
    private JFormattedTextField lowerYearLimitTextField;
    private JFormattedTextField upperYearLimitTextField;
    private JTextField brandTextField;
    private JTextField modelTextField;
    private JFormattedTextField lowerHorsepowerLimitTextField;
    private JFormattedTextField upperHorsepowerLimitTextField;
    private JFormattedTextField mileageLimitTextField;
  
    private JTextArea printField;

     public View(String title) {
        
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLayout(new CardLayout());
        frame.setResizable(false);
        JPanel browse = new JPanel();
        browse.setLayout(new GridLayout(0,2));
        JPanel manage = new JPanel();
        
        MaskFormatter priceMask = null;
        MaskFormatter yearOrHorsepowerMask = null;
        MaskFormatter mileageMask = null;
        try{
            priceMask = new MaskFormatter("#######.##");
            yearOrHorsepowerMask = new MaskFormatter("####");
            mileageMask = new MaskFormatter("#######");
        }
        catch(ParseException e){
            e.printStackTrace();
        }
        lowerPriceLimitTextField = new JFormattedTextField(priceMask);
        upperPriceLimitTextField= new JFormattedTextField(priceMask);
        lowerYearLimitTextField= new JFormattedTextField(yearOrHorsepowerMask);
        upperYearLimitTextField= new JFormattedTextField(yearOrHorsepowerMask);
        brandTextField= new JTextField(10);
        modelTextField= new JTextField(10);
        lowerHorsepowerLimitTextField= new JFormattedTextField(yearOrHorsepowerMask);
        upperHorsepowerLimitTextField= new JFormattedTextField(yearOrHorsepowerMask);
        mileageLimitTextField= new JFormattedTextField(mileageMask);
        
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Browse Cars", browse);
        tabs.addTab("Car management", manage);

        frame.setLocationRelativeTo(null);
      
        // Create UI elements
        searchButton = new JButton("Search");
       
        lowerPriceLimitLabel = new JLabel("Lower price limit: ");
        upperPriceLimitLabel = new JLabel("Upper price limit: ");
        lowerYearLimitLabel = new JLabel("Year from: ");
        upperYearLimitLabel = new JLabel("Year to: ");
        brandLabel = new JLabel("Brand: ");
        modelLabel = new JLabel("Model: ");
        lowerHorsepowerLimitLabel = new JLabel("Lower horsepower limit: ");
        upperHorsepowerLimitLabel = new JLabel("Upper horsepower limit: ");
        mileageLimitLabel = new JLabel("Upper mileage limit: ");
        
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
        for(int i = 0; i < numPairs; i++){
            layout.putConstraint(SpringLayout.WEST, labels[i], 5, SpringLayout.WEST, form);
            layout.putConstraint(SpringLayout.NORTH, labels[i] , 25*i, SpringLayout.NORTH, form);
            labels[i].setHorizontalTextPosition(JLabel.TRAILING);
            form.add(labels[i]);
            txtFields[i].setColumns(10);
            layout.putConstraint(SpringLayout.WEST, txtFields[i], 150, SpringLayout.WEST, form);
            layout.putConstraint(SpringLayout.NORTH, txtFields[i] , 25*i, SpringLayout.NORTH, form);
            form.add(txtFields[i]);
        }
        layout.putConstraint(SpringLayout.WEST,  searchButton, 100, SpringLayout.WEST, form);
        layout.putConstraint(SpringLayout.NORTH, searchButton , 25*(numPairs + 1), SpringLayout.NORTH, form);
        form.add(searchButton);
        browse.add(form);
        JScrollPane pane = new JScrollPane();
        printField = new JTextArea(80,150);
        printField.setEditable(false);
        pane.getViewport().setView(printField);
        browse.add(pane);
        
        frame.add(tabs);
        frame.setVisible(true);
     }
     

     public void clearFields(){
        lowerPriceLimitTextField.setText("");
        upperPriceLimitTextField.setText("");
        lowerYearLimitTextField.setText("");
        upperYearLimitTextField.setText("");
        brandTextField.setText("");
        modelTextField.setText("");
        lowerHorsepowerLimitTextField.setText("");
        upperHorsepowerLimitTextField.setText("");
        mileageLimitTextField.setText("");
     }
     
     public void noResultsWarning(){
         printField.setText("There are no results for this search");
     }
     
     public void somethingWentWrong(){
         printField.setText("Something went wrong");
     }
     public void printCar(Car car){
         printField.append("ID: " + String.valueOf(car.getId())+"\n");
         printField.append("Brand: " + car.getBrand() +"\n");
         printField.append("Model: " + car.getModel() +"\n");
         printField.append("Year: " + String.valueOf(car.getYear())+"\n");
         printField.append("Price: " + String.valueOf(car.getPrice())+"\n");
         printField.append("Horsepower: " + String.valueOf(car.getHorsepower())+"\n");
         printField.append("Mileage: " + String.valueOf(car.getMileage())+"\n\n");
     }
     public JButton search(){
         return searchButton;
     }
     
     public void clearTextArea(){
         printField.setText("");
     }
     
     public JFormattedTextField getLowerPriceLimitTextField(){
         return lowerPriceLimitTextField;
     } 
     
     public JFormattedTextField getUpperPriceLimitTextField(){
         return upperPriceLimitTextField;
     }
     
     public JFormattedTextField getLowerYearLimitTextField(){
         return lowerYearLimitTextField;
     }
     
     public JFormattedTextField getUpperYearLimitTextField(){
         return  upperYearLimitTextField;
     }
     
     public JTextField getBrandTextField(){
         return brandTextField;
     }
     
     public JTextField getModelTextField(){
        return modelTextField; 
     }
     
     public JFormattedTextField getLowerHorsepowerLimitTextField(){
        return lowerHorsepowerLimitTextField;
     }
     
     public JFormattedTextField getUpperHorsepowerLimitTextField(){
        return upperHorsepowerLimitTextField;
     }
     
     public JFormattedTextField getMileageLimitTextField(){
         return mileageLimitTextField;
     }
     
     
     public JFrame getFrame() {
        return frame;
     }
     public void setFrame(JFrame frame) {
        this.frame = frame;
     }
     /*public JLabel getFirstnameLabel() {
        return firstnameLabel;
     }
     public void setFirstnameLabel(JLabel firstnameLabel) {
        this.firstnameLabel = firstnameLabel;
     }
     public JLabel getLastnameLabel() {
        return lastnameLabel;
     }
     public void setLastnameLabel(JLabel lastnameLabel) {
        this.lastnameLabel = lastnameLabel;
     }
     public JTextField getFirstnameTextfield() {
        return firstnameTextfield;
     }
     public void setFirstnameTextfield(JTextField firstnameTextfield) {
        this.firstnameTextfield = firstnameTextfield;
     }
     public JTextField getLastnameTextfield() {
        return lastnameTextfield;
     }
     public void setLastnameTextfield(JTextField lastnameTextfield) {
        this.lastnameTextfield = lastnameTextfield;
     }  
     public JButton getFirstnameSaveButton() {
        return firstnameSaveButton;
     }
     public void setFirstnameSaveButton(JButton firstnameSaveButton) {
        this.firstnameSaveButton = firstnameSaveButton;
     }
     public JButton getLastnameSaveButton() {
        return lastnameSaveButton;
     }
     public void setLastnameSaveButton(JButton lastnameSaveButton) {
        this.lastnameSaveButton = lastnameSaveButton;
     }
     public JButton getHello() {
        return hello;
     }
     public void setHello(JButton hello) {
        this.hello = hello;
     }*/
}