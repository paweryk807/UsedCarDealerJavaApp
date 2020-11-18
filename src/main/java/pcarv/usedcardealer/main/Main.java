/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcarv.usedcardealer.main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import pcarv.usedcardealer.model.Car;
import pcarv.usedcardealer.model.CarList;
import pcarv.usedcardealer.view.View;
import pcarv.usedcardealer.controller.Controller;

/**
 * Prepares the program for action. Validates input parameters. Initializes the
 * database, controller and view. Passes database to model.
 *
 * @author Paweł Rykała
 * @version 2.0
 */
public class Main {

    private static String filename;

    /**
     * Method which initiates program.
     * @param args optional argument with the path to the file.
     */
    public static void main(String[] args) {

        filename = "";
        boolean readyToWork = false;
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (args.length == 0) {
            try {
                filename = JOptionPane.showInputDialog("Enter the file path");
                if (filename.contains(".txt")) {
                    JOptionPane.showMessageDialog(frame, "Selected database file: " + filename);
                    readyToWork = true;
                } else {
                    JOptionPane.showMessageDialog(frame, "database file not specified", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NullPointerException e) {
            }
        } else if (args.length == 1) {
            filename = args[0];
            if (filename.contains(".txt")) {
                JOptionPane.showMessageDialog(frame, "Selected database file: " + filename);
                readyToWork = true;
            } else {
                JOptionPane.showMessageDialog(frame, "database file not specified", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (readyToWork) {
            File file = new File(filename);

            final CarList model = initDb(file);
            if(model!=null){
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    saveDb(file, model);
                }
            });
            View view = new View("UsedCarsDealer", frame);
            Controller controller = new Controller(model, view);
            controller.initController();
            }
            else{
                JOptionPane.showMessageDialog(frame, "Incorrect parameters specified", "Error", JOptionPane.ERROR_MESSAGE);
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Incorrect number of parameters specified", "Error", JOptionPane.ERROR_MESSAGE);
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }

    }

    /**
     * A method that loads data from a file into the model.
     *
     * @param file - file containing the cars data - demo database
     */
    private static CarList initDb(File file) {
        try {
            CarList carList = new CarList();
            Scanner reader = new Scanner(file);
            reader.useDelimiter("\r\n");
            while (reader.hasNext()) {
                String line = reader.next();
                if (line.trim().isEmpty()) {
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
        } catch (IOException exc) {
        }
        return null;
    }

    /**
     * A method that save data from a model into the file.
     *
     * @param file - the file from which data was loaded - demo database
     * @param mode - the data the application was running on
     */
    private static void saveDb(File file, CarList model) {
        FileWriter fw = null;
        try {
            String lineToWrite = "";
            fw = new FileWriter(file);
            for (Car auto : model.getList()) {
                lineToWrite = String.valueOf(auto.getId()) + ";"
                        + auto.getBrand() + ";"
                        + auto.getModel() + ";"
                        + String.valueOf(auto.getHorsepower()) + ";"
                        + String.valueOf(auto.getMileage()) + ";"
                        + String.valueOf(auto.getPrice()) + ";"
                        + String.valueOf(auto.getYear()) + "\r\n";
                fw.write(lineToWrite);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
