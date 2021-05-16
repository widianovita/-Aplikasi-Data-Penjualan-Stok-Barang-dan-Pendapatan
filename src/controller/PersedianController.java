/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;
import javax.swing.JOptionPane;
import model.Penjualan;
import model.Persedian;
import view.FormPenjualan;
import view.FormPersediaan;
import view.FormUtama;
import view.LihatPersediaan;


/**
 *
 * @author Widia Novita
 */
public class PersedianController {
    private Persedian persediaan = new Persedian();
    private Penjualan penjualan = new Penjualan();
    private LihatPersediaan lihatPersediaan = new LihatPersediaan(null,true);
    
public void simpan(javax.swing.JTextField kode, javax.swing.JTextField nama, javax.swing.JComboBox kategori, javax.swing.JTextField qty, javax.swing.JFormattedTextField tgl, javax.swing.JTextField id){ 
    
    if (!kode.getText().equals(""))
    {
	persediaan.setKodeBarang(kode.getText()); 
	persediaan.setNama(nama.getText());
	persediaan.setKategori((String) kategori.getSelectedItem());
	persediaan.setQty(qty.getText()); 
        persediaan.setTgl(tgl.getText());
        persediaan.setId(id.getText());
	if (persediaan.simpan())
	{
            FormUtama.formPersediaan.setKodeBarang(""); 
            FormUtama.formPersediaan.setNama(""); 
            FormUtama.formPersediaan.setKategori("Jilbab"); 
            FormUtama.formPersediaan.setQty("");
            FormUtama.formPersediaan.setTgl("");
            FormUtama.formPersediaan.setId("");
	}
    } 
    else 
    {
	JOptionPane.showMessageDialog(null,"kode barang tidak boleh kosong\n","Kesalahan",JOptionPane.ERROR_MESSAGE);
    } 
    
     
} 

public void hapus(javax.swing.JTextField kode){ 
    if (!kode.getText().equals(""))
    {
	if (persediaan.hapus(kode.getText()))
	{
            FormUtama.formPersediaan.setKodeBarang(""); 
            FormUtama.formPersediaan.setNama(""); 
            FormUtama.formPersediaan.setKategori("Jilbab"); 
            FormUtama.formPersediaan.setQty("");
            FormUtama.formPersediaan.setTgl(""); 
            FormUtama.formPersediaan.setId("");
	}
    } 
    else 
    {
	JOptionPane.showMessageDialog(null,"Kode tidak boleh kosong\n","Kesalahan",JOptionPane.ERROR_MESSAGE); 
    }
} 

public void cari(javax.swing.JTextField kode){ 
    if (!kode.getText().equals(""))
    {
	if (persediaan.baca(kode.getText()))
	{
            FormUtama.formPersediaan.setNama(persediaan.getNama());
            FormUtama.formPersediaan.setKategori(persediaan.getKategori());
            FormUtama.formPersediaan.setQty(persediaan.getQty());
            FormUtama.formPersediaan.setTgl(persediaan.getTgl());
            FormUtama.formPersediaan.setId(persediaan.getId());
	}
	else 
	{
            FormUtama.formPersediaan.setNama(""); 
            FormUtama.formPersediaan.setKategori("Jilbab"); 
            FormUtama.formPersediaan.setQty("");
            FormUtama.formPersediaan.setTgl("");
            FormUtama.formPersediaan.setId("");
	}
        
        if (persediaan.baca(kode.getText()))
	{
            FormUtama.formPenjualan.setNama(penjualan.getNama());
            FormUtama.formPenjualan.setKategori(penjualan.getKategori());
            FormUtama.formPenjualan.setJumlah(penjualan.getJumlah());
            FormUtama.formPenjualan.setTgl(penjualan.getTgl());
	}
	else 
	{
            FormUtama.formPenjualan.setNama(""); 
            FormUtama.formPenjualan.setKategori("Jilbab"); 
            FormUtama.formPenjualan.setJumlah("");
            FormUtama.formPenjualan.setTgl("");
	}
    } 
    else 
    {
	JOptionPane.showMessageDialog(null,"Kode tidak boleh kosong\n","Kesalahan",JOptionPane.ERROR_MESSAGE);
    }  
}
 	
public void tampilkanLihatPersediaan(){ 
    if (!lihatPersediaan.isVisible())
    {
	LihatPersediaan.listPersediaan = persediaan.bacaLihat(); 
	lihatPersediaan = new LihatPersediaan(null, true); 
	lihatPersediaan.setVisible(true); 
	if (!LihatPersediaan.kodeDipilih.equals("")) 
	{
            if (persediaan.baca(LihatPersediaan.kodeDipilih))
            {
                FormUtama.formPenjualan.setKode(persediaan.getKodeBarang());
                FormUtama.formPenjualan.setNama(persediaan.getNama());
                FormUtama.formPenjualan.setKategori(persediaan.getKategori());
                FormUtama.formPenjualan.setJumlah(persediaan.getQty());
            } 
	}
    }
} 

    public void cetakLaporan(javax.swing.JComboBox kategoriComboBox)
    {
        String nota = "";
	persediaan.cetakLaporan(nota);
    }

}
