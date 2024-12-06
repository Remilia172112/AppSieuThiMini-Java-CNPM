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
    `MCV` INT(11) NOT NULL COMMENT 'Mã chức vụ',
    `TT` INT(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái',
    PRIMARY KEY(MNV)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `CHUCVU` (
    `MCV` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã chức vụ',
    `TEN` VARCHAR(255) NOT NULL COMMENT 'Họ chức vụ',
    `MUCLUONG` INT(11) NOT NULL COMMENT 'Mức lương',
    `TT` INT(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái',
    PRIMARY KEY(MCV)
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
    `DIEMTICHLUY` INT(11) DEFAULT 0 COMMENT 'Điểm tích lũy',
    `EMAIL` VARCHAR(50) UNIQUE COMMENT 'Email',
    `TT` INT(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái',
    PRIMARY KEY(MKH)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `HOADON` (
    `MHD` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã phiếu xuất',
    `MNV` INT(11) DEFAULT 1 COMMENT 'Mã nhân viên',
    `MKH` INT(11) NOT NULL COMMENT 'Mã khách hàng',
    `TIEN` INT(11) NOT NULL COMMENT 'Tổng tiền',
    `TG` DATETIME DEFAULT current_timestamp() COMMENT 'Thời gian tạo',
    `DIEMTICHLUY` INT(11) DEFAULT 0 COMMENT 'Điểm tích lũy giảm giá',
    `TT` INT(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái',
    PRIMARY KEY(MHD)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `CTHOADON` (
    `MHD` INT(11) NOT NULL COMMENT 'Mã phiếu xuất',
    `MSP` INT(11) NOT NULL COMMENT 'Mã sản phẩm',
    `MKM` VARCHAR(255) COMMENT 'Mã khuyến mãi',
    `SL` INT(11) NOT NULL COMMENT 'Số lượng',
    `TIENXUAT` INT(11) NOT NULL COMMENT 'Tiền xuất',
    PRIMARY KEY(MHD, MSP)
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
    `ML` INT(11) NOT NULL COMMENT 'Mã loại',
    `TIENX` INT(11) NOT NULL COMMENT 'Tiền xuất',
    `SL` INT(11) DEFAULT 0 COMMENT 'Số lượng',
    `MDV` INT(11) NOT NULL COMMENT 'Mã đơn vị',
    `MV` VARCHAR(255) UNIQUE NOT NULL COMMENT 'Mã vạch',
    `TT` INT(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái',
    PRIMARY KEY(MSP)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `DONVI` (
  `MDV` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã đơn vị',
  `TENDV` VARCHAR(255) NOT NULL COMMENT 'Tên đơn vị',
  `TT` INT(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái',
  PRIMARY KEY(MDV)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `LOAI` (
  `ML` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'Mã loại',
  `TENL` VARCHAR(255) NOT NULL COMMENT 'Tên loại',
  `TT` INT(11) NOT NULL DEFAULT 1 COMMENT 'Trạng thái',
  PRIMARY KEY(ML)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Thêm dữ liệu*/

INSERT INTO `DANHMUCCHUCNANG`(`MCN`, `TEN`, `TT`)
VALUES 
        ('sanpham', 'Quản lý sản phẩm', 1),
        ('thuoctinh', 'Quản lý thuộc tính', 1),
        ('khachhang', 'Quản lý khách hàng', 1),
        ('nhacungcap', 'Quản lý nhà cung cấp', 1),
        ('nhanvien', 'Quản lý nhân viên', 1),
        ('chucvu', 'Quản lý chức vụ', 1),
        ('nhaphang', 'Quản lý nhập hàng', 1),
        ('hoadon', 'Quản lý hóa đơn', 1),
        ('banhang', 'Bán hàng', 1),
        ('kiemke', 'Quản lý kiểm kê', 1),
        ('nhomquyen', 'Quản lý nhóm quyền', 1),
        ('taikhoan', 'Quản lý tài khoản', 1),
        ('makhuyenmai', 'Quản lý mã khuyến mãi', 1),
        ('thongke', 'Quản lý thống kê', 1);

INSERT INTO `CTQUYEN` (`MNQ`, `MCN`, `HANHDONG`)
VALUES
		(1, 'sanpham', 'create'),
        (1, 'sanpham', 'delete'),
        (1, 'sanpham', 'update'),
        (1, 'sanpham', 'view'),
        (1, 'thuoctinh', 'create'),
        (1, 'thuoctinh', 'delete'),
        (1, 'thuoctinh', 'update'),
        (1, 'thuoctinh', 'view'),
        (1, 'khachhang', 'create'),
        (1, 'khachhang', 'delete'),
        (1, 'khachhang', 'update'),
        (1, 'khachhang', 'view'),
        (1, 'nhacungcap', 'create'),
        (1, 'nhacungcap', 'delete'),
        (1, 'nhacungcap', 'update'),
        (1, 'nhacungcap', 'view'),
        (1, 'nhanvien', 'create'),
        (1, 'nhanvien', 'delete'),
        (1, 'nhanvien', 'update'),
        (1, 'nhanvien', 'view'),
        (1, 'chucvu', 'create'),
        (1, 'chucvu', 'delete'),
        (1, 'chucvu', 'update'),
        (1, 'chucvu', 'view'),
        (1, 'nhaphang', 'create'),
        (1, 'nhaphang', 'delete'),
        (1, 'nhaphang', 'update'),
        (1, 'nhaphang', 'view'),
        (1, 'hoadon', 'create'),
        (1, 'hoadon', 'delete'),
        (1, 'hoadon', 'update'),
        (1, 'hoadon', 'view'),
        (1, 'banhang', 'create'),
        (1, 'banhang', 'view'),
--         (1, 'kiemke', 'create'),
--         (1, 'kiemke', 'delete'),
--         (1, 'kiemke', 'update'),
--         (1, 'kiemke', 'view'),
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
        (2, 'hoadon', 'view'),
        (2, 'banhang', 'create'),
        (2, 'banhang', 'view'),
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
        (3, 'nhacungcap', 'view');

INSERT INTO `NHOMQUYEN` (`TEN`, `TT`)
VALUES
        ('Quản lý cửa hàng', 1),
        ('Nhân viên bán hàng', 1),
        ('Nhân viên quản lý kho', 1);

INSERT INTO `NHANVIEN` (`HOTEN`, `GIOITINH`, `NGAYSINH`, `SDT`, `EMAIL`, `MCV`, `TT`)
VALUES
        ('Lê Thế Minh', 0, '2077-01-01', '0505555505', 'remchan.com@gmail.com', 1, 1),
        ('Huỳnh Khôi Nguyên', 1, '2023-05-06', '0123456789', 'nguyeney111@gmail.com', 2, 1),
        ('Trần Gia Nguyễn', 1, '2004-07-17', '0387913347', 'trangianguyen.com@gmail.com', 3, 1),
        ('Hoàng Gia Bảo', 1, '2003-04-11', '0355374322', 'musicanime2501@gmail.com', 2, 1),
        ('Đỗ Nam Công Chính', 1, '2003-04-11', '0123456789', 'chinchin@gmail.com', 3, 1),
        ('Đinh Ngọc Ân', 1, '2003-04-03', '0123456789', 'ngocan@gmail.com', 3, 1);

INSERT INTO `CHUCVU` (`TEN`, `MUCLUONG`, `TT`)
VALUES
        ('Quản lý cửa hàng', 5000000, 1),
        ('Nhân viên bán hàng', 2000000, 1),
        ('Nhân viên quản lý kho', 2000000, 1);

INSERT INTO `TAIKHOAN` (`MNV`, `TDN`, `MK`, `MNQ`, `TT`, `OTP`)
VALUES
        (1, 'admin', '$2a$12$6GSkiQ05XjTRvCW9MB6MNuf7hOJEbbeQx11Eb8oELil1OrCq6uBXm', 1, 1, 'null'),
        (2, 'NV2', '$2a$12$6GSkiQ05XjTRvCW9MB6MNuf7hOJEbbeQx11Eb8oELil1OrCq6uBXm', 2, 1, 'null'),
        (3, 'NV3', '$2a$12$6GSkiQ05XjTRvCW9MB6MNuf7hOJEbbeQx11Eb8oELil1OrCq6uBXm', 3, 1, 'null');

INSERT INTO `KHACHHANG` (`HOTEN`, `DIACHI`, `SDT`, `TT`, `NGAYTHAMGIA`, `DIEMTICHLUY`)
VALUES
        ('Mặc định', '', '', 1, '2024-04-15 09:52:29',0),
        ('Nguyễn Văn A', 'Gia Đức, Ân Đức, Hoài Ân, Bình Định', '0387913347', 1, '2024-04-15 09:52:29',300),
        ('Trần Nhất Nhất', '205 Trần Hưng Đạo, Phường 10, Quận 5, Thành phố Hồ Chí Minh', '0123456789', 1, '2024-04-15 09:52:29',300),
        ('Hoàng Gia Bo', 'Khoa Trường, Hoài Ân, Bình Định', '0987654321', 1, '2024-04-15 09:52:29',0),
        ('Hồ Minh Hưng', 'Khoa Trường, Hoài Ân, Bình Định', '0867987456', 1, '2024-04-15 09:52:29',0),
        ('Nguyễn Thị Minh Anh', '123 Phố Huế, Quận Hai Bà Trưng, Hà Nội', '0935123456', 1, '2024-04-16 17:59:57',0),
        ('Trần Đức Minh', '789 Đường Lê Hồng Phong, Thành phố Đà Nẵng', '0983456789', 1, '2024-04-16 18:08:12',0),
        ('Lê Hải Yến', '456 Tôn Thất Thuyết, Quận 4, Thành phố Hồ Chí Minh', '0977234567', 1, '2024-04-16 18:08:47',0),
        ('Phạm Thanh Hằng', '102 Lê Duẩn, Thành phố Hải Phòng', '0965876543', 1, '2024-04-16 18:12:59',0),
        ('Hoàng Đức Anh', '321 Lý Thường Kiệt, Thành phố Cần Thơ', '0946789012', 1, '2024-04-16 18:13:47',0),
        ('Ngô Thanh Tùng', '987 Trần Hưng Đạo, Quận 1, Thành phố Hồ Chí Minh', '0912345678', 1, '2024-04-16 18:14:12',0),
        ('Võ Thị Kim Ngân', '555 Nguyễn Văn Linh, Quận Nam Từ Liêm, Hà Nội', '0916789123', 1, '2024-04-16 18:15:11',0),
        ('Đỗ Văn Tú', '777 Hùng Vương, Thành phố Huế', '0982345678', 1, '2024-04-30 18:15:56',0),
        ('Lý Thanh Trúc', '888 Nguyễn Thái Học, Quận Ba Đình, Hà Nội', '0982123456', 1, '2024-04-16 18:16:22',0),
        ('Bùi Văn Hoàng', '222 Đường 2/4, Thành phố Nha Trang', '0933789012', 1, '2024-04-16 18:16:53',0),
        ('Lê Văn Thành', '23 Đường 3 Tháng 2, Quận 10, TP. Hồ Chí Minh', '0933456789', 1, '2024-04-16 18:17:46',0),
        ('Nguyễn Thị Lan Anh', '456 Lê Lợi, Quận 1, TP. Hà Nội', '0965123456', 1, '2024-04-16 18:18:10',0),
        ('Phạm Thị Mai', '234 Lê Hồng Phong, Quận 5, TP. Hồ Chí Minh', '0946789013', 1, '2024-04-17 18:18:34',0),
        ('Hoàng Văn Nam', ' 567 Phố Huế, Quận Hai Bà Trưng, Hà Nội', '0912345679', 1, '2024-04-17 18:19:16',0);


INSERT INTO `HOADON` (`MNV`, `MKH`, `TIEN`, `TG`, `TT`)
VALUES
        (1, 2, 300000, '2024-12-05 17:34:12', 1),
        (1, 3, 300000, '2024-12-06 17:34:12', 1);

INSERT INTO `CTHOADON` (`MHD`, `MSP`, `SL`,  `TIENXUAT`)
VALUES
        (1, 1, 10, 15000),
        (1, 2, 10, 15000),
        (2, 19, 10, 15000),
        (2, 20, 10, 15000);

INSERT INTO `NHACUNGCAP` (`TEN`, `DIACHI`, `SDT`, `EMAIL`, `TT`)
VALUES
        ('Công Ty TNHH Nestlé Việt Nam', 'Lầu 5, Empress Tower, 138-142 Hai Bà Trưng, Phường Đa Kao, Quận 1, Tp.HCM', '02839113737', 'consumer.services@vn.nestle.com', 1),
		('Công Ty TNHH MTV TM Phúc Nam Sang', '69/10 ĐƯỜNG SỐ 18, P. BÌNH HƯNG HÒA, QUẬN BÌNH TÂN, Tp.HCM', '0908409053', 'PhucNamSang@gmail.com', 1),
		('Công ty CP Bibica', '443 Lý Thường Kiệt, P. 8, Q. Tân Bình, TP. Hồ Chí Minh', '02839717920', 'bibica.vn@gmail.com', 1),
		('Công ty CP bánh kẹo Hải Châu', '15 Mạc Thị Bưởi, phường Vĩnh Tuy, quận Hai Bà Trưng, TP. Hà Nội', '02438621520', 'pkdtthaichau@gmail.com', 1),
		('Công ty CP Tràng An ', 'Số 27 Trần Quốc Hoàn, phường Dịch Vọng Hậu, quận Cầu Giấy, Hà Nội', '02462821922', 'sale@trangantm.com', 1),
        ('Công ty TNHH Hải Hà - Kotobuki', 'Số 25 Trương Định, phường Trương Định, quận Hai Bà Trưng, Hà Nội', '0911638166', ' ptt@haiha-kotobuki.com.vn', 1),
        ('NPP Bánh kẹo Nguyễn Phước', '71/12/36 Nguyễn Bặc , phường 03 , quận Tân Bình, Tp.HCM', '02838449925', 'nguyenphuoc2512@gmail.com', 1);

INSERT INTO `PHIEUNHAP` (`MNV`, `MNCC`, `TIEN`, `TG`, `TT`)
VALUES
        (1, 1, 2000000, '2024-12-01 01:09:27', 1),
        (1, 2, 2000000, '2024-12-10 01:09:27', 1),
        (1, 3, 2000000, '2024-11-20 01:09:27', 1),
        (1, 4, 800000, '2024-11-30 01:09:27', 1);

INSERT INTO `CTPHIEUNHAP` (`MPN`, `MSP`, `SL`, `TIENNHAP`, `HINHTHUC`)
VALUES
        (1, 1, 20, 2000, 0),
        (1, 2, 20, 2000, 0),
        (1, 3, 10, 2000, 0),
        (1, 4, 10, 2000, 0),
        (1, 5, 10, 2000, 0),
        (1, 6, 10, 2000, 0),
        (1, 7, 10, 2000, 0),
        (1, 8, 10, 2000, 0),
        (1, 9, 10, 2000, 0),
        (2, 10, 10, 2000, 0),
        (2, 11, 10, 2000, 0),
        (2, 12, 10, 2000, 0),
        (2, 13, 10, 2000, 0),
        (2, 14, 10, 2000, 0),
        (2, 15, 10, 2000, 0),
        (2, 16, 10, 2000, 0),
        (2, 17, 10, 2000, 0),
        (2, 18, 10, 2000, 0),
        (2, 19, 20, 2000, 0),
        (2, 20, 20, 2000, 0),
        (3, 21, 10, 2000, 0),
        (3, 22, 10, 2000, 0),
        (3, 23, 10, 2000, 0),
        (3, 24, 10, 2000, 0),
        (3, 25, 10, 2000, 0),
        (3, 26, 10, 2000, 0),
        (3, 27, 10, 2000, 0),
        (3, 28, 10, 2000, 0),
        (3, 29, 10, 2000, 0),
        (3, 30, 10, 2000, 0),
        (4, 31, 10, 2000, 0),
        (4, 32, 10, 2000, 0),
        (4, 33, 10, 2000, 0),
        (4, 34, 10, 2000, 0);

INSERT INTO `PHIEUKIEMKE` (`MNV` , `TG` , `TT`) 
VALUES
        (1 , '2024-04-01 01:09:27' , 1);

INSERT INTO `CTPHIEUKIEMKE` (`MPKK`,`MSP` ,`TRANGTHAISP`, `GHICHU`)
VALUES 
        (1, 1, 1 ,'Hư' );

INSERT INTO `MAKHUYENMAI` (`MKM`,`TGBD`,`TGKT`,`TT`)
VALUES
        ('GT2024', '2024-04-01 00:00:00', '2024-06-01 00:00:00', 1),
        ('MINGEY2024', '2024-12-01 00:00:00', '2024-12-20 00:00:00', 1);

INSERT INTO `CTMAKHUYENMAI` (`MKM`, `MSP`,`PTG`)
VALUES
        ('GT2024', 1, 20),
        ('GT2024', 2, 20),
        ('GT2024', 3, 20),
        ('MINGEY2024', 4, 80),
        ('MINGEY2024', 5, 50),
        ('MINGEY2024', 6, 60);

INSERT INTO `SANPHAM` (`TEN`, `HINHANH`, `ML`, `TIENX`, `SL`, `MDV`, `MV`, `TT`)
VALUES
        ('Sá xị Chương Dương Sleek lon 330ml', 'sa-xi-chuong-duong-sleek-330ml-202107201356483509.jpg', 1, 9000, 10, 1, '8935015402544', 1),
        ('Nước ngọt Mirinda hương xá xị lon 320ml', '86628-1_202411131617358080.jpg', 1, 10500, 10, 1, '8934588132346', 1),
        ('Nước ngọt Sprite hương chanh chai 390ml', 'nuoc-ngot-sprite-huong-chanh-chai-390ml-202308231643521297.jpg', 1, 8000, 10, 2, '8935049510604', 1),
        ('Nước ngọt Sprite hương chanh lon 320ml', 'nuoc-ngot-sprite-huong-chanh-lon-320ml-202306200909144871.jpg', 1, 9000, 10, 1, '8935049501718', 1),
        ('Nước bù khoáng Revive không calo 500ml', 'thung-24-chai-nuoc-bu-khoang-revive-khong-calo-500ml-202311041004249172.jpg', 1, 11000, 10, 2, '8934588743054', 1),
        ('Nước tăng lực Warrior hương nho 330ml', '24-chai-nuoc-tang-luc-warrior-huong-nho-330ml-202408130927321524.jpg', 1, 10000, 10, 2, '8850228005446', 1),
        ('Nước tăng lực Sting Gold 330ml', '6-chai-nuoc-tang-luc-sting-gold-330ml-201912021710406909.jpg', 1, 10000, 10, 2, '8934588175073', 1),
        ('Nước tăng lực Redbull Thái kẽm và vitamin 250ml', 'nuoc-tang-luc-redbull-thai-kem-va-vitamin-250ml-202403091724043647.jpg', 1, 13000, 10, 1, '8850228007617', 1),
        ('Kẹo cà phê Cappuccino KOPIKO gói 140g', 'keo-ca-phe-coffeeshot-cappuccino-kopiko-goi-140g-202209121006188729.jpg', 2, 15000, 10, 3, '8996001320136', 1),
        ('Kẹo họng Orion vị quất mật ong 84g', 'keo-hong-orion-vi-quat-mat-ong-875g-202403311806297942.jpg', 2, 17000, 10, 3, '8936036025453', 1),
        ('Kẹo nhai hương bạc hà Mentos gói 89.1g', 'keo-nhai-huong-bac-ha-mentos-goi-1134g-202407101433285480.jpg', 2, 17000, 10, 3, '8935001713159', 1),
        ('Kẹo nhai socola hương bạc hà Dynamite Chews gói 125g', 'keo-nhai-socola-huong-bac-ha-dynamite-chew-goi-125g-201904081034006020.jpg', 2, 16500, 10, 3, '8934564130014', 1),
        ('Kẹo nho đen Hải Hà Chew gói 90g', 'keo-nho-den-hai-ha-chew-goi-100g-202304161131467354.jpg', 2, 9500, 10, 3, '8934595060472', 1),
        ('Bánh quy sữa Cosy Marie gói 408g', 'banh-quy-sua-cosy-marie-goi-408g-202303161622217136.jpg', 2, 49000, 10, 4, '8934680113878', 1),
        ('Bánh quy vị cà phê Coffee Joy gói 142g', 'banh-quy-vi-ca-phe-coffee-joy-goi-142g-201904110947353101.jpg', 2, 20000, 10, 4, '8996001301661', 1),
        ('Bánh cracker phô mai Gery hộp 180g', 'banh-cracker-pho-mai-gery-hop-200g-202307190930396248.jpg', 2, 39000, 10, 4, '8992775347256', 1),
        ('Bánh cá vị gà BBQ Orion Marine Boy hộp 35g', 'banh-ca-vi-ga-bbq-orion-marine-boy-hop-35g-201912152002260742.jpg', 2, 15000, 10, 4, '8936036025040', 1),
        ('Bánh quy kem vani Cream-O gói 85g', 'banh-quy-kem-vani-cream-o-goi-85g-202306010919157946.jpg', 2, 10000, 10, 3, '8934564300233', 1),
        ('Bánh gạo nướng vị tự nhiên Orion An 151.2g', 'banh-gao-nuong-vi-tu-nhien-orion-an-goi-1512g-202007070830065305.jpg', 2, 24000, 10, 3, '8936036026177', 1),
        ('Bánh gạo nướng vị tảo biển Orion An 111.3g', 'banh-gao-nuong-vi-tao-bien-orion-an-goi-1113g-201909201540118351.jpg', 2, 23000, 10, 3, '8936036026191', 1),
        ('Bánh quế vị kem socola Cosy 117.6g', 'banh-que-vi-kem-so-co-la-cosy-goi-126g-202309252126253207.jpg', 2, 14000, 10, 3, '8934680033114', 1),
        ('Snack khoai tây vị bò Wagyu Lays Max 42g', 'snack-khoai-tay-vi-bo-wagyu-lays-goi-42g-202309070947114976.jpg', 2, 13000, 10, 3, '8936079123825', 1),
        ('Snack tôm vị cay nồng Oishi 70g', 'snack-tom-vi-cay-nong-oishi-goi-70g-202305061641260781.jpg', 2, 12000, 10, 3, '8936079123826', 1),
        ('Snack nhân socola vị hazelnut Oishi Pillows 85g', 'snack-nhan-so-co-la-vi-hazelnut-oishi-pillows-goi-85g-202305061642558838.jpg', 2, 12000, 10, 3, '8934803071108', 1),
        ('Sữa tươi có đường TH true MILK 180ml', 'thung-sua-tuoi-tiet-trung-th-true-milk-co-duong-180ml-48-hop-201811270001534369.jpg', 3, 10000, 10, 4, '8935217400157', 1),
        ('Sữa lúa mạch vị socola Ovaltine 180ml', 'thung-48-hop-sua-lua-mach-vi-socola-ovaltine-bo-sung-canxi-180ml-202305061020014739.jpg', 3, 10500, 10, 4, '8936199520109', 1),
        ('Sữa chua lên men hương cam YoMost 170ml', 'loc-8-hop-sua-chua-len-men-tu-nhien-huong-cam-yomost-170ml-202309061009340150.jpg', 3, 7000, 10, 4, '8934841903331', 1),
        ('Sữa chua uống hương cam SuSu 110ml', 'loc-4-hop-sua-chua-uong-cam-susu-110ml-202308082017076899.jpg', 3, 6000, 10, 4, '8934841903332', 1),
        ('Mì Hảo Hảo tôm chua cay 75g', 'slide-1_202410151356065341.jpg', 4, 4500, 20, 3, '8934563138165', 1),
        ('Mì Hảo Hảo gà vàng gói 74g', 'slide_202410151521446289.jpg', 4, 4500, 20, 3, '8934563184148', 1),
        ('Mì Hảo Hảo sườn heo tỏi phi 73g', 'slide_202410151509351025.jpg', 4, 4500, 20, 3, '8934563182144', 1),
        ('Mì 3 Miền gà sợi phở gói 65g', 'thung-30-goi-mi-3-mien-ga-soi-pho-goi-65g-202406131345060143.jpg', 4, 4000, 20, 3, '8936048473921', 1),
        ('Mì 3 Miền tôm hùm 65g', 'mi-3-mien-vi-tom-hum-goi-65g-2-700x467.jpg', 4, 4000, 20, 3, '8936048470029', 1),
        ('Mì 3 Miền Gold tôm chua cay đặc biệt 75g', 'mi-3-mien-gold-tom-chua-cay-dac-biet-goi-75g-202309180855245245.jpg', 4, 4000, 20, 3, '8936048471248', 1);

INSERT INTO `DONVI` (`TENDV`)
VALUES
        ('Lon'),
        ('Chai'),
        ('Gói'),
        ('Hộp');
        
INSERT INTO `LOAI` (`TENL`)
VALUES
        ('Nước giải khát'),
        ('Bánh kẹo'),
        ('Sữa'),
        ('Mì');
        
/*Tạo quan hệ*/

ALTER TABLE `CTQUYEN` ADD CONSTRAINT FK_MNQ_CTQUYEN FOREIGN KEY (MNQ) REFERENCES `NHOMQUYEN`(MNQ) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `CTQUYEN` ADD CONSTRAINT FK_MCN_CTQUYEN FOREIGN KEY (MCN) REFERENCES `DANHMUCCHUCNANG`(MCN) ON DELETE NO ACTION ON UPDATE NO ACTION;           

ALTER TABLE `TAIKHOAN` ADD CONSTRAINT FK_MNV_TAIKHOAN FOREIGN KEY (MNV) REFERENCES `NHANVIEN`(MNV) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `TAIKHOAN` ADD CONSTRAINT FK_MNQ_TAIKHOAN FOREIGN KEY (MNQ) REFERENCES `NHOMQUYEN`(MNQ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `HOADON` ADD CONSTRAINT FK_MNV_HOADON FOREIGN KEY (MNV) REFERENCES `NHANVIEN`(MNV) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `HOADON` ADD CONSTRAINT FK_MKH_HOADON FOREIGN KEY (MKH) REFERENCES `KHACHHANG`(MKH) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `CTHOADON` ADD CONSTRAINT FK_MHD_CTHOADON FOREIGN KEY (MHD) REFERENCES `HOADON`(MHD) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `CTHOADON` ADD CONSTRAINT FK_MSP_CTHOADON FOREIGN KEY (MSP) REFERENCES `SANPHAM`(MSP) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `CTHOADON` ADD CONSTRAINT FK_MKM_CTHOADON FOREIGN KEY (MKM) REFERENCES `MAKHUYENMAI`(MKM) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `SANPHAM` ADD CONSTRAINT FK_ML_SANPHAM FOREIGN KEY (ML) REFERENCES `LOAI`(ML) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `SANPHAM` ADD CONSTRAINT FK_MDV_SANPHAM FOREIGN KEY (MDV) REFERENCES `DONVI`(MDV) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `PHIEUNHAP` ADD CONSTRAINT FK_MNV_PHIEUNHAP FOREIGN KEY (MNV) REFERENCES `NHANVIEN`(MNV) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `PHIEUNHAP` ADD CONSTRAINT FK_MNCC_PHIEUNHAP FOREIGN KEY (MNCC) REFERENCES `NHACUNGCAP`(MNCC) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `CTPHIEUNHAP` ADD CONSTRAINT FK_MPN_CTPHIEUNHAP FOREIGN KEY (MPN) REFERENCES `PHIEUNHAP`(MPN) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `CTPHIEUNHAP` ADD CONSTRAINT FK_MSP_CTPHIEUNHAP FOREIGN KEY (MSP) REFERENCES `SANPHAM`(MSP) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `PHIEUKIEMKE` ADD CONSTRAINT FK_MNV_PHIEUKIEMKE FOREIGN KEY (MNV) REFERENCES `NHANVIEN`(MNV) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `CTPHIEUKIEMKE` ADD CONSTRAINT FK_MPKK_CTPHIEUKIEMKE FOREIGN KEY (MPKK) REFERENCES `PHIEUKIEMKE`(MPKK) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `CTPHIEUKIEMKE` ADD CONSTRAINT FK_MSP_CTPHIEUKIEMKE FOREIGN KEY (MSP) REFERENCES `SANPHAM`(MSP) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `CTMAKHUYENMAI` ADD CONSTRAINT FK_MKM_CTMAKHUYENMAI FOREIGN KEY (MKM) REFERENCES `MAKHUYENMAI`(MKM) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE `CTMAKHUYENMAI` ADD CONSTRAINT FK_MSP_CTMAKHUYENMAI FOREIGN KEY (MSP) REFERENCES `SANPHAM`(MSP) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `NHANVIEN` ADD CONSTRAINT FK_MCV_NHANVIEN FOREIGN KEY (MCV) REFERENCES `CHUCVU`(MCV) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;