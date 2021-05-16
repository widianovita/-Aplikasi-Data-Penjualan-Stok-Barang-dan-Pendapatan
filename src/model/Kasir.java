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
public class Kasir {
    private String id_kasir, nama, password;

    public String getId() {
        return id_kasir;
    }

    public void setId(String id_kasir) {
        this.id_kasir = id_kasir;
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
		String SQLStatemen = "select * from kasir where id_kasir='"+id_kasir+"'"; 
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
                        SQLStatemen = "update kasir set nama='"+nama+ "', password='"+password+ "' where id_kasir='"+id_kasir+"'";
                        sta = cn.createStatement();
                        jumlahSimpan = sta.executeUpdate(SQLStatemen); 
                    } 
		} 
		else
                {
                    sta.close();
                    rset.close();
                    simpan = true;
                    SQLStatemen = "insert into kasir(id_kasir, nama, password) values ('"+ id_kasir +"','"+ nama +"','"+ password +"')"; 
                    sta = cn.createStatement();
                    jumlahSimpan = sta.executeUpdate(SQLStatemen); 
		}
			
		if (simpan) 
		{
                    if (jumlahSimpan > 0)
                    {
                        JOptionPane.showMessageDialog(null,"Data kasir sudah tersimpan","Informasi",JOptionPane.INFORMATION_MESSAGE); 
                    } 
                    else
                    {
                        adaKesalahan = true;
                        JOptionPane.showMessageDialog(null,"Gagal menyimpan data kasir","Kesalahan",JOptionPane.ERROR_MESSAGE);
                    } 
		} 
            } catch (SQLException | HeadlessException ex){
                adaKesalahan = true;
                JOptionPane.showMessageDialog(null,"Tidak dapat membuka tabel kasir\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE); 
		} 
	} 
    } 
    return !adaKesalahan;   
    }
    
    public boolean baca(String id_kasir)
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
                    String SQLStatemen = "select * from kasir where id_kasir='"+id_kasir+"'";
                    Statement sta = cn.createStatement();
                    ResultSet rset = sta.executeQuery(SQLStatemen);
                    rset.next();
                    if (rset.getRow()>0)
                    {
                        this.id_kasir = rset.getString("id_kasir");
                        this.nama = rset.getString("nama");
                        this.password = rset.getString("password");
                        sta.close();
                        rset.close();
                    }
                    else
                    {
                        sta.close();
                        rset.close();
                        adaKesalahan = true;
                        JOptionPane.showMessageDialog(null,"Id \""+id_kasir+"\" tidak ditemukan","Informasi",JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException | HeadlessException ex){
                    adaKesalahan = true;
                    JOptionPane.showMessageDialog(null,"Tidak dapat membuka tabel kasir\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
                    }
            } 
	} 
	return !adaKesalahan; 
    }
    
    public Object[][] bacaLihat()
    {
        boolean adaKesalahan = false;
        Connection cn = null;
        Object[][] lihatKasir = new Object[0][0];
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
                    SQLStatemen = "select id_kasir, nama from kasir";
                    sta = cn.createStatement();
                    rset = sta.executeQuery(SQLStatemen);
                    rset.next();
                    rset.last();
                    lihatKasir = new Object[rset.getRow()][2];
                    if (rset.getRow()>0)
                    {
                        rset.first();
                        int i=0;
                        do { lihatKasir[i] = new Object[]{rset.getString("id_kasir"), rset.getString("nama")}; i++; }
			while (rset.next());
                    }
                    sta.close();
                    rset.close();
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null,"Tidak dapat membuka tabel kasir\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
                    }
            } 
	} 
	return lihatKasir; 
    }

    public boolean hapus(String id_kasir)
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
                    String SQLStatemen = "delete from kasir where id_kasir='"+id_kasir+"'";
                    Statement sta = cn.createStatement();
                    jumlahHapus = sta.executeUpdate(SQLStatemen);
                    if (jumlahHapus>0)
                    {
                        sta.close(); 
                        JOptionPane.showMessageDialog(null,"Data kasir dengan Id "+id_kasir+" sudah dihapus","Informasi",JOptionPane.INFORMATION_MESSAGE);
                    } 
                    else 
                    {
                        sta.close();
                        JOptionPane.showMessageDialog(null,"Data kasir dengan Id "+id_kasir+" tidak ditemukan","Informasi",JOptionPane.INFORMATION_MESSAGE); 
                        adaKesalahan = true; 
                    }
                } catch (SQLException | HeadlessException ex){
                    adaKesalahan = true;
                    JOptionPane.showMessageDialog(null,"Tidak dapat membuka tabel kasir\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
                    }
            }
        } 
	return !adaKesalahan; 
    }

    public Object getPesan() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
