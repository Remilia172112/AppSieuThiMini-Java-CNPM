/*Chạy bằng MySQL*/
DROP DATABASE quanlysieuthimini;
CREATE DATABASE quanlysieuthimini;
USE quanlysieuthimini;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*Tạo bảng*/
CREATE TABLE `DANHMUCCHUCNANG` (
    `MCN` VARCHAR(50) NOT NULL COMMENT 'Mã chức năng',
    `TEN` VARCHAR(255) NOT NULL COMMENT 'Tên chức năng',
    `TT` INT(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái',
    PRIMARY KEY(MCN) 
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `CTQUYEN` (
    `MNQ` INT(11) NOT NULL COMMENT 'Mã nhóm quyền',
    `MCN` VARCHAR(50) NOT NULL COMMENT 'Mã chức năng',
    `HANHDONG` VARCHAR(255) NOT NULL COMMENT 'Hành động thực hiện',
    PRIMARY KEY(MNQ, MCN, HANHDONG)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `NHOMQUYEN` (
    `MNQ` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã nhóm quyền',
    `TEN` VARCHAR(255) NOT NULL COMMENT 'Tên nhóm quyền',
    `TT` INT(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái',
    PRIMARY KEY(MNQ) 
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `NHANVIEN` (
    `MNV` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã nhân viên',
    `HOTEN` VARCHAR(255) NOT NULL COMMENT 'Họ và tên NV',
    `GIOITINH` INT(11) NOT NULL COMMENT 'Giới tính',
    `NGAYSINH` DATE NOT NULL COMMENT 'Ngày sinh',
    `SDT` VARCHAR(11) NOT NULL COMMENT 'Số điện thoại',
    `EMAIL` VARCHAR(50) NOT NULL UNIQUE COMMENT 'Email',
    `TT` INT(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái',
    PRIMARY KEY(MNV)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;


CREATE TABLE `TAIKHOAN` (
    `MNV` INT(11) NOT NULL COMMENT 'Mã nhân viên',
    `MK` VARCHAR(255) NOT NULL COMMENT 'Mật khẩu',
    `TDN` VARCHAR(255) NOT NULL UNIQUE COMMENT 'Tên đăng nhập',
    `MNQ` INT(11) NOT NULL COMMENT 'Mã nhóm quyền',
    `TT` INT(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái',
    `OTP` VARCHAR(50) DEFAULT NULL COMMENT 'Mã OTP',
    PRIMARY KEY(MNV, TDN)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
CREATE TABLE `KHACHHANG` (
    `MKH` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã khách hàng',
    `HOTEN` VARCHAR(255) NOT NULL COMMENT 'Họ và tên KH',
    `NGAYTHAMGIA` DATE NOT NULL COMMENT 'Ngày tạo dữ liệu',
    `DIACHI` VARCHAR(255) COMMENT 'Địa chỉ',
    `SDT` VARCHAR(11) UNIQUE NOT NULL COMMENT 'Số điện thoại',
    `EMAIL` VARCHAR(50) UNIQUE COMMENT 'Email',
    `TT` INT(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái',
    PRIMARY KEY(MKH)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `PHIEUXUAT` (
    `MPX` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã phiếu xuất',
    `MNV` INT(11) DEFAULT 1 COMMENT 'Mã nhân viên',
    `MKH` INT(11) NOT NULL COMMENT 'Mã khách hàng',
    `TIEN` INT(11) NOT NULL COMMENT 'Tổng tiền',
    `TG` DATETIME DEFAULT current_timestamp() COMMENT 'Thời gian tạo',
    `TT` INT(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái',
    PRIMARY KEY(MPX)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `CTPHIEUXUAT` (
    `MPX` INT(11) NOT NULL COMMENT 'Mã phiếu xuất',
    `MSP` INT(11) NOT NULL COMMENT 'Mã sản phẩm',
    `MKM` VARCHAR(255) COMMENT 'Mã khuyến mãi',
    `SL` INT(11) NOT NULL COMMENT 'Số lượng',
    `TIENXUAT` INT(11) NOT NULL COMMENT 'Tiền xuất',
    PRIMARY KEY(MPX, MSP)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
CREATE TABLE `PHIEUTRA` (
    `MPX` INT(11) NOT NULL COMMENT 'Mã phiếu xuất',
    `MNV` INT(11) NOT NULL COMMENT 'Mã nhân viên',
    `MKH` INT(11) NOT NULL COMMENT 'Mã khách hàng',
    `TIEN` INT(11) NOT NULL COMMENT 'Tổng tiền',
    `TG` DATETIME DEFAULT current_timestamp() COMMENT 'Thời gian tạo',
    `TT` INT(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái',
    PRIMARY KEY(MPX)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `CTPHIEUTRA` (
    `MPX` INT(11) NOT NULL COMMENT 'Mã phiếu xuất',
    `MSP` INT(11) NOT NULL COMMENT 'Mã sản phẩm',
    `SL` INT(11) NOT NULL COMMENT 'Số lượng',
    `TIENTHU` INT(11) NOT NULL COMMENT 'Tiền thu',
    `LYDO` VARCHAR(255) NOT NULL COMMENT 'Lý do',
    PRIMARY KEY(MPX, MSP)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `NHACUNGCAP` (
    `MNCC` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã nhà cung cấp',
    `TEN` VARCHAR(255) NOT NULL COMMENT 'Tên NCC',
    `DIACHI` VARCHAR(255) COMMENT 'Địa chỉ',
    `SDT` VARCHAR(12) COMMENT 'Số điện thoại',
    `EMAIL` VARCHAR(50) COMMENT 'Email',
    `TT` INT(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái',
    PRIMARY KEY(MNCC)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `PHIEUNHAP` (
    `MPN` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã phiếu nhập',
    `MNV` INT(11) NOT NULL COMMENT 'Mã nhân viên',
    `MNCC` INT(11) NOT NULL COMMENT 'Mã nhà cung cấp',
    `TIEN` INT(11) NOT NULL COMMENT 'Tổng tiền',
    `TG` DATETIME DEFAULT current_timestamp() COMMENT 'Thời gian tạo',
    `TT` INT(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái',
    PRIMARY KEY(MPN)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `CTPHIEUNHAP` (
    `MPN` INT(11) NOT NULL COMMENT 'Mã phiếu nhập',
    `MSP` INT(11) NOT NULL COMMENT 'Mã sản phẩm',
    `SL` INT(11) NOT NULL COMMENT 'Số lượng',
    `TIENNHAP` INT(11) NOT NULL COMMENT 'Tiền nhập',
    `HINHTHUC` INT(11) NOT NULL DEFAULT 0 COMMENT 'Tổng tiền',
    PRIMARY KEY(MPN, MSP)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
CREATE TABLE `PHIEUKIEMKE` (
    `MPKK` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã phiếu kiểm kê',
    `MNV` INT(11) NOT NULL COMMENT 'Mã nhân viên',
    `TG` DATETIME DEFAULT current_timestamp() COMMENT 'Thời gian tạo',
    `TT` INT(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái',
    PRIMARY KEY(MPKK)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `CTPHIEUKIEMKE` (
    `MPKK` INT(11) NOT NULL COMMENT 'Mã phiếu kiểm kê',
    `MSP` INT(11) NOT NULL COMMENT 'Mã sản phẩm',
    `TRANGTHAISP` INT(11) NOT NULL COMMENT 'Trạng thái sản phẩm',
    `GHICHU` VARCHAR(255) COMMENT 'Ghi chú',
    PRIMARY KEY(MPKK, MSP)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
CREATE TABLE `MAKHUYENMAI` (
    `MKM` VARCHAR(255) NOT NULL COMMENT 'Mã khuyến mãi',
    `MNV` INT(11) NOT NULL COMMENT 'Mã nhân viên',
    `TGBD` DATE NOT NULL COMMENT 'Thời gian bắt đầu',
    `TGKT` DATE NOT NULL COMMENT 'Thời gian kết thúc',
    `TT` INT(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái',
    PRIMARY KEY(MKM)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `CTMAKHUYENMAI` (
    `MKM` VARCHAR(255) NOT NULL COMMENT 'Mã khuyến mãi',
    `MSP` INT(11) NOT NULL COMMENT 'Mã sản phẩm',
    `PTG` INT(11) NOT NULL COMMENT 'Phần trăm giảm',
    PRIMARY KEY(MKM, MSP)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `SANPHAM` (
    `MSP` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã sản phẩm',
    `TEN` VARCHAR(255) NOT NULL COMMENT 'Tên sản phẩm',
    `HINHANH` VARCHAR(255) NOT NULL COMMENT 'Hình sản phẩm',
    `DANHMUC` VARCHAR(255) NOT NULL COMMENT 'Danh mục',
    `NAMXB` INT(11) NOT NULL COMMENT 'Năm xuất bản',
    `MNXB` INT(11) NOT NULL COMMENT 'Mã nhà xuất bản',
    `TENTG` VARCHAR(255) NOT NULL COMMENT 'Tên tác giả',
    `MKVS` INT(11) NOT NULL COMMENT 'Mã khu vực sách',
    `TIENX` INT(11) NOT NULL COMMENT 'Tiền xuất',
    `TIENN` INT(11) NOT NULL COMMENT 'Tiền nhập',
    `SL` INT(11) DEFAULT 0 COMMENT 'Số lượng',
    `ISBN` VARCHAR(255) UNIQUE NOT NULL COMMENT 'Mã ISBN',
    `TT` INT(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái',
    PRIMARY KEY(MSP)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `KHUVUCSACH` (
    `MKVS` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã khu vực sách',
    `TEN` VARCHAR(255) NOT NULL COMMENT 'Tên khu vực sách',
    `GHICHU` VARCHAR(255) DEFAULT '' COMMENT 'Ghi chú',
    `TT` INT(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái',
    PRIMARY KEY(MKVS)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

/*Thêm dữ liệu*/

INSERT INTO `DANHMUCCHUCNANG`(`MCN`, `TEN`, `TT`)
VALUES 
        ('sanpham', 'Quản lý sản phẩm', 1),
        ('khachhang', 'Quản lý khách hàng', 1),
        ('nhacungcap', 'Quản lý nhà cung cấp', 1),
        ('nhanvien', 'Quản lý nhân viên', 1),
        ('nhaphang', 'Quản lý nhập hàng', 1),
        ('xuathang', 'Quản lý xuất hàng', 1),
        ('kiemke', 'Quản lý kiểm kê', 1),
        ('khuvucsach', 'Quản lý khu vực kho', 1),
        ('nhomquyen', 'Quản lý nhóm quyền', 1),
        ('taikhoan', 'Quản lý tài khoản', 1),
        ('makhuyenmai', 'Quản lý mã khuyến mãi', 1),
        ('thongke', 'Quản lý thống kê', 1),

INSERT INTO `CTQUYEN` (`MNQ`, `MCN`, `HANHDONG`)
VALUES
		(1, 'sanpham', 'create'),
        (1, 'sanpham', 'delete'),
        (1, 'sanpham', 'update'),
        (1, 'sanpham', 'view'),
        (1, 'khachhang', 'create'),
        (1, 'khachhang', 'delete'),
        (1, 'khachhang', 'update'),
        (1, 'khachhang', 'view'),
        (1, 'nhacungcap', 'create'),
        (1, 'nhacungcap', 'delete'),
        (1, 'nhacungcap', 'update'),
        (1, 'nhacungcap', 'view'),
        (1, 'nhaxuatban', 'create'),
        (1, 'nhaxuatban', 'delete'),
        (1, 'nhaxuatban', 'update'),
        (1, 'nhaxuatban', 'view'),
        (1, 'nhanvien', 'create'),
        (1, 'nhanvien', 'delete'),
        (1, 'nhanvien', 'update'),
        (1, 'nhanvien', 'view'),
        (1, 'nhaphang', 'create'),
        (1, 'nhaphang', 'delete'),
        (1, 'nhaphang', 'update'),
        (1, 'nhaphang', 'view'),
        (1, 'xuathang', 'create'),
        (1, 'xuathang', 'delete'),
        (1, 'xuathang', 'update'),
        (1, 'xuathang', 'view'),
        (1, 'kiemke', 'create'),
        (1, 'kiemke', 'delete'),
        (1, 'kiemke', 'update'),
        (1, 'kiemke', 'view'),
        (1, 'trahang', 'create'),
        (1, 'trahang', 'delete'),
        (1, 'trahang', 'update'),
        (1, 'trahang', 'view'),
        (1, 'khuvucsach', 'create'),
        (1, 'khuvucsach', 'delete'),
        (1, 'khuvucsach', 'update'),
        (1, 'khuvucsach', 'view'),
        (1, 'nhomquyen', 'create'),
        (1, 'nhomquyen', 'delete'),
        (1, 'nhomquyen', 'update'),
        (1, 'nhomquyen', 'view'),
        (1, 'taikhoan', 'create'),
        (1, 'taikhoan', 'delete'),
        (1, 'taikhoan', 'update'),
        (1, 'taikhoan', 'view'),
        (1, 'makhuyenmai', 'create'),
        (1, 'makhuyenmai', 'delete'),
        (1, 'makhuyenmai', 'update'),
        (1, 'makhuyenmai', 'view'),
        (1, 'thongke', 'create'),
        (1, 'thongke', 'delete'),
        (1, 'thongke', 'update'),
        (1, 'thongke', 'view'),
        (2, 'sanpham', 'view'),
        (2, 'khachhang', 'create'),
        (2, 'khachhang', 'delete'),
        (2, 'khachhang', 'update'),
        (2, 'khachhang', 'view'),
        (2, 'xuathang', 'create'),
        (2, 'xuathang', 'update'),
        (2, 'xuathang', 'view'),
        (3, 'sanpham', 'create'),
        (3, 'sanpham', 'delete'),
        (3, 'sanpham', 'update'),
        (3, 'sanpham', 'view'),
        (3, 'nhaphang', 'create'),
        (3, 'nhaphang', 'update'),
        (3, 'nhaphang', 'view'),
        (3, 'kiemke', 'create'),
        (3, 'kiemke', 'delete'),
        (3, 'kiemke', 'update'),
        (3, 'kiemke', 'view'),
        (3, 'nhacungcap', 'create'),
        (3, 'nhacungcap', 'delete'),
        (3, 'nhacungcap', 'update'),
        (3, 'nhacungcap', 'view'),,

INSERT INTO `NHOMQUYEN` (`TEN`, `TT`)
VALUES
        ('Quản lý cửa hàng', 1),
        ('Nhân viên bán hàng', 1),
        ('Nhân viên quản lý kho', 1),


INSERT INTO `NHANVIEN` (`HOTEN`, `GIOITINH`, `NGAYSINH`, `SDT`, `EMAIL`, `TT`)
VALUES
        ('Lê Thế Minh', 0, '2077-01-01', '0505555505', 'remchan.com@gmail.com', 1),
        ('Huỳnh Khôi Nguyên', 1, '2023-05-06', '0123456789', 'nguyeney111@gmail.com', 1),
        ('Trần Gia Nguyễn', 1, '2004-07-17', '0387913347', 'trangianguyen.com@gmail.com', 1),
        ('Hoàng Gia Bảo', 1, '2003-04-11', '0355374322', 'musicanime2501@gmail.com', 1),
        ('Đỗ Nam Công Chính', 1, '2003-04-11', '0123456789', 'chinchin@gmail.com', 1),
        ('Đinh Ngọc Ân', 1, '2003-04-03', '0123456789', 'ngocan@gmail.com', 1);

INSERT INTO `TAIKHOAN` (`MNV`, `TDN`, `MK`, `MNQ`, `TT`, `OTP`)
VALUES
        (1, 'admin', '$2a$12$6GSkiQ05XjTRvCW9MB6MNuf7hOJEbbeQx11Eb8oELil1OrCq6uBXm', 1, 1, 'null'),
        (2, 'NV2', '$2a$12$6GSkiQ05XjTRvCW9MB6MNuf7hOJEbbeQx11Eb8oELil1OrCq6uBXm', 2, 1, 'null'),
        (3, 'NV3', '$2a$12$6GSkiQ05XjTRvCW9MB6MNuf7hOJEbbeQx11Eb8oELil1OrCq6uBXm', 3, 1, 'null');

INSERT INTO `KHACHHANG` (`HOTEN`, `DIACHI`, `SDT`, `TT`, `NGAYTHAMGIA`)
VALUES
        ('Mặc định', '', '', 1, '2024-04-15 09:52:29'),
        ('Nguyễn Văn A', 'Gia Đức, Ân Đức, Hoài Ân, Bình Định', '0387913347', 1, '2024-04-15 09:52:29'),
        ('Trần Nhất Nhất', '205 Trần Hưng Đạo, Phường 10, Quận 5, Thành phố Hồ Chí Minh', '0123456789', 1, '2024-04-15 09:52:29'),
        ('Hoàng Gia Bo', 'Khoa Trường, Hoài Ân, Bình Định', '0987654321', 1, '2024-04-15 09:52:29'),
        ('Hồ Minh Hưng', 'Khoa Trường, Hoài Ân, Bình Định', '0867987456', 1, '2024-04-15 09:52:29'),
        ('Nguyễn Thị Minh Anh', '123 Phố Huế, Quận Hai Bà Trưng, Hà Nội', '0935123456', 1, '2024-04-16 17:59:57'),
        ('Trần Đức Minh', '789 Đường Lê Hồng Phong, Thành phố Đà Nẵng', '0983456789', 1, '2024-04-16 18:08:12'),
        ('Lê Hải Yến', '456 Tôn Thất Thuyết, Quận 4, Thành phố Hồ Chí Minh', '0977234567', 1, '2024-04-16 18:08:47'),
        ('Phạm Thanh Hằng', '102 Lê Duẩn, Thành phố Hải Phòng', '0965876543', 1, '2024-04-16 18:12:59'),
        ('Hoàng Đức Anh', '321 Lý Thường Kiệt, Thành phố Cần Thơ', '0946789012', 1, '2024-04-16 18:13:47'),
        ('Ngô Thanh Tùng', '987 Trần Hưng Đạo, Quận 1, Thành phố Hồ Chí Minh', '0912345678', 1, '2024-04-16 18:14:12'),
        ('Võ Thị Kim Ngân', '555 Nguyễn Văn Linh, Quận Nam Từ Liêm, Hà Nội', '0916789123', 1, '2024-04-16 18:15:11'),
        ('Đỗ Văn Tú', '777 Hùng Vương, Thành phố Huế', '0982345678', 1, '2024-04-30 18:15:56'),
        ('Lý Thanh Trúc', '888 Nguyễn Thái Học, Quận Ba Đình, Hà Nội', '0982123456', 1, '2024-04-16 18:16:22'),
        ('Bùi Văn Hoàng', '222 Đường 2/4, Thành phố Nha Trang', '0933789012', 1, '2024-04-16 18:16:53'),
        ('Lê Văn Thành', '23 Đường 3 Tháng 2, Quận 10, TP. Hồ Chí Minh', '0933456789', 1, '2024-04-16 18:17:46'),
        ('Nguyễn Thị Lan Anh', '456 Lê Lợi, Quận 1, TP. Hà Nội', '0965123456', 1, '2024-04-16 18:18:10'),
        ('Phạm Thị Mai', '234 Lê Hồng Phong, Quận 5, TP. Hồ Chí Minh', '0946789013', 1, '2024-04-17 18:18:34'),
        ('Hoàng Văn Nam', ' 567 Phố Huế, Quận Hai Bà Trưng, Hà Nội', '0912345679', 1, '2024-04-17 18:19:16');


INSERT INTO `PHIEUXUAT` (`MNV`, `MKH`, `TIEN`, `TG`, `TT`)
VALUES
        (1, 1, 100000, '2024-04-18 17:34:12', 1),
        (1, 2, 200000, '2024-04-17 18:19:51', 1);

INSERT INTO `CTPHIEUXUAT` (`MPX`, `MSP`, `SL`,  `TIENXUAT`)
VALUES
        (1, 1, 2, 100000),
        (1, 2, 2, 200000),
        (2, 3, 2, 300000),
        (2, 4, 2, 400000);

INSERT INTO `NHACUNGCAP` (`TEN`, `DIACHI`, `SDT`, `EMAIL`, `TT`)
VALUES
        ('MINH LONG BOOK', '33 Đỗ Thừa Tự, Tân Quý, Tân Phú, Thành phố Hồ Chí Minh', '02866751142', 'cskh@minhlongbook.vn', 1),
        ('ĐINH TỊ BOOK', 'Số 78, Đường số 1, P. 4, Q. Gò Vấp, TP. Hồ Chí Minh', '02473093388', 'contacts@dinhtibooks.vn', 1),
        ('NHÃ NAM', 'Số 59, Đỗ Quang, Trung Hoà, Cầu Giấy, Hà Nội', '02435146876', '', 1),
        ('KIM ĐỒNG', 'Số 55 Quang Trung, Nguyễn Du, Hai Bà Trưng, Hà Nội', '1900571595', 'cskh_online@nxbkimdong.com.vn', 1),
        ('1980 Books', '42/35 Nguyễn Minh Hoàng-Phường 12, Quận Tân Bình, Thành Phố Hồ Chí Minh', '02839333216', 'info.1980books@gmail.com', 1),
        ('THIÊN LONG', 'Tầng 10, Sofic Tower, Số 10 Đường Mai Chí Thọ, Phường Thủ Thiêm, Thành Phố Thủ Đức, Thành Phố Hồ Chí Minh', '1900866819', 'salesonline@thienlongvn.com', 1);

INSERT INTO `PHIEUNHAP` (`MNV`, `MNCC`, `TIEN`, `TG`, `TT`)
VALUES
        (1, 1, 100000, '2024-04-01 01:09:27', 1),
        (1, 1, 200000, '2024-04-02 01:09:27', 1);

INSERT INTO `CTPHIEUNHAP` (`MPN`, `MSP`, `SL`, `TIENNHAP`, `HINHTHUC`)
VALUES
        (1, 1, 2, 20000, 0),
        (1, 2, 2, 40000, 0),
        (2, 3, 2, 40000, 0),
        (2, 4, 2, 80000, 0);

INSERT INTO `PHIEUKIEMKE` (`MNV` , `TG` , `TT`) 
VALUES
        (1 , '2024-04-01 01:09:27' , 1);

INSERT INTO `CTPHIEUKIEMKE` (`MPKK`,`MSP` ,`TRANGTHAISP`, `GHICHU`)
VALUES 
        (1, 1, 1 ,'Hư' );

INSERT INTO `MAKHUYENMAI` (`MKM`,`MNV`,`TGBD`,`TGKT`,`TT`)
VALUES
        ('GT2024', 1, '2024-04-01 00:00:00', '2024-05-01 00:00:00', 1),
        ('MINGEY2024', 1, '2024-05-01 00:00:00', '2024-05-20 00:00:00', 1);

INSERT INTO `CTMAKHUYENMAI` (`MKM`, `MSP`,`PTG`)
VALUES
        ('GT2024', 1, 20),
        ('GT2024', 2, 20),
        ('GT2024', 3, 20),
        ('MINGEY2024', 4, 80),
        ('MINGEY2024', 5, 50),
        ('MINGEY2024', 6, 60);

INSERT INTO `SANPHAM` (`TEN`, `HINHANH`, `DANHMUC`, `NAMXB`, `MNXB`, `TENTG`, `MKVS`, `TIENX`, `TIENN`, `SL`, `ISBN`, `TT`) 
VALUES
        ('Ám thị tâm lý', 'kogoiob1cgjlqhndkc0dcw1hzj1kqook.png', 'Sách dành cho giới trẻ', 2022, 21,'Patrick King - Huy Nguyễn', 1, 134000, 100000, 5, 9786046863748, 1),
        ('Không gì là không thể', 'xtx0d0apioa66abawbnpybv4v8wsb54p.png', 'Sách dành cho giới trẻ', 2020, 3,'George Matthew Adams', 1, 63500, 60000, 5, 8935086837665, 1),
        ('Tư duy ngược', '050d67e2d58f5e291cbf25da53f55ef7.jpg', 'Sách dành cho giới trẻ', 2020, 10,'Nguyễn Anh Dũng', 1, 125000, 100000, 5, 9786043440287, 1),
        ('Bạn đắt giá bao nhiêu?', 'txq47334nnwj1cw5f5ux7egme8erj2k7.jpg', 'Sách dành cho giới trẻ', 2021,9 ,'Vãn Tình', 1, 107000, 100000, 5, 8936186543500, 1),
        ('Hãy gọi tên tôi', '90adcd1cc9b81aa80a272a653c7785e5.png', 'Sách dành cho giới trẻ', 2020,11 ,'Chanel Miller', 1, 118000, 100000, 5, 9786046863747, 1),
        ('Đời ngắn đừng ngủ dài', 'q9rcr9y1ax8gb1u372kxb0eivlg2xegc.jpeg', 'Sách dành cho giới trẻ', 2020,2 ,'Robin Sharma', 1, 64000, 60000, 5, 8934974158691, 1),
        ('Tội ác sau những bức tranh', 'rl7k9yn47k492vlxrbbnx9jmdy3ps89x.jpeg', 'Văn học - Nghệ thuật', 2022,6 ,'Jason Rekulak', 2, 146000, 100000, 5, 8934974158692, 1),
        ('Người rỗng', '93lh9xopdaqdtimbzosmo3n3h3evhmcv.jpeg', 'Văn học - Nghệ thuật', 2020,22 ,'John Dickson Carr', 2, 70000, 60000, 2, 9786049847257, 1),
        ('Tạp văn Nguyễn Ngọc Tư', '4b1ea768cbbef544c4ca16fe1967634b.jpg', 'Văn học - Nghệ thuật', 2021,2 ,'Nguyễn Ngọc Tư', 2, 72000, 60000, 5, 8934974168607, 1),
        ('Bông sen vàng', 'ythnuw9ks3kk4l0lb1pa6fdtoo1tz32a.jpeg', 'Văn học - Nghệ thuật', 2022,5 ,'Sơn Tùng', 2, 144000, 60000, 5, 8934974168617, 1),
        ('Hoa tuylip đen', 'sbgtbeq0vz1dhnag0qf3jrp6enxjgw20.jpeg', 'Văn học - Nghệ thuật', 2017,22 ,'Alexandre Dumas cha', 2, 49000, 30000, 5, 8936067597097, 1),
        ('Truyện ngụ ngôn E Dốp', '883868zq1vtv4qqm7plgby2g8k0i2mkv.jpeg', 'Văn học - Nghệ thuật', 2019,22 ,'Aesop', 2, 59000, 30000, 5, 9786049633198, 1),
        ('Nhật ký trong tù', 'cd19aa6163295ef2dff24012f78b9aec.jpg', 'Văn học - Nghệ thuật', 2021,5 ,'Hồ Chí Minh', 2, 105000, 50000, 5, 9786043238112, 1),
        ('Phận liễu', 'detep2imksxnh97om03avji0q1p47clp.jpeg', 'Văn học - Nghệ thuật', 2020,12 ,'Chu Thanh Hương', 2, 162000, 50000, 5, 9786047244300, 1),
        ('Đồng tiền hạnh phúc', '6619719473f6f132a18182f019364abf.jpg', 'Văn học - Nghệ thuật', 2020,13 ,'Ken Honda', 2, 85500, 50000, 5, 8935095629664, 1),
        ('Lũ trẻ thủy tinh', 'rnwjyi3x3zzxzjxlq4ba0zaeqpu0lugk.jpeg', 'Văn học thiếu nhi', 2021,1 ,'Kristina Ohlsson', 3, 28000, 10000, 5, 9786042190862, 1),
        ('Lũ trẻ đường ray', '22znw9gr4514dul0lc5rgisvc47qxfw6.jpeg', 'Văn học thiếu nhi', 2020,22 ,'Edith Nesbit', 3, 63000, 50000, 5, 9786049693465, 1),
        ('Tớ sợ cái đồng hồ', 'fs2xgxjq2yswm6l33gfv50mwg6mgu4qm.jpeg', 'Văn học thiếu nhi', 2018,14 ,'Nguyễn Quỳnh Mai', 3, 52000, 30000, 5, 9786045653012, 1),
        ('Khu rừng trong thành phố', 'cmpq0o6lb0jqmna7f9n2k7y61z84if0u.jpeg', 'Văn học thiếu nhi', 2018,14 ,'Nguyễn Quỳnh Mai', 3, 58000, 30000, 5, 9786045653005, 1),
        ('Đảo ngàn sao', '5hghrc3synmydygcyzwsl7noz3pt7u31.jpeg', 'Văn học thiếu nhi', 2021,1 ,'Emma Karinsdotter', 3, 48000, 30000, 5, 9786042221603, 1),
        ('Cậu bé bạc', 'ivssbyx4axf57c2tsww8kqyt1a4xauhv.jpeg', 'Văn học thiếu nhi', 2020,1 ,'Kristina Ohlsson', 3, 30000, 10000, 5, 9786042186315, 1),
        ('Ngựa ô yêu dấu', 'wf05ukmavgsdar1a772qc24c1iawtcfg.jpeg', 'Văn học thiếu nhi', 2022,23 ,'Anna Sewell', 3, 109000, 50000, 5, 9786043565027, 1),
        ('Chuyện con mèo dạy hải âu bay', 'iuj2x3gbldratw5xzjg9s1aterb66k8t.jpeg', 'Văn học thiếu nhi', 2019,4 ,'Luis Sepúlveda', 3, 42000, 10000, 5, 8935235222113, 1),
        ('Chú bé mang Pyjama sọc', 'o53krzx7g5du11thdcme6xl2uqd2s8gp.jpeg', 'Văn học thiếu nhi', 2018,4 ,'John Boyne', 3, 58000, 30000, 5, 8935235217898, 1),
        ('Những lá thư thời chiến Việt Nam (Tuyển tập)', '25ac83e2311e9fcaf146c655f672d6eb.jpg', 'Sách Chính trị - Xã hội', 2023,5 ,'Đặng Vương Hưng', 4, 144000, 100000, 5, 8935279148646, 1),
        ('Kỷ yếu Hoàng Sa', 'kry3vs4zab398dch1jc23zycidl0jvaq.jpeg', 'Sách Chính trị - Xã hội', 2014,15 ,'UBND Huyện Hoàng Sa', 4, 153000, 100000, 5, 9786048002930, 1),
        ('Dấu ấn Việt Nam trên Biển Đông', '1odzbcuzqspuou03uwdnstegf6pe4jp6.jpeg', 'Sách Chính trị - Xã hội', 2012,15 ,'TS. Trần Công Trục', 4, 191000, 100000, 5, 9786048018740, 1),
        ('Chân dung Ngô Tất Tố', 'ytja13yxd96huojklmyy1cadwq4rykoi.jpeg', 'Sách Chính trị - Xã hội', 2014,15 ,'Cao Đắc Điểm - Ngô Thị Thanh Lịch', 4, 38000, 20000, 5, 9786048005214, 1),
        ('Chính sách đối ngoại đổi mới của Việt Nam (1986 - 2015)', 'eplg2rc40dd1zfp0wzp4zo5s0aok0khp.jpeg', 'Sách Chính trị - Xã hội', 2018,16 ,'PGS. TS. Phạm Quang Minh', 4, 56000, 30000, 5, 9786047744749, 1),
        ('Đặc trưng và vai trò của tầng lớp trung lưu ở Việt Nam', 'v4e1gkm2jgz2b7tdktk504lsgemrff59.jpeg', 'Sách Chính trị - Xã hội', 2022,17 ,'TS. Lê Kim Sa', 4, 81000, 70000, 5, 9786043089585, 1),
        ('Sức mạnh mềm văn hóa Trung Quốc thời Tập Cận Bình và ứng xử của Việt Nam', 'qkphoffa38djvveox22613t5qx00ha8c.jpeg', 'Sách Chính trị - Xã hội', 2022,17 ,'Ths.Chử Thị Bích Thu - TS.Trần Thị Thủy (Đồng chủ biên)', 4, 99000, 50000, 5, 97860430839493, 1),
        ('Đường tới Điện Biên Phủ', 'dbd44e00c80b8c79694bc2a87a36c20f.jpg', 'Sách Chính trị - Xã hội', 2018,15 ,'Đại tướng Võ Nguyên Giáp', 4, 47000, 30000, 5, 9786048030759, 1),
        ('Đường tới Truông Bồn huyền thoại', 'wofawcrdyttsuu37j3mh06i2m6ii0lq0.jpeg', 'Sách Chính trị - Xã hội', 2019,18 ,'Văn Hiền', 4, 150000, 100000, 5, 9786049642937, 1),
        ('Vương Dương Minh toàn thư', '8b90cf59071f3ed109d17770a0ec50ed.jpg', 'Sách Chính trị - Xã hội', 2023,15 ,'Túc Dịch Minh - Nguyễn Thanh Hải', 4, 443000, 300000, 5, 9786048083021, 1),
        ('Thoát khỏi địa ngục Khmer đỏ - Hồi ký của một người còn sống', '7lmy4xhmjhgiqap0p6b2mt8ft12ju5pu.png', 'Sách Chính trị - Xã hội', 2019,5 ,'Denise Affonco', 4, 74000, 50000, 5, 9786045751718, 1),
        ('Điện Biên Phủ - Điểm hẹn lịch sử', '240bb8a0096e82c9769587fdb0ccfe2a.jpg', 'Sách Chính trị - Xã hội', 2018,15 ,'Đại tướng Võ Nguyên Giáp', 4, 53000, 30000, 5, 9786048030742, 1),
        ('Sử liệu cổ nhạc Việt Nam', 'kzaj4gc27vz9pguxepwe3uoz630caym2.jpeg', 'Sách Chính trị - Xã hội', 2020,19 ,'Đặng Hoành Loan', 4, 405000, 30000, 5, 9786047029396, 1),
        ('Sự sinh thành Việt Nam', '7p1zz1z2gdgxk3f7p62nrd0xdn24sn0x.jpeg', 'Sách Chính trị - Xã hội', 2018,16 ,'GS. Hà Văn Tấn', 4, 96000, 30000, 5, 9786047730087, 1),
        ('Người Dao Tiền ở Việt Nam', 'lc205cd61ud39xfvfom6lqxbeu8rklw5.jpeg', 'Sách Chính trị - Xã hội', 2021,17 ,'Lý Hành Sơn', 4, 157000, 100000, 5, 9786043086072, 1),
        ('Tôn tử binh pháp', 'wufet92dnkp0jt7yzehtazcyvrpnhll3.jpeg', 'Sách Chính trị - Xã hội', 2019,20 ,'Tôn Tử', 4, 64000, 30000, 5, 8935235222564, 1),
        ('5 đường mòn Hồ Chí Minh', '48a0hx2ovces506vnslpveb0qjcy6gi9.jpeg', 'Sách Chính trị - Xã hội', 2020,15 ,'Đặng Phong', 4, 161000, 100000, 5, 9786048049669, 1),
        ('Việt Nam bản hùng ca giữ nước', '363454f37c5e79344b2a87e4d0155e7e.png', 'Sách Chính trị - Xã hội', 2021,15 ,'Đặng Văn Việt', 4, 256000, 100000, 5, 9786048052508, 1),
        ('Bất khuất Mường Lò', 'w1xynsdkve2rv9k58gdkn8eixbvulr5y.jpeg', 'Sách Chính trị - Xã hội', 2023,19 ,'Trần Cao Đàm', 4, 108000, 50000, 5, 9786047035649, 1),
        ('Nếm trải Điện Biên', 'hgnpj4w7mbutt0tg9pjbsa8eu15q9k3a.jpeg', 'Sách Chính trị - Xã hội', 2018,15 ,'Cao Tiến Lê', 4, 33000, 10000, 5, 9786048032661, 1),
        ('Đường Bác Hồ Đi Cứu Nước', 'oqzeqlleza3c8550w5jjg54kvloow7oy.jpeg', 'Sách Chính trị - Xã hội', 2021,5 ,'GS.TS. Trình Quang Phú', 4, 148000, 100000, 5, 9786045767559, 1),
        ('Ký ức chiến trận - Quảng Trị 1972 - 2022 (Bìa cứng) - Nguyễn Xuân Vượng', 'ds7l546w53f0otq26c67em4mle8xoszq.jpeg', 'Sách Chính trị - Xã hội', 2022, 10,'Nguyễn Xuân Vượng', 4, 160000, 100000, 5, 9786043566628, 1);

INSERT INTO `KHUVUCSACH` (`TEN`, `GHICHU`, `TT`)
VALUES
        ('Khu vực A', 'Sách dành cho giới trẻ', 1),
        ('Khu vực B', 'Văn học - Nghệ thuật', 1),
        ('Khu vực C', 'Văn học thiếu nhi', 1),
        ('Khu vực D', 'Sách Chính trị - Xã hội', 1);

/*Tạo quan hệ*/

ALTER TABLE `CTQUYEN` ADD CONSTRAINT FK_MNQ_CTQUYEN FOREIGN KEY (MNQ) REFERENCES `NHOMQUYEN`(MNQ) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `CTQUYEN` ADD CONSTRAINT FK_MCN_CTQUYEN FOREIGN KEY (MCN) REFERENCES `DANHMUCCHUCNANG`(MCN) ON DELETE NO ACTION ON UPDATE NO ACTION;           

ALTER TABLE `TAIKHOAN` ADD CONSTRAINT FK_MNV_TAIKHOAN FOREIGN KEY (MNV) REFERENCES `NHANVIEN`(MNV) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `TAIKHOAN` ADD CONSTRAINT FK_MNQ_TAIKHOAN FOREIGN KEY (MNQ) REFERENCES `NHOMQUYEN`(MNQ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `PHIEUXUAT` ADD CONSTRAINT FK_MNV_PHIEUXUAT FOREIGN KEY (MNV) REFERENCES `NHANVIEN`(MNV) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `PHIEUXUAT` ADD CONSTRAINT FK_MKH_PHIEUXUAT FOREIGN KEY (MKH) REFERENCES `KHACHHANG`(MKH) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `CTPHIEUXUAT` ADD CONSTRAINT FK_MPX_CTPHIEUXUAT FOREIGN KEY (MPX) REFERENCES `PHIEUXUAT`(MPX) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `CTPHIEUXUAT` ADD CONSTRAINT FK_MSP_CTPHIEUXUAT FOREIGN KEY (MSP) REFERENCES `SANPHAM`(MSP) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `CTPHIEUXUAT` ADD CONSTRAINT FK_MKM_CTPHIEUXUAT FOREIGN KEY (MKM) REFERENCES `MAKHUYENMAI`(MKM) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `PHIEUNHAP` ADD CONSTRAINT FK_MNV_PHIEUNHAP FOREIGN KEY (MNV) REFERENCES `NHANVIEN`(MNV) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `PHIEUNHAP` ADD CONSTRAINT FK_MNCC_PHIEUNHAP FOREIGN KEY (MNCC) REFERENCES `NHACUNGCAP`(MNCC) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `CTPHIEUNHAP` ADD CONSTRAINT FK_MPN_CTPHIEUNHAP FOREIGN KEY (MPN) REFERENCES `PHIEUNHAP`(MPN) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `CTPHIEUNHAP` ADD CONSTRAINT FK_MSP_CTPHIEUNHAP FOREIGN KEY (MSP) REFERENCES `SANPHAM`(MSP) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `PHIEUKIEMKE` ADD CONSTRAINT FK_MNV_PHIEUKIEMKE FOREIGN KEY (MNV) REFERENCES `NHANVIEN`(MNV) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `CTPHIEUKIEMKE` ADD CONSTRAINT FK_MPKK_CTPHIEUKIEMKE FOREIGN KEY (MPKK) REFERENCES `PHIEUKIEMKE`(MPKK) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `CTPHIEUKIEMKE` ADD CONSTRAINT FK_MSP_CTPHIEUKIEMKE FOREIGN KEY (MSP) REFERENCES `SANPHAM`(MSP) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `MAKHUYENMAI` ADD CONSTRAINT FK_MNV_MAKHUYENMAI FOREIGN KEY (MNV) REFERENCES `NHANVIEN`(MNV) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `CTMAKHUYENMAI` ADD CONSTRAINT FK_MKM_CTMAKHUYENMAI FOREIGN KEY (MKM) REFERENCES `MAKHUYENMAI`(MKM) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `CTMAKHUYENMAI` ADD CONSTRAINT FK_MSP_CTMAKHUYENMAI FOREIGN KEY (MSP) REFERENCES `SANPHAM`(MSP) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `SANPHAM` ADD CONSTRAINT FK_MNXB_SANPHAM FOREIGN KEY (MNXB) REFERENCES `NHAXUATBAN`(MNXB) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `SANPHAM` ADD CONSTRAINT FK_MKVS_SANPHAM FOREIGN KEY (MKVS) REFERENCES `KHUVUCSACH`(MKVS) ON DELETE NO ACTION ON UPDATE NO ACTION;

COMMIT;