/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcarv.usedcardealer.main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import pcarv.usedcardealer.model.Car;
import pcarv.usedcardealer.model.CarList;
import pcarv.usedcardealer.view.View;
import pcarv.usedcardealer.controller.Controller;

/**
 * @author bambe
 */
public class Main {
    private static String filename; 
    /**
     @param args filename 
     */
    public static void main(String[] args){
        filename = "";
        JFrame frame = new JFrame();
        JDialog dialog = new JDialog();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        if(args.length == 0){
            try{
                while(!filename.contains(".txt")){
                    filename = JOptionPane.showInputDialog("Enter the file path");
                        
                }
                JOptionPane.showMessageDialog(frame, "Choosen file: " + filename);
                frame.pack();
                frame.setVisible(true);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        if(args.length == 1 || !filename.isEmpty()){
            if(args.length == 1)
                filename = args[0];
            
            System.out.println("Database file : " + filename);
            CarList model = new CarList();
            model = getCarsFromDatabase(filename);
            View view = new View("UsedCarsDealer");
            Controller controller = new Controller(model,view);
            controller.initController();

        }
        else{
            System.out.println("Incorrect number of parameters specified");
        }
        
    }
    /**
     @param filename - file in .txt format containing the cars like in database
     */
    private static CarList getCarsFromDatabase(String filename){
        try{
            CarList carList = new CarList();
            File file = new File(filename);
            Scanner reader = new Scanner(file);
            reader.useDelimiter("\r\n");
            while(reader.hasNext()){ 
               String line = reader.next();
               if(line.trim().isEmpty()){
                   continue;
               }
               String cells[] = line.split(";");
               Car tmp = new Car();
               if (cells.length == 7) {
                    tmp.setId(Integer.parseInt(cells[0]));
                    tmp.setBrand(cells[1]);
                    tmp.setModel(cells[2]);          
                    tmp.setHorsepower(Integer.parseInt(cells[3]));
                    tmp.setMileage(Integer.parseInt(cells[4]));
                    tmp.setPrice(Float.parseFloat(cells[5]));
                    tmp.setYear(Integer.parseInt(cells[6]));
               }
               carList.add(tmp);
            }
            return carList;
        } catch (IOException exc){
            exc.printStackTrace();
        }
        return null;
    }
}
