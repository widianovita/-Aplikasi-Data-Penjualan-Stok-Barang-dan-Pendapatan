-- phpMyAdmin SQL Dump
-- version 4.4.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 30, 2020 at 06:22 AM
-- Server version: 5.6.25
-- PHP Version: 5.6.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `penjualan_brg`
--

-- --------------------------------------------------------

--
-- Table structure for table `karyawan`
--

CREATE TABLE IF NOT EXISTS `karyawan` (
  `id_karyawan` char(12) NOT NULL,
  `nama_karyawan` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `karyawan`
--

INSERT INTO `karyawan` (`id_karyawan`, `nama_karyawan`, `password`) VALUES
('1234', 'Widia', '3f132ed0f4b301669300eabbe237da88');

-- --------------------------------------------------------

--
-- Table structure for table `kasir`
--

CREATE TABLE IF NOT EXISTS `kasir` (
  `id_kasir` char(12) NOT NULL,
  `nama` varchar(25) NOT NULL,
  `password` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kasir`
--

INSERT INTO `kasir` (`id_kasir`, `nama`, `password`) VALUES
('1111', 'Isan', 'aaaed2417888d4246e38db8d8d338bd2');

-- --------------------------------------------------------

--
-- Table structure for table `pembeli`
--

CREATE TABLE IF NOT EXISTS `pembeli` (
  `id_pembeli` char(12) NOT NULL,
  `nama_pembeli` varchar(25) NOT NULL,
  `telepon` char(12) NOT NULL,
  `alamat` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pembeli`
--

INSERT INTO `pembeli` (`id_pembeli`, `nama_pembeli`, `telepon`, `alamat`) VALUES
('1122', 'Adila', '0878889990', 'Pondok Pinang'),
('1133', 'Ita', '08922233399', 'Pamulang');

-- --------------------------------------------------------

--
-- Table structure for table `penjualan`
--

CREATE TABLE IF NOT EXISTS `penjualan` (
  `no_nota` char(12) NOT NULL,
  `tgl_penjualan` varchar(20) NOT NULL,
  `kode_barang` char(8) NOT NULL,
  `nama_barang` varchar(20) NOT NULL,
  `kategori` varchar(25) NOT NULL,
  `jmh` int(11) NOT NULL,
  `harga` int(11) NOT NULL,
  `diskon` int(11) NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `penjualan`
--

INSERT INTO `penjualan` (`no_nota`, `tgl_penjualan`, `kode_barang`, `nama_barang`, `kategori`, `jmh`, `harga`, `diskon`, `total`) VALUES
('F0001', '2020-06-10', '0898989', 'Tunik', 'Ladies Wear', 3, 150000, 5, 427500);

-- --------------------------------------------------------

--
-- Table structure for table `persediaan`
--

CREATE TABLE IF NOT EXISTS `persediaan` (
  `kode_barang` char(8) NOT NULL,
  `nama_barang` varchar(20) NOT NULL,
  `kategori` varchar(25) NOT NULL,
  `qty` int(10) NOT NULL,
  `tgl_input` date NOT NULL,
  `id_karyawan` char(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `persediaan`
--

INSERT INTO `persediaan` (`kode_barang`, `nama_barang`, `kategori`, `qty`, `tgl_input`, `id_karyawan`) VALUES
('0888888', 'pasminah', 'Jilbab', 100, '2020-05-31', '1234'),
('0898989', 'Tunik', 'Ladies Wear', 50, '2020-05-31', '1234'),
('9898989', 'Kemeja Koko', 'Mens Wear', 150, '2020-03-20', '1234');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE IF NOT EXISTS `transaksi` (
  `no_nota` char(12) NOT NULL,
  `id_pembeli` char(12) NOT NULL,
  `tgl_transaksi` date NOT NULL,
  `total` int(11) NOT NULL,
  `bayar` int(11) NOT NULL,
  `kembali` int(11) NOT NULL,
  `id_kasir` char(12) NOT NULL,
  `nama` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`no_nota`, `id_pembeli`, `tgl_transaksi`, `total`, `bayar`, `kembali`, `id_kasir`, `nama`) VALUES
('F0001', 'null', '2020-06-10', 427500, 450000, 22500, '1111', 'Isan');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `karyawan`
--
ALTER TABLE `karyawan`
  ADD PRIMARY KEY (`id_karyawan`);

--
-- Indexes for table `kasir`
--
ALTER TABLE `kasir`
  ADD PRIMARY KEY (`id_kasir`);

--
-- Indexes for table `pembeli`
--
ALTER TABLE `pembeli`
  ADD PRIMARY KEY (`id_pembeli`);

--
-- Indexes for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD PRIMARY KEY (`no_nota`),
  ADD KEY `kode_barang` (`kode_barang`);

--
-- Indexes for table `persediaan`
--
ALTER TABLE `persediaan`
  ADD PRIMARY KEY (`kode_barang`),
  ADD KEY `id_karyawan` (`id_karyawan`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD KEY `no_nota` (`no_nota`),
  ADD KEY `id_pembeli` (`id_pembeli`),
  ADD KEY `id_kasir` (`id_kasir`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD CONSTRAINT `penjualan_ibfk_1` FOREIGN KEY (`kode_barang`) REFERENCES `persediaan` (`kode_barang`);

--
-- Constraints for table `persediaan`
--
ALTER TABLE `persediaan`
  ADD CONSTRAINT `persediaan_ibfk_1` FOREIGN KEY (`id_karyawan`) REFERENCES `karyawan` (`id_karyawan`);

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`no_nota`) REFERENCES `penjualan` (`no_nota`),
  ADD CONSTRAINT `transaksi_ibfk_3` FOREIGN KEY (`id_kasir`) REFERENCES `kasir` (`id_kasir`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
