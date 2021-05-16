/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;
import javax.swing.JOptionPane;
import model.Enkripsi;
import model.Kasir;
import view.LihatKasir;
import view.FormUtama;

/**
 *
 * @author Widia Novita
 */
public class KasirController {
    private final Kasir kasir = new Kasir();
    private LihatKasir lihatKasir = new LihatKasir(null,true);
    private final Enkripsi enkripsi = new Enkripsi();
    private boolean hashed = false;

    public void setHashed(boolean hashed) {
        this.hashed = hashed;
    }
    
    public void simpan(javax.swing.JTextField idTextField, javax.swing.JTextField namaTextField, javax.swing.JPasswordField passwordField){
        if (!idTextField.getText().equals("")){
            kasir.setId(idTextField.getText());
            kasir.setNama(namaTextField.getText());
            
            if (hashed){
                kasir.setPassword(new String(passwordField.getPassword()));
            } else {
                try {
                    kasir.setPassword(enkripsi.hashMD5(new String(passwordField.getPassword())));
                } catch (Exception ex){
                    kasir.setPassword("");
                }
            }
            
            if (kasir.simpan()){
                FormUtama.formKasir.setId("");
                FormUtama.formKasir.setNama("");
                FormUtama.formKasir.setPassword("");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Id tidak boleh kosong", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void hapus(javax.swing.JTextField idTextField){
        if (!idTextField.getText().equals("")){
            if (kasir.hapus(idTextField.getText())){
                FormUtama.formKasir.setId("");
                FormUtama.formKasir.setNama("");
                FormUtama.formKasir.setPassword("");
            }else {
                JOptionPane.showMessageDialog(null, kasir.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Id tidak boleh kosong", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void cari(javax.swing.JTextField idTextField){
        if (!idTextField.getText().equals("")){
            if (kasir.baca(idTextField.getText())){
                FormUtama.formKasir.setNama(kasir.getNama());
                FormUtama.formKasir.setPassword(kasir.getPassword());
                hashed = true;
            } else {
                FormUtama.formKasir.setNama("");
                FormUtama.formKasir.setPassword("");
                
                JOptionPane.showMessageDialog(null, kasir.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Id tidak boleh kosong", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void cari1(javax.swing.JTextField idTextField){
        if (!idTextField.getText().equals("")){
            if (kasir.baca(idTextField.getText())){
                FormUtama.formTransaksi.setNama(kasir.getNama());
            } else {
                FormUtama.formTransaksi.setNama("");
                
                JOptionPane.showMessageDialog(null, kasir.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Id tidak boleh kosong", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void tampilkanFormLihatKasir(){
        if (!lihatKasir.isVisible())
        {
            LihatKasir.listKasir = kasir.bacaLihat(); 
            lihatKasir = new LihatKasir(null, true); 
            lihatKasir.setVisible(true); 
            if (!LihatKasir.idDipilih.equals("")) 
            {
                if (kasir.baca(LihatKasir.idDipilih))
                {
                    FormUtama.formKasir.setId(kasir.getId());
                    FormUtama.formKasir.setNama(kasir.getNama());
                    FormUtama.formKasir.setPassword(kasir.getPassword()); 
                } 
            }
        }
    }
}
