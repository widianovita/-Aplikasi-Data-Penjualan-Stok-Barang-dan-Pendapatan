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
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Widia Novita
 */
public class Transaksi {
    private String nota, id_pembeli, tgl, total, bayar, kembali, id, nama;

    public String getNo() {
        return nota;
    }

    public void setNo(String nota) {
        this.nota = nota;
    }
    
    public String getId_pembeli() {
        return id_pembeli;
    }

    public void setId_pembeli(String id_pembeli) {
        this.tgl = id_pembeli;
    }
    
    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getBayar() {
        return bayar;
    }

    public void setBayar(String bayar) {
        this.bayar = bayar;
    }
    
    public String getKembali() {
        return kembali;
    }

    public void setKembali(String kembali) {
        this.kembali = kembali;
    }
    
    public String getIdKasir() {
        return id;
    }

    public void setIdKasir(String id) {
        this.id = id;
    }
    
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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
                    String SQLStatemen = "select * from transaksi where no_nota='"+nota+"'"; 
                    Statement sta = cn.createStatement(); 
                    ResultSet rset = sta.executeQuery(SQLStatemen); 
                    rset.next();
                    if (rset.getRow()>0)
                    {
                        sta.close();
                        rset.close();
                        Object[] arrOpsi = {"Ya","Tidak"};
                        int pilih=JOptionPane.showOptionDialog(null,"Nota sudah ada\nApakah data diupdate?","Konfirmasi",
                            JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null,arrOpsi,arrOpsi[0]); 
                        
                        if (pilih==0)
                        {
                            simpan = true;
                            SQLStatemen = "update transaksi set id_pembeli='"+id_pembeli+ "', tgl_transaksi='"+tgl+ "', total='"+total+ "', bayar='"+bayar+ "', kembali='"+kembali+ "', id_kasir='"+id+ "', nama='"+nama+ "' where no_nota='"+nota+"'";
                            sta = cn.createStatement();
                            jumlahSimpan = sta.executeUpdate(SQLStatemen); 
                        } 
                    } 
                    else
                    {
                        sta.close();
                        rset.close();
                        simpan = true;
                        SQLStatemen = "insert into transaksi (no_nota, id_pembeli, tgl_transaksi, total, bayar, kembali, id_kasir, nama) values ('"+ nota +"','"+ id_pembeli +"','"+ tgl +"','"+ total +"','"+ bayar +"','"+ kembali +"','"+ id +"','"+ nama +"')"; 
                    
                        sta = cn.createStatement();
                        jumlahSimpan = sta.executeUpdate(SQLStatemen); 
                    }
			
