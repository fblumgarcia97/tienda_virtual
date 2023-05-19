/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fblumgarcia.model;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
     * <h1>DataBase</h1>
     * <p> Clase en la que se maneja la base de datos es decir se hace el CRUD
     * @author fblumgarcia
     * https://github.com/fblumgarcia
     * 
     */
public class DataBase {   
   private final String dir="jdbc:mysql://localhost/storefbgdb";
   private final String usDB="root";
   private final String pwDB="";
   
   public boolean tryLibrary(){
       boolean tryLibrary=false;
       try {
            Class.forName("com.mysql.cj.jdbc.Driver");//Verifica libreria instalada
            tryLibrary=true;
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
       return tryLibrary;
   }
   //Trabaja la tabla de los usuarios
    public boolean CreateUser(String name,String email,String password){//Se hace la validación de la creación del usuario
        boolean isCreated=false;
        tryLibrary();
        try(Connection conn=DriverManager.getConnection(dir,usDB,pwDB)){
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (name,email,password,usertype) VALUES ('"+name+"','"+email+"','"+password+"','CLIENTE')");
            stmt.executeUpdate();
            conn.close();
            isCreated=true;
        }catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return isCreated;
    }
    public String[] LoginUser(String email){
        String[] user={null,null,null,null};
        tryLibrary();
        try(Connection conn=DriverManager.getConnection(dir,usDB,pwDB)){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE email='"+email+"'");
            rs.next();
            user[0]=rs.getString("name");user[1]=rs.getString("email");
            user[2]=rs.getString("password");user[3]=rs.getString("usertype");
            conn.close();
        } catch (SQLException ex) {        
           Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
       }    
        return user;
    }
    
    public boolean UpdateUser(String email1,String email2,String password){
        boolean isUpdated=false;
        tryLibrary();
        try(Connection conn=DriverManager.getConnection(dir,usDB,pwDB)){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE email='"+email1+"'");
            rs.next();
            if(email2 == null){//Si no cambia el correo 2 se pone el mismo correo anterior
                email2=email1;                
            }
            if(password == null){//Si no cambia el password se pone el mismo password anterior
                password=rs.getString("password");
            }
            stmt.executeUpdate("UPDATE users SET email='"+email2+"', password='"+password+"' WHERE email='"+email1+"'");
            conn.close();
            isUpdated=true;
        } catch (SQLException ex) {
           Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isUpdated;
    }
    //Trabaja la tabla de productos
    
    public boolean UpdateBuy(String id, String quantity){
        boolean isUpdate=false;
        tryLibrary();
        try(Connection conn=DriverManager.getConnection(dir,usDB,pwDB)){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM products WHERE id='"+id+"'");
            rs.next();    
            int nq=rs.getInt("quantity")-(Integer.parseInt(quantity));
            if(nq>=0){
                stmt.executeUpdate("UPDATE products SET quantity='"+nq+"' WHERE id='"+id+"'");
                isUpdate=true;
            }
            
        }  catch (SQLException ex) {
               Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
           }
     return isUpdate; 
    }
        
    public boolean CreateProduct(String name,int price,int quantity,FileInputStream image,String description){
        boolean isCreated=false;
        tryLibrary();
        try(Connection conn=DriverManager.getConnection(dir,usDB,pwDB)){
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO products (name,price,quantity,image,description) VALUES (?,?,?,?,?)");
            stmt.setString(1, name);stmt.setString(5, description);//Guarda la info
            stmt.setInt(2, price);stmt.setInt(3, quantity);stmt.setBlob(4, image);//Pasa el FileInputStream a blob
            stmt.executeUpdate();
            conn.close();
            isCreated=true;
        }catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isCreated;
    }
    
    public ArrayList SearchProduct(String name){
        tryLibrary();
        ArrayList products=new ArrayList();
        try(Connection conn=DriverManager.getConnection(dir,usDB,pwDB)){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM products WHERE name LIKE'%"+name+"%'");//Se ace la busqueda
            rs.next();
            do{
                //Se añade toda la info dentro de un arrayList 
                products.add(rs.getString("id"));products.add(rs.getString("name"));products.add(rs.getString("price"));
                products.add(rs.getString("quantity"));products.add(rs.getString("description"));products.add(rs.getBlob("image"));
            }while(rs.next());
            conn.close();
        }  catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
       }
        return products;
    }

    public boolean UpdateProduct(String id,String name,String price,String quantity,String description){
        boolean isUpdated=false;
        tryLibrary();
        try(Connection conn=DriverManager.getConnection(dir,usDB,pwDB)){
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE products SET name='"+name+"', price='"+price+"', quantity='"+quantity+"', description='"+description+"' WHERE id='"+id+"'");
            conn.close();
            isUpdated=true;
       
        }  catch (SQLException ex) {
           Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
     return isUpdated;
    }
    
    public boolean DeleteProduct(String id){
         boolean isDeleted=false;
        tryLibrary();
        try(Connection conn=DriverManager.getConnection(dir,usDB,pwDB)){
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM products WHERE id='"+id+"' LIMIT 1");
            conn.close();
            isDeleted=true;
       
        }  catch (SQLException ex) {
           Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
           JOptionPane.showMessageDialog(null,"No se pudo actualizar el producto con id: "+id);
        }
        return isDeleted;    
    }
    
}//Cierra toda la calse
