/*
 * To change this template, choose Tools | Templates
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
public class Karyawan {
    private String id_karyawan, nama, password;

    public String getId() {
        return id_karyawan;
    }

    public void setId(String id_karyawan) {
        this.id_karyawan = id_karyawan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
		String SQLStatemen = "select * from karyawan where id_karyawan='"+id_karyawan+"'"; 
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
                        SQLStatemen = "update karyawan set nama_karyawan='"+nama+ "', password='"+password+ "' where id_karyawan='"+id_karyawan+"'";
                        sta = cn.createStatement();
                        jumlahSimpan = sta.executeUpdate(SQLStatemen); 
                    } 
		} 
		else
                {
                    sta.close();
                    rset.close();
                    simpan = true;
                    SQLStatemen = "insert into karyawan(id_karyawan, nama_karyawan, password) values ('"+ id_karyawan +"','"+ nama +"','"+ password +"')"; 
                    sta = cn.createStatement();
                    jumlahSimpan = sta.executeUpdate(SQLStatemen); 
		}
			
		if (simpan) 
		{
                    if (jumlahSimpan > 0)
                    {
                        JOptionPane.showMessageDialog(null,"Data karyawan sudah tersimpan","Informasi",JOptionPane.INFORMATION_MESSAGE); 
                    } 
                    else
                    {
                        adaKesalahan = true;
                        JOptionPane.showMessageDialog(null,"Gagal menyimpan data karyawan","Kesalahan",JOptionPane.ERROR_MESSAGE);
                    } 
		} 
            } catch (SQLException | HeadlessException ex){
                adaKesalahan = true;
                JOptionPane.showMessageDialog(null,"Tidak dapat membuka tabel karyawan\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE); 
		} 
	} 
    } 
    return !adaKesalahan;   
    }
    
    public boolean baca(String id_karyawan)
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
                    String SQLStatemen = "select * from karyawan where id_karyawan='"+id_karyawan+"'";
                    Statement sta = cn.createStatement();
                    ResultSet rset = sta.executeQuery(SQLStatemen);
                    rset.next();
                    if (rset.getRow()>0)
                    {
                        this.id_karyawan = rset.getString("id_karyawan");
                        this.nama = rset.getString("nama_karyawan");
                        this.password = rset.getString("password");
                        sta.close();
                        rset.close();
                    }
                    else
                    {
                        sta.close();
                        rset.close();
                        adaKesalahan = true;
                        JOptionPane.showMessageDialog(null,"Id \""+id_karyawan+"\" tidak ditemukan","Informasi",JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException | HeadlessException ex){
                    adaKesalahan = true;
                    JOptionPane.showMessageDialog(null,"Tidak dapat membuka tabel karyawan\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
                    }
            } 
	} 
	return !adaKesalahan; 
    }
    
    public Object[][] bacaLihat()
    {
        boolean adaKesalahan = false;
        Connection cn = null;
        Object[][] lihatKaryawan = new Object[0][0];
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
                    SQLStatemen = "select id_karyawan, nama_karyawan from karyawan";
                    sta = cn.createStatement();
                    rset = sta.executeQuery(SQLStatemen);
                    rset.next();
                    rset.last();
                    lihatKaryawan = new Object[rset.getRow()][2];
                    if (rset.getRow()>0)
                    {
                        rset.first();
                        int i=0;
                        do { lihatKaryawan[i] = new Object[]{rset.getString("id_karyawan"), rset.getString("nama_karyawan")}; i++; }
			while (rset.next());
                    }
                    sta.close();
                    rset.close();
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null,"Tidak dapat membuka tabel karyawan\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
                    }
            } 
	} 
	return lihatKaryawan; 
    }

    public boolean hapus(String id_karyawan)
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
                    String SQLStatemen = "delete from karyawan where id_karyawan='"+id_karyawan+"'";
                    Statement sta = cn.createStatement();
                    jumlahHapus = sta.executeUpdate(SQLStatemen);
                    if (jumlahHapus>0)
                    {
                        sta.close(); 
                        JOptionPane.showMessageDialog(null,"Data karyawan dengan Id "+id_karyawan+" sudah dihapus","Informasi",JOptionPane.INFORMATION_MESSAGE);
                    } 
                    else 
                    {
                        sta.close();
                        JOptionPane.showMessageDialog(null,"Data karyawan dengan Id "+id_karyawan+" tidak ditemukan","Informasi",JOptionPane.INFORMATION_MESSAGE); 
                        adaKesalahan = true; 
                    }
                } catch (SQLException | HeadlessException ex){
                    adaKesalahan = true;
                    JOptionPane.showMessageDialog(null,"Tidak dapat membuka tabel karyawan\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
                    }
            }
        } 
	return !adaKesalahan; 
    }

    public Object getPesan() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
