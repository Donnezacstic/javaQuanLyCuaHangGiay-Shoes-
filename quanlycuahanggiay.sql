-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th4 06, 2021 lúc 08:16 AM
-- Phiên bản máy phục vụ: 10.4.11-MariaDB
-- Phiên bản PHP: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlycuahanggiay`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account`
--

CREATE TABLE `account` (
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(155) COLLATE utf8_unicode_ci NOT NULL,
  `role` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Position` varchar(155) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Nhân Viên'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `account`
--

INSERT INTO `account` (`username`, `password`, `role`, `Position`) VALUES
('admin', 'admin', 'admin', 'Admin'),
('NV1', '123456', '010101010100', 'Nhân viên'),
('NV10', '123456', '000000000001', 'Nhân viên'),
('NV11', '123456', '00000000000', 'Nhân viên'),
('NV12', '123456', '111111111101', 'Nhân viên'),
('NV13', '123456', '111100100101', 'Nhân viên'),
('NV14', 'admin', '110011000101', 'Nhân viên'),
('NV15', '123456', '110000000001', 'Nhân viên'),
('NV2', '123456', '010000000001', 'Nhân viên'),
('NV3', '123456', '000010000101', 'Nhân viên'),
('NV4', '123456', '101010101001', 'Nhân viên'),
('NV5', '123456', '001101110001', 'Nhân viên'),
('NV6', '123456', '001100111001', 'Nhân viên'),
('NV7', '123456', '000000001001', 'Nhân viên'),
('NV8', '123456', '000000000001', 'Nhân viên'),
('NV9', '123456', '000000000001', 'Nhân viên');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `madonhang` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `masanpham` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `soluong` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dongia` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `thanhtien` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chitietdonhang`
--

INSERT INTO `chitietdonhang` (`madonhang`, `masanpham`, `soluong`, `dongia`, `thanhtien`) VALUES
('DH1', 'SP1', '1', '300000', '300000'),
('DH2', 'SP5', '1', '500000', '500000'),
('DH3', 'SP3', '1', '400000', '400000'),
('DH4', 'SP1', '1', '300000', '300000'),
('DH5', 'SP1', '1', '300000', '300000'),
('DH6', 'SP9', '1', '700000', '700000'),
('DH7', 'SP1', '1', '300000', '300000'),
('DH7', 'SP9', '1', '700000', '700000'),
('DH8', 'SP5', '1', '500000', '500000'),
('DH8', 'SP9', '1', '700000', '700000'),
('DH9', 'SP2', '10', '350000', '3500000'),
('DH9', 'SP4', '10', '450000', '4500000'),
('DH9', 'SP6', '10', '550000', '5500000'),
('DH9', 'SP8', '10', '650000', '6500000'),
('DH9', 'SP5', '20', '500000', '5000000'),
('DH10', 'SP1', '10', '300000', '3000000'),
('DH10', 'SP3', '10', '400000', '4000000'),
('DH10', 'SP5', '10', '500000', '5000000'),
('DH10', 'SP5', '10', '500000', '5000000'),
('DH11', 'SP4', '10', '450000', '4500000'),
('DH12', 'SP4', '10', '450000', '4500000'),
('DH13', 'SP4', '10', '450000', '4500000'),
('DH14', 'SP5', '10', '500000', '5000000'),
('DH15', 'SP9', '10', '700000', '7000000'),
('DH16', 'SP4', '5', '450000', '2250000'),
('DH17', 'SP3', '5', '400000', '2000000'),
('DH18', 'SP1', '12', '300000', '3600000'),
('DH18', 'SP2', '12', '350000', '4200000'),
('DH19', 'SP1', '1', '300000', '300000'),
('DH20', 'SP1', '1', '300000', '300000'),
('DH20', 'SP2', '1', '350000', '350000'),
('DH21', 'SP1', '1', '300000', '300000'),
('DH21', 'SP2', '1', '350000', '350000'),
('DH22', 'SP1', '1', '300000', '300000'),
('DH22', 'SP2', '1', '350000', '350000'),
('DH23', 'SP1', '1', '300000', '300000'),
('DH23', 'SP2', '1', '350000', '350000'),
('DH24', 'SP1', '1', '300000', '300000'),
('DH24', 'SP2', '1', '350000', '350000'),
('DH25', 'SP1', '1', '300000', '300000'),
('DH25', 'SP2', '1', '350000', '350000'),
('DH26', 'SP1', '1', '300000', '300000'),
('DH26', 'SP2', '1', '350000', '350000'),
('DH27', 'SP1', '1', '300000', '300000'),
('DH27', 'SP2', '1', '350000', '350000'),
('DH28', 'SP1', '1', '300000', '300000'),
('DH28', 'SP2', '1', '350000', '350000'),
('DH29', 'SP4', '1', '450000', '450000'),
('DH29', 'SP7', '1', '600000', '600000'),
('DH30', 'SP7', '2', '600000', '1200000'),
('DH30', 'SP8', '2', '650000', '1300000'),
('DH31', 'SP1', '1', '300000', '300000'),
('DH32', 'SP1', '1', '300000', '300000'),
('DH33', 'SP1', '5', '300000', '1500000'),
('DH34', 'SP1', '1', '300000', '300000'),
('DH34', 'SP5', '10', '500000', '5000000'),
('DH35', 'SP2', '39', '350000', '13650000'),
('DH36', 'SP6', '40', '550000', '22000000'),
('DH37', 'SP7', '37', '600000', '22200000'),
('DH38', 'SP8', '31', '650000', '20150000'),
('DH39', 'SP8', '31', '650000', '20150000'),
('DH40', 'SP8', '44', '650000', '28600000'),
('DH41', 'SP9', '31', '700000', '21700000'),
('DH42', 'SP9', '31', '700000', '21700000'),
('DH43', 'SP9', '31', '700000', '21700000'),
('DH44', 'SP1', '3', '300000', '900000'),
('DH45', 'SP4', '4', '450000', '1800000'),
('DH46', 'SP1', '3', '300000', '900000');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietkhuyenmai`
--

CREATE TABLE `chitietkhuyenmai` (
  `machitietkhuyenmai` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `machuongtrinhkhuyenmai` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `macode` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `phantramkhuyenmai` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `giatienkhuyenmai` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `giatritoithieusudung` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `giatrigiamtoida` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `thoigianbatdausudung` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `thoigianketthucsudung` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `soluong` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `soluongdadung` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chitietkhuyenmai`
--

INSERT INTO `chitietkhuyenmai` (`machitietkhuyenmai`, `machuongtrinhkhuyenmai`, `macode`, `phantramkhuyenmai`, `giatienkhuyenmai`, `giatritoithieusudung`, `giatrigiamtoida`, `thoigianbatdausudung`, `thoigianketthucsudung`, `soluong`, `soluongdadung`) VALUES
('CTKM1', 'CT1', 'MAGIAMGIA1', '15', '0', '300000', '30000', '1577881800', '1609417800', '100', '94'),
('CTKM2', 'CT2', 'MAGIAMGIA2', '0', '20000', '300000', '20000', '1577881800', '1609417800', '30', '25'),
('CTKM3', 'CT1', 'mungnamtansuu', '15', '0', '1000000', 'b', '1615420800', '1618099200', '10', '0');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietphieunhap`
--

CREATE TABLE `chitietphieunhap` (
  `maphieunhap` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `masanpham` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dongia` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `soluong` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `thanhtien` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chitietphieunhap`
--

INSERT INTO `chitietphieunhap` (`maphieunhap`, `masanpham`, `dongia`, `soluong`, `thanhtien`) VALUES
('PN1', 'SP1', '300000', '50', '15000000'),
('PN1', 'SP2', '350000', '40', '14000000'),
('PN1', 'SP3', '400000', '50', '20000000'),
('PN2', 'SP4', '450000', '30', '13500000'),
('PN2', 'SP5', '500000', '70', '20000000'),
('PN2', 'SP6', '550000', '50', '27500000'),
('PN2', 'SP7', '600000', '40', '24000000'),
('PN3', 'SP8', '650000', '100', '65000000'),
('PN3', 'SP9', '700000', '120', '84000000'),
('PN4', 'SP1', '300000', '20', '6000000'),
('PN4', 'SP3', '400000', '20', '8000000'),
('PN4', 'SP5', '500000', '20', '10000000'),
('PN5', 'SP2', '350000', '30', '10500000'),
('PN6', 'SP4', '450000', '30', '13500000'),
('PN7', 'SP6', '550000', '30', '16500000'),
('PN8', 'SP8', '650000', '30', '19500000'),
('PN9', 'SP9', '700000', '30', '21000000'),
('PN10', 'SP1', '2000', '2', '4000');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chuongtrinhkhuyenmai`
--

CREATE TABLE `chuongtrinhkhuyenmai` (
  `machuongtrinhkhuyenmai` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tenchuongtrinhkhuyenmai` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ngaytao` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chuongtrinhkhuyenmai`
--

INSERT INTO `chuongtrinhkhuyenmai` (`machuongtrinhkhuyenmai`, `tenchuongtrinhkhuyenmai`, `ngaytao`) VALUES
('CT1', 'Chương Trình Khuyến Mãi 1', '1577865600'),
('CT2', 'Chương Trình Khuyến Mãi 2', '1577865720');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `donhang`
--

CREATE TABLE `donhang` (
  `madonhang` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `makhachhang` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `manhanvien` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ngayxuat` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `macode` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tamtinh` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `giamgia` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `phivanchuyen` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tongtien` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `trangthai` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `donhang`
--

INSERT INTO `donhang` (`madonhang`, `makhachhang`, `manhanvien`, `ngayxuat`, `macode`, `tamtinh`, `giamgia`, `phivanchuyen`, `tongtien`, `trangthai`) VALUES
('DH1', 'KH1', 'NV1', '1590313403', 'MAGIAMGIA1', '300000', '30000', '30000', '300000', '1'),
('DH10', 'KH7', 'NV5', '1589966609', 'MAGIAMGIA1', '12000000', '30000', '30000', '12000000', '1'),
('DH11', 'KH8', 'NV6', '1590699073', 'MAGIAMGIA2', '4500000', '20000', '30000', '4510000', '1'),
('DH12', 'KH9', 'NV6', '1590699073', 'MAGIAMGIA2', '4500000', '20000', '30000', '4510000', '1'),
('DH13', 'KH10', 'NV6', '1590699073', 'MAGIAMGIA2', '4500000', '20000', '30000', '4510000', '1'),
('DH14', 'KH11', 'NV5', '1591311285', 'MAGIAMGIA1', '5000000', '30000', '30000', '5000000', '0'),
('DH15', 'KH12', 'NV5', '1591311495', 'MAGIAMGIA2', '7000000', '20000', '30000', '7010000', '0'),
('DH16', 'KH14', 'NV2', '1592151011', NULL, '2250000', '0', '30000', '2280000', '1'),
('DH17', 'KH15', 'NV1', '1592151175', NULL, '2000000', '0', '30000', '2030000', '1'),
('DH18', 'KH16', 'NV1', '1592156494', NULL, '7800000', '0', '30000', '7830000', '0'),
('DH19', 'KH0', 'NV1', '1592161367', NULL, '300000', '0', '30000', '330000', '1'),
('DH2', 'KH1', 'NV1', '1590333836', 'MAGIAMGIA2', '500000', '20000', '30000', '510000', '1'),
('DH20', 'KH16', 'NV1', '1592162206', NULL, '650000', '0', '30000', '680000', '1'),
('DH21', 'KH0', 'NV1', '1592162360', NULL, '650000', '0', '30000', '680000', '1'),
('DH22', 'KH16', 'NV1', '1592162377', NULL, '650000', '0', '30000', '680000', '1'),
('DH23', 'KH16', 'NV1', '1592163347', NULL, '650000', '0', '30000', '680000', '1'),
('DH24', 'KH17', 'NV1', '1592163706', NULL, '650000', '0', '30000', '680000', '1'),
('DH25', 'KH17', 'NV1', '1592163730', NULL, '650000', '0', '30000', '680000', '1'),
('DH26', 'KH0', 'NV1', '1592163807', NULL, '650000', '0', '30000', '680000', '1'),
('DH27', 'KH17', 'NV1', '1592163826', NULL, '650000', '0', '30000', '680000', '1'),
('DH28', 'KH0', 'NV1', '1592170479', NULL, '650000', '0', '30000', '680000', '1'),
('DH29', 'KH17', 'NV1', '1592170540', NULL, '1050000', '0', '30000', '1080000', '1'),
('DH3', 'KH2', 'NV2', '1590924263', 'MAGIAMGIA1', '400000', '30000', '30000', '400000', '1'),
('DH30', 'KH18', 'NV1', '1592170729', NULL, '2500000', '0', '30000', '2530000', '1'),
('DH31', 'KH20', 'NV1', '1592174572', 'magiamgia1', '300000', '30000', '30000', '300000', '1'),
('DH32', 'KH0', 'NV1', '1592174937', NULL, '300000', '0', '30000', '330000', '1'),
('DH33', 'KH0', 'NV1', '1592175093', NULL, '1500000', '0', '30000', '1530000', '1'),
('DH34', 'KH0', 'NV12', '1592907957', NULL, '5300000', '0', '30000', '5330000', '1'),
('DH35', 'KH0', 'NV12', '1592909517', NULL, '13650000', '0', '30000', '13680000', '1'),
('DH36', 'KH8', 'NV12', '1592910648', NULL, '22000000', '0', '30000', '22030000', '1'),
('DH37', 'KH8', 'NV12', '1592911012', NULL, '22200000', '0', '30000', '22230000', '1'),
('DH38', 'KH8', 'NV12', '1592911244', NULL, '20150000', '0', '30000', '20180000', '1'),
('DH39', 'KH21', 'NV12', '1592911280', NULL, '20150000', '0', '30000', '20180000', '1'),
('DH4', 'KH3', 'NV2', '1590999345', 'MAGIAMGIA1', '300000', '30000', '30000', '300000', '1'),
('DH40', 'KH0', 'NV12', '1592918769', NULL, '28600000', '0', '30000', '28630000', '1'),
('DH41', 'KH0', 'NV12', '1593353476', NULL, '21700000', '0', '30000', '21730000', '1'),
('DH42', 'KH8', 'NV12', '1593353500', NULL, '21700000', '0', '30000', '21730000', '1'),
('DH43', 'KH22', 'NV12', '1593353540', NULL, '21700000', '0', '30000', '21730000', '1'),
('DH44', 'KH0', 'NV12', '1615450161', NULL, '900000', '0', '30000', '930000', '1'),
('DH45', 'KH23', 'NV12', '1615450232', NULL, '1800000', '0', '30000', '1830000', '1'),
('DH46', 'KH16', 'NV12', '1616055041', NULL, '900000', '0', '30000', '930000', '1'),
('DH5', 'KH3', 'NV2', '1591005361', 'MAGIAMGIA2', '300000', '20000', '30000', '310000', '1'),
('DH6', 'KH4', 'NV3', '1591021264', 'MAGIAMGIA2', '700000', '20000', '30000', '710000', '1'),
('DH7', 'KH5', 'NV3', '1591137916', 'MAGIAMGIA2', '1000000', '20000', '30000', '1100000', '1'),
('DH8', 'KH5', 'NV4', '1591180233', 'MAGIAMGIA1', '1200000', '30000', '30000', '1200000', '1'),
('DH9', 'KH6', 'NV4', '1591228092', 'MAGIAMGIA1', '30000000', '30000', '30000', '30000000', '1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `makhachhang` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `hoten` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sodienthoai` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  `diachi` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`makhachhang`, `hoten`, `sodienthoai`, `diachi`, `email`) VALUES
('KH0', 'Vãng Lai', '0999999999', '273 An Duong Vuong', 'sagoshoes@sago.vn'),
('KH1', 'Phan Công Hà', '0916375864', '120 Công Chúa Ngọc Hân', 'phancongha@gmail.com'),
('KH10', 'Triển Chill', '0906456789', '120 Công Chúa Ngọc Hân', 'chientrill@gmail.com'),
('KH11', 'Thần Deal Đại Hiệp', '039126327', '120 Công Chúa Ngọc Hân', 'dealhiepdaithan@gmail.com'),
('KH12', 'Trần Hổ', '0544246357', 'Phao Sần Pa Lay', 'donaldtiger@gmail.com'),
('KH13', 'Nguyen HUUNhan', '0775852596', '123 minh phung', '123kkkkkkkKKK@gmail.com'),
('KH14', 'Nguyen HUUNhan', '0775852596', '123 minh phung', '123kkkkkkkKKK@gmail.com'),
('KH15', 'Nguyen HUUNhan', '0775852596', '123 minh phung', '123kkkkkkkKKK@gmail.com'),
('KH16', 'Thai Kien Hao', '0123456789', '273 An Duong Vuong', 'SDAGAS@gmail.com'),
('KH17', 'Son Hao PMT', '0987654321', '273 Minh Phung', 'SBHAEBR@gmail.com'),
('KH18', 'Hao Ro Si', '0998877665', '273 Nguyen Trai', 'thaikienhao@sago.vn'),
('KH2', 'Phan Công Sơn', '0916375864', '120 Công Chúa Ngọc Hân', 'phancongson@gmail.com'),
('KH20', 'phan cong son', '0916375645', '120 cong chua ngoc han', 'congsonlatoi1@gmail.com'),
('KH21', 'Thai Kien Hao', '0906395804', '273 An Duong Vuong', 'thaikienhao@Gmail.com'),
('KH22', 'Hao Thai Kien', '0906395805', '273 An Duong Vuong', 'haothaikien@gmail.com'),
('KH23', 'Au Khanh Toan', '0767896626', 'Phạm Văn Chí', 'toanallkill@gmail.com'),
('KH3', 'Âu Khánh Toàn', '0916375864', '120 Công Chúa Ngọc Hân', 'aukhanhtoan@gmail.com'),
('KH4', 'Nguyễn Hữu Nhân', '0916375864', '120 Công Chúa Ngọc Hân', 'nguyenhuunhan@gmail.com'),
('KH5', 'Nguyễn Minh Phương Nam', '0785121248', '120 Công Chúa Ngọc Hân', 'nguyenminhphuongnam@gmail.com'),
('KH6', 'Hồ Sỹ Đạt', '0916375864', '120 Công Chúa Ngọc Hân', 'hosydat@gmail.com'),
('KH7', 'Trần Quốc Bảo', '0916375864', '120 Công Chúa Ngọc Hân', 'tranquocbao@gmail.com'),
('KH8', 'Thái Kiến Hào', '0906395803', '120 Công Chúa Ngọc Hân', 'thaikienhao@gmail.com'),
('KH9', 'Sơn Hào TMP', '0916375864', '120 Công Chúa Ngọc Hân', 'sonhaotmp@gmail.com');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `mau`
--

CREATE TABLE `mau` (
  `mamau` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tenmau` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `mau`
--

INSERT INTO `mau` (`mamau`, `tenmau`) VALUES
('M1', 'Đỏ'),
('M2', 'Đen'),
('M3', 'Trắng Xám'),
('M4', 'Trắng'),
('M5', 'Xanh'),
('M6', 'Hồng'),
('M7', 'Xám'),
('M8', 'Tím'),
('M9', 'Vàng Tươi');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `manhacungcap` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tennhacungcap` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sodienthoai` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `diachi` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nhacungcap`
--

INSERT INTO `nhacungcap` (`manhacungcap`, `tennhacungcap`, `sodienthoai`, `diachi`) VALUES
('NCC1', 'Nhà Cung Cấp A', '0912345678', '512 Vương Diệu'),
('NCC10', 'Nhà Cung Cấp F', '0913175755', '512 An Dương Vương, Phường 12, Quận 5'),
('NCC11', 'Nhà Cung Cấp Đạt', '0912345678', '123 An Bình'),
('NCC12', 'Nhà Cung Cấp O', '0913232455', '120 Nguyễn Văn Cừ,Phường 12, Quận 11'),
('NCC13', 'Nhà Cung Cấp P', '0913175122', '120, An Dương Vương, Phường 12,Quận 8'),
('NCC14', 'Nhà Cung Cấp Hà', '0913175755', '12 Hồ Vĩnh Hy,Phường 12,Quận 11'),
('NCC2', 'Nhà Cung Cấp B', '0912345678', '120 Minh Phụng, Phường 10, Quận 11'),
('NCC3', 'Nhà Cung Cấp C', '0912345678', '120 Minh Phụng, Phường 10, Quận 11'),
('NCC4', 'Nhà Cung Cấp D', '0912345678', '120 Minh Phụng, Phường 10, Quận 11'),
('NCC5', 'Nhà Cung Cấp E', '0912345678', '120 Minh Phụng, Phường 10, Quận 11'),
('NCC6', 'Nhà Cung Cấp F', '0912345678', '120 Công Chúa Ngọc Hân\r\nPhường 12 Quận 11'),
('NCC7', 'Nhà Cung Cấp G ', '0912345678', '120 Công Chúa Ngọc Hân\r\nPhường 12 Quận 11'),
('NCC8', 'Nhà Cung Cấp H', '0912345678', '120 Công Chúa Ngọc Hân\r\nPhường 12 Quận 11'),
('NCC9', 'Nhà Cung Cấp J', '0912345678', '120 Công Chúa Ngọc Hân\r\nPhường 12 Quận 11');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `manhanvien` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `hoten` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `gioitinh` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `namsinh` int(20) NOT NULL,
  `sodienthoai` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  `diachi` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `trangthai` varchar(20) COLLATE utf8_unicode_ci DEFAULT 'Còn làm',
  `luong` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ngayvaolam` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `calam` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `imgsource` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`manhanvien`, `hoten`, `gioitinh`, `namsinh`, `sodienthoai`, `diachi`, `trangthai`, `luong`, `ngayvaolam`, `calam`, `imgsource`) VALUES
('NV1', 'Phan Công Hà', 'Nam', 2000, '0916375864', '120 Công Chúa Ngọc Hân', 'Nghỉ làm', '20000', '1589726979', 'Ca chiều', 'NV1.jpg'),
('NV10', 'Lại 1 Ai Đó Trên Mạng', 'Nữ', 1999, '0913175755', 'Ở trên mạng', 'Còn làm', '2999999', '1590835456', 'Ca sáng', 'NV10.jpg'),
('NV11', 'Vũ Thị Hương', 'Nam', 1998, '0913175755', '1238 Đại thế giới,Phường 12,Quận 11', 'Còn làm', '199999', '1590836601', 'Ca sáng', 'default.jpg'),
('NV12', 'Thai Kien Hao', 'Nam', 2000, '0123456789', '273 An Duong Vuong', 'Còn làm', '50000000', '1592907776', 'Ca sáng', 'NV12.jpg'),
('NV13', 'Kien Hai', 'Nam', 2000, '0123456789', '273 An Duong Vuong', 'Còn làm', '20000000', '1592907834', 'Ca sáng', 'NV13.jpg'),
('NV14', 'Toann All Kill', 'Nam', 2003, '0767896626', 'Quan Cam', 'Còn làm', '20000000', '1615450847', 'Ca tối', 'NV14.jpg'),
('NV15', 'exp', 'Nam', 2000, '0906396803', '273 ADV', 'Còn làm', '10000000', '1616055291', 'Ca sáng', 'NV15.jpg'),
('NV2', 'Âu Thị Toàn', 'Nữ', 2000, '0913175755', '120 Công Chúa Ngọc Hân\nPhường 12 Quận 11', 'Còn làm', '2000000', '1590156670', 'Ca chiều', NULL),
('NV3', 'Phan Công Sơn', 'Nam', 2000, '0913175755', '120 Công Chúa Ngọc Hân\nPhường 12 Quận 11', 'Còn làm', '20000', '1590157976', 'Ca sáng', NULL),
('NV4', 'Nguyễn Hữu Nhân', 'Nữ', 2000, '0913175755', '120 Minh Phụng, Phường 10, Quận 11', 'Nghỉ làm', '20000', '1590159721', 'Ca sáng', NULL),
('NV5', 'Thái Kiến Hào', 'Nam', 2000, '0916375645', '120 Minh Phụng, Phường 10, Quận 11', 'Còn làm', '250000', '1590159670', 'Ca sáng', NULL),
('NV6', 'Nguyễn Minh Phương Nam', 'Nam', 2000, '0913175755', '120 Công Chúa Ngọc Hân\nPhường 12 Quận 11', 'Còn làm', '20000', '1590178736', 'Ca tối', NULL),
('NV7', 'Trần Quốc Bảo', 'Nam', 2000, '0913175755', '120 Công Chúa Ngọc Hân\nPhường 12 Quận 11', 'Còn làm', '20000', '1590267523', 'Ca chiều', NULL),
('NV8', 'Tôn Thất Nguyên', 'Nam', 2000, '0913175755', '120 Công Chúa Ngọc Hân\nPhường 12 Quận 11', 'Còn làm', '2000000', '1590756609', 'Ca chiều', NULL),
('NV9', 'Ai Đó Trên Mạng', 'Nam', 2000, '0913175755', 'Ở đâu đó trên mạng3', 'Còn làm', '3000000', '1590796007', 'Ca sáng', 'NV9.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieunhap`
--

CREATE TABLE `phieunhap` (
  `maphieunhap` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `manhanvien` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `manhacungcap` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ngaynhap` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tongtien` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `phieunhap`
--

INSERT INTO `phieunhap` (`maphieunhap`, `manhanvien`, `manhacungcap`, `ngaynhap`, `tongtien`) VALUES
('PN1', 'NV1', 'NCC2', '1590313403', '49000000'),
('PN10', 'NV12', 'NCC1', '1592870400', '4000'),
('PN2', 'NV2', 'NCC4', '1590333836', '85000000'),
('PN3', 'NV3', 'NCC6', '1590924263', '149000000'),
('PN4', 'NV4', 'NCC8', '1590999345', '24000000'),
('PN5', 'NV5', 'NCC1', '1591005361', '10500000'),
('PN6', 'NV6', 'NCC3', '1591021264', '13500000'),
('PN7', 'NV7', 'NCC5', '1591137916', '16500000'),
('PN8', 'NV1', 'NCC7', '1591180233', '19500000'),
('PN9', 'NV2', 'NCC9', '1591228092', '21000000');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `masanpham` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `tensanpham` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `mathuonghieu` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `masize` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `mamau` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `dongia` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `soluong` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `imgsource` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`masanpham`, `tensanpham`, `mathuonghieu`, `masize`, `mamau`, `dongia`, `soluong`, `imgsource`) VALUES
('SP1', 'Nike', 'TH3', 'S4', 'M5', '300000', '0', 'null'),
('SP2', 'Adidas', 'TH4', 'S1', 'M5', '350000', '0', 'null'),
('SP3', 'Nike Air', 'TH3', 'S2', 'M4', '400000', '0', 'null'),
('SP4', 'Bitis Hunter', 'TH2', 'S3', 'M4', '450000', '0', 'null'),
('SP5', 'Bitis', 'TH2', 'S2', 'M2', '500000', '4', 'null'),
('SP6', 'Thượng Đình', 'TH1', 'S3', 'M5', '550000', '0', 'null'),
('SP7', 'Nike', 'TH3', 'S6', 'M2', '600000', '0', 'null'),
('SP8', 'Adidas', 'TH4', 'S1', 'M5', '650000', '0', 'null'),
('SP9', 'Bitis', 'TH2', 'S2', 'M2', '700000', '201', 'null');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanphamkhuyenmai`
--

CREATE TABLE `sanphamkhuyenmai` (
  `machitietkhuyenmai` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `masanpham` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `sanphamkhuyenmai`
--

INSERT INTO `sanphamkhuyenmai` (`machitietkhuyenmai`, `masanpham`) VALUES
('CTKM1', 'SP1'),
('CTKM1', 'SP2'),
('CTKM1', 'SP3'),
('CTKM1', 'SP4'),
('CTKM1', 'SP5'),
('CTKM1', 'SP6'),
('CTKM1', 'SP7'),
('CTKM1', 'SP8'),
('CTKM1', 'SP9'),
('CTKM2', NULL),
('CTKM3', 'SP1'),
('CTKM3', 'SP3'),
('CTKM3', 'SP4'),
('CTKM3', 'SP5'),
('CTKM3', 'SP6');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `size`
--

CREATE TABLE `size` (
  `masize` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tensize` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `size`
--

INSERT INTO `size` (`masize`, `tensize`) VALUES
('S1', '35'),
('S10', '41'),
('S11', '42'),
('S12', '43'),
('S13', '44'),
('S14', '49'),
('S15', '51'),
('S16', '52'),
('S17', '52'),
('S18', '54'),
('S19', '53'),
('S2', '36'),
('S20', '42'),
('S21', '38'),
('S22', '51'),
('S23', '52'),
('S24', '53'),
('S25', '11'),
('S26', '10'),
('S27', '39'),
('S3', '37'),
('S4', '38'),
('S5', '39'),
('S6', '40'),
('S7', '60'),
('S8', '15'),
('S9', '69');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thuonghieu`
--

CREATE TABLE `thuonghieu` (
  `mathuonghieu` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `tenthuonghieu` varchar(150) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `thuonghieu`
--

INSERT INTO `thuonghieu` (`mathuonghieu`, `tenthuonghieu`) VALUES
('TH1', 'Thượng Đỉnh'),
('TH2', 'Bitis'),
('TH3', 'Nike'),
('TH4', 'Adidas'),
('TH5', 'Filla'),
('TH6', 'Puma'),
('TH7', 'Adudu'),
('TH8', 'Balenciaga'),
('TH9', 'Thái');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`username`);

--
-- Chỉ mục cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD KEY `madonhang` (`madonhang`),
  ADD KEY `masanpham` (`masanpham`);

--
-- Chỉ mục cho bảng `chitietkhuyenmai`
--
ALTER TABLE `chitietkhuyenmai`
  ADD PRIMARY KEY (`machitietkhuyenmai`),
  ADD KEY `machuongtrinhkhuyenmai` (`machuongtrinhkhuyenmai`),
  ADD KEY `macode` (`macode`);

--
-- Chỉ mục cho bảng `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD KEY `maphieunhap` (`maphieunhap`),
  ADD KEY `mansanpham` (`masanpham`);

--
-- Chỉ mục cho bảng `chuongtrinhkhuyenmai`
--
ALTER TABLE `chuongtrinhkhuyenmai`
  ADD PRIMARY KEY (`machuongtrinhkhuyenmai`);

--
-- Chỉ mục cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`madonhang`),
  ADD KEY `makhachhang` (`makhachhang`),
  ADD KEY `manhanvien` (`manhanvien`),
  ADD KEY `macode` (`macode`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`makhachhang`);

--
-- Chỉ mục cho bảng `mau`
--
ALTER TABLE `mau`
  ADD PRIMARY KEY (`mamau`);

--
-- Chỉ mục cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`manhacungcap`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`manhanvien`);

--
-- Chỉ mục cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`maphieunhap`),
  ADD KEY `manhanvien` (`manhanvien`),
  ADD KEY `manhacungcap` (`manhacungcap`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`masanpham`),
  ADD KEY `mathuonghieu` (`mathuonghieu`),
  ADD KEY `masize` (`masize`),
  ADD KEY `mamau` (`mamau`);

--
-- Chỉ mục cho bảng `sanphamkhuyenmai`
--
ALTER TABLE `sanphamkhuyenmai`
  ADD KEY `machitietkhuyenmai` (`machitietkhuyenmai`),
  ADD KEY `masanpham` (`masanpham`);

--
-- Chỉ mục cho bảng `size`
--
ALTER TABLE `size`
  ADD PRIMARY KEY (`masize`);

--
-- Chỉ mục cho bảng `thuonghieu`
--
ALTER TABLE `thuonghieu`
  ADD PRIMARY KEY (`mathuonghieu`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD CONSTRAINT `chitietdonhang_hoadon` FOREIGN KEY (`madonhang`) REFERENCES `donhang` (`madonhang`),
  ADD CONSTRAINT `chitietdonhang_masanpham` FOREIGN KEY (`masanpham`) REFERENCES `sanpham` (`masanpham`);

--
-- Các ràng buộc cho bảng `chitietkhuyenmai`
--
ALTER TABLE `chitietkhuyenmai`
  ADD CONSTRAINT `chitietkhuyenmai_chuongtrinhkhuyenmai` FOREIGN KEY (`machuongtrinhkhuyenmai`) REFERENCES `chuongtrinhkhuyenmai` (`machuongtrinhkhuyenmai`);

--
-- Các ràng buộc cho bảng `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD CONSTRAINT `chitietphieunhap_masanpham` FOREIGN KEY (`masanpham`) REFERENCES `sanpham` (`masanpham`),
  ADD CONSTRAINT `chitietphieunhap_phieunhap` FOREIGN KEY (`maphieunhap`) REFERENCES `phieunhap` (`maphieunhap`);

--
-- Các ràng buộc cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD CONSTRAINT `donhang_chitietkhuyenmai` FOREIGN KEY (`macode`) REFERENCES `chitietkhuyenmai` (`macode`),
  ADD CONSTRAINT `donhang_makhachhang` FOREIGN KEY (`makhachhang`) REFERENCES `khachhang` (`makhachhang`),
  ADD CONSTRAINT `donhang_manhanvien` FOREIGN KEY (`manhanvien`) REFERENCES `nhanvien` (`manhanvien`);

--
-- Các ràng buộc cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `phieunhap_ibfk_1` FOREIGN KEY (`manhanvien`) REFERENCES `nhanvien` (`manhanvien`),
  ADD CONSTRAINT `phieunhap_ibfk_2` FOREIGN KEY (`manhacungcap`) REFERENCES `nhacungcap` (`manhacungcap`);

--
-- Các ràng buộc cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `FK_mamau` FOREIGN KEY (`mamau`) REFERENCES `mau` (`mamau`),
  ADD CONSTRAINT `FK_masize` FOREIGN KEY (`masize`) REFERENCES `size` (`masize`),
  ADD CONSTRAINT `FK_mathuonghieu` FOREIGN KEY (`mathuonghieu`) REFERENCES `thuonghieu` (`mathuonghieu`);

--
-- Các ràng buộc cho bảng `sanphamkhuyenmai`
--
ALTER TABLE `sanphamkhuyenmai`
  ADD CONSTRAINT `sanphamkhuyenmai_chitietkhuyenmai` FOREIGN KEY (`machitietkhuyenmai`) REFERENCES `chitietkhuyenmai` (`machitietkhuyenmai`),
  ADD CONSTRAINT `sanphamkhuyenmai_sanpham` FOREIGN KEY (`masanpham`) REFERENCES `sanpham` (`masanpham`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
