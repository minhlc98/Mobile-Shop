-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: May 20, 2019 at 12:48 PM
-- Server version: 10.3.14-MariaDB
-- PHP Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id6644979_dienthoaididong`
--

-- --------------------------------------------------------

--
-- Table structure for table `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `ID` int(11) NOT NULL,
  `ID_DonHang` int(11) NOT NULL,
  `ID_TenSanPham` int(11) NOT NULL,
  `Gia` int(11) NOT NULL,
  `SoLuong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `chitietdonhang`
--

INSERT INTO `chitietdonhang` (`ID`, `ID_DonHang`, `ID_TenSanPham`, `Gia`, `SoLuong`) VALUES
(90, 83, 36, 20990000, 2),
(91, 84, 31, 6490000, 1),
(92, 85, 37, 28790000, 1),
(93, 86, 37, 28790000, 3),
(94, 87, 38, 12990000, 3),
(95, 88, 37, 28790000, 2),
(96, 89, 38, 12990000, 2),
(97, 90, 1, 12490000, 1),
(98, 90, 3, 15990000, 1),
(99, 90, 19, 34790000, 1),
(100, 90, 36, 20990000, 1),
(101, 90, 35, 23990000, 1),
(102, 90, 37, 28790000, 1),
(103, 90, 32, 2990000, 1),
(104, 90, 31, 6490000, 1),
(105, 90, 30, 7290000, 1),
(106, 90, 29, 3390000, 1),
(107, 91, 37, 28790000, 1),
(108, 92, 37, 28790000, 1),
(109, 93, 37, 28790000, 1),
(110, 94, 37, 28790000, 1),
(111, 95, 1, 12490000, 1),
(112, 95, 39, 24990000, 3),
(113, 95, 36, 20990000, 1),
(114, 95, 40, 37990000, 1),
(115, 96, 39, 24990000, 2),
(116, 97, 39, 24990000, 1),
(117, 98, 40, 37990000, 1),
(118, 99, 39, 24990000, 1),
(119, 100, 39, 24990000, 1),
(120, 101, 39, 24990000, 2),
(121, 102, 40, 37990000, 1),
(122, 103, 39, 24990000, 1),
(123, 104, 40, 37990000, 1),
(124, 105, 40, 37990000, 1),
(125, 106, 40, 37990000, 1),
(126, 107, 40, 37990000, 1),
(127, 108, 40, 37990000, 1),
(128, 109, 40, 37990000, 1),
(129, 110, 38, 12990000, 1),
(130, 111, 40, 37990000, 1),
(131, 112, 40, 37990000, 1),
(132, 113, 40, 37990000, 1),
(133, 114, 40, 37990000, 1),
(134, 115, 37, 28790000, 1),
(135, 116, 39, 24990000, 1),
(136, 117, 39, 24990000, 1),
(137, 118, 37, 28790000, 1),
(138, 119, 37, 28790000, 1),
(139, 120, 38, 12990000, 1),
(140, 121, 40, 37990000, 1),
(141, 122, 40, 37990000, 1),
(142, 123, 33, 16990000, 1),
(143, 124, 40, 37990000, 1),
(144, 125, 37, 28790000, 1),
(145, 126, 38, 12990000, 1),
(146, 127, 39, 24990000, 1),
(147, 128, 40, 37990000, 1),
(148, 129, 32, 2990000, 74),
(149, 130, 37, 28790000, 8),
(150, 131, 14, 5690000, 1),
(151, 132, 40, 37990000, 2),
(152, 133, 40, 37990000, 1),
(153, 134, 37, 28790000, 1),
(154, 156, 39, 24990000, 1),
(155, 157, 39, 24990000, 1),
(156, 158, 40, 37990000, 1),
(157, 158, 38, 12990000, 1),
(158, 159, 40, 37990000, 1),
(159, 160, 39, 24990000, 1),
(160, 161, 39, 24990000, 2),
(161, 162, 40, 37990000, 1),
(162, 165, 39, 24990000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `danhgia`
--

CREATE TABLE `danhgia` (
  `ID_NguoiDung` int(11) NOT NULL,
  `ID_SanPham` int(11) NOT NULL,
  `danh_gia` int(11) NOT NULL,
  `tieude` text COLLATE utf8_unicode_ci NOT NULL,
  `noidung` text COLLATE utf8_unicode_ci NOT NULL,
  `thoigian` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `danhgia`
--

INSERT INTO `danhgia` (`ID_NguoiDung`, `ID_SanPham`, `danh_gia`, `tieude`, `noidung`, `thoigian`) VALUES
(1, 38, 5, 'Cực Kì Hài Lòng', 'hacker\' --', '2019-05-12 19:23:07'),
(1, 39, 5, 'Cực Kì Hài Lòng', 'tốt', '2019-05-12 19:03:07'),
(1, 40, 4, 'Giao nhanh, nguyên seal', 'Rất hai long, giao hang nhanh', '2019-05-12 18:20:39'),
(8, 40, 5, 'Cực Kì Hài Lòng', 'Cấu hình rất mạnh, rất thích hợp cho game thủ', '2019-05-11 22:11:34'),
(10, 40, 4, 'Hài Lòng', 'cấu hình rất tốt, giá mắc quá', '2019-05-20 15:00:56');

-- --------------------------------------------------------

--
-- Table structure for table `donhang`
--

CREATE TABLE `donhang` (
  `ID_DonHang` int(11) NOT NULL,
  `TenKH` varchar(50) NOT NULL,
  `DienThoai` varchar(30) NOT NULL,
  `DiaChi` varchar(200) NOT NULL,
  `Email` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `donhang`
--

INSERT INTO `donhang` (`ID_DonHang`, `TenKH`, `DienThoai`, `DiaChi`, `Email`) VALUES
(80, 'Cẩm Minh', '0938965143', '93/2N', 'minhlc98@gmail.com'),
(81, 'Cẩm Minh', '0938965143', '93/2N Phùng Tá Chu', 'minhlc98@gmail.com'),
(82, 'minh', '0938965143', '93', 'minhlc98@gmail.com'),
(83, 'Minh', '0938965143', '99', 'minhlc98@gmail.com'),
(84, 'Cẩm Minh', '0938965143', '93/2N Phùng Tá Chu', 'minhlc98@gmail.com'),
(85, 'Minh', '0938965143', '93/2N', 'minhlc98@gmail.com'),
(86, 'Minh', '0938965143', '93/2', 'minhlc98@gmail.com'),
(87, 'minh', '01285428605', '96', 'minhlc98@gmail.com'),
(88, 'minh', '0938965143', '93', 'minhlc@gmail.com'),
(89, 'minh', '0938965143', '93', 'minhlc98@gmail.com'),
(90, 'minh', '0938965143', '93/2N', 'minhlc98@gmail.com'),
(91, 'minh', '0938965143', '93/2N', 'minhlc98@gmail.com'),
(92, 'minh', '0938965146', '93', 'minhlc98@gmail.com'),
(93, 'minh', '0938965143', '999', 'minhlc98@gmail.com'),
(94, 'Minh', '0938965143', '93', 'minhlc@gmail.com'),
(95, 'minh', '0938965143', '93/2N', 'minhlc98@gmail.com'),
(96, 'Cẩm Minh', '+84938965143', '93/2N Phùng Tá Chu', 'minhlc98@gmail.com'),
(97, 'minh', '+84786111229', '93/2 N', 'minh@gmail.com'),
(98, 'Minhlc', '+84938965143', '93/2N Phùng Tá Chu', 'minhlc98@gmail.com'),
(99, 'm', '+84786111229', '93', 'a@gmail.com'),
(100, 'Cm', '+84938965143', '99', 'a@gmail.com'),
(101, 'minh', '+84786111229', '93', 'a@gmail.com'),
(102, 'm', '+84786111229', '93', 'minhlc98@gmail.com'),
(103, 'minh', '+84938965143', '93/2N', 'minhlc98@gmail.com'),
(104, 'minh', '+84938965143', '93', 'a@gmail.com'),
(105, 'Minh', '+84786111229', '93', 'a@gmail.com'),
(106, 'minh', '+84704553141', '93', 'a@gmail.com'),
(107, 'Phuong', '+84935673869', '123 Hoàng Hoa Thám', 'abc@gmail.com'),
(108, 'nhi', '+84934932108', '93', 'a@gmail.com'),
(109, 'nhi', '+84934932108', '93', 'm@gmail.com'),
(110, 'n', '+84938965143', '9', 'n@gmail.com'),
(111, 'n', '+84938965143', '9', 'a@gmail.com'),
(112, 'minhlc', '+84786111229', '93', 'a@gmail.com'),
(113, 'minh', '+84938965143', '93', 'a@gmail.com'),
(114, 'Minh', '+84938965143', '93/2N ', 'minhlc98@gmail.com'),
(115, 'Minh', '+84938965143', '93/2N Phùng Tá Chu', 'minhlc98@gmail.com'),
(116, 'Cẩm Minh', '+84938965143', '93/2N Phùng Tá Chu', 'minhlc98@gmail.com'),
(117, 'Cẩm Minh', '+84938965143', '93/2N Phùng Tá Chu0', 'minhlc98@gmail.com'),
(118, 'Cẩm Minh', '938965143', '93/2N Phùng Tá Chu', 'minhlc98@gmail.com'),
(119, 'Cẩm Minh', '938965143', '93/2N Phùng Tá Chu', 'minhlc98@gmail.com'),
(120, 'Cẩm Minh', '938965143', '93/2N Phùng Tá Chu', 'minhlc98@gmail.com'),
(121, 'Cẩm Minh', '938965143', '93/2N Phùng Tá Chu', 'minhlc98@gmail.com'),
(122, 'Cẩm Minh', '786111229', '93/2N Phùng Tá Chu', 'minhlc98@gmail.com'),
(123, 'Cẩm Minh', '786111229', '93/2N Phùng Tá Chu', 'minhlc98@gmail.com'),
(124, 'Cẩm Minh', '786111229', '93/2N Phùng Tá Chu', 'minhlc98@gmail.com'),
(125, 'son', '786790082', '93', 'a@gmail.com'),
(126, 'son', '786790082', '93', 'a@gmail.com'),
(127, 'son', '786790082', '93', 'a@gmail.com'),
(128, 'kỳ', '938965143', '93', 'a@gmail.com'),
(129, 'kỳ', '938965143', '93', 'a@gmail.com'),
(130, 'Cẩm Minh', '332072362', '93/2N Phùng Tá Chu', 'minhlc98@gmail.com'),
(131, 'Cẩm Minh', '938965143', '93/2N Phùng Tá Chu', 'minhlc98@gmail.com'),
(132, 'Cẩm Minh', '938965143', '93/2N Phùng Tá Chu', 'minhlc98@gmail.com'),
(133, 'Cẩm Minh', '938965143', '93/2N Phùng Tá Chu', 'minhlc98@gmail.com'),
(134, 'Cẩm Minh', '938965143', '93/2N Phùng Tá Chu', 'minhlc98@gmail.com'),
(135, 'Cẩm Minh', '938965143', '93/2N Phùng Tá Chu', 'minhlc98@gmail.com'),
(136, 'Cẩm Minh', '938965143', '93/2N Phùng Tá Chu', 'minhlc98@gmail.com'),
(137, 'Cẩm Minh', '938965143', '93/2N Phùng Tá Chu', 'minhlc98@gmail.com'),
(138, 'Cẩm Minh', '938965143', '93/2N Phùng Tá Chu', 'minhlc989@gmail.com'),
(139, 'Cẩm Minh', '+84938965143', '93/2N Phùng Tá Chu', 'minhlc989@gmail.com'),
(140, 'Cẩm Minh', '+84938965143', '93/2N Phùng Tá Chu', 'minhlc989@gmail.com'),
(141, 'camminh', '+84938965143', '142', 'mn@gmail.com'),
(142, 'd', '+84938965143', '123', 'mn@gmail.com'),
(143, 'abc', '123', 'ưer', 'minh'),
(144, 'abc', '123', 'ưer', 'minh'),
(145, 'abc', '123', 'ưer', 'minh'),
(146, 'abc', '123', 'ưer', 'minh'),
(147, 'abc', '123', 'ưer', 'minh'),
(148, 'abc', '123', 'ưer', 'minh'),
(149, 'abc', '123', 'ưer', 'minh'),
(150, 'abc', '123', 'ưer', 'minh'),
(151, 'abc', '123', 'ưer', 'minh'),
(152, 'abc', '123', 'ưer', 'minh'),
(153, 'abc', '123', 'ưer', 'minh'),
(154, 'abc', '123', 'ưer', 'minh'),
(155, 'abc', '123', 'ưer', 'minh'),
(156, 'Cẩm Minh', '938965143', '93/2N Phùng Tá Chu', 'minhlc989@gmail.com'),
(157, 'Cẩm Minh', '938965143', '93/2N Phùng Tá Chu', 'minhlc989@gmail.com'),
(158, 'Cẩm Minh', '938965143', '93/2N Phùng Tá Chu', 'minhlc98@gmail.com'),
(159, 'Cẩm Minh', '938965143', '93/2N Phùng Tá Chu', 'minhlc98@gmail.com'),
(160, 'duyên', '0774143273', '528/11A Minh Phụng P9Q11', 'Muftlun11@gmail.com'),
(161, 'Cẩm Minh', '+84938965143', '93/2N Phùng Tá Chu', 'minhlc98@gmail.com'),
(162, 'Cẩm Minh', '+84938965143', '93/2N', 'minhlc98@gmail.com'),
(163, '', '', '', ''),
(164, '', '', '', ''),
(165, 'Nguyễn Văn A', '+84938965143', '93', 'minhlc@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `ID_LoaiSP` int(11) NOT NULL,
  `TenSanPham` varchar(30) NOT NULL,
  `HinhAnh` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `loaisanpham`
--

INSERT INTO `loaisanpham` (`ID_LoaiSP`, `TenSanPham`, `HinhAnh`) VALUES
(1, 'iPhone', 'http://www.iconhot.com/icon/png/crystal/256/apple.png'),
(2, 'SamSung', 'https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Samsung_Logo.svg/1200px-Samsung_Logo.svg.png'),
(3, 'Sony', 'https://orig00.deviantart.net/35ac/f/2014/246/c/3/sony_flurry_icon_by_icecoolish-d7xuhtw.png'),
(4, 'XiaoMi', 'https://seeklogo.com/images/M/mi-mobiles-logo-71C82247EB-seeklogo.com.png'),
(5, 'Oppo', 'https://image.winudf.com/v2/image/Y29tLm9wcG8uZjFzLm9wcG90aGVtZS5sYXVuY2hlcl9pY29uX3NhN3lwY2N5/icon.png?w=170&fakeurl=1&type=.png'),
(6, 'HTC', 'https://www.apkmirror.com/wp-content/uploads/2017/08/599c884b0bdaf-384x384.png');

-- --------------------------------------------------------

--
-- Table structure for table `nguoidung`
--

CREATE TABLE `nguoidung` (
  `ID` int(11) NOT NULL,
  `TenKH` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `taikhoan` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `matkhau` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `diachi` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `maquocgia` int(11) NOT NULL,
  `dienthoai` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `gioitinh` tinyint(1) NOT NULL,
  `hinh` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `nguoidung`
--

INSERT INTO `nguoidung` (`ID`, `TenKH`, `taikhoan`, `matkhau`, `diachi`, `email`, `maquocgia`, `dienthoai`, `gioitinh`, `hinh`) VALUES
(1, 'Cẩm Minhh', 'minhlc', 'f8a8df42cecd3d08761593f4fcdf8247828ac91e', '93/2N', 'minhlc98@gmail.com', 0, '938965143', 1, 'image-user/received_24508033716603401556278522711.jpeg'),
(2, 'Nguyễn Văn A', 'nva', '981d876b56cc4d26f203b94a51d379bda6abdb53', '', '', 0, '', 1, 'image-user/IMG_UPLOAD_20190322_1822511554443205702.jpg'),
(3, 'Cẩm Minh', 'minhlc98', 'f8a8df42cecd3d08761593f4fcdf8247828ac91e', '923', 'minhlc98@gmail.com', 0, '938965143', 1, 'image-user/Blank-profile.png'),
(4, 'Minh', 'camminh102', 'f8a8df42cecd3d08761593f4fcdf8247828ac91e', '', '', 0, '938965143', 1, 'image-user/Blank-profile.png'),
(5, 'm', 'm', '6b0d31c0d563223024da45691584643ac78c96e8', '', '', 0, '938965143', 1, 'image-user/Blank-profile.png'),
(6, 'c', 'c', '84a516841ba77a5b4648de2cd0dfcb30ea46dbb4', '', '', 0, '123', 1, 'image-user/Blank-profile.png'),
(7, 'a', 'a', '86f7e437faa5a7fce15d1ddcb9eaeaea377667b8', '', '', 0, '', 1, 'image-user/Blank-profile.png'),
(8, 'Nguyễn Văn A', 'anv', '05f6a964ac5c9c1e5d8be6199aa55c9f0c3b60f2', '', '', 0, '938965143', 1, 'image-user/FB_IMG_15521908283661557501952893.jpg'),
(9, 'Nguyễn Văn Tèo', 'teonv', '8abaccba49b05a88e823ab7567d140e55de5ba0c', '93', 'teo', 0, '938965143', 1, 'image-user/Blank-profile.png'),
(10, 'Nguyễn Thị Linh', 'linh', 'e9d632ba8c41abab90cc9e171aa3117c18707868', '93', 'linh@gmail.com', 0, '938965143', 0, 'image-user/Blank-profile.png'),
(11, 'Nguyễn Thị Linh', 'linhnt\' --', 'cd3b511c70e72659dc2e682bd197a3dae462e219', '93', '', 0, '938965143', 0, 'image-user/Blank-profile.png'),
(12, 'Cẩm Minh', 'minh\' --', 'b29025a795c4f5ea28fd2d7270a5600fe8099077', '93', '', 0, '938965143', 1, 'image-user/IMG_UPLOAD_20190322_1822511557666927827.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `quangcao`
--

CREATE TABLE `quangcao` (
  `ID` int(11) NOT NULL,
  `URL_QuangCao` varchar(150) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `quangcao`
--

INSERT INTO `quangcao` (`ID`, `URL_QuangCao`) VALUES
(1, 'https://www.lowyat.net/wp-content/uploads/2018/10/iphone-xs-max-unboxing-02-770x533.jpg'),
(2, 'https://hoanghamobile.com/tin-tuc/wp-content/uploads/2018/11/cau-hinh-galaxy-m20-1.jpg'),
(3, 'https://akm-img-a-in.tosshub.com/indiatoday/images/story/201808/oppo_f9_pro_0_0.jpeg?eEGzefGRNFaKYriWS0jUBT1c0E8Xv7GU'),
(4, 'https://lh3.googleusercontent.com/pWSdJz54U0_E_ymjFtRGUwGQ5Nchpnk0YQ0J-feA0GXWBlY4iSXEzAK3bJUVw7yRBqliJkcxuw=w640-h400-e365'),
(5, 'https://i.ytimg.com/vi/Or9dgtsRK-A/maxresdefault.jpg'),
(6, 'https://i.ytimg.com/vi/OIGmsOwOE3g/maxresdefault.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `ID` int(11) NOT NULL,
  `TenSP` varchar(80) NOT NULL,
  `Gia` int(11) NOT NULL,
  `HinhAnh` varchar(150) NOT NULL,
  `ID_LoaiSP` int(11) NOT NULL,
  `MoTa` text NOT NULL,
  `total_rating` int(11) NOT NULL,
  `rating_count` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`ID`, `TenSP`, `Gia`, `HinhAnh`, `ID_LoaiSP`, `MoTa`, `total_rating`, `rating_count`) VALUES
(1, 'Điện thoại iPhone 6s 32GB', 12490000, 'https://cdn.tgdd.vn/Products/Images/42/87842/iphone-6s-32gb-vang-dong-2-400x460.png', 1, 'Công nghệ màn hình LED-backlit IPS LCD\r\nĐộ phân giải HD (1334 x 750 Pixels)\r\nMàn hình rộng 4.7\"\r\nMặt kính cảm ứng Kính oleophobic (ion cường lực)\r\nCamera sau\r\nĐộ phân giải 12 MP\r\nQuay phim Quay phim 4K 2160p@30fps\r\nĐèn Flash Có\r\nChụp ảnh nâng cao Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama\r\nCamera trước\r\nĐộ phân giải 5 MP\r\nVideocall Có\r\nThông tin khác Selfie ngược sáng HDR, Tự động cân bằng sáng, Nhận diện khuôn mặt\r\nHệ điều hành - CPU\r\nHệ điều hành iOS 11\r\nChipset (hãng SX CPU) Apple A9 2 nhân 64-bit\r\nTốc độ CPU 1.8 GHz\r\nChip đồ họa (GPU) PowerVR GT7600\r\nBộ nhớ & Lưu trữ\r\nRAM 2 GB\r\nBộ nhớ trong 32 GB\r\nBộ nhớ còn lại (khả dụng) Khoảng 28 GB\r\nThẻ nhớ ngoài Không\r\nKết nối\r\nMạng di động 3G, 4G LTE Cat 6\r\nSIM 1 Nano SIM\r\nWifi Wi-Fi 802.11 a/b/g/n/ac, Dual-band, DLNA, Wi-Fi Direct, Wi-Fi hotspot\r\nGPS A-GPS, GLONASS\r\nBluetooth A2DP, V4.1\r\nCổng kết nối/sạc Lightning\r\nJack tai nghe 3.5 mm\r\nKết nối khác Air Play, OTG, HDMI\r\nThiết kế & Trọng lượng\r\nThiết kế Nguyên khối, mặt kính cong 2.5D\r\nChất liệu Hợp kim nhôm\r\nKích thước Dài 138.3 mm - Ngang 67.1 mm - Dày 7.1 mm\r\nTrọng lượng 143 g\r\nThông tin pin & Sạc\r\nDung lượng pin 1715 mAh\r\nLoại pin Pin chuẩn Li-Po\r\nCông nghệ pin Tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao Mở khóa bằng vân tay\r\nTính năng đặc biệt Mặt kính 2.5D\r\n3D Touch\r\nGhi âm Có\r\nRadio Không\r\nXem phim MP4, AVI, WMV, H.263, H.264(MPEG4-AVC), DivX\r\nNghe nhạc MP3, WAV, WMA\r\nThông tin khác\r\nThời điểm ra mắt 11/2015\r\n', 0, 0),
(2, 'Điện thoại iPhone 6 32GB', 7490000, 'https://cdn.tgdd.vn/Products/Images/42/92962/iphone-6-32gb-vang-400-400x460.png', 1, 'Công nghệ màn hình LED-backlit IPS LCD\r\nĐộ phân giải HD (1334 x 750 Pixels)\r\nMàn hình rộng 4.7\"\r\nMặt kính cảm ứng Kính oleophobic (ion cường lực)\r\nCamera sau\r\nĐộ phân giải 8 MP\r\nQuay phim Quay phim FullHD 1080p@60fps\r\nĐèn Flash Có\r\nChụp ảnh nâng cao Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama\r\nCamera trước\r\nĐộ phân giải 1.2 MP\r\nVideocall Có\r\nThông tin khác Tự động lấy nét\r\nHệ điều hành - CPU\r\nHệ điều hành iOS 11\r\nChipset (hãng SX CPU) Apple A8 2 nhân 64-bit\r\nTốc độ CPU 1.4 GHz\r\nChip đồ họa (GPU) PowerVR GX6450\r\nBộ nhớ & Lưu trữ\r\nRAM 1 GB\r\nBộ nhớ trong 32 GB\r\nBộ nhớ còn lại (khả dụng) Khoảng 26 GB\r\nThẻ nhớ ngoài Không\r\nKết nối\r\nMạng di động 3G, 4G LTE Cat 4\r\nSIM 1 Nano SIM\r\nWifi Wi-Fi 802.11 a/b/g/n/ac, Dual-band, Wi-Fi Direct, Wi-Fi hotspot\r\nGPS Có, A-GPS\r\nBluetooth V4.0, A2DP\r\nCổng kết nối/sạc Lightning\r\nJack tai nghe 3.5 mm\r\nKết nối khác Air Play, OTG, HDMI\r\nThiết kế & Trọng lượng\r\nThiết kế Nguyên khối\r\nChất liệu Hợp kim nhôm\r\nKích thước Dài 138.1 mm - Ngang 67 mm - Dày 6.9 mm\r\nTrọng lượng 124 g\r\nThông tin pin & Sạc\r\nDung lượng pin 1810 mAh\r\nLoại pin Pin chuẩn Li-Po\r\nCông nghệ pin Tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao Mở khóa bằng vân tay\r\nTính năng đặc biệt Mặt kính 2.5D\r\nGhi âm Có\r\nRadio Không\r\nXem phim 3GP, MP4, AVI, WMV\r\nNghe nhạc Lossless, MP3, WAV, WMA\r\nThông tin khác\r\nThời điểm ra mắt 3/2017\r\n', 0, 0),
(3, 'Điện thoại iPhone 7 32GB', 15990000, 'https://cdn.tgdd.vn/Products/Images/42/74110/iphone-7-32gb-den-400x460.png', 1, 'Công nghệ màn hình LED-backlit IPS LCD\r\nĐộ phân giải HD (1334 x 750 Pixels)\r\nMàn hình rộng 4.7\"\r\nMặt kính cảm ứng Kính oleophobic (ion cường lực)\r\nCamera sau\r\nĐộ phân giải 12 MP\r\nQuay phim Quay phim 4K 2160p@30fps\r\nĐèn Flash 4 đèn LED (2 tông màu)\r\nChụp ảnh nâng cao Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama, Chống rung quang học (OIS)\r\nCamera trước\r\nĐộ phân giải 7 MP\r\nVideocall Hỗ trợ VideoCall thông qua ứng dụng\r\nThông tin khác Nhận diện khuôn mặt, Quay video Full HD, Tự động lấy nét, Selfie ngược sáng HDR, Retina Flash, Panorama\r\nHệ điều hành - CPU\r\nHệ điều hành iOS 11\r\nChipset (hãng SX CPU) Apple A10 Fusion 4 nhân 64-bit\r\nTốc độ CPU 2.3 GHz\r\nChip đồ họa (GPU) Chip đồ họa 6 nhân\r\nBộ nhớ & Lưu trữ\r\nRAM 2 GB\r\nBộ nhớ trong 32 GB\r\nBộ nhớ còn lại (khả dụng) Khoảng 28 GB\r\nThẻ nhớ ngoài Không\r\nKết nối\r\nMạng di động 3G, 4G LTE Cat 9\r\nSIM 1 Nano SIM\r\nWifi Wi-Fi 802.11 a/b/g/n/ac, Dual-band, Wi-Fi hotspot\r\nGPS A-GPS, GLONASS\r\nBluetooth v4.2, A2DP, LE\r\nCổng kết nối/sạc Lightning\r\nJack tai nghe Không\r\nKết nối khác Air Play, NFC, OTG, HDMI\r\nThiết kế & Trọng lượng\r\nThiết kế Nguyên khối, mặt kính cong 2.5D\r\nChất liệu Hợp kim Nhôm + Magie\r\nKích thước Dài 138.3 mm - Ngang 67.1 mm - Dày 7.1 mm\r\nTrọng lượng 138 g\r\nThông tin pin & Sạc\r\nDung lượng pin 1960 mAh\r\nLoại pin Pin chuẩn Li-Ion\r\nCông nghệ pin Tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao Mở khóa bằng vân tay\r\nTính năng đặc biệt Chuẩn Kháng nước, Chuẩn kháng bụi\r\n3D Touch\r\nGhi âm Có, microphone chuyên dụng chống ồn\r\nRadio Không\r\nXem phim H.265, MP4, AVI, H.264(MPEG4-AVC), DivX, Xvid\r\nNghe nhạc Lossless, MP3, WAV, AAC, FLAC\r\nThông tin khác\r\nThời điểm ra mắt 11/2016\r\n', 0, 0),
(4, 'Điện thoại Samsung Galaxy J2 Prime', 2690000, 'https://cdn.tgdd.vn/Products/Images/42/88573/samsung-galaxy-j2-prime-1-400x460.png', 2, 'Công nghệ màn hình PLS TFT LCD\r\nĐộ phân giải DVGA (540 x 960 pixels)\r\nMàn hình rộng 5\"\r\nMặt kính cảm ứng Kính thường\r\nCamera sau\r\nĐộ phân giải 8 MP\r\nQuay phim Quay phim HD 720p@30fps\r\nĐèn Flash Có\r\nChụp ảnh nâng cao Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt\r\nCamera trước\r\nĐộ phân giải 5 MP\r\nVideocall Hỗ trợ VideoCall thông qua ứng dụng\r\nThông tin khác Chế độ làm đẹp, Đèn Flash trợ sáng\r\nHệ điều hành - CPU\r\nHệ điều hành Android 6.0 (Marshmallow)\r\nChipset (hãng SX CPU) MT6737 4 nhân\r\nTốc độ CPU 1.4 GHz\r\nChip đồ họa (GPU) Mali T720\r\nBộ nhớ & Lưu trữ\r\nRAM 1.5 GB\r\nBộ nhớ trong 8 GB\r\nBộ nhớ còn lại (khả dụng) 3.3 GB\r\nThẻ nhớ ngoài MicroSD, hỗ trợ tối đa 256 GB\r\nKết nối\r\nMạng di động 3G, 4G LTE Cat 4\r\nSIM 2 Micro SIM\r\nWifi Wi-Fi 802.11 b/g/n, Wi-Fi Direct, Wi-Fi hotspot\r\nGPS A-GPS, GLONASS\r\nBluetooth v4.2, A2DP\r\nCổng kết nối/sạc Micro USB\r\nJack tai nghe 3.5 mm\r\nKết nối khác OTG\r\nThiết kế & Trọng lượng\r\nThiết kế Pin rời\r\nChất liệu Nhựa\r\nKích thước Dài 144.8 mm - Ngang 72.1 mm - Dày 8.9 mm\r\nTrọng lượng 160 g\r\nThông tin pin & Sạc\r\nDung lượng pin 2600 mAh\r\nLoại pin Pin chuẩn Li-Ion\r\nCông nghệ pin Tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao Không\r\nTính năng đặc biệt Không\r\nGhi âm Có\r\nRadio FM radio với RDS\r\nXem phim 3GP, MP4, AVI, WMV, H.264(MPEG4-AVC)\r\nNghe nhạc Midi, MP3, WAV, WMA, AAC, FLAC\r\nThông tin khác\r\nThời điểm ra mắt 12/2016\r\n', 0, 0),
(5, 'Điện thoại Samsung Galaxy J7 Prime', 4990000, 'https://cdn.tgdd.vn/Products/Images/42/84798/samsung-galaxy-j7-prime-2-400x460.png', 2, 'Công nghệ màn hình  PLS TFT LCD\r\nĐộ phân giải  Full HD (1080 x 1920 pixels)\r\nMàn hình rộng  5.5\"\r\nMặt kính cảm ứng  Kính cường lực Gorilla Glass 4\r\nCamera sau\r\nĐộ phân giải  13 MP\r\nQuay phim  Quay phim FullHD 1080p@30fps\r\nĐèn Flash  Có\r\nChụp ảnh nâng cao  Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama, Chế độ chụp chuyên nghiệp\r\nCamera trước\r\nĐộ phân giải  8 MP\r\nVideocall  Có\r\nThông tin khác  Selfie bằng cử chỉ, Chế độ làm đẹp, Camera góc rộng, Nhận diện khuôn mặt\r\nHệ điều hành - CPU\r\nHệ điều hành  Android 6.0 (Marshmallow)\r\nChipset (hãng SX CPU)  Exynos 7870 8 nhân 64-bit\r\nTốc độ CPU  1.6 GHz\r\nChip đồ họa (GPU)  Mali-T830\r\nBộ nhớ & Lưu trữ\r\nRAM  3 GB\r\nBộ nhớ trong  32 GB\r\nBộ nhớ còn lại (khả dụng)  25.3 GB\r\nThẻ nhớ ngoài  MicroSD, hỗ trợ tối đa 256 GB\r\nKết nối\r\nMạng di động  Có hỗ trợ 4G\r\nSIM  2 Nano SIM\r\nWifi  Wi-Fi 802.11 b/g/n, Wi-Fi hotspot\r\nGPS  A-GPS, GLONASS\r\nBluetooth  Có\r\nCổng kết nối/sạc  Micro USB\r\nJack tai nghe  3.5 mm\r\nKết nối khác  OTG\r\nThiết kế & Trọng lượng\r\nThiết kế  Nguyên khối, mặt kính cong 2.5D\r\nChất liệu  Hợp kim nhôm\r\nKích thước  Dài 151.5 mm - Ngang 74.9 mm - Dày 8.1 mm.\r\nTrọng lượng  167 g\r\nThông tin pin & Sạc\r\nDung lượng pin  3300 mAh\r\nLoại pin  Pin chuẩn Li-Ion\r\nCông nghệ pin  Tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao  Mở khóa bằng vân tay\r\nTính năng đặc biệt  Mặt kính 2.5D\r\nGhi âm  Có\r\nRadio  Có\r\nXem phim  H.265, 3GP, MP4, AVI, WMV, H.264(MPEG4-AVC), DivX, WMV9, Xvid\r\nNghe nhạc  Lossless, MP3, WAV, WMA\r\nThông tin khác\r\nThời điểm ra mắt  9/2016\r\n', 0, 0),
(6, 'Điện thoại Samsung Galaxy S8 Plus', 17990000, 'https://cdn.tgdd.vn/Products/Images/42/91131/samsung-galaxy-s8-plus-tim-2-400x460.png', 2, 'Công nghệ màn hình Super AMOLED\r\nĐộ phân giải 2K+ (1440 x 2960 Pixels)\r\nMàn hình rộng 6.2\"\r\nMặt kính cảm ứng Corning Gorilla Glass 5\r\nCamera sau\r\nĐộ phân giải 12 MP\r\nQuay phim Quay phim 4K 2160p@30fps\r\nĐèn Flash Đèn LED 2 tông màu\r\nChụp ảnh nâng cao Chụp phơi sáng, Chụp ảnh xóa phông, Chụp trước lấy nét sau, Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama, Chống rung quang học (OIS), Chế độ chụp chuyên nghiệp\r\nCamera trước\r\nĐộ phân giải 8 MP\r\nVideocall Có\r\nThông tin khác Camera góc rộng, Chế độ làm đẹp, Nhận diện khuôn mặt, Flash màn hình, Selfie bằng cử chỉ\r\nHệ điều hành - CPU\r\nHệ điều hành Android 7.0 (Nougat)\r\nChipset (hãng SX CPU) Exynos 8895 8 nhân 64-bit\r\nTốc độ CPU 4 nhân 2.3 GHz và 4 nhân 1.7 GHz\r\nChip đồ họa (GPU) Mali™ G71\r\nBộ nhớ & Lưu trữ\r\nRAM 4 GB\r\nBộ nhớ trong 64 GB\r\nBộ nhớ còn lại (khả dụng) 52.3 GB\r\nThẻ nhớ ngoài MicroSD, hỗ trợ tối đa 256 GB\r\nKết nối\r\nMạng di động 3G, 4G LTE Cat 16\r\nSIM 2 SIM Nano (SIM 2 chung khe thẻ nhớ)\r\nWifi Wi-Fi 802.11 a/b/g/n/ac, Dual-band, DLNA, Wi-Fi Direct, Wi-Fi hotspot\r\nGPS A-GPS, GLONASS\r\nBluetooth v5.0, apt-X, A2DP, LE, EDR\r\nCổng kết nối/sạc USB Type-C\r\nJack tai nghe 3.5 mm\r\nKết nối khác Kết nối nhanh™, OTG\r\nThiết kế & Trọng lượng\r\nThiết kế Nguyên khối\r\nChất liệu Khung kim loại + mặt kính cường lực\r\nKích thước Dài 159.5 mm - Ngang 73.4 mm - Dày 8.1 mm\r\nTrọng lượng 173 g\r\nThông tin pin & Sạc\r\nDung lượng pin 3500 mAh\r\nLoại pin Pin chuẩn Li-Ion\r\nCông nghệ pin Sạc pin nhanh, Sạc pin không dây\r\nTiện ích\r\nBảo mật nâng cao Quét mống mắt, Mở khóa bằng vân tay\r\nTính năng đặc biệt Sạc pin nhanh\r\nChuẩn Kháng nước, Chuẩn kháng bụi\r\nGhi âm Có, microphone chuyên dụng chống ồn\r\nRadio Không\r\nXem phim H.265, 3GP, MP4, AVI, WMV\r\nNghe nhạc Lossless, Midi, MP3, WAV, WMA\r\nThông tin khác\r\nThời điểm ra mắt 4/2017\r\n', 0, 0),
(7, 'Điện thoại Sony Xperia XZ1', 10990000, 'https://cdn.tgdd.vn/Products/Images/42/108680/sony-xperia-xz1-xanh-2-400x460.png', 3, 'Công nghệ màn hình IPS HDR LCD\r\nĐộ phân giải Full HD (1080 x 1920 pixels)\r\nMàn hình rộng 5.2\"\r\nMặt kính cảm ứng Corning Gorilla Glass 5\r\nCamera sau\r\nĐộ phân giải 19 MP\r\nQuay phim Quay phim siêu chậm 960 fps, Quay phim 4K 2160p@30fps\r\nĐèn Flash Có\r\nChụp ảnh nâng cao Chống rung kỹ thuật số (EIS), Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama\r\nCamera trước\r\nĐộ phân giải 13 MP\r\nVideocall Hỗ trợ VideoCall thông qua ứng dụng\r\nThông tin khác Nhận diện khuôn mặt, Chế độ làm đẹp, Quay video Full HD, Camera góc rộng\r\nHệ điều hành - CPU\r\nHệ điều hành Android 8.0 (Oreo)\r\nChipset (hãng SX CPU) Qualcomm Snapdragon 835 8 nhân 64-bit\r\nTốc độ CPU 4 nhân 2.45 GHz Kryo & 4 nhân 1.9 GHz Kryo\r\nChip đồ họa (GPU) Adreno 540\r\nBộ nhớ & Lưu trữ\r\nRAM 4 GB\r\nBộ nhớ trong 64 GB\r\nBộ nhớ còn lại (khả dụng) Khoảng 53 GB\r\nThẻ nhớ ngoài MicroSD, hỗ trợ tối đa 256 GB\r\nKết nối\r\nMạng di động 3G, 4G LTE Cat 16\r\nSIM 2 Nano SIM\r\nWifi Wi-Fi 802.11 a/b/g/n/ac, Dual-band, DLNA, Wi-Fi Direct, Wi-Fi hotspot\r\nGPS A-GPS, GLONASS\r\nBluetooth v5.0, apt-X, A2DP, LE, EDR\r\nCổng kết nối/sạc USB Type-C\r\nJack tai nghe 3.5 mm\r\nKết nối khác NFC, OTG\r\nThiết kế & Trọng lượng\r\nThiết kế Nguyên khối\r\nChất liệu Kim loại\r\nKích thước Dài 148 mm - Ngang 73.4 mm - Dày 7.4 mm\r\nTrọng lượng 155 g\r\nThông tin pin & Sạc\r\nDung lượng pin 2700 mAh\r\nLoại pin Pin chuẩn Li-Ion\r\nCông nghệ pin Sạc nhanh Quick Charge 3.0, Siêu tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao Mở khóa bằng vân tay\r\nTính năng đặc biệt Chuẩn Kháng nước, Chuẩn kháng bụi\r\nGhi âm Có, microphone chuyên dụng chống ồn\r\nRadio Không\r\nXem phim H.265, 3GP, MP4, AVI, WMV, H.263, H.264(MPEG4-AVC), DivX\r\nNghe nhạc Lossless, Midi, MP3, WAV, WMA, WMA9, AAC, AAC+, AAC++\r\nThông tin khác\r\nThời điểm ra mắt 8/2017\r\n', 0, 0),
(8, 'Điện thoại Sony Xperia XA1', 3990000, 'https://cdn.tgdd.vn/Products/Images/42/92081/sony-xperia-xa1-400x4601-400x460.png', 3, 'Công nghệ màn hình IPS LCD\r\nĐộ phân giải HD (720 x 1280 pixels)\r\nMàn hình rộng 5\"\r\nMặt kính cảm ứng Kính chống xước\r\nCamera sau\r\nĐộ phân giải 23 MP\r\nQuay phim Quay phim FullHD 1080p@30fps\r\nĐèn Flash Có\r\nChụp ảnh nâng cao Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama\r\nCamera trước\r\nĐộ phân giải 8 MP\r\nVideocall Hỗ trợ VideoCall thông qua ứng dụng\r\nThông tin khác Camera góc rộng, Quay video Full HD, Chế độ làm đẹp, Nhận diện khuôn mặt\r\nHệ điều hành - CPU\r\nHệ điều hành Android 7.0 (Nougat)\r\nChipset (hãng SX CPU) Mediatek Helio P20\r\nTốc độ CPU 4 nhân 2.3 GHz và 4 nhân 1.7 GHz\r\nChip đồ họa (GPU) Mali-T880 MP4\r\nBộ nhớ & Lưu trữ\r\nRAM 3 GB\r\nBộ nhớ trong 32 GB\r\nBộ nhớ còn lại (khả dụng) Khoảng 20 GB\r\nThẻ nhớ ngoài MicroSD, hỗ trợ tối đa 256 GB\r\nKết nối\r\nMạng di động 3G, 4G LTE Cat 6\r\nSIM 2 Nano SIM\r\nWifi Wi-Fi 802.11 b/g/n, Wi-Fi Direct, Wi-Fi hotspot\r\nGPS A-GPS, GLONASS\r\nBluetooth v4.2, apt-X, A2DP, LE\r\nCổng kết nối/sạc USB Type-C\r\nJack tai nghe 3.5 mm\r\nKết nối khác NFC, OTG, Miracast\r\nThiết kế & Trọng lượng\r\nThiết kế Nguyên khối\r\nChất liệu Nhựa, nhôm\r\nKích thước Dài 145 mm - Ngang 67 mm - Dày 8 mm\r\nTrọng lượng 143 g\r\nThông tin pin & Sạc\r\nDung lượng pin 2300 mAh\r\nLoại pin Pin chuẩn Li-Ion\r\nCông nghệ pin Sạc pin nhanh, Siêu tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao Không\r\nTính năng đặc biệt Mặt kính 2.5D\r\nDolby Audio™\r\nGhi âm Có, microphone chuyên dụng chống ồn\r\nRadio Có\r\nXem phim H.265, 3GP, MP4, AVI, WMV\r\nNghe nhạc Midi, Lossless, MP3, WAV, WMA\r\nThông tin khác\r\nThời điểm ra mắt 3/2017\r\n', 0, 0),
(9, 'Điện thoại Sony Xperia XA1 Plus', 5990000, 'https://cdn.tgdd.vn/Products/Images/42/112833/sony-xepria-xa1-plus-1-400x460.png', 3, 'Công nghệ màn hình IPS LCD\r\nĐộ phân giải Full HD (1080 x 1920 pixels)\r\nMàn hình rộng 5.5\"\r\nMặt kính cảm ứng Kính cường lực\r\nCamera sau\r\nĐộ phân giải 23 MP\r\nQuay phim Quay phim FullHD 1080p@30fps\r\nĐèn Flash Có\r\nChụp ảnh nâng cao Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama\r\nCamera trước\r\nĐộ phân giải 8 MP\r\nVideocall Có\r\nThông tin khác Quay video Full HD, Camera góc rộng, Nhận diện khuôn mặt, Chế độ làm đẹp\r\nHệ điều hành - CPU\r\nHệ điều hành Android 7.0 (Nougat)\r\nChipset (hãng SX CPU) Mediatek Helio P20\r\nTốc độ CPU 4 nhân 2.3 GHz + 4 nhân 1.6 GHz\r\nChip đồ họa (GPU) Mali-T880MP2\r\nBộ nhớ & Lưu trữ\r\nRAM 4 GB\r\nBộ nhớ trong 32 GB\r\nBộ nhớ còn lại (khả dụng) 21 GB\r\nThẻ nhớ ngoài MicroSD, hỗ trợ tối đa 256 GB\r\nKết nối\r\nMạng di động 3G, 4G LTE Cat 6\r\nSIM 2 Nano SIM\r\nWifi Wi-Fi 802.11 b/g/n, Wi-Fi Direct, Wi-Fi hotspot\r\nGPS A-GPS, GLONASS\r\nBluetooth v4.2, apt-X, A2DP, LE\r\nCổng kết nối/sạc USB Type-C\r\nJack tai nghe 3.5 mm\r\nKết nối khác NFC, Miracast\r\nThiết kế & Trọng lượng\r\nThiết kế Nguyên khối\r\nChất liệu Vỏ nhựa + khung nhôm\r\nKích thước Dài 155 mm - Ngang 75 mm - Dày 8.7 mm\r\nTrọng lượng 189 g\r\nThông tin pin & Sạc\r\nDung lượng pin 3430 mAh\r\nLoại pin Pin chuẩn Li-Ion\r\nCông nghệ pin Sạc pin nhanh, Siêu tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao Mở khóa bằng vân tay\r\nTính năng đặc biệt Ghi âm cuộc gọi, Chặn cuộc gọi, Sạc pin nhanh, Mặt kính 2.5D\r\nGhi âm Không\r\nRadio Có\r\nXem phim H.265, 3GP, MP4, AVI, WMV, H.263\r\nNghe nhạc Lossless, Midi, MP3, WAV, WMA9, WMA, AAC, AAC+, AAC++, eAAC+, OGG\r\nThông tin khác\r\nThời điểm ra mắt 8/2017\r\n', 0, 0),
(10, 'Điện thoại Samsung Galaxy J7 Pro', 6090000, 'https://cdn.tgdd.vn/Products/Images/42/103404/samsung-galaxy-j7-pro-6-400x460.png', 2, 'Công nghệ màn hình Super AMOLED\r\nĐộ phân giải Full HD (1080 x 1920 pixels)\r\nMàn hình rộng 5.5\"\r\nMặt kính cảm ứng Kính cường lực Gorilla Glass 4\r\nCamera sau\r\nĐộ phân giải 13 MP\r\nQuay phim Có quay phim\r\nĐèn Flash Có\r\nChụp ảnh nâng cao Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama, Chế độ chụp chuyên nghiệp\r\nCamera trước\r\nĐộ phân giải 13 MP\r\nVideocall Có\r\nThông tin khác Chế độ làm đẹp, Selfie bằng cử chỉ, Nhận diện khuôn mặt, Camera góc rộng\r\nHệ điều hành - CPU\r\nHệ điều hành Android 7.0 (Nougat)\r\nChipset (hãng SX CPU) Exynos 7870 8 nhân 64-bit\r\nTốc độ CPU 1.6 GHz\r\nChip đồ họa (GPU) Mali-T830\r\nBộ nhớ & Lưu trữ\r\nRAM 3 GB\r\nBộ nhớ trong 32 GB\r\nBộ nhớ còn lại (khả dụng) 24.4 GB\r\nThẻ nhớ ngoài MicroSD, hỗ trợ tối đa 256 GB\r\nKết nối\r\nMạng di động 3G, 4G LTE Cat 6\r\nSIM 2 Nano SIM\r\nWifi Wi-Fi 802.11 b/g/n, Wi-Fi hotspot\r\nGPS A-GPS, GLONASS\r\nBluetooth Có\r\nCổng kết nối/sạc Micro USB\r\nJack tai nghe 3.5 mm\r\nKết nối khác OTG\r\nThiết kế & Trọng lượng\r\nThiết kế Nguyên khối, mặt kính cong 2.5D\r\nChất liệu Hợp kim nhôm\r\nKích thước Dài 152.4mm - Ngang 74.7m - Dày 7.9mm\r\nTrọng lượng 181 g\r\nThông tin pin & Sạc\r\nDung lượng pin 3600 mAh\r\nLoại pin Pin chuẩn Li-Ion\r\nCông nghệ pin Tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao Mở khóa bằng vân tay\r\nTính năng đặc biệt Màn hình luôn hiển thị AOD\r\nMặt kính 2.5D\r\nGhi âm Có\r\nRadio Có\r\nXem phim H.265, 3GP, MP4, AVI, WMV, H.264(MPEG4-AVC), DivX, WMV9, Xvid\r\nNghe nhạc Lossless, MP3, WAV, WMA\r\nThông tin khác\r\nThời điểm ra mắt 6/2017\r\n', 0, 0),
(11, 'Điện thoại Samsung Galaxy C9 Pro', 8990000, 'https://cdn.tgdd.vn/Products/Images/42/88397/samsung-galaxy-c9-pro-1-400x460.png', 2, 'Công nghệ màn hình : Super AMOLED\r\nĐộ phân giải : Full HD (1080 x 1920 pixels)\r\nMàn hình rộng : 6.0\"\r\nMặt kính cảm ứng : Kính cường lực Gorilla Glass 4\r\nCamera sau\r\nĐộ phân giải : 16 MP\r\nQuay phim : Quay phim FullHD 1080p@30fps\r\nĐèn Flash : Đèn LED 2 tông màu\r\nChụp ảnh nâng cao : Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama\r\nCamera trước\r\nĐộ phân giải : 16 MP\r\nVideocall : Hỗ trợ VideoCall thông qua ứng dụng\r\nThông tin khác : Camera góc rộng, Quay video Full HD, Nhận diện khuôn mặt, Chế độ làm đẹp\r\nHệ điều hành - CPU\r\nHệ điều hành : Android 6.0 (Marshmallow)\r\nChipset (hãng SX CPU) : Snapdragon 653 8 nhân 64-bit\r\nTốc độ CPU : 4 nhân 1.95 GHz và 4 nhân 1.40 GHz\r\nChip đồ họa (GPU) : Adreno 510\r\nBộ nhớ & Lưu trữ\r\nRAM : 6 GB\r\nBộ nhớ trong : 64 GB\r\nBộ nhớ còn lại (khả dụng) : Khoảng 50 GB\r\nThẻ nhớ ngoài : MicroSD, hỗ trợ tối đa 256 GB\r\nKết nối\r\nMạng di động : 3G, 4G LTE Cat 6\r\nSIM : 2 Nano SIM\r\nWifi : Wi-Fi 802.11 a/b/g/n/ac, Dual-band, Wi-Fi Direct, Wi-Fi hotspot\r\nGPS : A-GPS, GLONASS\r\nBluetooth : v4.2, A2DP, LE\r\nCổng kết nối/sạc : USB Type-C\r\nJack tai nghe : 3.5 mm\r\nKết nối khác : NFC, OTG\r\nThiết kế & Trọng lượng\r\nThiết kế : Nguyên khối, mặt kính cong 2.5D\r\nChất liệu : Nhôm xước\r\nKích thước : Dài 162.9 mm - Ngang 80.7 mm - Dày 6.9 mm\r\nTrọng lượng : 189 g\r\nThông tin pin & Sạc\r\nDung lượng pin : 4000 mAh\r\nLoại pin : Pin chuẩn Li-Ion\r\nCông nghệ pin : Sạc pin nhanh, Siêu tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao : Mở khóa bằng vân tay\r\nTính năng đặc biệt : Mặt kính 2.5D\r\nGhi âm : Có\r\nRadio : Có\r\nXem phim : H.265, 3GP, MP4, AVI, WMV, H.264(MPEG4-AVC), DivX, WMV9, Xvid\r\nNghe nhạc : Midi, Lossless, MP3, WAV, WMA, WMA9, AAC+, AAC++\r\nThông tin khác\r\nThời điểm ra mắt : 3/2017\r\n', 0, 0),
(12, 'Điện thoại OPPO A83 2018 16GB', 3690000, 'https://cdn.tgdd.vn/Products/Images/42/169239/oppo-a83-16gb-400x460.png', 5, 'Công nghệ màn hình : IPS LCD\r\nĐộ phân giải : HD+ (720 x 1440 Pixels)\r\nMàn hình rộng : 5.7\"\r\nMặt kính cảm ứng : Corning Gorilla Glass 5\r\nCamera sau\r\nĐộ phân giải : 13 MP\r\nQuay phim : Quay phim FullHD 1080p@30fps\r\nĐèn Flash : Có\r\nChụp ảnh nâng cao : Tự động lấy nét, Chạm lấy nét\r\nCamera trước\r\nĐộ phân giải : 8 MP\r\nVideocall : Hỗ trợ VideoCall thông qua ứng dụng\r\nThông tin khác : Công nghệ Selfie A.I Beauty, Nhận diện khuôn mặt, Quay video Full HD, Chế độ làm đẹp\r\nHệ điều hành - CPU\r\nHệ điều hành : Android 7.1 (Nougat)\r\nChipset (hãng SX CPU) : Mediatek Helio P23 8 nhân 64-bit\r\nTốc độ CPU : 2.5 GHz\r\nChip đồ họa (GPU) : Mali-G71 MP2\r\nBộ nhớ & Lưu trữ\r\nRAM : 2 GB\r\nBộ nhớ trong : 16 GB\r\nBộ nhớ còn lại (khả dụng) : Khoảng 8 GB\r\nThẻ nhớ ngoài : MicroSD, hỗ trợ tối đa 256 GB\r\nKết nối\r\nMạng di động : 4G LTE Cat 7\r\nSIM : 2 Nano SIM\r\nWifi : Wi-Fi 802.11 b/g/n, Wi-Fi Direct, Wi-Fi hotspot\r\nGPS : Có\r\nBluetooth : v4.2, A2DP, LE\r\nCổng kết nối/sạc : Micro USB\r\nJack tai nghe : 3.5 mm\r\nKết nối khác : OTG\r\nThiết kế & Trọng lượng\r\nThiết kế : Nguyên khối\r\nChất liệu : Nhựa\r\nKích thước : Dài 150.5 mm - Ngang 73.1 mm - Dày 7.7 mm\r\nTrọng lượng : 143 g\r\nThông tin pin & Sạc\r\nDung lượng pin : 3180 mAh\r\nLoại pin : Lithium - Ion\r\nCông nghệ pin : Tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao : Không\r\nTính năng đặc biệt : Chạm 2 lần sáng màn hình\r\nChặn cuộc gọi\r\nChặn tin nhắn\r\nGhi âm : Có\r\nRadio : Không\r\nXem phim : H.265, 3GP, MP4, AVI\r\nNghe nhạc : Lossless, MP3, WAV, WMA\r\nThông tin khác\r\nThời điểm ra mắt : 01/2018\r\n', 0, 0),
(13, 'Điện thoại OPPO F7', 7990000, 'https://cdn.tgdd.vn/Products/Images/42/155261/oppo-f7-bac-400x460.png', 5, 'Công nghệ màn hình : LTPS LCD\r\nĐộ phân giải : Full HD+ (1080 x 2280 Pixels)\r\nMàn hình rộng : 6.23\"\r\nMặt kính cảm ứng : Corning Gorilla Glass 5\r\nCamera sau\r\nĐộ phân giải : 16 MP\r\nQuay phim : Quay phim HD 720p@24fps, Quay phim FullHD 1080p@30fps\r\nĐèn Flash : Có\r\nChụp ảnh nâng cao : Chụp ảnh xóa phông, Chế độ Time-Lapse, Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama, Beautify, Chế độ chụp chuyên nghiệp\r\nCamera trước\r\nĐộ phân giải : 25 MP\r\nVideocall : Hỗ trợ VideoCall thông qua ứng dụng\r\nThông tin khác : Sticker AR (biểu tượng thực tế ảo), Flash màn hình, Panorama, Quay video HD, Nhận diện khuôn mặt, Quay video Full HD, Selfie ngược sáng HDR, Công nghệ Selfie A.I Beauty, Chế độ làm đẹp\r\nHệ điều hành - CPU\r\nHệ điều hành : ColorOS 5.0 (Android 8.1)\r\nChipset (hãng SX CPU) : MediaTek Helio P60 8 nhân 64-bit\r\nTốc độ CPU : 2.0 GHz\r\nChip đồ họa (GPU) : Mali-G72 MP3\r\nBộ nhớ & Lưu trữ\r\nRAM : 4 GB\r\nBộ nhớ trong : 64 GB\r\nBộ nhớ còn lại (khả dụng) : Khoảng 49 GB\r\nThẻ nhớ ngoài : MicroSD, hỗ trợ tối đa 256 GB\r\nKết nối\r\nMạng di động : Hỗ trợ 4G\r\nSIM : 2 Nano SIM\r\nWifi : Wi-Fi 802.11 b/g/n, DLNA, Wi-Fi Direct, Wi-Fi hotspot\r\nGPS : A-GPS, GLONASS\r\nBluetooth : v4.2, A2DP, LE\r\nCổng kết nối/sạc : Micro USB\r\nJack tai nghe : 3.5 mm\r\nKết nối khác : OTG\r\nThiết kế & Trọng lượng\r\nThiết kế : Nguyên khối\r\nChất liệu : Nhựa\r\nKích thước : Dài 156 mm - Ngang 75.3 mm - Dày 7.8 mm\r\nTrọng lượng : 158 g\r\nThông tin pin & Sạc\r\nDung lượng pin : 3400 mAh\r\nLoại pin : Pin chuẩn Li-Ion\r\nCông nghệ pin : Tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao : Mở khóa bằng vân tay, Mở khóa bằng khuôn mặt\r\nTính năng đặc biệt : Ghi âm cuộc gọi\r\nChặn tin nhắn\r\nMặt kính 2.5D\r\nChặn cuộc gọi\r\nGhi âm : Có, microphone chuyên dụng chống ồn\r\nRadio : Có\r\nXem phim : MP4, H.263, H.264(MPEG4-AVC)\r\nNghe nhạc : MP3, WAV, eAAC+, FLAC\r\nThông tin khác\r\nThời điểm ra mắt : 04/2018\r\n', 0, 0),
(14, 'Điện thoại Xiaomi Redmi Note 5', 5690000, 'https://cdn.tgdd.vn/Products/Images/42/153953/xiaomi-redmi-note-5-pro-400x460.png', 4, 'Công nghệ màn hình : IPS LCD\r\nĐộ phân giải : Full HD+ (1080 x 2160 Pixels)\r\nMàn hình rộng : 5.99\"\r\nMặt kính cảm ứng : 2.5D Diamond Cut Glass\r\nCamera sau\r\nĐộ phân giải : 12 MP và 5 MP (2 camera)\r\nQuay phim : Quay phim FullHD 1080p@30fps\r\nĐèn Flash : Có\r\nChụp ảnh nâng cao : Chụp ảnh xóa phông, Chế độ chụp ban đêm (ánh sáng yếu), Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama, Beautify\r\nCamera trước\r\nĐộ phân giải : 13 MP\r\nVideocall : Hỗ trợ VideoCall thông qua ứng dụng\r\nThông tin khác : Selfie ngược sáng HDR, Đèn Flash trợ sáng, Tự động lấy nét, Quay video Full HD, Nhận diện khuôn mặt, Chế độ làm đẹp\r\nHệ điều hành - CPU\r\nHệ điều hành : Android 8.1 (Oreo)\r\nChipset (hãng SX CPU) : Qualcomm Snapdragon 636 8 nhân\r\nTốc độ CPU : 1.8 GHz\r\nChip đồ họa (GPU) : Adreno 509\r\nBộ nhớ & Lưu trữ\r\nRAM : 4 GB\r\nBộ nhớ trong : 64 GB\r\nBộ nhớ còn lại (khả dụng) : Khoảng 50 GB\r\nThẻ nhớ ngoài : MicroSD, hỗ trợ tối đa 128 GB\r\nKết nối\r\nMạng di động : Hỗ trợ 4G\r\nSIM : 2 SIM Nano (SIM 2 chung khe thẻ nhớ)\r\nWifi : Wi-Fi 802.11 a/b/g/n/ac, Dual-band, Wi-Fi Direct, Wi-Fi hotspot\r\nGPS : BDS, A-GPS, GLONASS\r\nBluetooth : v5.0, A2DP, LE\r\nCổng kết nối/sạc : Micro USB\r\nJack tai nghe : 3.5 mm\r\nKết nối khác : USB 2.0\r\nThiết kế & Trọng lượng\r\nThiết kế : Nguyên khối\r\nChất liệu : Kim loại\r\nKích thước : Dài 158.6 mm - Ngang 75.4 mm - Dày 8.1 mm\r\nTrọng lượng : 181 g\r\nThông tin pin & Sạc\r\nDung lượng pin : 4000 mAh\r\nLoại pin : Pin chuẩn Li-Po\r\nCông nghệ pin : Sạc nhanh Quick Charge 2.0, Tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao : Mở khóa bằng vân tay\r\nTính năng đặc biệt : Mặt kính 2.5D\r\nChạm 2 lần sáng màn hình\r\nChặn tin nhắn\r\nĐèn pin\r\nChặn cuộc gọi\r\nGhi âm : Có, microphone chuyên dụng chống ồn\r\nRadio : Có\r\nXem phim : MP4, Xvid\r\nNghe nhạc : AMR, MP3, WMA, AAC, AAC+, FLAC\r\nThông tin khác\r\nThời điểm ra mắt : 05/2018\r\n', 0, 0),
(15, 'Điện thoại Xiaomi Redmi 5 Plus 4GB', 4790000, 'https://cdn.tgdd.vn/Products/Images/42/143465/xiaomi-redmi-5-plus-den-3-400x460.png', 4, 'Công nghệ màn hình : IPS LCD\r\nĐộ phân giải : Full HD+ (1080 x 2160 Pixels)\r\nMàn hình rộng : 5.99\"\r\nMặt kính cảm ứng : Kính cường lực Gorilla Glass\r\nCamera sau\r\nĐộ phân giải : 12 MP\r\nQuay phim : Quay phim FullHD 1080p@30fps, Quay phim 4K 2160p@30fps\r\nĐèn Flash : Đèn LED 2 tông màu\r\nChụp ảnh nâng cao : Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama\r\nCamera trước\r\nĐộ phân giải : 5 MP\r\nVideocall : Có\r\nThông tin khác : Quay video Full HD, Nhận diện khuôn mặt, Chế độ làm đẹp\r\nHệ điều hành - CPU\r\nHệ điều hành : Android 7.1 (Nougat)\r\nChipset (hãng SX CPU) : Snapdragon 625 8 nhân 64-bit\r\nTốc độ CPU : 2.0 GHz\r\nChip đồ họa (GPU) : Adreno 506\r\nBộ nhớ & Lưu trữ\r\nRAM : 4 GB\r\nBộ nhớ trong : 64 GB\r\nBộ nhớ còn lại (khả dụng) : Khoảng 53 GB\r\nThẻ nhớ ngoài : MicroSD, hỗ trợ tối đa 256 GB\r\nKết nối\r\nMạng di động : 3G, 4G LTE Cat 6\r\nSIM : 2 SIM Nano (SIM 2 chung khe thẻ nhớ)\r\nWifi : Wi-Fi 802.11 b/g/n, Wi-Fi Direct, Wi-Fi hotspot\r\nGPS : BDS, A-GPS, GLONASS\r\nBluetooth : v4.2, A2DP, LE\r\nCổng kết nối/sạc : Micro USB\r\nJack tai nghe : 3.5 mm\r\nKết nối khác : OTG\r\nThiết kế & Trọng lượng\r\nThiết kế : Nguyên khối\r\nChất liệu : Kim loại\r\nKích thước : Dài 158.5 mm - Ngang 75.5 mm - Dày 8.1 mm\r\nTrọng lượng : 180 g\r\nThông tin pin & Sạc\r\nDung lượng pin : 4000 mAh\r\nLoại pin : Pin chuẩn Li-Po\r\nCông nghệ pin : Tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao : Mở khóa bằng vân tay\r\nTính năng đặc biệt : Chặn cuộc gọi\r\nMặt kính 2.5D\r\nChạm 2 lần sáng màn hình\r\nChặn tin nhắn\r\nGhi âm : Có, microphone chuyên dụng chống ồn\r\nRadio : Có\r\nXem phim : H.265, 3GP, MP4, AVI, WMV, H.263, H.264(MPEG4-AVC)\r\nNghe nhạc : MP3, WAV, WMA, AAC++, eAAC+, FLAC\r\nThông tin khác\r\nThời điểm ra mắt : 03/2018\r\n', 0, 0),
(16, 'Điện thoại Xiaomi Mi A1 64GB', 4990000, 'https://cdn.tgdd.vn/Products/Images/42/113572/xiaomi-mi-a1-mau-vang-hong-400x460.png', 4, 'Công nghệ màn hình : LTPS LCD\r\nĐộ phân giải : Full HD (1080 x 1920 pixels)\r\nMàn hình rộng : 5.5\"\r\nMặt kính cảm ứng : Kính cường lực Gorilla Glass\r\nCamera sau\r\nĐộ phân giải : 2 camera 12 MP\r\nQuay phim : Quay phim 4K 2160p@30fps\r\nĐèn Flash : Có\r\nChụp ảnh nâng cao : Chụp ảnh xóa phông, Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama\r\nCamera trước\r\nĐộ phân giải : 5 MP\r\nVideocall : Có\r\nThông tin khác : Quay video Full HD, Chế độ làm đẹp, Nhận diện khuôn mặt\r\nHệ điều hành - CPU\r\nHệ điều hành : Android 7.1 (Nougat)\r\nChipset (hãng SX CPU) : Snapdragon 625 8 nhân 64-bit\r\nTốc độ CPU : 2.0 GHz\r\nChip đồ họa (GPU) : Adreno 506\r\nBộ nhớ & Lưu trữ\r\nRAM : 4 GB\r\nBộ nhớ trong : 64 GB\r\nBộ nhớ còn lại (khả dụng) : Khoảng 50 GB\r\nThẻ nhớ ngoài : MicroSD, hỗ trợ tối đa 128 GB\r\nKết nối\r\nMạng di động : 3G, 4G LTE Cat 6\r\nSIM : 2 SIM Nano (SIM 2 chung khe thẻ nhớ)\r\nWifi : Wi-Fi 802.11 a/b/g/n/ac, Dual-band, DLNA, Wi-Fi Direct, Wi-Fi hotspot\r\nGPS : A-GPS, GLONASS\r\nBluetooth : v4.2, A2DP, LE\r\nCổng kết nối/sạc : USB Type-C\r\nJack tai nghe : 3.5 mm\r\nKết nối khác : OTG, Hồng Ngoại\r\nThiết kế & Trọng lượng\r\nThiết kế : Nguyên khối, mặt kính cong 2.5D\r\nChất liệu : Kim loại\r\nKích thước : Dài 155.4 mm - Ngang 75.8 mm - Dày 7.3 mm\r\nTrọng lượng : 165 g\r\nThông tin pin & Sạc\r\nDung lượng pin : 3080 mAh\r\nLoại pin : Pin chuẩn Li-Ion\r\nCông nghệ pin : Tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao : Mở khóa bằng vân tay\r\nTính năng đặc biệt : Chặn cuộc gọi\r\nChặn tin nhắn\r\nMặt kính 2.5D\r\nGhi âm : Có, microphone chuyên dụng chống ồn\r\nRadio : Không\r\nXem phim : H.265, 3GP, MP4, AVI, WMV, H.263\r\nNghe nhạc : Midi, Lossless, MP3, WAV, WMA9, WMA, AAC, AAC+, AAC++\r\nThông tin khác\r\nThời điểm ra mắt : 9/2017\r\n', 0, 0),
(17, 'Điện thoại HTC U Ultra', 8990000, 'https://cdn.tgdd.vn/Products/Images/42/90772/htc-u-ultra-2-400x460.png', 6, 'Công nghệ màn hình : Super LCD\r\nĐộ phân giải : 2K (1440 x 2560 pixels)\r\nMàn hình rộng : Chính: 5.7\", phụ: 2.05\"\r\nMặt kính cảm ứng : Corning Gorilla Glass 5\r\nCamera sau\r\nĐộ phân giải : 12 MP\r\nQuay phim : Quay phim 4K 2160p@30fps\r\nĐèn Flash : Đèn LED 2 tông màu\r\nChụp ảnh nâng cao : Lấy nét bằng laser, Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama, Chống rung quang học (OIS)\r\nCamera trước\r\nĐộ phân giải : 16 MP\r\nVideocall : Hỗ trợ VideoCall thông qua ứng dụng\r\nThông tin khác : Chế độ làm đẹp, Nhận diện khuôn mặt, Camera góc rộng, Tự động lấy nét\r\nHệ điều hành - CPU\r\nHệ điều hành : Android 7.0 (Nougat)\r\nChipset (hãng SX CPU) : Qualcomm Snapdragon 821 4 nhân 64-bit\r\nTốc độ CPU : 2 nhân 2.15 GHz Kryo & 2 nhân 1.6 GHz Kryo\r\nChip đồ họa (GPU) : Adreno 530\r\nBộ nhớ & Lưu trữ\r\nRAM : 4 GB\r\nBộ nhớ trong : 64 GB\r\nBộ nhớ còn lại (khả dụng) : Khoảng 53 GB\r\nThẻ nhớ ngoài : MicroSD, hỗ trợ tối đa 2 TB\r\nKết nối\r\nMạng di động : 3G, 4G LTE Cat 11\r\nSIM : 2 Nano SIM\r\nWifi : Wi-Fi 802.11 a/b/g/n/ac, Dual-band, DLNA, Wi-Fi Direct, Wi-Fi hotspot\r\nGPS : A-GPS, GLONASS\r\nBluetooth : v4.2, A2DP, LE\r\nCổng kết nối/sạc : USB Type-C\r\nJack tai nghe : USB Type C\r\nKết nối khác : NFC, HTC Connect, OTG\r\nThiết kế & Trọng lượng\r\nThiết kế : Nguyên khối, mặt kính cong 2.5D\r\nChất liệu : Khung kim loại + mặt kính cường lực\r\nKích thước : Dài 162.4 mm - Ngang 79.8 mm - Dày 8 mm\r\nTrọng lượng : 170 g\r\nThông tin pin & Sạc\r\nDung lượng pin : 3000 mAh\r\nLoại pin : Pin chuẩn Li-Ion\r\nCông nghệ pin : Sạc nhanh Quick Charge 3.0, Tiết kiệm pin, Siêu tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao : Mở khóa bằng vân tay\r\nTính năng đặc biệt : HTC BoomSound™\r\nMặt kính 2.5D\r\nGhi âm : Có, microphone chuyên dụng chống ồn\r\nRadio : Có\r\nXem phim : 3GP, MP4, AVI, WMV, H.263, H.264(MPEG4-AVC), DivX, WMV9, Xvid\r\nNghe nhạc : Lossless, Midi, MP3, WAV, WMA9, WMA, AAC, AAC+, AAC++, eAAC+\r\nThông tin khác\r\nThời điểm ra mắt : 2/2017\r\n', 0, 0),
(18, 'Điện thoại HTC 10 evo', 3990000, 'https://cdn.tgdd.vn/Products/Images/42/88632/htc-10-evo-400-400x460.png', 6, 'Công nghệ màn hình : Super LCD 3\r\nĐộ phân giải : 2K (1440 x 2560 pixels)\r\nMàn hình rộng : 5.5\"\r\nMặt kính cảm ứng : Corning Gorilla Glass 5\r\nCamera sau\r\nĐộ phân giải : 16 MP\r\nQuay phim : Quay phim 4K 2160p@30fps\r\nĐèn Flash : Đèn LED 2 tông màu\r\nChụp ảnh nâng cao : Chế độ Slow Motion, Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama, Chống rung quang học (OIS)\r\nCamera trước\r\nĐộ phân giải : 8 MP\r\nVideocall : Hỗ trợ VideoCall thông qua ứng dụng\r\nThông tin khác : Nhận diện khuôn mặt, Chế độ làm đẹp, Tự động lấy nét, Camera góc rộng, Selfie ngược sáng HDR, Flash màn hình, Panorama\r\nHệ điều hành - CPU\r\nHệ điều hành : Android 7.0 (Nougat)\r\nChipset (hãng SX CPU) : Snapdragon 810 8 nhân 64-bit\r\nTốc độ CPU : 4 nhân 1.5 GHz Cortex-A53 & 4 nhân 2 GHz Cortex-A57\r\nChip đồ họa (GPU) : Adreno 430\r\nBộ nhớ & Lưu trữ\r\nRAM : 3 GB\r\nBộ nhớ trong : 32 GB\r\nBộ nhớ còn lại (khả dụng) : Khoảng 23 GB\r\nThẻ nhớ ngoài : MicroSD, hỗ trợ tối đa 2 TB\r\nKết nối\r\nMạng di động : 3G, 4G LTE Cat 6\r\nSIM : 1 Nano SIM\r\nWifi : Wi-Fi 802.11 a/b/g/n/ac\r\nGPS : GLONASS\r\nBluetooth : V4.1\r\nCổng kết nối/sạc : USB Type-C\r\nJack tai nghe : USB Type C\r\nKết nối khác : NFC, OTG\r\nThiết kế & Trọng lượng\r\nThiết kế : Nguyên khối\r\nChất liệu : Kim loại\r\nKích thước : Dài 153.6 mm - Ngang 77.3 mm - Dày 8.1 mm\r\nTrọng lượng : 174 g\r\nThông tin pin & Sạc\r\nDung lượng pin : 3200 mAh\r\nLoại pin : Pin chuẩn Li-Ion\r\nCông nghệ pin : Sạc nhanh Quick Charge 2.0\r\nTiện ích\r\nBảo mật nâng cao : Mở khóa bằng vân tay\r\nTính năng đặc biệt : HTC BoomSound™\r\nCông nghệ âm thanh Hi-Res Audio\r\nChuẩn Kháng nước, Chuẩn kháng bụi\r\nGhi âm : Có, microphone chuyên dụng chống ồn\r\nRadio : Không\r\nXem phim : H.265, 3GP, MP4, AVI, WMV, H.263, H.264(MPEG4-AVC), DivX, Xvid\r\nNghe nhạc : MP3, WAV, eAAC+\r\nThông tin khác\r\nThời điểm ra mắt : 6/2017\r\n', 0, 0),
(19, 'Điện thoại iPhone X 256GB Gray', 34790000, 'https://cdn.tgdd.vn/Products/Images/42/114111/iphone-x-256gb-h2-400x460-400x460.png', 1, 'Công nghệ màn hình : OLED\r\nĐộ phân giải : 1125 x 2436 Pixels\r\nMàn hình rộng : 5.8\"\r\nMặt kính cảm ứng : Kính oleophobic (ion cường lực)\r\nCamera sau\r\nĐộ phân giải : 2 camera 12 MP\r\nQuay phim : Quay phim 4K 2160p@60fps\r\nĐèn Flash : 4 đèn LED (2 tông màu)\r\nChụp ảnh nâng cao : Lấy nét dự đoán, Chụp ảnh xóa phông, Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama, Chống rung quang học (OIS)\r\nCamera trước\r\nĐộ phân giải : 7 MP\r\nVideocall : Có\r\nThông tin khác : Camera góc rộng, Selfie ngược sáng HDR, Nhận diện khuôn mặt, Quay video Full HD\r\nHệ điều hành - CPU\r\nHệ điều hành : iOS 11\r\nChipset (hãng SX CPU) : Apple A11 Bionic 6 nhân\r\nTốc độ CPU : 2.39 GHz\r\nChip đồ họa (GPU) : Apple GPU 3 nhân\r\nBộ nhớ & Lưu trữ\r\nRAM : 3 GB\r\nBộ nhớ trong : 256 GB\r\nBộ nhớ còn lại (khả dụng) : Khoảng 249 GB\r\nThẻ nhớ ngoài : Không\r\nKết nối\r\nMạng di động : 3G, 4G LTE Cat 16\r\nSIM : 1 Nano SIM\r\nWifi : Wi-Fi 802.11 a/b/g/n/ac, Dual-band, Wi-Fi hotspot\r\nGPS : A-GPS, GLONASS\r\nBluetooth : v5.0, A2DP, LE, EDR\r\nCổng kết nối/sạc : Lightning\r\nJack tai nghe : Không\r\nKết nối khác : NFC, OTG\r\nThiết kế & Trọng lượng\r\nThiết kế :  Nguyên khối\r\nChất liệu : Khung kim loại + mặt kính cường lực\r\nKích thước : Dài 143.6 mm - Ngang 70.9 mm - Dày 7.7 mm\r\nTrọng lượng : 174 g\r\nThông tin pin & Sạc\r\nDung lượng pin : 2716 mAh\r\nLoại pin : Pin chuẩn Li-Ion\r\nCông nghệ pin : Sạc pin nhanh, Sạc pin không dây, Tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao : Nhận diện khuôn mặt Face ID\r\nTính năng đặc biệt : Chuẩn Kháng nước, Chuẩn kháng bụi, 3D Touch\r\nGhi âm : Có, microphone chuyên dụng chống ồn\r\nRadio : Không\r\nXem phim : H.265, 3GP, MP4, AVI, WMV, H.263, H.264(MPEG4-AVC)\r\nNghe nhạc : Midi, Lossless, MP3, WAV, WMA9, WMA, AAC, AAC+, AAC++, eAAC+\r\nThông tin khác\r\nThời điểm ra mắt : 9/2017\r\n', 0, 0),
(20, 'Điện thoại Samsung Galaxy A8+ (2018)', 13490000, 'https://cdn.tgdd.vn/Products/Images/42/142465/samsung-galaxy-a8-plus-2018-gold-400x460.png', 2, 'Công nghệ màn hình : Super AMOLED\r\nĐộ phân giải : Full HD+ (1080 x 2220 Pixels)\r\nMàn hình rộng : 6\"\r\nMặt kính cảm ứng : Kính cường lực Gorilla Glass\r\nCamera sau\r\nĐộ phân giải : 16 MP\r\nQuay phim : Quay phim FullHD 1080p@30fps\r\nĐèn Flash : Có\r\nChụp ảnh nâng cao : Chế độ Time-Lapse, Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama, Beautify, Ảnh GIF, Chế độ chụp chuyên nghiệp\r\nCamera trước\r\nĐộ phân giải : 16 MP và 8 MP\r\nVideocall : Có\r\nThông tin khác : Camera kép, Chụp bằng giọng nói, Chống rung, Nhận diện khuôn mặt, Chế độ làm đẹp, Quay video Full HD, Đèn Flash trợ sáng, Camera góc rộng, Selfie ngược sáng HDR, Panorama, Selfie bằng cử chỉ\r\nHệ điều hành - CPU\r\nHệ điều hành : Android 7.1 (Nougat)\r\nChipset (hãng SX CPU) : Exynos 7885 8 nhân 64-bit\r\nTốc độ CPU : 2 nhân 2.2 GHz Cortex-A73 & 6 nhân 1.6 GHz Cortex-A53\r\nChip đồ họa (GPU) : Mali™ G71\r\nBộ nhớ & Lưu trữ\r\nRAM : 6 GB\r\nBộ nhớ trong : 64 GB\r\nBộ nhớ còn lại (khả dụng) : Khoảng 52 GB\r\nThẻ nhớ ngoài : MicroSD, hỗ trợ tối đa 256 GB\r\nKết nối\r\nMạng di động : Hỗ trợ 4G\r\nSIM : 2 Nano SIM\r\nWifi : Wi-Fi 802.11 a/b/g/n/ac, Dual-band, Wi-Fi Direct, Wi-Fi hotspot\r\nGPS : Có\r\nBluetooth : v5.0, A2DP, LE, EDR\r\nCổng kết nối/sạc : USB Type-C\r\nJack tai nghe : 3.5 mm\r\nKết nối khác : NFC\r\nThiết kế & Trọng lượng\r\nThiết kế : Nguyên khối\r\nChất liệu : Khung kim loại + mặt kính cường lực\r\nKích thước : Dài 159.9 mm - Ngang 75.7 mm - Dày 8.3 mm\r\nTrọng lượng : 191 g\r\nThông tin pin & Sạc\r\nDung lượng pin : 3500 mAh\r\nLoại pin : Pin chuẩn Li-Ion\r\nCông nghệ pin : Sạc pin nhanh, Siêu tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao : Mở khóa bằng vân tay, Mở khóa bằng khuôn mặt\r\nTính năng đặc biệt : Tương thích kính thực tế ảo Gear VR\r\nChuẩn Kháng nước, Chuẩn kháng bụi\r\nMặt kính 2.5D\r\nSạc pin nhanh\r\nGhi âm : Có, microphone chuyên dụng chống ồn\r\nRadio : Có\r\nXem phim : H.265, 3GP, MP4, AVI, H.263, H.264(MPEG4-AVC)\r\nNghe nhạc : Lossless, Midi, MP3, WAV, WMA\r\nThông tin khác\r\nThời điểm ra mắt : 12/2017', 0, 0),
(21, 'Điện thoại Samsung Galaxy J8', 7290000, 'https://cdn.tgdd.vn/Products/Images/42/153830/samsung-galaxy-j8-400x460.png', 2, 'Công nghệ màn hình : Super AMOLED\r\nĐộ phân giải : HD+ (720 × 1480 Pixels)\r\nMàn hình rộng : 6.0\"\r\nMặt kính cảm ứng : 2.5D Diamond Cut Glass\r\nCamera sau\r\nĐộ phân giải : 16 MP và 5 MP (2 camera)\r\nQuay phim : Quay phim FullHD 1080p@30fps\r\nĐèn Flash : Có\r\nChụp ảnh nâng cao : Chụp ảnh xóa phông, Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama, Beautify\r\nCamera trước\r\nĐộ phân giải : 16 MP\r\nVideocall : Hỗ trợ VideoCall thông qua ứng dụng\r\nThông tin khác : Đèn Flash trợ sáng, Tự động lấy nét, Quay video Full HD, Nhận diện khuôn mặt, Chế độ làm đẹp\r\nHệ điều hành - CPU\r\nHệ điều hành : Android 8.0 (Oreo)\r\nChipset (hãng SX CPU) : Qualcomm Snapdragon 450 8 nhân 64-bit\r\nTốc độ CPU : 1.8 GHz\r\nChip đồ họa (GPU) : Adreno 506\r\nBộ nhớ & Lưu trữ\r\nRAM : 3 GB\r\nBộ nhớ trong : 32 GB\r\nBộ nhớ còn lại (khả dụng) : Đang cập nhật\r\nThẻ nhớ ngoài : MicroSD, hỗ trợ tối đa 256 GB\r\nKết nối\r\nMạng di động : Hỗ trợ 4G\r\nSIM : 2 Nano SIM\r\nWifi : Wi-Fi 802.11 b/g/n, Wi-Fi Direct, Wi-Fi hotspot\r\nGPS : BDS, A-GPS, GLONASS\r\nBluetooth : v4.2, A2DP, LE\r\nCổng kết nối/sạc : Micro USB\r\nJack tai nghe : 3.5 mm\r\nKết nối khác : OTG\r\nThiết kế & Trọng lượng\r\nThiết kế : Nguyên khối\r\nChất liệu : Nhựa\r\nKích thước : Đang cập nhật\r\nTrọng lượng : Đang cập nhật\r\nThông tin pin & Sạc\r\nDung lượng pin : 3500 mAh\r\nLoại pin : Pin chuẩn Li-Ion\r\nCông nghệ pin : Tiết kiệm pin, Siêu tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao : Mở khóa bằng vân tay, Mở khóa bằng khuôn mặt\r\nTính năng đặc biệt : Chặn tin nhắn\r\nĐèn pin\r\nGhi âm cuộc gọi\r\nChặn cuộc gọi\r\nGhi âm : Có\r\nRadio : Có\r\nXem phim : 3GP, MP4, AVI, WMV\r\nNghe nhạc : AMR, Midi, MP3, WAV, WMA, AAC, OGG, FLAC\r\nThông tin khác\r\nThời điểm ra mắt : Đang cập nhật', 0, 0),
(22, 'Điện thoại iPhone X 64GB Gray', 29990000, 'https://cdn.tgdd.vn/Products/Images/42/114115/iphone-x-64gb-1-400x460.png', 1, 'Công nghệ màn hình : OLED\r\nĐộ phân giải : 1125 x 2436 Pixels\r\nMàn hình rộng : 5.8\"\r\nMặt kính cảm ứng : Kính oleophobic (ion cường lực)\r\nCamera sau\r\nĐộ phân giải : 2 camera 12 MP\r\nQuay phim : Quay phim 4K 2160p@60fps\r\nĐèn Flash : 4 đèn LED (2 tông màu)\r\nChụp ảnh nâng cao : Lấy nét dự đoán, Chụp ảnh xóa phông, Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama, Chống rung quang học (OIS)\r\nCamera trước\r\nĐộ phân giải : 7 MP\r\nVideocall : Có\r\nThông tin khác : Camera góc rộng, Selfie ngược sáng HDR, Nhận diện khuôn mặt, Quay video Full HD\r\nHệ điều hành - CPU\r\nHệ điều hành : iOS 11\r\nChipset (hãng SX CPU) : Apple A11 Bionic 6 nhân\r\nTốc độ CPU : 2.39 GHz\r\nChip đồ họa (GPU) : Apple GPU 3 nhân\r\nBộ nhớ & Lưu trữ\r\nRAM : 3 GB\r\nBộ nhớ trong : 64 GB\r\nBộ nhớ còn lại (khả dụng) : Khoảng 55 GB\r\nThẻ nhớ ngoài : Không\r\nKết nối\r\nMạng di động : 3G, 4G LTE Cat 16\r\nSIM : 1 Nano SIM\r\nWifi : Wi-Fi 802.11 a/b/g/n/ac, Dual-band, Wi-Fi hotspot\r\nGPS : A-GPS, GLONASS\r\nBluetooth : v5.0, A2DP, LE, EDR\r\nCổng kết nối/sạc : Lightning\r\nJack tai nghe : Không\r\nKết nối khác : NFC, OTG\r\nThiết kế & Trọng lượng\r\nThiết kế : Nguyên khối\r\nChất liệu : Khung kim loại + mặt kính cường lực\r\nKích thước : Dài 143.6 mm - Ngang 70.9 mm - Dày 7.7 mm\r\nTrọng lượng : 174 g\r\nThông tin pin & Sạc\r\nDung lượng pin : 2716 mAh\r\nLoại pin : Pin chuẩn Li-Ion\r\nCông nghệ pin : Sạc pin nhanh, Sạc pin không dây, Tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao : Nhận diện khuôn mặt Face ID\r\nTính năng đặc biệt : Chuẩn Kháng nước, Chuẩn kháng bụi\r\n3D Touch\r\nGhi âm : Có, microphone chuyên dụng chống ồn\r\nRadio : Không\r\nXem phim : H.265, 3GP, MP4, AVI, WMV, H.263, H.264(MPEG4-AVC)\r\nNghe nhạc : Midi, Lossless, MP3, WAV, WMA9, WMA, AAC, AAC+, AAC++, eAAC+\r\nThông tin khác\r\nThời điểm ra mắt : 09/2017', 0, 0),
(23, 'Điện thoại iPhone 7 Plus 128GB', 22990000, 'https://cdn.tgdd.vn/Products/Images/42/87839/iphone-7-plus-128gb-de-400x460.png', 1, 'Công nghệ màn hình : LED-backlit IPS LCD\r\nĐộ phân giải : Full HD (1080 x 1920 pixels)\r\nMàn hình rộng : 5.5\"\r\nMặt kính cảm ứng : Kính oleophobic (ion cường lực)\r\nCamera sau\r\nĐộ phân giải : 2 camera 12 MP\r\nQuay phim : Quay phim 4K 2160p@30fps\r\nĐèn Flash : 4 đèn LED (2 tông màu)\r\nChụp ảnh nâng cao : Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama, Chống rung quang học (OIS)\r\nCamera trước\r\nĐộ phân giải : 7 MP\r\nVideocall : Hỗ trợ VideoCall thông qua ứng dụng\r\nThông tin khác : Tự động lấy nét, Nhận diện khuôn mặt, Quay video Full HD, Selfie ngược sáng HDR\r\nHệ điều hành - CPU\r\nHệ điều hành : iOS 11\r\nChipset (hãng SX CPU) : Apple A10 Fusion 4 nhân 64-bit\r\nTốc độ CPU : 2.3 GHz\r\nChip đồ họa (GPU) : Chip đồ họa 6 nhân\r\nBộ nhớ & Lưu trữ\r\nRAM : 3 GB\r\nBộ nhớ trong : 128 GB\r\nBộ nhớ còn lại (khả dụng) : Khoảng 120.59 GB\r\nThẻ nhớ ngoài : Không\r\nKết nối\r\nMạng di động : 3G, 4G LTE Cat 9\r\nSIM : 1 Nano SIM\r\nWifi : Wi-Fi 802.11 a/b/g/n/ac, Dual-band, Wi-Fi hotspot\r\nGPS : A-GPS, GLONASS\r\nBluetooth : v4.2, A2DP, LE\r\nCổng kết nối/sạc : Lightning\r\nJack tai nghe : Không\r\nKết nối khác : NFC, Air Play, OTG, HDMI\r\nThiết kế & Trọng lượng\r\nThiết kế : Nguyên khối, mặt kính cong 2.5D\r\nChất liệu : Hợp kim Nhôm + Magie\r\nKích thước : Dài 158.2 mm - Ngang 77.9 mm - Dày 7.3 mm\r\nTrọng lượng : 188 g\r\nThông tin pin & Sạc\r\nDung lượng pin : 2900 mAh\r\nLoại pin : Pin chuẩn Li-Ion\r\nCông nghệ pin : Tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao : Mở khóa bằng vân tay\r\nTính năng đặc biệt : Chuẩn Kháng nước, Chuẩn kháng bụi\r\n3D Touch\r\nGhi âm : Có, microphone chuyên dụng chống ồn\r\nRadio : Không\r\nXem phim : H.265, 3GP, MP4, AVI, WMV, H.264(MPEG4-AVC), DivX, WMV9, Xvid\r\nNghe nhạc : Lossless, Midi, MP3, WAV, WMA, AAC, AAC++\r\nThông tin khác\r\nThời điểm ra mắt : 11/2016', 0, 0),
(24, 'Điện thoại iPhone X 64GB Silver', 29990000, 'https://cdn.tgdd.vn/Products/Images/42/172602/iphone-x-64gb-silver-400x460.png', 1, 'Công nghệ màn hình : OLED\r\nĐộ phân giải : 1125 x 2436 Pixels\r\nMàn hình rộng : 5.8\"\r\nMặt kính cảm ứng : Kính oleophobic (ion cường lực)\r\nCamera sau\r\nĐộ phân giải : 2 camera 12 MP\r\nQuay phim : Quay phim 4K 2160p@60fps\r\nĐèn Flash : 4 đèn LED (2 tông màu)\r\nChụp ảnh nâng cao : Lấy nét dự đoán, Chụp ảnh xóa phông, Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama, Chống rung quang học (OIS)\r\nCamera trước\r\nĐộ phân giải : 7 MP\r\nVideocall : Có\r\nThông tin khác : Camera góc rộng, Selfie ngược sáng HDR, Nhận diện khuôn mặt, Quay video Full HD\r\nHệ điều hành - CPU\r\nHệ điều hành : iOS 11\r\nChipset (hãng SX CPU) : Apple A11 Bionic 6 nhân\r\nTốc độ CPU : 2.39 GHz\r\nChip đồ họa (GPU) : Apple GPU 3 nhân\r\nBộ nhớ & Lưu trữ\r\nRAM : 3 GB\r\nBộ nhớ trong : 64 GB\r\nBộ nhớ còn lại (khả dụng) : Khoảng 55 GB\r\nThẻ nhớ ngoài : Không\r\nKết nối\r\nMạng di động : 3G, 4G LTE Cat 16\r\nSIM : 1 Nano SIM\r\nWifi : Wi-Fi 802.11 a/b/g/n/ac, Dual-band, Wi-Fi hotspot\r\nGPS : A-GPS, GLONASS\r\nBluetooth : v5.0, A2DP, LE, EDR\r\nCổng kết nối/sạc : Lightning\r\nJack tai nghe : Không\r\nKết nối khác : NFC, OTG\r\nThiết kế & Trọng lượng\r\nThiết kế : Nguyên khối\r\nChất liệu : Khung kim loại + mặt kính cường lực\r\nKích thước : Dài 143.6 mm - Ngang 70.9 mm - Dày 7.7 mm\r\nTrọng lượng : 174 g\r\nThông tin pin & Sạc\r\nDung lượng pin : 2716 mAh\r\nLoại pin : Pin chuẩn Li-Ion\r\nCông nghệ pin : Sạc pin nhanh, Sạc pin không dây, Tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao : Nhận diện khuôn mặt Face ID\r\nTính năng đặc biệt : Chuẩn Kháng nước, Chuẩn kháng bụi\r\n3D Touch\r\nGhi âm : Có, microphone chuyên dụng chống ồn\r\nRadio : Không\r\nXem phim : H.265, 3GP, MP4, AVI, WMV, H.263, H.264(MPEG4-AVC)\r\nNghe nhạc : Midi, Lossless, MP3, WAV, WMA9, WMA, AAC, AAC+, AAC++, eAAC+\r\nThông tin khác\r\nThời điểm ra mắt : 09/2017', 0, 0),
(26, 'Điện thoại Samsung Galaxy S9+ 64GB', 23490000, 'https://cdn.tgdd.vn/Products/Images/42/147939/samsung-galaxy-s9-plus-64gb-xanh-san-ho-2-400x460.png', 2, 'Công nghệ màn hình : Super AMOLED\r\nĐộ phân giải : 2K+ (1440 x 2960 Pixels)\r\nMàn hình rộng : 6.2\"\r\nMặt kính cảm ứng : Corning Gorilla Glass 5\r\nCamera sau\r\nĐộ phân giải : 2 camera 12 MP\r\nQuay phim : Quay phim siêu chậm 960 fps, Quay phim FullHD 1080p@60fps, Quay phim 4K 2160p@60fps\r\nĐèn Flash : Có\r\nChụp ảnh nâng cao : Zoom quang học (Camera kép), Chụp ảnh xóa phông, Chế độ chụp ban đêm (ánh sáng yếu), Điều chỉnh khẩu độ, Super Slow motion (quay siêu chậm), Tự động lấy nét, Chạm lấy nét, HDR, Panorama, Chống rung quang học (OIS), Ảnh GIF, Chế độ chụp chuyên nghiệp\r\nCamera trước\r\nĐộ phân giải : 8 MP\r\nVideocall : Có\r\nThông tin khác : Chụp bằng giọng nói, Nhận diện khuôn mặt, Chế độ làm đẹp, Quay video Full HD, Tự động lấy nét, Đèn Flash trợ sáng, Camera góc rộng, Selfie ngược sáng HDR, Tự động chụp khi nhận diện nụ cười, Selfie bằng cử chỉ\r\nHệ điều hành - CPU\r\nHệ điều hành : Android 8.0 (Oreo)\r\nChipset (hãng SX CPU) : Exynos 9810 8 nhân 64 bit\r\nTốc độ CPU : 4 nhân 2.8 GHz & 4 nhân 1.7 GHz\r\nChip đồ họa (GPU) : Mali-G72 MP18\r\nBộ nhớ & Lưu trữ\r\nRAM : 6 GB\r\nBộ nhớ trong : 64 GB\r\nBộ nhớ còn lại (khả dụng) : Khoảng 50 GB\r\nThẻ nhớ ngoài : MicroSD, hỗ trợ tối đa 400 GB\r\nKết nối\r\nMạng di động : 3G, 4G LTE Cat 18\r\nSIM : 2 SIM Nano (SIM 2 chung khe thẻ nhớ)\r\nWifi : Wi-Fi 802.11 a/b/g/n/ac, Dual-band, DLNA, Wi-Fi Direct, Wi-Fi hotspot\r\nGPS : BDS, A-GPS, GLONASS\r\nBluetooth : v5.0, apt-X, A2DP, LE, EDR\r\nCổng kết nối/sạc : USB Type-C\r\nJack tai nghe : 3.5 mm\r\nKết nối khác : NFC, OTG, Miracast\r\nThiết kế & Trọng lượng\r\nThiết kế : Nguyên khối\r\nChất liệu : Khung kim loại + mặt kính cường lực\r\nKích thước : Dài 158.1 mm - Ngang 73.8 mm - 8.5 mm\r\nTrọng lượng : 189 g\r\nThông tin pin & Sạc\r\nDung lượng pin : 3500 mAh\r\nLoại pin : Pin chuẩn Li-Ion\r\nCông nghệ pin : Sạc pin nhanh, Sạc pin không dây, Siêu tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao : Mở khóa bằng khuôn mặt, Mở khóa bằng vân tay, Quét mống mắt\r\nTính năng đặc biệt : Đèn pin, Chuẩn Kháng nước, Chuẩn kháng bụi, Sạc pin nhanh, Ghi âm cuộc gọi, Samsung Pay, Màn hình luôn hiển thị AOD, Chặn tin nhắn, Chặn cuộc gọi\r\nGhi âm : Có, microphone chuyên dụng chống ồn\r\nRadio : Không\r\nXem phim : H.265, 3GP, MP4, AVI, WMV, H.264(MPEG4-AVC), DivX, WMV9, Xvid\r\nNghe nhạc : Midi, Lossless, MP3, WAV, WMA, AAC++, eAAC+, OGG, AC3, FLAC\r\nThông tin khác\r\nThời điểm ra mắt 03/2018', 0, 0);
INSERT INTO `sanpham` (`ID`, `TenSP`, `Gia`, `HinhAnh`, `ID_LoaiSP`, `MoTa`, `total_rating`, `rating_count`) VALUES
(27, 'Điện thoại Xiaomi Redmi 6', 3590000, 'https://cdn.tgdd.vn/Products/Images/42/173957/xiaomi-redmi-6-xam-400x460.png', 4, 'Công nghệ màn hình : IPS LCD\r\nĐộ phân giải : HD+ (720 x 1440 Pixels)\r\nMàn hình rộng : 5.45\"\r\nMặt kính cảm ứng : Đang cập nhật\r\nCamera sau\r\nĐộ phân giải : 12 MP và 5 MP (2 camera)\r\nQuay phim : Quay phim FullHD 1080p@30fps\r\nĐèn Flash : Có\r\nChụp ảnh nâng cao : Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama\r\nCamera trước\r\nĐộ phân giải : 5 MP\r\nVideocall : Hỗ trợ VideoCall thông qua ứng dụng\r\nThông tin khác : Chế độ làm đẹp\r\nHệ điều hành - CPU\r\nHệ điều hành : Android 8.1 (Oreo)\r\nChipset (hãng SX CPU) : MediaTek MT6762 8 nhân 64-bit (Helio P22)\r\nTốc độ CPU : 2.0 GHz\r\nChip đồ họa (GPU) : PowerVR GE8320\r\nBộ nhớ & Lưu trữ\r\nRAM : 3 GB\r\nBộ nhớ trong : 32 GB\r\nBộ nhớ còn lại (khả dụng) : Khoảng 22 GB\r\nThẻ nhớ ngoài : MicroSD, hỗ trợ tối đa 256 GB\r\nKết nối\r\nMạng di động : Hỗ trợ 4G\r\nSIM : 2 Nano SIM\r\nWifi : Wi-Fi 802.11 b/g/n, Wi-Fi Direct, Wi-Fi hotspot\r\nGPS : BDS, A-GPS, GLONASS\r\nBluetooth : v4.2, A2DP, LE\r\nCổng kết nối/sạc : Micro USB\r\nJack tai nghe : 3.5 mm\r\nKết nối khác : Không\r\nThiết kế & Trọng lượng\r\nThiết kế : Nguyên khối\r\nChất liệu : Nhựa\r\nKích thước : Dài 147.46 m - Ngang 71.49 mm - Dày 8.3 mm\r\nTrọng lượng : 146 g\r\nThông tin pin & Sạc\r\nDung lượng pin : 3000 mAh\r\nLoại pin : Pin chuẩn Li-Ion\r\nCông nghệ pin : Tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao : Mở khóa bằng vân tay\r\nTính năng đặc biệt : Đèn pin\r\nGhi âm : Có, microphone chuyên dụng chống ồn\r\nRadio : Có\r\nXem phim : H.265, MP4, H.264(MPEG4-AVC)\r\nNghe nhạc : MP3, WAV, AAC, FLAC\r\nThông tin khác\r\nThời điểm ra mắt : 07/2018', 0, 0),
(28, 'Điện thoại Xiaomi Redmi Note 4', 4290000, 'https://cdn.tgdd.vn/Products/Images/42/84905/xiaomi-redmi-note-4-1-400x460.png', 4, 'Công nghệ màn hình : IPS LCD\r\nĐộ phân giải : Full HD (1080 x 1920 pixels)\r\nMàn hình rộng : 5.5\"\r\nMặt kính cảm ứng : Kính cường lực\r\nCamera sau\r\nĐộ phân giải : 13 MP\r\nQuay phim : Quay phim FullHD 1080p@30fps\r\nĐèn Flash : Đèn LED 2 tông màu\r\nChụp ảnh nâng cao : Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama\r\nCamera trước\r\nĐộ phân giải : 5 MP\r\nVideocall : Có\r\nThông tin khác : Chế độ làm đẹp, Quay video Full HD\r\nHệ điều hành - CPU\r\nHệ điều hành : Android 6.0 (Marshmallow)\r\nChipset (hãng SX CPU) : Snapdragon 625 8 nhân 64-bit\r\nTốc độ CPU : 2.0 GHz\r\nChip đồ họa (GPU) : Adreno 506\r\nBộ nhớ & Lưu trữ\r\nRAM : 3 GB\r\nBộ nhớ trong : 32 GB\r\nBộ nhớ còn lại (khả dụng) : Khoảng 24.6 GB\r\nThẻ nhớ ngoài : MicroSD, hỗ trợ tối đa 128 GB\r\nKết nối\r\nMạng di động : Có hỗ trợ 4G\r\nSIM : Nano SIM & Micro SIM (SIM 2 chung khe thẻ nhớ)\r\nWifi : Wi-Fi 802.11 a/b/g/n/ac, Dual-band, Wi-Fi Direct, Wi-Fi hotspot\r\nGPS : A-GPS, GLONASS\r\nBluetooth : v4.2, A2DP, LE\r\nCổng kết nối/sạc : Micro USB\r\nJack tai nghe : 3.5 mm\r\nKết nối khác : OTG, Hồng Ngoại\r\nThiết kế & Trọng lượng\r\nThiết kế : Nguyên khối\r\nChất liệu : Kim loại\r\nKích thước : Dài 151 mm - Ngang 76 mm - Dày 8.4 mm\r\nTrọng lượng : 165 g\r\nThông tin pin & Sạc\r\nDung lượng pin : 4100 mAh\r\nLoại pin : Pin chuẩn Li-Po\r\nCông nghệ pin : Tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao : Mở khóa bằng vân tay\r\nTính năng đặc biệt : Mặt kính 2.5D\r\nGhi âm : Có, microphone chuyên dụng chống ồn\r\nRadio : Có\r\nXem phim : H.265, MP4, AVI, WMV, H.264(MPEG4-AVC), DivX, Xvid\r\nNghe nhạc : Lossless, MP3, WAV, WMA, eAAC+, AC3, FLAC\r\nThông tin khác\r\nThời điểm ra mắt : 3/2017', 0, 0),
(29, 'Điện thoại Xiaomi Redmi Note 5A Prime', 3390000, 'https://cdn.tgdd.vn/Products/Images/42/133150/xiaomi-redmi-note-5a-prime-2-400x460.png', 4, 'Công nghệ màn hình IPS LCD\r\nĐộ phân giải HD (720 x 1280 pixels)\r\nMàn hình rộng 5.5\"\r\nMặt kính cảm ứng\r\nKính cường lực\r\nCamera sau\r\nĐộ phân giải 13 MP\r\nQuay phim Quay phim FullHD 1080p@30fps\r\nĐèn Flash Có\r\nChụp ảnh nâng cao Tự động lấy nét, Chạm lấy nét, HDR, Panorama\r\nCamera trước\r\nĐộ phân giải 16 MP\r\nVideocall Hỗ trợ VideoCall thông qua ứng dụng\r\nThông tin khác Chế độ làm đẹp, Quay video Full HD\r\nHệ điều hành - CPU\r\nHệ điều hành Android 7.0 (Nougat)\r\nChipset (hãng SX CPU) Snapdragon 435 8 nhân 64-bit\r\nTốc độ CPU 1.4 GHz\r\nChip đồ họa (GPU) Adreno 505\r\nBộ nhớ & Lưu trữ\r\nRAM 3GB\r\nBộ nhớ trong 32 GB\r\nBộ nhớ còn lại (khả dụng) Khoảng 21 GB\r\nThẻ nhớ ngoài MicroSD, hỗ trợ tối đa 256 GB\r\nKết nối\r\nMạng di động Hỗ trợ 4G\r\nSIM2 Nano SIM\r\nWifiWi-Fi 802.11 b/g/n, DLNA, Wi-Fi Direct, Wi-Fi hotspot\r\nGPSA-GPS\r\nBluetooth Có\r\nCổng kết nối/sạc Micro USB\r\nJack tai nghe 3.5 mm\r\nKết nối khác OTG, Hồng Ngoại\r\nThiết kế & Trọng lượng\r\nThiết kế Nguyên khối\r\nChất liệu Vỏ nhựa + khung nhôm\r\nKích thước Dài 153 mm - Ngang 76.2 mm - Dày 7.7 mm\r\nTrọng lượng 153 g\r\nThông tin pin & Sạc\r\nDung lượng pin 3080 mAh\r\nLoại pin Pin chuẩn Li-Po\r\nCông nghệ pin Tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao Mở khóa bằng vân tay\r\nTính năng đặc biệt Mặt kính 2.5D, Chạm 2 lần sáng màn hình\r\nGhi âm Có\r\nRadio Không\r\nXem phim 3GP, MP4, H.263, H.264(MPEG4-AVC)\r\nNghe nhạcLossless, MP3, FLAC\r\nThông tin khác\r\nThời điểm ra mắt 11/2017', 0, 0),
(30, 'Điện thoại Samsung Galaxy J7+', 7290000, 'https://cdn.tgdd.vn/Products/Images/42/112970/samsung-galaxy-j7-plus-2-400x460.png', 2, 'Công nghệ màn hình	Super AMOLED\r\nĐộ phân giải	Full HD (1080 x 1920 pixels)\r\nMàn hình rộng	5.5\"\r\nMặt kính cảm ứng	Kính cường lực\r\nCamera sau\r\nĐộ phân giải	13 MP và 5 MP (2 camera)\r\nQuay phim	Quay phim FullHD 1080p@30fps\r\nĐèn Flash	Có\r\nChụp ảnh nâng cao	Chụp ảnh xóa phông, Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama\r\nCamera trước\r\nĐộ phân giải	16 MP\r\nVideocall	Có\r\nThông tin khác	Quay video Full HD, Camera góc rộng, Nhận diện khuôn mặt, Chế độ làm đẹp\r\nHệ điều hành - CPU\r\nHệ điều hành	Android 7.0 (Nougat)\r\nChipset (hãng SX CPU)	Mediatek Helio P25 Lite 8 nhân\r\nTốc độ CPU	4 nhân 2.39 GHz & 4 nhân 1.69 GHz\r\nChip đồ họa (GPU)	Mali-T880MP2\r\nBộ nhớ & Lưu trữ\r\nRAM	4 GB\r\nBộ nhớ trong	32 GB\r\nBộ nhớ còn lại (khả dụng)	23.9 GB\r\nThẻ nhớ ngoài	MicroSD, hỗ trợ tối đa 256 GB\r\nKết nối\r\nMạng di động	3G, 4G LTE Cat 6\r\nSIM	2 SIM Nano (SIM 2 chung khe thẻ nhớ)\r\nWifi	Wi-Fi 802.11 b/g/n, Wi-Fi Direct, Wi-Fi hotspot\r\nGPS	A-GPS, GLONASS\r\nBluetooth	v4.2, A2DP, LE\r\nCổng kết nối/sạc	Micro USB\r\nJack tai nghe	3.5 mm\r\nKết nối khác	OTG\r\nThiết kế & Trọng lượng\r\nThiết kế	Nguyên khối\r\nChất liệu	Kim loại\r\nKích thước	Dài 152.4 mm - Ngang 74.7 mm - Dày 7.9 mm\r\nTrọng lượng	180 g\r\nThông tin pin & Sạc\r\nDung lượng pin	3000 mAh\r\nLoại pin	Pin chuẩn Li-Ion\r\nCông nghệ pin	Siêu tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao	Mở khóa bằng vân tay, Mở khóa bằng khuôn mặt\r\nTính năng đặc biệt	Mặt kính 2.5D\r\nMàn hình luôn hiển thị AOD\r\nGhi âm	Có\r\nRadio	Có\r\nXem phim	H.265, 3GP, MP4, AVI, WMV, H.263, H.264(MPEG4-AVC), DivX\r\nNghe nhạc	Lossless, Midi, MP3, WAV, WMA, WMA9, AAC, AAC+, AAC++, eAAC+\r\nThông tin khác\r\nThời điểm ra mắt	2017', 0, 0),
(31, 'Điện thoại OPPO F5 6GB', 6490000, 'https://cdn.tgdd.vn/Products/Images/42/142106/oppo-f5-6gb-a-2-400x460.png', 5, 'Công nghệ màn hình : IPS LCD\r\nĐộ phân giải : Full HD+ (1080 x 2160 Pixels)\r\nMàn hình rộng : 6.0\"\r\nMặt kính cảm ứng : Corning Gorilla Glass 5\r\nCamera sau\r\nĐộ phân giải : 16 MP\r\nQuay phim : Quay phim FullHD 1080p@30fps\r\nĐèn Flash : Có\r\nChụp ảnh nâng cao : Chế độ chụp ban đêm (ánh sáng yếu), Tự động lấy nét, Chạm lấy nét, HDR, Panorama\r\nCamera trước\r\nĐộ phân giải : 20 MP\r\nVideocall : Hỗ trợ VideoCall thông qua ứng dụng\r\nThông tin khác : Selfie bằng cử chỉ, Chế độ làm đẹp, Công nghệ Selfie A.I Beauty\r\nHệ điều hành - CPU\r\nHệ điều hành : ColorOS 3.2 (Android 7.1)\r\nChipset (hãng SX CPU) : Mediatek Helio P23 8 nhân 64-bit\r\nTốc độ CPU : 2.5 GHz\r\nChip đồ họa (GPU) : Mali-G71 MP2\r\nBộ nhớ & Lưu trữ\r\nRAM : 6 GB\r\nBộ nhớ trong : 64 GB\r\nBộ nhớ còn lại (khả dụng) : Khoảng 50 GB\r\nThẻ nhớ ngoài : MicroSD, hỗ trợ tối đa 256 GB\r\nKết nối\r\nMạng di động : Hỗ trợ 4G\r\nSIM : 2 Nano SIM\r\nWifi : Wi-Fi 802.11 b/g/n, Wi-Fi hotspot\r\nGPS : A-GPS\r\nBluetooth : v4.2, A2DP, LE\r\nCổng kết nối/sạc : Micro USB\r\nJack tai nghe : 3.5 mm\r\nKết nối khác : OTG\r\nThiết kế & Trọng lượng\r\nThiết kế : Nguyên khối\r\nChất liệu : Nhựa\r\nKích thước : Dài 156.5 mm - Ngang 76 mm - Dày 7.5 mm\r\nTrọng lượng : 152 g\r\nThông tin pin & Sạc\r\nDung lượng pin : 3200 mAh\r\nLoại pin : Pin chuẩn Li-Ion\r\nCông nghệ pin : Tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao : Mở khóa bằng khuôn mặt, Mở khóa bằng vân tay\r\nTính năng đặc biệt : Mặt kính 2.5D\r\nChạm 2 lần sáng màn hình\r\nGhi âm : Có, microphone chuyên dụng chống ồn\r\nRadio : Có\r\nXem phim : MP4, H.263, H.264(MPEG4-AVC)\r\nNghe nhạc : MP3, WAV, eAAC+, FLAC\r\nThông tin khác\r\nThời điểm ra mắt : 12/2017', 0, 0),
(32, 'Điện thoại OPPO A71k (2018)', 2990000, 'https://cdn.tgdd.vn/Products/Images/42/160716/oppo-a71-mau-xanh-2018-1-1-400x460.png', 5, 'Công nghệ màn hình : IPS TFT\r\nĐộ phân giải : HD (720 x 1280 pixels)\r\nMàn hình rộng : 5.2\"\r\nMặt kính cảm ứng : 2.5D Diamond Cut Glass\r\nCamera sau\r\nĐộ phân giải : 13 MP\r\nQuay phim : Quay phim FullHD 1080p@30fps\r\nĐèn Flash : Có\r\nChụp ảnh nâng cao : Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama\r\nCamera trước\r\nĐộ phân giải : 5 MP\r\nVideocall : Hỗ trợ VideoCall thông qua ứng dụng\r\nThông tin khác : Chế độ làm đẹp, Công nghệ Selfie A.I Beauty, Nhận diện khuôn mặt\r\nHệ điều hành - CPU\r\nHệ điều hành : ColorOS 3.2 (Android 7.1)\r\nChipset (hãng SX CPU) : Qualcomm Snapdragon 450 8 nhân 64-bit\r\nTốc độ CPU : 1.8 GHz\r\nChip đồ họa (GPU) : Adreno 506\r\nBộ nhớ & Lưu trữ\r\nRAM : 2 GB\r\nBộ nhớ trong : 16 GB\r\nBộ nhớ còn lại (khả dụng) : Khoảng 8 GB\r\nThẻ nhớ ngoài : MicroSD, hỗ trợ tối đa 256 GB\r\nKết nối\r\nMạng di động : Hỗ trợ 4G\r\nSIM : 2 Nano SIM\r\nWifi : Wi-Fi 802.11 b/g/n, Wi-Fi Direct, Wi-Fi hotspot\r\nGPS : A-GPS, GLONASS\r\nBluetooth : v4.2, LE, EDR\r\nCổng kết nối/sạc : Micro USB\r\nJack tai nghe : 3.5 mm\r\nKết nối khác : OTG\r\nThiết kế & Trọng lượng\r\nThiết kế : Nguyên khối\r\nChất liệu : Nhựa\r\nKích thước : Dài 148.1 mm - Ngang 73.8 mm - Dày 7.6 mm\r\nTrọng lượng : 137 g\r\nThông tin pin & Sạc\r\nDung lượng pin : 3000 mAh\r\nLoại pin : Pin chuẩn Li-Ion\r\nCông nghệ pin : Tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao : Không\r\nTính năng đặc biệt : Mặt kính 2.5D\r\nGhi âm : Có\r\nRadio : Không\r\nXem phim : H.265, 3GP, MP4, AVI, WMV, H.263\r\nNghe nhạc : Midi, MP3, WAV, WMA, AAC, eAAC+, FLAC\r\nThông tin khác\r\nThời điểm ra mắt : 04/2018', 0, 0),
(33, 'Điện thoại Sony Xperia XZ2', 16990000, 'https://cdn.tgdd.vn/Products/Images/42/146014/sony-xperia-xz2-2-400x460.png', 3, 'Công nghệ màn hình : IPS HDR LCD\r\nĐộ phân giải : Full HD+ (1080 x 2160 Pixels)\r\nMàn hình rộng : 5.7\"\r\nMặt kính cảm ứng : Corning Gorilla Glass 5\r\nCamera sau\r\nĐộ phân giải : 19 MP\r\nQuay phim : Quay phim siêu chậm 960 fps, Quay phim FullHD 1080p@30fps, Quay phim FullHD 1080p@60fps, Quay phim 4K 2160p@30fps\r\nĐèn Flash : Có\r\nChụp ảnh nâng cao : Super Slow motion (quay siêu chậm), Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama\r\nCamera trước\r\nĐộ phân giải : 5 MP\r\nVideocall : Hỗ trợ VideoCall thông qua ứng dụng\r\nThông tin khác : Đèn Flash trợ sáng, Nhận diện khuôn mặt, Chế độ làm đẹp\r\nHệ điều hành - CPU\r\nHệ điều hành : Android 8.0 (Oreo)\r\nChipset (hãng SX CPU) : Snapdragon 845 8 nhân\r\nTốc độ CPU : 4 nhân 2.8 GHz Kryo & 4 nhân 1.8 GHz Kryo\r\nChip đồ họa (GPU) : Adreno 630\r\nBộ nhớ & Lưu trữ\r\nRAM : 4 GB\r\nBộ nhớ trong : 64 GB\r\nBộ nhớ còn lại (khả dụng) : Khoảng 50 GB\r\nThẻ nhớ ngoài : MicroSD, hỗ trợ tối đa 400 GB\r\nKết nối\r\nMạng di động : 3G, 4G LTE Cat 18\r\nSIM : 2 Nano SIM\r\nWifi : Wi-Fi 802.11 a/b/g/n/ac, DLNA, Wi-Fi Direct, Wi-Fi hotspot\r\nGPS : BDS, A-GPS, GLONASS\r\nBluetooth : v5.0, apt-X, A2DP, LE\r\nCổng kết nối/sạc : USB Type-C\r\nJack tai nghe : Không\r\nKết nối khác : NFC, OTG, Miracast\r\nThiết kế & Trọng lượng\r\nThiết kế : Nguyên khối, mặt kính cong 2.5D\r\nChất liệu : Khung kim loại + mặt kính cường lực\r\nKích thước : Dài 153 mm - Ngang 72 mm - Dày 11.1 mm\r\nTrọng lượng : 198 g\r\nThông tin pin & Sạc\r\nDung lượng pin : 3180 mAh\r\nLoại pin : Pin chuẩn Li-Ion\r\nCông nghệ pin : Sạc nhanh Quick Charge 3.0, Sạc pin không dây, Tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao : Mở khóa bằng vân tay\r\nTính năng đặc biệt : Chặn cuộc gọi, Sạc pin nhanh, Đèn pin, Chuẩn Kháng nước, Chuẩn kháng bụi\r\nMặt kính 2.5D\r\nCông nghệ âm thanh Hi-Res Audio\r\nChặn tin nhắn\r\nGhi âm cuộc gọi\r\nGhi âm : Có, microphone chuyên dụng chống ồn\r\nRadio : Không\r\nXem phim : H.265, 3GP, MP4, AVI, H.263, H.264(MPEG4-AVC), Xvid\r\nNghe nhạc : MP3, WAV, eAAC+, FLAC\r\nThông tin khác\r\nThời điểm ra mắt : 03/2018', 0, 0),
(34, 'Điện thoại Sony Xperia XZ Dual', 9990000, 'https://cdn.tgdd.vn/Products/Images/42/85235/sony-xperia-xz-h-1-400x460.png', 3, 'Công nghệ màn hình : TRILUMINOS™\r\nĐộ phân giải : Full HD (1080 x 1920 pixels)\r\nMàn hình rộng : 5.2\"\r\nMặt kính cảm ứng : Kính cường lực Gorilla Glass\r\nCamera sau\r\nĐộ phân giải : 23 MP\r\nQuay phim : Quay phim 4K 2160p@30fps\r\nĐèn Flash : Có\r\nChụp ảnh nâng cao : Lấy nét bằng laser, Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama\r\nCamera trước\r\nĐộ phân giải : 13 MP\r\nVideocall : Hỗ trợ VideoCall thông qua ứng dụng\r\nThông tin khác : Camera góc rộng, Selfie ngược sáng HDR, Quay video Full HD\r\nHệ điều hành - CPU\r\nHệ điều hành : Android 7.0 (Nougat)\r\nChipset (hãng SX CPU) : Snapdragon 820 4 nhân 64-bit\r\nTốc độ CPU : 2 nhân 2.15 GHz Kryo & 2 nhân 1.6 GHz Kryo\r\nChip đồ họa (GPU) : Adreno 530\r\nBộ nhớ & Lưu trữ\r\nRAM : 3 GB\r\nBộ nhớ trong : 64 GB\r\nBộ nhớ còn lại (khả dụng) : Khoảng 50.2 GB\r\nThẻ nhớ ngoài : MicroSD, hỗ trợ tối đa 256 GB\r\nKết nối\r\nMạng di động : 3G, 4G LTE Cat 9\r\nSIM : 2 SIM Nano (SIM 2 chung khe thẻ nhớ)\r\nWifi : Wi-Fi 802.11 a/b/g/n/ac, Dual-band, DLNA, Wi-Fi Direct, Wi-Fi hotspot\r\nGPS : A-GPS, GLONASS\r\nBluetooth : v4.2, apt-X, A2DP, LE, EDR\r\nCổng kết nối/sạc : USB Type-C\r\nJack tai nghe : 3.5 mm\r\nKết nối khác : NFC, OTG, Miracast\r\nThiết kế & Trọng lượng\r\nThiết kế : Nguyên khối, mặt kính cong 2.5D\r\nChất liệu : Hợp kim nhôm\r\nKích thước : Dài 146 mm - Ngang 72 mm - Dày 8.1 mm\r\nTrọng lượng : 161 g\r\nThông tin pin & Sạc\r\nDung lượng pin : 2900 mAh\r\nLoại pin : Pin chuẩn Li-Ion\r\nCông nghệ pin : Sạc pin nhanh, Siêu tiết kiệm pin, Tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao : Mở khóa bằng vân tay\r\nTính năng đặc biệt : Công nghệ âm thanh Hi-Res Audio\r\nChuẩn Kháng nước, Chuẩn kháng bụi\r\nMặt kính 2.5D\r\nGhi âm : Có, microphone chuyên dụng chống ồn\r\nRadio : Không\r\nXem phim : H.265, 3GP, MP4, WMV, H.264(MPEG4-AVC), DivX, WMV9, Xvid\r\nNghe nhạc : Lossless, Midi, MP3, WAV, WMA, AAC+, AAC++, FLAC\r\nThông tin khác\r\nThời điểm ra mắt : 9/2016', 0, 0),
(35, 'Điện thoại iPhone 8 Plus 64GB', 23990000, 'https://cdn.tgdd.vn/Products/Images/42/114110/iphone-8-plus-64gb-h1-400x460.png', 1, 'Công nghệ màn hình : LED-backlit IPS LCD\r\nĐộ phân giải : Full HD (1080 x 1920 pixels)\r\nMàn hình rộng : 5.5\"\r\nMặt kính cảm ứng : Kính oleophobic (ion cường lực)\r\nCamera sau\r\nĐộ phân giải : 2 camera 12 MP\r\nQuay phim : Quay phim 4K 2160p@60fps\r\nĐèn Flash : 4 đèn LED (2 tông màu)\r\nChụp ảnh nâng cao : Lấy nét dự đoán, Chụp ảnh xóa phông, Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama, Chống rung quang học (OIS)\r\nCamera trước\r\nĐộ phân giải : 7 MP\r\nVideocall : Có\r\nThông tin khác : Selfie ngược sáng HDR, Camera góc rộng, Quay video Full HD, Nhận diện khuôn mặt\r\nHệ điều hành - CPU\r\nHệ điều hành : iOS 11\r\nChipset (hãng SX CPU) : Apple A11 Bionic 6 nhân\r\nTốc độ CPU : 2 nhân 2.1 GHz Monsoon và 4 nhân 2.1 GHz Mistral\r\nChip đồ họa (GPU) : Apple GPU 3 nhân\r\nBộ nhớ & Lưu trữ\r\nRAM : 3 GB\r\nBộ nhớ trong : 64 GB\r\nBộ nhớ còn lại (khả dụng) : Khoảng 55 GB\r\nThẻ nhớ ngoài : Không\r\nKết nối\r\nMạng di động : 3G, 4G LTE Cat 16\r\nSIM : 1 Nano SIM\r\nWifi : Wi-Fi 802.11 a/b/g/n/ac, Dual-band, Wi-Fi hotspot\r\nGPS : A-GPS, GLONASS\r\nBluetooth : v5.0, A2DP, LE, EDR\r\nCổng kết nối/sạc : Lightning\r\nJack tai nghe : Không\r\nKết nối khác : NFC, OTG\r\nThiết kế & Trọng lượng\r\nThiết kế :  Nguyên khối\r\nChất liệu : Khung kim loại + mặt kính cường lực\r\nKích thước : Dài 158.4 mm - Ngang 78.1 mm - Dày 7.5 mm\r\nTrọng lượng : 202 g\r\nThông tin pin & Sạc\r\nDung lượng pin : 2691 mAh\r\nLoại pin : Pin chuẩn Li-Ion\r\nCông nghệ pin : Sạc pin nhanh, Sạc pin không dây, Tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao : Mở khóa bằng vân tay\r\nTính năng đặc biệt : 3D Touch\r\nGhi âm : Có, microphone chuyên dụng chống ồn\r\nRadio : Không\r\nXem phim : H.265, 3GP, MP4, AVI, WMV, H.263, H.264(MPEG4-AVC)\r\nNghe nhạc : Lossless, Midi, MP3, WAV, WMA9, WMA, AAC, AAC+, AAC++, eAAC+\r\nThông tin khác\r\nThời điểm ra mắt : 09/2017', 0, 0),
(36, 'Điện thoại iPhone 8 64GB', 20990000, 'https://cdn.tgdd.vn/Products/Images/42/114113/iphone-8-64gb-1-400x460-1-400x460.png', 1, 'Công nghệ màn hình : LED-backlit IPS LCD\r\nĐộ phân giải : HD (1334 x 750 Pixels)\r\nMàn hình rộng : 4.7\"\r\nMặt kính cảm ứng : Kính oleophobic (ion cường lực)\r\nCamera sau\r\nĐộ phân giải : 12 MP\r\nQuay phim : Quay phim 4K 2160p@60fps\r\nĐèn Flash : 4 đèn LED (2 tông màu)\r\nChụp ảnh nâng cao : Lấy nét dự đoán, Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama, Chống rung quang học (OIS)\r\nCamera trước\r\nĐộ phân giải : 7 MP\r\nVideocall : Có\r\nThông tin khác : Camera góc rộng, Selfie ngược sáng HDR, Nhận diện khuôn mặt, Quay video Full HD\r\nHệ điều hành - CPU\r\nHệ điều hành : iOS 11\r\nChipset (hãng SX CPU) : Apple A11 Bionic 6 nhân\r\nTốc độ CPU : 2 nhân 2.1 GHz Monsoon và 4 nhân 2.1 GHz Mistral\r\nChip đồ họa (GPU) : Apple GPU 3 nhân\r\nBộ nhớ & Lưu trữ\r\nRAM : 2 GB\r\nBộ nhớ trong : 64 GB\r\nBộ nhớ còn lại (khả dụng) : Khoảng 55 GB\r\nThẻ nhớ ngoài : Không\r\nKết nối\r\nMạng di động : 3G, 4G LTE Cat 16\r\nSIM : 1 Nano SIM\r\nWifi : Wi-Fi 802.11 a/b/g/n/ac, Dual-band, Wi-Fi hotspot\r\nGPS : A-GPS, GLONASS\r\nBluetooth : v5.0, A2DP, LE, EDR\r\nCổng kết nối/sạc : Lightning\r\nJack tai nghe : Không\r\nKết nối khác : NFC, OTG\r\nThiết kế & Trọng lượng\r\nThiết kế : Nguyên khối\r\nChất liệu : Khung kim loại + mặt kính cường lực\r\nKích thước : Dài 138.4 mm - Ngang 67.3 mm - Dày 7.3 mm\r\nTrọng lượng : 148 g\r\nThông tin pin & Sạc\r\nDung lượng pin : 1821 mAh\r\nLoại pin : Pin chuẩn Li-Ion\r\nCông nghệ pin : Sạc pin nhanh, Sạc pin không dây, Tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao : Mở khóa bằng vân tay\r\nTính năng đặc biệt : Chuẩn Kháng nước, Chuẩn kháng bụi\r\n3D Touch\r\nGhi âm : Có, microphone chuyên dụng chống ồn\r\nRadio : Không\r\nXem phim : H.265, 3GP, MP4, AVI, WMV, H.263, H.264(MPEG4-AVC)\r\nNghe nhạc : Lossless, Midi, MP3, WAV, WMA9, WMA, AAC, AAC+, AAC++, eAAC+\r\nThông tin khác\r\nThời điểm ra mắt : 9/2017', 0, 0),
(37, 'Điện thoại iPhone 8 Plus Red 256GB (Đỏ)', 28790000, 'https://cdn.tgdd.vn/Products/Images/42/164236/iphone-8-plus-do-256gb-400x460.png', 1, 'Công nghệ màn hình : LED-backlit IPS LCD\r\nĐộ phân giải : Full HD (1080 x 1920 pixels)\r\nMàn hình rộng : 5.5\"\r\nMặt kính cảm ứng : Kính oleophobic (ion cường lực)\r\nCamera sau\r\nĐộ phân giải : 2 camera 12 MP\r\nQuay phim : Quay phim 4K 2160p@60fps\r\nĐèn Flash : 4 đèn LED (2 tông màu)\r\nChụp ảnh nâng cao : Lấy nét dự đoán, Zoom quang học (Camera kép), Chụp ảnh xóa phông, Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama, Chống rung quang học (OIS)\r\nCamera trước\r\nĐộ phân giải : 7 MP\r\nVideocall : Có\r\nThông tin khác : Camera góc rộng, Selfie ngược sáng HDR, Nhận diện khuôn mặt, Quay video Full HD\r\nHệ điều hành - CPU\r\nHệ điều hành : iOS 11\r\nChipset (hãng SX CPU) : Apple A11 Bionic 6 nhân\r\nTốc độ CPU : 2 nhân 2.1 GHz Monsoon và 4 nhân 2.1 GHz Mistral\r\nChip đồ họa (GPU) : Apple GPU 3 nhân\r\nBộ nhớ & Lưu trữ\r\nRAM : 3 GB\r\nBộ nhớ trong : 256 GB\r\nBộ nhớ còn lại (khả dụng) : Khoảng 246 GB\r\nThẻ nhớ ngoài : Không\r\nKết nối\r\nMạng di động : 3G, 4G LTE Cat 16\r\nSIM : 1 Nano SIM\r\nWifi : Wi-Fi 802.11 a/b/g/n/ac, Dual-band, Wi-Fi hotspot\r\nGPS : A-GPS, GLONASS\r\nBluetooth : v5.0, A2DP, LE, EDR\r\nCổng kết nối/sạc : Lightning\r\nJack tai nghe : Không\r\nKết nối khác : NFC, OTG\r\nThiết kế & Trọng lượng\r\nThiết kế : Nguyên khối\r\nChất liệu : Khung kim loại + mặt kính cường lực\r\nKích thước : Dài 158.4 mm - Ngang 78.1 mm - Dày 7.5 mm\r\nTrọng lượng : 202 g\r\nThông tin pin & Sạc\r\nDung lượng pin : 2691 mAh\r\nLoại pin : Pin chuẩn Li-Ion\r\nCông nghệ pin : Sạc pin nhanh, Sạc pin không dây, Tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao : Mở khóa bằng vân tay\r\nTính năng đặc biệt : 3D Touch\r\nGhi âm : Có, microphone chuyên dụng chống ồn\r\nRadio : Không\r\nXem phim : H.265, 3GP, MP4, AVI, WMV, H.263, H.264(MPEG4-AVC)\r\nNghe nhạc : Midi, Lossless, MP3, WAV, WMA, WMA9, AAC, AAC+, AAC++, eAAC+\r\nThông tin khác\r\nThời điểm ra mắt : Đang cập nhật', 0, 0),
(38, 'Điện thoại Xiaomi Mi 8', 12990000, 'https://cdn.tgdd.vn/Products/Images/42/167539/xiaomi-mi-8-black-400x460.png', 4, 'Công nghệ màn hình : Super AMOLED\r\nĐộ phân giải : Full HD+ (1080 x 2248 Pixels)\r\nMàn hình rộng : 6.21\"\r\nMặt kính cảm ứng : Corning Gorilla Glass 5\r\nCamera sau\r\nĐộ phân giải : 2 camera 12 MP\r\nQuay phim : Quay phim FullHD 1080p@240fps, Quay phim FullHD 1080p@30fps, Quay phim 4K 2160p@60fps\r\nĐèn Flash : Đèn LED kép\r\nChụp ảnh nâng cao : Chụp ảnh xóa phông, Chế độ Slow Motion, Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama, Chống rung quang học (OIS), Beautify\r\nCamera trước\r\nĐộ phân giải : 20 MP\r\nVideocall : Hỗ trợ VideoCall thông qua ứng dụng\r\nThông tin khác : Nhận diện khuôn mặt, Chế độ làm đẹp, Quay video Full HD, Tự động lấy nét\r\nHệ điều hành - CPU\r\nHệ điều hành : Android 8.1 (Oreo)\r\nChipset (hãng SX CPU) : Snapdragon 845 8 nhân\r\nTốc độ CPU : 4 nhân 2.8 GHz Kryo & 4 nhân 1.8 GHz Kryo\r\nChip đồ họa (GPU) : Adreno 630\r\nBộ nhớ & Lưu trữ\r\nRAM : 6 GB\r\nBộ nhớ trong : 64 GB\r\nBộ nhớ còn lại (khả dụng) : Đang cập nhật\r\nThẻ nhớ ngoài : Không\r\nKết nối\r\nMạng di động : Hỗ trợ 4G\r\nSIM : 2 Nano SIM\r\nWifi : Wi-Fi 802.11 a/b/g/n/ac, Wi-Fi Direct, Wi-Fi hotspot\r\nGPS : A-GPS, GLONASS\r\nBluetooth : v5.0, A2DP, LE\r\nCổng kết nối/sạc : USB Type-C\r\nJack tai nghe : Không\r\nKết nối khác : NFC\r\nThiết kế & Trọng lượng\r\nThiết kế : Nguyên khối\r\nChất liệu : Khung kim loại + mặt kính cường lực\r\nKích thước : Dài 154.9 mm - Ngang 74.8 mm - Dày 7.6 mm\r\nTrọng lượng : 175 g\r\nThông tin pin & Sạc\r\nDung lượng pin : 3400 mAh\r\nLoại pin : Pin chuẩn Li-Ion\r\nCông nghệ pin : Sạc nhanh Quick Charge 3.0, Tiết kiệm pin, Siêu tiết kiệm pin\r\nTiện ích\r\nBảo mật nâng cao : Mở khóa bằng vân tay, Mở khóa bằng khuôn mặt\r\nTính năng đặc biệt : Đèn pin\r\nMặt kính 2.5D\r\nGhi âm : Có, microphone chuyên dụng chống ồn\r\nRadio : Không\r\nXem phim : 3GP, MP4, AVI, WMV, DivX, Xvid\r\nNghe nhạc : Midi, MP3, WAV, WMA, AAC, OGG, FLAC\r\nThông tin khác\r\nThời điểm ra mắt : 08/2018', 5, 1),
(39, 'Điện thoại iPhone Xr 256GB', 24990000, 'https://cdn.tgdd.vn/Products/Images/42/190326/iphone-xr-256gb-white-400x460.png', 1, 'Công nghệ màn hình: IPS LCD\r\nĐộ phân giải: 828 x 1792 Pixels\r\nMàn hình rộng: 6.1\"\r\nMặt kính cảm ứng: Kính oleophobic (ion cường lực)\r\nCamera sau\r\nĐộ phân giải: 12 MP\r\nQuay phim: Quay phim FullHD 1080p@30fps, Quay phim FullHD 1080p@60fps, Quay phim FullHD 1080p@120fps, Quay phim FullHD 1080p@240fps, Quay phim 4K 2160p@24fps, Quay phim 4K 2160p@30fps, Quay phim 4K 2160p@60fps\r\nĐèn Flash: 4 đèn LED (2 tông màu)\r\nChụp ảnh nâng cao: Chụp ảnh xóa phông, Chế độ Slow Motion, Chế độ chụp ban đêm (ánh sáng yếu), A.I Camera, Điều chỉnh khẩu độ, Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama, Chống rung quang học (OIS)\r\nCamera trước\r\nĐộ phân giải: 7 MP\r\nVideocall: Có\r\nThông tin khác: Selfie ngược sáng HDR, Camera góc rộng, Nhận diện khuôn mặt, Quay video Full HD\r\nHệ điều hành - CPU\r\nHệ điều hành: iOS 12\r\nChipset (hãng SX CPU): Apple A12 Bionic 6 nhân\r\nTốc độ CPU: 2 nhân 2.5 GHz Vortex & 4 nhân 1.6 GHz Tempest\r\nChip đồ họa (GPU): Apple GPU 4 nhân\r\nBộ nhớ & Lưu trữ\r\nRAM: 3 GB\r\nBộ nhớ trong: 256 GB\r\nBộ nhớ còn lại (khả dụng): Khoảng 249 GB\r\nThẻ nhớ ngoài: Không\r\nKết nối\r\nMạng di động: Hỗ trợ 4G\r\nSIM: Nano SIM & eSIM\r\nWifi: Wi-Fi 802.11 a/b/g/n/ac, Dual-band, Wi-Fi hotspot\r\nGPS: A-GPS, GLONASS\r\nBluetooth: A2DP, LE, v5.0\r\nCổng kết nối/sạc: Lightning\r\nJack tai nghe: Không\r\nKết nối khác: NFC, OTG\r\nThiết kế & Trọng lượng\r\nThiết kế: Nguyên khối\r\nChất liệu: Khung nhôm + mặt kính cường lực\r\nKích thước: Dài 150.9 mm - Ngang 75.7 mm - Dày 8.3 mm\r\nTrọng lượng: 194 g\r\nThông tin pin & Sạc\r\nDung lượng pin: 2942 mAh\r\nLoại pin: Pin chuẩn Li-Ion\r\nCông nghệ pin: Tiết kiệm pin, Sạc pin nhanh, Sạc pin không dây\r\nTiện ích\r\nBảo mật nâng cao: Nhận diện khuôn mặt Face ID\r\nTính năng đặc biệt: Chuẩn Kháng nước, Chuẩn kháng bụi, Sạc pin nhanh, Apple Pay\r\nGhi âm: Có, microphone chuyên dụng chống ồn\r\nRadio: Không\r\nXem phim: H.265, 3GP, MP4, AVI, WMV, H.263, H.264(MPEG4-AVC)\r\nNghe nhạc: Lossless, Midi, MP3, WAV, WMA, WMA9, AAC, AAC+, AAC++, eAAC+\r\nThông tin khác\r\nThời điểm ra mắt: 11/2018', 5, 1),
(40, 'Điện thoại iPhone Xs Max 256GB', 37990000, 'https://cdn.tgdd.vn/Products/Images/42/190322/iphone-xs-max-256gb-white-400x460.png', 1, 'Công nghệ màn hình: OLED\r\nĐộ phân giải: 1242 x 2688 Pixels\r\nMàn hình rộng: 6.5\"\r\nMặt kính cảm ứng: Kính oleophobic (ion cường lực)\r\nCamera sau\r\nĐộ phân giải: 2 camera 12 MP\r\nQuay phim: Quay phim FullHD 1080p@30fps, Quay phim FullHD 1080p@60fps, Quay phim FullHD 1080p@120fps, Quay phim FullHD 1080p@240fps, Quay phim 4K 2160p@24fps, Quay phim 4K 2160p@30fps, Quay phim 4K 2160p@60fps\r\nĐèn Flash: 4 đèn LED (2 tông màu)\r\nChụp ảnh nâng cao: Zoom quang học (Camera kép), Chụp ảnh xóa phông, Chế độ Slow Motion, Chế độ chụp ban đêm (ánh sáng yếu), A.I Camera, Điều chỉnh khẩu độ, Tự động lấy nét, Chạm lấy nét, Nhận diện khuôn mặt, HDR, Panorama, Chống rung quang học (OIS)\r\nCamera trước\r\nĐộ phân giải: 7 MP\r\nVideocall: Có\r\nThông tin khác: Selfie ngược sáng HDR, Camera góc rộng, Nhận diện khuôn mặt, Quay video Full HD\r\nHệ điều hành - CPU\r\nHệ điều hành: iOS 12\r\nChipset (hãng SX CPU): Apple A12 Bionic 6 nhân\r\nTốc độ CPU: 2 nhân 2.5 GHz Vortex & 4 nhân 1.6 GHz Tempest\r\nChip đồ họa (GPU): Apple GPU 4 nhân\r\nBộ nhớ & Lưu trữ\r\nRAM: 4 GB\r\nBộ nhớ trong: 256 GB\r\nBộ nhớ còn lại (khả dụng): Khoảng 249 GB\r\nThẻ nhớ ngoài: Không\r\nKết nối\r\nMạng di động: Hỗ trợ 4G\r\nSIM: Nano SIM & eSIM\r\nWifi: Wi-Fi 802.11 a/b/g/n/ac, Dual-band, Wi-Fi hotspot\r\nGPS: A-GPS, GLONASS\r\nBluetooth: LE, A2DP, v5.0\r\nCổng kết nối/sạc: Lightning\r\nJack tai nghe: Không\r\nKết nối khác: NFC, OTG\r\nThiết kế & Trọng lượng\r\nThiết kế: Nguyên khối\r\nChất liệu: Khung thép không gỉ + mặt kính cường lực\r\nKích thước: Dài 157.5 mm - Ngang 77.4 mm - Dày 7.7 mm\r\nTrọng lượng: 208 g\r\nThông tin pin & Sạc\r\nDung lượng pin: 3174 mAh\r\nLoại pin: Pin chuẩn Li-Ion\r\nCông nghệ pin: Tiết kiệm pin, Sạc pin nhanh, Sạc pin không dây\r\nTiện ích\r\nBảo mật nâng cao: Nhận diện khuôn mặt Face ID\r\nTính năng đặc biệt: Chuẩn Kháng nước, Chuẩn kháng bụi, Sạc pin nhanh, Apple Pay, 3D Touch\r\nGhi âm: Có, microphone chuyên dụng chống ồn\r\nRadio: Không\r\nXem phim: H.265, 3GP, MP4, AVI, WMV, H.263, H.264(MPEG4-AVC)\r\nNghe nhạc: Lossless, Midi, MP3, WAV, WMA9, WMA, AAC, AAC+, AAC++, eAAC+\r\nThông tin khác\r\nThời điểm ra mắt: 11/2018\r\n', 13, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_DonHang` (`ID_DonHang`),
  ADD KEY `ID_TenSanPham` (`ID_TenSanPham`);

