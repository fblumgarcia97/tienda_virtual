package com.fblumgarcia.main;


import java.awt.List;
import java.util.ArrayList;
import com.fblumgarcia.ui.UILogin;

/**
 *<h1>Store FBG</h1>
 * <p> 
 * Es una aplicación que simula una tienda, con la posibildad de que los compradores se puedan registrar,
 * ingresar. Adentro pueden buscar productos, seleccionar varios y añadirlos al carrito de compra, luego 
 * ejecutar la compra. Conectado a una base de datos para para irse actualizando
 * @author fblumgarcia
 * https://github.com/fblumgarcia
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UILogin login=new UILogin();
        login.setVisible(true);       
    }
    
}
