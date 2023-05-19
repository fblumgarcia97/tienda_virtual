/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fblumgarcia.model;

/**
     * <h1>User</h1>
     * <p> Clase para verificar el login sea correcto
     * @author fblumgarcia
     * https://github.com/fblumgarcia
     * 
     */
public class User {
    String password,email,name;
    public User(String email,String password){
        this.email=email;this.password=password;   
    }
    

    
    public String[] Login(){
        String[] msg={null,null,null,null};
        Validations val=new Validations();
        DataBase log=new DataBase();
        String[] user=log.LoginUser(email);
        if(true==val.valEmail(email)){
            if(user[0]!=null){//Verifica que exista ese usuario en la DB
                if(password.equals(user[2])){//Verifica que la contraseña coincida
                    msg[0]=user[0];msg[1]=user[1];msg[2]="Bienvenido "+user[0]+" a StoreFBG";msg[3]=user[3];//Crea un array con nombre, email,tipo usuario y un mensaje 
                }else{msg[2]="Verífique los datos ingresados";}//Crea array con mensaje
            }else{msg[2]="Verífique los datos ingresados";}
        }else{msg[2]="Verífique los datos ingresados";}
        return msg;
    }
}
