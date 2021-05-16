/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;
import javax.swing.JOptionPane;
import model.Pembeli;
import view.FormUtama;
import view.LihatPembeli;

/**
 *
 * @author Widia Novita
 */
public class PembeliController {
    private Pembeli pembeli = new Pembeli(); 
    private LihatPembeli lihatPembeli = new LihatPembeli(null,true);
    
public void simpan(javax.swing.JTextField id, javax.swing.JTextField nama, javax.swing.JTextField telepon, javax.swing.JTextField alamat){ 
    if (!id.getText().equals(""))
    {
	pembeli.setId(id.getText()); 
	pembeli.setNama(nama.getText());
        pembeli.setTlp(telepon.getText());
        pembeli.setAlamat(alamat.getText());
	if (pembeli.simpan())
	{
            FormUtama.formPembeli.setId(""); 
            FormUtama.formPembeli.setNama(""); 
            FormUtama.formPembeli.setTlp(""); 
            FormUtama.formPembeli.setAlamat("");
	}
    } 
    else 
    {
	JOptionPane.showMessageDialog(null,"Id tidak boleh kosong\n","Kesalahan",JOptionPane.ERROR_MESSAGE);
    } 
} 

public void hapus(javax.swing.JTextField id){ 
    if (!id.getText().equals(""))
    {
	if (pembeli.hapus(id.getText()))
	{
            FormUtama.formPembeli.setId("");
            FormUtama.formPembeli.setNama("");
            FormUtama.formPembeli.setTlp("");
            FormUtama.formPembeli.setAlamat(""); 
	}
    } 
    else 
    {
	JOptionPane.showMessageDialog(null,"Id tidak boleh kosong\n","Kesalahan",JOptionPane.ERROR_MESSAGE); 
    }
} 

public void cari(javax.swing.JTextField id){ 
    if (!id.getText().equals(""))
    {
	if (pembeli.baca(id.getText()))
	{
            FormUtama.formPembeli.setNama(pembeli.getNama());
            FormUtama.formPembeli.setTlp(pembeli.getTlp());
            FormUtama.formPembeli.setAlamat(pembeli.getAlamat());
	}
	else 
	{
            FormUtama.formPembeli.setNama("");
            FormUtama.formPembeli.setTlp("");
            FormUtama.formPembeli.setAlamat(""); 
	}
    } 
    else 
    {
	JOptionPane.showMessageDialog(null,"Id tidak boleh kosong\n","Kesalahan",JOptionPane.ERROR_MESSAGE);
    }  
}
 
	
public void tampilkanLihat(){ 
    if (!lihatPembeli.isVisible())
    {
	LihatPembeli.listPembeli = pembeli.bacaLihat(); 
	lihatPembeli = new LihatPembeli(null, true); 
	lihatPembeli.setVisible(true); 
	if (!LihatPembeli.idDipilih.equals("")) 
	{
            if (pembeli.baca(LihatPembeli.idDipilih))
            {
                FormUtama.formPembeli.setId(pembeli.getId());
                FormUtama.formPembeli.setNama(pembeli.getNama());
                FormUtama.formPembeli.setTlp(pembeli.getTlp());
                FormUtama.formPembeli.setAlamat(pembeli.getAlamat());
            }
            
	}
    }
} 

public void tampilkanLihat1(){ 
    if (!lihatPembeli.isVisible())
    {
	LihatPembeli.listPembeli = pembeli.bacaLihat(); 
	lihatPembeli = new LihatPembeli(null, true); 
	lihatPembeli.setVisible(true); 
	if (!LihatPembeli.idDipilih.equals("")) 
	{
            if (pembeli.baca(LihatPembeli.idDipilih))
            {
                FormUtama.formTransaksi.setId_pembeli(pembeli.getId());
            } 
	}
    }
} 

}

