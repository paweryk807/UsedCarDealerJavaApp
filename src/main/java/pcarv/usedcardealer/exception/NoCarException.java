/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcarv.usedcardealer.exception;

/**
 *
 * @author Paweł Rykała
 * @version 1.0
 */
public class NoCarException extends Exception {
    public NoCarException(String errorMessage){
        super(errorMessage);
    }
}
