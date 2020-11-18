/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcarv.usedcardealer.exception;

/**
 * The class representing the exception 
 * used to handle unsafe behavior in the model.
 * 
 * @author Paweł Rykała
 * @version 1.0
 */
public class NoCarException extends Exception {
   
    
    /**
     * Initiates an object.
     * @param errorMessage message to set.
     */
    public NoCarException(String errorMessage){
        super(errorMessage);
    }
}
