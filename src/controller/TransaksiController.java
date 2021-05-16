/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;
import javax.swing.JOptionPane;
import model.Transaksi; 
import view.LihatPenjualan; 
import view.LihatPembeli;
import view.FormUtama;

/**
 *
 * @author Widia Novita
 */
public class TransaksiController {
    private Transaksi transaksi = new Transaksi(); 
    private LihatPenjualan lihatPenjualan = new LihatPenjualan(null,true); 
    private LihatPembeli lihatPembeli = new LihatPembeli(null,true);
    
public void simpan(javax.swing.JTextField nota, javax.swing.JTextField id_pembeli, javax.swing.JFormattedTextField tgl, javax.swing.JTextField total, javax.swing.JTextField bayar, javax.swing.JTextField kembali, javax.swing.JTextField id, javax.swing.JTextField nama){ 
    if (!nota.getText().equals(""))
    {
	transaksi.setNo(nota.getText());
        transaksi.setId_pembeli(id_pembeli.getText());
        transaksi.setTgl(tgl.getText());
	transaksi.setTotal(total.getText());
        transaksi.setBayar(bayar.getText());
        transaksi.setKembali(kembali.getText());
        transaksi.setIdKasir(id.getText());
        transaksi.setNama(nama.getText());
	if (transaksi.simpan())
	{
            FormUtama.formTransaksi.setNo("");
            FormUtama.formTransaksi.setId_pembeli("");
            FormUtama.formTransaksi.setTgl("");
            FormUtama.formTransaksi.setTotal(""); 
            FormUtama.formTransaksi.setBayar(""); 
            FormUtama.formTransaksi.setKembali("");
            FormUtama.formTransaksi.setIdKasir("");
            FormUtama.formTransaksi.setNama("");
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
	if (transaksi.hapus(nota.getText()))
	{
            FormUtama.formTransaksi.setNo("");
            FormUtama.formTransaksi.setId_pembeli("");
            FormUtama.formTransaksi.setTgl("");
            FormUtama.formTransaksi.setTotal(""); 
            FormUtama.formTransaksi.setBayar(""); 
            FormUtama.formTransaksi.setKembali("");
            FormUtama.formTransaksi.setIdKasir("");
            FormUtama.formTransaksi.setNama(""); 
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
	if (transaksi.baca(nota.getText()))
	{
            FormUtama.formTransaksi.setId_pembeli(transaksi.getId_pembeli());
            FormUtama.formTransaksi.setTgl(transaksi.getTgl());
            FormUtama.formTransaksi.setTotal(transaksi.getTotal());
            FormUtama.formTransaksi.setBayar(transaksi.getBayar());
            FormUtama.formTransaksi.setKembali(transaksi.getKembali());
            FormUtama.formTransaksi.setIdKasir(transaksi.getIdKasir());
            FormUtama.formTransaksi.setNama(transaksi.getNama());
	}
	else 
	{
            FormUtama.formTransaksi.setId_pembeli("");
            FormUtama.formTransaksi.setTgl("");
            FormUtama.formTransaksi.setTotal(""); 
            FormUtama.formTransaksi.setBayar(""); 
            FormUtama.formTransaksi.setKembali("");
            FormUtama.formTransaksi.setIdKasir("");
            FormUtama.formTransaksi.setNama(""); 
	}
    } 
    else 
    {
	JOptionPane.showMessageDialog(null,"Nota tidak boleh kosong\n","Kesalahan",JOptionPane.ERROR_MESSAGE);
    }  
}

    public void cetakLaporan(javax.swing.JTextField noTextField)
    {
        String nota = "";
	transaksi.cetakLaporan(nota);
    }
 
}
