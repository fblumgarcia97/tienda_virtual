/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.fblumgarcia.ui;

import com.fblumgarcia.model.DataBase;
import com.fblumgarcia.model.Product;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
     * <h1>UIMPmodify</h1>
     * <p> Formato visual para acceder al modificador del producto
     * @author fblumgarcia
     * https://github.com/fblumgarcia
     * 
     */
public class UIMPmodify extends javax.swing.JPanel {

    /**
     * Creates new form UIMPmodify
     */
    public UIMPmodify() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableInfo = new javax.swing.JTable();
        searchTF = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        changeBtn = new javax.swing.JButton();

        tableInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "PRECIO", "CANTIDAD", "DESCRIPCION", "IMAGEN", "ELIMINAR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, ImageIcon.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableInfo);

        searchBtn.setText("Buscar");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        changeBtn.setText("Realizar Cambios");
        changeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(searchTF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(searchBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(changeBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(searchTF, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchBtn)
                .addGap(106, 106, 106)
                .addComponent(changeBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        Product sP = new Product();   //Llama la base de datos   
        ArrayList prods=sP.SearchProduct(searchTF.getText().toUpperCase());//Ejecuta la busqueda
        DefaultTableModel model=(DefaultTableModel) tableInfo.getModel();//Se instancia la tabla
        model.setRowCount(0); //Se borra todas las filas
        for(int i=0;i<prods.size()/6;i++){
            Object[] row=new Object[7];//Se crea una array de la fila
            int k=0;//Es usado para ubicar dentro del array de la fila
            for(int j=i*6;j<(6*(i+1))-1;j++){//El j ya que inicia en 0 6 12..., y termina en 4 10...
                row[k]=(String) prods.get(j);//Se añade al array row de las 5 primeras columnas
                k++;//Se da el incremento del k
            }           
             row[5]=(Icon) prods.get(6*(i+1)-1);  
             row[6]=false;
                model.addRow(row);//Se añade a la fila dentro de la tabla
                tableInfo.setRowHeight(i, 200);//Dar la altura de la imagen a la fila
            
        }
    }//GEN-LAST:event_searchBtnActionPerformed

    private void changeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeBtnActionPerformed
        DataBase mP = new DataBase();
        for(int i=0;i<tableInfo.getRowCount();i++){
            if(!(boolean) tableInfo.getValueAt(i, 6).equals(true)){
                boolean checkUpd=mP.UpdateProduct((String) tableInfo.getValueAt(i, 0), ((String) tableInfo.getValueAt(i, 1)).toUpperCase(), (String) tableInfo.getValueAt(i, 2), (String) tableInfo.getValueAt(i, 3), ((String) tableInfo.getValueAt(i, 4)).toUpperCase());
                if(checkUpd==false){
                    JOptionPane.showMessageDialog(null,"No se pudo actualizar el producto: "+(String) tableInfo.getValueAt(i, 1));
                }                
            }else{
                boolean checkUpd=mP.DeleteProduct((String) tableInfo.getValueAt(i, 0));
                if(checkUpd==false){
                    JOptionPane.showMessageDialog(null,"No se pudo actualizar el producto: "+(String) tableInfo.getValueAt(i, 1));
                }    
            }
        }
        JOptionPane.showMessageDialog(null,"Productos actualizados");
    }//GEN-LAST:event_changeBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton changeBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField searchTF;
    private javax.swing.JTable tableInfo;
    // End of variables declaration//GEN-END:variables
}