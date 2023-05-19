/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fblumgarcia.model;

import com.fblumgarcia.ui.UISProducts;
import com.mysql.cj.jdbc.Blob;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


    /**
     * <h1>Product</h1>
     * <p> Formato visual para acceder a la búsqueda de los productos
     * @author fblumgarcia
     * https://github.com/fblumgarcia
     * 
     */
public class Product {
    
    public ArrayList SearchProduct(String search){
        ArrayList products=new ArrayList();
        DataBase sP = new DataBase();   //Llama la base de datos   
        products=sP.SearchProduct(search.toUpperCase());//Ejecuta la busqueda
        for(int i=0;i<products.size()/6;i++){
            //Se convierte el archivo blob a ImageIcon
            Blob blob=(Blob) products.get(6*(i+1)-1);
            try {
                int blobLength=(int) blob.length();
                byte[] bytes=blob.getBytes(1, blobLength);
                blob.free();
                BufferedImage img = ImageIO.read(new ByteArrayInputStream(bytes));
                ImageIcon icon = new ImageIcon(img); 
                products.set(6*(i+1)-1, icon);//Reemplaza en la misma posición la imágen
            } catch (SQLException | IOException ex) {
                Logger.getLogger(UISProducts.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        return products;
    }
     
}
