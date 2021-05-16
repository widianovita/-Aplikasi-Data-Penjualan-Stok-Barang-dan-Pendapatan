/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;
import javax.swing.JOptionPane;
import model.Enkripsi;
import model.Kasir;
import model.Karyawan;
import view.FormLogin;

/**
 *
 * @author Widia Novita
 */
public class LoginController {
    private final Karyawan karyawan = new Karyawan();
    private final Kasir kasir = new Kasir();
    private final Enkripsi enkripsi = new Enkripsi();
    
    public boolean validasi(javax.swing.JTextField userIdTextField, javax.swing.JPasswordField passwordField){
        boolean valid = false, userIdSalah=false;
        String hashedInputPassword = "";
        
        if (!userIdTextField.getText().equals("")){
            
            if (!valid){
                if (karyawan.baca(userIdTextField.getText())){
                   try {
                        hashedInputPassword = enkripsi.hashMD5(new String(passwordField.getPassword()));
                    } catch (Exception ex){}
                
                    if (karyawan.getPassword().equalsIgnoreCase(hashedInputPassword)){
                        valid = true;
                        FormLogin.tipe = "Karyawan";
                    } else {
                        userIdSalah = true;
                    }
                }
                
                if (kasir.baca(userIdTextField.getText())){
                    try {
                        hashedInputPassword = enkripsi.hashMD5(new String(passwordField.getPassword()));
                    } catch (Exception ex){}
                    
                    if (kasir.getPassword().equalsIgnoreCase(hashedInputPassword)){
                        valid = true;
                        FormLogin.tipe = "Kasir";
                    } else {
                        userIdSalah = true;
                    }
                }
                
                if (!valid){
                    if (userIdSalah){
                        JOptionPane.showMessageDialog(null, "User Id atau password salah", "Kesalahan", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, kasir.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            
        
        } else {
            JOptionPane.showMessageDialog(null, "User Id (Id) tidak boleh kosong", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
        
        
        
        return valid;
    }
} 
    