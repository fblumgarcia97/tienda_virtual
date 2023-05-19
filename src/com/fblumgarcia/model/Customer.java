/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fblumgarcia.model;

import java.util.regex.*;

/**
     * <h1>Customer</h1>
     * <p> Clase donde se crea los usuarios y se hace una validación de las passwords
     * @author fblumgarcia
     * https://github.com/fblumgarcia
     * 
     */
public class Customer extends User{
    Validations val=new Validations();
    
    public Customer (String email,String password){
        super(email,password);
    }
    public String Registry(String name,String password2){
        String msg = null;//Inicializa variable mensaje
        if(true==val.valEmail(email)){
            if(password.equals(password2)){//Compara las dos contraseñas
                DataBase create=new DataBase();//Se llama la creación del usuario
                if(create.CreateUser(name,email,password)==true){//Verifica que haya salido todo bien
                    msg="Bienvenido "+name+" a STOREFBG";
                }
            }else{//Si las 2 contraseñas no son iguales
                msg="Verífique datos ingresados";
            }
        }else{//Si da un correo que no tiene la forma que es
            msg="Verífique datos ingresados";
        }        
        return msg;
    }   

}