--
-- Indexes for table `danhgia`
--
ALTER TABLE `danhgia`
  ADD PRIMARY KEY (`ID_NguoiDung`,`ID_SanPham`);

--
-- Indexes for table `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`ID_DonHang`);

--
-- Indexes for table `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`ID_LoaiSP`);

--
-- Indexes for table `nguoidung`
--
ALTER TABLE `nguoidung`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `quangcao`
--
ALTER TABLE `quangcao`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_LoaiSP` (`ID_LoaiSP`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=163;

--
-- AUTO_INCREMENT for table `donhang`
--
ALTER TABLE `donhang`
  MODIFY `ID_DonHang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=166;

--
-- AUTO_INCREMENT for table `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `ID_LoaiSP` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `nguoidung`
--
ALTER TABLE `nguoidung`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `quangcao`
--
ALTER TABLE `quangcao`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD CONSTRAINT `chitietdonhang_ibfk_1` FOREIGN KEY (`ID_DonHang`) REFERENCES `donhang` (`ID_DonHang`),
  ADD CONSTRAINT `chitietdonhang_ibfk_2` FOREIGN KEY (`ID_TenSanPham`) REFERENCES `sanpham` (`ID`);

--
-- Constraints for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `sanpham_ibfk_1` FOREIGN KEY (`ID_LoaiSP`) REFERENCES `loaisanpham` (`ID_LoaiSP`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
