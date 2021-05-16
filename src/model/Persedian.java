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
import javax.swing.JOptionPane; 
import java.util.HashMap;
import net.sf.jasperreports.engine.*; 
import net.sf.jasperreports.engine.design.JasperDesign; 
import net.sf.jasperreports.engine.xml.JRXmlLoader; 
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Widia Novita
 */
public class Persedian {
    private String kode, nama, kategori, qty, tgl, id; 
    
    public String getKodeBarang() {
        return kode;
    }

    public void setKodeBarang(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
    
    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
                    String SQLStatemen = "select * from persediaan where kode_barang='"+kode+"'"; 
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
                            SQLStatemen = "update persediaan set nama_barang='"+nama+ "', kategori='"+kategori+ "', qty='"+qty+ "', tgl_input='"+tgl+ "', id_karyawan='"+id+ "' where kode_barang='"+kode+"'";
                            sta = cn.createStatement();
                            jumlahSimpan = sta.executeUpdate(SQLStatemen); 
                        } 
                    } 
                    else
                    {
                        sta.close();
                        rset.close();
                        simpan = true;
                        SQLStatemen = "insert into persediaan(kode_barang, nama_barang, kategori, qty, tgl_input, id_karyawan) values ('"+ kode +"','"+ nama +"','"+ kategori +"','"+ qty +"', '"+ tgl +"', '"+ id +"')"; 
                        sta = cn.createStatement();
                        jumlahSimpan = sta.executeUpdate(SQLStatemen); 
                    }
			
                    if (simpan) 
                    {
                        if (jumlahSimpan > 0)
                        {
                            JOptionPane.showMessageDialog(null,"Data persediaan sudah tersimpan","Informasi",JOptionPane.INFORMATION_MESSAGE); 
                        } 
                        else
                        {
                            adaKesalahan = true;
                            JOptionPane.showMessageDialog(null,"Gagal menyimpan data persediaan","Kesalahan",JOptionPane.ERROR_MESSAGE);
                        } 
                    } 
                } catch (SQLException | HeadlessException ex){
                    adaKesalahan = true;
                    JOptionPane.showMessageDialog(null,"Tidak dapat membuka tabel persediaan\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE); 
		} 
            } 
        } 
        return !adaKesalahan;   
    }
    
    public boolean baca(String kode)
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
                    String SQLStatemen = "select * from persediaan where kode_barang='"+kode+"'";
                    Statement sta = cn.createStatement();
                    ResultSet rset = sta.executeQuery(SQLStatemen);
                    rset.next();
                    if (rset.getRow()>0)
                    {
                        this.kode = rset.getString("kode_barang");
                        this.nama = rset.getString("nama_barang");
                        this.kategori = rset.getString("kategori");
                        this.qty = rset.getString("qty");
                        this.tgl = rset.getString("tgl_input");
                        this.id = rset.getString("id_karyawan");
                        sta.close();
                        rset.close();
                    }
                    else
                    {
                        sta.close();
                        rset.close();
                        adaKesalahan = true;
                        JOptionPane.showMessageDialog(null,"kode_barang \""+kode+"\" tidak ditemukan","Informasi",JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException | HeadlessException ex){
                    adaKesalahan = true;
                    JOptionPane.showMessageDialog(null,"Tidak dapat membuka tabel persediaan\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
                    }
            } 
	} 
	return !adaKesalahan; 
    }
    
    public Object[][] bacaLihat()
    {
        boolean adaKesalahan = false;
        Connection cn = null;
        Object[][] lihatPersediaan = new Object[0][0];
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
                    SQLStatemen = "select kode_barang, nama_barang from persediaan";
                    sta = cn.createStatement();
                    rset = sta.executeQuery(SQLStatemen);
                    rset.next();
                    rset.last();
                    lihatPersediaan = new Object[rset.getRow()][2];
                    if (rset.getRow()>0)
                    {
                        rset.first();
                        int i=0;
                        do { lihatPersediaan[i] = new Object[]{rset.getString("kode_barang"), rset.getString("nama_barang")}; i++; }
			while (rset.next());
                    }
                    sta.close();
                    rset.close();
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null,"Tidak dapat membuka tabel persediaan\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
                    }
            } 
	} 
	return lihatPersediaan; 
    }
    
    
    public boolean hapus(String kode)
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
                    String SQLStatemen = "delete from persediaan where kode_barang='"+kode+"'";
                    Statement sta = cn.createStatement();
                    jumlahHapus = sta.executeUpdate(SQLStatemen);
                    if (jumlahHapus>0)
                    {
                        sta.close(); 
                        JOptionPane.showMessageDialog(null,"Data persediaan dengan kode_barang "+kode+" sudah dihapus","Informasi",JOptionPane.INFORMATION_MESSAGE);
                    } 
                    else 
                    {
                        sta.close();
                        JOptionPane.showMessageDialog(null,"Data persediaan dengan kode_barang "+kode+" tidak ditemukan","Informasi",JOptionPane.INFORMATION_MESSAGE); adaKesalahan = true; 
                    }
                } catch (SQLException | HeadlessException ex){
                    adaKesalahan = true;
                    JOptionPane.showMessageDialog(null,"Tidak dapat membuka tabel persediaan\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
                    }
            }
        } 
	return !adaKesalahan; 
    }
    
    public void cetakLaporan(String kode)
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
                cn = DriverManager.getConnection(Koneksi.database +"?user=" +Koneksi.user+ "&password="+Koneksi.password+"");
            } catch (Exception ex) {
                adaKesalahan = true;
                JOptionPane.showMessageDialog(null,"Koneksi ke "+ Koneksi.database +" gagal\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
            }
            
            if (!adaKesalahan)
            {
                String SQLStatement="";
                try
                {
                    Statement statement = cn.createStatement();
                    SQLStatement = "SELECT karyawan.`id_karyawan` AS karyawan_id_karyawan, " +
                                   "karyawan.`nama_karyawan` AS karyawan_nama_karyawan," +
                                   "karyawan.`password` AS karyawan_password," +
                                   "kasir.`id_kasir` AS kasir_id_kasir," +
				   "kasir.`nama` AS kasir_nama," +
				   "kasir.`password` AS kasir_password," +
     				   "pembeli.`id_pembeli` AS pembeli_id_pembeli," +
     				   "pembeli.`nama_pembeli` AS pembeli_nama_pembeli," +
                                   "pembeli.`telepon` AS pembeli_telepon," +
                                   "pembeli.`alamat` AS pembeli_alamat," +
                                   "penjualan.`no_nota` AS penjualan_no_nota," +
                                   "penjualan.`tgl_penjualan` AS penjualan_tgl_penjualan," +
                                   "penjualan.`kode_barang` AS penjualan_kode_barang," +
                                   "penjualan.`nama_barang` AS penjualan_nama_barang," +
                                   "penjualan.`kategori` AS penjualan_kategori," +
                                   "penjualan.`jmh` AS penjualan_jmh," +
                                   "penjualan.`harga` AS penjualan_harga," +
                                   "penjualan.`diskon` AS penjualan_diskon," +
                                   "penjualan.`total` AS penjualan_total," +
                                   "persediaan.`kode_barang` AS persediaan_kode_barang," +
                                   "persediaan.`nama_barang` AS persediaan_nama_barang," +
                                   "persediaan.`kategori` AS persediaan_kategori," +
                                   "persediaan.`qty` AS persediaan_qty," +
                                   "persediaan.`tgl_input` AS persediaan_tgl_input," +
                                   "persediaan.`id_karyawan` AS persediaan_id_karyawan," +
                                   "transaksi.`no_nota` AS transaksi_no_nota," +
                                   "transaksi.`id_pembeli` AS transaksi_id_pembeli," +
                                   "transaksi.`tgl_transaksi` AS transaksi_tgl_transaksi," +
                                   "transaksi.`total` AS transaksi_total," +
                                   "transaksi.`bayar` AS transaksi_bayar," +
                                   "transaksi.`kembali` AS transaksi_kembali," +
                                   "transaksi.`id_kasir` AS transaksi_id_kasir, " +
                                   "transaksi.`nama` AS transaksi_nama " +
                                   "FROM " + "`karyawan` karyawan INNER JOIN `persediaan` persediaan ON karyawan.`id_karyawan` = persediaan.`id_karyawan` " +
                                   "INNER JOIN `penjualan` penjualan ON persediaan.`kode_barang` = penjualan.`kode_barang` " +
                                   "INNER JOIN `transaksi` transaksi ON penjualan.`no_nota` = transaksi.`no_nota` " +
                                   "INNER JOIN `kasir` kasir ON transaksi.`id_kasir` = kasir.`id_kasir`,`pembeli` pembeli " ;

                    
                    SQLStatement = SQLStatement +" ORDER BY " +"karyawan.`id_karyawan` ASC, " +"karyawan.`nama_karyawan` ASC";
                    JasperDesign disain = JRXmlLoader.load( "src/reports/LaporanPersediaan.jrxml");
                    JasperReport persediaanLaporan = JasperCompileManager.compileReport(disain);
                    ResultSet resultSet = statement.executeQuery(SQLStatement);
                    JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
                    JasperPrint cetak = JasperFillManager.fillReport( persediaanLaporan,new HashMap(), resultSetDataSource);
                    JasperViewer.viewReport(cetak,false);
                } catch (Exception ex) { JOptionPane.showMessageDialog(null,"\nGagal mencetak\n" +ex,"Kesalahan",JOptionPane.ERROR_MESSAGE); }
            } 
	}
    }
    
}
