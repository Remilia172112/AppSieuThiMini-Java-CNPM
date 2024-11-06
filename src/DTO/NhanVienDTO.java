package DTO;

import java.util.Date;
import java.util.Objects;

public class NhanVienDTO {

    private int MNV;
    private String HOTEN;
    private int GIOITINH;
    private String SDT;
    private Date NGAYSINH;
    private int TT;
    private String EMAIL;

    public NhanVienDTO() {
    }

    public NhanVienDTO(int MNV, String HOTEN, int GIOITINH, Date NGAYSINH, String SDT, int TT, String EMAIL) {
        this.MNV = MNV;
        this.HOTEN = HOTEN;
        this.GIOITINH = GIOITINH;
        this.NGAYSINH = NGAYSINH;
        this.SDT = SDT;
        this.TT = TT;
        this.EMAIL = EMAIL;
    }

    public NhanVienDTO(String HOTEN, int GIOITINH, Date NGAYSINH, String SDT, int TT) {
        this.HOTEN = HOTEN;
        this.GIOITINH = GIOITINH;
        this.NGAYSINH = NGAYSINH;
        this.SDT = SDT;
        this.TT = TT;
    }

    public int getMNV() {
        return MNV;
    }

    public void setMNV(int MNV) {
        this.MNV = MNV;
    }

    public String getHOTEN() {
        return HOTEN;
    }

    public void setHOTEN(String HOTEN) {
        this.HOTEN = HOTEN;
    }

    public int getGIOITINH() {
        return GIOITINH;
    }

    public void setGIOITINH(int GIOITINH) {
        this.GIOITINH = GIOITINH;
    }

    public Date getNGAYSINH() {
        return NGAYSINH;
    }

    public void setNGAYSINH(Date NGAYSINH) {
        this.NGAYSINH = NGAYSINH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public int getTT() {
        return TT;
    }

    public void setTT(int TT) {
        this.TT = TT;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.MNV;
        hash = 17 * hash + Objects.hashCode(this.HOTEN);
        hash = 17 * hash + Objects.hashCode(this.GIOITINH);
        hash = 17 * hash + Objects.hashCode(this.NGAYSINH);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NhanVienDTO other = (NhanVienDTO) obj;
        if (this.MNV != other.MNV) {
            return false;
        }
        if (this.HOTEN != other.HOTEN) {
            return false;
        }
        if (this.GIOITINH != other.GIOITINH) {
            return false;
        }
        if (this.NGAYSINH != other.NGAYSINH) {
            return false;
        }
        if (this.SDT != other.SDT) {
            return false;
        }
        if (this.EMAIL != other.EMAIL) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "NhanVien{" + "Ma nhan vien = " + MNV + ", Ho ten = " + HOTEN + ", Gioi tinh = " + GIOITINH + ", Ngay sinh = " + NGAYSINH + '}';
    }

    public int getColumnCount() {
        return getClass().getDeclaredFields().length;
    }

}
