/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;
import javax.swing.JOptionPane;
import model.Penjualan; 
import view.LihatPenjualan; 
import view.FormUtama;

/**
 *
 * @author Widia Novita
 */
public class PenjualanController {
    private Penjualan penjualan = new Penjualan(); 
    private LihatPenjualan lihatPenjualan = new LihatPenjualan(null,true); 
    
public void simpan(javax.swing.JTextField nota, javax.swing.JTextField tgl, javax.swing.JTextField kode, javax.swing.JTextField nama, javax.swing.JComboBox kategori, javax.swing.JTextField jmh, javax.swing.JTextField harga, javax.swing.JTextField diskon, javax.swing.JTextField total){ 
    if (!nota.getText().equals(""))
    {
	penjualan.setNo(nota.getText());
        penjualan.setTgl(tgl.getText());
        penjualan.setKode(kode.getText());
	penjualan.setNama(nama.getText());
	penjualan.setKategori((String) kategori.getSelectedItem());
        penjualan.setJumlah(jmh.getText());
        penjualan.setHarga(harga.getText());
        penjualan.setDiskon(diskon.getText());
        penjualan.setTotal(total.getText());
	if (penjualan.simpan())
	{
            FormUtama.formPenjualan.setNo("");
            FormUtama.formPenjualan.setTgl("");
            FormUtama.formPenjualan.setKode("");
            FormUtama.formPenjualan.setNama(""); 
            FormUtama.formPenjualan.setKategori(""); 
            FormUtama.formPenjualan.setJumlah("");
            FormUtama.formPenjualan.setHarga("");
            FormUtama.formPenjualan.setDiskon("");
            FormUtama.formPenjualan.setTotal("");
	}
    } 
    else 
    {
	JOptionPane.showMessageDialog(null,"Nota tidak boleh kosong\n","Kesalahan",JOptionPane.ERROR_MESSAGE);
    } 
} 

public void hapus(javax.swing.JTextField nota){ 
    if (!nota.getText().equals(""))
    {
	if (penjualan.hapus(nota.getText()))
	{
            FormUtama.formPenjualan.setNo("");
            FormUtama.formPenjualan.setTgl("");
            FormUtama.formPenjualan.setKode("");
            FormUtama.formPenjualan.setNama(""); 
            FormUtama.formPenjualan.setKategori(""); 
            FormUtama.formPenjualan.setJumlah("");
            FormUtama.formPenjualan.setHarga("");
            FormUtama.formPenjualan.setDiskon("");
            FormUtama.formPenjualan.setTotal(""); 
	}
    } 
    else 
    {
	JOptionPane.showMessageDialog(null,"Nota tidak boleh kosong\n","Kesalahan",JOptionPane.ERROR_MESSAGE); 
    }
} 

public void cari(javax.swing.JTextField nota){ 
    if (!nota.getText().equals(""))
    {
	if (penjualan.baca(nota.getText()))
	{
            FormUtama.formPenjualan.setTgl(penjualan.getTgl());
            FormUtama.formPenjualan.setKode(penjualan.getKode());
            FormUtama.formPenjualan.setNama(penjualan.getNama());
            FormUtama.formPenjualan.setKategori(penjualan.getKategori());
            FormUtama.formPenjualan.setJumlah(penjualan.getJumlah());
            FormUtama.formPenjualan.setHarga(penjualan.getHarga());
            FormUtama.formPenjualan.setDiskon(penjualan.getDiskon());
            FormUtama.formPenjualan.setTotal(penjualan.getTotal());
	}
	else 
	{
            FormUtama.formPenjualan.setTgl("");
            FormUtama.formPenjualan.setKode("");
            FormUtama.formPenjualan.setNama(""); 
            FormUtama.formPenjualan.setKategori(""); 
            FormUtama.formPenjualan.setJumlah("");
            FormUtama.formPenjualan.setHarga("");
            FormUtama.formPenjualan.setDiskon("");
            FormUtama.formPenjualan.setTotal(""); 
	}
        
    } 
    else 
    {
	JOptionPane.showMessageDialog(null,"Nota tidak boleh kosong\n","Kesalahan",JOptionPane.ERROR_MESSAGE);
    }  
}
 
public void tampilkanLihat(){ 
    if (!lihatPenjualan.isVisible())
    {
	LihatPenjualan.listPenjualan = penjualan.bacaLihat(); 
	lihatPenjualan = new LihatPenjualan(null, true); 
	lihatPenjualan.setVisible(true); 
	if (!LihatPenjualan.noDipilih.equals("")) 
	{
            if (penjualan.baca(LihatPenjualan.noDipilih))
            {
                FormUtama.formTransaksi.setNo(penjualan.getNo());
                FormUtama.formTransaksi.setTotal(penjualan.getTotal());
                FormUtama.formTransaksi.setTgl(penjualan.getTgl());
            } 
	}
    }
}

    public void cetakLaporan(javax.swing.JComboBox kategoriComboBox)
    {
        String no_nota = "";
	penjualan.cetakLaporan(no_nota);
    }
    
}