                    if (simpan) 
                    {
                        if (jumlahSimpan > 0)
                        {
                            JOptionPane.showMessageDialog(null,"Data transaksi sudah tersimpan","Informasi",JOptionPane.INFORMATION_MESSAGE); 
                        } 
                        else
                        {
                            adaKesalahan = true;
                            JOptionPane.showMessageDialog(null,"Gagal menyimpan data transaksi","Kesalahan",JOptionPane.ERROR_MESSAGE);
                        } 
                    }    
                } catch (SQLException | HeadlessException ex){
                    adaKesalahan = true;
                    JOptionPane.showMessageDialog(null,"Tidak dapat membuka tabel transaksi\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE); 
		}
            } 
        } 
        return !adaKesalahan;   
    }
    
    public boolean baca(String nota)
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
                    String SQLStatemen = "select * from transaksi where no_nota='"+nota+"'";
                    Statement sta = cn.createStatement();
                    ResultSet rset = sta.executeQuery(SQLStatemen);
                    rset.next();
                    if (rset.getRow()>0)
                    {
                        this.nota = rset.getString("no_nota");
                        this.id_pembeli = rset.getString("id_pembeli");
                        this.tgl = rset.getString("tgl_transaksi");
                        this.total = rset.getString("total");
                        this.bayar = rset.getString("bayar");
                        this.kembali = rset.getString("kembali");
                        this.id = rset.getString("id_kasir");
                        this.nama = rset.getString("nama");
                        sta.close();
                        rset.close();
                    }
                    else
                    {
                        sta.close();
                        rset.close();
                        adaKesalahan = true;
                        JOptionPane.showMessageDialog(null,"Nota \""+nota+"\" tidak ditemukan","Informasi",JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException | HeadlessException ex){
                    adaKesalahan = true;
                    JOptionPane.showMessageDialog(null,"Tidak dapat membuka tabel transaksi\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
                    }
            } 
	} 
	return !adaKesalahan; 
    }
    

    public boolean hapus(String nota)
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
                    String SQLStatemen = "delete from transaksi where no_nota='"+nota+"'";
                    Statement sta = cn.createStatement();
                    jumlahHapus = sta.executeUpdate(SQLStatemen);
                    if (jumlahHapus>0)
                    {
                        sta.close(); 
                        JOptionPane.showMessageDialog(null,"Data transaksi dengan Nota "+nota+" sudah dihapus","Informasi",JOptionPane.INFORMATION_MESSAGE);
                    } 
                    else 
                    {
                        sta.close();
                        JOptionPane.showMessageDialog(null,"Data transaksi dengan Nota "+nota+" tidak ditemukan","Informasi",JOptionPane.INFORMATION_MESSAGE); adaKesalahan = true; 
                    }
                } catch (SQLException | HeadlessException ex){
                    adaKesalahan = true;
                    JOptionPane.showMessageDialog(null,"Tidak dapat membuka tabel transaksi\n"+ex,"Kesalahan",JOptionPane.ERROR_MESSAGE);
                    }
            }
        } 
	return !adaKesalahan; 
    }
    
    public void cetakLaporan(String nota)
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
                    SQLStatement = "SELECT  karyawan.`id_karyawan` AS karyawan_id_karyawan, " +
                                   "karyawan.`nama_karyawan` AS karyawan_nama_karyawan, " +
                                   "karyawan.`password` AS karyawan_password, " +
                                   "kasir.`id_kasir` AS kasir_id_kasir, " +
                                   "kasir.`nama` AS kasir_nama, " +
                                   "kasir.`password` AS kasir_password, " +
                                   "pembeli.`id_pembeli` AS pembeli_id_pembeli, " +
                                   "pembeli.`nama_pembeli` AS pembeli_nama_pembeli, " +
                                   "pembeli.`telepon` AS pembeli_telepon, " +
                                   "pembeli.`alamat` AS pembeli_alamat, " +
                                   "penjualan.`no_nota` AS penjualan_no_nota, " +
                                   "penjualan.`tgl_penjualan` AS penjualan_tgl_penjualan, " +
                                   "penjualan.`kode_barang` AS penjualan_kode_barang, " +
                                   "penjualan.`nama_barang` AS penjualan_nama_barang, " +
                                   "penjualan.`kategori` AS penjualan_kategori, " +
                                   "penjualan.`jmh` AS penjualan_jmh, " +
                                   "penjualan.`harga` AS penjualan_harga, " +
                                   "penjualan.`diskon` AS penjualan_diskon, " +
                                   "penjualan.`total` AS penjualan_total, " +
                                   "persediaan.`kode_barang` AS persediaan_kode_barang, " +
                                   "persediaan.`nama_barang` AS persediaan_nama_barang, " +
                                   "persediaan.`kategori` AS persediaan_kategori, " +
                                   "persediaan.`qty` AS persediaan_qty, " +
                                   "persediaan.`tgl_input` AS persediaan_tgl_input, " +
                                   "persediaan.`id_karyawan` AS persediaan_id_karyawan, " +
                                   "transaksi.`no_nota` AS transaksi_no_nota, " +
                                   "transaksi.`id_pembeli` AS transaksi_id_pembeli, " +
                                   "transaksi.`tgl_transaksi` AS transaksi_tgl_transaksi, " +
                                   "transaksi.`total` AS transaksi_total, " +
                                   "transaksi.`bayar` AS transaksi_bayar, " +
                                   "transaksi.`kembali` AS transaksi_kembali, "+
                                   "transaksi.`id_kasir` AS transaksi_id_kasir, " +
                                   "transaksi.`nama` AS transaksi_nama " +
                                   "FROM " + "`karyawan` karyawan INNER JOIN `persediaan` persediaan ON karyawan.`id_karyawan` = persediaan.`id_karyawan`  " +
                                   "INNER JOIN `penjualan` penjualan ON persediaan.`kode_barang` = penjualan.`kode_barang` " +
                                   "INNER JOIN `transaksi` transaksi ON penjualan.`no_nota` = transaksi.`no_nota` " +
                                   "INNER JOIN `kasir` kasir ON transaksi.`id_kasir` = kasir.`id_kasir`, `pembeli` pembeli " ;

                    
                    SQLStatement = SQLStatement +" ORDER BY " +"penjualan.`no_nota` ASC, " +"penjualan.`kode_barang` ASC";
                    JasperDesign disain = JRXmlLoader.load( "src/reports/StrukTransaksi.jrxml");
                    JasperReport transaksiLaporan = JasperCompileManager.compileReport(disain);
                    ResultSet resultSet = statement.executeQuery(SQLStatement);
                    JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
                    JasperPrint cetak = JasperFillManager.fillReport( transaksiLaporan,new HashMap(), resultSetDataSource);
                    JasperViewer.viewReport(cetak,false);
                } catch (Exception ex) { JOptionPane.showMessageDialog(null,"\nGagal mencetak\n" +ex,"Kesalahan",JOptionPane.ERROR_MESSAGE); }
            } 
	}
    }
    
}

