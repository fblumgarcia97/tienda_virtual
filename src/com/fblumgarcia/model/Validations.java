/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fblumgarcia.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
     * <h1>Validations</h1>
     * <p> Clase en la que se verifia que lo que ingresa el usuario esta bien
     * @author fblumgarcia
     * https://github.com/fblumgarcia
     * 
     */
public class Validations {
    public boolean valEmail(String email){//Validación de que tiene forma de correo
        boolean ans = false;
        String regx="^[A-Za-z0-9+_.-]+@(.+)$";//Regular expression
        Pattern pattern=Pattern.compile(regx);//Compile regular expression to get the pattern
        Matcher matcher=pattern.matcher(email); //Create instance of matcher
        if(true==matcher.matches()){
            ans=true;
        }
        return ans; 
    }
    public boolean OnlyNumbers(char character){
        boolean isNumber=false;
        if(((character < '0') ||(character > '9')) &&(character != '\b' /*corresponde a BACK_SPACE*/)){
            isNumber=true;  // ignorar el evento de teclado
        } 
        return isNumber;
    }
}
