/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JOptionPane;
import model.Enkripsi;
import model.Karyawan;
import view.LihatKaryawan;
import view.FormUtama;

/**
 *
 * @author Widia Novita
 */
public class KaryawanController {
    
    private final Karyawan karyawan = new Karyawan();
    private LihatKaryawan lihatKaryawan = new LihatKaryawan(null,true);
    private final Enkripsi enkripsi = new Enkripsi();
    private boolean hashed = false;

    public void setHashed(boolean hashed) {
        this.hashed = hashed;
    }
    
    public void simpan(javax.swing.JTextField idTextField, javax.swing.JTextField namaTextField, javax.swing.JPasswordField passwordField){
        if (!idTextField.getText().equals("")){
            karyawan.setId(idTextField.getText());
            karyawan.setNama(namaTextField.getText());
            
            if (hashed){
                karyawan.setPassword(new String(passwordField.getPassword()));
            } else {
                try {
                    karyawan.setPassword(enkripsi.hashMD5(new String(passwordField.getPassword())));
                } catch (Exception ex){
                    karyawan.setPassword("");
                }
            }
            
            if (karyawan.simpan()){
                FormUtama.formKaryawan.setId("");
                FormUtama.formKaryawan.setNama("");
                FormUtama.formKaryawan.setPassword("");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Id tidak boleh kosong", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void hapus(javax.swing.JTextField idTextField){
        if (!idTextField.getText().equals("")){
            if (karyawan.hapus(idTextField.getText())){
                FormUtama.formKaryawan.setId("");
                FormUtama.formKaryawan.setNama("");
                FormUtama.formKaryawan.setPassword("");
            }else {
                JOptionPane.showMessageDialog(null, karyawan.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Id tidak boleh kosong", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void cari(javax.swing.JTextField idTextField){
        if (!idTextField.getText().equals("")){
            if (karyawan.baca(idTextField.getText())){
                FormUtama.formKaryawan.setNama(karyawan.getNama());
                FormUtama.formKaryawan.setPassword(karyawan.getPassword());
                hashed = true;
            } else {
                FormUtama.formKaryawan.setNama("");
                FormUtama.formKaryawan.setPassword("");
                
                JOptionPane.showMessageDialog(null, karyawan.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Id tidak boleh kosong", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void tampilkanFormLihatKaryawan(){
        if (!lihatKaryawan.isVisible())
        {
            LihatKaryawan.listKaryawan = karyawan.bacaLihat(); 
            lihatKaryawan = new LihatKaryawan(null, true); 
            lihatKaryawan.setVisible(true); 
            if (!LihatKaryawan.idDipilih.equals("")) 
            {
                if (karyawan.baca(LihatKaryawan.idDipilih))
                {
                    FormUtama.formKaryawan.setId(karyawan.getId());
                    FormUtama.formKaryawan.setNama(karyawan.getNama());
                    FormUtama.formKaryawan.setPassword(karyawan.getPassword()); 
                } 
            }
        }
    }
    
    public void tampilkanFormLihatKaryawan1(){
        if (!lihatKaryawan.isVisible())
        {
            LihatKaryawan.listKaryawan = karyawan.bacaLihat(); 
            lihatKaryawan = new LihatKaryawan(null, true); 
            lihatKaryawan.setVisible(true); 
            if (!LihatKaryawan.idDipilih.equals("")) 
            {
                if (karyawan.baca(LihatKaryawan.idDipilih))
                {
                    FormUtama.formPersediaan.setId(karyawan.getId());
                }  
            }
        }
    }
}
