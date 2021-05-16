/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Widia Novita
 */
public class Pembeli {
    private String id, nama, telepon, alamat; 

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public String getTlp() {
        return telepon;
    }

    public void setTlp(String telepon) {
        this.telepon = telepon;
    }
    
    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public boolean simpan(){ 
        boolean adaKesalahan = false; 
        Connection cn = null;
        
        try
        { 
            Class.forName(Koneksi.driver); 
        } catch (Exception ex){ 
            adaKesalahan = true; 
            JOptionPane.showMessageDialog(null,"JDBC Driver tidak ditemukan atau rusak\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
            } 
	
        if (!adaKesalahan)
        { 
            try 
            { 
                cn = DriverManager.getConnection(Koneksi.database+"?user="+Koneksi.user+"&password="+Koneksi.password+"");  
            } catch (Exception ex) { 
                adaKesalahan = true; 
                JOptionPane.showMessageDialog(null,"Koneksi ke "+Koneksi.database+" gagal\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE); 
                } 
            if (!adaKesalahan)
            { 
                int jumlahSimpan=0; 
                boolean simpan = false; 
                try 
                {
                    String SQLStatemen = "select * from pembeli where id_pembeli='"+id+"'"; 
                    Statement sta = cn.createStatement(); 
                    ResultSet rset = sta.executeQuery(SQLStatemen); 
                    rset.next();
                    if (rset.getRow()>0)
                    {
                        sta.close();
                        rset.close();
                        Object[] arrOpsi = {"Ya","Tidak"};
                        int pilih=JOptionPane.showOptionDialog(null,"Id sudah ada\nApakah data diupdate?","Konfirmasi",
                            JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null,arrOpsi,arrOpsi[0]); 
                        if (pilih==0)
                        {
                            simpan = true;
                            SQLStatemen = "update pembeli set nama_pembeli='"+nama+ "', telepon='"+telepon+ "', alamat='"+alamat+ "' where id_pembeli='"+id+"'";
                            sta = cn.createStatement();
                            jumlahSimpan = sta.executeUpdate(SQLStatemen); 
                        } 
                    } 
                    else
                    {
                        sta.close();
                        rset.close();
                        simpan = true;
                        SQLStatemen = "insert into pembeli(id_pembeli, nama_pembeli, telepon, alamat) values ('"+ id +"','"+ nama +"','"+ telepon +"','"+ alamat +"')"; 
                        sta = cn.createStatement();
                        jumlahSimpan = sta.executeUpdate(SQLStatemen); 
                    }
			
                    if (simpan) 
                    {
                        if (jumlahSimpan > 0)
                        {
                            JOptionPane.showMessageDialog(null,"Data pembeli sudah tersimpan","Informasi",JOptionPane.INFORMATION_MESSAGE); 
                        } 
                        else
                        {
                            adaKesalahan = true;
                            JOptionPane.showMessageDialog(null,"Gagal menyimpan data pembeli","Kesalahan",JOptionPane.ERROR_MESSAGE);
                        } 
                    } 
                } catch (SQLException | HeadlessException ex){
                    adaKesalahan = true;
                    JOptionPane.showMessageDialog(null,"Tidak dapat membuka tabel pembeli\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE); 
                    } 
            } 
        } 
        return !adaKesalahan;   
    }
    
    public boolean baca(String id)
    { 
	boolean adaKesalahan = false; 
	Connection cn = null;
			
	try
	{ 
            Class.forName(Koneksi.driver); 
	} catch (Exception ex){ 
            adaKesalahan = true; 
            JOptionPane.showMessageDialog(null,"JDBC Driver tidak ditemukan atau rusak\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
            } 
		
	if (!adaKesalahan)
	{
            try
            {
                cn = DriverManager.getConnection(Koneksi.database+"?user="+Koneksi.user+"&password="+Koneksi.password+"");
            } catch (Exception ex) {
                adaKesalahan = true;
                JOptionPane.showMessageDialog(null,"Koneksi ke "+Koneksi.database+" gagal\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
            } 
            
            if (!adaKesalahan)
            {
                try
                {
                    String SQLStatemen = "select * from pembeli where id_pembeli='"+id+"'";
                    Statement sta = cn.createStatement();
                    ResultSet rset = sta.executeQuery(SQLStatemen);
                    rset.next();
                    if (rset.getRow()>0)
                    {
                        this.id = rset.getString("id_pembeli");
                        this.nama = rset.getString("nama_pembeli");
                        this.telepon = rset.getString("telepon");
                        this.alamat = rset.getString("alamat");
                        sta.close();
                        rset.close();
                    }
                    else
                    {
                        sta.close();
                        rset.close();
                        adaKesalahan = true;
                        JOptionPane.showMessageDialog(null,"id_pembeli \""+id+"\" tidak ditemukan","Informasi",JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException | HeadlessException ex){
                    adaKesalahan = true;
                    JOptionPane.showMessageDialog(null,"Tidak dapat membuka tabel pembeli\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
                    }
            } 
	} 
	return !adaKesalahan; 
    }
    
    public Object[][] bacaLihat()
    {
        boolean adaKesalahan = false;
        Connection cn = null;
        Object[][] lihatPembeli = new Object[0][0];
        try
        {
            Class.forName(Koneksi.driver);
        } catch (Exception ex){
            adaKesalahan = true;
            JOptionPane.showMessageDialog(null,"JDBC Driver tidak ditemukan atau rusak\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
            } 
	
        if (!adaKesalahan)
        {
            try
            {
                cn = DriverManager.getConnection(Koneksi.database+"?user="+Koneksi.user+"&password="+Koneksi.password+"");
            } catch (Exception ex) {
                adaKesalahan = true;
                JOptionPane.showMessageDialog(null,"Koneksi ke "+Koneksi.database+" gagal\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
                }
            
            if (!adaKesalahan)
            {
                String SQLStatemen;
                Statement sta;
                ResultSet rset;
                try
                {
                    SQLStatemen = "select id_pembeli, nama_pembeli from pembeli";
                    sta = cn.createStatement();
                    rset = sta.executeQuery(SQLStatemen);
                    rset.next();
                    rset.last();
                    lihatPembeli = new Object[rset.getRow()][2];
                    if (rset.getRow()>0)
                    {
                        rset.first();
                        int i=0;
                        do { lihatPembeli[i] = new Object[]{rset.getString("id_pembeli"), rset.getString("nama_pembeli")}; i++; }
			while (rset.next());
                    }
                    sta.close();
                    rset.close();
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null,"Tidak dapat membuka tabel pembeli\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
                    }
            } 
	} 
	return lihatPembeli; 
    }
    
    public boolean hapus(String id)
    {
        boolean adaKesalahan = false;
	Connection cn = null;
        try
        {
            Class.forName(Koneksi.driver); 
        } catch (Exception ex){
            adaKesalahan = true;
            JOptionPane.showMessageDialog(null,"JDBC Driver tidak ditemukan atau rusak\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
            }
        
        if (!adaKesalahan)
        {
            try 
            {
                cn = DriverManager.getConnection(Koneksi.database+"?user="+Koneksi.user+"&password="+Koneksi.password+"");
            } catch (Exception ex) {
                adaKesalahan = true; 
                JOptionPane.showMessageDialog(null,"Koneksi ke database "+Koneksi.database+" gagal\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE); 
                } 
            if (!adaKesalahan)
            {
                int jumlahHapus;
                try
                {
                    String SQLStatemen = "delete from pembeli where id_pembeli='"+id+"'";
                    Statement sta = cn.createStatement();
                    jumlahHapus = sta.executeUpdate(SQLStatemen);
                    if (jumlahHapus>0)
                    {
                        sta.close(); 
                        JOptionPane.showMessageDialog(null,"Data pembeli dengan id_pembeli "+id+" sudah dihapus","Informasi",JOptionPane.INFORMATION_MESSAGE);
                    } 
                    else 
                    {
                        sta.close();
                        JOptionPane.showMessageDialog(null,"Data pembeli dengan id_pembeli "+id+" tidak ditemukan","Informasi",JOptionPane.INFORMATION_MESSAGE); adaKesalahan = true; 
                    }
                } catch (SQLException | HeadlessException ex){
                    adaKesalahan = true;
                    JOptionPane.showMessageDialog(null,"Tidak dapat membuka tabel pembeli\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
                    }
            }
        } 
	return !adaKesalahan; 
    }
}

