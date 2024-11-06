package DTO;

import java.util.Date;

public class KhachHangDTO {

    private int MKH;
    private String HOTEN;
    private String SDT;
    private String DIACHI;
    private String EMAIL;
    private Date NGAYTHAMGIA;

    public KhachHangDTO() {
    }

    public KhachHangDTO(int MKH, String HOTEN, String SDT, String DIACHI, String EMAIL) {
        this.MKH = MKH;
        this.HOTEN = HOTEN;
        this.SDT = SDT;
        this.DIACHI = DIACHI;
        this.EMAIL = EMAIL;
    }
    
    public KhachHangDTO(int MKH, String HOTEN, String SDT, String DIACHI, String EMAIL,Date NGAYTHAMGIA) {
        this.MKH = MKH;
        this.HOTEN = HOTEN;
        this.SDT = SDT;
        this.DIACHI = DIACHI;
        this.EMAIL = EMAIL;
        this.NGAYTHAMGIA = NGAYTHAMGIA;
    }

    public Date getNgaythamgia() {
        return NGAYTHAMGIA;
    }

    public void setNgaythamgia(Date NGAYTHAMGIA) {
        this.NGAYTHAMGIA = NGAYTHAMGIA;
    }

    public int getMaKH() {
        return MKH;
    }

    public void setMaKH(int MKH) {
        this.MKH = MKH;
    }

    public String getHoten() {
        return HOTEN;
    }

    public void setHoten(String HOTEN) {
        this.HOTEN = HOTEN;
    }

    public String getSdt() {
        return SDT;
    }

    public void setSdt(String SDT) {
        this.SDT = SDT;
    }

    public String getDiachi() {
        return DIACHI;
    }

    public void setDiachi(String DIACHI) {
        this.DIACHI = DIACHI;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String eMAIL) {
        EMAIL = eMAIL;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + MKH;
        result = prime * result + ((HOTEN == null) ? 0 : HOTEN.hashCode());
        result = prime * result + ((SDT == null) ? 0 : SDT.hashCode());
        result = prime * result + ((DIACHI == null) ? 0 : DIACHI.hashCode());
        result = prime * result + ((EMAIL == null) ? 0 : EMAIL.hashCode());
        result = prime * result + ((NGAYTHAMGIA == null) ? 0 : NGAYTHAMGIA.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        KhachHangDTO other = (KhachHangDTO) obj;
        if (MKH != other.MKH)
            return false;
        if (HOTEN == null) {
            if (other.HOTEN != null)
                return false;
        } else if (!HOTEN.equals(other.HOTEN))
            return false;
        if (SDT == null) {
            if (other.SDT != null)
                return false;
        } else if (!SDT.equals(other.SDT))
            return false;
        if (DIACHI == null) {
            if (other.DIACHI != null)
                return false;
        } else if (!DIACHI.equals(other.DIACHI))
            return false;
        if (EMAIL == null) {
            if (other.EMAIL != null)
                return false;
        } else if (!EMAIL.equals(other.EMAIL))
            return false;
        if (NGAYTHAMGIA == null) {
            if (other.NGAYTHAMGIA != null)
                return false;
        } else if (!NGAYTHAMGIA.equals(other.NGAYTHAMGIA))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "KhachHangDTO [MKH=" + MKH + ", HOTEN=" + HOTEN + ", SDT=" + SDT + ", DIACHI=" + DIACHI + ", EMAIL="
                + EMAIL + ", NGAYTHAMGIA=" + NGAYTHAMGIA + "]";
    }


}
